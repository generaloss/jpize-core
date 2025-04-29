package io.gvicst.summoner.entity;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import jpize.context.Jpize;
import jpize.opengl.texture.Texture2D;
import jpize.util.font.Font;
import jpize.util.math.Mathc;
import jpize.util.mesh.TextureBatch;

import java.util.ArrayList;

public class EnemySpot {

    private final Main main;
    private final ArrayList<Enemy> enemy;
    private final ArrayList<DamageNumber> damage_numbers;

    public EnemySpot(Main main){
        this.main = main;
        this.enemy = new ArrayList<>();
        this.damage_numbers = new ArrayList<>();
    }

    public void update(GameScreen game) {
        if (!enemy.isEmpty()) {
            enemy.get(0).update(game);
            if (enemy.get(0).hp <= 0) {
                if (main.params.SUBLEVEL < 10) {
                    if (!main.params.GRIND_MODE)
                        main.params.SUBLEVEL++;
                    else if (main.params.SUBLEVEL < 9)
                        main.params.SUBLEVEL++;
                } else {
                    main.params.SUBLEVEL = 1;
                    main.params.LEVEL++;
                }
                if (main.params.LEVEL % 10 == 0 && main.params.SUBLEVEL == 10)
                    main.params.DIAMONDS++;
                double gold = main.params.LEVEL*4 + main.params.LEVEL * (Math.random() * 0.5f + 1) + main.params.LEVEL * (Math.random() * 0.5f + 1) * main.params.BONUS_GOLD * (enemy.get(0).type != 0 ? 10 : 1);
                main.params.gold.addAmount((float) (main.params.spells[2].isActive() ? gold * 2 : gold), 0);
                enemy.remove(0);
                main.params.save();
            }
        } else {
            int boss_id = (main.params.LEVEL % 5 == 0 ? ((main.params.LEVEL - 5) / 5) % 20 : (((main.params.LEVEL - 1) / 2) * 2) % 100 + (6 - (int) (Math.random() * 2 - 2) - Math.max(0, main.params.LEVEL % 50 - 43)));
            int enemy_id = ((((main.params.LEVEL - 1) / 2) * 2) % 100 + (int) (Math.random() * (3 - Math.max(0, Math.min(3, main.params.LEVEL % 51 - 48)))));
            if (main.params.SUBLEVEL == 10) {
                if (main.params.LEVEL % 5 == 0) {
                    enemy.add(new Enemy(main, 1, boss_id, (int) (Math.random() * 32 - 16), (int) (Math.random() * 8 - 4), main.params.LEVEL * main.params.LEVEL - main.params.LEVEL + 1, 75 + main.params.LEVEL * 350));
                } else {
                    enemy.add(new Enemy(main, 0, boss_id, (int) (Math.random() * 32 - 16), (int) (Math.random() * 8 - 4), main.params.LEVEL * main.params.LEVEL - main.params.LEVEL + 1, 75 + main.params.LEVEL * 150));
                }
            } else if (Math.random() * 100 < 100 / (main.params.CHEST_APPEARANCE_CHANCE + 0.05)) {
                enemy.add(new Enemy(main, 0, enemy_id, (int) (Math.random() * 32 - 16), (int) (Math.random() * 32 - 16), 0, 75 + main.params.LEVEL * 50));
            } else {
                enemy.add(new Enemy(main, 2, 0, (int) (Math.random() * 32 - 16), (int) (Math.random() * 32 - 16), 0, 75 + main.params.LEVEL * 50));
            }
        }
    }

    public void draw(TextureBatch batch, GameScreen game) {
        if (!enemy.isEmpty())
            enemy.get(0).draw(batch, game);

        if (!damage_numbers.isEmpty())
            for (int i = 0; i < damage_numbers.size(); i++) {
                damage_numbers.get(i).draw(game, main.font());
                if (damage_numbers.get(i).timer < 0)
                    damage_numbers.remove(i);
            }
    }

    public void hit(float damage, int crit){
        if(!enemy.isEmpty()) {
            enemy.get(0).hp -= damage;
            enemy.get(0).x += 10;
            damage_numbers.add(new DamageNumber((int)damage, crit, enemy.get(0).x, enemy.get(0).y, enemy.get(0).w, enemy.get(0).h));
        }
    }

    public void removeEnemy() {
        if (!enemy.isEmpty()) {
            enemy.remove(0);
        }
    }

    public static class Enemy {

        public Main context;
        private final int type, y;
        public float w, h, x, def_x;
        private Texture2D tex;
        public float timer, hp, max_hp;
        public float timer_call_down;
        public float damage;

        public Enemy(Main context, int type, int id, int x, int y, float atk, float hp){
            this.context = context;
            this.type = type;
            this.x = x;
            this.def_x = x;
            this.y = y;
            this.tex = context.res.enemies.get(0);
            if(type==0)
                this.tex = context.res.enemies.get(id);
            if(type==1)
                this.tex = context.res.bosses.get(id);
            if(type==2)
                this.tex = context.res.treasureChest;
            this.w = tex.getWidth();
            this.h = tex.getHeight();
            this.timer_call_down = 0;
            this.damage = atk;
            this.max_hp = hp + (float)Math.random() * (hp/20f) - (hp/10f);
            this.hp = max_hp;
        }

        public void update(GameScreen game){
            timer += Jpize.getDeltaTime()*6f;

            x += (def_x-x)/20f*Jpize.getDeltaTime()*360;

            if(timer>20f){
                if(game.main.params.SUMMONER_HP>0)
                    attack(game);
                timer = 5f;
            }
        }

        public void draw(TextureBatch batch, GameScreen game){
            batch.setAlpha(Math.min(timer/3f, 1f));
            batch.flipX(true);
            batch.draw(tex, Jpize.getWidth()/2f+96*game.ratio+x*game.ratio-w*game.ratio, Jpize.getHeight()/2f+64*game.ratio+y*game.ratio, w*game.ratio*2, h*game.ratio*2);
            batch.flipX(false);
            batch.setAlpha(1f);
            batch.drawRect(Jpize.getWidth()/2f+96*game.ratio+x*game.ratio-w*game.ratio*1.5f/2f, Jpize.getHeight()/2f+64*game.ratio+y*game.ratio+h*game.ratio/10f*22, w*game.ratio*1.5f, 4*game.ratio, 0, 0, 0, 0.25f);
            batch.drawRect(Jpize.getWidth()/2f+96*game.ratio+x*game.ratio-w*game.ratio*1.5f/2f, Jpize.getHeight()/2f+64*game.ratio+y*game.ratio+h*game.ratio/10f*22, w*game.ratio*(hp/max_hp)*1.5f, 4*game.ratio, 1f, 0.25f, 0.25f);
        }

        public void attack(GameScreen game){
            if(this.damage>0) {
                game.main.params.SUMMONER_HP-=this.damage;
                game.getSummoner().move(-5);
                x-=15;
                if(game.main.params.SUMMONER_HP<0)
                    game.main.params.SUMMONER_HP=0;
            }
        }
    }

    public static class DamageNumber{

        private final int damage, crit;
        private final float x, y, w, h;
        private float timer = 3;

        public DamageNumber(int damage, int crit, float x, float y, float w, float h){
            this.damage = damage;
            this.crit = crit;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public void draw(GameScreen game, Font font){
            timer-=Jpize.getDeltaTime()*2f;
            font.getOptions().scale().set(0.5f*(Jpize.getHeight()/960f));
            font.getOptions().color().set(1, 1.5f-crit/2f, 1f-crit/2f, (timer*timer*timer)/27f);
            font.drawText(damage+"", Jpize.getWidth()/2f+96*game.ratio-font.getTextWidth(damage+"")/2f+x*game.ratio-w*game.ratio/4f, Jpize.getHeight()/2f+64*game.ratio+y*game.ratio+h*game.ratio*2+font.getTextHeight(damage+"")*game.ratio+(Mathc.cbrt(3)-Mathc.cbrt(timer))*100*game.ratio);
            font.getOptions().color().setAlpha(1);
        }

    }

}
