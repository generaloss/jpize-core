package jpize.util.ctrl;

import jpize.app.Jpize;
import jpize.glfw.callback.GlfwCallbacks;
import jpize.glfw.input.GlfwAction;
import jpize.glfw.input.GlfwMods;
import jpize.glfw.input.Key;
import jpize.glfw.window.GlfwWindow;
import jpize.util.array.StringList;
import jpize.util.font.Charset;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.StringJoiner;

public class TextInput implements Iterable<String> {

    private final StringList lines;
    private final Vec2i position;
    private int multilineX;
    private int tabSpaces;
    private int maxLines;
    private Charset specialCharset;

    public TextInput() {
        this.lines = new StringList("");
        this.position = new Vec2i();
        this.tabSpaces = 4;
        this.maxLines = -1;
        this.specialCharset = Charset.SPECIAL_SYMBOLS.copy();
    }


    public TextInput insert(int x, int y, CharSequence string) {
        if(string.isEmpty())
            return this;

        final String[] lines = string.toString().split("\n");

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

    public TextInput insert(CharSequence string) {
        this.insert(position.x, position.y, string);
        this.advanceX(string.length());
        return this;
    }


    public boolean removeSlice(int x, int y, int length) {
        final String line = this.getLine(y);
        final int lineLength = line.length();
        if(x > lineLength - 1 || x + length > lineLength)
            return false;

        this.setLine(y, line.substring(0, x) + line.substring(x + length));
        return true;
    }

    public boolean remove() {
        if(position.x == 0){
            if(position.y == 0)
                return false;

            final String line = lines.remove(position.y);
            final String part2 = line.substring(position.x);
            position.y--;
            this.setEndX();
            this.setLine(position.y, this.getLine(position.y) + part2);
            return true;
        }

        final boolean removed = this.removeSlice(position.x - 1, position.y, 1);
        if(removed)
            this.advanceX(-1);
        return removed;
    }

    public int remove(int count) {
        if(count < 1)
            return 0;

        while(count > 0){
            if(!this.remove())
                break;
            count--;
        }

        return count;
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


    private void onChar(GlfwWindow window, char codepoint) {
        this.insert(String.valueOf(codepoint));
    }

    private void onKey(GlfwWindow window, Key key, int scancode, GlfwAction action, GlfwMods mods) {
        if(action.isRelease())
            return;

        if(mods.hasCtrl()){
            switch(key){
                case V -> insert(Jpize.input().getClipboardString());
                case LEFT -> {
                    final int length = this.wordLength(true);
                    if(length == 0) advanceX(-1);
                    else advanceX(-length);
                }
                case RIGHT -> {
                    final int length = this.wordLength(false);
                    if(length == 0) advanceX(1);
                    else advanceX(length);
                }
                case HOME -> setHomeY();
                case END -> setEndY();
                case ENTER -> insert(position.x, position.y, "\n");
                case BACKSPACE -> {
                    final int length = this.wordLength(true);
                    if(length == 0) remove();
                    else remove(length);
                }
            }
        }else{
            switch(key){
                case UP -> advanceY(-1);
                case DOWN -> advanceY(1);
                case LEFT -> advanceX(-1);
                case RIGHT -> advanceX(1);
                case HOME -> setHomeX();
                case END -> setEndX();
                case TAB -> insert(" ".repeat(tabSpaces));
                case ENTER -> insert("\n");
                case BACKSPACE -> remove();
            }
        }
    }

    public TextInput enable() {
        final GlfwCallbacks callbacks = GlfwWindow.getCurrentContext().getCallbacks();
        callbacks.addCharCallback(this::onChar);
        callbacks.addKeyCallback(this::onKey);
        return this;
    }

    public TextInput disable() {
        final GlfwCallbacks callbacks = GlfwWindow.getCurrentContext().getCallbacks();
        callbacks.removeCharCallback(this::onChar);
        callbacks.removeKeyCallback(this::onKey);
        return this;
    }

    public TextInput enable(boolean enable) {
        if(enable) this.enable();
        else this.disable();
        return this;
    }


    public int lines() {
        return lines.size();
    }

    public StringList getLines() {
        return lines.copy();
    }

    public String getLine(int y) {
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
        lines.add(y, string.toString());
        return true;
    }

    public boolean addLine(CharSequence string) {
        if(maxLines != -1 && lines.size() >= maxLines)
            return false;
        lines.add(string.toString());
        return true;
    }

    public TextInput setLine(int y, CharSequence line) {
        lines.set(y, line.toString());
        return this;
    }

    public TextInput setLine(CharSequence line) {
        return this.setLine(position.y, line);
    }

    public String removeLine(int y) {
        final String line = lines.remove(y);
        if(position.y >= this.lines())
            setEndY();
        return line;
    }

    @Override
    public @NotNull Iterator<String> iterator() {
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


    public String makeString(boolean invert) {
        final StringJoiner joiner = new StringJoiner("\n");
        if(!invert){
            for(String line: lines)
                joiner.add(line);
        }else{
            for(int i = 0; i < this.lines(); i++)
                joiner.add(this.getLine(this.lines() - 1 - i));
        }
        return joiner.toString();
    }

    public String makeString() {
        return this.makeString(false);
    }


    @Override
    public String toString() {
        return lines.toString();
    }

}