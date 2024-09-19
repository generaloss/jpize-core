package jpize.util.postprocess;

import jpize.gl.texture.*;
import jpize.gl.type.GlType;
import org.lwjgl.BufferUtils;
import jpize.app.Jpize;
import jpize.gl.buffer.GlAttachment;
import jpize.gl.GlObject;
import jpize.gl.texture.GlTexture2D;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL33.*;

public class FrameBufferObject extends GlObject {

    private int width, height;
    private GlAttachment attachment;
    private final GlTexture2D texture;
    private boolean draw, read;
    private GlInternalFormat format;
    private GlType type;

    public FrameBufferObject(int width, int height) {
        super(glGenFramebuffers());
        
        this.width = width;
        this.height = height;
        this.attachment = GlAttachment.color(0);
        this.draw = true;
        this.read = true;
        
        this.texture = new GlTexture2D()
            .setWrapST(GlWrap.CLAMP_TO_EDGE).setFilters(GlFilter.NEAREST).setMaxLevel(0)
            .setDefaultImage(width, height);
    }

    public FrameBufferObject() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public FrameBufferObject setAttachment(GlAttachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public FrameBufferObject setRead(boolean read) {
        this.read = read;
        return this;
    }

    public FrameBufferObject setWrite(boolean draw) {
        this.draw = draw;
        return this;
    }

    public FrameBufferObject setInternalFormat(GlInternalFormat format) {
        this.format = format;
        return this;
    }

    public FrameBufferObject setType(GlType type) {
        this.type = type;
        return this;
    }


    public void create() {
        updateTexture();
        
        bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.value, GL_TEXTURE_2D, texture.getID(), 0);
        glDrawBuffer(draw ? attachment.value : GL_NONE);
        glReadBuffer(read ? attachment.value : GL_NONE);
        unbind();
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


    public void copyTo(GlTexture2D texture) {
        bind();
        texture.bind();
        glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, width, height);
        unbind();
    }

    public ByteBuffer getBuffer(GlTexture2D texture) {
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
    

    public GlTexture2D getTexture() {
        return texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, ID);
    }
    
    
    @Override
    public void dispose() {
        glDeleteFramebuffers(ID);
        texture.dispose();
    }
    
    
    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

}
