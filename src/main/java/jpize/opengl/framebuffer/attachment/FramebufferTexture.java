package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GLFramebuffer;
import jpize.opengl.texture.GLInternalFormat;
import jpize.opengl.texture.GLWrap;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.type.GLType;

public class FramebufferTexture extends FramebufferAttachment {

    protected GLInternalFormat format;
    protected GLType type;
    protected final Texture2D texture;

    public FramebufferTexture(GLAttachment attachment, GLInternalFormat format, GLType type) {
        super(attachment);

        this.format = format;
        this.type = type;
        this.texture = new Texture2D()
            .setWrapST(GLWrap.CLAMP_TO_EDGE);
    }


    public GLInternalFormat getFormat() {
        return format;
    }

    public void setFormat(GLInternalFormat format) {
        this.format = format;
    }

    public GLType getType() {
        return type;
    }

    public void setType(GLType type) {
        this.type = type;
    }


    public Texture2D getTexture() {
        return texture;
    }


    private void setTextureImage(int width, int height) {
        texture.setImage(width, height, 0, format, type);
    }

    @Override
    public void attach(GLFramebuffer framebuffer) {
        this.setTextureImage(framebuffer.getWidth(), framebuffer.getHeight());
        framebuffer.framebufferTexture2D(super.attachment, texture);
    }

    @Override
    public void resize(int width, int height) {
        this.setTextureImage(width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
