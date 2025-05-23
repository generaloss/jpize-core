package jpize.opengl.query;

import jpize.context.Jpize;
import jpize.opengl.GLObject;
import jpize.opengl.gl.GL15I;
import jpize.opengl.type.GLBool;
import jpize.util.time.TimeUtils;

public class GLQuery extends GLObject {
    
    private GLQueryTarget type;
    
    public GLQuery(GLQueryTarget type) {
        super(Jpize.GL15.glGenQueries());
        this.type = type;
    }
    
    public void begin() {
        Jpize.GL15.glBeginQuery(type.value, ID);
    }
    
    public void end() {
        Jpize.GL15.glEndQuery(type.value);
    }

    public void beginEndAction(Runnable action) {
        this.begin();
        try {
            action.run();
        }finally{
            this.end();
        }
    }


    public GLQueryTarget getType() {
        return type;
    }

    public void setType(GLQueryTarget type) {
        this.type = type;
    }


    public int getResult() {
        return Jpize.GL15.glGetQueryObjecti(ID, GL15I.GL_QUERY_RESULT);
    }

    public boolean isResultAvailable() {
        return GLBool.of(Jpize.GL15.glGetQueryObjecti(ID, GL15I.GL_QUERY_RESULT_AVAILABLE));
    }

    public int waitForResult() {
        TimeUtils.waitFor(this::isResultAvailable);
        return this.getResult();
    }


    @Override
    public void dispose() {
        Jpize.GL15.glDeleteQueries(ID);
    }
    
}
