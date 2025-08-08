package jpize.util.font;

import generaloss.resourceflow.ResUtils;
import generaloss.resourceflow.resource.*;
import jpize.opengl.texture.GLFilter;
import jpize.util.region.Region;
import jpize.opengl.texture.Texture2D;
import generaloss.resourceflow.stream.FastReader;

import java.nio.file.Path;
import java.util.zip.ZipFile;

class FNTFontLoader {

    private static String getValue(String token) {
        return token.split("=")[1];
    }

    private static int getIntValue(String token) {
        return Integer.parseInt(getValue(token));
    }

    private static float getFloatValue(String token) {
        return (float) getIntValue(token);
    }

    public static Font load(Font font, Resource resource, FontLoadOptions options) {
        // clear font
        font.dispose();

        // read
        final FastReader reader = resource.reader();

        while(reader.hasNext()){
            final String[] tokens = reader.nextLine().trim().split("\\s+");

            switch(tokens[0].toLowerCase()){
                case "info" -> font.setItalic(getIntValue(tokens[4]) == 1);
                case "common" -> {
                    font.setHeight(getIntValue(tokens[1]));
                    font.setAscent(getIntValue(tokens[2]));
                    font.setDescent(font.getAscent() - font.getHeight());
                }
                case "page" -> {
                    final int pageID = getIntValue(tokens[1]);
                    String pageRelativePath = getValue(tokens[2]).replace("\"", "");

                    // page path
                    final Path path = Path.of(resource.path());
                    if(path.getParent() != null){
                        final Path pagePath = Path.of(path.getParent() + "/" + pageRelativePath);
                        pageRelativePath = pagePath.normalize().toString();
                    }
                    pageRelativePath = ResUtils.osGeneralizePath(pageRelativePath);

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
                        .setFilters(options.isLinearFilter() ? GLFilter.LINEAR : GLFilter.NEAREST)
                        .setImage(pageResource);

                    font.pages().put(pageID, texture);
                }
                case "char" -> {
                    final int code = getIntValue(tokens[1]);

                    final int pageID = getIntValue(tokens[9]);
                    final Texture2D pageTexture = font.pages().get(pageID);

                    final float s0 = (getFloatValue(tokens[2]) / pageTexture.getWidth());
                    final float t0 = (getFloatValue(tokens[3]) / pageTexture.getHeight());
                    final float s1 = (getFloatValue(tokens[4]) / pageTexture.getWidth() + s0);
                    final float t1 = (getFloatValue(tokens[5]) / pageTexture.getHeight() + t0);

                    final Region regionOnTexture = new Region(s0, t0, s1, t1);
                    final float glyphHeight = regionOnTexture.getPixelHeight(font.pages().get(pageID));
                    final float glyphWidth = regionOnTexture.getPixelWidth(font.pages().get(pageID));

                    final int offsetX = getIntValue(tokens[6]);
                    final float offsetY = (font.getHeight() - getIntValue(tokens[7]) - glyphHeight);
                    final int advanceX = getIntValue(tokens[8]);

                    final GlyphInfo glyph = font.glyphs()
                            .getOrDefault(code, new GlyphInfo(code))
                                .setAdvanceX(advanceX)
                                .setPageID(pageID)
                                .setRegion(regionOnTexture)
                                .setSize(glyphWidth, glyphHeight)
                                .setOffset(offsetX, offsetY);

                    font.glyphs().put(code, glyph);
                }
                case "kerning" -> {
                    final int code_0 = getIntValue(tokens[1]);
                    final int code_1 = getIntValue(tokens[2]);
                    final int advance = getIntValue(tokens[3]);

                    // get glyph
                    final GlyphInfo glyph = font.glyphs()
                            .getOrDefault(code_0, new GlyphInfo(code_0));
                    
                    // add kerning entry
                    glyph.kernings().put((long) code_1, advance);
                }
            }
        }
        reader.close();
        return font;
    }

    public static Font load(Font font, String internalPath, FontLoadOptions options) {
        return load(font, Resource.internal(internalPath), options);
    }


    public static Font load(Font font, Resource resource, boolean linearFilter) {
        final FontLoadOptions options = new FontLoadOptions().linearFilter(linearFilter);
        return load(font, resource, options);
    }

    public static Font load(Font font, String internalPath, boolean linearFilter) {
        final FontLoadOptions options = new FontLoadOptions().linearFilter(linearFilter);
        return load(font, Resource.internal(internalPath), options);
    }

}
