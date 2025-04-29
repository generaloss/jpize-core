package jpize.util.postprocess;

import jpize.opengl.framebuffer.GlFramebuffer;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.gl.GL;
import jpize.opengl.shader.Shader;
import jpize.opengl.texture.Texture2D;
import jpize.util.RenderQuad;
import jpize.util.math.vector.Vec2f;
import jpize.util.res.Resource;

public class MipChainedBloom implements IPostProcessEffect {

    private static final int MAX_MIPS = 5;

    private final GlFramebuffer sceneBuffer;
    private final GlFramebuffer brightBuffer;
    private final GlFramebuffer[] mipChain;
    private final Shader brightShader, blurShader, combineShader;

    private float brightness = 1F;
    private float bloom = 1F;
    private float exposure = 1F;
    private float gamma = 1F;

    public MipChainedBloom() {
        this.sceneBuffer = new GlFramebuffer()
            .attach(FramebufferAttachment.color(0))
            .checkCompletionError();

        this.brightBuffer = new GlFramebuffer()
            .attach(FramebufferAttachment.color(0))
            .checkCompletionError();

        this.mipChain = new GlFramebuffer[MAX_MIPS];
        for(int i = 0; i < MAX_MIPS; i++) {
            this.mipChain[i] = new GlFramebuffer()
                .attach(FramebufferAttachment.color(0))
                .checkCompletionError();
        }

        final Resource vert = Resource.internal("/shader/bloom/vert.glsl");
        this.brightShader = new Shader(vert, Resource.internal("/shader/bloom/bright.frag"));
        this.blurShader = new Shader(vert, Resource.internal("/shader/bloom/blur_mip.frag"));
        this.combineShader = new Shader(vert, Resource.internal("/shader/bloom/combine.frag"));
    }

    @Override
    public void begin() {
        sceneBuffer.bind();
        GL.clearColorBuffer();
    }

    @Override
    public void end() {
        sceneBuffer.unbind();

        // bright pixels
        brightBuffer.bind();
        GL.clearColorBuffer();
        {
            brightShader.bind();
            brightShader.uniform("u_frame", sceneBuffer.getTextureAttachment(0).getTexture());
            brightShader.uniform("u_brightness", brightness);
            RenderQuad.instance().render();
        }
        brightBuffer.unbind();

        // gen mip chain
        Texture2D currentTex = brightBuffer.getTextureAttachment(0).getTexture();
        for(int i = 0; i < MAX_MIPS; i++) {
            final GlFramebuffer mip = mipChain[i];

            mip.bind();
            GL.clearColorBuffer();

            Vec2f texelSize = new Vec2f(1F / currentTex.getWidth(), 1F / currentTex.getHeight());

            // horizontal blur
            blurShader.bind();
            blurShader.uniform("u_frame", currentTex);
            blurShader.uniform("u_texelSize", texelSize);
            blurShader.uniform("u_direction", new Vec2f(1F, 0F));
            RenderQuad.instance().render();

            mip.unbind();

            // vertical blur
            mip.bind();
            GL.clearColorBuffer();

            blurShader.bind();
            blurShader.uniform("u_frame", mip.getTextureAttachment(0).getTexture());
            blurShader.uniform("u_texelSize", new Vec2f(1F / mip.getWidth(), 1F / mip.getHeight()));
            blurShader.uniform("u_direction", new Vec2f(0F, 1F));
            RenderQuad.instance().render();

            mip.unbind();

            currentTex = mip.getTextureAttachment(0).getTexture(); // next level
        }

        // apply bloom
        combineShader.bind();
        combineShader.uniform("u_frame1", sceneBuffer.getTextureAttachment(0).getTexture());
        combineShader.uniform("u_exposure", exposure);
        combineShader.uniform("u_gamma", gamma);

        // sum levels
        for(int i = 0; i < MAX_MIPS; i++)
            combineShader.uniform("u_frame" + (i + 2), mipChain[i].getTextureAttachment(0).getTexture());

        combineShader.uniform("u_bloom", bloom);
        RenderQuad.instance().render();
    }

    public void resize(int width, int height) {
        sceneBuffer.resize(width, height);
        brightBuffer.resize(width, height);

        int mipWidth = width / 2;
        int mipHeight = height / 2;

        for(GlFramebuffer mip: mipChain) {
            mip.resize(mipWidth, mipHeight);
            mipWidth = Math.max(1, mipWidth / 2);
            mipHeight = Math.max(1, mipHeight / 2);
        }
    }

    @Override
    public void dispose() {
        sceneBuffer.dispose();
        brightBuffer.dispose();
        for(GlFramebuffer mip: mipChain)
            mip.dispose();
        brightShader.dispose();
        blurShader.dispose();
        combineShader.dispose();
    }

    @Override
    public void end(IPostProcessEffect target) {
        // no implementation
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
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

}