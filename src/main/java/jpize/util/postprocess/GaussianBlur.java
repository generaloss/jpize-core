package jpize.util.postprocess;

import jpize.app.Jpize;
import jpize.gl.Gl;
import jpize.gl.tesselation.GlFramebuffer;
import jpize.gl.tesselation.GlRenderbuffer;
import jpize.gl.shader.Shader;
import jpize.util.res.Resource;

public class GaussianBlur implements PostProcessEffect {

    private final GlFramebuffer fbo1, fbo2;
    private final GlRenderbuffer rbo;
    private final Shader shader;
    private float radius;

    public GaussianBlur(float radius) {
        this.radius = radius;

        final int width = Jpize.getWidth();
        final int height = Jpize.getHeight();

        // framebuffer 1 & renderbuffer
        this.fbo1 = new GlFramebuffer(width, height);
        this.fbo1.create();
        this.fbo1.bind();
        this.rbo = new GlRenderbuffer(width, height);
        this.rbo.create();
        this.fbo1.unbind();

        // framebuffer 2
        this.fbo2 = new GlFramebuffer(width, height);
        this.fbo2.create();

        // shader (quad)
        this.shader = new Shader(Resource.internal("/shader/gaussian_blur/vert.glsl"), Resource.internal("/shader/gaussian_blur/frag.glsl"));
    }


    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void begin() {
        // draw scene in fbo 1
        rbo.bind();
        fbo1.bind();
        Gl.clearColorDepthBuffers();
    }

    @Override
    public void end() {
        fbo1.unbind();
        rbo.unbind();

        // draw fbo 1 + blurX in fbo 2
        fbo2.bind();
        Gl.clearColorBuffer();
        {
            shader.bind();
            shader.uniform("u_frame", fbo1.getTexture());
            shader.uniform("u_axis", 0);
            shader.uniform("u_radius", radius);
            RenderQuad.instance().render();
        }
        fbo2.unbind();

        // draw fbo 2 + blurY on screen
        shader.bind();
        shader.uniform("u_frame", fbo2.getTexture());
        shader.uniform("u_axis", 1);
        shader.uniform("u_radius", radius);
        RenderQuad.instance().render();
    }

    @Override
    public void end(PostProcessEffect target) {
        fbo1.unbind();
        rbo.unbind();

        // draw fbo 1 + blurX in fbo 2
        fbo2.bind();
        Gl.clearColorBuffer();
        {
            shader.bind();
            shader.uniform("u_frame", fbo1.getTexture());
            shader.uniform("u_axis", 0);
            shader.uniform("u_radius", radius);
            RenderQuad.instance().render();
        }
        fbo2.unbind();

        // draw fbo 2 + blurY in target
        target.begin();

        shader.bind();
        shader.uniform("u_frame", fbo2.getTexture());
        shader.uniform("u_axis", 1);
        shader.uniform("u_radius", radius);
        RenderQuad.instance().render();
    }

    public void resize(int width, int height) {
        fbo1.resize(width, height);
        fbo2.resize(width, height);

        rbo.resize(width, height);
    }

    @Override
    public void dispose() {
        fbo1.dispose();
        fbo2.dispose();

        rbo.dispose();

        shader.dispose();
    }

}
