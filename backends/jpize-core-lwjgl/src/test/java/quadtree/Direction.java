package quadtree;

public enum Direction {

    UP    ( 0,  1),
    LEFT  (-1,  0),
    DOWN  ( 0, -1),
    RIGHT ( 1,  0);

    public final int signX;
    public final int signY;

    Direction(int signX, int signY) {
        this.signX = signX;
        this.signY = signY;
    }

    public Direction opposite() {
        final int newSignX = (this.signX * -1);
        final int newSignY = (this.signY * -1);
        return bySign(newSignX, newSignY);
    }

    public static Direction bySign(int signX, int signY) {
        for(Direction dir : Direction.values())
            if(dir.signX == signX && dir.signY == signY)
                return dir;
        return null;
    }

}
