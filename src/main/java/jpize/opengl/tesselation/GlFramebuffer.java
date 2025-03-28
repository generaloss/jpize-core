package jpize.opengl.tesselation;

import jpize.opengl.buffer.GlAttachment;
import jpize.opengl.gl.GLI11;
import jpize.opengl.gl.GLI30;
import jpize.opengl.texture.*;
import jpize.opengl.type.GlType;
import jpize.util.postprocess.RenderQuad;
import jpize.context.Jpize;
import jpize.opengl.GlObject;

import java.nio.ByteBuffer;

public class GlFramebuffer extends GlObject {

    private int width, height;
    private GlAttachment attachment;
    private boolean draw, read;
    private GlInternalFormat format;
    private GlType type;
    private final Texture2D texture;

    public GlFramebuffer(int width, int height) {
        super(Jpize.GL30.glGenFramebuffers());
        
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
        return GlFramebufferStatus.byValue(Jpize.GL30.glCheckFramebufferStatus(GLI30.GL_FRAMEBUFFER));
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
        Jpize.GL30.glFramebufferTexture2D(GLI30.GL_FRAMEBUFFER, attachment.value, GLI11.GL_TEXTURE_2D, texture.getID(), 0);
        Jpize.GL30.glDrawBuffer(draw ? attachment.value : GLI11.GL_NONE);
        Jpize.GL30.glReadBuffer(read ? attachment.value : GLI11.GL_NONE);
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
        Jpize.GL30.glCopyTexSubImage2D(GLI11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, width, height);
        this.unbind();
    }

    public ByteBuffer getBuffer(Texture2D texture) {
        this.bind();
        final int width = texture.getWidth();
        final int height = texture.getHeight();

        final GlBaseFormat format = texture.getInternalFormat().base;
        final ByteBuffer buffer = Jpize.allocator.memCalloc(width * height * format.channels);
        Jpize.GL30.glReadPixels(0, 0, width, height, format.value, type.value, buffer);

        this.unbind();
        return buffer;
    }
    

    public void renderToScreen() {
        this.unbind();
        RenderQuad.instance().render(texture);
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
        Jpize.GL30.glBindFramebuffer(GLI30.GL_FRAMEBUFFER, ID);
        return this;
    }
    
    
    @Override
    public void dispose() {
        Jpize.GL30.glDeleteFramebuffers(ID);
        texture.dispose();
    }
    
    
    public GlFramebuffer unbind() {
        Jpize.GL30.glBindFramebuffer(GLI30.GL_FRAMEBUFFER, 0);
        return this;
    }

}
