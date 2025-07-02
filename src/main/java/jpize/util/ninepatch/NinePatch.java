package jpize.util.ninepatch;

import jpize.opengl.texture.GLWrap;
import jpize.opengl.texture.Texture2D;
import jpize.util.Disposable;
import jpize.util.array.IntList;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2f;
import jpize.util.mesh.TextureBatch;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.TextureRegion;

import java.util.function.Function;

public class NinePatch implements Disposable {

    private final Vec2f scale, extraScale;
    private boolean drawCenter;
    private StretchMode stretchModeX, stretchModeY;
    private Texture2D texture;
    private Patch[][] patchesXY;

    private int totalStaticWidth, totalStaticHeight;
    private int totalDynamicWidth, totalDynamicHeight;

    private float contentStaticX, contentDynamicX;
    private float contentStaticY, contentDynamicY;
    private float contentStaticWidth, contentDynamicWidth;
    private float contentStaticHeight, contentDynamicHeight;

    public NinePatch() {
        this.scale = new Vec2f(1F);
        this.extraScale = new Vec2f(1F);
        this.drawCenter = true;
        this.setStretchModeX(StretchMode.STRETCH);
        this.setStretchModeY(StretchMode.STRETCH);
    }

    public NinePatch(Texture2D texture, StretchMode stretchMode, float scale) {
        this();
        this.load(texture);
        this.setStretchMode(stretchMode);
        this.scale.set(scale);
    }

    public NinePatch(Pixmap pixmap, StretchMode stretchMode, float scale) {
        this(new Texture2D(pixmap), stretchMode, scale);
    }

    public NinePatch(String internalPath, StretchMode stretchMode, float scale) {
        this(new Texture2D(internalPath), stretchMode, scale);
    }


    public Vec2f scale() {
        return scale;
    }

    public NinePatch setScale(double scaleX, double scaleY) {
        this.scale.set(scaleX, scaleY);
        return this;
    }

    public NinePatch setScale(double scaleXY) {
        return this.setScale(scaleXY, scaleXY);
    }


    public boolean isDrawCenter() {
        return drawCenter;
    }

    public NinePatch setDrawCenter(boolean drawCenter) {
        this.drawCenter = drawCenter;
        return this;
    }


    public StretchMode getStretchModeX() {
        return stretchModeX;
    }

    public NinePatch setStretchModeX(StretchMode mode) {
        this.stretchModeX = mode;
        return this;
    }

    public StretchMode getStretchModeY() {
        return stretchModeY;
    }

    public NinePatch setStretchModeY(StretchMode mode) {
        this.stretchModeY = mode;
        return this;
    }

    public NinePatch setStretchMode(StretchMode modeX, StretchMode modeY) {
        this.stretchModeX = modeX;
        this.stretchModeY = modeY;
        return this;
    }

    public NinePatch setStretchMode(StretchMode mode) {
        return this.setStretchMode(mode, mode);
    }


    public float getMinWidth() {
        return (totalDynamicWidth + totalStaticWidth) * scale.x;
    }

    public float getMinHeight() {
        return (totalDynamicHeight + totalStaticHeight) * scale.y;
    }


    public float getStaticWidth() {
        return (totalStaticWidth * scale.x);
    }

    public float getStaticHeight() {
        return (totalStaticHeight * scale.y);
    }

    private float getDynamicWidth(float width, float unstretchedWidth) {
        return (width - unstretchedWidth);
    }

    private float getDynamicHeight(float height, float unstretchedHeight) {
        return (height - unstretchedHeight);
    }

    public float getDynamicWidth(float width) {
        return this.getDynamicWidth(width, this.getStaticWidth());
    }

    public float getDynamicHeight(float height) {
        return this.getDynamicHeight(height, this.getStaticHeight());
    }


    public float getContentX(float width) {
        if(width < 1)
            return 0;
        final float staticWidth = this.getStaticWidth() * extraScale.x;
        final float dynamicWidth = this.getDynamicWidth(width, staticWidth);
        return (
            contentStaticX * staticWidth +
            contentDynamicX * dynamicWidth
        );
    }

    public float getContentY(float height) {
        if(height < 1)
            return 0;
        final float staticHeight = this.getStaticHeight() * extraScale.y;
        final float dynamicHeight = this.getDynamicHeight(height, staticHeight);
        return (
            contentStaticY * staticHeight +
            contentDynamicY * dynamicHeight
        );
    }

    public float getContentWidth(float width) {
        if(width < 1)
            return 0;
        final float staticWidth = this.getStaticWidth() * extraScale.x;
        final float dynamicWidth = this.getDynamicWidth(width, staticWidth);
        return (
            contentStaticWidth * staticWidth +
            contentDynamicWidth * dynamicWidth
        );
    }

    public float getContentHeight(float height) {
        if(height < 1)
            return 0;
        final float staticHeight = this.getStaticHeight() * extraScale.y;
        final float dynamicHeight = this.getDynamicHeight(height, staticHeight);
        return (
            contentStaticHeight * staticHeight +
            contentDynamicHeight * dynamicHeight
        );
    }



    public NinePatch load(String internalPath) {
        return this.load(new Texture2D(internalPath));
    }

    public NinePatch load(Pixmap pixmap) {
        return this.load(new Texture2D(pixmap));
    }

    public NinePatch load(Texture2D texture) {
        if(this.texture != null)
            this.dispose();

        this.texture = texture;

        // load pixels
        final PixmapRGBA pixmap = new PixmapRGBA(texture.getWidth(), texture.getHeight());
        texture.getImage(pixmap);

        // get patches offset & size
        final int[] patchSizesX = NinePatch.getPatchSizes(
            pixmap, pixmap.getWidth(),
            x -> pixmap.getPixelRGBA(x, 0)
        );
        final int[] patchSizesY = NinePatch.getPatchSizes(
            pixmap, pixmap.getHeight(),
            y -> pixmap.getPixelRGBA(0, y)
        );

        if(patchSizesX.length < 3 || patchSizesY.length < 3)
            throw new IllegalStateException("Invalid marked patches");

        // total sizes
        totalStaticWidth = 0;
        totalStaticHeight = 0;
        totalDynamicWidth = 0;
        totalDynamicHeight = 0;
        for(int i = 0; i < patchSizesX.length; i++){
            final int width = patchSizesX[i];
            if(i % 2 == 0){
                totalStaticWidth += width;
            }else{
                totalDynamicWidth += width;
            }
        }
        for(int i = 0; i < patchSizesY.length; i++){
            final int height = patchSizesY[i];
            if(i % 2 == 0){
                totalStaticHeight += height;
            }else{
                totalDynamicHeight += height;
            }
        }

        // number
        final int dynamicNumberX = (patchSizesX.length - 1) / 2;
        final int dynamicNumberY = (patchSizesY.length - 1) / 2;
        final int staticNumberX = (patchSizesX.length - dynamicNumberX);
        final int staticNumberY = (patchSizesY.length - dynamicNumberY);

        // patches & content
        this.createPatches(patchSizesX, patchSizesY);
        this.getContentSize(pixmap, patchSizesX, patchSizesY);

        pixmap.dispose();
        return this;
    }

    private void createPatches(int[] sizesX, int[] sizesY) {
        patchesXY = new Patch[sizesX.length][sizesY.length];

        int x = 1;
        boolean isDynamicX = false;

        for(int i = 0; i < sizesX.length; i++){
            final int width = sizesX[i];
            int y = 1;
            boolean isDynamicY = false;

            for(int j = 0; j < sizesY.length; j++){
                final int height = sizesY[j];

                final TextureRegion region;
                if(isDynamicX || isDynamicY){
                    // create dynamic patch
                    final PixmapRGBA patchPixmap = new PixmapRGBA(width, height);
                    texture.getSubImage(x, y, width, height, patchPixmap);

                    final Texture2D patchTexture = new Texture2D(patchPixmap);
                    patchTexture.setWrapST(GLWrap.REPEAT);

                    region = new TextureRegion(patchTexture);
                    patchPixmap.dispose();
                }else{
                  // create static patch
                  region = new TextureRegion(texture, x, y, width, height);
                }
                patchesXY[i][sizesY.length - 1 - j] = new Patch(region, isDynamicX, isDynamicY);

                isDynamicY = !isDynamicY;
                y += height;
            }
            isDynamicX = !isDynamicX;
            x += width;
        }
    }

    private void getContentSize(PixmapRGBA pixmap, int[] patchSizesX, int[] patchSizesY) {
        final int pixmapBoundY = (pixmap.getHeight() - 1);
        final float[] contentSizesX = NinePatch.getContentSizes(
            pixmap, pixmap.getWidth() - 1, false,
            x -> pixmap.getPixelRGBA(x, 0),
            x -> pixmap.getPixelRGBA(x, pixmapBoundY)
        );

        contentStaticX      = contentSizesX[0] / totalStaticWidth;
        contentDynamicX     = contentSizesX[1] / totalDynamicWidth;
        contentStaticWidth  = contentSizesX[2] / totalStaticWidth;
        contentDynamicWidth = contentSizesX[3] / totalDynamicWidth;

        final int pixmapBoundX = (pixmap.getWidth() - 1);
        final float[] contentSizesY = NinePatch.getContentSizes(
            pixmap, pixmap.getHeight() - 1, true,
            y -> pixmap.getPixelRGBA(0, y),
            y -> pixmap.getPixelRGBA(pixmapBoundX, y)
        );

        contentStaticY       = contentSizesY[0] / totalStaticHeight;
        contentDynamicY      = contentSizesY[1] / totalDynamicHeight;
        contentStaticHeight  = contentSizesY[2] / totalStaticHeight;
        contentDynamicHeight = contentSizesY[3] / totalDynamicHeight;
    }

    private static int[] getPatchSizes(PixmapRGBA pixels, int size, Function<Integer, Integer> colorFunc) {
        final IntList partsList = new IntList(3);

        boolean prevIsDynamic = false;
        int prevPosition = 0;

        int position = 0;
        while(position < size){
            final int color = colorFunc.apply(position);
            final boolean isDynamic = (
                (color & 0xFF) == 255 &&
                    (color & 0xFFFFFF00) == 0 // is black
            );
            if(prevIsDynamic != isDynamic){
                prevIsDynamic = isDynamic;

                partsList.add(position - prevPosition);
                prevPosition = position;
            }
            position++;
        }

        partsList.add(position - prevPosition);

        // remove corners
        partsList.elementSub(0, 1);
        partsList.elementSub(partsList.lastIndex(), 1);

        return partsList.arrayTrimmed();
    }

    private static float[] getContentSizes(PixmapRGBA pixels, int size, boolean invert,
                                           Function<Integer, Integer> colorFuncPatch,
                                           Function<Integer, Integer> colorFuncContent) {
        final float[] result = new float[4];

        boolean prevIsContent = false;

        int position = (invert ? (size - 1) : 1);
        while(position < size || position > 1){
            final int colorContent = colorFuncContent.apply(position);
            final boolean isContent = (
                (colorContent & 0xFF) == 255 &&
                (colorContent & 0xFFFFFF00) == 0 // is black
            );

            if(prevIsContent && !isContent)
                break;
            prevIsContent = isContent;

            final int colorPatch = colorFuncPatch.apply(position);
            final boolean isStretchablePatch = (
                (colorPatch & 0xFF) == 255 &&
                (colorPatch & 0xFFFFFF00) == 0 // is black
            );

            if(isContent) {
                if(isStretchablePatch){
                    result[3] += 1;
                }else{
                    result[2] += 1;
                }
            }else{
                if(isStretchablePatch){
                    result[1] += 1;
                }else{
                    result[0] += 1;
                }
            }

            position += (invert ? -1 : 1);
        }

        return result;
    }


    public void draw(TextureBatch batch, float x, float y, float width, float height) {
        if(patchesXY == null || width < 1F || height < 1F)
            return;

        // extra scale
        extraScale.set(1F);
        final float minWidth  = this.getMinWidth();
        if(width < minWidth){
            extraScale.x = (width / minWidth);
            width = minWidth;
        }
        final float minHeight = this.getMinHeight();
        if(height < minHeight){
            extraScale.y = (height / minHeight);
            height = minHeight;
        }

        final float dynamicWidth = this.getDynamicWidth(width) / totalDynamicWidth;
        final float dynamicHeight = this.getDynamicHeight(height) / totalDynamicHeight;

        // iterate patches
        float offsetX = x;
        for(final Patch[] patchesY : patchesXY){
            float offsetY = y;

            for(final Patch patch : patchesY){
                // set draw size
                patch.setDrawWidth(patch.getPixelWidth() * extraScale.x *
                    (patch.isDynamicX() ? dynamicWidth : scale.x)
                );
                patch.setDrawHeight(patch.getPixelHeight() * extraScale.y *
                    (patch.isDynamicY() ? dynamicHeight : scale.y)
                );

                // setup region with stretch mode
                if(patch.isDynamicX()) {
                    if(stretchModeX == StretchMode.TILE) {
                        patch.getRegion().u2 = (dynamicWidth / scale.x);
                    }else if(stretchModeX == StretchMode.TILE_FIT) {
                        patch.getRegion().u2 = Math.max(1F, Maths.round(dynamicWidth / scale.x));
                    }
                }
                if(patch.isDynamicY()) {
                    if(stretchModeX == StretchMode.TILE) {
                        patch.getRegion().v2 = (dynamicHeight / scale.y);
                    }else if(stretchModeX == StretchMode.TILE_FIT) {
                        patch.getRegion().v2 = Math.max(1F, Maths.round(dynamicHeight / scale.y));
                    }
                }

                // draw
                final boolean isDrawPatch = (
                    patch.getDrawWidth() > 0F && patch.getDrawHeight() > 0F &&
                    (drawCenter || !patch.isDynamicX() || !patch.isDynamicY())
                );
                if(isDrawPatch) {
                    batch.draw(patch.getRegion(), offsetX, offsetY, patch.getDrawWidth(), patch.getDrawHeight());
                }

                offsetY += patch.getDrawHeight();
            }
            offsetX += patchesY[0].getDrawWidth();
        }
    }

    @Override
    public void dispose() {
        for(Patch[] patchesY: patchesXY){
            for(Patch patch: patchesY){
                final Texture2D patchTexture = patch.getRegion().getTexture();
                if(patchTexture != texture)
                    patchTexture.dispose();
            }
        }
        patchesXY = null;
        texture.dispose();
    }

}
