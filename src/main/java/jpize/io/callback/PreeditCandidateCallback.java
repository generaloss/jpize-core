package jpize.io.callback;

public interface PreeditCandidateCallback {

    void invoke(int candidatesCount, int selectedIndex, int pageStart, int pageSize);

}
