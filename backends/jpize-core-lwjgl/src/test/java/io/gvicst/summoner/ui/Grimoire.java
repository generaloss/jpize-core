package io.gvicst.summoner.ui;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import jpize.context.Jpize;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.util.color.Color;
import spatialmath.Maths;
import spatialmath.geometry.Intersector;
import spatialmath.vector.Vec4f;
import jpize.util.mesh.TextureBatch;

public class Grimoire {

    private final GameScreen game;
    private final Main main;
    private boolean isGrimoireActivated;
    public final Vec4f grimoirePos;
    public Color grimoireColor;
    public float grimoireInertia = 0;
    private float grimoireFlashInertia = 0;
    private boolean summon;
    private float summon_timer = 0;
    private final int cost = 20;

    public Grimoire(GameScreen game){
        this.game = game;
        this.main = game.main;
        this.grimoirePos = new Vec4f(0,0,0,0);
        this.grimoireColor = new Color();
    }

    public void update(){
        boolean is_hovering_button = Intersector.isPointOnRect(Jpize.input.getCursorX(),Jpize.input.getCursorY(),
                grimoirePos.x,grimoirePos.y,grimoirePos.z,grimoirePos.w);
        setGrimoireState(game.getSummoner().getState()!=4 && (Key.SPACE.pressed() || (is_hovering_button && MouseBtn.pressedAny(MouseBtn.values()))));
        grimoireColor.set(0.95f-0.43f*((((1-(main.params.spells[1].getCall_down()/((main.params.SPELLS_DURATION*0.2f+1)*5)))*(main.params.spells[1].getCall_down()/(main.params.SPELLS_DURATION*0.2f+1)*5))*(main.params.spells[1].isActive()?1:0))),0.8f,0.99f,grimoireInertia);

        if(summon && summon_timer < 1f)
            summon_timer+=Jpize.getDeltaTime()+(1-summon_timer)*Jpize.getDeltaTime()*2f;
        else {
            summon_timer = 0;
            summon = false;
        }

        if (isGrimoireActivated()) {
            if (grimoireInertia < 1)
                grimoireInertia += Jpize.getDeltaTime()*4;
            if (grimoireInertia > 0.9f)
                grimoireFlashInertia = 0;
        } else {
            grimoireInertia -= Math.min(Jpize.getDeltaTime()*2f, grimoireInertia);
            grimoireFlashInertia += Jpize.getDeltaTime()*60*4;
        }
    }

    public void drawBg(TextureBatch batch){
        batch.draw(main.res.bgGrimoireCircle, game.width/2f-224* game.ratio, game.height/2f+2 * game.ratio, main.res.bgGrimoireCircle.getWidth()*2f * game.ratio, main.res.bgGrimoireCircle.getHeight()*2f * game.ratio, grimoireColor);
    }

    public void drawHUD(TextureBatch batch){
        float grimoireSize = 2.25f * game.ratio;
        this.grimoirePos.set(
                game.width/2f - main.res.hudGrimoireCircle.getWidth() * grimoireSize /2f,
                game.height/4.15f - main.res.hudGrimoireCircle.getHeight() * grimoireSize /2f,
                main.res.hudGrimoireCircle.getWidth() * grimoireSize,
                main.res.hudGrimoireCircle.getHeight() * grimoireSize
        );

        batch.drawRect(0, 0, game.width, game.height/2.2f, new Color(0.2890625f, 0.3046875f, 0.33984375f));

        batch.drawRGBA(main.res.hudGrimoireCircle,
                grimoirePos.x, grimoirePos.y, grimoirePos.z, grimoirePos.w,
                0xffffff00+(int)(250*(Maths.sinDeg(grimoireFlashInertia)+1.5f)*0.15f)
        );
        batch.draw(main.res.hudGrimoireCircle,
                grimoirePos.x, grimoirePos.y, grimoirePos.z, grimoirePos.w, grimoireColor);
        float alpha = grimoireColor.alpha;
        grimoireColor.setAlpha(summon?1-summon_timer:0);
        batch.draw(main.res.hudGrimoireCircle,
                game.width/2f - grimoirePos.z*(1 + 0.1f*summon_timer*game.ratio)/2f,
                game.height/4.15f - grimoirePos.w*(1 + 0.1f*summon_timer*game.ratio)/2f,
                grimoirePos.z*(1 + 0.1f*summon_timer*game.ratio), grimoirePos.w*(1 + 0.1f*summon_timer*game.ratio), grimoireColor);
        grimoireColor.setAlpha(alpha);
        batch.draw(main.res.hudGrimoire,
                game.width/2f - main.res.hudGrimoire.getWidth()* grimoireSize /2f,
                game.height/4.15f - main.res.hudGrimoire.getHeight()* grimoireSize /2f,
                main.res.hudGrimoire.getWidth() * grimoireSize,
                main.res.hudGrimoire.getHeight() * grimoireSize);
    }

    public void summon(){
        summon = true;
        summon_timer = 0;
    }

    public int getCost() {
        return (int)(cost*main.params.GRIMOIRE_ATK);
    }

    public boolean isGrimoireActivated() {
        return isGrimoireActivated;
    }

    public void setGrimoireState(boolean grimoireState) {
        isGrimoireActivated = grimoireState;
    }

}
