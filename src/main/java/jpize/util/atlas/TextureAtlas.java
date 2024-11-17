package jpize.util.atlas;

import jpize.gl.texture.Texture2D;
import jpize.util.Disposable;
import jpize.util.math.vector.Vec2i;
import jpize.util.region.TextureRegion;
import jpize.util.res.Resource;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.pixmap.PixmapIO;

import java.util.*;

public class TextureAtlas<T> implements Disposable {

    private final List<AtlasImage<T>> images;
    private final Map<T, TextureRegion> regions;
    private final Map<T, Vec2i> sizes;
    private final PixmapRGBA pixmap;
    private final Texture2D texture;

    public TextureAtlas() {
        this.images = new ArrayList<>();
        this.regions = new HashMap<>();
        this.sizes = new HashMap<>();
        this.pixmap = new PixmapRGBA(0, 0);
        this.texture = new Texture2D();
    }

    public void build(int width, int height, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        // sort images by perimeter
        images.sort(Comparator.comparingInt(
            image -> -image.halfPerimeter
        ));

        regions.clear();
        sizes.clear();
        pixmap.resize(width, height);
        texture.setDefaultImage(width, height);

        final AtlasNode root = new AtlasNode(0, 0, width - paddingLeft, height - paddingTop);

        // iterate all images to generate
        for(final AtlasImage<T> image: images){
            final AtlasNode drawResult = root.insert(image.pixmap, paddingLeft, paddingTop, paddingRight, paddingBottom);
            if(drawResult == null)
                throw new RuntimeException("Too small area of " + this.getClass().getSimpleName() + ".");

            final int drawX = drawResult.getX() + paddingLeft;
            final int drawY = drawResult.getY() + paddingTop;
            final int drawWidth = image.pixmap.getWidth();
            final int drawHeight = image.pixmap.getHeight();

            pixmap.drawPixmap(image.pixmap, drawX, drawY);

            regions.put(image.identifier, new TextureRegion(texture, drawX, drawY, drawWidth, drawHeight));
            sizes.put(image.identifier, new Vec2i(drawWidth, drawHeight));
        }
        // set texture
        texture.setImage(pixmap);

        for(AtlasImage<T> image: images)
            image.pixmap.dispose();
        images.clear();
    }

    public void build(int width, int height, int paddingRight, int paddingBottom) {
        this.build(width, height, 0, 0, paddingRight, paddingBottom);
    }

    public void build(int width, int height, int padding) {
        this.build(width, height, padding, padding);
    }

    public void build(int width, int height) {
        this.build(width, height, 0);
    }


    public void put(T identifier, PixmapRGBA pixmap) {
        images.add(new AtlasImage<>(pixmap, identifier));
    }

    public void put(T identifier, Resource res) {
        this.put(identifier, PixmapIO.load(res));
    }

    public void put(T identifier, String internalpath) {
        this.put(identifier, Resource.internal(internalpath));
    }


    public Map<T, TextureRegion> getRegionMap() {
        return regions;
    }

    public TextureRegion getRegion(T identifier) {
        return regions.get(identifier);
    }

    public Vec2i getSize(T identifier) {
        return sizes.get(identifier);
    }


    public PixmapRGBA getPixmap() {
        return pixmap;
    }

    public Texture2D getTexture() {
        return texture;
    }


    public int size() {
        return regions.size();
    }

    @Override
    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }

}
