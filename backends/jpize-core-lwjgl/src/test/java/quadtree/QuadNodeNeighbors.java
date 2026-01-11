package quadtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuadNodeNeighbors {
    
    private static final int GROUPS_NUM = (Direction.values().length); // 4
    
    private final List<QuadNode>[] neighborGroups;
    
    @SuppressWarnings("unchecked")
    public QuadNodeNeighbors() {
        this.neighborGroups = new List[GROUPS_NUM];
        for(int i = 0; i < GROUPS_NUM; i++) 
            this.neighborGroups[i] = new ArrayList<>();
    }
    
    public void addNeighbor(Direction dir, QuadNode node) {
        final int groupIndex = dir.ordinal();
        final List<QuadNode> neighbors = neighborGroups[groupIndex];
        neighbors.add(node);
    }

    public void addNeighbors(Direction dir, Collection<QuadNode> nodes) {
        final int groupIndex = dir.ordinal();
        final List<QuadNode> neighbors = neighborGroups[groupIndex];
        neighbors.addAll(nodes);
    }
    
    public Collection<QuadNode> getNeighbors(Direction dir) {
        final int groupIndex = dir.ordinal();
        return neighborGroups[groupIndex];
    }

    public void clear() {
        for(List<QuadNode> neighborGroup : neighborGroups)
            neighborGroup.clear();
    }
    
}
