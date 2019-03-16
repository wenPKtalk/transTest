import exception.NoSuchRouterException;
import org.junit.Assert;
import org.junit.Test;
import pojo.TownsNode;

import java.util.ArrayList;


public class TrainsUnitTest {


    /**
     * The distance of the route A-B-C
     */
    @Test
    public void testAOne_ABC() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphRouter graph = new GraphRouter();
        route.add(new TownsNode("A"));
        route.add(new TownsNode("B"));
        route.add(new TownsNode("C"));
        Assert.assertEquals(9, graph.distanceBetween(route));
    }

    /**
     * The distance of the route A-D
     */
    @Test
    public void testBTwo_AD() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphRouter graph = new GraphRouter();
        route.add(new TownsNode("A"));
        route.add(new TownsNode("B"));
        Assert.assertEquals(5, graph.distanceBetween(route));

    }

    /**
     * The distance of the route A-D-C.
     */
    @Test
    public void testCThree_ADC() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphRouter graph = new GraphRouter();

        route.add(new TownsNode("A"));
        route.add(new TownsNode("D"));
        route.add(new TownsNode("C"));
        Assert.assertEquals(13, graph.distanceBetween(route));
    }

    /**
     * The distance of the route A-E-B-C-D.
     */
    @Test
    public void testDFour_AEBCD() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphRouter graph = new GraphRouter();

        route.add(new TownsNode("A"));
        route.add(new TownsNode("E"));
        route.add(new TownsNode("B"));
        route.add(new TownsNode("C"));
        route.add(new TownsNode("D"));
        Assert.assertEquals(22, graph.distanceBetween(route));
    }

    /**
     * The distance of the route A-E-D..
     */
    @Test(expected = NoSuchRouterException.class)
    public void testEFive_AED() throws Exception {
        ArrayList<TownsNode> route = new ArrayList<TownsNode>();
        GraphRouter graph = new GraphRouter();
        route.add(new TownsNode("A"));
        route.add(new TownsNode("E"));
        route.add(new TownsNode("D"));
        graph.distanceBetween(route);

    }

    /**
     * The number of trips starting at C and ending at C with a maximum of 3 stops.
     * In the sample data below, there are two such trips: C-D-C (2 stops).
     */
    @Test
    public void testFSix_CC3() throws Exception {
        int numStops = 0;
        GraphRouter graph = new GraphRouter();
        numStops = graph.numStops(new TownsNode("C"), new TownsNode("C"), 3);
        Assert.assertEquals(2, numStops);
    }

    /**
     * The number of trips starting at A and ending at C with exactly 4 stops.
     */
    @Test
    public void testGSeven_AC4() throws Exception {
        int numStops = 0;
        GraphRouter graph = new GraphRouter();
        numStops = graph.numStops(new TownsNode("A"), new TownsNode("C"), 4);
        Assert.assertEquals(4, numStops);
    }

    /**
     * The length of the shortest route (in terms of distance to travel) from A to C.
     */

    @Test
    public void testHEight_AC() throws Exception {
        int shortestRoute = 0;
        GraphRouter graph = new GraphRouter();
        shortestRoute = graph.shortestRoute(new TownsNode("A"), new TownsNode("C"));
        Assert.assertEquals(9, shortestRoute);

    }

    /**
     * The length of the shortest route (in terms of distance to travel) from B to B.
     */

    @Test
    public void testINine_BB() throws Exception {
        int shortestRoute = 0;
        GraphRouter graph = new GraphRouter();
        shortestRoute = graph.shortestRoute(new TownsNode("B"), new TownsNode("B"));
        Assert.assertEquals(9, shortestRoute);
    }

    /**
     * The number of different routes from C to C with a distance of less than 30.
     * In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC
     */
    @Test
    public void testJTen_CC30() throws Exception {
        int numRoutesWithin = 0;
        GraphRouter graph = new GraphRouter();
        numRoutesWithin = graph.numRoutesWithin(new TownsNode("C"), new TownsNode("C"), 30);
        Assert.assertEquals(7, numRoutesWithin);
    }

}