package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.TextureBatch;
import jpize.util.ctrl.TextInput;
import jpize.util.font.Font;
import jpize.util.font.FontLoader;

public class DevelopTextInput extends JpizeApplication {

    public static void main(String[] args) {
        Glfw.glfwInitHintPlatform(GlfwPlatform.X11);
        Jpize.create(720, 480, "Window")
            .build().setApp(new DevelopTextInput());
        Jpize.run();
    }

    private final TextInput input;
    private final TextureBatch batch;
    private final Font font;

    public DevelopTextInput() {
        this.input = new TextInput().enable();
        this.batch = new TextureBatch();
        this.font = FontLoader.loadDefault();
        font.options.invLineWrap = true;
    }

    public void render() {
        Gl.clearColorBuffer();
        batch.begin();
        font.drawText(batch, input.makeString(), 0, Jpize.getHeight());
        final String text = "";
        final float height = font.options.getLineHeightScaled();
        final String line = input.getLine(input.getY());
        final int xx = Math.min(input.getX(), line.length());
        if(input.getX() > xx)
            System.out.println("warn cursorX > lineLength (" + input.getX() + " > " + line.length() + ")");

        final float x = font.getTextWidth(line.substring(0, xx));
        final float y = Jpize.getHeight() - ((input.getY() + 1) * height);
        batch.drawRect(1F, 1F, 1F, 1F, x, y, 5, height);

        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
