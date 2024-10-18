package jpize.gl.tesselation;

import jpize.app.Jpize;
import jpize.gl.GlObject;
import jpize.gl.buffer.GlAttachment;
import jpize.gl.texture.*;
import jpize.gl.type.GlType;
import jpize.gl.texture.Texture2D;

import static org.lwjgl.opengl.GL33.*;

public class GlRenderbuffer extends GlObject {

    private int width, height;
    private GlAttachment attachment;
    private GlInternalFormat format;
    private GlType type;
    private final Texture2D texture;

    public GlRenderbuffer(int width, int height) {
        super(glGenRenderbuffers());
        
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
        texture.setImage(0, width, height, format, type);
    }

    public GlRenderbuffer create() {
        this.updateTexture();
        this.bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.value, GL_TEXTURE_2D, texture.getID(), 0);
        glRenderbufferStorage(GL_RENDERBUFFER, format.value, width, height);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment.value, GL_RENDERBUFFER, ID);
        this.unbind();
        return this;
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.updateTexture();

        this.bind();
        glRenderbufferStorage(GL_RENDERBUFFER, format.value, width, height);
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
        glBindRenderbuffer(GL_RENDERBUFFER, ID);
        return this;
    }

    @Override
    public void dispose() {
        glDeleteRenderbuffers(ID);
        texture.dispose();
    }

    public GlRenderbuffer unbind() {
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
        return this;
    }

}
