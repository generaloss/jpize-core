package io.gvicst.summoner.utils;

import jpize.opengl.texture.Texture2D;
import jpize.util.ninepatch.NinePatch;

import java.util.ArrayList;

public class Resources {

    public NinePatch hudSpellHolder;
    public NinePatch hudInfoHolder;
    public NinePatch hudInterfaceHolder;
    public NinePatch hudItemHolder;
    public NinePatch hudButton;
    public NinePatch hudButtonQuit;
    public NinePatch hudButtonRestart;
    public NinePatch hudButtonUpgradeOff;
    public NinePatch hudButtonUpgradeOn;
    public NinePatch hudUpgradePages;
    public Texture2D hudGrimoire;
    public Texture2D hudGrimoireCircle;
    public Texture2D hudLock;
    public Texture2D bgGrimoireCircle;
    public Texture2D logoMainMenu;
    public Texture2D bgSky;
    public Texture2D bgGround;
    public Texture2D[] boosters;
    public ArrayList<Animation> heroes;
    public Animation summoner, healer, knight, warrior, hunter, wizard, assassin;
    public Animation imp, mandrade, goblin, fungus, skeleton, harpy, succubus, wight, orc, lizard, lich, troll, longtree;
    public ArrayList<Animation> familiars;
    public ArrayList<Texture2D> enemies, bosses;
    public Texture2D treasureChest;
    public Animation small_explotano;
    public Animation large_explotano;
    public ArrayList<Animation> explotano;
//     public AlMusic musicMainMenu;
    // public AlMusic musicGame;
    // public AlSound soundStartGame;
    // public ArrayList<AlMusic> music;
    // public ArrayList<AlSound> sound;


    public Resources(){
        // textures
        hudSpellHolder = new NinePatch();
        hudInterfaceHolder = new NinePatch();
        hudItemHolder = new NinePatch();
        hudInfoHolder = new NinePatch();
        hudGrimoire = new Texture2D();
        hudGrimoireCircle = new Texture2D();
        hudButton = new NinePatch();
        hudButtonQuit = new NinePatch();
        hudButtonRestart = new NinePatch();
        hudButtonUpgradeOff = new NinePatch();
        hudButtonUpgradeOn = new NinePatch();
        hudUpgradePages = new NinePatch();
        hudLock = new Texture2D();
        bgGrimoireCircle = new Texture2D();
        logoMainMenu = new Texture2D();
        bgSky = new Texture2D();
        bgGround = new Texture2D();

        boosters = new Texture2D[]{
                new Texture2D("/assets/textures/hud/game/boost_atk.png"),
                new Texture2D("/assets/textures/hud/game/boost_speed.png"),
                new Texture2D("/assets/textures/hud/game/boost_gold.png"),
                new Texture2D("/assets/textures/hud/game/boost_crt.png"),
                new Texture2D("/assets/textures/hud/game/boost_life.png"),
                new Texture2D("/assets/textures/hud/game/boost_hero.png")};

        imp = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/imp.png", 4, 3, 10);
        mandrade = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/mandrade.png", 4, 4, 16);
        goblin = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/goblin.png", 4, 4, 15);
        fungus = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/fungus.png", 5, 4, 18);
        skeleton = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/skeleton.png", 3, 3, 8);
        harpy = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/harpy.png", 4, 3, 11);
        succubus = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/succubus.png", 6, 6, 32);
        wight = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/wight.png", 4, 4, 15);
        orc = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/orc.png", 6, 6, 33);
        lizard = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/lizard.png", 3, 3, 8);
        lich = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/lich.png", 5, 5, 20);
        troll = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/troll.png", 5, 5, 24);
        longtree = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/army/longtree.png", 4, 4, 10);

        summoner = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/summoner.png", 28, 1, 28);
        healer = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/healer.png",   4, 3, 12);
        knight = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/knight.png",   3, 3, 9);
        warrior = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/warrior.png",  4, 3, 12);
        hunter = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/hunter.png",   4, 3, 12);
        wizard = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/wizard.png",   4, 3, 11);
        assassin = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/entity/hero/assassin.png",  4, 3, 11);

        small_explotano = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/explotanos/small_explotano.png", 3, 3, 8);
        large_explotano = new Animation(0.5f, Animation.Mode.LOOP, "/assets/textures/explotanos/large_explotano.png", 2, 4, 8);

        explotano = new ArrayList<>();
        explotano.add(0, small_explotano);
        explotano.add(1, small_explotano);
        explotano.add(2, small_explotano);
        explotano.add(3, small_explotano);
        explotano.add(4, small_explotano);
        explotano.add(5, small_explotano);
        explotano.add(6, small_explotano);
        explotano.add(7, small_explotano);
        explotano.add(8, large_explotano);
        explotano.add(9, large_explotano);
        explotano.add(10, small_explotano);
        explotano.add(11, large_explotano);
        explotano.add(12, large_explotano);
        familiars = new ArrayList<>();
        familiars.add(0, imp);
        familiars.add(1, mandrade);
        familiars.add(2, goblin);
        familiars.add(3, fungus);
        familiars.add(4, skeleton);
        familiars.add(5, harpy);
        familiars.add(6, succubus);
        familiars.add(7, wight);
        familiars.add(8, orc);
        familiars.add(9, lizard);
        familiars.add(10, lich);
        familiars.add(11, troll);
        familiars.add(12, longtree);
        heroes = new ArrayList<>();
        heroes.add(0, summoner);
        heroes.add(1, healer);
        heroes.add(2, knight);
        heroes.add(3, warrior);
        heroes.add(4, hunter);
        heroes.add(5, wizard);
        heroes.add(6, assassin);

        enemies = new ArrayList<>();
        for (int i = 1; i < 106; i++) {
            enemies.add(i-1, new Texture2D("/assets/textures/entity/enemy/"+i+".png"));
        bosses = new ArrayList<>();
        }for (int i = 1; i < 30; i++) {
            bosses.add(i-1, new Texture2D("/assets/textures/entity/boss/"+i+".png"));
        }

        treasureChest = new Texture2D("/assets/textures/entity/enemy/0.png");

        // music = new ArrayList<>();
        // musicMainMenu = new AlMusic("/audio/music/main_menu.ogg");
        // musicMainMenu.setLooping(true);
        // music.add(musicMainMenu);
        // musicGame = new AlMusic("/audio/music/game.ogg");
        // musicGame.setLooping(true);
        // music.add(musicGame);

        // soundStartGame = new AlSound("/audio/sound/start_game.ogg");
        // soundStartGame.setLooping(false);

        // sound = new ArrayList<>();
        // sound.add(soundStartGame);
    }

    public void load(){
        // textures
        hudSpellHolder.load("/assets/textures/hud/game/spell_holder.9.png");
        hudInfoHolder.load("/assets/textures/hud/game/info_holder.9.png");
        hudInterfaceHolder.load("/assets/textures/hud/game/interface_holder.9.png");
        hudItemHolder.load("/assets/textures/hud/game/item_holder.9.png");
        hudGrimoire.setImage("/assets/textures/hud/game/grimoire.png");
        hudGrimoireCircle.setImage("/assets/textures/hud/game/grimoire_circle.png");
        hudButton.load("/assets/textures/hud/game/button.9.png");
        hudButtonQuit.load("/assets/textures/hud/game/button_quit.9.png");
        hudButtonRestart.load("/assets/textures/hud/game/button_restart.9.png");
        hudButtonUpgradeOff.load("/assets/textures/hud/game/button_upgrade_off.9.png");
        hudButtonUpgradeOn.load("/assets/textures/hud/game/button_upgrade_on.9.png");
        hudUpgradePages.load("/assets/textures/hud/game/upgrade_pages.9.png");
        hudLock.setImage("/assets/textures/hud/game/lock.png");
        logoMainMenu.setImage("/assets/textures/hud/main_menu/logo.png");

        bgSky.setImage("/assets/textures/background/sky.png");
        bgGround.setImage("/assets/textures/background/ground.png");
        bgGrimoireCircle.setImage("/assets/textures/hud/game/grimoire_circle_background.png");

    }

}
