package jpize.context.input;

public enum Action {

    DOWN,
    PRESSED,
    UP,
    NONE;

    public boolean isDown() {
        return (this == DOWN);
    }

    public boolean isPressed() {
        return (this == PRESSED || this.isDown());
    }

    public boolean isUp() {
        return (this == UP);
    }

    public boolean isNone() {
        return (this == NONE || this.isUp());
    }

}
