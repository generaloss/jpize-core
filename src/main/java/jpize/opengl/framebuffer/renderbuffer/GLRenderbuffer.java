package jpize.opengl.framebuffer.renderbuffer;

import jpize.context.Jpize;
import jpize.opengl.GLObject;
import jpize.opengl.texture.*;

public class GLRenderbuffer extends GLObject {

    public static final GLRenderbufferTarget TARGET = GLRenderbufferTarget.RENDERBUFFER;

    private int width, height;
    private GLInternalFormat format;
    private final int samples;

    public GLRenderbuffer(int width, int height, GLInternalFormat format, int samples) {
        super(Jpize.GL30.glGenRenderbuffers());
        
        this.width = width;
        this.height = height;
        this.format = format;
        this.samples = samples;

        this.setStorage(width, height);
    }

    public GLRenderbuffer(int width, int height, GLInternalFormat format) {
        this(width, height, format, 0);
    }

    public GLRenderbuffer(GLInternalFormat format) {
        this(Jpize.getWidth(), Jpize.getHeight(), format);
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public GLInternalFormat getFormat() {
        return format;
    }

    public void setFormat(GLInternalFormat format) {
        this.format = format;
    }


    public void setStorage(int width, int height) {
        this.bind();

        if(samples > 0) {
            Jpize.GL30.glRenderbufferStorageMultisample(TARGET.value, samples, format.value, width, height);
        }else{
            Jpize.GL30.glRenderbufferStorage(TARGET.value, format.value, width, height);
        }

        this.unbind();
    }


    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.setStorage(width, height);
    }


    public GLRenderbuffer bind() {
        Jpize.GL30.glBindRenderbuffer(TARGET.value, ID);
        return this;
    }

    public GLRenderbuffer unbind() {
        Jpize.GL30.glBindRenderbuffer(TARGET.value, 0);
        return this;
    }


    @Override
    public void dispose() {
        Jpize.GL30.glDeleteRenderbuffers(ID);
    }

}