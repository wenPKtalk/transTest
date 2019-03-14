import exception.NoRouterException;
import org.junit.Assert;
import org.junit.Test;
import pojo.TownsNode;

import java.util.ArrayList;


public class TrainsUnitTest {


    /**
     * The distance of the route A-B-C
     * 问题1测试方法
     * 求路线ABC之间距离
     *
     * @throws Exception
     */
    @Test
    public void testAOne_ABC() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        route.add(new TownsNode("A"));
        route.add(new TownsNode("B"));
        route.add(new TownsNode("C"));
        Assert.assertEquals(9, graph.distanceBetween(route));
    }

    /**
     * The distance of the route A-D
     * 问题2测试方法
     * 求AD之间距离
     *
     * @throws Exception
     */
    @Test
    public void testBTwo_AD() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        route.add(new TownsNode("A"));
        route.add(new TownsNode("B"));
        Assert.assertEquals(5, graph.distanceBetween(route));

    }

    /**
     * The distance of the route A-D-C.
     * 问题3测试方法
     * 求ADC路线之间距离
     *
     * @throws Exception
     */
    @Test
    public void testCThree_ADC() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphDecorator graph = new GraphRouter(Graph.getGraph());

        route.add(new TownsNode("A"));
        route.add(new TownsNode("D"));
        route.add(new TownsNode("C"));
        Assert.assertEquals(13, graph.distanceBetween(route));
    }

    /**
     * The distance of the route A-E-B-C-D.
     * 问题4测试方法
     * 求 A-E-B-C-D路线之间距离
     *
     * @throws Exception
     */
    @Test
    public void testDFour_AEBCD() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphDecorator graph = new GraphRouter(Graph.getGraph());

        route.add(new TownsNode("A"));
        route.add(new TownsNode("E"));
        route.add(new TownsNode("B"));
        route.add(new TownsNode("C"));
        route.add(new TownsNode("D"));
        Assert.assertEquals(22, graph.distanceBetween(route));
    }

    /**
     * The distance of the route A-E-D..
     * 问题5测试方法
     * 求 A-E-D路线之间距离
     *
     * @throws Exception
     */
    @Test(expected = NoRouterException.class)
    public void testEFive_AED() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        route.add(new TownsNode("A"));
        route.add(new TownsNode("E"));
        route.add(new TownsNode("D"));
        graph.distanceBetween(route);

    }

    /**
     * The number of trips starting at C and ending at C with a maximum of 3 stops.
     * In the sample data below, there are two such trips: C-D-C (2 stops).
     * and C-E-B-C (3 stops).
     * 问题6测试方法
     * 从C出发回到C且最大停靠站只有三站共有几种路线
     *
     * @throws Exception
     */
    @Test
    public void testFSix_CC3() throws Exception {
        int numStops = 0;
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        numStops = graph.numStops(new TownsNode("C"), new TownsNode("C"), 3);
        Assert.assertEquals(2, numStops);
    }

    /**
     * The number of trips starting at A and ending at C with exactly 4 stops.
     * In the sample data below,
     * there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B)
     * 问题7测试方法
     * 从A到C恰好有4站的路线
     *
     * @throws Exception
     */
    @Test
    public void testGSeven_AC4() throws Exception {
        int numStops = 0;
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        numStops = graph.numStops(new TownsNode("A"), new TownsNode("C"), 4);
        Assert.assertEquals(4, numStops);
    }

    /**
     * The length of the shortest route (in terms of distance to travel) from A to C.
     * 问题8测试方法
     * A到C的最短距离
     *
     * @throws Exception
     */

    @Test
    public void testHEight_AC() throws Exception {
        int shortestRoute = 0;
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        shortestRoute = graph.shortestRoute(new TownsNode("A"), new TownsNode("C"));
        Assert.assertEquals(9, shortestRoute);

    }

    /**
     * The length of the shortest route (in terms of distance to travel) from B to B.
     * 问题9测试方法
     * 从B出发返回B的最短距离
     *
     * @throws Exception
     */

    @Test
    public void testINine_BB() throws Exception {
        int shortestRoute = 0;
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        shortestRoute = graph.shortestRoute(new TownsNode("B"), new TownsNode("B"));
        Assert.assertEquals(9, shortestRoute);
    }

    /**
     * The number of different routes from C to C with a distance of less than 30.
     * In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC
     * 问题10测试方法
     * 从C出发返回到C距离小于30的路线
     *
     * @throws Exception
     */
    @Test
    public void testJTen_CC30() throws Exception {
        int numRoutesWithin = 0;
        GraphDecorator graph = new GraphRouter(Graph.getGraph());
        numRoutesWithin = graph.numRoutesWithin(new TownsNode("C"), new TownsNode("C"), 30);
        Assert.assertEquals(7, numRoutesWithin);
    }

}