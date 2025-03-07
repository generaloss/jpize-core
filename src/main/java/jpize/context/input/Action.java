package jpize.context.input;

public enum Action {

    RELEASE,
    PRESS,
    REPEAT;

    public boolean isDown() {
        return (this == PRESS);
    }

    public boolean isPressed() {
        return (this != RELEASE);
    }

    public boolean isRelease() {
        return (this == RELEASE);
    }

}
