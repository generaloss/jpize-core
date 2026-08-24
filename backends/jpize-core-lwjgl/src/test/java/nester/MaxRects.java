package nester;

import java.util.*;

class MaxRects {

    private final int width;
    private final int height;

    private final List<Rect> freeRects = new ArrayList<>();

    public MaxRects(int width, int height) {
        this.width = width;
        this.height = height;

        freeRects.add(new Rect(0, 0, width, height));
    }


    public Placement insert(
        int imageWidth,
        int imageHeight,
        boolean allowRotation,
        int paddingLeft,
        int paddingTop,
        int paddingRight,
        int paddingBottom
    ) {
        final int paddedWidth =
            imageWidth + paddingLeft + paddingRight;

        final int paddedHeight =
            imageHeight + paddingTop + paddingBottom;

        Placement best = null;

        for (Rect free : freeRects) {

            // Normal
            if (paddedWidth <= free.width &&
                paddedHeight <= free.height) {

                Placement candidate = score(
                    free,
                    paddedWidth,
                    paddedHeight,
                    false
                );

                if (better(candidate, best))
                    best = candidate;
            }

            // Rotated
            if (allowRotation &&
                paddedHeight <= free.width &&
                paddedWidth <= free.height &&
                paddedWidth != paddedHeight) {

                Placement candidate = score(
                    free,
                    paddedHeight,
                    paddedWidth,
                    true
                );

                if (better(candidate, best))
                    best = candidate;
            }
        }

        if (best == null)
            return null;

        splitFreeRects(best.rect);

        pruneFreeRects();

        return best;
    }


    private Placement score(
        Rect free,
        int width,
        int height,
        boolean rotated
    ) {
        final int leftoverHorizontal =
            Math.abs(free.width - width);

        final int leftoverVertical =
            Math.abs(free.height - height);

        final int shortSide =
            Math.min(leftoverHorizontal, leftoverVertical);

        final int longSide =
            Math.max(leftoverHorizontal, leftoverVertical);

        return new Placement(
            free.x,
            free.y,
            width,
            height,
            rotated,
            shortSide,
            longSide,
            free
        );
    }


    private boolean better(Placement a, Placement b) {
        if (b == null)
            return true;

        // Best Short Side Fit
        if (a.shortSideFit != b.shortSideFit)
            return a.shortSideFit < b.shortSideFit;

        // Best Long Side Fit
        if (a.longSideFit != b.longSideFit)
            return a.longSideFit < b.longSideFit;

        return false;
    }


    private void splitFreeRects(Rect used) {
        for (int i = 0; i < freeRects.size();) {

            Rect free = freeRects.get(i);

            if (!free.intersects(used)) {
                i++;
                continue;
            }

            freeRects.remove(i);

            // Left
            if (used.x > free.x) {
                freeRects.add(new Rect(
                    free.x,
                    free.y,
                    used.x - free.x,
                    free.height
                ));
            }

            // Right
            if (used.x + used.width < free.x + free.width) {
                freeRects.add(new Rect(
                    used.x + used.width,
                    free.y,
                    (free.x + free.width) -
                        (used.x + used.width),
                    free.height
                ));
            }

            // Top
            if (used.y > free.y) {
                freeRects.add(new Rect(
                    free.x,
                    free.y,
                    free.width,
                    used.y - free.y
                ));
            }

            // Bottom
            if (used.y + used.height < free.y + free.height) {
                freeRects.add(new Rect(
                    free.x,
                    used.y + used.height,
                    free.width,
                    (free.y + free.height) -
                        (used.y + used.height)
                ));
            }
        }
    }


    private void pruneFreeRects() {
        for (int i = 0; i < freeRects.size(); i++) {

            Rect a = freeRects.get(i);

            if (a.width <= 0 || a.height <= 0) {
                freeRects.remove(i--);
                continue;
            }

            for (int j = i + 1; j < freeRects.size(); j++) {

                Rect b = freeRects.get(j);

                if (contains(a, b)) {
                    freeRects.remove(j--);
                } else if (contains(b, a)) {
                    freeRects.remove(i);
                    i--;
                    break;
                }
            }
        }
    }


    private boolean contains(Rect a, Rect b) {
        return b.x >= a.x &&
               b.y >= a.y &&
               b.x + b.width <= a.x + a.width &&
               b.y + b.height <= a.y + a.height;
    }


    public static class Placement {

        public final int x;
        public final int y;
        public final int width;
        public final int height;
        public final boolean rotated;

        private final int shortSideFit;
        private final int longSideFit;

        private final Rect rect;

        private Placement(
            int x,
            int y,
            int width,
            int height,
            boolean rotated,
            int shortSideFit,
            int longSideFit,
            Rect rect
        ) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.rotated = rotated;
            this.shortSideFit = shortSideFit;
            this.longSideFit = longSideFit;
            this.rect = new Rect(x, y, width, height);
        }
    }


    public static class Rect {

        public int x;
        public int y;
        public int width;
        public int height;

        public Rect(
            int x,
            int y,
            int width,
            int height
        ) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean intersects(Rect other) {
            return x < other.x + other.width &&
                   x + width > other.x &&
                   y < other.y + other.height &&
                   y + height > other.y;
        }
    }
}