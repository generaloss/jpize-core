package jpize.context.input;

public enum ConnectEvent {

    CONNECTED,
    DISCONNECTED;

    public boolean isConnected() {
        return (this == CONNECTED);
    }

}
