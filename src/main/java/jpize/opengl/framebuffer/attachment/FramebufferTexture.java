package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GlFramebuffer;
import jpize.opengl.texture.GlInternalFormat;
import jpize.opengl.texture.GlWrap;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.type.GlType;

public class FramebufferTexture extends FramebufferAttachment {

    protected GlInternalFormat format;
    protected GlType type;
    protected final Texture2D texture;

    public FramebufferTexture(GlAttachment attachment, GlInternalFormat format, GlType type) {
        super(attachment);

        this.format = format;
        this.type = type;
        this.texture = new Texture2D()
            .setWrapST(GlWrap.CLAMP_TO_EDGE);
    }


    public GlInternalFormat getFormat() {
        return format;
    }

    public void setFormat(GlInternalFormat format) {
        this.format = format;
    }

    public GlType getType() {
        return type;
    }

    public void setType(GlType type) {
        this.type = type;
    }


    public Texture2D getTexture() {
        return texture;
    }


    private void setTextureImage(int width, int height) {
        texture.setImage(width, height, 0, format, type);
    }

    @Override
    public void attach(GlFramebuffer framebuffer) {
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
