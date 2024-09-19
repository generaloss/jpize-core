package jpize.util.scissor;

import jpize.gl.Gl;

import java.awt.*;

public class ScissorNode {
    
    private final long index, parentIndex;
    private final Rectangle rectangle;
    
    public ScissorNode(long index, Rectangle rectangle, long parentIndex) {
        this.index = index;
        this.rectangle = rectangle;
        this.parentIndex = parentIndex;
    }
    
    public ScissorNode(long index, int x, int y, int width, int height, long parentIndex) {
        this(index, new Rectangle(x, y, width, height), parentIndex);
    }
    
    
    public void activate() {
        Gl.scissor(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    
    public long getIndex() {
        return index;
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public long getParentIndex() {
        return parentIndex;
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
