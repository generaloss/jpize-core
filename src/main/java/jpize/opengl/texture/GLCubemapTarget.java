package jpize.opengl.texture;

public enum GLCubemapTarget {

    POSITIVE_X (GLTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_X),
    NEGATIVE_X (GLTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_X),
    POSITIVE_Y (GLTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Y),
    NEGATIVE_Y (GLTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Y),
    POSITIVE_Z (GLTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Z),
    NEGATIVE_Z (GLTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Z);

    public final GLTexImg2DTarget imageTarget;

    GLCubemapTarget(GLTexImg2DTarget imageTarget) {
        this.imageTarget = imageTarget;
    }

}
