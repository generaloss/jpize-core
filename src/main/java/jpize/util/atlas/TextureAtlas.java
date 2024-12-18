package jpize.util.atlas;

import jpize.gl.texture.GlWrap;
import jpize.gl.texture.Texture2D;
import jpize.util.Disposable;
import jpize.util.math.Maths;
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
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private boolean fillPaddings;

    public TextureAtlas() {
        this.images = new ArrayList<>();
        this.regions = new HashMap<>();
        this.sizes = new HashMap<>();
        this.pixmap = new PixmapRGBA(0, 0);
        this.texture = new Texture2D()
            .setWrapST(GlWrap.CLAMP_TO_EDGE);
    }

    public TextureAtlas<T> build(int width, int height) {
        this.initBuild(width, height);
        this.drawPixmap(width, height);

        // set texture
        texture.setImage(pixmap);

        // clean up
        for(AtlasImage<T> image: images)
            image.pixmap.dispose();
        images.clear();
        return this;
    }

    private void initBuild(int width, int height) {
        // sort images by perimeter
        images.sort(Comparator.comparingInt(
            image -> -image.halfPerimeter
        ));
        // clear
        regions.clear();
        sizes.clear();
        // resize
        pixmap.resize(width, height);
        texture.setDefaultImage(width, height);
    }

    private void drawPixmap(int width, int height) {
        final AtlasNode root = new AtlasNode(0, 0, width - paddingLeft, height - paddingTop);

        // iterate all images to generate
        for(final AtlasImage<T> image: images){
            final AtlasNode drawResult = root.insert(image.pixmap, paddingLeft, paddingTop, paddingRight, paddingBottom);
            if(drawResult == null)
                throw new RuntimeException("Too small area of " + this.getClass().getSimpleName() + ".");

            final int x1 = drawResult.getX() + paddingLeft;
            final int y1 = drawResult.getY() + paddingTop;
            final int imageWidth = image.pixmap.getWidth();
            final int imageHeight = image.pixmap.getHeight();

            pixmap.drawPixmap(image.pixmap, x1, y1);
            if(fillPaddings)
                fillPaddings(x1, y1, imageWidth, imageHeight);

            regions.put(image.identifier, new TextureRegion(texture, x1, y1, imageWidth, imageHeight));
            sizes.put(image.identifier, new Vec2i(imageWidth, imageHeight));
        }
    }

    private void fillPaddings(int x1, int y1, int width, int height) {
        final int x2 = (x1 + width  - 1);
        final int y2 = (y1 + height - 1);
        // left
        for(int p = 1; p <= paddingLeft; p++){
            for(int y = y1 - Math.min(p - 1, paddingBottom); y <= y2 + Math.min(p, paddingTop); y++){
                final int color = pixmap.getPixelRGBA(x1, Maths.clamp(y, y1, y2));
                pixmap.setPixelRGBA(x1 - p, y, color);
            }
        }
        // top
        for(int p = 1; p <= paddingTop; p++){
            for(int x = x1 - Math.min(p - 1, paddingLeft); x <= x2 + Math.min(p, paddingRight); x++){
                final int color = pixmap.getPixelRGBA(Maths.clamp(x, x1, x2), y2);
                pixmap.setPixelRGBA(x, y2 + p, color);
            }
        }
        // right
        for(int p = 1; p <= paddingRight; p++){
            for(int y = y1 - Math.min(p, paddingBottom); y <= y2 + Math.min(p - 1, paddingTop); y++){
                final int color = pixmap.getPixelRGBA(x2, Maths.clamp(y, y1, y2));
                pixmap.setPixelRGBA(x2 + p, y, color);
            }
        }
        // bottom
        for(int p = 1; p <= paddingBottom; p++){
            for(int x = x1 - Math.min(p, paddingLeft); x <= x2 + Math.min(p - 1, paddingRight); x++){
                final int color = pixmap.getPixelRGBA(Maths.clamp(x, x1, x2), y1);
                pixmap.setPixelRGBA(x, y1 - p, color);
            }
        }
    }


    public TextureAtlas<T> setPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
        return this;
    }

    public TextureAtlas<T> setPadding(int right, int bottom) {
        return this.setPadding(right, bottom, right, bottom);
    }

    public TextureAtlas<T> setPadding(int all) {
        return this.setPadding(all, all, all, all);
    }


    public TextureAtlas<T> setFillPaddings(boolean enable) {
        fillPaddings = enable;
        return this;
    }


    public TextureAtlas<T> put(T identifier, PixmapRGBA pixmap) {
        images.add(new AtlasImage<>(pixmap, identifier));
        return this;
    }

    public TextureAtlas<T> put(T identifier, Resource res) {
        return this.put(identifier, PixmapIO.load(res));
    }

    public TextureAtlas<T> put(T identifier, String internalpath) {
        return this.put(identifier, Resource.internal(internalpath));
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
