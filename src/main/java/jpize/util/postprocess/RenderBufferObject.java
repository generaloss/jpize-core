package jpize.util.postprocess;

import jpize.app.Jpize;
import jpize.gl.GlObject;
import jpize.gl.buffer.GlAttachment;
import jpize.gl.texture.*;
import jpize.gl.type.GlType;
import jpize.gl.texture.GlTexture2D;

import static org.lwjgl.opengl.GL33.*;

public class RenderBufferObject extends GlObject {

    private int width, height;
    private GlAttachment attachment;
    private final GlTexture2D texture;

    public RenderBufferObject(int width, int height) {
        super(glGenRenderbuffers());
        
        this.width = width;
        this.height = height;
        this.attachment = GlAttachment.DEPTH_ATTACHMENT;

        this.texture = new GlTexture2D()
            .setWrapST(GlWrap.CLAMP_TO_EDGE).setFilters(GlFilter.NEAREST).setMaxLevel(0)
            .setDefaultImage(width, height);
    }

    public RenderBufferObject() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public void setAttachment(GlAttachment attachment) {
        this.attachment = attachment;
    }

    public void create() {
        updateTexture();
        
        bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.value, GL_TEXTURE_2D, texture.getID(), 0);
        glRenderbufferStorage(GL_RENDERBUFFER, GlInternalFormat.DEPTH_COMPONENT32.value, width, height);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment.value, GL_RENDERBUFFER, ID);
        unbind();
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        updateTexture();

        bind();
        glRenderbufferStorage(GL_RENDERBUFFER, GlInternalFormat.DEPTH_COMPONENT32.value, width, height);
        unbind();
    }

    private void updateTexture() {
        texture.setImage(0, width, height, GlInternalFormat.DEPTH_COMPONENT32, GlType.FLOAT);
        texture.generateMipmap();
    }
    

    public GlTexture getTexture() {
        return texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void bind() {
        glBindRenderbuffer(GL_RENDERBUFFER, ID);
    }

    @Override
    public void dispose() {
        glDeleteRenderbuffers(ID);
        texture.dispose();
    }

    public void unbind() {
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }

}
