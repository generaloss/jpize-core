package jpize.util.camera;

import jpize.context.Jpize;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2i;

public class OrthographicCamera extends Camera2D {

    private float scale, rotation;
    private final Matrix4f projection, view, combined, translationScaleMatrix, rotationMatrix;
    private boolean imaginaryX, imaginaryY;

    public OrthographicCamera(int width, int height) {
        super(width, height);

        this.scale = 1F;
        this.translationScaleMatrix = new Matrix4f();
        this.rotationMatrix = new Matrix4f();
        this.view = new Matrix4f();
        this.projection = new Matrix4f();
        this.combined = new Matrix4f();

        this.updateProjectionMatrix();
        this.updateViewMatrix();
    }

    public OrthographicCamera() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    @Override
    public void update() {
        this.updateViewMatrix();
    }

    private void updateProjectionMatrix() {
        projection.identity().setOrthographic(0F, 0F, width, height);
    }

    private void updateViewMatrix() {
        // setup translation & scale
        translationScaleMatrix.setTranslatePart(imaginaryX ? 0F : -position.x, imaginaryY ? 0F : -position.y, 0F);
        translationScaleMatrix.setScalePart(scale, scale, 1F);
        // setup rotation
        rotationMatrix.setRotationZ(rotation);
        // multiply
        translationScaleMatrix.mul(view, rotationMatrix);
        this.updateCombinedMatrix();
    }

    private void updateCombinedMatrix() {
        projection.mul(combined, view);
    }

    @Override
    public void resize(int width, int height) {
        if(Vec2i.equals(width, height, this.width, this.height))
            return;
        super.resize(width, height);
        this.updateProjectionMatrix();
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

    public void setRotation(float degrees) {
        rotation = degrees;
    }

    public void rotate(float degrees) {
        rotation += degrees;
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
