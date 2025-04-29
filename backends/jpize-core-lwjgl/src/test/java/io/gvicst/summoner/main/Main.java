package io.gvicst.summoner.main;

import io.gvicst.summoner.config.GameParameters;
import io.gvicst.summoner.utils.Resources;
import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.lwjgl.context.ContextManager;
import jpize.lwjgl.context.GlfwContextBuilder;
import jpize.opengl.gl.GL;
import jpize.opengl.texture.Texture2D;
import jpize.util.font.Charset;
import jpize.util.font.Font;
import jpize.util.font.FontLoadOptions;
import jpize.util.mesh.TextureBatch;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.screen.ScreenManager;
import io.gvicst.summoner.screen.GameScreen;
import io.gvicst.summoner.screen.MainMenuScreen;
import jpize.util.time.Sync;

public class Main extends JpizeApplication {

    public TextureBatch batch;
    public Font font;
    public Resources res;
    public ScreenManager<String> screens;
    public GameParameters params;

    private Sync sync;
    private Texture2D circleTexture;

    public static void main(String[] args) {
        GlfwContextBuilder.create(720, 1280, "Summoner")
            .build().setApp(new Main());
        ContextManager.run();
    }

    @Override
    public void init() {
        this.screens = new ScreenManager<>();
        this.batch = new TextureBatch();
        this.params = new GameParameters();
        this.font = new Font().loadTTF(
            "/assets/fonts/main.ttf",
            new FontLoadOptions().size(48).charset(Charset.DEFAULT_ENG_RUS)
        );
        // config
        params.init();
        // load resources
        res = new Resources();
        res.load();
        // register screens
        screens.register(MainMenuScreen.SCREEN_ID, new MainMenuScreen(this));
        screens.register(GameScreen.SCREEN_ID, new GameScreen(this));
        screens.setCurrent(MainMenuScreen.SCREEN_ID);

        this.sync = new Sync(0);

        final PixmapRGBA pixmap = new PixmapRGBA(64, 64);
        pixmap.fillCircleRGB(31, 31, 32, 0xFFFFFF);
        this.circleTexture = new Texture2D(pixmap);
        pixmap.dispose();

        Jpize.callbacks.addCursorPos((cursorIndex, x, y) ->
            System.out.println("cursorPos: " + cursorIndex + ", x: " + x + ", y: " + y)
        );
        Jpize.callbacks.addMouseButton(((mouseIndex, button, action, mods) ->
            System.out.println("mouseButton: " + mouseIndex + ", " + button + ", " + action + ", " + mods))
        );
    }

    @Override
    public void update() {
        sync.sync();

        params.GAME_TIME += Jpize.getDeltaTime()/6f;
        if (params.GAME_TIME >= 360)
            params.GAME_TIME = 0;

        // screens
        screens.update();
        if(Key.F11.up())
            Jpize.window.setFullscreen(!Jpize.window.isFullscreen());
        // font
        font.getOptions().scale().set(Jpize.getHeight() / font.getHeight() * 0.03F);
    }

    @Override
    public void render() {
        GL.clearColorDepthBuffers();
        batch.setup();
        screens.getCurrent().render();

        for(int i = 0; i < Jpize.input.getMouseButtonPressedCount(MouseBtn.LEFT); i++) {
            final float x = Jpize.getX(i);
            final float y = Jpize.getY(i);
            final Action action = MouseBtn.LEFT.action();
            batch.drawRGB(circleTexture, x - 32, y - 32, 64, 64, action.isDown() ? 0xFF0000 : action.isUp() ? 0x0000FF : 0x00FF00);
        }

        // debug
        // font.getOptions().scale().set(0.5f*Jpize.getHeight()/960f);
        // font.getOptions().color().set(1,0,1);
        // font.drawText(Jpize.getFPS()+" | "+(int)Math.floor((params.GAME_TIME/15f)%24)+":"+(int)Math.floor(params.GAME_TIME*4%60), Jpize.getWidth()/2f-font.getTextWidth(Jpize.getFPS()+" | "+(int)Math.floor((params.GAME_TIME/15f)%24)+":"+(int)Math.floor(params.GAME_TIME*10%60))/2f, Jpize.getHeight() - 25F*Jpize.getHeight()/960f);

        batch.render();
    }

    public Font font(){
        return this.font;
    }

    @Override
    public void resize(int width, int height) {
        screens.resize(width, height);
    }

    @Override
    public void dispose() {
        this.params.save();
        screens.dispose();
    }

}
