package jpize.util.ninepatch;

import jpize.gl.texture.Texture2D;
import jpize.util.Disposable;
import jpize.util.array.IntList;
import jpize.util.math.vector.Vec2f;
import jpize.util.mesh.TextureBatch;
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
    private int totalStretchablesX, totalStretchablesY;

    public NinePatch() {
        this.scale = new Vec2f(1F);
        this.setStretchModeX(StretchMode.STRETCH);
        this.setStretchModeY(StretchMode.STRETCH);
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


    public void load(Texture2D texture) {
        this.texture = texture;

        // load pixels
        final PixmapRGBA pixmap = new PixmapRGBA(texture.getWidth(), texture.getHeight());
        texture.getImage(pixmap);

        // get lenghts
        final int[] sizesX = this.getAxisSizes(pixmap, x -> pixmap.getPixelRGBA(x, 0));
        final int[] sizesY = this.getAxisSizes(pixmap, y -> pixmap.getPixelRGBA(0, y));
        pixmap.dispose();

        if(sizesX.length < 3 || sizesY.length < 3)
            return;

        totalStretchablesX = (sizesX.length - 1) / 2;
        totalStretchablesY = (sizesY.length - 1) / 2;

        // create patches
        this.createPatches(sizesX, sizesY);
        this.updateSizes();
    }

    public void load(PixmapRGBA pixmap) {
        this.load(new Texture2D(pixmap));
    }

    public void load(String internalPath) {
        this.load(new Texture2D(internalPath));
    }


    private int[] getAxisSizes(PixmapRGBA pixels, Function<Integer, Integer> indexToColorRgbaFunc) {
        final IntList partsList = new IntList();
        boolean prevStretchable = false;
        int prevPosition = 0;

        int x = 0;
        while(x < pixels.getWidth()){
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
            final int width  = sizesX[i];
            int y = 1;
            boolean stretchableY = false;

            for(int j = (sizesY.length - 1); j >= 0; j--){
                final int height = sizesY[j];

                final TextureRegion region = new TextureRegion(texture, x, y, width, height);
                final Patch patch = new Patch(region, stretchableX, stretchableY);
                patch.top = (j == sizesY.length - 1);
                patch.left = (i == 0);
                patch.bottom = (j == 0);
                patch.right = (j == sizesX.length - 1);

                patches[i][j] = patch;
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
            final Patch patch = patchesY[0];
            final int width = (int) patch.region.getPixelWidth();
            if(patch.stretchableX){
                stretchableWidth += width;
            }else{
                unstretchableWidth += width;
            }
        }
        for(Patch patch : patches[0]){
            final int height = (int) patch.region.getPixelHeight();
            if(patch.stretchableY){
                stretchableHeight += height;
            }else{
                unstretchableHeight += height;
            }
        }
    }


    private Vec2f getStretchedSize(float width, float height) {
        float stretchedWidth  = (width  - unstretchableWidth  * scale.x);
        float stretchedHeight = (height - unstretchableHeight * scale.y);
        if(stretchModeX == StretchMode.TILE){}
    }

    public void draw(TextureBatch batch, float x, float y, float width, float height) {
        if(patches == null)
            return;

        final Vec2f stretchedSize = this.getStretchedSize(width, height);

        float offsetX = x;
        for(final Patch[] patchesY : patches){
            float offsetY = y;

            for(final Patch patch : patchesY){
                batch.draw(
                    patch.region,
                    offsetX,
                    offsetY,
                    patch.region.getPixelWidth()  * 60 * (patch.stretchableX ? 3 : 1),
                    patch.region.getPixelHeight() * 60 * (patch.stretchableY ? 3 : 1)
                );
                offsetY += patch.region.getPixelHeight() * 60;
            }
            offsetX += patchesY[0].region.getPixelWidth() * 60;
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
