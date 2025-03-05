package jpize.io.callback;

import jpize.io.input.ConnectEvent;

public interface IMonitorCallback {

    void invoke(ConnectEvent event);

}
