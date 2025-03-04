package jpize.opengl.texture;

public enum GlCubemapTarget {

    POSITIVE_X (GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_X),
    NEGATIVE_X (GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_X),
    POSITIVE_Y (GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Y),
    NEGATIVE_Y (GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Y),
    POSITIVE_Z (GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Z),
    NEGATIVE_Z (GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Z);

    public final GlTexImg2DTarget imageTarget;

    GlCubemapTarget(GlTexImg2DTarget imageTarget) {
        this.imageTarget = imageTarget;
    }

}
