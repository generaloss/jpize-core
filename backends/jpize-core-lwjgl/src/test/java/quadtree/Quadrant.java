package quadtree;

public enum Quadrant {

    RIGHT_UP   ( 1,  1),
    LEFT_UP    (-1,  1),
    LEFT_DOWN  (-1, -1),
    RIGHT_DOWN ( 1, -1);

    public final int signX;
    public final int signY;

    Quadrant(int signX, int signY) {
        this.signX = signX;
        this.signY = signY;
    }

    public Quadrant opposite(boolean axisX, boolean axisY) {
        final int newSignX = this.signX * (axisX ? -1 : 1);
        final int newSignY = this.signY * (axisY ? -1 : 1);
        return bySign(newSignX, newSignY);
    }


    public static Quadrant bySign(int signX, int signY) {
        for(Quadrant quadrant : Quadrant.values())
            if(quadrant.signX == signX && quadrant.signY == signY)
                return quadrant;
        return null;
    }

}
