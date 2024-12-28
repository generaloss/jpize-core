package jpize.util.ninepatch;

import jpize.gl.texture.GlWrap;
import jpize.gl.texture.Texture2D;
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
    private StretchMode stretchModeX, stretchModeY;
    private Texture2D texture;
    private Patch[][] patches;

    private int unstretWidth, unstretHeight;
    private int stretWidth, stretHeight;
    private int unstretchablesX, unstretchablesY;
    private int stretchablesX, stretchablesY;

    private float contentUnstrFactorX, contentStretFactorX, contentUnstrFactorY, contentStretFactorY;
    private float contentUnstrFactorWidth, contentStretFactorWidth, contentUnstrFactorHeight, contentStretFactorHeight;

    public NinePatch() {
        this.scale = new Vec2f(1F);
        this.extraScale = new Vec2f(1F);
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
        return (stretWidth + unstretWidth) * scale.x;
    }

    public float getMinHeight() {
        return (stretHeight + unstretHeight) * scale.y;
    }


    public float getUnstretchedWidth() {
        return (unstretWidth * scale.x);
    }

    public float getUnstretchedHeight() {
        return (unstretHeight * scale.y);
    }

    private float getStretchedWidth(float width, float unstretchedWidth) {
        return (width - unstretchedWidth);
    }

    private float getStretchedHeight(float height, float unstretchedHeight) {
        return (height - unstretchedHeight);
    }

    public float getStretchedWidth(float width) {
        return this.getStretchedWidth(width, this.getUnstretchedWidth());
    }

    public float getStretchedHeight(float height) {
        return this.getStretchedHeight(height, this.getUnstretchedHeight());
    }


    public float getContentX(float width) {
        final float unstretchedWidth = this.getUnstretchedWidth();
        final float stretchedWidth = this.getStretchedWidth(width, unstretchedWidth);
        return (
            contentUnstrFactorX * unstretchedWidth +
            contentStretFactorX * stretchedWidth
        ) * 0.5F;
    }

    public float getContentY(float height) {
        final float unstretchedHeight = this.getUnstretchedHeight();
        final float stretchedHeight = this.getStretchedHeight(height, unstretchedHeight);
        return (
            contentUnstrFactorY * unstretchedHeight +
            contentStretFactorY * stretchedHeight
        ) * 0.5F;
    }

    public float getContentWidth(float width) {
        final float unstretchedWidth = this.getUnstretchedWidth();
        final float stretchedWidth  = this.getStretchedWidth(width, unstretchedWidth);
        return (
            contentUnstrFactorWidth * unstretchedWidth +
            contentStretFactorWidth * stretchedWidth
        );
    }

    public float getContentHeight(float height) {
        final float unstretchedHeight = this.getUnstretchedHeight();
        final float stretchedHeight  = this.getStretchedHeight(height, unstretchedHeight);
        return (
            contentUnstrFactorHeight * unstretchedHeight +
            contentStretFactorHeight * stretchedHeight
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
        final int[] patchSizesX = NinePatch.getPatchSizes(pixmap, pixmap.getWidth() ,
            x -> pixmap.getPixelRGBA(x, 0)
        );
        final int[] patchSizesY = NinePatch.getPatchSizes(pixmap, pixmap.getHeight(),
            y -> pixmap.getPixelRGBA(0, y)
        );

        if(patchSizesX.length < 3 || patchSizesY.length < 3)
            throw new IllegalStateException("Invalid marked patches");

        stretchablesX = (patchSizesX.length - 1) / 2;
        stretchablesY = (patchSizesY.length - 1) / 2;
        unstretchablesX = (patchSizesX.length - stretchablesX);
        unstretchablesY = (patchSizesY.length - stretchablesY);

        // create patches
        this.createPatches(patchSizesX, patchSizesY);
        this.updateSizes();

        this.getContentSize(pixmap, patchSizesX, patchSizesY);

        pixmap.dispose();
        return this;
    }

    private void getContentSize(PixmapRGBA pixmap, int[] patchSizesX, int[] patchSizesY) {
        final int pixmapBoundY = (pixmap.getHeight() - 1);
        final float[] contentSizesX = NinePatch.getContentSizes(pixmap, pixmap.getWidth() - 1,
            x -> pixmap.getPixelRGBA(x, 0),
            x -> pixmap.getPixelRGBA(x, pixmapBoundY)
        );

        contentUnstrFactorX = contentSizesX[0];
        contentStretFactorX = contentSizesX[1];
        contentUnstrFactorWidth = contentSizesX[2];
        contentStretFactorWidth = contentSizesX[3];

        System.out.println(contentSizesX[0] + " / " + unstretWidth);
        final int pixmapBoundX = (pixmap.getWidth() - 1);
        final float[] contentSizesY = NinePatch.getContentSizes(pixmap, pixmap.getHeight() - 1,
            y -> pixmap.getPixelRGBA(0, y),
            y -> pixmap.getPixelRGBA(pixmapBoundX, y)
        );

        contentUnstrFactorY = contentSizesY[0];
        contentStretFactorY = contentSizesY[1];
        contentUnstrFactorHeight = contentSizesY[2];
        contentStretFactorHeight = contentSizesY[3];
    }

    private void createPatches(int[] sizesX, int[] sizesY) {
        patches = new Patch[sizesX.length][sizesY.length];
        int x = 1;
        boolean stretchableX = false;

        for(int i = 0; i < sizesX.length; i++){
            final int width = sizesX[i];
            int y = 1;
            boolean stretchableY = false;

            for(int j = 0; j < sizesY.length; j++){
                final int height = sizesY[j];

                final TextureRegion region;
                if(stretchableX || stretchableY){
                    final PixmapRGBA patchPixmap = new PixmapRGBA(width, height);
                    texture.getSubImage(x, y, width, height, patchPixmap);

                    final Texture2D patchTexture = new Texture2D(patchPixmap);
                    patchTexture.setWrapST(GlWrap.REPEAT);

                    region = new TextureRegion(patchTexture);
                    patchPixmap.dispose();
                }else{
                    region = new TextureRegion(texture, x, y, width, height);
                }
                final Patch patch = new Patch(region, stretchableX, stretchableY);

                patches[i][sizesY.length - 1 - j] = patch;
                stretchableY = !stretchableY;

                y += height;
            }
            stretchableX = !stretchableX;
            x += width;
        }
    }

    private void updateSizes() {
        stretWidth = 0;
        stretHeight = 0;
        unstretWidth = 0;
        unstretHeight = 0;

        for(Patch[] patchesY : patches){
            final Patch patch = patchesY[1];
            final int width = (int) patch.region.getPixelWidth();
            if(patch.stretchableX){
                stretWidth += width;
            }else{
                unstretWidth += width;
            }
        }
        for(Patch patch : patches[1]){
            final int height = (int) patch.region.getPixelHeight();
            if(patch.stretchableY){
                stretHeight += height;
            }else{
                unstretHeight += height;
            }
        }
    }

    private static int[] getPatchSizes(PixmapRGBA pixels, int size, Function<Integer, Integer> colorFunc) {
        final IntList partsList = new IntList(3);
        boolean prevStretchable = false;
        int prevPosition = 0;

        int x = 0;
        while(x < size){
            final int color = colorFunc.apply(x);
            final boolean stretchable = (
                (color & 0xFF) == 255 &&
                    (color & 0xFFFFFF00) == 0 // is black
            );
            if(prevStretchable != stretchable){
                prevStretchable = stretchable;

                partsList.add(x - prevPosition);
                prevPosition = x;
            }
            x++;
        }
        partsList.add(x - prevPosition);

        partsList.elementSub(0, 1);
        partsList.elementSub(partsList.lastIdx(), 1);

        return partsList.arrayTrimmed();
    }

    private static float[] getContentSizes(PixmapRGBA pixels, int size,
                                         Function<Integer, Integer> colorFuncPatch,
                                         Function<Integer, Integer> icolorFuncContent) {
        final float[] result = new float[4];

        boolean prevIsContent = false;
        float stretchablePixels = 0;
        float unstretchablePixels = 0;

        int x = 1;
        while(x < size){
            final int colorContent = icolorFuncContent.apply(x);
            final boolean isContent = (
                (colorContent & 0xFF) == 255 &&
                (colorContent & 0xFFFFFF00) == 0 // is black
            );

            if(prevIsContent && !isContent)
                break;
            prevIsContent = isContent;

            final int colorPatch = colorFuncPatch.apply(x);
            final boolean isStretchablePatch = (
                (colorPatch & 0xFF) == 255 &&
                (colorPatch & 0xFFFFFF00) == 0 // is black
            );
            if(isStretchablePatch){
                stretchablePixels++;
            }else{
                unstretchablePixels++;
            }

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

            x++;
        }

        result[0] /= unstretchablePixels;
        result[1] /= stretchablePixels;
        result[2] /= unstretchablePixels;
        result[3] /= stretchablePixels;

        return result;
    }



    public void draw(TextureBatch batch, float x, float y, float width, float height) {
        if(patches == null || width < 1 || height < 1)
            return;

        // min size
        final float minWidth = this.getMinWidth();
        final float minHeight = this.getMinHeight();
        extraScale.set(1F);
        if(width < minWidth){
            extraScale.x = (width / minWidth);
            width = minWidth;
        }
        if(height < minHeight){
            extraScale.y = (height / minHeight);
            height = minHeight;
        }

        final float stretchedWidth = this.getStretchedWidth(width) / stretchablesX;
        final float stretchedHeight = this.getStretchedWidth(height) / stretchablesY;

        // iterate patches
        float offsetX = x;
        for(final Patch[] patchesY : patches){
            float offsetY = y;

            for(final Patch patch : patchesY){
                // set patch draw size
                patch.width  = (patch.stretchableX ? stretchedWidth  : patch.region.getPixelWidth()  * scale.x) * extraScale.x;
                patch.height = (patch.stretchableY ? stretchedHeight : patch.region.getPixelHeight() * scale.y) * extraScale.y;

                // setup region with stretch mode
                if(patch.stretchableX){
                    if(stretchModeX == StretchMode.TILE) {
                        patch.region.u2 =
                            stretchedWidth * stretchablesX / (stretWidth * scale.x);
                    }else if(stretchModeX == StretchMode.TILE_FIT){
                        patch.region.u2 = Math.max(1, Maths.round(
                            stretchedWidth * stretchablesX / (stretWidth * scale.x)
                        ));
                    }
                }
                if(patch.stretchableY){
                    if(stretchModeX == StretchMode.TILE) {
                        patch.region.v2 =
                            stretchedHeight * stretchablesY / (stretHeight * scale.y);
                    }else if(stretchModeX == StretchMode.TILE_FIT){
                        patch.region.v2 = Math.max(1, Maths.round(
                            stretchedHeight * stretchablesY / (stretHeight * scale.y)
                        ));
                    }
                }

                // draw
                if(patch.width > 0 && patch.height > 0)
                    batch.draw(patch.region, offsetX, offsetY, patch.width, patch.height);

                offsetY += patch.height;
            }
            offsetX += patchesY[0].width;
        }
    }

    @Override
    public void dispose() {
        for(Patch[] patchesY: patches){
            for(Patch patch: patchesY){
                final Texture2D patchTexture = patch.region.getTexture();
                if(patchTexture != texture)
                    patchTexture.dispose();
            }
        }
        texture.dispose();
        patches = null;
    }

}
