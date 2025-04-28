package jpize.opengl.framebuffer.renderbuffer;

import jpize.context.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.texture.*;

public class GlRenderbuffer extends GlObject {

    public static final GlRenderbufferTarget TARGET = GlRenderbufferTarget.RENDERBUFFER;

    private int width, height;
    private GlInternalFormat format;
    private final int samples;

    public GlRenderbuffer(int width, int height, GlInternalFormat format, int samples) {
        super(Jpize.GL30.glGenRenderbuffers());
        
        this.width = width;
        this.height = height;
        this.format = format;
        this.samples = samples;

        this.setStorage(width, height);
    }

    public GlRenderbuffer(int width, int height, GlInternalFormat format) {
        this(width, height, format, 0);
    }

    public GlRenderbuffer(GlInternalFormat format) {
        this(Jpize.getWidth(), Jpize.getHeight(), format);
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public GlInternalFormat getFormat() {
        return format;
    }

    public void setFormat(GlInternalFormat format) {
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


    public GlRenderbuffer bind() {
        Jpize.GL30.glBindRenderbuffer(TARGET.value, ID);
        return this;
    }

    public GlRenderbuffer unbind() {
        Jpize.GL30.glBindRenderbuffer(TARGET.value, 0);
        return this;
    }


    @Override
    public void dispose() {
        Jpize.GL30.glDeleteRenderbuffers(ID);
    }

}