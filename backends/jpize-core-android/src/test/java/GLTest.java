import android.os.Bundle;
import jpize.android.context.JpizeAndroidActivity;
import jpize.context.Jpize;
import jpize.context.JpizeApplication;

public class GLTest extends JpizeAndroidActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Jpize.context.setApp(new App());
    }

    static private class App extends JpizeApplication {

        @Override
        public void init() {
            System.out.println("init");
        }

        @Override
        public void update() {
            System.out.println("update");
        }

        @Override
        public void render() {
            System.out.println("render");
        }

        @Override
        public void resize(int width, int height) {
            System.out.println("resize " + width + " " + height);
        }

        @Override
        public void dispose() {
            System.out.println("dispose");
        }

    }

}
