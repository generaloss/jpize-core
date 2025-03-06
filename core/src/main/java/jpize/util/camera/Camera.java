package jpize.util.camera;

import jpize.util.math.matrix.Matrix4f;

public abstract class Camera {

    protected int width;
    protected int height;

    public Camera(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract Matrix4f getView();

    public abstract Matrix4f getProjection();

    public abstract Matrix4f getCombined();


    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public float getHalfWidth() {
        return width * 0.5F;
    }

    public float getHalfHeight() {
        return height * 0.5F;
    }

}
