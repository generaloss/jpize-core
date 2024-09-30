package jpize.util.camera;

import jpize.app.Jpize;
import jpize.util.math.Maths;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2i;

public class OrthographicCameraCentered extends Camera2D {
    
    private float scale, rotation;
    private final Matrix4f projection, view, combined, scalingMatrix, translationMatrix, rotationMatrix;
    private boolean imaginaryX, imaginaryY;

    public OrthographicCameraCentered(int width, int height) {
        super(width, height);

        this.scale = 1F;
        this.scalingMatrix = new Matrix4f();
        this.translationMatrix = new Matrix4f();
        this.rotationMatrix = new Matrix4f();
        this.view = new Matrix4f();
        this.projection = new Matrix4f();
        this.combined = new Matrix4f();

        updateProjectionMatrix();
        updateViewMatrix();
    }

    public OrthographicCameraCentered() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    @Override
    public void update() {
        updateViewMatrix();
    }

    private void updateProjectionMatrix() {
        projection.identity().setOrthographic(-Maths.round(width / 2F), -Maths.round(height / 2F), width, height);
    }

    private void updateViewMatrix() {
        translationMatrix.setTranslate(imaginaryX ? 0 : -position.x, imaginaryY ? 0 : -position.y);
        scalingMatrix.setScale(scale);
        rotationMatrix.setRotationZ(rotation);
        view.set(scalingMatrix.mul(translationMatrix).mul(rotationMatrix));
        updateCombinedMatrix();
    }

    private void updateCombinedMatrix() {
        combined.set(projection).mul(view);
    }

    @Override
    public void resize(int width, int height) {
        if(Vec2i.equals(width, height, this.width, this.height))
            return;
        super.resize(width, height);
        updateProjectionMatrix();
    }

    public void setImaginaryOrigins(boolean x, boolean y) {
        imaginaryX = x;
        imaginaryY = y;
    }

    @Override
    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void scale(float scale) {
        this.scale *= scale;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    public void setRotation(float deg) {
        rotation = deg;
    }

    public void rotate(float deg) {
        rotation += deg;
    }

    @Override
    public Matrix4f getProjection() {
        return projection;
    }

    @Override
    public Matrix4f getView() {
        return view;
    }

    @Override
    public Matrix4f getCombined() {
        return combined;
    }

}
