package io.gvicst.summoner.ui;

import io.gvicst.summoner.screen.GameScreen;
import jpize.context.Jpize;

public class Restart {

    public boolean restart_fade = false;
    public float restart_fade_value = 0;
    public boolean start_fade = true;
    public float start_fade_value = 1f;

    public void draw(GameScreen game){
        game.getBatch().setAlpha(restart_fade_value);
        float width = game.width;
        float height = game.height;
        float ratio = game.ratio;

        restart_fade_value+=restart_fade_value<1f? Jpize.getDeltaTime()*4f:0f;
        restart_fade_value-=restart_fade?Jpize.getDeltaTime()*4.5f:0f;

        if(restart_fade && restart_fade_value<0) {
            game.main.params.SUMMONER_HP = game.main.params.SUMMONER_MAX_HP;
            game.getSummoner().setState(0);
            game.main.params.SUBLEVEL = 1;
            game.getEnemySpot().removeEnemy();
            game.main.params.GRIND_MODE = true;
            game.main.params.save();
            restart_fade_value = 0;
            restart_fade = false;
            start_fade = true;
            start_fade_value = 1f;
        }else {
            game.getBatch().drawRectBlack(0, 0, width, height, 0.5f*restart_fade_value);

            game.main.res.hudInterfaceHolder.scale().set(2.25f * ratio);
            game.main.res.hudInterfaceHolder.draw(game.getBatch(), width / 2f - 150 * ratio, height / 2f - height / 5f, 300 * ratio, height / 6.5f);

//            game.getTexter().drawTextRGBA("Конец игры", 0.75f, width / 2f, height / 1.6f, true, true, 1, 1, 1, restart_fade_value);
//            game.getTexter().drawTextRGBA("Рестарт?", 0.5f, width / 2f, (height / 2f - height / 5f + height / 7.5f) / ratio, true, true, 1, 1, 1, restart_fade_value);

            float button_y = height / 2f - (height / 7f) - 30f * ratio;

            game.main.res.hudButtonRestart.scale().set(2.25f * ratio);
            game.main.res.hudButtonRestart.draw(game.getBatch(), width / 2f - 90*ratio, button_y, 60 * ratio, 62 * ratio);

            game.main.res.hudButtonQuit.scale().set(2.25f * ratio);
            game.main.res.hudButtonQuit.draw(game.getBatch(), width / 2f + 30*ratio, button_y, 60 * ratio, 62 * ratio);
        }
        game.getBatch().setAlpha(1f);

        game.getBatch().drawRectRGBA(0, 0, width, height, 0xffffff00 + (int)(0xff*(restart_fade?1:0)*(1f-restart_fade_value)));
    }

    public void update(GameScreen game){
        start_fade_value-=start_fade?Jpize.getDeltaTime()/1.5f:0f;
        if (start_fade_value<=0) start_fade = false;
        game.getBatch().drawRectRGBA(0, 0, game.width, game.height, 0xffffff00 + (int)(0xff*(start_fade?1:0)*start_fade_value));
    }

}
