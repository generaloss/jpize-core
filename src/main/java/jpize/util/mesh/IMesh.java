package jpize.util.mesh;

import jpize.opengl.buffer.GlVertexBuffer;
import jpize.util.Disposable;

public interface IMesh extends Disposable {

    void render();

    GlVertexBuffer vertices();

}
