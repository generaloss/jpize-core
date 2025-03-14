package jpize.util.font;

import jpize.util.Utils;
import jpize.util.res.*;
import jpize.opengl.texture.GlFilter;
import jpize.util.region.Region;
import jpize.opengl.texture.Texture2D;
import jpize.util.io.FastReader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;

class FNTFontLoader {

    private static String getValue(String token) {
        return token.split("=")[1];
    }

    public static Font load(Font font, Resource resource, boolean linearFilter) {
        // clear font
        font.pages().clear();
        font.glyphs().clear();

        // read
        final FastReader reader = resource.reader();

        while(reader.hasNext()){
            final String[] tokens = reader.nextLine().trim().split("\\s+");

            switch(tokens[0].toLowerCase()){
                case "info" -> font.setItalic(Integer.parseInt(getValue(tokens[4])) == 1);
                case "common" -> {
                    font.setHeight(Integer.parseInt(getValue(tokens[1])));
                    font.setAscent(Integer.parseInt(getValue(tokens[2])));
                    font.setDescent(font.getAscent() - font.getHeight());
                }
                case "page" -> {
                    final int pageID = Integer.parseInt(getValue(tokens[1]));
                    String pageRelativePath = getValue(tokens[2]).replace("\"", "");

                    // page path
                    final Path path = Path.of(resource.path());
                    if(path.getParent() != null){
                        final Path pagePath = Path.of(path.getParent() + "/" + pageRelativePath);
                        pageRelativePath = pagePath.normalize().toString();
                    }
                    pageRelativePath = Utils.osGeneralizePath(pageRelativePath);

                    // page resource
                    final Resource pageResource;
                    if(resource.isInternalRes()){
                        pageResource = Resource.internal(pageRelativePath);
                    }else if(resource.isFileRes()){
                        pageResource = Resource.file(pageRelativePath);
                    }else if(resource.isZipRes()){
                        final ZipResource zipRes = resource.asZipRes();
                        final ZipFile file = zipRes.file();
                        pageResource = Resource.zip(file, file.getEntry(pageRelativePath));
                    }else{
                        throw new IllegalArgumentException("Unable to load FNT font from UrlResource.");
                    }

                    // page texture
                    final Texture2D texture = new Texture2D()
                        .setFilters(linearFilter ? GlFilter.LINEAR : GlFilter.NEAREST)
                        .setImage(pageResource);

                    font.pages().put(pageID, texture);
                }
                case "char" -> {
                    final int code = Integer.parseInt(getValue(tokens[1]));

                    final int page = Integer.parseInt(getValue(tokens[9]));
                    final Texture2D pageTexture = font.pages().get(page);

                    final float s0 = (float) Integer.parseInt(getValue(tokens[2])) / pageTexture.getWidth();
                    final float t0 = (float) Integer.parseInt(getValue(tokens[3])) / pageTexture.getHeight();
                    final float s1 = (float) Integer.parseInt(getValue(tokens[4])) / pageTexture.getWidth() + s0;
                    final float t1 = (float) Integer.parseInt(getValue(tokens[5])) / pageTexture.getHeight() + t0;

                    final int offsetX = Integer.parseInt(getValue(tokens[6]));
                    final int offsetY = Integer.parseInt(getValue(tokens[7]));
                    final int advanceX = Integer.parseInt(getValue(tokens[8]));

                    final Region regionOnTexture = new Region(s0, t0, s1, t1);
                    final float glyphHeight = regionOnTexture.getPixelHeight(font.pages().get(page));
                    final float glyphWidth = regionOnTexture.getPixelWidth(font.pages().get(page));

                    font.glyphs().put(code, new GlyphInfo(
                        code,

                        offsetX,
                        (font.getHeight() - offsetY - glyphHeight),
                        glyphWidth,
                        glyphHeight,

                        regionOnTexture,
                        advanceX,
                        page
                    ));
                }
                case "kerning" -> {
                    final int code_0 = Integer.parseInt(getValue(tokens[1]));
                    final int code_1 = Integer.parseInt(getValue(tokens[2]));
                    final int advance = Integer.parseInt(getValue(tokens[3]));

                    // add kerning entry
                    final Map<Integer, Integer> kerning = font.kernings().getOrDefault(code_0, new HashMap<>());
                    kerning.put(code_1, advance);
                    font.kernings().put(code_0, kerning);
                }
            }
        }
        reader.close();
        return font;
    }

    public static Font load(Font font, String internalPath, boolean linearFilter) {
        return load(font, Resource.internal(internalPath), linearFilter);
    }

}
