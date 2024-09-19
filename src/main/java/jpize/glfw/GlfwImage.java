package jpize.glfw;

import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.res.Resource;
import org.lwjgl.glfw.GLFWImage;

public class GlfwImage {

    private final GLFWImage image;

    public GlfwImage() {
        this.image = GLFWImage.calloc();
    }

    public GlfwImage(GLFWImage image) {
        this();
        this.set(image);
    }

    public GlfwImage(GlfwImage image) {
        this();
        this.set(image);
    }

    public GlfwImage(PixmapRGBA pixmap) {
        this();
        this.set(pixmap);
    }

    public GlfwImage(Resource resource) {
        this(PixmapIO.load(resource));
    }

    public GlfwImage(String filepath) {
        this(PixmapIO.load(filepath));
    }


    public GLFWImage getGLFWImage() {
        return image;
    }

    public GlfwImage set(GLFWImage image) {
        this.image.set(image);
        return this;
    }

    public GlfwImage set(GlfwImage image) {
        this.image.set(image.getGLFWImage());
        return this;
    }

    public GlfwImage set(PixmapRGBA pixmap) {
        this.image.set(pixmap.getWidth(), pixmap.getHeight(), pixmap.getBuffer());
        return this;
    }

    public int getWidth() {
        return image.width();
    }

    public int getHeight() {
        return image.height();
    }

    public void free() {
        image.free();
    }


    public static GLFWImage.Buffer makeBuffer(GlfwImage... images) {
        final GLFWImage.Buffer buffer = GLFWImage.calloc(images.length);
        for(GlfwImage image: images)
            buffer.put(image.getGLFWImage());
        buffer.flip();
        return buffer;
    }

    public static GlfwImage[] makeArray(PixmapRGBA... pixmap) {
        final GlfwImage[] array = new GlfwImage[pixmap.length];
        for(int i = 0; i < array.length; i++)
            array[i] = new GlfwImage(pixmap[i]);
        return array;
    }

    public static GlfwImage[] loadArray(Resource... resources) {
        final GlfwImage[] array = new GlfwImage[resources.length];
        for(int i = 0; i < array.length; i++)
            array[i] = new GlfwImage(resources[i]);
        return array;
    }

    public static GlfwImage[] loadArray(String... filepaths) {
        final GlfwImage[] array = new GlfwImage[filepaths.length];
        for(int i = 0; i < array.length; i++)
            array[i] = new GlfwImage(filepaths[i]);
        return array;
    }

}
