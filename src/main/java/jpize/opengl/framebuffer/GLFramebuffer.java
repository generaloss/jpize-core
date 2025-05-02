package jpize.opengl.framebuffer;

import jpize.opengl.framebuffer.attachment.GLAttachment;
import jpize.opengl.gl.GL11I;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.framebuffer.renderbuffer.GLRenderbuffer;
import jpize.opengl.gl.GL30I;
import jpize.opengl.texture.*;
import jpize.util.MemoryUtils;
import jpize.util.array.IntList;
import jpize.util.math.vector.Vec2i;
import jpize.util.RenderQuad;
import jpize.context.Jpize;
import jpize.opengl.GLObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GLFramebuffer extends GLObject {

    private final GLFramebufferTarget target;
    private int width, height;
    private final boolean multisample;
    private GLFramebuffer resolveFramebuffer;
    private final List<FramebufferAttachment> attachments;

    public GLFramebuffer(GLFramebufferTarget target, int width, int height, boolean multisample) {
        super(Jpize.GL30.glGenFramebuffers());
        this.target = target;

        this.width = width;
        this.height = height;
        this.multisample = multisample;

        if(multisample)
            this.resolveFramebuffer = new GLFramebuffer(target, width, height, false);

        this.attachments = new ArrayList<>();
    }

    public GLFramebuffer(int width, int height, boolean multisample) {
        this(GLFramebufferTarget.FRAMEBUFFER, width, height, multisample);
    }

    public GLFramebuffer(int width, int height) {
        this(width, height, false);
    }

    public GLFramebuffer() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public GLFramebufferTarget getTarget() {
        return target;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMultisample() {
        return multisample;
    }


    public Iterable<FramebufferAttachment> getAttachments() {
        return attachments;
    }

    public <T extends FramebufferAttachment> T getAttachment(int index) {
        // noinspection unchecked
        return (T) attachments.get(index);
    }

    public FramebufferTexture getTextureAttachment(int index) {
        return (FramebufferTexture) attachments.get(index);
    }


    public GLFramebufferStatus checkStatus() {
        return GLFramebufferStatus.byValue(Jpize.GL30.glCheckFramebufferStatus(target.value));
    }

    public GLFramebuffer checkCompletionError() {
        final GLFramebufferStatus status = this.checkStatus();
        if(status != GLFramebufferStatus.COMPLETE)
            throw new IllegalStateException("Framebuffer is not complete: " + status);
        return this;
    }


    public void framebufferTexture2D(GLAttachment attachment, Texture2D texture) {
        final int textureID = (texture == null ? 0 : texture.getID());
        Jpize.GL30.glFramebufferTexture2D(target.value, attachment.value, Texture2D.TARGET.value, textureID, 0);
    }

    public void framebufferRenderbuffer(GLAttachment attachment, GLRenderbuffer renderbuffer) {
        final int renderbufferID = (renderbuffer == null ? 0 : renderbuffer.getID());
        Jpize.GL30.glFramebufferRenderbuffer(target.value, attachment.value, GLRenderbuffer.TARGET.value, renderbufferID);
    }


    public GLFramebuffer attach(FramebufferAttachment attachment) {
        attachments.add(attachment);

        this.bind();
        attachment.attach(this);
        this.updateDrawBuffers();
        this.unbind();

        return this;
    }

    public GLFramebuffer attach(FramebufferAttachment... attachments) {
        for(FramebufferAttachment attachment: attachments)
            this.attach(attachment);
        return this;
    }


    public GLFramebuffer detach(FramebufferAttachment attachment) {
        this.bind();

        if(attachments.remove(attachment)) {
            final GLAttachment glAttachment = attachment.getAttachment();

            if(glAttachment.isColor() || glAttachment == GLAttachment.DEPTH || glAttachment == GLAttachment.STENCIL) {
                this.framebufferTexture2D(glAttachment, null);
            }else{
                this.framebufferRenderbuffer(glAttachment, null);
            }

            this.updateDrawBuffers();
        }

        this.unbind();
        return this;
    }

    public GLFramebuffer detach(FramebufferAttachment... attachments) {
        for(FramebufferAttachment attachment: attachments)
            this.detach(attachment);
        return this;
    }


    private void updateDrawBuffers() {
        final IntList colorAttachments = new IntList();

        for(FramebufferAttachment attachment: attachments) {
            final GLAttachment glAttachment = attachment.getAttachment();
            if(glAttachment.isColor())
                colorAttachments.add(glAttachment.value);
        }

        if(colorAttachments.isEmpty()) {
            Jpize.GL30.glDrawBuffer(GL11I.GL_NONE);
            Jpize.GL30.glReadBuffer(GL11I.GL_NONE);
        }else{
            colorAttachments.trim();
            Jpize.GL30.glDrawBuffers(colorAttachments.array());
        }
    }


    public void resize(int width, int height) {
        if(Vec2i.equals(width, height, this.width, this.height))
            return;

        this.width = width;
        this.height = height;

        if(resolveFramebuffer != null)
            resolveFramebuffer.resize(width, height);

        // resize attachments
        for(FramebufferAttachment attachment: attachments)
            attachment.resize(width, height);
    }


    public void resolveMultisampling() {
        if(resolveFramebuffer == null)
            return;

        this.bind(GLFramebufferTarget.READ_FRAMEBUFFER);
        resolveFramebuffer.bind(GLFramebufferTarget.DRAW_FRAMEBUFFER);

        Jpize.GL30.glBlitFramebuffer(
                0, 0, width, height,
                0, 0, width, height,
                GL11I.GL_COLOR_BUFFER_BIT, GL11I.GL_NEAREST
        );

        this.unbind(GLFramebufferTarget.READ_FRAMEBUFFER);
        this.unbind(GLFramebufferTarget.DRAW_FRAMEBUFFER);
    }


    public void renderToScreen(FramebufferTexture attachment) {
        if(multisample) {
            this.resolveMultisampling();
            resolveFramebuffer.renderToScreen(attachment);
        }else{
            RenderQuad.instance().render(attachment.getTexture());
        }
    }

    public void copyTo(Texture2D target) {
        this.bind();
        target.bind();
        Jpize.GL11.glReadBuffer(GL30I.GL_COLOR_ATTACHMENT0);
        Jpize.GL30.glCopyTexSubImage2D(Texture2D.TARGET.value, 0, 0, 0, 0, 0, width, height);
        this.unbind();
    }

    public ByteBuffer getBuffer(FramebufferTexture attachment) {
        this.bind();

        final GLBaseFormat format = attachment.getFormat().base;
        final int size = (width * height * format.channels);
        final ByteBuffer buffer = MemoryUtils.alloc(size);
        Jpize.GL30.glReadPixels(0, 0, width, height, format.value, attachment.getType().value, buffer);

        this.unbind();
        return buffer;
    }

    
    public GLFramebuffer bind(GLFramebufferTarget target) {
        Jpize.GL30.glBindFramebuffer(target.value, ID);
        return this;
    }

    public GLFramebuffer bind() {
        return this.bind(target);
    }

    public GLFramebuffer unbind(GLFramebufferTarget target) {
        Jpize.GL30.glBindFramebuffer(target.value, 0);
        return this;
    }

    public GLFramebuffer unbind() {
        return this.unbind(target);
    }


    @Override
    public void dispose() {
        Jpize.GL30.glDeleteFramebuffers(ID);
        if(resolveFramebuffer != null)
            resolveFramebuffer.dispose();
    }

}