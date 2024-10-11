package jpize.util.camera;

import jpize.app.Jpize;
import jpize.util.math.matrix.Frustum;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2i;
import jpize.util.math.vector.Vec3f;

public class PerspectiveCamera extends Camera3D {

    private float fovY, near, far;
    private final Matrix4f projection, view, combined, imaginaryView, rollMatrix;
    private final Vec3f direction;
    private final Frustum frustum;
    private boolean imaginaryX, imaginaryY, imaginaryZ, hasImaginaryAxis;

    public PerspectiveCamera(double near, double far, double fovY) {
        this(Jpize.getWidth(), Jpize.getHeight(), near, far, fovY);
    }

    public PerspectiveCamera(int width, int height, double near, double far, double fovY) {
        super(width, height);
        
        this.near = (float) near;
        this.far = (float) far;
        this.fovY = (float) fovY;
        this.imaginaryView = new Matrix4f();
        this.rollMatrix = new Matrix4f();
        this.frustum = new Frustum();
        this.view = new Matrix4f();
        this.projection = new Matrix4f();
        this.combined = new Matrix4f();
        this.direction = new Vec3f();

        this.updateProjectionMatrix();
        this.update();
    }

    @Override
    public void update() {
        // update direction
        rotation.getDirection(direction);
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
        // Roll camera rotation
        rollMatrix.setRotationZ(rotation.roll);

        // View matrix
        imaginaryView.setLookAlong(position, direction);
        imaginaryView.set(rollMatrix.copy().mul(imaginaryView));

        // View matrix
        if(hasImaginaryAxis) {
            view.setLookAlong(
                imaginaryX ? 0 : position.x,
                imaginaryY ? 0 : position.y,
                imaginaryZ ? 0 : position.z,
                direction
            );
            view.set(rollMatrix.mul(view));
        }else
            view.set(imaginaryView);
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
        this.hasImaginaryAxis = imaginaryX || imaginaryY || imaginaryZ;
    }

    public Frustum getFrustum() {
        return frustum;
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
