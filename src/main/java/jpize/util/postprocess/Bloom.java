package jpize.util.postprocess;

import jpize.opengl.framebuffer.Framebuffer2D;
import jpize.opengl.framebuffer.Framebuffer3D;
import jpize.util.res.Resource;
import jpize.opengl.gl.Gl;
import jpize.opengl.shader.Shader;

public class Bloom implements IPostProcessEffect {

    private final Framebuffer3D colorBuffer;
    private final Framebuffer2D fbo2, blurBuffer;
    private final Shader brightShader, blurShader, combineShader;
    private float brightness, radius, bloom, exposure, gamma;

    public Bloom(float brightness, float radius) {
        this.brightness = brightness;
        this.radius = radius;

        this.bloom = 1F;
        this.exposure = 2F;
        this.gamma = 0.6F;

        // Frame Buffer 1 & Render Buffer
        this.colorBuffer = new Framebuffer3D();

        // Frame Buffers 2 & 3
        this.fbo2 = new Framebuffer2D();
        this.blurBuffer = new Framebuffer2D();

        // Shader
        final Resource vertexShaderRes = Resource.internal("/shader/bloom/vert.glsl");

        this.brightShader = new Shader(vertexShaderRes, Resource.internal("/shader/bloom/bright.frag"));
        this.blurShader = new Shader(vertexShaderRes, Resource.internal("/shader/bloom/blur.frag"));
        this.combineShader = new Shader(vertexShaderRes, Resource.internal("/shader/bloom/combine.frag"));
    }


    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setBloom(float bloom) {
        this.bloom = bloom;
    }

    public void setExposure(float exposure) {
        this.exposure = exposure;
    }

    public void setGamma(float gamma) {
        this.gamma = gamma;
    }

    @Override
    public void begin() {
        // draw scene in fbo 1
        colorBuffer.begin();
    }

    @Override
    public void end() {
        colorBuffer.end();

        fbo2.begin();
        Gl.clearColorBuffer();
        {
            brightShader.bind();
            brightShader.uniform("u_frame", colorBuffer.getColorTexture());
            brightShader.uniform("u_brightness", brightness);
            RenderQuad.instance().render();
        }
        fbo2.end();

        blurBuffer.begin();
        Gl.clearColorBuffer();
        {
            blurShader.bind();
            blurShader.uniform("u_frame", fbo2.getColorTexture());
            blurShader.uniform("u_radius", radius);
            RenderQuad.instance().render();
        }
        blurBuffer.end();

        combineShader.bind();
        combineShader.uniform("u_frame1", colorBuffer.getColorTexture());
        combineShader.uniform("u_frame2", blurBuffer.getColorTexture());
        combineShader.uniform("u_bloom", bloom);
        combineShader.uniform("u_exposure", exposure);
        combineShader.uniform("u_gamma", gamma);
        RenderQuad.instance().render();
    }

    @Override
    public void end(IPostProcessEffect target) { }

    public void resize(int width, int height) {
        colorBuffer.resize(width, height);
        fbo2.resize(width, height);
        blurBuffer.resize(width, height);
    }

    @Override
    public void dispose() {
        colorBuffer.dispose();
        fbo2.dispose();
        blurBuffer.dispose();
        brightShader.dispose();
        blurShader.dispose();
        combineShader.dispose();
    }

}