package jpize.util.postprocess;

import jpize.context.Jpize;
import jpize.opengl.framebuffer.Framebuffer3D;
import jpize.opengl.gl.GL11I;
import jpize.util.Disposable;
import jpize.util.res.Resource;
import jpize.opengl.gl.Gl;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.shader.Shader;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec3f;

public class ShadowMapping implements Disposable {

    private final int width, height;
    private final Vec3f pos, dir;
    private final Framebuffer3D framebuffer;
    private final Shader shader;
    private final Matrix4f projectionMatrix, spaceMatrix, lookAtMatrix;

    public ShadowMapping(int width, int height, Vec3f size, Vec3f pos, Vec3f dir) {
        this.pos = pos;
        this.dir = dir.nor();

        this.width = width;
        this.height = height;

        // framebuffer
        this.framebuffer = new Framebuffer3D(width, height);
        this.framebuffer.setDraw(false);
        this.framebuffer.setRead(false);

        // shader
        this.shader = new Shader(Resource.internal("/shader/shadow_mapping/vert.glsl"), Resource.internal("/shader/shadow_mapping/frag.glsl"));

        this.projectionMatrix = new Matrix4f().setOrthographic(-size.x * 0.5F, size.x * 0.5F, size.y * 0.5F, -size.y * 0.5F, 1F, size.z + 1F);
        this.spaceMatrix = new Matrix4f();
        this.lookAtMatrix = new Matrix4f();
    }


    public void begin() {
        Gl.viewport(width, height);
        framebuffer.bind();
        Jpize.GL11.glClear(GL11I.GL_DEPTH_BUFFER_BIT);
        
        shader.bind();
        spaceMatrix.set(projectionMatrix.getMul(lookAtMatrix.setLookAlong(pos, dir)));
        shader.uniform("u_space", spaceMatrix);
    }

    public void end() {
        framebuffer.unbind();
        Gl.viewport(Jpize.getWidth(), Jpize.getHeight());
    }

    public Texture2D getShadowMap() {
        return framebuffer.getDepthTexture();
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
        framebuffer.dispose();
    }

}
