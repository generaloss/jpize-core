package jpize.util.postprocess;

import jpize.util.Disposable;

public interface IPostProcessEffect extends Disposable {
    void begin();
    void end();
    void end(IPostProcessEffect target);
}
