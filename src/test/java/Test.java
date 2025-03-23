import jpize.stb.freetype.FreeType;

public class Test {

    public static int toInt(int value) {
        return ((value + 63) & -64) >> 6;
    }

    public static void main(String[] args) throws Exception {
        final FreeType library = FreeType.initFreeType();

        // final Face face = library.newFace(Resource.internal("/lsans.ttf"), 0);
        // face.setPixelSizes(0, 15);
        // final SizeMetrics faceMetrics = face.getSize().getMetrics();
        // System.out.println(toInt(faceMetrics.getAscender()) + ", " + toInt(faceMetrics.getDescender()) + ", " + toInt(faceMetrics.getHeight()));

        // String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*�?�?�?�?�? ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
        // for(int i = 0; i < chars.length(); i++) {
        //     if(!face.loadGlyph(face.getCharIndex(chars.charAt(i)), 0)) continue;
        //     if(!face.getGlyph().renderGlyph(FT_RENDER_MODE_NORMAL)) continue;
        //     final Bitmap bitmap = face.getGlyph().getBitmap();
        //     final GlyphMetrics glyphMetrics = face.getGlyph().getMetrics();
        //     System.out.println(toInt(glyphMetrics.getHoriBearingX()) + ", " + toInt(glyphMetrics.getHoriBearingY()));
        //     System.out.println(toInt(glyphMetrics.getWidth()) + ", " + toInt(glyphMetrics.getHeight()) + ", " + toInt(glyphMetrics.getHoriAdvance()));
        //     System.out.println(bitmap.getWidth() + ", " + bitmap.getRows() + ", " + bitmap.getPitch() + ", " + bitmap.getNumGray());
        //     for(int y = 0; y < bitmap.getRows(); y++) {
        //         for(int x = 0; x < bitmap.getWidth(); x++) {
        //             System.out.print(bitmap.getBuffer().get(x + bitmap.getPitch() * y) != 0? "X": " ");
        //         }
        //         System.out.println();
        //     }
        // }

        // face.dispose();
        // library.dispose();
    }

}
