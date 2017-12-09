import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class TrainsUnitTest {
    static TrainsRoutesMain graph;
    static CityNode a, b, c, d, e;

    /**
     * 初始化图
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass()  {
        graph = new TrainsRoutesMain(); //Build graph

        a = new CityNode("A");
        b = new CityNode("B");
        c = new CityNode("C");
        d = new CityNode("D");
        e = new CityNode("E");

		/*Input given in programming challenge
		Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7*/
        graph.routeTable.put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
        graph.routeTable.put(b, new Edge(b, c, 4));
        graph.routeTable.put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
        graph.routeTable.put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
        graph.routeTable.put(e, new Edge(e, b, 3));
    }

    /**
     * The distance of the route A-B-C
     * 问题1测试方法
     * 求路线ABC之间距离
     * @throws Exception
     */
    @Test
    public void testAOne_ABC()  {
        ArrayList<CityNode> route = new ArrayList<CityNode>();
        route.add(a);
        route.add(b);
        route.add(c);
        try {
            System.out.println("Output #1: "+graph.distanceBetween(route));
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
    }

    /**
     *The distance of the route A-D
     * 问题2测试方法
     * 求AD之间距离
     * @throws Exception
     */
    @Test
    public void testBTwo_AD()  {
        ArrayList<CityNode> route = new ArrayList<CityNode>();
        route.add(a);
        route.add(d);
        try {
            System.out.println("Output #2: "+graph.distanceBetween(route));
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }

    }
    /**
     *The distance of the route A-D-C.
     * 问题3测试方法
     * 求ADC路线之间距离
     * @throws Exception
     */
    @Test
    public void testCThree_ADC()   {
        ArrayList<CityNode> route = new ArrayList<CityNode>();
        route.add(a);
        route.add(d);
        route.add(c);
        try {
            System.out.println("Output #3: "+graph.distanceBetween(route));
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
    }

    /**
     *The distance of the route A-E-B-C-D.
     * 问题4测试方法
     * 求 A-E-B-C-D路线之间距离
     * @throws Exception
     */
    @Test
    public void testDFour_AEBCD() {
        ArrayList<CityNode> route = new ArrayList<CityNode>();
        route.add(a);
        route.add(e);
        route.add(b);
        route.add(c);
        route.add(d);
        try {
            System.out.println("Output #4: "+graph.distanceBetween(route));
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
    }
    /**
     *The distance of the route A-E-D..
     * 问题5测试方法
     * 求 A-E-D路线之间距离
     * @throws Exception
     */
    @Test
    public void testEFive_AED() {
        ArrayList<CityNode> route = new ArrayList<CityNode>();
        route.add(a);
        route.add(e);
        route.add(d);
        try {
            graph.distanceBetween(route);
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }

    }
    /**
     *The number of trips starting at C and ending at C with a maximum of 3 stops.
     * In the sample data below, there are two such trips: C-D-C (2 stops).
     * and C-E-B-C (3 stops).
     * 问题6测试方法
     * 从C出发回到C且最大停靠站只有三站共有几种路线
     * @throws Exception
     */
    @Test
    public void testFSix_CC3()  {
        int numStops = 0;
        try {
            numStops = graph.numStops(c, c, 3);
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
        System.out.println("Output #6: "+numStops);
    }
    /**
     *The number of trips starting at A and ending at C with exactly 4 stops.
     * In the sample data below,
     * there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B)
     * 问题7测试方法
     * 从A到C恰好有4站的路线
     * @throws Exception
     */
    @Test
    public void testGSeven_AC4()  {
        int numStops = 0;
        try {
            numStops = graph.numStops(a, c, 4);
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
        System.out.println("Output #7: "+numStops);
    }
    /**
     *The length of the shortest route (in terms of distance to travel) from A to C.
     * 问题8测试方法
     * A到C的最短距离
     * @throws Exception
     */

    @Test
    public void testHEight_AC()  {
        int shortestRoute = 0;
        try {
            shortestRoute = graph.shortestRoute(a, c);
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
        System.out.println("Output #8: "+shortestRoute);

    }
    /**
     *The length of the shortest route (in terms of distance to travel) from B to B.
     * 问题9测试方法
     * 从B出发返回B的最短距离
     * @throws Exception
     */

    @Test
    public void testINine_BB()  {
        int shortestRoute = 0;
        try {
            shortestRoute = graph.shortestRoute(b, b);
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
        System.out.println("Output #9: "+shortestRoute);
    }

    /**
     *The number of different routes from C to C with a distance of less than 30.
     * In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC
     * 问题10测试方法
     * 从C出发返回到C距离小于30的路线
     * @throws Exception
     */
    @Test
    public void testJTen_CC30()  {
        int numRoutesWithin = 0;
        try {
            numRoutesWithin = graph.numRoutesWithin(c, c, 30);
        } catch (NoRouterException e1) {
            e1.printStackTrace();
        }
        System.out.println("Output #10: "+numRoutesWithin);
    }

}