package jpize.util.postprocess;

import jpize.util.Disposable;

public interface PostProcessEffect extends Disposable {
    void begin();
    void end();
    void end(PostProcessEffect target);
}
