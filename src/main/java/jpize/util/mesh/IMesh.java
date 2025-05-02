package jpize.util.mesh;

import jpize.opengl.buffer.GLVertexBuffer;
import jpize.util.Disposable;

public interface IMesh extends Disposable {

    void render();

    GLVertexBuffer vertices();

}
