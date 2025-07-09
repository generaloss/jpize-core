package jpize.util.font;

import generaloss.freetype.freetype.FTLoad;
import generaloss.freetype.freetype.FTLoadTarget;

public enum FontHinting {

    NONE,
	LIGHT       (FTLoadTarget.LIGHT),
	MEDIUM      (FTLoadTarget.NORMAL),
	FULL        (FTLoadTarget.MONO),
	AUTO_LIGHT  (FTLoadTarget.LIGHT,  FTLoad.FORCE_AUTOHINT),
	AUTO_MEDIUM (FTLoadTarget.NORMAL, FTLoad.FORCE_AUTOHINT),
	AUTO_FULL   (FTLoadTarget.MONO,   FTLoad.FORCE_AUTOHINT);

    public final int loadFlags;

    FontHinting() {
        this.loadFlags = FTLoad.NO_HINTING.getBit();
    }

    FontHinting(FTLoadTarget target) {
        this.loadFlags = target.value;
    }

    FontHinting(FTLoadTarget target, FTLoad flag) {
        this.loadFlags = (target.value | flag.getBit());
    }

}
