package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.glfw.input.Key;
import jpize.glfw.input.MouseBtn;
import jpize.gl.texture.TextureBatch;
import jpize.util.ctrl.TextInput;
import jpize.util.font.Font;
import jpize.util.font.FontLoader;
import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2i;

import java.util.StringJoiner;

public class TextEditorTest extends JpizeApplication {

    private final TextInput input = new TextInput().enable().insert(Jpize.input().getClipboardString());
    private final Font font = FontLoader.loadDefault();
    private final TextureBatch batch = new TextureBatch();
    private float editorScale = 1;
    private float lineHeight;
    private float scroll, animatedScroll, scrollY;
    private float numerationWidth;
    private final Vec2i selectionStart = new Vec2i();
    private final Vec2i selectionEnd = new Vec2i();
    private TextInput.Selection selection = new TextInput.Selection(input, 0, 0, 0, 0);

    public void init() {
        font.options().invLineWrap = true;
        Gl.clearColor(0.1, 0.11, 0.12);
        // System.out.println(font.getTextAdvance("i ") + " | " + (font.getTextAdvance("i") + font.getTextAdvance(" ")));

        // remove selected
        Jpize.callbacks().addKeyCallback((window, key, scancode, action, mods) -> {
            if(key == Key.BACKSPACE && !selection.isEmpty() && action.isDown()){
                input.setPos(selection.end);
                input.remove(selection.length + selection.lines.length - 1);
                selection = new TextInput.Selection(input, 0, 0, 0, 0);
                input.enable();
            }
        });
    }

    public void update() {
        // scroll & scaling
        if(Jpize.getScroll() != 0){
            if(Key.LCTRL.pressed()){
                final float scaleFactor = 1.2F;
                editorScale *= Mathc.pow(Jpize.getScroll() > 0 ? scaleFactor : 1 / scaleFactor, Math.abs(Jpize.getScroll()));
            }else{
                final float scrollFactor = 1.5F / editorScale;
                scroll -= Jpize.getScroll() * scrollFactor;
            }
        }

        // cinematic
        font.options().scale += (editorScale - font.options().scale) / 10;
        lineHeight = font.options().getLineHeightScaled();
        numerationWidth = 200 * font.options().scale;
        animatedScroll += (scroll - animatedScroll) / 10;
        scrollY = animatedScroll * lineHeight - Jpize.getHeight() * 0.5F;

        // cursor point & selection
        if(MouseBtn.LEFT.pressed()){
            final float touchX = Jpize.getX() - numerationWidth;
            final float touchY = Jpize.input().getCursorNativeY() + scrollY;
            final int cursorY = Maths.floor(touchY / lineHeight);
            input.setY(cursorY);
            final String line = input.getLine();
            final int cursorX = cursorXbyTouch(line, touchX);
            input.setX(cursorX);
            selectionEnd.set(input.getX(), input.getY());
        }
        if(MouseBtn.LEFT.down()){
            selectionStart.set(input.getX(), input.getY());
        }
        if(MouseBtn.LEFT.pressed() || MouseBtn.LEFT.up()) {
            selection = input.selection(selectionStart, selectionEnd);

            if(!selection.isEmpty())
                input.disable();
            else if(MouseBtn.LEFT.up()){
                if(selection.isEmpty())
                    input.enable();
            }
        }

    }

    private int cursorXbyTouch(String line, float touchX) {
        if(line.isEmpty())
            return 0;

        for(int i = 0; i <= line.length(); i++)
            if(font.getTextWidth(line.substring(0, i)) > touchX)
                return (i - 1);

        return line.length();
    }

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

            StringJoiner numeration = new StringJoiner("\n");
            for(int i = 0; i < input.lines(); i++)
                numeration.add(String.valueOf(i + 1));

            batch.drawRect(0.3F, 0.32F, 0.35F, 1F,  numerationWidth - 2, numerationY, 2, numerationHeight);
            font.options().color.set(0.3, 0.32, 0.35);
            font.drawText(batch, numeration.toString(), 0, Jpize.getHeight() + scrollY);

            // render selection
            if(!selection.isEmpty()) {
                final float firstLineOffsetX = font.getTextAdvance(
                    input.getLine(selection.start.y)
                        .substring(0, selection.start.x)
                );

                float lineX = numerationWidth + firstLineOffsetX;
                float lineY = textY - lineHeight * (selection.start.y + 1);
                float lineWidth = font.getTextWidth(selection.line(0));
                batch.drawRect(0.05F, 0.35F, 0.75F, 1F,  lineX, lineY, lineWidth, lineHeight);

                for(int i = 1; i < selection.size(); i++){
                    lineX = numerationWidth;
                    lineY = textY - lineHeight * (selection.start.y + i + 1);
                    lineWidth = font.getTextWidth(selection.line(i));
                    batch.drawRect(0.05F, 0.35F, 0.75F, 1F,  lineX, lineY, lineWidth, lineHeight);
                }
            }

            // render text
            font.options().color.set(0.95, 0.95, 0.93);
            font.drawText(batch, text, numerationWidth, textY);

            // render cursor
            final float x = font.getTextWidth(input.getLine(input.getY()).substring(0, input.getX())) + numerationWidth;
            final float y = Jpize.getHeight() - (input.getY() + 1) * lineHeight + scrollY;
            batch.drawRect(1F, 1F, 1F, 1F, x, y, 3, lineHeight);
        }
        batch.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux")) Glfw.glfwInitHintPlatform(GlfwPlatform.X11);
        Jpize.create(1280, 1280, "Editor").build().setApp(new TextEditorTest());
        Jpize.run();
    }

}
