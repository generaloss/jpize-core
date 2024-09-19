package jpize.util.postprocess.effects;

import jpize.app.Jpize;
import jpize.util.Disposable;
import jpize.util.res.Resource;
import jpize.gl.Gl;
import jpize.gl.buffer.GlAttachment;
import jpize.gl.texture.GlFilter;
import jpize.gl.texture.GlInternalFormat;
import jpize.gl.texture.GlWrap;
import jpize.gl.type.GlType;
import jpize.util.postprocess.FrameBufferObject;
import jpize.gl.texture.GlTexture2D;
import jpize.util.shader.Shader;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec3f;

import static org.lwjgl.opengl.GL33.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL33.glClear;

public class ShadowMapping implements Disposable {

    private final int width, height;
    private final Vec3f pos, dir;
    private final FrameBufferObject fbo;
    private final Shader shader;
    private final Matrix4f projectionMatrix, spaceMatrix, lookAtMatrix;

    public ShadowMapping(int width, int height, Vec3f size, Vec3f pos, Vec3f dir) {
        this.pos = pos;
        this.dir = dir.nor();

        this.width = width;
        this.height = height;

        // framebuffer
        this.fbo = new FrameBufferObject(width, height);

        this.fbo.setAttachment(GlAttachment.DEPTH_ATTACHMENT);
        this.fbo.setWrite(false);
        this.fbo.setRead(false);

        this.fbo.setInternalFormat(GlInternalFormat.DEPTH_COMPONENT32).setType(GlType.FLOAT);
        this.fbo.getTexture().setWrapST(GlWrap.CLAMP_TO_BORDER).setFilters(GlFilter.NEAREST).setBorderColor(1, 1, 1);

        this.fbo.create();

        // shader
        this.shader = new Shader(Resource.internal("/shader/shadowMapping/shadow.vert"), Resource.internal("/shader/shadowMapping/shadow.frag"));

        this.projectionMatrix = new Matrix4f().setOrthographic(-size.x / 2, size.x / 2, size.y / 2, -size.y / 2, 1, size.z + 1);
        this.spaceMatrix = new Matrix4f();
        this.lookAtMatrix = new Matrix4f();
    }


    public void begin() {
        Gl.viewport(width, height);
        fbo.bind();
        glClear(GL_DEPTH_BUFFER_BIT);
        
        shader.bind();
        spaceMatrix.set(projectionMatrix.getMul(lookAtMatrix.setLookAlong(pos, dir)));
        shader.uniform("u_space", spaceMatrix);
    }

    public void end() {
        fbo.unbind();
        Gl.viewport(Jpize.getWidth(), Jpize.getHeight());
    }

    public GlTexture2D getShadowMap() {
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
