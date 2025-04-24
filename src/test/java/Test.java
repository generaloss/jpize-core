import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;

public class Test {

    public static void main(String[] args) {
        PixmapRGBA pixmap = PixmapIO.load("/icon2.png");
        pixmap.dispose();
    }

}
