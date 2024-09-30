package jpize.gl.tesselation;

import jpize.gl.Gl;
import jpize.gl.glenum.GlTarget;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class Scissor {
    
    private final Runnable onFlush;
    private final Map<Long, Node> scissors;
    private Node active;
    
    public Scissor(Runnable onFlush) {
        this.onFlush = onFlush;
        this.scissors = new HashMap<>();
    }


    public Node getActive() {
        return active;
    }
    
    public void begin(long ID, double x, double y, double width, double height) {
        this.begin(ID, Maths.round(x), Maths.round(y), Maths.round(width), Maths.round(height));
    }
    
    public void begin(long ID, double x, double y, double width, double height, long parentIndex) {
        this.begin(ID, Maths.round(x), Maths.round(y), Maths.round(width), Maths.round(height), parentIndex);
    }
    
    public void begin(long ID, int x, int y, int width, int height) {
        this.begin(ID, x, y, width, height, -1L);
    }
    
    public void begin(Node scissor) {
        if(scissor.getID() < 0L)
            return;
        
        if(scissor.getParentID() != -1L && !scissors.isEmpty()){
            final Node parent = scissors.get(scissor.getParentID());
            
            final int parentX2 = parent.getX2();
            final int parentY2 = parent.getY2();
            
            final int oldX2 = scissor.getX2();
            final int oldY2 = scissor.getY2();
            
            scissor.getRectangle().x = Math.max(Math.min(scissor.getX(), parentX2), parent.getX());
            scissor.getRectangle().y = Math.max(Math.min(scissor.getY(), parentY2), parent.getY());
            scissor.getRectangle().width  = Math.max(0, Math.min(Math.min(scissor.getWidth() , oldX2 - parent.getX()), Math.min(parentX2, scissor.getX2()) - scissor.getX()));
            scissor.getRectangle().height = Math.max(0, Math.min(Math.min(scissor.getHeight(), oldY2 - parent.getY()), Math.min(parentY2, scissor.getY2()) - scissor.getY()));
        }
        
        scissors.put(scissor.getID(), scissor);

        onFlush.run();
        if(!Gl.isEnabled(GlTarget.SCISSOR_TEST))
            Gl.enable(GlTarget.SCISSOR_TEST);
        scissor.activate();
        active = scissor;
    }
    
    public void begin(long ID, int x, int y, int width, int height, long parentIndex) {
        if(ID < 0L)
            return;
        
        if(parentIndex != -1L && !scissors.isEmpty()){
            final Node parent = scissors.get(parentIndex);
            
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
        
        final Node scissor = new Node(ID, x, y, width, height, parentIndex);
        scissors.put(ID, scissor);

        onFlush.run();
        if(!Gl.isEnabled(GlTarget.SCISSOR_TEST))
            Gl.enable(GlTarget.SCISSOR_TEST);
        scissor.activate();
        active = scissor;
    }
    
    public void end(long ID) {
        final Node removedScissor = scissors.remove(ID);
        final long removedParent = removedScissor.getParentID();

        onFlush.run();
        if(removedParent != -1L && !scissors.isEmpty()){
            final Node scissor = scissors.get(removedParent);
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


    public static class Node {

        private final long ID, parentID;
        private final Rectangle rectangle;

        public Node(long ID, Rectangle rectangle, long parentID) {
            this.ID = ID;
            this.rectangle = rectangle;
            this.parentID = parentID;
        }

        public Node(long ID, int x, int y, int width, int height, long parentID) {
            this(ID, new Rectangle(x, y, width, height), parentID);
        }


        public void activate() {
            Gl.scissor(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }


        public long getID() {
            return ID;
        }

        public long getParentID() {
            return parentID;
        }

        public Rectangle getRectangle() {
            return rectangle;
        }


        public int getX() {
            return rectangle.x;
        }

        public int getY() {
            return rectangle.y;
        }

        public int getX2() {
            return rectangle.x + rectangle.width;
        }

        public int getY2() {
            return rectangle.y + rectangle.height;
        }

        public int getWidth() {
            return rectangle.width;
        }

        public int getHeight() {
            return rectangle.height;
        }

    }
    
}
