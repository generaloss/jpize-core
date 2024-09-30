package jpize.gl.tesselation;

import jpize.gl.buffer.GlAttachment;
import jpize.gl.texture.*;
import jpize.gl.type.GlType;
import jpize.util.postprocess.ScreenQuad;
import jpize.util.postprocess.ScreenQuadShader;
import org.lwjgl.BufferUtils;
import jpize.app.Jpize;
import jpize.gl.GlObject;
import jpize.gl.texture.Texture2D;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL33.*;

public class GlFramebuffer extends GlObject {

    private int width, height;
    private GlAttachment attachment;
    private final Texture2D texture;
    private boolean draw, read;
    private GlInternalFormat format;
    private GlType type;

    public GlFramebuffer(int width, int height) {
        super(glGenFramebuffers());
        
        this.width = width;
        this.height = height;
        this.attachment = GlAttachment.color(0);
        this.draw = true;
        this.read = true;
        
        this.texture = new Texture2D()
            .setWrapST(GlWrap.CLAMP_TO_EDGE).setFilters(GlFilter.NEAREST).setMaxLevel(0)
            .setDefaultImage(width, height);
    }

    public GlFramebuffer() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public GlFramebuffer setAttachment(GlAttachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public GlFramebuffer setRead(boolean read) {
        this.read = read;
        return this;
    }

    public GlFramebuffer setWrite(boolean draw) {
        this.draw = draw;
        return this;
    }

    public GlFramebuffer setInternalFormat(GlInternalFormat format) {
        this.format = format;
        return this;
    }

    public GlFramebuffer setType(GlType type) {
        this.type = type;
        return this;
    }


    public GlFramebuffer create() {
        updateTexture();
        
        bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.value, GL_TEXTURE_2D, texture.getID(), 0);
        glDrawBuffer(draw ? attachment.value : GL_NONE);
        glReadBuffer(read ? attachment.value : GL_NONE);
        unbind();
        return this;
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        updateTexture();
    }

    private void updateTexture() {
        texture.setImage(0, width, height, GlInternalFormat.DEPTH_COMPONENT32, GlType.FLOAT);
        texture.generateMipmap();
    }


    public void copyTo(Texture2D texture) {
        bind();
        texture.bind();
        glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, width, height);
        unbind();
    }

    public ByteBuffer getBuffer(Texture2D texture) {
        bind();
        final int width = texture.getWidth();
        final int height = texture.getHeight();

        final GlBaseFormat format = texture.getInternalFormat().base;
        final ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * format.channels);
        glReadPixels(0, 0, width, height, format.value, GlType.UNSIGNED_BYTE.value, buffer);

        unbind();
        return buffer;
    }
    

    public void renderToScreen() {
        unbind();

        ScreenQuadShader.use(texture);
        ScreenQuad.render();
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
    
    public GlFramebuffer bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, ID);
        return this;
    }
    
    
    @Override
    public void dispose() {
        glDeleteFramebuffers(ID);
        texture.dispose();
    }
    
    
    public GlFramebuffer unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        return this;
    }

}
