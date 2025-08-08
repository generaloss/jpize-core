package io.gvicst.summoner.ui;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import io.gvicst.summoner.utils.Texter;
import jpize.context.Jpize;
import jpize.util.color.Color;
import generaloss.spatialmath.vector.Vec2f;
import jpize.util.mesh.TextureBatch;

public class SpellHolder {


    public void draw(GameScreen game){
        Main main = game.main;
        Texter texter = game.getTexter();
        TextureBatch batch = game.getBatch();
        float height = Jpize.getHeight();
        float width = Jpize.getWidth();
        float ratio = height/960f;

        batch.drawRect(0, height/2.12f, width, height/40f, new Color(0.421875f, 0.44921875f, 0.4921875f));
        batch.drawRect(width/2f-173.25f*ratio, height/2.15f, (main.params.SUMMONER_HP/ main.params.SUMMONER_MAX_HP)*346.5f*ratio, height/40f, new Color(1f, 0.25f, 0.25f));

        main.res.hudSpellHolder.scale().set(1.5f*ratio);
        main.res.hudSpellHolder.draw(batch, 0, height/2.5f, width, height/10f);

        texter.drawText(main.params.gold.valToString((int)Math.ceil(game.main.params.SUMMONER_HP),0)+"/"+main.params.gold.valToString((int)Math.ceil(game.main.params.SUMMONER_MAX_HP),0), 0.25f, width/2f, 960-507, true, true);
        texter.drawText(game.main.params.PLAYER_NAME, 0.3f, width/2f, 960-491, true, true);

        Vec2f ywh_spells = new Vec2f(height/2.5f+15*1.5f*ratio, 25*1.5f*ratio);
        for (int i = 0; i < 6; i++) {
            float x_spells = width / 2f - ((85 - 34 * i) * 1.5f * ratio) - 25 * 1.5f * ratio / 2f;
            float locked_batch = !main.params.spells[i].isUnlocked()?0.4f:!main.params.spells[i].isActive() && main.params.spells[i].getCall_down() <= -main.params.spells[i].getCall_up()?1f-(1f+(float)Math.sin(main.params.GAME_TIME*40f))/15f:0.6f;
            batch.color().set(locked_batch, locked_batch, locked_batch);
            batch.draw(main.res.boosters[i], x_spells, ywh_spells.x, ywh_spells.y, ywh_spells.y);
            batch.color().set(1, 1, 1);
            if (!main.params.spells[i].isUnlocked())
                batch.draw(main.res.hudLock, x_spells, ywh_spells.x, ywh_spells.y, ywh_spells.y);

        }
        for (int i = 0; i < 6; i++) {
            if(main.params.spells[i].isUnlocked()) {
                float x_spells = width / 2f - ((85 - 34 * i) * 1.5f * ratio) - 25 * 1.5f * ratio / 2f;
                float num_color = main.params.spells[i].getCall_down() > 0 ? 1f : 0.2f;
                texter.drawTextRGB(main.params.spells[i].getCall_down() > -main.params.spells[i].getCall_up() ? main.params.spells[i].getCall_down() > 0 ? "" + (int) (1 + main.params.spells[i].getCall_down()) : "" + (int) (1 + main.params.spells[i].getCall_up() + main.params.spells[i].getCall_down()) : "",
                        0.3f, x_spells + ywh_spells.y / 2f, (ywh_spells.x + ywh_spells.y / 2f) / game.ratio, true, true,
                        0.9f, 0.9f, 1f - num_color);
            }
        }
    }
}
