package jpize.opengl.query;

import jpize.context.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.gl.GL15I;
import jpize.opengl.type.GlBool;
import jpize.util.time.TimeUtils;

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
        return Jpize.GL15.glGetQueryObjecti(ID, GL15I.GL_QUERY_RESULT);
    }

    public boolean isResultAvailable() {
        return GlBool.of(Jpize.GL15.glGetQueryObjecti(ID, GL15I.GL_QUERY_RESULT_AVAILABLE));
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
