/**
 * Created by wenpengkun on 2017/12/09.
 */
public class CityNode {
    public String name;
    public boolean visited;

    public CityNode(String name) {
        this.name = name;
        this.visited = false;
    }

    @Override
    public boolean equals(Object b) {
        if (b == null || b.getClass() != getClass()) {
            return false;
        }
        CityNode bx = (CityNode)b;
        return this.name.equals(bx.name);
    }

    @Override
    public int hashCode() {
        if(this.name == null) return 0;
        return this.name.hashCode();
    }
}
