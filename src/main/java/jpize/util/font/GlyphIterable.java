package jpize.util.font;

public class GlyphIterable implements Iterable<GlyphSprite> {

    private final GlyphIterator iterator;

    public GlyphIterable(GlyphIterator iterator) {
        this.iterator = iterator;
    }

    @Override
    public GlyphIterator iterator() {
        return iterator;
    }

}
