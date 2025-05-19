package jpize.opengl.texture;

public enum GLCubemapTarget {

    POSITIVE_X (GLTexImage2DTarget.TEXTURE_CUBE_MAP_POSITIVE_X),
    NEGATIVE_X (GLTexImage2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_X),
    POSITIVE_Y (GLTexImage2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Y),
    NEGATIVE_Y (GLTexImage2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Y),
    POSITIVE_Z (GLTexImage2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Z),
    NEGATIVE_Z (GLTexImage2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Z);

    public final GLTexImage2DTarget imageTarget;

    GLCubemapTarget(GLTexImage2DTarget imageTarget) {
        this.imageTarget = imageTarget;
    }

}
