package jpize.util.mesh;

import jpize.util.buffer.VertexBuffer;
import jpize.util.Disposable;

public interface IMesh extends Disposable {

    void render();

    VertexBuffer getBuffer();

}
