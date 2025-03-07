package jpize.opengl.tesselation;

import jpize.app.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.buffer.GlAttachment;
import jpize.opengl.gl.GLI11;
import jpize.opengl.gl.GLI30;
import jpize.opengl.texture.*;
import jpize.opengl.type.GlType;
import jpize.opengl.texture.Texture2D;

public class GlRenderbuffer extends GlObject {

    private int width, height;
    private GlAttachment attachment;
    private GlInternalFormat format;
    private GlType type;
    private final Texture2D texture;

    public GlRenderbuffer(int width, int height) {
        super(Jpize.GL30.glGenRenderbuffers());
        
        this.width = width;
        this.height = height;
        this.attachment = GlAttachment.DEPTH_ATTACHMENT;
        this.format = GlInternalFormat.DEPTH_COMPONENT32;
        this.type = GlType.FLOAT;
        this.texture = new Texture2D().setWrapST(GlWrap.CLAMP_TO_EDGE);
    }

    public GlRenderbuffer() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public GlRenderbuffer setAttachment(GlAttachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public GlRenderbuffer setInternalFormat(GlInternalFormat format) {
        this.format = format;
        return this;
    }

    public GlRenderbuffer setType(GlType type) {
        this.type = type;
        return this;
    }


    private void updateTexture() {
        texture.setImage(width, height, 0, format, type);
    }

    public GlRenderbuffer create() {
        this.updateTexture();
        this.bind();
        Jpize.GL30.glFramebufferTexture2D(GLI30.GL_FRAMEBUFFER, attachment.value, GLI11.GL_TEXTURE_2D, texture.getID(), 0);
        Jpize.GL30.glRenderbufferStorage(GLI30.GL_RENDERBUFFER, format.value, width, height);
        Jpize.GL30.glFramebufferRenderbuffer(GLI30.GL_FRAMEBUFFER, attachment.value, GLI30.GL_RENDERBUFFER, ID);
        this.unbind();
        return this;
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.updateTexture();

        this.bind();
        Jpize.GL30.glRenderbufferStorage(GLI30.GL_RENDERBUFFER, format.value, width, height);
        this.unbind();
    }


    public Texture2D getTexture() {
        return texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public GlRenderbuffer bind() {
        Jpize.GL30.glBindRenderbuffer(GLI30.GL_RENDERBUFFER, ID);
        return this;
    }

    @Override
    public void dispose() {
        Jpize.GL30.glDeleteRenderbuffers(ID);
        texture.dispose();
    }

    public GlRenderbuffer unbind() {
        Jpize.GL30.glBindRenderbuffer(GLI30.GL_RENDERBUFFER, 0);
        return this;
    }

}
