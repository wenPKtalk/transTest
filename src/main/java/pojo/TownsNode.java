package pojo;

import java.util.Objects;

/**
 * Created by wenpengkun on 2017/12/09.
 */
public class TownsNode {
    public String name;
    public boolean visited;

    public TownsNode(String name) {
        this.name = name;
        this.visited = false;
    }

    @Override
    public String toString() {
        return "TownsNode{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TownsNode townsNode = (TownsNode) o;
        return Objects.equals(name, townsNode.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
