package jpize.util.font;

import generaloss.freetype.UnicodeVariationSelector;
import jpize.util.array.CharList;

import java.util.function.BiConsumer;

public class Charset extends CharList {

    public static final Charset SPECIAL_SYMBOLS = new Charset("~`!@#$%^&*()-_+={}[]|\\/:;\"'<>,.?—«» ");
    public static final Charset NUMBERS = new Charset("0123456789");
    public static final Charset ENG = new Charset("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    public static final Charset RUS = new Charset("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя");
    public static final Charset DEFAULT = new Charset(SPECIAL_SYMBOLS.charsString() + NUMBERS.charsString() + ENG.charsString());
    public static final Charset DEFAULT_ENG_RUS = new Charset(SPECIAL_SYMBOLS.charsString() + NUMBERS.charsString() + ENG.charsString() + RUS.charsString());

    private int min, max;

    public Charset(Charset charset) {
        super(charset);
        this.updateMinMax();
    }

    public Charset(String charset) {
        super(charset);
        this.updateMinMax();
    }

    public void updateMinMax() {
        this.max = 0;
        this.min = Integer.MAX_VALUE;

        for(char c: this){
            this.max = Math.max(max, c);
            this.min = Math.min(min, c);
        }
    }

    public int minChar() {
        return min;
    }

    public int maxChar() {
        return max;
    }

    public void forEach(BiConsumer<Integer, Integer> action) {
        final int size = super.size();

        for(int i = 0; i < size; i++) {
            final int charcode = super.get(i);

            final int nextIndex = (i + 1);
            final boolean hasNext = (nextIndex < size);
            final char nextcode = (hasNext ? super.get(nextIndex) : 0);

            final boolean hasVariation = (UnicodeVariationSelector.byCode(nextcode) != null);
            if(hasVariation)
                i++;

            final int variationSelector = (hasVariation ? nextcode : 0);
            action.accept(charcode, variationSelector);
        }
    }


    @Override
    public Charset copy() {
        return new Charset(this);
    }

}
