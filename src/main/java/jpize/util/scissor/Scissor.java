package jpize.util.scissor;

import jpize.gl.Gl;
import jpize.gl.glenum.GlTarget;
import jpize.util.TextureBatch;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class Scissor {
    
    private final TextureBatch batch;
    private final Map<Long, ScissorNode> scissorList;
    private ScissorNode active;
    
    public Scissor(TextureBatch batch) {
        this.batch = batch;
        this.scissorList = new HashMap<>();
    }


    public ScissorNode getActive() {
        return active;
    }
    
    public void begin(long index, double x, double y, double width, double height) {
        this.begin(index, Maths.round(x), Maths.round(y), Maths.round(width), Maths.round(height));
    }
    
    public void begin(long index, double x, double y, double width, double height, long parentIndex) {
        this.begin(index, Maths.round(x), Maths.round(y), Maths.round(width), Maths.round(height), parentIndex);
    }
    
    public void begin(long index, int x, int y, int width, int height) {
        this.begin(index, x, y, width, height, -1L);
    }
    
    public void begin(ScissorNode scissor) {
        if(scissor.getIndex() < 0L)
            return;
        
        if(scissor.getParentIndex() != -1L && !scissorList.isEmpty()){
            final ScissorNode parent = scissorList.get(scissor.getParentIndex());
            
            final int parentX2 = parent.getX2();
            final int parentY2 = parent.getY2();
            
            final int oldX2 = scissor.getX2();
            final int oldY2 = scissor.getY2();
            
            scissor.getRectangle().x = Math.max(Math.min(scissor.getX(), parentX2), parent.getX());
            scissor.getRectangle().y = Math.max(Math.min(scissor.getY(), parentY2), parent.getY());
            scissor.getRectangle().width  = Math.max(0, Math.min(Math.min(scissor.getWidth() , oldX2 - parent.getX()), Math.min(parentX2, scissor.getX2()) - scissor.getX()));
            scissor.getRectangle().height = Math.max(0, Math.min(Math.min(scissor.getHeight(), oldY2 - parent.getY()), Math.min(parentY2, scissor.getY2()) - scissor.getY()));
        }
        
        scissorList.put(scissor.getIndex(), scissor);
        
        batch.render();
        if(!Gl.isEnabled(GlTarget.SCISSOR_TEST))
            Gl.enable(GlTarget.SCISSOR_TEST);
        scissor.activate();
        active = scissor;
    }
    
    public void begin(long index, int x, int y, int width, int height, long parentIndex) {
        if(index < 0L)
            return;
        
        if(parentIndex != -1L && !scissorList.isEmpty()){
            final ScissorNode parent = scissorList.get(parentIndex);
            
            final int parentX2 = parent.getX2();
            final int parentY2 = parent.getY2();
            
            final int oldX2 = x + width;
            final int oldY2 = y + height;
            
            x = Math.max(Math.min(x, parentX2), parent.getX());
            y = Math.max(Math.min(y, parentY2), parent.getY());
            
            final int x2 = x + width;
            final int y2 = y + height;
            
            width  = Math.max(0, Math.min(Math.min(width , oldX2 - parent.getX()), Math.min(parentX2, x2) - x));
            height = Math.max(0, Math.min(Math.min(height, oldY2 - parent.getY()), Math.min(parentY2, y2) - y));
        }
        
        final ScissorNode scissor = new ScissorNode(index, x, y, width, height, parentIndex);
        scissorList.put(index, scissor);
        
        batch.render();
        if(!Gl.isEnabled(GlTarget.SCISSOR_TEST))
            Gl.enable(GlTarget.SCISSOR_TEST);
        scissor.activate();
        active = scissor;
    }
    
    public void end(long index) {
        final ScissorNode removedScissor = scissorList.remove(index);
        final long removedParent = removedScissor.getParentIndex();
        
        batch.render();
        if(removedParent != -1L && !scissorList.isEmpty()){
            final ScissorNode scissor = scissorList.get(removedParent);
            scissor.activate();
            active = scissor;
            
        }else if(Gl.isEnabled(GlTarget.SCISSOR_TEST)){
            Gl.disable(GlTarget.SCISSOR_TEST);
            active = null;
        }
    }


    public boolean contains(double x, double y) {
        if(isActive())
            return active.getRectangle().contains(x, y);
        return true;
    }

    public boolean contains(Vec2d vec2) {
        return contains(vec2.x, vec2.y);
    }

    public boolean contains(Vec2f vec2) {
        return contains(vec2.x, vec2.y);
    }

    public boolean contains(Vec2i vec2) {
        return contains(vec2.x, vec2.y);
    }


    public boolean contains(double x, double y, double width, double height) {
        if(isActive())
            return active.getRectangle().contains(x, y, width, height);
        return true;
    }

    public boolean contains(Rectangle2D rectangle) {
        return contains(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }


    public boolean intersects(double x, double y, double width, double height) {
        if(isActive())
            return active.getRectangle().intersects(x, y, width, height);
        return true;
    }

    public boolean intersects(Rectangle2D rectangle) {
        return intersects(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public boolean isActive() {
        return active != null;
    }
    
}
