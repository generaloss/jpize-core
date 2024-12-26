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

    private int unstretchableWidth, unstretchableHeight;
    private int stretchableWidth, stretchableHeight;
    private int unstretchablesX, unstretchablesY;
    private int stretchablesX, stretchablesY;

    private float contentX_u, contentX_s, contentY_u, contentY_s;
    private float contentWidth_u, contentWidth_s, contentHeight_u, contentHeight_s;

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
        return (stretchableWidth + unstretchableWidth) * scale.x;
    }

    public float getMinHeight() {
        return (stretchableHeight + unstretchableHeight) * scale.y;
    }


    public float getContentX() {
        final float contentX = (contentX_u * unstretchableWidth + contentX_s * stretchableWidth);
        return (contentX * scale.x * extraScale.x);
    }

    public float getContentY() {
        final float contentY = (contentY_u * unstretchableHeight + contentY_s * stretchableHeight);
        return (contentY * scale.y * extraScale.y);
    }

    public float getContentWidth() {
        final float contentWidth = (contentWidth_u * unstretchableWidth + contentWidth_s * stretchableWidth);
        return (contentWidth * scale.x * extraScale.x);
    }

    public float getContentHeight() {
        final float contentHeight = (contentHeight_u * unstretchableHeight + contentHeight_s * stretchableHeight);
        return (contentHeight * scale.y * extraScale.y);
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
        final int[] patchSizesX = NinePatch.getAxisSizes(pixmap, pixmap.getWidth() ,
            x -> pixmap.getPixelRGBA(x, 0));
        final int[] patchSizesY = NinePatch.getAxisSizes(pixmap, pixmap.getHeight(),
            y -> pixmap.getPixelRGBA(0, y));

        if(patchSizesX.length < 3 || patchSizesY.length < 3)
            throw new IllegalStateException("Invalid marked patches");

        stretchablesX = (patchSizesX.length - 1) / 2;
        stretchablesY = (patchSizesY.length - 1) / 2;
        unstretchablesX = (patchSizesX.length - stretchablesX);
        unstretchablesY = (patchSizesY.length - stretchablesY);

        // create patches
        this.createPatches(patchSizesX, patchSizesY);
        this.updateSizes();

        this.getContentSize(pixmap);

        pixmap.dispose();
        return this;
    }

    private void getContentSize(PixmapRGBA pixmap) {
        final int pixmapBoundY = (pixmap.getHeight() - 1);
        final int pixmapBoundX = (pixmap.getWidth() - 1);

        final int[] contentSizesX = NinePatch.getAxisSizes(pixmap, pixmap.getWidth(),
            x -> pixmap.getPixelRGBA(x, pixmapBoundY));

        final int[] contentSizesY = NinePatch.getAxisSizes(pixmap, pixmap.getHeight(),
            y -> pixmap.getPixelRGBA(pixmapBoundX, y));

        if((contentSizesX.length != 3 && contentSizesX.length != 1) ||
            (contentSizesY.length != 3 && contentSizesY.length != 1))
            throw new IllegalStateException("Invalid marked content area");

        // contentX = (contentSizesX.length == 1 ? 0 : contentSizesX[0]);
        // contentY = (contentSizesY.length == 1 ? 0 : contentSizesY[0]);
        // contentWidth  = (contentSizesX[1 - 1 / contentSizesX.length]);
        // contentHeight = (contentSizesY[1 - 1 / contentSizesY.length]);

        if(contentSizesX.length == 1) {
            contentX_u = 0F;
            contentX_s = 0F;
        }else{
            contentX_u = 0F;
            contentX_s = 0F;
        }

        if(contentSizesY.length == 1) {
            contentY_u = 0F;
            contentY_s = 0F;
        }else{
            contentY_u = 0F;
            contentY_s = 0F;
        }
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
        stretchableWidth = 0;
        stretchableHeight = 0;
        unstretchableWidth = 0;
        unstretchableHeight = 0;

        for(Patch[] patchesY : patches){
            final Patch patch = patchesY[1];
            final int width = (int) patch.region.getPixelWidth();
            if(patch.stretchableX){
                stretchableWidth += width;
            }else{
                unstretchableWidth += width;
            }
        }
        for(Patch patch : patches[1]){
            final int height = (int) patch.region.getPixelHeight();
            if(patch.stretchableY){
                stretchableHeight += height;
            }else{
                unstretchableHeight += height;
            }
        }
    }

    private static int[] getAxisSizes(PixmapRGBA pixels, int size, Function<Integer, Integer> indexToColorRgbaFunc) {
        final IntList partsList = new IntList(3);
        boolean prevStretchable = false;
        int prevPosition = 0;
        System.out.println("a");
        int x = 0;
        while(x < size){
            final int color = indexToColorRgbaFunc.apply(x);
            final boolean stretchable = (
                (color & 0xFF) == 255 &&
                    (color & 0xFFFFFF00) == 0 // is black
            );
            System.out.println("is black: " + stretchable);
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


    public void draw(TextureBatch batch, float x, float y, float width, float height) {
        if(patches == null)
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

        // total stretched patches size
        final float stretchedWidth  = (width  - unstretchableWidth  * scale.x) / stretchablesX;
        final float stretchedHeight = (height - unstretchableHeight * scale.y) / stretchablesY;

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
                            stretchedWidth * stretchablesX / (stretchableWidth * scale.x);
                    }else if(stretchModeX == StretchMode.TILE_FIT){
                        patch.region.u2 = Math.max(1, Maths.round(
                            stretchedWidth * stretchablesX / (stretchableWidth * scale.x)
                        ));
                    }
                }
                if(patch.stretchableY){
                    if(stretchModeX == StretchMode.TILE) {
                        patch.region.v2 =
                            stretchedHeight * stretchablesY / (stretchableHeight * scale.y);
                    }else if(stretchModeX == StretchMode.TILE_FIT){
                        patch.region.v2 = Math.max(1, Maths.round(
                            stretchedHeight * stretchablesY / (stretchableHeight * scale.y)
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
