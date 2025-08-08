package io.gvicst.summoner.entity;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import io.gvicst.summoner.utils.Animation;
import jpize.context.Jpize;
import spatialmath.Mathc;
import jpize.util.mesh.TextureBatch;

import java.util.ArrayList;
import java.util.Comparator;

public class FamiliarSpot {

    private final Main main;
    private final GameScreen game;
    private final int[] yOffsetLines = {24, 16, 8, -8, -16, -24};
    private final int[] xLineLength = {128, 132, 164, 164, 132, 128};
    private final int max_amount;
    private final ArrayList<Familiar> familiars;

    public FamiliarSpot(GameScreen game, int max_amount){
        this.game = game;
        this.main = game.main;
        this.max_amount = max_amount;
        this.familiars = new ArrayList<>();
    }

    public void update(){
        if(!familiars.isEmpty())
            for(int i = 0; i< familiars.size(); i++) {
                familiars.get(i).update();
                if (familiars.get(i).die_timer > 80 * main.params.LIFETIME_OF_FAMILIARS)
                    familiars.remove(i);
            }
    }

    public void drawUpper(TextureBatch batch){
        if(!familiars.isEmpty())
            for (Familiar familiar : familiars)
                if (familiar.y > 0)
                    familiar.draw(batch);
    }
    public void drawDown(TextureBatch batch){
        if(!familiars.isEmpty())
            for (Familiar familiar : familiars)
                if (familiar.y < 0)
                    familiar.draw(batch);
    }

    public void summon(){
        if(familiars.size()<max_amount){
            int d = (int)(Math.random()*yOffsetLines.length);
            int type = main.params.army_list.get((int) (Math.random() * main.params.army_list.size()));
            familiars.add(new Familiar(game, type, (int) (Math.random() * xLineLength[d] - xLineLength[d] / 2f), yOffsetLines[d] + (int) (Math.random() * 8) - 4));
        }
        familiars.sort(Comparator.comparing(Familiar::getY));
    }

    public ArrayList<Familiar> getFamiliars() {
        return familiars;
    }

    public static class Familiar {

        private final Main main;
        private final GameScreen game;
        private final Animation animation_familiar, animation_explotano;
        private final int[] attackFrames;
        private final int num;
        private final float x, y, w, h, damage;
        private float timer, die_timer, timer_call_down;
        private boolean explotano, attacked;

        public Familiar(GameScreen game, int type, int x, int y){
            this.game = game;
            this.main = game.main;
            this.x = x;
            this.y = y;
            this.animation_familiar = main.res.familiars.get(type);
            this.animation_explotano = main.res.explotano.get(type);
            this.num = animation_familiar.getFramesAmount();
            this.w = animation_familiar.getFrameWidth()*4;
            this.h = animation_familiar.getFrameHeight()*4;
            this.timer_call_down = 0;
            this.damage = 10 + (main.params.GRIMOIRE_ATK - 1)*25 + main.params.GRIMOIRE_ATK * 10.45f * main.params.familiars[type].getAttackBonus() + ((10+10.45f* main.params.GRIMOIRE_ATK)/10*Mathc.random()-(10+10.45f* main.params.GRIMOIRE_ATK)/20);
            this.attackFrames = new int[][]{{8}, {7}, {5,13}, {6,15}, {6}, {8}, {9,27}, {9,13}, {9,29}, {6}, {7,16}, {7,17,21}, {8}}[type];
        }

        public void update(){
            timer += Jpize.getDeltaTime()*6f;
            die_timer += main.params.spells[4].isActive()?Jpize.getDeltaTime()/2f:Jpize.getDeltaTime()*6f;

            if(timer>2.5f)
                explotano=true;

            if(timer_call_down>0)
                timer_call_down-=Jpize.getDeltaTime();
            else
                attacked = false;

            for (int attackFrame : attackFrames) {
                if ((int) timer % num == attackFrame && !attacked) {
                    attack();
                    timer_call_down = 0.25f;
                    attacked = true;
                }
            }


        }

        public void draw(TextureBatch batch){
            batch.setAlpha(Math.min(timer*3,Math.min(Math.min(80 * main.params.LIFETIME_OF_FAMILIARS - 3, 80 * main.params.LIFETIME_OF_FAMILIARS - die_timer), 1f)));
            batch.draw(animation_familiar.getFrame((int) timer % num), Jpize.getWidth() / 2f - 96 * game.ratio + x * game.ratio - w * game.ratio / 4f,
                    Jpize.getHeight() / 2f + 64 * game.ratio + y * game.ratio, w * game.ratio / 2f, h * game.ratio / 2f);
            batch.setAlpha(1f);
            if(!explotano)
                batch.draw(animation_explotano.getFrame((int)(timer*3%num)), Jpize.getWidth() / 2f - 96 * game.ratio+x * game.ratio - w * game.ratio / 4f,
                        Jpize.getHeight() / 2f + 64 * game.ratio + y * game.ratio, w * game.ratio / 2f, h * game.ratio / 2f);
        }

        public void attack(){
            float crit = (main.params.spells[3].isActive()?((Math.random()* main.params.CRT_CHANCE*1.5f>1f)? main.params.CRT_DAMAGE:1f):((Math.random()* main.params.CRT_CHANCE>1f)? main.params.CRT_DAMAGE:1f));
            float crit_damage = (this.damage + (main.params.spells[0].isActive()?1:0)*this.damage*0.2f)*crit + this.damage*(Mathc.random()/20f)-this.damage/10f;
            game.getEnemySpot().hit(crit_damage, crit>1?2:1);
        }

        public float getY() {
            return -y;
        }
    }

}