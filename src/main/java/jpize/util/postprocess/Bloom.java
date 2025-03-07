package jpize.util.postprocess;

import jpize.context.Jpize;
import jpize.util.res.Resource;
import jpize.opengl.gl.Gl;
import jpize.opengl.tesselation.GlFramebuffer;
import jpize.opengl.tesselation.GlRenderbuffer;
import jpize.opengl.shader.Shader;

public class Bloom implements PostProcessEffect {

    private final GlFramebuffer colorBuffer, fbo2, blurBuffer;
    private final GlRenderbuffer depthBuffer;
    private final Shader brightShader, blurShader, combineShader;
    private float brightness, radius, bloom, exposure, gamma;

    public Bloom(float brightness, float radius) {
        this.brightness = brightness;
        this.radius = radius;

        this.bloom = 1F;
        this.exposure = 2F;
        this.gamma = 0.6F;

        final int width = Jpize.getWidth();
        final int height = Jpize.getHeight();

        // Frame Buffer 1 & Render Buffer
        this.colorBuffer = new GlFramebuffer(width, height);
        this.colorBuffer.create();
        this.colorBuffer.bind();
        this.depthBuffer = new GlRenderbuffer(width, height);
        this.depthBuffer.create();
        this.colorBuffer.unbind();

        // Frame Buffers 2 & 3
        this.fbo2 = new GlFramebuffer(width, height);
        this.fbo2.create();
        this.blurBuffer = new GlFramebuffer(width, height);
        this.blurBuffer.create();

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
        depthBuffer.bind();
        colorBuffer.bind();
        Gl.clearColorDepthBuffers();
    }

    @Override
    public void end() {
        depthBuffer.unbind();
        colorBuffer.unbind();

        fbo2.bind();
        Gl.clearColorBuffer();
        {
            brightShader.bind();
            brightShader.uniform("u_frame", colorBuffer.getTexture());
            brightShader.uniform("u_brightness", brightness);
            RenderQuad.instance().render();
        }
        fbo2.unbind();

        blurBuffer.bind();
        Gl.clearColorBuffer();
        {
            blurShader.bind();
            blurShader.uniform("u_frame", fbo2.getTexture());
            blurShader.uniform("u_radius", radius);
            RenderQuad.instance().render();
        }
        blurBuffer.unbind();

        combineShader.bind();
        combineShader.uniform("u_frame1", colorBuffer.getTexture());
        combineShader.uniform("u_frame2", blurBuffer.getTexture());
        combineShader.uniform("u_bloom", bloom);
        combineShader.uniform("u_exposure", exposure);
        combineShader.uniform("u_gamma", gamma);
        RenderQuad.instance().render();
    }

    @Override
    public void end(PostProcessEffect target) { }

    public void resize(int width, int height) {
        depthBuffer.resize(width, height);
        colorBuffer.resize(width, height);
        fbo2.resize(width, height);
        blurBuffer.resize(width, height);
    }

    @Override
    public void dispose() {
        depthBuffer.dispose();
        colorBuffer.dispose();
        fbo2.dispose();
        blurBuffer.dispose();
        brightShader.dispose();
        blurShader.dispose();
        combineShader.dispose();
    }

}
