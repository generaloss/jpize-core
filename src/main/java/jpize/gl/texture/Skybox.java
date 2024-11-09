package jpize.gl.texture;

import jpize.gl.Gl;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.camera.Camera;
import jpize.util.mesh.IndexedMesh;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.pixmap.Pixmap;
import jpize.util.res.Resource;
import jpize.gl.shader.Shader;

public class Skybox extends TextureCubemap {

    private final Shader shader;
    private final IndexedMesh mesh;
    private final Matrix4f viewMatrix;

    public Skybox() {
        this.shader = new Shader(Resource.internal("/shader/skybox/vert.glsl"), Resource.internal("/shader/skybox/frag.glsl"));
        this.mesh = new IndexedMesh(new GlVertAttr(3, GlType.FLOAT));
        this.mesh.vertices().setData(
            -2F, -2F,  2F, // 0
             2F, -2F,  2F, // 1
            -2F,  2F,  2F, // 2
             2F,  2F,  2F, // 3
            -2F, -2F, -2F, // 4
             2F, -2F, -2F, // 5
            -2F,  2F, -2F, // 6
             2F,  2F, -2F  // 7
        );
        this.mesh.indices().setData(
            7, 6, 2,  2, 3, 7, // top
            0, 4, 5,  5, 1, 0, // bottom
            0, 2, 6,  6, 4, 0, // left
            7, 3, 1,  1, 5, 7, // right
            3, 2, 0,  0, 1, 3, // front
            4, 6, 7,  7, 5, 4  // back
        );

        this.viewMatrix = new Matrix4f();
    }

    public Skybox(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        this();
        super.setDefaultImages(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }

    public Skybox(String positiveX, String negativeX, String positiveY, String negativeY, String positiveZ, String negativeZ) {
        this();
        super.setDefaultImages(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }


    public void render(Matrix4f projection, Matrix4f view) {
        Gl.depthMask(false);
        shader.bind();
        shader.uniform("u_projection", projection);
        shader.uniform("u_view", view);
        shader.uniform("u_cubemap", this);

        mesh.render();
        Gl.depthMask(true);
    }

    public void render(Camera camera) {
        viewMatrix.set(camera.getView()).cullPosition();
        this.render(camera.getProjection(), viewMatrix);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        shader.dispose();
        mesh.dispose();
    }

}
