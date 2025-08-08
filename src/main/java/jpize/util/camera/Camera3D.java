package jpize.util.camera;

import generaloss.spatialmath.vector.Vec3f;

public abstract class Camera3D extends Camera {

    protected final Vec3f position;

    public Camera3D(int width, int height) {
        super(width, height);
        this.position = new Vec3f();
    }


    public Vec3f position() {
        return position;
    }

    public abstract Vec3f getDirection();


    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getZ() {
        return position.z;
    }


    public abstract float getFOV();

    public abstract void setFOV(float fieldOfView);


    public abstract float getFar();

    public abstract void setFar(float far);

    public abstract float getNear();

    public abstract void setNear(float near);

}
