package jpize.util.camera;

import jpize.util.math.rot.EulerAngles;
import jpize.util.math.vector.Vec3f;

public abstract class Camera3D extends Camera {

    protected final Vec3f position;
    protected final EulerAngles rotation;

    public Camera3D(int width, int height) {
        super(width, height);
        
        position = new Vec3f();
        rotation = new EulerAngles();
    }


    public Vec3f position() {
        return position;
    }

    public EulerAngles rotation() {
        return rotation;
    }


    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getZ() {
        return position.z;
    }


    public abstract float getFov();

    public abstract void setFov(float fieldOfView);


    public abstract float getFar();

    public abstract void setFar(float far);

    public abstract float getNear();

    public abstract void setNear(float near);

}
