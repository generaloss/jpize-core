package jpize.util.pixmap;

import generaloss.stb.image.StbImage;
import jpize.util.res.Resource;
import java.nio.ByteBuffer;

public class PixmapIO {

    public static PixmapRGBA load(byte[] data, boolean flipVertically) {
        final int[] width = new int[1];
        final int[] height = new int[1];

        StbImage.setFlipVerticallyOnLoad(flipVertically);

        final ByteBuffer pixels = StbImage.loadFromMemory(data, width, height, null, 4);
        if(pixels == null)
            throw new RuntimeException("Error reading image: " + StbImage.failureReason());

        final PixmapRGBA pixmap = new PixmapRGBA(width[0], height[0]);
        pixmap.buffer()
              .put(pixels)
              .flip();

        StbImage.imageFree(pixels);
        return pixmap;
    }

    public static PixmapRGBA load(Resource res, boolean flipVertically) {
        return load(res.readBytes(), flipVertically);
    }

    public static PixmapRGBA load(String internalPath, boolean flipVertically) {
        return load(Resource.internal(internalPath), flipVertically);
    }

    public static PixmapRGBA load(Resource res) {
        return load(res, false);
    }

    public static PixmapRGBA load(String internalPath) {
        return load(internalPath, false);
    }


    public static PixmapRGBA[] load(boolean flipVertically, Resource... resources) {
        final PixmapRGBA[] array = new PixmapRGBA[resources.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(resources[i], flipVertically);
        return array;
    }

    public static PixmapRGBA[] load(boolean flipVertically, String... internalPaths) {
        final PixmapRGBA[] array = new PixmapRGBA[internalPaths.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(internalPaths[i], flipVertically);
        return array;
    }

    public static PixmapRGBA[] load(Resource... resources) {
        final PixmapRGBA[] array = new PixmapRGBA[resources.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(resources[i]);
        return array;
    }

    public static PixmapRGBA[] load(String... internalPaths) {
        final PixmapRGBA[] array = new PixmapRGBA[internalPaths.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(internalPaths[i]);
        return array;
    }
    
}
