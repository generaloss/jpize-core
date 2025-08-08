package jpize.opengl.tesselation;

import jpize.opengl.gl.GL;
import jpize.opengl.glenum.GLTarget;
import generaloss.spatialmath.Maths;
import generaloss.spatialmath.geometry.Recti;

import java.util.Hashtable;

public class GLScissor<T> {

    private int index;
    private final Hashtable<T, Recti> scissors;

    public GLScissor(int index) {
        this.index = index;
        this.scissors = new Hashtable<>();
    }

    public GLScissor() {
        this(0);
    }


    public int getIndex() {
        return index;
    }

    public GLScissor<T> setIndex(int index) {
        this.index = index;
        return this;
    }

    public Hashtable<T, Recti> getScissors() {
        return scissors;
    }


    public GLScissor<T> put(T handle, Recti scissor) {
        scissors.put(handle, scissor);
        return this;
    }

    public GLScissor<T> put(T handle, int x, int y, int width, int height) {
        return this.put(handle, new Recti(x, y, width, height));
    }

    public GLScissor<T> put(T handle, double x, double y, double width, double height) {
        return this.put(handle, Maths.round(x), Maths.round(y), Maths.round(width), Maths.round(height));
    }


    public GLScissor<T> remove(T handle) {
        scissors.remove(handle);
        return this;
    }


    public GLScissor<T> apply() {
        if(scissors.isEmpty()){
            GL.disable(GLTarget.SCISSOR_TEST);
            return this;
        }

        GL.enable(GLTarget.SCISSOR_TEST);
        
        if(scissors.size() == 1){
            final Recti scissor = scissors.values().iterator().next();
            GL.scissorIndexed(index, scissor.x, scissor.y, scissor.width, scissor.height);
            return this;
        }

        final int[] array = new int[scissors.size() * 4];
        int i = 0;
        for(Recti scissor: scissors.values()){
            array[i++] = scissor.x;
            array[i++] = scissor.y;
            array[i++] = scissor.width;
            array[i++] = scissor.height;
        }

        GL.scissorArray(index, array);
        return this;
    }

    public GLScissor<T> disable() {
        GL.disable(GLTarget.SCISSOR_TEST);
        return this;
    }
    
}
