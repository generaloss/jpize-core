package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.cursor.GlfwCursor;
import jpize.glfw.cursor.GlfwCursorShape;
import jpize.glfw.init.GlfwPlatform;
import jpize.glfw.input.Key;
import jpize.glfw.input.MouseBtn;
import jpize.util.font.FontRenderOptions;
import jpize.util.math.vector.Vec2f;
import jpize.util.mesh.TextureBatch;
import jpize.util.input.TextInput;
import jpize.util.font.Font;
import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2i;

import java.util.StringJoiner;

public class TextEditorTest extends JpizeApplication {

    private final TextInput input;
    private final Font font;
    private final FontRenderOptions renderOptions;
    private final TextureBatch batch;
    private final GlfwCursor cursorArrow, cursorIbeam;

    private final Vec2f editorScale;
    private float lineHeight;
    private float scroll, animatedScroll, scrollY;
    private float numerationWidth;
    private final Vec2i selectionStart;
    private final Vec2i selectionEnd;
    private TextInput.Selection selection;

    public TextEditorTest() {
        this.input = new TextInput().enable().insert(Jpize.input().getClipboardString());
        this.font = new Font().loadDefault();
        this.renderOptions = font.getRenderOptions().setInvLineWrap(true);
        this.batch = new TextureBatch();

        this.cursorArrow = new GlfwCursor(GlfwCursorShape.ARROW);
        this.cursorIbeam = new GlfwCursor(GlfwCursorShape.IBEAM);

        this.editorScale = new Vec2f(1F);
        this.selectionStart = new Vec2i();
        this.selectionEnd = new Vec2i();
        this.selection = new TextInput.Selection(input, 0, 0, 0, 0);
    }

    @Override
    public void init() {
        Gl.clearColor(0.1F, 0.11F, 0.12F);

        Jpize.callbacks().addKeyCallback((window, key, scancode, action, mods) -> {
            if(key == Key.Y && mods.hasCtrl() && action.isPressed()){
                input.removeLine(input.getY());
            }
        });

        input.addCursorCallback((deltaX, deltaY) -> {
            System.out.println(deltaX + " " + deltaY);
            if(Key.LCTRL.pressed() && Key.LSHIFT.pressed()){
                if(Key.LEFT.pressed() || Key.RIGHT.pressed() || Key.UP.pressed() || Key.DOWN.pressed()){
                    if(selection.isEmpty())
                        selectionStart.set(input.getX() - deltaX, input.getY() - deltaY);
                    selectionEnd.set(input.getX(), input.getY());
                    selection = new TextInput.Selection(input, selectionStart.x, selectionStart.y, selectionEnd.x, selectionEnd.y);
                }
            }else if(!selection.isEmpty()){
                if(Key.LEFT.pressed()){
                    input.setPos(selection.start);
                    selection = new TextInput.Selection(input, 0, 0, 0, 0);
                }else if(Key.RIGHT.pressed()) {
                    input.setPos(selection.end);
                    selection = new TextInput.Selection(input, 0, 0, 0, 0);
                }else if(Key.UP.pressed()){
                    input.setPos(selection.start);
                    input.advanceY(-1);
                    selection = new TextInput.Selection(input, 0, 0, 0, 0);
                }else if(Key.DOWN.pressed()) {
                    input.setPos(selection.end);
                    input.advanceY(1);
                    selection = new TextInput.Selection(input, 0, 0, 0, 0);
                }
            }
        });

        input.addRemoveCallback((removed) -> {
            if(!selection.isEmpty()){
                input.insert(removed);
                input.setPos(selection.end);
                input.remove(selection.length + selection.lines.length - 1);
                selection = new TextInput.Selection(input, 0, 0, 0, 0);
            }
        });

        input.addInputCallback((inputText) -> {
            if(!selection.isEmpty()){
                input.remove(inputText.length());
                input.setPos(selection.end);
                input.remove(selection.length + selection.lines.length - 1);
                input.insert(inputText);
                selection = new TextInput.Selection(input, 0, 0, 0, 0);
            }
        });
    }

    @Override
    public void update() {
        // copying
        if(!selection.isEmpty() && Key.LCTRL.pressed()){
            if(Key.C.down()){
                Jpize.input().setClipboardString(selection.toString());

            }else if(Key.X.down()){
                Jpize.input().setClipboardString(selection.toString());
                input.setPos(selection.end);
                input.remove(selection.length + selection.lines.length - 1);
                selection = new TextInput.Selection(input, 0, 0, 0, 0);
            }
        }

        // scroll & scaling
        if(Jpize.getScroll() != 0){
            if(Key.LCTRL.pressed()){
                final float scaleFactor = 1.2F;
                editorScale.mul(Mathc.pow(Jpize.getScroll() > 0 ? scaleFactor : 1F / scaleFactor, Math.abs(Jpize.getScroll())));
            }else{
                final float scrollFactor = 1.5F / editorScale.y;
                scroll -= Jpize.getScroll() * scrollFactor;
            }
        }

        // cinematic
        renderOptions.scale().add(editorScale.copy().sub(font.getRenderOptions().scale()).div(10));
        lineHeight = font.getHeightScaled();
        numerationWidth = 200F * renderOptions.scale().x;
        animatedScroll += (scroll - animatedScroll) / 10F;
        scrollY = animatedScroll * lineHeight - Jpize.getHeight() * 0.5F;

        // cursor point & selection
        if(MouseBtn.LEFT.pressed()){
            final float touchX = Jpize.getX() - numerationWidth;
            final float touchY = Jpize.input().getCursorNativeY() + scrollY;
            final int cursorY = Maths.floor(touchY / lineHeight);
            input.setY(cursorY);
            final String line = input.getLine();
            final int cursorX = cursorXfromTouchX(line, touchX);
            input.setX(cursorX);
            selectionEnd.set(input.getX(), input.getY());
        }
        if(MouseBtn.LEFT.down()){
            selectionStart.set(input.getX(), input.getY());
        }
        if(MouseBtn.LEFT.pressed() || MouseBtn.LEFT.up()) {
            selection = input.selection(selectionStart, selectionEnd);
            if(Jpize.getY() < 0)
                scroll += (0.3F / editorScale.y);
            if(Jpize.getY() > Jpize.getHeight())
                scroll -= (0.3F / editorScale.y);
        }

        // selection hotkeys
        if(Key.LCTRL.pressed() && Key.A.down()){
            input.setEndY();
            input.setEndX();
            selection = input.selection(0, 0, input.getX(), input.getY());
        }

        // cursor
        final boolean isCursor2 = (Jpize.getX() >= numerationWidth);
        Jpize.input().setCursor(isCursor2 ? cursorIbeam : cursorArrow);
    }

    private int cursorXfromTouchX(String line, float touchX) {
        if(line.isEmpty())
            return 0;

        for(int i = 0; i <= line.length(); i++){
            final char correctionChar = line.charAt(Math.min(i, line.length() - 1));
            final float halfCharWidth = font.getTextWidth(String.valueOf(correctionChar)) * 0.5F;

            if(font.getTextWidth(line.substring(0, i)) > touchX + halfCharWidth)
                return (i - 1);
        }

        return line.length();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup();
        {
            // external vars
            final String text = input.makeString();
            final float textY = (Jpize.getHeight() + scrollY);

            // render line numeration
            final float numerationHeight = input.lines() * lineHeight;
            final float numerationY = (Jpize.getHeight() - numerationHeight + scrollY);

            final StringJoiner numeration = new StringJoiner("\n");
            for(int i = 0; i < input.lines(); i++)
                numeration.add(String.valueOf(i + 1));

            batch.drawRect(numerationWidth - 2F, numerationY, 2F, numerationHeight,  0.3F, 0.32F, 0.35F);
            renderOptions.color().set(0.3, 0.32, 0.35);
            font.drawText(batch, numeration.toString(), 0F, Jpize.getHeight() + scrollY);

            // render selection
            if(!selection.isEmpty()) {
                final float firstLineOffsetX = font.getTextWidthWithAdvance(
                    input.getLine(selection.start.y)
                        .substring(0, selection.start.x)
                );

                float lineX = numerationWidth + firstLineOffsetX;
                float lineY = textY - lineHeight * (selection.start.y + 1F);
                float lineWidth = font.getTextWidth(selection.line(0));
                batch.drawRect(lineX, lineY, lineWidth, lineHeight,  0.05F, 0.35F, 0.75F);

                for(int i = 1; i < selection.size(); i++){
                    lineX = numerationWidth;
                    lineY = textY - lineHeight * (selection.start.y + i + 1F);
                    lineWidth = font.getTextWidth(selection.line(i));
                    batch.drawRect(lineX, lineY, lineWidth, lineHeight,  0.05F, 0.35F, 0.75F);
                }
            }

            // render text
            renderOptions.color().set(0.95, 0.95, 0.93);
            renderOptions.enableCullLines(0F, Jpize.getHeight());
            font.drawText(batch, text, numerationWidth, textY);

            // render cursor
            final float x = font.getTextWidth(input.getLine(input.getY()).substring(0, input.getX())) + numerationWidth;
            final float y = Jpize.getHeight() - (input.getY() + 1) * lineHeight + scrollY;
            batch.drawRect(x, y, 3F, lineHeight,  1F, 1F, 1F);
        }
        batch.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        cursorArrow.dispose();
        cursorIbeam.dispose();
    }


    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux")) Glfw.glfwInitHintPlatform(GlfwPlatform.X11);
        Jpize.create(1280, 1280, "Editor").build().setApp(new TextEditorTest());
        Jpize.run();
    }

}
