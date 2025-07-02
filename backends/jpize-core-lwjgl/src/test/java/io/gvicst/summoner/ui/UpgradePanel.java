package io.gvicst.summoner.ui;

import io.gvicst.summoner.main.Main;
import io.gvicst.summoner.screen.GameScreen;
import io.gvicst.summoner.utils.*;
import jpize.context.Jpize;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.util.color.Color;
import jpize.util.math.geometry.Intersector;
import jpize.util.mesh.TextureBatch;

public class UpgradePanel {

    private boolean panelOpen;
    private boolean closing, opening;

    private float input_temp;
    private float panel_y = -Jpize.getHeight();
    private int page_num;
    private float page_annotation;
    private float page_content;
    private final float[] scroll_y = {0,0,0,0};

    public void draw(GameScreen game){
        Main main = game.main;
        Texter texter = game.getTexter();
        TextureBatch batch = game.getBatch();
        float height = Jpize.getHeight();
        float width = Jpize.getWidth();
        float ratio = height/960f;
        float[] page_content_height = new float[]{400 * ratio, 3000 * ratio, 1000 * ratio, 1000 * ratio};

        boolean isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(),Jpize.input.getCursorY(),
                5*ratio, 5*ratio, width - 10*ratio, 40*ratio);

        if(game.getSummoner().getState()!=4) {
            if (Key.W.down())
                panelOpen = true;
            if (Key.S.down())
                panelOpen = false;
            if (isOnRect && MouseBtn.upAny(MouseBtn.values()))
                panelOpen = !panelOpen;
            if ((isOnRect && MouseBtn.upAny(MouseBtn.values())) || Key.W.down() || Key.S.down()) {
                if (!closing && !panelOpen) {
                    closing = true;
                    opening = false;
                }
                if (!opening && panelOpen) {
                    opening = true;
                    closing = false;
                }
            }
        } else {
            panelOpen = false;
            if (!closing) {
                closing = true;
                opening = false;
            }
        }

        if(panelOpen){
            main.res.hudUpgradePages.scale().set(1.5f*ratio);

            if(Key.A.down() && page_num>0)
                page_num-=1;
            if(Key.D.down() && page_num<3)
                page_num+=1;

            for (int i = 0; i <4; i++) {
                isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(), Jpize.input.getCursorY(),
                        width / 2f - (main.res.hudUpgradePages.getMinWidth() / 2f) + (i*main.res.hudUpgradePages.getMinWidth()/4f), height - panel_y - 105f*ratio, main.res.hudUpgradePages.getMinWidth() / 4f, 160*ratio);
                if (isOnRect && MouseBtn.downAny(MouseBtn.values())){
                    page_num = i;
                }
            }

            if(MouseBtn.downAny(MouseBtn.values()))
                input_temp = Jpize.input.getCursorY();

            scroll_y[page_num] -= Jpize.input.getScrollY() * 10f - (MouseBtn.pressedAny(MouseBtn.values()) ? (Jpize.input.getCursorY() - input_temp):0);
            scroll_y[page_num] = Math.min(Math.max(0, scroll_y[page_num]), page_content_height[page_num]);

            System.out.println("Panel dif: " + (Jpize.input.getCursorY() - input_temp) + ", cur: " + Jpize.input.getCursorY() + ", prev: " + input_temp);

            input_temp = Jpize.input.getCursorY();
        }

        page_annotation += (page_num*main.res.hudUpgradePages.getMinWidth()/4f - page_annotation)/20f * Jpize.getDeltaTime()*360;
        page_content += (page_num*width - page_content)/20f * Jpize.getDeltaTime()*360;


        if(opening) {
            if (panel_y < 0)
                panel_y += (Jpize.getDeltaTime()*ratio - panel_y)/20f*Jpize.getDeltaTime()*360;
            else {
                opening = false;
                panel_y = 0;
            }
        }
        if(closing) {
            if (panel_y > -height)
                panel_y -= (Jpize.getDeltaTime()*ratio - panel_y)/10f*Jpize.getDeltaTime()*360;
            else {
                closing = false;
                panel_y = -height;
                panelOpen = false;
            }
        }
        batch.drawRect(0, panel_y, width, height, new Color(0.2890625f, 0.3046875f, 0.33984375f));

        if(panelOpen || closing) {
            drawContent(game, texter);

            batch.drawRect(0, height-panel_y-98f*ratio, width, 160*ratio, new Color(0.2890625f, 0.3046875f, 0.33984375f));
            batch.drawRect(0, height-panel_y-98f*ratio, width, 4*ratio, new Color(0.4492f, 0.5273f, 0.6523f));
            batch.drawRect(width/2f-(main.res.hudUpgradePages.getMinWidth()/2f)+ page_annotation, height-panel_y-98f*ratio, main.res.hudUpgradePages.getMinWidth()/4f, 160*ratio, new Color(1, 1, 1));
            main.res.hudUpgradePages.draw(batch, 0, height-panel_y-160*ratio, width, 160*ratio);
        }

        batch.drawRect(0, 0, width, 50*ratio, new Color(0.4492f, 0.5273f, 0.6523f));
        if(!panelOpen) {
            main.res.hudButtonUpgradeOff.scale().set(1.5f*ratio);
            main.res.hudButtonUpgradeOff.draw(batch, 70*ratio, 5*ratio, width - 140*ratio, 40*ratio);
        }else{
            main.res.hudButtonUpgradeOn.scale().set(1.5f*ratio);
            main.res.hudButtonUpgradeOn.draw(batch, 70*ratio, 5*ratio, width - 140*ratio, 40*ratio);
        }
    }

    public void setClosing(boolean closing) {
        this.closing = closing;
    }

    public void drawContent(GameScreen game, Texter texter){
        Main main = game.main;
        TextureBatch batch = game.getBatch();
        float ratio = game.ratio;
        float height = game.height;
        float width = game.width;
        boolean is_changing_pages = !(page_content>page_num*width-10 && page_content<page_num*width+10);

        main.res.hudButton.scale().set(2.25f*ratio);
        main.res.hudItemHolder.scale().set(2.25f*ratio);
        main.res.hudInterfaceHolder.scale().set(2.25f*ratio);

        if(page_num == 0 || is_changing_pages) {
            draw_first_page(game, batch, texter, width/2f - 241*ratio - page_content, panel_y + height - 120*ratio + scroll_y[0], ratio);
        }
        if(page_num == 1 || is_changing_pages) {
            draw_second_page(game, batch, texter, width + width/2f - 241*ratio - page_content, panel_y + height - 120*ratio + scroll_y[1], ratio);
        }
        if(page_num == 2 || is_changing_pages) {
            draw_third_page(game, batch, texter, width*2 + width/2f - 241*ratio - page_content, panel_y + height - 120*ratio + scroll_y[2], ratio);
        }
//        if(page_num == 3 || is_changing_pages) {
//            draw_fourth_page(game, batch, texter, width*3 + width/2f - 241*ratio - page_content, panel_y + height - 120*ratio + scroll_y[3], ratio);
//        }
    }

    public void draw_first_page(GameScreen game, TextureBatch batch, Texter texter, float left_border, float top_border, float r){
        Main m = game.main;
        m.res.hudItemHolder.draw(batch, left_border, top_border - 300*r, 482*r, 300*r);
        texter.drawText(m.params.PLAYER_NAME, 0.4f, left_border + 10*r, (top_border - 20*r) / r, false, true);
        batch.draw(m.res.heroes.get(0).getFrame(0), left_border + 241*r - m.res.heroes.get(0).getFrameWidth()*2*r, top_border - 230*r, m.res.heroes.get(0).getFrameWidth()*2 * 2*r, m.res.heroes.get(0).getFrameHeight()*2 * 2*r);
        texter.drawText("HP " + m.params.gold.valToString((int)Math.ceil(m.params.SUMMONER_MAX_HP), 0), 0.35f, left_border + 180*r, (top_border - 270*r) / r, true, true);
        float color;
        if (!m.params.gold.isMoreThan(game.getSummoner().getCost(),0))
            color = 0.5f;
        else
            color = 1f;
        batch.color().set(color, color, color);
        m.res.hudButton.draw(batch, left_border + 352*r, top_border - 295*r, 120*r, 50*r);
        texter.drawTextRGB(m.params.gold.valToString(game.getSummoner().getCost(),0), 0.35f, left_border + 412*r, (top_border - 270*r) / r, true, true, 0.23f * color, 0.23f * color, 0.23f * color);
        batch.color().set(1, 1, 1);
        boolean isOnRectGrim = Jpize.input.isCursorInRect(left_border + 352*r, top_border - 295*r, 120*r, 50*r);
        if (isOnRectGrim && MouseBtn.upAny(MouseBtn.values()) && m.params.gold.isMoreThan(game.getSummoner().getCost(),0)) {
            game.getSummoner().upgrade();
        }

        for (Spell spell : m.params.spells) {
            if (!spell.isUnlocked())
                batch.color().set(0.5f, 0.5f, 0.5f);
            m.res.hudInterfaceHolder.draw(batch, left_border, top_border - (410 + 110 * spell.getId())*r, 482*r, 100*r);
            batch.draw(m.res.boosters[spell.getId()], left_border + 10*r, top_border - (345 + 110 * spell.getId())*r, 30*r, 30*r);

            float text_color = 1f;
            if (!spell.isUnlocked())
                text_color = 0.5f;
            texter.drawTextRGB(spell.getName(), 0.4f, left_border + 50*r, (top_border - (330 + 110 * spell.getId())*r) / r, false, true, text_color, text_color, text_color);
            texter.drawTextRGB(spell.getDescription(), 0.35f, left_border + 20*r, (top_border - (380 + 110 * spell.getId())*r) / r, false, true, text_color, text_color, text_color);
            if(!spell.isUnlocked()) {
                if (!m.params.gold.isMoreThan(spell.getCost(),0))
                    text_color = 0.5f;
                else
                    text_color = 1f;
                batch.color().set(text_color, text_color, text_color);
                m.res.hudButton.draw(batch, left_border + 352*r, top_border - (405 + 110 * spell.getId())*r, 120*r, 50*r);
                texter.drawTextRGB(m.params.gold.valToString(spell.getCost(),0), 0.35f, left_border + 412*r, (top_border - (380 + 110 * spell.getId())*r) / r, true, true, 0.23f * text_color, 0.23f * text_color, 0.23f * text_color);
                batch.color().set(1, 1, 1);
                if (!spell.isUnlocked())
                    batch.draw(m.res.hudLock, left_border + 241*r - m.res.hudLock.getWidth()*r, top_border - (385 + 110 * spell.getId())*r, m.res.hudLock.getWidth() * 2*r, m.res.hudLock.getHeight() * 2*r);
                boolean isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(), Jpize.input.getCursorY(),
                        left_border + 352*r, top_border - (405 + 110 * spell.getId())*r, 120*r, 50*r);
                if (isOnRect && MouseBtn.upAny(MouseBtn.values()) && m.params.gold.isMoreThan(spell.getCost(),0)) {
                    if (!spell.isUnlocked())
                        spell.setUnlocked(true);
                    m.params.gold.addAmount(-spell.getCost(),0);
                    m.params.save();
                }
            }
        }

        m.res.hudInterfaceHolder.draw(batch, left_border, top_border - (410 + 110 * 6)*r, 482*r, 100*r);
        texter.drawText("Перерождение", 0.4f, left_border + 10*r, (top_border - (330 + 110 * 6)*r) / r, false, true);
        texter.drawText("Вернуться на 1 уровень и получить", 0.35f, left_border + 20*r, (top_border - (370 + 110 * 6)*r) / r, false, true);
        texter.drawText("бонус к выпадению Золота", 0.35f, left_border + 20*r, (top_border - (390 + 110 * 6)*r) / r, false, true);
        m.res.hudButton.draw(batch, left_border + 352*r, top_border - (405 + 110 * 6)*r, 120*r, 50*r);
    }

    public void draw_second_page(GameScreen game, TextureBatch batch, Texter texter, float left_border, float top_border, float r){
        Main m = game.main;
        m.res.hudItemHolder.draw(batch, left_border, top_border - 300*r, 482*r, 300*r);
        texter.drawText("Гримуар", 0.4f, left_border + 10*r, (top_border - 20*r) / r, false, true);
        int grimoire_attack = (int)(10 + (m.params.GRIMOIRE_ATK - 1)*25f + m.params.GRIMOIRE_ATK * 10.45f);
        batch.draw(m.res.hudGrimoire, left_border + 241*r - m.res.hudGrimoire.getWidth()*r, top_border - 230*r, m.res.hudGrimoire.getWidth() * 2*r, m.res.hudGrimoire.getHeight() * 2*r);
        texter.drawText("ATK " + NumToString.convert(grimoire_attack), 0.35f, left_border + 180*r, (top_border - 270*r) / r, true, true);
        float color;
        if (!m.params.gold.isMoreThan(game.getSummoner().getGrimoire().getCost(),0))
            color = 0.5f;
        else
            color = 1f;
        batch.color().set(color, color, color);
        m.res.hudButton.draw(batch, left_border + 352*r, top_border - 295*r, 120*r, 50*r);
        texter.drawTextRGB(NumToString.convert(game.getSummoner().getGrimoire().getCost()), 0.35f, left_border + 412*r, (top_border - 270*r) / r, true, true, 0.23f * color, 0.23f * color, 0.23f * color);
        batch.color().set(1, 1, 1);
        boolean isOnRectGrim = Intersector.isPointOnRect(Jpize.input.getCursorX(), Jpize.input.getCursorY(),
                left_border + 352*r, top_border - 295*r, 120*r, 50*r);
        if (isOnRectGrim && MouseBtn.upAny(MouseBtn.values()) && m.params.gold.isMoreThan(game.getSummoner().getGrimoire().getCost(),0)) {
            m.params.gold.addAmount(-game.getSummoner().getGrimoire().getCost(),0);
            m.params.GRIMOIRE_ATK+=0.2f;
            m.params.save();
        }

        for (Familiar familiar : m.params.familiars) {
            if(Jpize.getHeight() > top_border - (560 + 260 * familiar.getId())*r && 0 < top_border - (560 + 260 * familiar.getId())*r + Jpize.getHeight()) {
                if (!familiar.isUnlocked())
                    batch.color().set(0.5f, 0.5f, 0.5f);
                m.res.hudItemHolder.draw(batch, left_border, top_border - (560 + 260 * familiar.getId())*r, 482*r, 250*r);
                batch.draw(m.res.familiars.get(familiar.getId()).getFrame(0), left_border + 241*r - m.res.familiars.get(familiar.getId()).getFrameWidth()*r, top_border - (490 + 260 * familiar.getId())*r, m.res.familiars.get(familiar.getId()).getFrameWidth() * 2*r, m.res.familiars.get(familiar.getId()).getFrameHeight() * 2*r);
                float text_color = 1f;
                if (!familiar.isUnlocked())
                    text_color = 0.5f;
                texter.drawTextRGB(familiar.getName(), 0.4f, left_border + 10*r, (top_border - (330 + 260 * familiar.getId())*r) / r, false, true, text_color, text_color, text_color);
                texter.drawTextRGB("ATK x " + familiar.getAttackBonus(), 0.35f, left_border + 180*r, (top_border - (530 + 260 * familiar.getId())*r) / r, true, true, text_color, text_color, text_color);
                if (!m.params.gold.isMoreThan(familiar.getCost(),0))
                    text_color = 0.5f;
                else
                    text_color = 1f;
                batch.color().set(text_color, text_color, text_color);
                m.res.hudButton.draw(batch, left_border + 352*r, top_border - (555 + 260 * familiar.getId())*r, 120*r, 50*r);
                texter.drawTextRGB(NumToString.convert(familiar.getCost()), 0.35f, left_border + 412*r, (top_border - (530 + 260 * familiar.getId())*r) / r, true, true, 0.23f * text_color, 0.23f * text_color, 0.23f * text_color);
                batch.color().set(1, 1, 1);
                if(!familiar.isUnlocked())
                    batch.draw(m.res.hudLock, left_border + 241*r - m.res.hudLock.getWidth()*r, top_border - (460 + 260 * familiar.getId())*r, m.res.hudLock.getWidth() * 2*r, m.res.hudLock.getHeight() * 2*r);
                boolean isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(), Jpize.input.getCursorY(),
                        left_border + 352*r, top_border - (555 + 260 * familiar.getId())*r, 120*r, 50*r);
                if (isOnRect && MouseBtn.upAny(MouseBtn.values()) && m.params.gold.isMoreThan(familiar.getCost(),0)) {
                    m.params.gold.addAmount(-familiar.getCost(),0);
                    if(!familiar.isUnlocked())
                        familiar.setUnlocked(true);
                    else
                        familiar.setLevel(familiar.getLevel()+1);
                    m.params.save();
                }
            }
        }
    }

    public void draw_third_page(GameScreen game, TextureBatch batch, Texter texter, float left_border, float top_border, float r) {
        Main m = game.main;
        for (Hero hero : m.params.heroes) {
            if(Jpize.getHeight() > top_border - (250 + 260 * hero.getId())*r && 0 < top_border - (250 + 260 * hero.getId())*r + Jpize.getHeight()) {
                if (!hero.isUnlocked())
                    batch.color().set(0.5f, 0.5f, 0.5f);
                m.res.hudItemHolder.draw(batch, left_border, top_border - (250 + 260 * hero.getId())*r, 482*r, 250*r);
                batch.draw(m.res.heroes.get(hero.getId()+1).getFrame(0), left_border + 241*r - m.res.heroes.get(hero.getId()+1).getFrameWidth()*r, top_border - (180 + 260 * hero.getId())*r, m.res.heroes.get(hero.getId()+1).getFrameWidth() * 2*r, m.res.heroes.get(hero.getId()+1).getFrameHeight() * 2*r);
                float text_color = 1f;
                if (!hero.isUnlocked())
                    text_color = 0.5f;
                texter.drawTextRGB(hero.getName(), 0.4f, left_border + 10*r, (top_border - (20 + 260 * hero.getId())*r) / r, false, true, text_color, text_color, text_color);
                texter.drawTextRGB(hero.getParam(), 0.35f, left_border + 180*r, (top_border - (210 + 260 * hero.getId())*r) / r, true, true, text_color, text_color, text_color);
                if (!m.params.gold.isMoreThan(hero.getCost(),0))
                    text_color = 0.5f;
                else
                    text_color = 1f;
                batch.color().set(text_color, text_color, text_color);
                m.res.hudButton.draw(batch, left_border + 352*r, top_border - (245 + 260 * hero.getId())*r, 120*r, 50*r);
                texter.drawTextRGB(NumToString.convert(hero.getCost()), 0.35f, left_border + 412*r, (top_border - (220 + 260 * hero.getId())*r) / r, true, true, 0.23f * text_color, 0.23f * text_color, 0.23f * text_color);
                batch.color().set(1, 1, 1);
                if(!hero.isUnlocked())
                    batch.draw(m.res.hudLock, left_border + 241*r - m.res.hudLock.getWidth()*r, top_border - (150 + 260 * hero.getId())*r, m.res.hudLock.getWidth() * 2*r, m.res.hudLock.getHeight() * 2*r);
                boolean isOnRect = Intersector.isPointOnRect(Jpize.input.getCursorX(), Jpize.input.getCursorY(),
                        left_border + 352*r, top_border - (245 + 260 * hero.getId())*r, 120*r, 50*r);
                if (isOnRect && MouseBtn.upAny(MouseBtn.values()) && m.params.gold.isMoreThan(hero.getCost(),0)) {
                    m.params.gold.addAmount(-hero.getCost(),0);
                    if(!hero.isUnlocked())
                        hero.setUnlocked(true);
                    else
                        hero.setLevel(hero.getLevel()+1);
                    m.params.save();
                }
            }
        }
    }

//    public void draw_fourth_page(GameScreen game, TextureBatch batch, Texter texter, float left_border, float top_border, float r) {
//        Main m = game.main;
//    }


    public float getOpenRatio() {
        return (0-panel_y)/Jpize.getHeight();
    }
}
