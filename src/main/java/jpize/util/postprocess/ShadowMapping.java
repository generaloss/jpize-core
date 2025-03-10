package jpize.util.postprocess;

import jpize.context.Jpize;
import jpize.opengl.gl.GLI11;
import jpize.util.Disposable;
import jpize.util.res.Resource;
import jpize.opengl.gl.Gl;
import jpize.opengl.buffer.GlAttachment;
import jpize.opengl.texture.GlFilter;
import jpize.opengl.texture.GlInternalFormat;
import jpize.opengl.texture.GlWrap;
import jpize.opengl.type.GlType;
import jpize.opengl.tesselation.GlFramebuffer;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.shader.Shader;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec3f;

public class ShadowMapping implements Disposable {

    private final int width, height;
    private final Vec3f pos, dir;
    private final GlFramebuffer fbo;
    private final Shader shader;
    private final Matrix4f projectionMatrix, spaceMatrix, lookAtMatrix;

    public ShadowMapping(int width, int height, Vec3f size, Vec3f pos, Vec3f dir) {
        this.pos = pos;
        this.dir = dir.nor();

        this.width = width;
        this.height = height;

        // framebuffer
        this.fbo = new GlFramebuffer(width, height);

        this.fbo.setAttachment(GlAttachment.DEPTH_ATTACHMENT);
        this.fbo.setWrite(false);
        this.fbo.setRead(false);

        this.fbo.setInternalFormat(GlInternalFormat.DEPTH_COMPONENT32).setType(GlType.FLOAT);
        this.fbo.getTexture().setWrapST(GlWrap.CLAMP_TO_BORDER).setFilters(GlFilter.NEAREST).setBorderColor(1F, 1F, 1F, 1F);

        this.fbo.create();

        // shader
        this.shader = new Shader(Resource.internal("/shader/shadow_mapping/vert.glsl"), Resource.internal("/shader/shadow_mapping/frag.glsl"));

        this.projectionMatrix = new Matrix4f().setOrthographic(-size.x * 0.5F, size.x * 0.5F, size.y * 0.5F, -size.y * 0.5F, 1F, size.z + 1F);
        this.spaceMatrix = new Matrix4f();
        this.lookAtMatrix = new Matrix4f();
    }


    public void begin() {
        Gl.viewport(width, height);
        fbo.bind();
        Jpize.GL11.glClear(GLI11.GL_DEPTH_BUFFER_BIT);
        
        shader.bind();
        spaceMatrix.set(projectionMatrix.getMul(lookAtMatrix.setLookAlong(pos, dir)));
        shader.uniform("u_space", spaceMatrix);
    }

    public void end() {
        fbo.unbind();
        Gl.viewport(Jpize.getWidth(), Jpize.getHeight());
    }

    public Texture2D getShadowMap() {
        return fbo.getTexture();
    }

    public Matrix4f getLightSpace() {
        return spaceMatrix;
    }

    public void setModelMatrix(Matrix4f model) {
        shader.uniform("u_model", model);
    }

    public Vec3f getPos() {
        return pos;
    }

    public Vec3f getDir() {
        return dir;
    }

    @Override
    public void dispose() {
        shader.dispose();
        fbo.dispose();
    }

}
