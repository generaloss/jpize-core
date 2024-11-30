package jpize.util;

import jpize.util.res.ExternalResource;
import org.lwjgl.BufferUtils;
import jpize.app.Jpize;
import jpize.util.res.Resource;
import jpize.gl.texture.GlBaseFormat;
import jpize.gl.type.GlType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.lwjgl.opengl.GL33.glReadPixels;

public class ScreenUtils{

    public static void saveScreenshot(String format, OutputStream stream){
        final int width = Jpize.getWidth();
        final int height = Jpize.getHeight();
        
        final ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4).order(ByteOrder.LITTLE_ENDIAN);
        glReadPixels(0, 0, width, height, GlBaseFormat.BGRA.value, GlType.UNSIGNED_BYTE.value, buffer);
        
        final int[] pixels = new int[width * height];
        buffer.asIntBuffer().get(pixels);
        
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                bufferedImage.setRGB(x, height - y - 1, pixels[y * width + x]);

        try{
            ImageIO.write(bufferedImage, format, stream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveScreenshot(ExternalResource resource){
        saveScreenshot(resource.extension(), resource.outStream());
    }

    public static void saveScreenshot(String internalPath){
        saveScreenshot(Resource.external(internalPath));
    }

}
