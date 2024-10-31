package jpize.gl.texture;

import jpize.gl.Gl;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.Disposable;
import jpize.util.camera.Camera;
import jpize.util.mesh.IndexedMesh;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapIO;
import jpize.util.res.Resource;
import jpize.gl.shader.Shader;

public class Skybox implements Disposable {

    private final TextureCubeMap cubeMap;
    private final Shader shader;
    private final IndexedMesh mesh;
    private final Matrix4f viewMatrix;

    public Skybox(Pixmap positive_x, Pixmap negative_x, Pixmap positive_y, Pixmap negative_y, Pixmap positive_z, Pixmap negative_z) {
        this.cubeMap = new TextureCubeMap(positive_x, negative_x, positive_y, negative_y, positive_z, negative_z);
        this.shader = new Shader(Resource.internal("/shader/skybox/vert.glsl"), Resource.internal("/shader/skybox/frag.glsl"));

        this.mesh = new IndexedMesh(new GlVertAttr(3, GlType.FLOAT));
        this.mesh.vertices().setData(new float[]{
            -2, -2,  2, // 0
            2, -2,  2, // 1
            -2,  2,  2, // 2
            2,  2,  2, // 3
            -2, -2, -2, // 4
            2, -2, -2, // 5
            -2,  2, -2, // 6
            2,  2, -2  // 7
        });
        this.mesh.indices().setData(
            7, 6, 2,  2, 3, 7, // Top     2, 6, 7,  7, 3, 2,
            0, 4, 5,  5, 1, 0, // Bottom  5, 4, 0,  0, 1, 5,
            0, 2, 6,  6, 4, 0, // Left    6, 2, 0,  0, 4, 6,
            7, 3, 1,  1, 5, 7, // Right   1, 3, 7,  7, 5, 1,
            3, 2, 0,  0, 1, 3, // Front   0, 2, 3,  3, 1, 0,
            4, 6, 7,  7, 5, 4  // Back    7, 6, 4,  4, 5, 7
        );

        this.viewMatrix = new Matrix4f();
    }

    public Skybox(String positive_x, String negative_x, String positive_y, String negative_y, String positive_z, String negative_z) {
        this(
            PixmapIO.load(positive_x), PixmapIO.load(negative_x),
            PixmapIO.load(positive_y), PixmapIO.load(negative_y),
            PixmapIO.load(positive_z), PixmapIO.load(negative_z)
        );
    }


    public void render(Matrix4f projection, Matrix4f view) {
        Gl.depthMask(false);
        shader.bind();
        shader.uniform("u_projection", projection);
        shader.uniform("u_view", view);
        shader.uniform("u_cubeMap", cubeMap);

        mesh.render();
        Gl.depthMask(true);
    }

    public void render(Camera camera) {
        viewMatrix.set(camera.getView()).cullPosition();
        this.render(camera.getProjection(), viewMatrix);
    }
    
    public TextureCubeMap getCubeMap() {
        return cubeMap;
    }

    @Override
    public void dispose() {
        cubeMap.dispose();
        shader.dispose();
        mesh.dispose();
    }

}
