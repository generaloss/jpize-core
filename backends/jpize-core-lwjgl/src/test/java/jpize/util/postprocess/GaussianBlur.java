package jpize.util.postprocess;

import jpize.opengl.framebuffer.Framebuffer2D;
import jpize.opengl.framebuffer.Framebuffer3D;
import jpize.opengl.gl.GL;
import jpize.opengl.shader.Shader;
import jpize.util.RenderQuad;
import resourceflow.resource.Resource;

public class GaussianBlur implements IPostProcessEffect {

    private final Framebuffer3D fbo1;
    private final Framebuffer2D fbo2;
    private final Shader shader;
    private float radius;

    public GaussianBlur(float radius) {
        this.radius = radius;

        this.fbo1 = new Framebuffer3D();
        this.fbo2 = new Framebuffer2D();

        // shader (quad)
        this.shader = new Shader(
            Resource.internal("/shader/gaussian_blur/vert.glsl"),
            Resource.internal("/shader/gaussian_blur/frag.glsl")
        );
    }


    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void begin() {
        // draw scene in fbo 1
        fbo1.begin();
    }

    @Override
    public void end() {
        fbo1.end();

        // draw fbo 1 + blurX in fbo 2
        fbo2.begin();
        {
            shader.bind();
            shader.uniform("u_frame", fbo1.getColorTexture());
            shader.uniform("u_axis", 0);
            shader.uniform("u_radius", radius);
            RenderQuad.instance().render();
        }
        fbo2.end();

        // draw fbo 2 + blurY on screen
        shader.bind();
        shader.uniform("u_frame", fbo2.getColorTexture());
        shader.uniform("u_axis", 1);
        shader.uniform("u_radius", radius);
        RenderQuad.instance().render();
    }

    @Override
    public void end(IPostProcessEffect target) {
        fbo1.end();

        // draw fbo 1 + blurX in fbo 2
        fbo2.begin();
        GL.clearColorBuffer();
        {
            shader.bind();
            shader.uniform("u_frame", fbo1.getColorTexture());
            shader.uniform("u_axis", 0);
            shader.uniform("u_radius", radius);
            RenderQuad.instance().render();
        }
        fbo2.end();

        // draw fbo 2 + blurY in target
        target.begin();

        shader.bind();
        shader.uniform("u_frame", fbo2.getColorTexture());
        shader.uniform("u_axis", 1);
        shader.uniform("u_radius", radius);
        RenderQuad.instance().render();
    }

    public void resize(int width, int height) {
        fbo1.resize(width, height);
        fbo2.resize(width, height);
    }

    @Override
    public void dispose() {
        fbo1.dispose();
        fbo2.dispose();
        shader.dispose();
    }

}
