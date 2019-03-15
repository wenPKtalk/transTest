package pojo;

import java.util.Objects;

/**
 * Created by wenpengkun on 2017/12/09.
 */
public class Edge {

    public TownsNode origin;  //源城市节点
    public TownsNode destination; //目的地城市节点
    public int weight;//距离
    public Edge next;  //下个的路线

    /**
     * 构造路线
     * @param origin
     * @param destination
     * @param weight
     */
    public Edge(TownsNode origin, TownsNode destination, int weight) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                Objects.equals(origin, edge.origin) &&
                Objects.equals(destination, edge.destination) &&
                Objects.equals(next, edge.next);
    }

    @Override
    public int hashCode() {

        return Objects.hash(origin, destination, weight, next);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", weight=" + weight +
                ", next=" + next +
                '}';
    }
}
