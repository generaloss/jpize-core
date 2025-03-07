package jpize.util.input;

import jpize.context.Jpize;
import jpize.context.callback.CharCallback;
import jpize.context.callback.KeyCallback;
import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.Mods;
import jpize.util.array.StringList;
import jpize.util.font.Charset;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2i;

import java.util.*;

public class TextInput implements Iterable<String> {

    private boolean enabled, enabledCharInput, enabledKeyInput;
    private final StringList lines;
    private final Vec2i position;
    private int multilineX;
    private int tabSpaces;
    private int maxLines;
    private Charset specialCharset;
    private final CharCallback charCallback;
    private final KeyCallback keyCallback;
    private final List<CursorCallback> cursorCallbacks;
    private final List<RemoveCallback> removeCallbacks;
    private final List<InputCallback> inputCallbacks;

    public TextInput() {
        this.enabledCharInput = true;
        this.enabledKeyInput = true;

        this.lines = new StringList("");
        this.position = new Vec2i();
        this.tabSpaces = 4;
        this.maxLines = -1;
        this.specialCharset = Charset.SPECIAL_SYMBOLS.copy();

        this.charCallback = this::onChar;
        this.keyCallback = this::onKey;

        this.cursorCallbacks = new ArrayList<>();
        this.removeCallbacks = new ArrayList<>();
        this.inputCallbacks = new ArrayList<>();
    }


    public interface CursorCallback {
        void invoke(int deltaX, int deltaY);
    }

    public TextInput addCursorCallback(CursorCallback callback) {
        cursorCallbacks.add(callback);
        return this;
    }

    public TextInput removeCursorCallback(CursorCallback callback) {
        cursorCallbacks.remove(callback);
        return this;
    }

    private void invokeCursorCallbacks(int deltaX, int deltaY) {
        for(CursorCallback callback: cursorCallbacks)
            callback.invoke(deltaX, deltaY);
    }


    public interface RemoveCallback {
        void invoke(String removed);
    }

    public TextInput addRemoveCallback(RemoveCallback callback) {
        removeCallbacks.add(callback);
        return this;
    }

    public TextInput removeRemoveCallback(RemoveCallback callback) {
        removeCallbacks.remove(callback);
        return this;
    }

    private void invokeRemoveCallbacks(String removed) {
        for(RemoveCallback callback: removeCallbacks)
            callback.invoke(removed);
    }


    public interface InputCallback {
        void invoke(String inputText);
    }

    public TextInput addInputCallback(InputCallback callback) {
        inputCallbacks.add(callback);
        return this;
    }

    public TextInput removeInputCallback(InputCallback callback) {
        inputCallbacks.remove(callback);
        return this;
    }

    private void invokeInputCallbacks(String inputText) {
        for(InputCallback callback: inputCallbacks)
            callback.invoke(inputText);
    }


    public TextInput insert(int x, int y, CharSequence string) {
        if(string == null || string.isEmpty())
            return this;

        final String[] lines = string.toString().split("\n");

        if(this.lines.isEmpty())
            insertLine(0, "");

        final String line = this.getLine(y);
        final String part1 = line.substring(0, x);
        final String part2 = line.substring(x);
        final int lastIndex = (lines.length - 1);

        if(lines.length == 0){
            this.setLine(y, part1);
            this.insertLine(y + 1, part2);

        }else if(lines.length == 1){
            this.setLine(y, (part1 + lines[0] + part2));

        }else{
            this.setLine(y, part1 + lines[0]);
            for(int i = 1; i < lastIndex; i++)
                this.insertLine(y + i, lines[i]);

            this.insertLine(y + lastIndex, lines[lastIndex] + part2);
        }
        return this;
    }

    public TextInput insert(CharSequence str) {
        if(str == null)
            return this.insert("");
        this.insert(position.x, position.y, str);
        this.advanceX(str.length());
        return this;
    }


    public String removeSlice(int x, int y, int length) {
        final String line = this.getLine(y);
        final int lineLength = line.length();
        if(x > lineLength - 1 || x + length > lineLength)
            return "";

        final String removed = line.substring(x, x + length);
        this.setLine(y, line.substring(0, x) + line.substring(x + length));
        return removed;
    }

    public String remove() {
        if(position.x == 0){
            if(position.y == 0)
                return "";

            final String line = lines.remove(position.y);
            final String part2 = line.substring(position.x);
            position.y--;
            this.setEndX();
            this.setLine(position.y, this.getLine(position.y) + part2);
            return "\n";
        }

        final String removed = this.removeSlice(position.x - 1, position.y, 1);
        if(!removed.isEmpty())
            this.advanceX(-1);
        return removed;
    }

    public String remove(int count) {
        if(count < 1)
            return "";

        final StringBuilder removedBuilder = new StringBuilder();
        while(count > 0){
            final String removed = this.remove();
            removedBuilder.append(removed);
            if(removed.isEmpty())
                break;
            count--;
        }

        return removedBuilder.toString();
    }

    private boolean isWordEnd(char newc, char oldc) {
        final boolean different = (newc != oldc);
        return
            (different && (specialCharset.contains(newc) || specialCharset.contains(oldc)))
            && (
                (specialCharset.contains(newc) && !specialCharset.contains(oldc))
                || oldc != ' '
            );
    }

    public int wordLength(int x, int y, boolean invert) {
        final String line = this.getLine(y);
        final int length = line.length();
        int count = 0;

        if(invert){
            if(x == 0) return 0;
            else if(x == 1) return 1;

            char oldc = line.charAt(x - 1);
            while(x > 0){
                final char c = line.charAt(x - 1);
                if(this.isWordEnd(c, oldc)) break;
                oldc = c;
                x--;
                count++;
            }
        }else{
            if(x == length) return 0;
            else if(x == length - 1) return 1;

            char oldc = line.charAt(x);
            while(x < length){
                final char c = line.charAt(x);
                if(this.isWordEnd(c, oldc)) break;
                oldc = c;
                x++;
                count++;
            }
        }
        return count;
    }

    public int wordLength(boolean invert) {
        return this.wordLength(position.x, position.y, invert);
    }


    private void onChar(char codepoint) {
        if(!enabledCharInput)
            return;

        final String character = String.valueOf(codepoint);
        this.insert(character);
        this.invokeInputCallbacks(character);
    }

    private void onKey(Key key, int scancode, Action action, Mods mods) {
        if(!enabledKeyInput || action.isRelease())
            return;

        final Vec2i prevPosition = position.copy();

        if(mods.hasCtrl()){
            switch(key){
                case V -> {
                    final String text = Jpize.input.getClipboardString();
                    this.insert(text);
                    this.invokeInputCallbacks(text);
                }
                case LEFT -> {
                    final int length = this.wordLength(true);
                    if(length == 0) this.advanceX(-1);
                    else this.advanceX(-length);
                }
                case RIGHT -> {
                    final int length = this.wordLength(false);
                    if(length == 0) this.advanceX(1);
                    else this.advanceX(length);
                }
                case HOME -> this.setHomeY();
                case END -> this.setEndY();
                case ENTER -> {
                    this.insert(position.x, position.y, "\n");
                    this.invokeInputCallbacks("\n");
                }
                case BACKSPACE -> {
                    final int length = this.wordLength(true);
                    if(length == 0) this.invokeRemoveCallbacks(this.remove());
                    else this.invokeRemoveCallbacks(this.remove(length));
                }
            }
        }else{
            switch(key){
                case UP -> this.advanceY(-1);
                case DOWN -> this.advanceY(1);
                case LEFT -> this.advanceX(-1);
                case RIGHT -> this.advanceX(1);
                case HOME -> this.setHomeX();
                case END -> this.setEndX();
                case TAB -> this.insert(" ".repeat(tabSpaces));
                case ENTER -> {
                    this.insert("\n");
                    this.invokeInputCallbacks("\n");
                }
                case BACKSPACE -> this.invokeRemoveCallbacks(this.remove());
            }
        }

        final int deltaX = position.x - prevPosition.x;
        final int deltaY = position.y - prevPosition.y;
        if(deltaX != 0 || deltaY != 0)
            this.invokeCursorCallbacks(deltaX, deltaY);
    }


    public boolean isEnabled() {
        return enabled;
    }

    public TextInput enable() {
        if(enabled)
            return this;
        enabled = true;
        Jpize.callbacks.addChar(charCallback);
        Jpize.callbacks.addKey(keyCallback);
        return this;
    }

    public TextInput disable() {
        if(!enabled)
            return this;
        enabled = false;
        Jpize.callbacks.removeChar(charCallback);
        Jpize.callbacks.removeKey(keyCallback);
        return this;
    }

    public TextInput enable(boolean enable) {
        if(enable) this.enable();
        else this.disable();
        return this;
    }


    public boolean isEnabledCharInput() {
        return enabledCharInput;
    }

    public TextInput setCharInputEnabled(boolean enabled) {
        this.enabledCharInput = enabled;
        return this;
    }

    public boolean isEnableKeyInput() {
        return enabledKeyInput;
    }

    public TextInput setKeyInputEnabled(boolean enabled) {
        this.enabledKeyInput = enabled;
        return this;
    }


    public int lines() {
        return lines.size();
    }

    public StringList getLines() {
        return lines.copy();
    }

    public String getLine(int y) {
        if(y >= lines.size())
            return "";
        return lines.get(y);
    }

    public int lineLength() {
        return getLine().length();
    }

    public String getLine() {
        return getLine(position.y);
    }

    public boolean insertLine(int y, CharSequence string) {
        if(maxLines != -1 && y >= maxLines)
            return false;
        lines.add(y, string.toString()).trim();
        return true;
    }

    public boolean addLine(CharSequence string) {
        if(maxLines != -1 && lines.size() >= maxLines)
            return false;
        lines.add(string.toString()).trim();
        return true;
    }

    public TextInput setLine(int y, CharSequence line) {
        lines.set(y, line.toString());
        return this;
    }

    public TextInput setLine(CharSequence line) {
        this.setLine(position.y, line);
        if(position.x > line.length())
            position.x = line.length();
        return this;
    }

    public String removeLine(int y) {
        if(lines.isEmpty())
            return "";

        final String line = lines.remove(y);
        if(position.y >= this.lines())
            this.setEndY();

        if(position.x >= this.lineLength())
            this.setEndX();
        return line;
    }

    @Override
    public Iterator<String> iterator() {
        return this.lines.iterator();
    }


    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public int setX(int x) {
        position.x = Maths.clamp(x, 0, this.lineLength());
        return (x - position.x);
    }

    public int setY(int y) {
        position.y = Maths.clamp(y, 0, this.lines() - 1);
        return (y - position.y);
    }

    public boolean setPos(int x, int y) {
        return (this.setY(y) == 0) && (this.setX(x) == 0);
    }

    public boolean setPos(Vec2i pos) {
        return this.setPos(pos.x, pos.y);
    }

    public TextInput setEndX() {
        this.setX(this.lineLength());
        this.setMultilineX(position.x);
        return this;
    }

    public TextInput setEndY() {
        this.setY(this.lines() - 1);
        this.setEndX();
        return this;
    }

    public TextInput setHomeX() {
        this.setX(0);
        this.setMultilineX(0);
        return this;
    }

    public TextInput setHomeY() {
        this.setY(0);
        this.setX(0);
        return this;
    }

    public int advanceX(int advance) {
        int remainder = this.setX(position.x + advance);

        // advanceY
        while(remainder > 0){
            if(this.advanceY(1) == 1) // at end
                break;
            remainder = this.setX(remainder - 1);
            this.setMultilineX(position.x);
        }
        while(remainder < 0){
            if(this.advanceY(-1) == -1) // at start
                break;
            remainder = this.setX(this.lineLength() + remainder + 1);
        }

        // update multilineX
        if(advance < 0 || (advance > 0 && position.x > multilineX))
            this.setMultilineX(position.x);

        return remainder;
    }

    public int advanceY(int advance) {
        final int remainder = this.setY(position.y + advance);
        final int lineLength = this.lineLength();

        // multilineX
        if(position.x > lineLength && position.x > multilineX){ // update multilineX
            this.setMultilineX(position.x);
        }else if(position.x < lineLength){ // to multilineX
            position.x = Math.min(lineLength, multilineX);
        }

        // clamp x
        if(position.x > lineLength)
            this.setX(lineLength);

        return remainder;
    }


    public int getMultilineX() {
        return multilineX;
    }

    public TextInput setMultilineX(int x) {
        this.multilineX = x;
        return this;
    }


    public int getTabSpaces() {
        return tabSpaces;
    }

    public TextInput setTabSpaces(int spaces) {
        this.tabSpaces = spaces;
        return this;
    }


    public int getMaxLines() {
        return maxLines;
    }

    public TextInput setMaxLines(int maxLines) {
        this.maxLines = maxLines;
        return this;
    }


    public Charset getSpecialCharset() {
        return specialCharset;
    }

    public TextInput setSpecialCharset(Charset charset) {
        this.specialCharset = charset;
        return this;
    }


    public static String makeString(String[] lines, boolean invert) {
        final StringJoiner joiner = new StringJoiner("\n");
        if(!invert){
            for(String line: lines)
                joiner.add(line);
        }else{
            final int linesNum = lines.length;
            for(int i = 0; i < linesNum; i++)
                joiner.add(lines[linesNum - 1 - i]);
        }
        return joiner.toString();
    }

    public String makeString(boolean invert) {
        return makeString(this.lines.array(), invert);
    }

    public String makeString() {
        return this.makeString(false);
    }

    public static class Selection implements Iterable<String>{

        public final boolean invert, oneline;
        public final Vec2i start, end;
        public final String[] lines;
        public final int length;

        public Selection(TextInput input, int x1, int y1, int x2, int y2) {
            final int maxY = (input.lines() - 1);
            y1 = Maths.clamp(y1, 0, maxY);
            y2 = Maths.clamp(y2, 0, maxY);
            x1 = Maths.clamp(x1, 0, input.getLine(y1).length());
            x2 = Maths.clamp(x2, 0, input.getLine(y2).length());

            this.oneline = (y1 == y2);
            this.invert = (y1 >= y2) && !oneline;

            final int startY = Math.min(y2, y1);
            final int endY = Math.max(y2, y1);

            final int startX = (oneline ? Math.min(x1, x2) : (invert ? x2 : x1));
            final int endX =   (oneline ? Math.max(x1, x2) : (invert ? x1 : x2));

            this.start = new Vec2i(startX, startY);
            this.end = new Vec2i(endX, endY);

            if(oneline){
                if(x1 == x2){
                    this.lines = new String[0];
                    this.length = 0;
                    return;
                }

                final String line = input.getLine(startY).substring(startX, endX);
                this.lines = new StringList(line).array();
                this.length = line.length();
                return;
            }

            final StringList slice = new StringList();
            slice.add(input.getLine(startY).substring(startX));

            for(int y = startY + 1; y < endY; y++)
                slice.add(input.getLine(y));

            slice.add(input.getLine(endY).substring(0, endX));
            this.lines = slice.trim().array();

            int length = 0;
            for(String line: lines)
                length += line.length();
            this.length = length;
        }

        public int size() {
            return lines.length;
        }

        public String line(int index) {
            return lines[index];
        }

        public boolean isEmpty() {
            return lines.length == 0;
        }

        @Override
        public String toString() {
            final StringJoiner joiner = new StringJoiner("\n");
            for(String line: lines)
                joiner.add(line);
            return joiner.toString();
        }

        @Override
        public Iterator<String> iterator() {
            return Arrays.stream(lines).iterator();
        }

    }

    public Selection selection(int x1, int y1, int x2, int y2) {
        return new Selection(this, x1, y1, x2, y2);
    }

    public Selection selection(Vec2i start, Vec2i end) {
        return this.selection(start.x, start.y, end.x, end.y);
    }


    @Override
    public String toString() {
        return lines.toString();
    }

}