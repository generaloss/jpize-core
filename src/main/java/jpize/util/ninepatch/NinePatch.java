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

    private final Vec2f scale;
    private StretchMode stretchModeX, stretchModeY;
    private Texture2D texture;
    private Patch[][] patches;

    private int unstretchableWidth, unstretchableHeight;
    private int stretchableWidth, stretchableHeight;
    private int stretchablesX, stretchablesY;
    private int unstretchablesX, unstretchablesY;

    public NinePatch() {
        this.scale = new Vec2f(1F);
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

    public NinePatch setStretchMode(StretchMode mode) {
        this.stretchModeX = mode;
        this.stretchModeY = mode;
        return this;
    }


    public float getMinWidth() {
        return (stretchableWidth + unstretchableWidth) * scale.x;
    }

    public float getMinHeight() {
        return (stretchableHeight + unstretchableHeight) * scale.y;
    }


    public void load(Texture2D texture) {
        this.texture = texture;

        // load pixels
        final PixmapRGBA pixmap = new PixmapRGBA(texture.getWidth(), texture.getHeight());
        texture.getImage(pixmap);

        // get lenghts
        final int[] sizesX = this.getAxisSizes(pixmap, pixmap.getWidth() , x -> pixmap.getPixelRGBA(x, 0));
        final int[] sizesY = this.getAxisSizes(pixmap, pixmap.getHeight(), y -> pixmap.getPixelRGBA(0, y));
        pixmap.dispose();

        if(sizesX.length < 3 || sizesY.length < 3)
            return;

        stretchablesX = (sizesX.length - 1) / 2;
        stretchablesY = (sizesY.length - 1) / 2;
        unstretchablesX = (sizesX.length - stretchablesX);
        unstretchablesY = (sizesY.length - stretchablesY);

        // create patches
        this.createPatches(sizesX, sizesY);
        this.updateSizes();
    }

    public void load(Pixmap pixmap) {
        this.load(new Texture2D(pixmap));
    }

    public void load(String internalPath) {
        this.load(new Texture2D(internalPath));
    }


    private int[] getAxisSizes(PixmapRGBA pixels, int size, Function<Integer, Integer> indexToColorRgbaFunc) {
        final IntList partsList = new IntList();
        boolean prevStretchable = false;
        int prevPosition = 0;

        int x = 0;
        while(x < size){
            final int color = indexToColorRgbaFunc.apply(x);
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

        if(partsList.get(0) == 1 || partsList.getLast() == 1)
            throw new IllegalStateException("Invalid marked stretchable area");

        partsList.elementSub(0, 1);
        partsList.elementSub(partsList.size() - 1, 1);
        partsList.trim();
        return partsList.array();
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

        System.out.println(stretchableWidth + ", " + unstretchableHeight);
    }


    public void draw(TextureBatch batch, float x, float y, float width, float height) {
        if(patches == null)
            return;

        // clamp draw size to minimum
        width = Math.max(width, this.getMinWidth());
        height = Math.max(height, this.getMinHeight());

        // total stretched patches size
        final float stretchedWidth  = (width  - unstretchableWidth  * scale.x) / stretchablesX;
        final float stretchedHeight = (height - unstretchableHeight * scale.y) / stretchablesY;

        // iterate patches
        float offsetX = x;
        for(final Patch[] patchesY : patches){
            float offsetY = y;

            for(final Patch patch : patchesY){
                // set patch draw size
                patch.width  = (patch.stretchableX ? stretchedWidth  : patch.region.getPixelWidth()  * scale.x);
                patch.height = (patch.stretchableY ? stretchedHeight : patch.region.getPixelHeight() * scale.y);

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
                batch.draw(patch.region, offsetX, offsetY, patch.width, patch.height);
                offsetY += patch.height;
            }
            offsetX += patchesY[0].width;
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
