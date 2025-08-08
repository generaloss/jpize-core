package jpize.util.camera;

import spatialmath.vector.Vec2f;

public abstract class Camera2D extends Camera {

    protected final Vec2f position;

    public Camera2D(int width, int height) {
        super(width, height);
        position = new Vec2f();
    }


    public Vec2f position() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }


    public abstract float getRotation();

    public abstract float getScale();


    public float getScaledWidth() {
        return super.getWidth() / this.getScale();
    }

    public float getScaledHeight() {
        return super.getHeight() / this.getScale();
    }

    public float getScaledHalfWidth() {
        return super.getHalfWidth() / this.getScale();
    }

    public float getScaledHalfHeight() {
        return super.getHalfHeight() / this.getScale();
    }

}
