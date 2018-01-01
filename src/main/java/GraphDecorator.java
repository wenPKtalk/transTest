import java.util.ArrayList;
import java.util.Hashtable;

public abstract class GraphDecorator implements GraphInter {
    private GraphInter graph;

    public GraphDecorator(GraphInter _graph){
        this.graph = _graph;
    }
    public abstract int distanceBetween(ArrayList<TownsNode> cities) throws NoRouterException;

    public abstract int numStops(TownsNode start, TownsNode end, int maxStops)
            throws NoRouterException;

    public abstract int shortestRoute(TownsNode start, TownsNode end) throws NoRouterException;

    public abstract int numRoutesWithin(TownsNode start, TownsNode end, int maxDistance)
            throws NoRouterException;

}
