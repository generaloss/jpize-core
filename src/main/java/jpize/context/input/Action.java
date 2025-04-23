package jpize.context.input;

// per-frame key/button action
public enum Action {

    DOWN,
    PRESSED,
    UP,
    NOT_PRESSED;

    public boolean isDown() {
        return (this == DOWN);
    }

    public boolean isPressed() {
        return (this == PRESSED || this.isDown());
    }

    public boolean isUp() {
        return (this == UP);
    }

    public boolean isNotPressed() {
        return (this == NOT_PRESSED || this.isUp());
    }

}
