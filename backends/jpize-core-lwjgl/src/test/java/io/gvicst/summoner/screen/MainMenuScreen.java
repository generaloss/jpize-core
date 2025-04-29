package io.gvicst.summoner.screen;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.utils.IScreen;
import jpize.context.Jpize;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.opengl.gl.GL;
import jpize.util.math.vector.Vec4f;
import jpize.util.camera.PerspectiveCamera;
import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.mesh.TextureBatch;

public class MainMenuScreen extends IScreen {

    public static final String SCREEN_ID = "main_menu";

    private final Vec4f backgroundPosition;
    private final PerspectiveCamera camera;
    private final TextureBatch batch;
    private float width, height, ratio, hratio;
    private float fadeIn = 0;
    private float timer = 0;
    private boolean screenChanging;
    private final int language = 0;

    public MainMenuScreen(Main context) {
        super(context);

        this.batch = new TextureBatch();

        this.backgroundPosition = new Vec4f(0,0, Jpize.getWidth(),Jpize.getHeight());
        // camera
        this.camera = new PerspectiveCamera(0.01F, 5F, 85F);
    }

    public void init() {
        GL.clearColor(1,1,1);
        GL.clearColorBuffer();
//        main.params.load();
    }

    @Override
    public void show() {
        GL.clearColor(1F, 1F, 1F);
        fadeIn = 0;
        // main.res.music.get(0).play();
    }

    @Override
    public void hide() {
        // main.res.music.get(0).pause();
    }

    @Override
    public void update() {
        // main.res.music.get(0).update();
        // main.res.music.get(0).setGain(fadeIn/4f);
        camera.update();

        width = Jpize.getWidth();
        height = Jpize.getHeight();
        ratio = (height/960f);
        hratio = Math.min(width/512f/ratio, ratio);

        // if(!screenChanging && (MouseBtn.upAny(MouseBtn.values()) || Key.upAny(Key.values())))
        //     main.res.sound.get(0).play();

        if (MouseBtn.upAny(MouseBtn.values()) || Key.upAny(Key.values()))
            screenChanging = true;

        if (Key.ESCAPE.down())
            Jpize.exit();
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        // font init
        final Font font = super.main.font();
        final FontRenderOptions fontOptions = font.getOptions();
        fontOptions.color().set(0,0,0);
        batch.setup();

        timer += Jpize.getDeltaTime()*3;
        fadeIn += screenChanging?-Jpize.getDeltaTime()/1.5f:(fadeIn < 1)?Jpize.getDeltaTime()/3f:0;
        if (fadeIn < 0) {
            screenChanging = false;
            // main.res.music.get(0).setSecOffset(0);
            main.screens.setCurrent(GameScreen.SCREEN_ID);
        }
        fadeIn = Math.min(fadeIn, 1);
        batch.setAlpha(fadeIn);
        fontOptions.color().setAlpha(fadeIn);
        // background
        drawLogo();
        drawCharacters();
        // hints
        // overlay
        fontOptions.color().set(0.23f, 0.23f, 0.23f, (1.5f+Math.sin(timer))/3f*fadeIn);
        fontOptions.scale().set(0.5f*ratio);
        font.drawText(batch, "Нажмите чтобы Начать", width/2f-(int)(font.getTextWidth("Нажмите чтобы Начать")/2f), height/3.25f-(int)(font.getLineAdvanceScaled()/2f));

        fontOptions.scale().set(0.35f*ratio);
        fontOptions.color().set(0.23f, 0.23f, 0.23f, 0.75f*fadeIn);
        font.drawText(batch, "HalfUP Games", width/2f-font.getTextWidth("HalfUP Games")/2f, height/10f+20F);
        font.drawText(batch, "&                        &", width/2f-font.getTextWidth("&                        &")/2f, height/10f+12.5F);
        font.drawText(batch, "GVicSt", width/2f-font.getTextWidth("GVicSt")/2f, height/10f+5F);

        batch.render();
    }

    public void drawCharacters(){
        for (int i = 0; i < main.res.familiars.size(); i++) {
            int j = new int[]{0,9,2,12,10,6,8,5,11,3,4,1,7}[i];
            batch.draw(main.res.familiars.get(j).getFrame((int)((main.params.GAME_TIME*(40-i))%4)), width/2f-32*8*ratio*hratio+32*i*ratio*hratio, height/2.75f, main.res.familiars.get(j).getFrameWidth()*2f*ratio*hratio, main.res.familiars.get(j).getFrameHeight()*2f*ratio*hratio);
        }
        for (int i = 0; i < main.res.heroes.size(); i++) {
            int j = new int[]{5,4,1,0,2,3,6}[i];
            batch.draw(main.res.heroes.get(j).getFrame((int)((main.params.GAME_TIME*(40-i))%4)), width/2f-64*4*ratio*hratio+64*i*ratio*hratio, height/2.75f, main.res.heroes.get(j).getFrameWidth()*2f*ratio*hratio, main.res.heroes.get(j).getFrameHeight()*2f*ratio*hratio);
        }
    }

    public void drawLogo(){
        float grimoireSize = 2f * Jpize.getHeight() / 960;
        backgroundPosition.set(
                Jpize.getWidth()/2f - main.res.logoMainMenu.getWidth()/2f* grimoireSize /4.5f,
                Jpize.getHeight()/1.48f - main.res.logoMainMenu.getHeight()/2f* grimoireSize /4.5f,
                main.res.logoMainMenu.getWidth()* grimoireSize /4.5f,
                main.res.logoMainMenu.getHeight()* grimoireSize /4.5f);

        batch.drawRGBA(main.res.hudGrimoireCircle,
                Jpize.getWidth()/2f- main.res.hudGrimoireCircle.getWidth()* grimoireSize /2f, Jpize.getHeight()/1.5f- main.res.hudGrimoireCircle.getHeight()* grimoireSize /2f,
                main.res.hudGrimoireCircle.getWidth()* grimoireSize, main.res.hudGrimoireCircle.getHeight()* grimoireSize,
                0xf2cefe00 + (int)(Math.min(fadeIn*255,255)));
        batch.draw(main.res.logoMainMenu, backgroundPosition.x, backgroundPosition.y, backgroundPosition.z, backgroundPosition.w);
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

}
