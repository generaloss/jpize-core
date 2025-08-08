package io.gvicst.summoner.entity;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import io.gvicst.summoner.ui.Grimoire;
import io.gvicst.summoner.utils.Spell;
import jpize.context.Jpize;
import jpize.context.input.MouseBtn;
import generaloss.spatialmath.geometry.Intersector;
import generaloss.spatialmath.vector.Vec2f;
import jpize.util.mesh.TextureBatch;

public class Summoner {

    private final Main main;
    private final GameScreen game;
    private final FamiliarSpot familiar_spot;
    private final Grimoire grimoire;

    private int state = 0;
    private final int cost = 20;

    private float timer;
    private float x;
    private final float[] anim_speed = {1,0.865f,3,0.865f/1.5f,2f};
    private final int[][] frames = {{0,1,2,3}, {10,11,12}, {13,14,15,16}, {12,11,10}, {17,18,19,20,21,22,23,24,25,26,27}};



    public Summoner(GameScreen game){
        this.familiar_spot = new FamiliarSpot(game, 128);
        this.grimoire = new Grimoire(game);
        this.game = game;
        this.main = game.main;
        this.main.params.SUMMONER_MAX_HP = main.params.SUMMONER_LEVEL * 11 + 9 + (main.params.SUMMONER_LEVEL - 1)*25;
        this.main.params.SUMMONER_HP = main.params.SUMMONER_MAX_HP;
        this.x = 0;
    }

    public void update(GameScreen game){
        updateRender(game);
        updateSummon();
        updateSpell(game);
        grimoire.update();
        familiar_spot.update();
        if (main.params.SUMMONER_HP>main.params.SUMMONER_MAX_HP)
            main.params.SUMMONER_HP = main.params.SUMMONER_MAX_HP;
        if (main.params.SUMMONER_HP<0)
            main.params.SUMMONER_HP=0;
    }

    public void draw(TextureBatch batch){
        familiar_spot.drawUpper(batch);
        batch.draw(main.res.heroes.get(0).getFrame(getFrameIndex(game.timer)),
                Jpize.getWidth()/2f - 164 * game.ratio + this.x * game.ratio,
                Jpize.getHeight()/2f + 64 * game.ratio, 128 * game.ratio, 128 * game.ratio);
        familiar_spot.drawDown(batch);
    }

    public void updateSpell(GameScreen game){
        Vec2f ywh_spells = new Vec2f(Jpize.getHeight()/2.5f+15*1.5f*(Jpize.getHeight() / 960f), 25*1.5f*(Jpize.getHeight() / 960f));
//        Vec2f ywh_spells = new Vec2f(Jpize.getHeight() / 2.429f, 25 * 2.25f * (Jpize.getHeight() / 960f));
        Spell[] spells= main.params.spells;
        for (int i = 0; i < 6; i++) {
            if(state!=4) {
                if (spells[i].isUnlocked() && spells[i].getCall_down() <= -spells[i].getCall_up() && (spells[i].getHotkey().down() || (Intersector.isPointOnRect(Jpize.input.getCursorX(), Jpize.input.getCursorY(), (Jpize.getWidth() / 2f - (85-34*i) * 1.5f * game.ratio) - 25 * 1.5f * game.ratio / 2f, ywh_spells.x, ywh_spells.y, ywh_spells.y) && MouseBtn.downAny(MouseBtn.values()))))
                    spells[i].setCall_down(spells[i].getDuration());

                if (spells[i].getCall_down() > 0) {
                    spells[i].setActive(true);
                }
                if (spells[i].getCall_down() > -spells[i].getCall_up()) {
                    spells[i].updateCall_down();
                }
                if (spells[i].getCall_down() <= 0 && spells[i].isActive())
                    spells[i].setActive(false);
            }else{
                spells[i].setActive(false);
                spells[i].setCall_down(0);
            }
        }
    }

    public void updateSummon(){
        // updating summon timer
        if (timer > 1) {
            familiar_spot.summon();
            grimoire.summon();
            timer = 0;
        } else
            timer += Jpize.getDeltaTime()*(state==2?(main.params.spells[1].isActive()?2:1):0);
    }

    public void updateRender(GameScreen game){
        // updating timer if state is changed
        game.timer = state!=4 && (int)Math.ceil(main.params.SUMMONER_HP)<=0?0:game.timer;
        state = (int)Math.ceil(main.params.SUMMONER_HP)<=0?4:state;

        if(state!=4) {
            // updating state
            int state_temp = grimoire.isGrimoireActivated()?
                    (grimoire.grimoireInertia<1?1:2):
                    (grimoire.grimoireInertia==0)?
                            0:(grimoire.grimoireInertia<1&&grimoire.grimoireInertia!=0)?
                            3:0;

            game.timer = state != state_temp?0: game.timer;

            state = state_temp;

            // updating position to be in center
            x += (-x) / 20f;
        }
    }

    public FamiliarSpot getFamiliarSpot() {
        return familiar_spot;
    }

    public Grimoire getGrimoire() {
        return grimoire;
    }

    public int getFrameIndex(float time){
        return state!=4?
                frames[state][(int)(time * anim_speed[state])%(frames[state].length-1)]:
                frames[state][Math.min((int)(time * anim_speed[state]),frames[state].length-1)];
    }

    public int getCost() {
        return (int)(cost*main.params.SUMMONER_LEVEL*2);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void upgrade(){
        this.main.params.gold.addAmount(-getCost(),0);
        this.main.params.SUMMONER_HP = main.params.SUMMONER_HP - (main.params.SUMMONER_LEVEL * 11 + 9 + (main.params.SUMMONER_LEVEL - 1)*25);
        this.main.params.SUMMONER_LEVEL += 1;
        this.main.params.SUMMONER_MAX_HP = main.params.SUMMONER_LEVEL * 11 + 9 + (main.params.SUMMONER_LEVEL - 1)*25;
        this.main.params.SUMMONER_HP = main.params.SUMMONER_HP + (main.params.SUMMONER_LEVEL * 11 + 9 + (main.params.SUMMONER_LEVEL - 1)*25);
        this.main.params.save();
    }

    public void move(float x) {
        this.x += x;
    }
}
