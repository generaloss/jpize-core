package jpize.util.camera;

import jpize.app.Jpize;
import jpize.util.math.matrix.Frustum;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2i;

public class PerspectiveCamera extends Camera3D {

    private float fovY, near, far;
    private final Matrix4f projection, view, combined, imaginaryView, rollMatrix;
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

        updateProjectionMatrix();
        updateViewMatrix();
    }

    @Override
    public void update() {
        updateViewMatrix();
    }

    private void updateProjectionMatrix() {
        projection.setPerspective(width, height, near, far, fovY);
    }

    private void updateViewMatrix() {
        // Roll camera rotation
        rollMatrix.setRotationZ(rotation.roll);

        // View matrix
        imaginaryView.setLookAlong(position, rotation.getDir());
        imaginaryView.set(rollMatrix.copy().mul(imaginaryView));

        // View matrix
        if(hasImaginaryAxis){
            view.setLookAlong(
                imaginaryX ? 0 : position.x,
                imaginaryY ? 0 : position.y,
                imaginaryZ ? 0 : position.z,
                rotation.getDir()
            );
            view.set(rollMatrix.mul(view));
        }else
            view.set(imaginaryView);

        // Update combined
        combined.set(projection).mul(imaginaryView);

        // Update frustum
        frustum.setFrustum(combined);
    }

    @Override
    public void resize(int width, int height) {
        if(Vec2i.equals(width, height, this.width, this.height))
            return;

        super.resize(width, height);
        updateProjectionMatrix();
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
    public float getFov() {
        return fovY;
    }

    @Override
    public void setFov(float fieldOfView) {
        if(this.fovY == fieldOfView)
            return;

        this.fovY = fieldOfView;
        updateProjectionMatrix();
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
        updateProjectionMatrix();
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
        updateProjectionMatrix();
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
