package jpize.gl.tesselation;

import jpize.gl.Gl;
import jpize.gl.glenum.GlTarget;
import jpize.util.math.Maths;

import java.awt.*;
import java.util.Hashtable;

public class GlScissor<T> {

    private int index;
    private final Hashtable<T, Rectangle> scissors;

    public GlScissor(int index) {
        this.index = index;
        this.scissors = new Hashtable<>();
    }

    public GlScissor() {
        this(0);
    }


    public int getIndex() {
        return index;
    }

    public GlScissor<T> setIndex(int index) {
        this.index = index;
        return this;
    }

    public Hashtable<T, Rectangle> getScissors() {
        return scissors;
    }


    public GlScissor<T> put(T handle, Rectangle scissor) {
        scissors.put(handle, scissor);
        return this;
    }

    public GlScissor<T> put(T handle, int x, int y, int width, int height) {
        return this.put(handle, new Rectangle(x, y, width, height));
    }

    public GlScissor<T> put(T handle, double x, double y, double width, double height) {
        return this.put(handle, Maths.round(x), Maths.round(y), Maths.round(width), Maths.round(height));
    }


    public GlScissor<T> remove(T handle) {
        scissors.remove(handle);
        return this;
    }


    public GlScissor<T> apply() {
        if(scissors.isEmpty()){
            Gl.disable(GlTarget.SCISSOR_TEST);
            return this;
        }

        Gl.enable(GlTarget.SCISSOR_TEST);
        
        if(scissors.size() == 1){
            final Rectangle scissor = scissors.values().iterator().next();
            Gl.scissorIndexed(index, scissor.x, scissor.y, scissor.width, scissor.height);
            return this;
        }

        final int[] array = new int[scissors.size() * 4];
        int i = 0;
        for(Rectangle scissor: scissors.values()){
            array[i    ] = scissor.x;
            array[i + 1] = scissor.y;
            array[i + 2] = scissor.width;
            array[i + 3] = scissor.height;
            i += 4;
        }

        Gl.scissorArray(index, array);
        return this;
    }

    public GlScissor<T> disable() {
        Gl.disable(GlTarget.SCISSOR_TEST);
        return this;
    }
    
}
