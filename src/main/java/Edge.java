/**
 * Created by wenpengkun on 2017/12/09.
 */
public class Edge {

    public CityNode origin;  //源城市节点
    public CityNode destination; //目的地城市节点
    public int weight;//距离
    public Edge next;  //下个可能的路线

    /**
     * 构造路线
     * @param origin
     * @param destination
     * @param weight
     */
    public Edge(CityNode origin, CityNode destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.next = null;
    }

    /**
     * 下个可能的路线
     * @param edge
     * @return
     */
    public Edge next(Edge edge) {
        this.next = edge;
        return this;
    }
}
