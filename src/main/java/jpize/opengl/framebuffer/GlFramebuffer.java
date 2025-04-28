package jpize.opengl.framebuffer;

import jpize.opengl.framebuffer.attachment.GlAttachment;
import jpize.opengl.gl.GL11I;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.framebuffer.renderbuffer.GlRenderbuffer;
import jpize.opengl.gl.GL30I;
import jpize.opengl.texture.*;
import jpize.util.MemoryUtils;
import jpize.util.array.IntList;
import jpize.util.math.vector.Vec2i;
import jpize.util.postprocess.RenderQuad;
import jpize.context.Jpize;
import jpize.opengl.GlObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GlFramebuffer extends GlObject {

    private final GlFramebufferTarget target;
    private int width, height;
    private final boolean multisample;
    private GlFramebuffer resolveFramebuffer;
    private final List<FramebufferAttachment> attachments;

    public GlFramebuffer(GlFramebufferTarget target, int width, int height, boolean multisample) {
        super(Jpize.GL30.glGenFramebuffers());
        this.target = target;

        this.width = width;
        this.height = height;
        this.multisample = multisample;

        if(multisample)
            resolveFramebuffer = new GlFramebuffer(target, width, height, false);

        this.attachments = new ArrayList<>();
    }

    public GlFramebuffer(int width, int height, boolean multisample) {
        this(GlFramebufferTarget.FRAMEBUFFER, width, height, multisample);
    }

    public GlFramebuffer(int width, int height) {
        this(width, height, false);
    }

    public GlFramebuffer() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public GlFramebufferTarget getTarget() {
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


    public GlFramebufferStatus checkStatus() {
        return GlFramebufferStatus.byValue(Jpize.GL30.glCheckFramebufferStatus(target.value));
    }

    public GlFramebuffer checkCompletionError() {
        final GlFramebufferStatus status = this.checkStatus();
        if(status != GlFramebufferStatus.COMPLETE)
            throw new IllegalStateException("Framebuffer is not complete: " + status);
        return this;
    }


    public void framebufferTexture2D(GlAttachment attachment, Texture2D texture) {
        final int textureID = (texture == null ? 0 : texture.getID());
        Jpize.GL30.glFramebufferTexture2D(target.value, attachment.value, Texture2D.TARGET.value, textureID, 0);
    }

    public void framebufferRenderbuffer(GlAttachment attachment, GlRenderbuffer renderbuffer) {
        final int renderbufferID = (renderbuffer == null ? 0 : renderbuffer.getID());
        Jpize.GL30.glFramebufferRenderbuffer(target.value, attachment.value, GlRenderbuffer.TARGET.value, renderbufferID);
    }


    public GlFramebuffer attach(FramebufferAttachment attachment) {
        this.bind();

        attachments.add(attachment);
        attachment.attach(this);
        this.updateDrawBuffers();

        this.unbind();
        return this;
    }

    public GlFramebuffer attach(FramebufferAttachment... attachments) {
        for(FramebufferAttachment attachment: attachments)
            this.attach(attachment);
        return this;
    }


    public GlFramebuffer detach(FramebufferAttachment attachment) {
        this.bind();

        if(attachments.remove(attachment)) {
            final GlAttachment glAttachment = attachment.getAttachment();

            if(glAttachment.isColor() || glAttachment == GlAttachment.DEPTH || glAttachment == GlAttachment.STENCIL) {
                this.framebufferTexture2D(glAttachment, null);
            }else{
                this.framebufferRenderbuffer(glAttachment, null);
            }

            this.updateDrawBuffers();
        }

        this.unbind();
        return this;
    }

    public GlFramebuffer detach(FramebufferAttachment... attachments) {
        for(FramebufferAttachment attachment: attachments)
            this.detach(attachment);
        return this;
    }


    private void updateDrawBuffers() {
        final IntList colorAttachments = new IntList();

        for(FramebufferAttachment attachment: attachments) {
            final GlAttachment glAttachment = attachment.getAttachment();
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

        this.bind(GlFramebufferTarget.READ_FRAMEBUFFER);
        resolveFramebuffer.bind(GlFramebufferTarget.DRAW_FRAMEBUFFER);

        Jpize.GL30.glBlitFramebuffer(
                0, 0, width, height,
                0, 0, width, height,
                GL11I.GL_COLOR_BUFFER_BIT, GL11I.GL_NEAREST
        );

        this.unbind(GlFramebufferTarget.READ_FRAMEBUFFER);
        this.unbind(GlFramebufferTarget.DRAW_FRAMEBUFFER);
    }


    public void renderToScreen(FramebufferTexture attachment) {
        if(multisample) {
            this.resolveMultisampling();
            resolveFramebuffer.renderToScreen(attachment);
        }else{
            this.unbind();
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

        final GlBaseFormat format = attachment.getFormat().base;
        final int size = (width * height * format.channels);
        final ByteBuffer buffer = MemoryUtils.alloc(size);
        Jpize.GL30.glReadPixels(0, 0, width, height, format.value, attachment.getType().value, buffer);

        this.unbind();
        return buffer;
    }

    
    public GlFramebuffer bind(GlFramebufferTarget target) {
        Jpize.GL30.glBindFramebuffer(target.value, ID);
        return this;
    }

    public GlFramebuffer bind() {
        return this.bind(target);
    }

    public GlFramebuffer unbind(GlFramebufferTarget target) {
        Jpize.GL30.glBindFramebuffer(target.value, 0);
        return this;
    }

    public GlFramebuffer unbind() {
        return this.unbind(target);
    }


    @Override
    public void dispose() {
        Jpize.GL30.glDeleteFramebuffers(ID);
        if(resolveFramebuffer != null)
            resolveFramebuffer.dispose();
    }

}