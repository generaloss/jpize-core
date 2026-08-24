import generaloss.resourceflow.resource.FileResource;
import generaloss.resourceflow.resource.Resource;
import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.opengl.texture.Texture2D;
import jpize.util.RenderQuad;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;
import nester.Nester;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NesterTest extends JpizeApplication {

    private Nester<String> nester;
    private Texture2D[] textures;

    public void init() {
        GL.clearColor(0.3, 0.4, 0.7);
        this.nester = new Nester<>();
        for(FileResource res: Resource.file("/home/user/Downloads/nesting/1").listResources())
            if(res.isFile())
                nester.put(res.simpleName(), res);

        nester.setPadding(0, 0, 0, 0);
        nester.enablePaddingFilling(false);
        nester.build(2480, 3508);

        final List<PixmapRGBA> pixmaps = nester.getPixmaps();
        this.textures = new Texture2D[pixmaps.size()];
        for(int i = 0; i < textures.length; i++) {
            final PixmapRGBA pixmap = pixmaps.get(i);
            this.textures[i] = new Texture2D(pixmap);
            save(pixmap, "/home/user/Downloads/nesting/nested_" + i + ".png");
        }

        Jpize.exit();
    }

    private static void save(PixmapRGBA pixmap, String name) {
        final BufferedImage image = new BufferedImage(pixmap.getWidth(), pixmap.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < pixmap.getWidth(); i++)
            for(int j = 0; j < pixmap.getHeight(); j++)
                image.setRGB(i, j, pixmap.getPixelARGB(i, j));

        final FileResource res = Resource.file(name);
        res.createWithParents();
        try {
            ImageIO.write(image, "PNG", res.file());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    int index = 0;

    @Override
    public void render() {
        GL.clearColorBuffer();
        RenderQuad.instance().render(textures[index]);
        if(Key.SPACE.down())
            index = (index + 1) % textures.length;
    }

    @Override
    public void dispose() {
        nester.dispose();
    }


    public static void main(String[] args) {
        GlfwContextBuilder.create(3508 / 2, 2480 / 2, "Atlas Test")
            .icon("/icon2.png")
            .build().setApp(new NesterTest());
        GlfwContextManager.run();
    }

}
