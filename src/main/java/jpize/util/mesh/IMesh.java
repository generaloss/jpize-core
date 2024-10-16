package jpize.util.mesh;

import jpize.gl.buffer.VertexBuffer;
import jpize.util.Disposable;

public interface IMesh extends Disposable {

    void render();

    VertexBuffer vertices();

}
