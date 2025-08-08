package io.gvicst.summoner.screen;

import io.gvicst.summoner.entity.EnemySpot;
import io.gvicst.summoner.entity.Summoner;
import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.ui.InfoHolder;
import io.gvicst.summoner.ui.Restart;
import io.gvicst.summoner.ui.SpellHolder;
import io.gvicst.summoner.ui.UpgradePanel;
import io.gvicst.summoner.utils.IScreen;
import io.gvicst.summoner.utils.Texter;
import jpize.context.input.MouseBtn;
import jpize.util.camera.OrthographicCamera;
import spatialmath.Maths;
import spatialmath.geometry.Intersector;
import jpize.context.Jpize;
import jpize.opengl.gl.GL;
import jpize.context.input.Key;
import jpize.util.mesh.TextureBatch;


public class GameScreen extends IScreen{

    public static final String SCREEN_ID = "game";

    private final OrthographicCamera camera;
    private final TextureBatch batch;
    private final Texter texter;

    private final InfoHolder info_holder;
    private final UpgradePanel upgrade_panel;
    private final SpellHolder spell_holder;
    private final Restart restart_screen;

    private final Summoner summoner;
    private final EnemySpot enemy_spot;

    public float width = 0;
    public float height = 0;
    public float ratio = 0;
    public float timer = 0;


    public GameScreen(Main main) {
        super(main);

        this.camera = new OrthographicCamera();
        this.batch = new TextureBatch();
        this.batch.setRoundVertices(false);
        this.texter = new Texter(main.font(), batch);

        this.info_holder = new InfoHolder();
        this.upgrade_panel = new UpgradePanel();
        this.spell_holder = new SpellHolder();
        this.restart_screen = new Restart();

        this.summoner = new Summoner(this);
        this.enemy_spot = new EnemySpot(main);
    }

    public void init() {
        GL.clearColor(1,1,1);
        GL.clearColorBuffer();
//        super.main.params.load();
    }

    @Override
    public void show() {
//        main.res.music.get(1).play();
        GL.clearColor(0F, 0F, 0F);
    }

    @Override
    public void hide() {
//        main.res.music.get(1).pause();
    }

    @Override
    public void update() {

//        main.res.music.get(1).update();
//        main.res.music.get(1).setGain((1-restart_screen.start_fade_value)/4f);

        timer += Jpize.getDeltaTime()*6f;
        if (timer >= 100000)
            timer = 10;

        if(upgrade_panel.getOpenRatio()>0.95f) {
            summoner.update(this);
            enemy_spot.update(this);
        }
        camera.update();

        width = Jpize.getWidth();
        height = Jpize.getHeight();
        ratio = (height/960f);


        boolean isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(),Jpize.input.getCursorY(),
                width/2f+25, height/2f-(height/7f)-37.5f*ratio, 100*ratio,75*ratio);
        if(summoner.getState()==4 && summoner.getFamiliarSpot().getFamiliars().isEmpty() && (Key.Q.up() || (isOnRect && MouseBtn.upAny(MouseBtn.values())))) {
            main.params.save();
            Jpize.exit();
        }

        isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(),Jpize.input.getCursorY(),
                width/2f-125*ratio, height/2f-(height/7f)-37.5f*ratio, 100*ratio,75*ratio);
        if(summoner.getState()==4 && summoner.getFamiliarSpot().getFamiliars().isEmpty() && (Key.E.up() || (isOnRect && MouseBtn.upAny(MouseBtn.values())))) {
            restart_screen.restart_fade = true;
        }

        isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(),Jpize.input.getCursorY(),
                width-125*ratio, height-150*ratio, 100*ratio,75*ratio);
        if(summoner.getState()!=4 && main.params.SUBLEVEL==9 && main.params.GRIND_MODE && (Key.E.down() || (isOnRect && MouseBtn.downAny(MouseBtn.values())))){
            main.params.SUBLEVEL++;
            enemy_spot.removeEnemy();
            main.params.GRIND_MODE = false;
        }

        main.params.gold.update();

        if(Key.ESCAPE.up()) {
            super.main.screens.setCurrent(MainMenuScreen.SCREEN_ID);
            restart_screen.restart_fade_value = 0;
            restart_screen.restart_fade = false;
            restart_screen.start_fade = true;
            restart_screen.start_fade_value = 1f;
        }
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        batch.setup();

        drawBackground();
        drawEntities();
        drawHUD();

//        debug(main);

        batch.render();
    }

    public void drawEntities(){
        // summoner
        float groundColor = Math.max((Maths.sinDeg(main.params.GAME_TIME+180)+1)/2,0.25f*(1+2*summoner.getGrimoire().grimoireInertia*(0.75f+summoner.getGrimoire().grimoireColor.getGreen()/2f)));
        batch.color().set(groundColor,groundColor,1f,1f);
        summoner.draw(batch);
        // enemy
        groundColor = Math.max((Maths.sinDeg(main.params.GAME_TIME+180)+1)/2,0.25f);
        batch.color().set(groundColor,groundColor,1f,1f);
        enemy_spot.draw(batch, this);

        batch.color().set(1f,1f,1f,1f);
    }

    public void drawBackground(){
        float groundColor = Math.max((Maths.sinDeg(main.params.GAME_TIME+180)+1)/2,0.25f);
        batch.color().set(groundColor,groundColor,1f,1f);
        // sky
        batch.draw(main.res.bgSky, 0, height/2f+main.res.bgGround.getHeight()*ratio/1.5f, width, main.res.bgSky.getHeight()*ratio);
        // ground
        batch.draw(main.res.bgGround, width/2f-(main.res.bgGround.getWidth()*2*ratio)/2f, height/2.02f,
                main.res.bgGround.getWidth()*2*ratio, main.res.bgGround.getHeight()*2*ratio);
        batch.color().set(1f,1f,1f,1f);
        // grimoire circle on the ground
        summoner.getGrimoire().drawBg(batch);
    }

    public void drawHUD(){

        summoner.getGrimoire().drawHUD(batch);
        spell_holder.draw(this);

        if(main.params.GRIND_MODE && main.params.SUBLEVEL==9){
            main.res.hudButton.scale().set(2.25f*ratio);
            main.res.hudButton.draw(batch, width-125*ratio, height-150*ratio, 100*ratio,75*ratio);
            texter.drawTextRGB("Босс", 0.45f, width-75*ratio, (height-150*ratio+75*ratio/2f)/ratio, true, true, 0.32f, 0.35f, 0.4f);
        }

        upgrade_panel.draw(this);
        info_holder.draw(this);

        if(summoner.getState()==4) {
            summoner.getGrimoire().setGrimoireState(false);
            if (summoner.getFamiliarSpot().getFamiliars().isEmpty()) {
                restart_screen.draw(this);
            }
        }

        restart_screen.update(this);
    }

    public void debug(Main context){
        if(Key.G.pressed())
            context.params.gold.addAmount(500,1);
        texter.drawTextRGB(main.params.gold.getAmount() + "   " + main.params.gold.getMultiplier(), 0.3f, 45*ratio, (height-150*ratio+75*ratio/2f)/ratio, false, false, 0.32f, 0.35f, 0.4f);
    }

    @Override
    public void resize(int width, int height) {
        upgrade_panel.setClosing(true);
        camera.resize(width, height);
    }

    public TextureBatch getBatch() {
        return batch;
    }

    public Texter getTexter() {
        return texter;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    public EnemySpot getEnemySpot() {
        return enemy_spot;
    }

    public UpgradePanel getUpgradePanel() {
        return upgrade_panel;
    }
}
