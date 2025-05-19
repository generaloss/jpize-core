package io.gvicst.summoner.utils;

import jpize.context.Jpize;
import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.mesh.RectBatch;

public class Texter {

    private final Font font;
    private final FontRenderOptions font_r;
    private final RectBatch batch;

    public Texter(Font font, RectBatch batch){
        this.font = font;
        this.font_r = font.getOptions();
        this.batch = batch;
    }

    public void drawText(String text, float size, float x, float y, boolean align, boolean valign){
        float ratio = (Jpize.getHeight()/960f);
        font_r.scale().set(size*ratio);
        font_r.color().set(1,1,1);
        font.drawText(batch, text, x - (align?font.getTextWidth(text)/2f:0), y*ratio-(valign?font.getLineAdvanceScaled()/2f:0));
    }

    public void drawTextRGB(String text, float size, float x, float y, boolean align, boolean valign, float r, float g, float b){
        float ratio = (Jpize.getHeight()/960f);
        font_r.scale().set(size*ratio);
        font_r.color().set(r,g,b);
        font.drawText(batch, text, x - (align?font.getTextWidth(text)/2f:0), y*ratio-(valign?font.getLineAdvanceScaled()/2f:0));
        font_r.color().set(1,1,1);
    }

    public void drawTextRGBA(String text, float size, float x, float y, boolean align, boolean valign, float r, float g, float b, float a){
        float ratio = (Jpize.getHeight()/960f);
        font_r.scale().set(size*ratio);
        font_r.color().set(r,g,b,a);
        font.drawText(batch, text, x - (align?font.getTextWidth(text)/2f:0), y*ratio-(valign?font.getLineAdvanceScaled()/2f:0));
        font_r.color().set(1,1,1,1);
    }

}
