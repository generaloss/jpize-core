import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.io.input.Key;
import jpize.io.input.MouseBtn;
import jpize.lwjgl.app.GlfwContextBuilder;
import jpize.opengl.gl.Gl;
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
        Gl.clearColorBuffer();
        batch.setup();

        final StringJoiner keys = new StringJoiner(", ");
        for(Key key: Key.values())
            if(key.pressed())
                keys.add(key.getName());
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
            .build().setApp(new InputTest());

        Jpize.run();
    }

}
