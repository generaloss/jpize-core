import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.util.font.Font;
import jpize.util.mesh.TextureBatch;

import java.util.StringJoiner;

public class InputTest extends JpizeApplication {

    private final TextureBatch batch;
    private final Font font;

    public InputTest() {
        this.batch = new TextureBatch();
        this.font = new Font().loadFNT("/font.fnt", false);
        this.font.getOptions().scale().set(4F);
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        batch.setup();

        if(Key.S.down())
            System.out.println("down");
        if(Key.S.pressed())
            System.out.println("pressed");
        if(Key.S.up())
            System.out.println("up");

        final StringJoiner keys = new StringJoiner(", ");

        // System.out.println(Jpize.input.getKey(Key.A));

        for(Key key: Key.values())
            if(key.pressed())
                keys.add(key.toString());
        font.drawText(batch, "Keys: " + keys, 100, 100);

        final StringJoiner buttons = new StringJoiner(", ");
        for(MouseBtn button: MouseBtn.values())
            if(button.pressed())
                buttons.add(button.toString());
        font.drawText(batch, "Buttons: " + buttons, 100, 200);

        batch.render();
    }


    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 720, "Input Test")
            .icon("/icon2.png")
            .decorated(false)
            .build().setApp(new InputTest());

        GlfwContextManager.run();
    }

}
