package jpize.util.postprocess;

import generaloss.resourceflow.Disposable;

public interface IPostProcessEffect extends Disposable {

    void begin();

    void end();

    void end(IPostProcessEffect target);

}
