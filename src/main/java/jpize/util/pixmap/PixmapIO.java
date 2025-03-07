package jpize.util.pixmap;

import jpize.util.res.Resource;
import jpize.util.res.FileResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;

public class PixmapIO {

    public static PixmapRGBA load(boolean invX, boolean invY, BufferedImage image) {
        if(image == null)
            throw new IllegalArgumentException("Unable to load null image");

        final int width = image.getWidth();
        final int height = image.getHeight();
        
        final PixmapRGBA pixmap = new PixmapRGBA(width, height);
        final ByteBuffer buffer = pixmap.buffer();
        
        final int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                final int x = (!invX ? i : (width  - 1 - i));
                final int y = (!invY ? j : (height - 1 - j));
                final int pixel = pixels[y * width + x];

                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8 ) & 0xFF));
                buffer.put((byte) ((pixel      ) & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }
        buffer.flip();

        return pixmap;
    }

    public static PixmapRGBA load(boolean invX, boolean invY, Resource res) {
        try{
            return load(invX, invY, ImageIO.read(res.inStream()));
        }catch(IOException e){
            throw new RuntimeException("Pixmap '" + res + "' does not exists");
        }
    }

    public static PixmapRGBA load(boolean invX, boolean invY, String internalPath) {
        return load(invX, invY, Resource.internal(internalPath));
    }

    public static PixmapRGBA load(BufferedImage image) {
        return load(false, false, image);
    }

    public static PixmapRGBA load(Resource res) {
        return load(false, false, res);
    }

    public static PixmapRGBA load(String internalPath) {
        return load(false, false, internalPath);
    }


    public static PixmapRGBA[] load(boolean invX, boolean invY, BufferedImage... images) {
        final PixmapRGBA[] array = new PixmapRGBA[images.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(invX, invY, images[i]);
        return array;
    }

    public static PixmapRGBA[] load(boolean invX, boolean invY, Resource... resources) {
        final PixmapRGBA[] array = new PixmapRGBA[resources.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(invX, invY, resources[i]);
        return array;
    }

    public static PixmapRGBA[] load(boolean invX, boolean invY, String... internalPaths) {
        final PixmapRGBA[] array = new PixmapRGBA[internalPaths.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(invX, invY, internalPaths[i]);
        return array;
    }

    public static PixmapRGBA[] load(BufferedImage... images) {
        final PixmapRGBA[] array = new PixmapRGBA[images.length];
        for(int i = 0; i < array.length; i++)
            array[i] = load(images[i]);
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
    
    
    public static void save(PixmapRGBA pixmap, OutputStream stream) {
        final BufferedImage bufferedImage = new BufferedImage(
                pixmap.getWidth(), pixmap.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for(int x = 0; x < pixmap.getWidth(); x++)
            for(int y = 0; y < pixmap.getHeight(); y++)
                bufferedImage.setRGB(x, y, pixmap.getPixelARGB(x, y));

        try{
            ImageIO.write(bufferedImage, "PNG", stream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static void save(PixmapRGBA pixmap, File file) {
        try{
            save(pixmap, new FileOutputStream(file));
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    
    public static void save(PixmapRGBA pixmap, String externalPath) {
        final FileResource resource = Resource.file(externalPath);
        resource.mkAll();
        save(pixmap, resource.file());
    }
    
}
