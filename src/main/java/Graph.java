import java.util.Hashtable;

/**
 * 用单例的方式创建图
 */
public class Graph {
    public Hashtable<TownsNode, Edge> routeTable;



    private static Graph graph=null;

    private Graph() {
        this.routeTable = new Hashtable<TownsNode, Edge>();
    }

    public static Graph getInstance() {
        if (graph == null) {
            graph = new Graph();
        }
        return graph;
    }
}
