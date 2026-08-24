package nester;

import generaloss.resourceflow.Disposable;
import generaloss.resourceflow.resource.Resource;
import generaloss.spatialmath.vector.Vec2i;
import jpize.opengl.texture.GLWrap;
import jpize.opengl.texture.Texture2D;
import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.TextureRegion;

import java.util.*;

public class Nester<T> implements Disposable {

    private final List<NestImage<T>> images = new ArrayList<>();

    private final Map<T, NestPlacement<T>> placements =
        new HashMap<>();

    private final List<PixmapRGBA> pixmaps =
        new ArrayList<>();

    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;

    private boolean fillPaddings;
    private boolean allowRotation = true;

    public Nester<T> build(int width, int height) {

        clearResult();

        /*
         * Самые большие изображения сначала.
         *
         * Area обычно даёт немного лучший результат,
         * чем perimeter.
         */
        images.sort(
            Comparator
                .comparingLong(
                    (NestImage<T> image) ->
                        -(long) image.pixmap.getWidth()
                        * image.pixmap.getHeight()
                )
                .thenComparingInt(
                    image -> -(
                        image.pixmap.getWidth()
                        + image.pixmap.getHeight()
                    )
                )
        );

        List<NestImage<T>> remaining =
            new ArrayList<>(images);

        while (!remaining.isEmpty()) {

            PixmapRGBA page =
                new PixmapRGBA(width, height);

            MaxRects maxRects =
                new MaxRects(width, height);

            List<NestImage<T>> notPlaced =
                new ArrayList<>();

            int pageIndex = pixmaps.size();

            for (NestImage<T> image : remaining) {

                MaxRects.Placement result =
                    maxRects.insert(
                        image.pixmap.getWidth(),
                        image.pixmap.getHeight(),
                        allowRotation,
                        paddingLeft,
                        paddingTop,
                        paddingRight,
                        paddingBottom
                    );

                if (result == null) {
                    notPlaced.add(image);
                    continue;
                }

                draw(
                    page,
                    image,
                    result,
                    pageIndex
                );
            }

            pixmaps.add(page);

            if (notPlaced.size() == remaining.size()) {
                throw new RuntimeException(
                    "Image does not fit into page: "
                    + notPlaced.get(0).identifier
                );
            }

            remaining = notPlaced;
        }

        return this;
    }


    private void draw(
        PixmapRGBA page,
        NestImage<T> image,
        MaxRects.Placement placement,
        int pageIndex
    ) {
        int x = placement.x + paddingLeft;
        int y = placement.y + paddingTop;

        PixmapRGBA source = image.pixmap;

        /*
         * Если понадобится, здесь можно сделать
         * физический поворот PixmapRGBA.
         *
         * Пока предполагаем, что drawRotated90()
         * будет добавлен в PixmapRGBA.
         */
        if (placement.rotated) {
            source = rotate90(source);
        }

        page.drawPixmap(
            source,
            x,
            y
        );

        if (fillPaddings) {
            fillPaddings(
                page,
                x,
                y,
                source.getWidth(),
                source.getHeight()
            );
        }

        placements.put(
            image.identifier,
            new NestPlacement<>(
                image.identifier,
                pageIndex,
                x,
                y,
                source.getWidth(),
                source.getHeight(),
                placement.rotated
            )
        );

        if (source != image.pixmap)
            source.dispose();
    }


    private PixmapRGBA rotate90(PixmapRGBA source) {
        int width = source.getWidth();
        int height = source.getHeight();

        PixmapRGBA result =
            new PixmapRGBA(height, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int color =
                    source.getPixelRGBA(x, y);

                result.setPixelRGBA(
                    height - y - 1,
                    x,
                    color
                );
            }
        }

        return result;
    }


    private void fillPaddings(
        PixmapRGBA pixmap,
        int x1,
        int y1,
        int width,
        int height
    ) {
        int x2 = x1 + width - 1;
        int y2 = y1 + height - 1;

        for (int p = 1; p <= paddingLeft; p++) {
            for (int y = y1; y <= y2; y++) {
                pixmap.setPixelRGBA(
                    x1 - p,
                    y,
                    pixmap.getPixelRGBA(x1, y)
                );
            }
        }

        for (int p = 1; p <= paddingRight; p++) {
            for (int y = y1; y <= y2; y++) {
                pixmap.setPixelRGBA(
                    x2 + p,
                    y,
                    pixmap.getPixelRGBA(x2, y)
                );
            }
        }

        for (int p = 1; p <= paddingTop; p++) {
            for (int x = x1; x <= x2; x++) {
                pixmap.setPixelRGBA(
                    x,
                    y2 + p,
                    pixmap.getPixelRGBA(x, y2)
                );
            }
        }

        for (int p = 1; p <= paddingBottom; p++) {
            for (int x = x1; x <= x2; x++) {
                pixmap.setPixelRGBA(
                    x,
                    y1 - p,
                    pixmap.getPixelRGBA(x, y1)
                );
            }
        }
    }


    private void clearResult() {
        for (PixmapRGBA pixmap : pixmaps)
            pixmap.dispose();

        pixmaps.clear();
        placements.clear();
    }


    public Nester<T> setPadding(
        int left,
        int top,
        int right,
        int bottom
    ) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
        return this;
    }


    public Nester<T> setPadding(int horizontal, int vertical) {
        return setPadding(
            horizontal,
            vertical,
            horizontal,
            vertical
        );
    }


    public Nester<T> setPadding(int all) {
        return setPadding(
            all,
            all,
            all,
            all
        );
    }


    public Nester<T> enablePaddingFilling(boolean enable) {
        fillPaddings = enable;
        return this;
    }


    public Nester<T> enableRotation(boolean enable) {
        allowRotation = enable;
        return this;
    }


    public Nester<T> put(
        T identifier,
        PixmapRGBA pixmap
    ) {
        images.add(
            new NestImage<>(
                pixmap,
                identifier
            )
        );

        return this;
    }


    public Nester<T> put(
        T identifier,
        Resource resource
    ) {
        return put(
            identifier,
            PixmapIO.load(resource)
        );
    }


    public Nester<T> put(
        T identifier,
        String internalPath
    ) {
        return put(
            identifier,
            Resource.internal(internalPath)
        );
    }


    public List<PixmapRGBA> getPixmaps() {
        return Collections.unmodifiableList(
            pixmaps
        );
    }


    public PixmapRGBA getPixmap(int page) {
        return pixmaps.get(page);
    }


    public int getPageCount() {
        return pixmaps.size();
    }


    public NestPlacement<T> getPlacement(T identifier) {
        return placements.get(identifier);
    }


    public Map<T, NestPlacement<T>> getPlacements() {
        return Collections.unmodifiableMap(
            placements
        );
    }


    public int size() {
        return placements.size();
    }


    @Override
    public void dispose() {
        clearResult();

        for (NestImage<T> image : images)
            image.pixmap.dispose();

        images.clear();
    }
}