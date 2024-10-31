package jpize.gl.tesselation;

import jpize.gl.buffer.GlAttachment;
import jpize.gl.texture.*;
import jpize.gl.type.GlType;
import jpize.util.postprocess.ScreenQuadMesh;
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
    private boolean draw, read;
    private GlInternalFormat format;
    private GlType type;
    private final Texture2D texture;

    public GlFramebuffer(int width, int height) {
        super(glGenFramebuffers());
        
        this.width = width;
        this.height = height;
        this.attachment = GlAttachment.color(0);
        this.draw = true;
        this.read = true;
        this.format = GlInternalFormat.RGBA8;
        this.type = GlType.UNSIGNED_BYTE;
        this.texture = new Texture2D().setWrapST(GlWrap.CLAMP_TO_EDGE);
    }

    public GlFramebuffer() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public GlFramebufferStatus checkStatus() {
        return GlFramebufferStatus.byValue(glCheckFramebufferStatus(GL_FRAMEBUFFER));
    }


    public GlFramebuffer setRead(boolean read) {
        this.read = read;
        return this;
    }

    public GlFramebuffer setWrite(boolean draw) {
        this.draw = draw;
        return this;
    }

    public GlFramebuffer setAttachment(GlAttachment attachment) {
        this.attachment = attachment;
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


    private void updateTexture() {
        texture.setImage(width, height, 0, format, type);
    }

    public GlFramebuffer create() {
        this.updateTexture();
        this.bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.value, GL_TEXTURE_2D, texture.getID(), 0);
        glDrawBuffer(draw ? attachment.value : GL_NONE);
        glReadBuffer(read ? attachment.value : GL_NONE);
        this.unbind();
        return this;
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.updateTexture();
    }


    public void copyTo(Texture2D texture) {
        this.bind();
        texture.bind();
        glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, width, height);
        this.unbind();
    }

    public ByteBuffer getBuffer(Texture2D texture) {
        this.bind();
        final int width = texture.getWidth();
        final int height = texture.getHeight();

        final GlBaseFormat format = texture.getInternalFormat().base;
        final ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * format.channels);
        glReadPixels(0, 0, width, height, format.value, type.value, buffer);

        this.unbind();
        return buffer;
    }
    

    public void renderToScreen() {
        this.unbind();
        ScreenQuadShader.bind(texture);
        ScreenQuadMesh.render();
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
