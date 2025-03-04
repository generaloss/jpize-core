package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.opengl.gl.Gl;
import jpize.util.camera.PerspectiveCamera;
import jpize.util.shader.ShaderBuilder;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.type.GlType;
import jpize.opengl.vertex.GlVertAttr;
import jpize.util.mesh.Mesh;
import jpize.opengl.shader.Shader;
import jpize.util.math.axisaligned.AABox;
import jpize.util.math.axisaligned.AABoxBody;

public class RenderTest extends JpizeApplication {

    private final AABoxBody box1, box2;
    private final Mesh mesh;
    private final Shader shader;
    private final PerspectiveCamera camera;

    public RenderTest() {
        // boxes
        this.box1 = new AABoxBody(new AABox(-1, -1, -1, 1, 1, 1));
        this.box2 = new AABoxBody(new AABox(-1, -1, -1, 1, 1, 1));
        this.box1.position().set(-2, 0, 0);
        this.box2.position().set( 2, 0, 0);
        // mesh
        this.mesh = new Mesh(new GlVertAttr(3, GlType.FLOAT), new GlVertAttr(4, GlType.FLOAT));
        this.mesh.setMode(GlPrimitive.QUADS);
        this.mesh.vertices().setData(
            -1,  1, 4,  1, 0, 0, 1F,
            -1, -1, 4,  1, 0, 0, 1F,
             1, -1, 4,  1, 0, 0, 1F,
             1,  1, 4,  1, 0, 0, 1F
        );
        // camera
        this.camera = new PerspectiveCamera(0.1, 100, 85);
        // GL
        Gl.clearColor(0.05, 0.05, 0.1);

        // shader
        this.shader = ShaderBuilder.create()
            .attribute("vec3", "pos")
            .attribute("vec4", "color")
            .uniform("mat4", "u_combined")
            .mainVert("gl_Position = u_combined * vec4(v_pos, 1);")
            .mainFrag("gl_FragColor = pow(f_color, vec4(0.75));")
            .build();

        shader.setBinaryRetrievable(true);
        System.out.println(shader.getBinaryLength());
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        shader.bind();
        camera.update();
        shader.uniform("u_combined", camera.getCombined());
        mesh.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }


    public static void main(String[] args) {
        Jpize.create(1280, 720, "Render Test").build().setApp(new RenderTest());
        Jpize.run();
    }

}
