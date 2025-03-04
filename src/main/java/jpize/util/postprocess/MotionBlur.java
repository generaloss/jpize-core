package jpize.util.postprocess;

import jpize.app.Jpize;
import jpize.util.res.Resource;
import jpize.opengl.gl.Gl;
import jpize.opengl.tesselation.GlFramebuffer;
import jpize.opengl.tesselation.GlRenderbuffer;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.shader.Shader;

public class MotionBlur implements PostProcessEffect {

    private final GlFramebuffer fbo1, fbo2;
    private final GlRenderbuffer rbo1, rbo2;
    private final Texture2D backframe;
    private final Shader shader;

    public MotionBlur() {
        final int width = Jpize.getWidth();
        final int height = Jpize.getHeight();

        // framebuffer 1 & renderbuffer 1
        this.fbo1 = new GlFramebuffer(width, height);
        this.fbo1.create();
        this.fbo1.bind();
        this.rbo1 = new GlRenderbuffer(width, height);
        this.rbo1.create();
        this.fbo1.unbind();

        // framebuffer 2 & renderbuffer 2
        this.fbo2 = new GlFramebuffer(width, height);
        this.fbo2.create();
        this.fbo2.bind();
        this.rbo2 = new GlRenderbuffer(width, height);
        this.rbo2.create();
        this.fbo2.unbind();

        // shader (quad)
        this.shader = new Shader(Resource.internal("/shader/motion_blur/vert.glsl"), Resource.internal("/shader/motion_blur/frag.glsl"));

        // texture (previous frame)
        this.backframe = new Texture2D(width, height);
    }


    @Override
    public void begin() {
        // draw scene in fbo 1
        fbo1.bind();
        rbo1.bind();
        Gl.clearColorDepthBuffers();
    }

    @Override
    public void end() {
        fbo1.unbind();
        rbo1.unbind();

        // draw scene + fbo 1 in fbo 2
        fbo2.bind();
        rbo2.bind();
        Gl.clearColorDepthBuffers();
        {
            shader.bind();
            shader.uniform("u_frame", fbo1.getTexture());
            shader.uniform("u_backFrame", backframe);
            RenderQuad.instance().render();
        }
        fbo2.unbind();
        rbo2.unbind();

        // copy fbo 2 as backframe
        fbo2.copyTo(backframe);

        // draw scene + fbo 1 on screen
        shader.bind();
        shader.uniform("u_frame", fbo1.getTexture());
        shader.uniform("u_backFrame", backframe);
        RenderQuad.instance().render();
    }

    @Override
    public void end(PostProcessEffect target) {
        fbo1.unbind();
        rbo1.unbind();

        // draw scene + fbo 1 in fbo 2
        fbo2.bind();
        rbo2.bind();
        Gl.clearColorDepthBuffers();
        {
            shader.bind();
            shader.uniform("u_frame", fbo1.getTexture());
            shader.uniform("u_backFrame", backframe);
            RenderQuad.instance().render();
        }
        fbo2.unbind();
        rbo2.unbind();

        // copy fbo 2 as backframe
        fbo2.copyTo(backframe);

        // draw scene + fbo 1 in target
        target.begin();

        shader.bind();
        shader.uniform("u_frame", fbo1.getTexture());
        shader.uniform("u_backFrame", backframe);
        RenderQuad.instance().render();
    }

    public void resize(int width, int height) {
        fbo1.resize(width, height);
        fbo2.resize(width, height);
        rbo1.resize(width, height);
        rbo2.resize(width, height);
        backframe.setDefaultImage(width, height);
    }

    @Override
    public void dispose() {
        fbo1.dispose();
        fbo2.dispose();
        rbo1.dispose();
        rbo2.dispose();
        backframe.dispose();
        shader.dispose();
    }

}
