package jpize.util.mesh;

import jpize.opengl.buffer.GLVertexBuffer;
import generaloss.resourceflow.Disposable;

public interface IMesh extends Disposable {

    void render();

    GLVertexBuffer vertices();

}
