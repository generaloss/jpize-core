package io.gvicst.summoner.config;

import io.gvicst.summoner.utils.Familiar;
import io.gvicst.summoner.utils.Gold;
import io.gvicst.summoner.utils.Hero;
import io.gvicst.summoner.utils.Spell;
import jpize.context.input.Key;
import generaloss.resourceflow.resource.FileResource;
import generaloss.resourceflow.resource.Resource;

import java.util.ArrayList;

public class GameParameters {

    public boolean VSYNC = true;
    public boolean GRIND_MODE = true;
    public float GAME_TIME = 180;
    public float HERO_ATK = 1;
    public float GRIMOIRE_ATK = 1;
    public float SUMMONER_LEVEL = 1;
    public float SUMMONER_HP = 20;
    public float SUMMONER_MAX_HP = 20;
    public float CRT_CHANCE = 1;
    public float CRT_DAMAGE = 1;
    public float SPELLS_CALLDOWN = 1;
    public float SPELLS_DURATION = 0;
    public float UPGRADE_COST = 1;
    public float CHEST_APPEARANCE_CHANCE = 1;
    public float MAILMENS_WAITING_TIME = 1;
    public float LIFETIME_OF_FAMILIARS = 1;

    public float BONUS_GOLD = 0;
    public int REINCARNATIONS = 0;
    public int LEVEL = 1;
    public int SUBLEVEL = 1;
    public int DIAMONDS = 0;

    public Familiar[] familiars;
    public Hero[] heroes;
    public Spell[] spells;

    public Gold gold;

    public String PLAYER_NAME = "Призыватель";
    public boolean LOGGED = false;

    public ArrayList<Integer> army_list;

    private FileResource configResource;
    private final Config config = new Config();

    public void init(){
        configResource = Resource.file("./config.conf");
//        configResource =
        configResource.createWithParents();
        config.load(configResource);

        spells = new Spell[]{
                new Spell(this, 0, "Заклинание усиления атаки"    , "Сила атаки +20%"                     , Key.NUM_1, 10, 15 , 8000),
                new Spell(this, 1, "Заклинание скорости"          , "Скорость призыва +100%"              , Key.NUM_2, 15, 5  , 20000),
                new Spell(this, 2, "Заклинание золотой лихорадки" , "Золото +100%"                        , Key.NUM_3, 15, 15 , 89000),
                new Spell(this, 3, "Заклинание критического удара", "Шанс критического удара +50%"        , Key.NUM_4, 14, 16 , 560000),
                new Spell(this, 4, "Заклинание парада монстров"   , "Продлевает время жизни фамильяров"   , Key.NUM_5, 20, 10 , 4200000),
                new Spell(this, 5, "Заклинание усиления героя"    , "Скорость героя +100%"                , Key.NUM_6, 13, 17 , 34000000)};
        familiars = new Familiar[]{
                new Familiar(0 , "Чертёнок", 1000, true),
                new Familiar(1 , "Мандрагора", 4600),
                new Familiar(2 , "Гоблин", 13000),
                new Familiar(3 , "Грибуля", 28000),
                new Familiar(4 , "Скелет", 59000),
                new Familiar(5 , "Гарпия", 130000),
                new Familiar(6 , "Суккуб", 340000),
                new Familiar(7 , "Вайт", 920000),
                new Familiar(8 , "Орк", 2500000),
                new Familiar(9 , "Ящер", 7100000),
                new Familiar(10, "Лич", 20000000),
                new Familiar(11, "Тролль", 57000000),
                new Familiar(12, "Древесень", 160000000),
        };

        heroes = new Hero[]{
                new Hero(this, 0, "Клерик", 4000, "HP"),
                new Hero(this, 1, "Рыцарь", 12000, "DEF"),
                new Hero(this, 2, "Воин", 30000, "ATK"),
                new Hero(this, 3, "Охотник", 48000, "ATK"),
                new Hero(this, 4, "Колдун", 120000, "ATK"),
                new Hero(this, 5, "Ассасин", 312000, "ATK"),
        };

        gold = new Gold();

        PLAYER_NAME = "Призыватель";

        config.putIfAbsent("game.vsync", VSYNC);
        config.putIfAbsent("game.time", (int)Math.floor((GAME_TIME/15f)%24)+"."+(int)Math.floor(GAME_TIME*4%60));

        config.putIfAbsent("HERO_ATK", HERO_ATK);
        config.putIfAbsent("hero.0.grimoire.attack", GRIMOIRE_ATK);
        config.putIfAbsent("hero.0.level", SUMMONER_LEVEL);
        config.putIfAbsent("hero.0.hp", SUMMONER_HP);
        config.putIfAbsent("hero.0.max_hp", SUMMONER_MAX_HP);
        config.putIfAbsent("hero.0.grimoire.crit_chance", CRT_CHANCE);
        config.putIfAbsent("hero.0.grimoire.crit_damage", CRT_DAMAGE);
        config.putIfAbsent("SPELLS_CALLDOWN", SPELLS_CALLDOWN);
        config.putIfAbsent("SPELLS_DURATION", SPELLS_DURATION);
        config.putIfAbsent("UPGRADE_COST", UPGRADE_COST);
        config.putIfAbsent("CHEST_APPEARANCE_CHANCE", CHEST_APPEARANCE_CHANCE);
        config.putIfAbsent("MAILMENS_WAITING_TIME", MAILMENS_WAITING_TIME);
        config.putIfAbsent("hero.0.grimoire.lifetime", LIFETIME_OF_FAMILIARS);

        config.putIfAbsent("BONUS_GOLD", BONUS_GOLD);
        config.putIfAbsent("game.reincarnations", REINCARNATIONS);
        config.putIfAbsent("game.level", LEVEL);
        config.putIfAbsent("game.sublevel", SUBLEVEL);
        config.putIfAbsent("game.gold_amount", gold.getAmount());
        config.putIfAbsent("game.gold_multiplier", gold.getMultiplier());
        config.putIfAbsent("game.diamonds", DIAMONDS);

        for (Spell spell: spells)
            config.putIfAbsent("spell."+spell.getId()+".unlocked", spell.isUnlocked());

        for (Familiar familiar: familiars) {
            config.putIfAbsent("familiar." + familiar.getId() + ".unlocked", familiar.isUnlocked());
            config.putIfAbsent("familiar." + familiar.getId() + ".level", familiar.getLevel());
        }

        for (Hero hero: heroes) {
            config.putIfAbsent("hero." + (hero.getId()+1) + ".unlocked", hero.isUnlocked());
            config.putIfAbsent("hero." + (hero.getId()+1) + ".level", hero.getLevel());
        }

        army_list = new ArrayList<>();
        updateArmy();

//        config.save(configResource);
//        this.load();
    }

    public void save(){
        config.put("game.vsync", VSYNC);
        config.put("game.time", (int)Math.floor((GAME_TIME/15f)%24)+"."+(int)Math.floor(GAME_TIME*4%60));

        config.put("HERO_ATK", HERO_ATK);
        config.put("hero.0.grimoire.attack", GRIMOIRE_ATK);
        config.put("hero.0.level", SUMMONER_LEVEL);
        config.put("hero.0.hp", SUMMONER_HP);
        config.put("hero.0.max_hp", SUMMONER_MAX_HP);
        config.put("hero.0.grimoire.crit_chance", CRT_CHANCE);
        config.put("hero.0.grimoire.crit_damage", CRT_DAMAGE);
        config.put("SPELLS_CALLDOWN", SPELLS_CALLDOWN);
        config.put("SPELLS_DURATION", SPELLS_DURATION);
        config.put("UPGRADE_COST", UPGRADE_COST);
        config.put("CHEST_APPEARANCE_CHANCE", CHEST_APPEARANCE_CHANCE);
        config.put("MAILMENS_WAITING_TIME", MAILMENS_WAITING_TIME);
        config.put("hero.0.grimoire.lifetime", LIFETIME_OF_FAMILIARS);
        config.put("BONUS_GOLD", BONUS_GOLD);

        config.put("game.reincarnations", REINCARNATIONS);
        config.put("game.level", LEVEL);
        config.put("game.sublevel", SUBLEVEL);
        config.put("game.gold_amount", gold.getAmount());
        config.put("game.gold_multiplier", gold.getMultiplier());
        config.put("game.diamonds", DIAMONDS);

        for (Spell spell: spells)
            config.put("spell."+spell.getId()+".unlocked", spell.isUnlocked());

        for (Familiar familiar: familiars) {
            config.put("familiar." + familiar.getId() + ".unlocked", familiar.isUnlocked());
            config.put("familiar." + familiar.getId() + ".level", familiar.getLevel());
        }

        for (Hero hero: heroes) {
            config.put("hero." + (hero.getId()+1) + ".unlocked", hero.isUnlocked());
            config.put("hero." + (hero.getId()+1) + ".level", hero.getLevel());
        }

        updateArmy();
//        config.save(configResource);
    }

    public void load(){
        config.load(configResource);

        VSYNC = config.getBool("game.vsync");
        GAME_TIME = Float.parseFloat(config.getString("game.time").split("\\.")[0])*15+Float.parseFloat(config.getString("game.time").split("\\.")[1])*0.25f;

        HERO_ATK = config.getFloat("HERO_ATK");
        GRIMOIRE_ATK = config.getFloat("hero.0.grimoire.attack");
        SUMMONER_LEVEL = config.getFloat("hero.0.level");
        SUMMONER_HP = config.getFloat("hero.0.hp");
        CRT_CHANCE = config.getFloat("hero.0.grimoire.crit_chance");
        CRT_DAMAGE = config.getFloat("hero.0.grimoire.crit_damage");
        SPELLS_CALLDOWN = config.getFloat("SPELLS_CALLDOWN");
        SPELLS_DURATION = config.getFloat("SPELLS_DURATION");
        UPGRADE_COST = config.getFloat("UPGRADE_COST");
        CHEST_APPEARANCE_CHANCE = config.getFloat("CHEST_APPEARANCE_CHANCE");
        MAILMENS_WAITING_TIME = config.getFloat("MAILMENS_WAITING_TIME");
        LIFETIME_OF_FAMILIARS = config.getFloat("hero.0.grimoire.lifetime");

        BONUS_GOLD = config.getFloat("BONUS_GOLD");
        REINCARNATIONS = config.getInt("game.reincarnations");
        LEVEL = config.getInt("game.level");
        SUBLEVEL = Math.min(config.getInt("game.sublevel"), 9);
        gold.setMultiplier(config.getInt("game.gold_multiplier"));
        gold.setAmount(config.getFloat("game.gold_amount"));
        DIAMONDS = config.getInt("game.diamonds");

        for (Spell spell: spells)
            spell.setUnlocked(config.getBool("spell."+spell.getId()+".unlocked"));

        for (Familiar familiar: familiars) {
            familiar.setUnlocked(config.getBool("familiar."+familiar.getId()+".unlocked"));
            familiar.setLevel(config.getInt("familiar."+familiar.getId()+".level"));
        }

        for (Hero hero: heroes) {
            hero.setUnlocked(config.getBool("hero."+(hero.getId()+1)+".unlocked"));
            hero.setLevel(config.getInt("hero."+(hero.getId()+1)+".level"));
        }

        updateArmy();
    }

    private void updateArmy() {
        army_list.clear();
        for(Familiar familiar: familiars)
            if(familiar.isUnlocked()) army_list.add(familiar.getId());
    }

}
