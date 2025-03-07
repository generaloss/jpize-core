package jpize.opengl.query;

import jpize.app.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.gl.GLI15;

public class GlQuery extends GlObject {
    
    private GlQueryTarget type;
    
    public GlQuery(GlQueryTarget type) {
        super(Jpize.GL15.glGenQueries());
        this.type = type;
    }
    
    public void begin() {
        Jpize.GL15.glBeginQuery(type.value, ID);
    }
    
    public void end() {
        Jpize.GL15.glEndQuery(type.value);
    }


    public GlQueryTarget getType() {
        return type;
    }

    public void setType(GlQueryTarget type) {
        this.type = type;
    }


    public int getResult() {
        return Jpize.GL15.glGetQueryObjecti(ID, GLI15.GL_QUERY_RESULT);
    }

    public boolean isResultAvailable() {
        return (Jpize.GL15.glGetQueryObjecti(ID, GLI15.GL_QUERY_RESULT_AVAILABLE) == GLI15.GL_TRUE);
    }

    public int waitForResult() {
        while(!isResultAvailable());
        return getResult();
    }


    @Override
    public void dispose() {
        Jpize.GL15.glDeleteQueries(ID);
    }
    
}
