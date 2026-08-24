package nester;

import generaloss.spatialmath.vector.Vec2i;
import jpize.util.region.TextureRegion;

public class NestPlacement<T> {

    private final T identifier;
    private final int page;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final boolean rotated;

    public NestPlacement(
        T identifier,
        int page,
        int x,
        int y,
        int width,
        int height,
        boolean rotated
    ) {
        this.identifier = identifier;
        this.page = page;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotated = rotated;
    }

    public T getIdentifier() {
        return identifier;
    }

    public int getPage() {
        return page;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isRotated() {
        return rotated;
    }
}