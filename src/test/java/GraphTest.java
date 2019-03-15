import org.junit.Assert;
import org.junit.Test;
import pojo.Edge;
import pojo.TownsNode;

import java.util.HashMap;
import java.util.Map;

public class GraphTest {


    @Test
    public void testInitGraph() throws Exception {
        HashMap<TownsNode, Edge> table = Graph.getGraph().getRouteTable();
        Edge edgeAB = new Edge(new TownsNode("A"),new TownsNode("B"),5);
        Edge edgeAE = new Edge(new TownsNode("A"),new TownsNode("E"),7);
        Edge edgeAD = new Edge(new TownsNode("A"),new TownsNode("D"),5);
        Edge edgeBC = new Edge(new TownsNode("B"),new TownsNode("C"),4);
        Edge edgeCD = new Edge(new TownsNode("C"),new TownsNode("D"),8);
        Edge edgeCE = new Edge(new TownsNode("C"),new TownsNode("E"),2);
        Edge edgeDC = new Edge(new TownsNode("D"),new TownsNode("C"),8);
        Edge edgeDE = new Edge(new TownsNode("D"),new TownsNode("E"),5);
        Edge edgeEB = new Edge(new TownsNode("E"),new TownsNode("B"),3);
        table.forEach((k,v) -> {
            if(("A").equals(k.name)){
                Edge a = edgeAB.next(edgeAD.next(edgeAE));
                Assert.assertEquals(a,v);
            }

        });
    }

}