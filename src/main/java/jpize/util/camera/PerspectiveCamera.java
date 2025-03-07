package jpize.util.camera;

import jpize.context.Jpize;
import jpize.util.math.EulerAngles;
import jpize.util.math.Frustum;
import jpize.util.math.Quaternion;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2i;
import jpize.util.math.vector.Vec3f;

public class PerspectiveCamera extends Camera3D {

    private float near, far, fovY;
    private final Quaternion quaternion;
    private final EulerAngles rotation;
    private final Matrix4f projection, view, combined, imaginaryView;
    private final Vec3f direction;
    private final Frustum frustum;
    private boolean imaginaryX, imaginaryY, imaginaryZ, hasImaginaryAxis;

    public PerspectiveCamera(int width, int height, double near, double far, double fovY) {
        super(width, height);

        this.near = (float) near;
        this.far = (float) far;
        this.fovY = (float) fovY;
        this.quaternion = new Quaternion();
        this.rotation = new EulerAngles();
        this.imaginaryView = new Matrix4f();
        this.frustum = new Frustum();
        this.view = new Matrix4f();
        this.projection = new Matrix4f();
        this.combined = new Matrix4f();
        this.direction = new Vec3f();

        this.updateProjectionMatrix();
        this.update();
    }

    public PerspectiveCamera(double near, double far, double fovY) {
        this(Jpize.getWidth(), Jpize.getHeight(), near, far, fovY);
    }

    @Override
    public void update() {
        // update direction
        quaternion.setRotation(-rotation.yaw + 90, -rotation.pitch, -rotation.roll);
        quaternion.getDirection(direction);
        // update view
        this.updateViewMatrix();
        // update combined
        combined.set(projection).mul(imaginaryView);
        // update frustum
        frustum.setFrustum(combined);
    }

    private void updateProjectionMatrix() {
        projection.setPerspective(width, height, near, far, fovY);
    }

    private void updateViewMatrix() {
        imaginaryView.setQuaternion(quaternion)
            .translate(-position.x, -position.y, -position.z);

        if(hasImaginaryAxis) {
            view.setQuaternion(quaternion).translate(
                (imaginaryX ? 0F : -position.x),
                (imaginaryY ? 0F : -position.y),
                (imaginaryZ ? 0F : -position.z)
            );
        }else{
            view.set(imaginaryView);
        }
    }

    @Override
    public void resize(int width, int height) {
        if(Vec2i.equals(width, height, this.width, this.height))
            return;

        super.resize(width, height);
        this.updateProjectionMatrix();
    }

    public void setImaginaryOrigins(boolean x, boolean y, boolean z) {
        this.imaginaryX = x;
        this.imaginaryY = y;
        this.imaginaryZ = z;
        this.hasImaginaryAxis = (imaginaryX || imaginaryY || imaginaryZ);
    }

    public Frustum frustum() {
        return frustum;
    }

    public EulerAngles rotation() {
        return rotation;
    }

    @Override
    public Vec3f getDirection() {
        return direction;
    }

    @Override
    public float getFOV() {
        return fovY;
    }

    @Override
    public void setFOV(float fieldOfView) {
        if(this.fovY == fieldOfView)
            return;

        this.fovY = fieldOfView;
        this.updateProjectionMatrix();
    }

    @Override
    public float getNear() {
        return near;
    }

    @Override
    public void setNear(float near) {
        if(this.near == near)
            return;

        this.near = near;
        this.updateProjectionMatrix();
    }

    @Override
    public float getFar() {
        return far;
    }

    @Override
    public void setFar(float far) {
        if(this.far == far)
            return;

        this.far = far;
        this.updateProjectionMatrix();
    }

    @Override
    public Matrix4f getView() {
        return view;
    }

    public Matrix4f getImaginaryView() {
        return imaginaryView;
    }

    @Override
    public Matrix4f getProjection() {
        return projection;
    }

    @Override
    public Matrix4f getCombined() {
        return combined;
    }

}