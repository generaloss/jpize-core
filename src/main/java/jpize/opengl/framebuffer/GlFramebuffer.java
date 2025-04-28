package jpize.opengl.framebuffer;

import jpize.opengl.framebuffer.attachment.GlAttachment;
import jpize.opengl.gl.GL11I;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.framebuffer.renderbuffer.GlRenderbuffer;
import jpize.opengl.texture.*;
import jpize.util.MemoryUtils;
import jpize.util.postprocess.RenderQuad;
import jpize.context.Jpize;
import jpize.opengl.GlObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GlFramebuffer extends GlObject {

    private final GlFramebufferTarget target;
    private int width, height;
    private boolean draw, read;
    private final List<FramebufferAttachment> attachments;

    public GlFramebuffer(GlFramebufferTarget target, int width, int height) {
        super(Jpize.GL30.glGenFramebuffers());
        this.target = target;

        this.width = width;
        this.height = height;
        this.draw = true;
        this.read = true;

        this.attachments = new ArrayList<>();
    }

    public GlFramebuffer(int width, int height) {
        this(GlFramebufferTarget.FRAMEBUFFER, width, height);
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


    public boolean isRead() {
        return read;
    }

    public GlFramebuffer setRead(boolean read) {
        this.read = read;
        return this;
    }

    public boolean isDraw() {
        return draw;
    }

    public GlFramebuffer setDraw(boolean draw) {
        this.draw = draw;
        return this;
    }


    public GlFramebufferStatus checkStatus() {
        return GlFramebufferStatus.byValue(Jpize.GL30.glCheckFramebufferStatus(target.value));
    }

    public void checkCompletionError() {
        final GlFramebufferStatus status = this.checkStatus();
        if(status != GlFramebufferStatus.COMPLETE)
            throw new IllegalStateException("Framebuffer is not complete: " + status);
    }


    private void setDrawReadBuffer(GlAttachment attachment) {
        Jpize.GL30.glDrawBuffer(draw ? attachment.value : GL11I.GL_NONE);
        Jpize.GL30.glReadBuffer(read ? attachment.value : GL11I.GL_NONE);
    }

    public GlFramebuffer attachTexture2D(GlAttachment attachment, Texture2D texture) {
        Jpize.GL30.glFramebufferTexture2D(target.value, attachment.value, Texture2D.TARGET.value, texture.getID(), 0);
        if(attachment.isColor())
            this.setDrawReadBuffer(attachment);
        return this;
    }

    public GlFramebuffer attachRenderbuffer(GlAttachment attachment, GlRenderbuffer renderbuffer) {
        Jpize.GL30.glFramebufferRenderbuffer(target.value, attachment.value, GlRenderbuffer.TAGET.value, renderbuffer.getID());
        this.setDrawReadBuffer(attachment);
        return this;
    }

    public GlFramebuffer attach(FramebufferAttachment attachment) {
        this.bind();
        attachments.add(attachment);
        attachment.attach(this);
        this.unbind();
        return this;
    }


    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        // resize attachments
        for(FramebufferAttachment attachment: attachments)
            attachment.resize(width, height);
    }


    public void copyTo(Texture2D target) {
        this.bind();
        target.bind();
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

    public void renderToScreen(FramebufferTexture attachment) {
        this.unbind();
        RenderQuad.instance().render(attachment.getTexture());
    }
    
    public GlFramebuffer bind() {
        Jpize.GL30.glBindFramebuffer(target.value, ID);
        return this;
    }
    
    
    @Override
    public void dispose() {
        Jpize.GL30.glDeleteFramebuffers(ID);
    }
    
    
    public GlFramebuffer unbind() {
        Jpize.GL30.glBindFramebuffer(target.value, 0);
        return this;
    }

}