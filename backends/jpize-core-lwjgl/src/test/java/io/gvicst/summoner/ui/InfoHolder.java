package io.gvicst.summoner.ui;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import io.gvicst.summoner.utils.Texter;
import jpize.context.Jpize;
import jpize.util.mesh.TextureBatch;

public class InfoHolder {


    public void draw(GameScreen game){
        Main main = game.main;
        Texter texter = game.getTexter();
        TextureBatch batch = game.getBatch();
        float height = Jpize.getHeight();
        float width = Jpize.getWidth();
        float ratio = height/960f;

        main.res.hudInfoHolder.scale().set(1.5f*ratio);
        main.res.hudInfoHolder.draw(batch, 0, height-height/14f, width, height/14f);

        texter.drawText(main.params.gold.getValue(), 0.3f, width/2f-(67*1.5f*ratio), 960-31, true, true);
        texter.drawText(main.params.gold.valToString(main.params.DIAMONDS,0), 0.3f, width/2f+(67*1.5f*ratio), 960-31, true, true);

        texter.drawTextRGB(main.params.gold.valToInt(main.params.LEVEL,0), 0.34f, width/2f-21*ratio, 960-28, true, true, 0.32f, 0.35f, 0.4f);
        texter.drawTextRGB("-", 0.4f, width/2f, 960-28, true, true, 0.232f, 0.35f, 0.4f);
        texter.drawTextRGB(main.params.SUBLEVEL+"", 0.34f, width/2f+19*ratio, 960-28, true, true, 0.32f, 0.35f, 0.4f);

        texter.drawTextRGB((int)Math.floor((main.params.GAME_TIME/15f)%24)+":"+((int)Math.floor(main.params.GAME_TIME*4%60)<10?"0":"")+(int)Math.floor(main.params.GAME_TIME*4%60), 0.3f, width/2f, 960-11, true, true, 1f, 1f, 1f);

    }

}
