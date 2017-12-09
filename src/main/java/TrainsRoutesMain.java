import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by wenpengkun on 2017/12/09.
 */
public class TrainsRoutesMain {
    public Hashtable<CityNode, Edge> routeTable;

    public TrainsRoutesMain() {
        this.routeTable = new Hashtable<CityNode, Edge>();
    }

    /**
     * 给出节点计算之间的距离
     *
     * @param cities
     * @return
     * @throws Exception
     */
    public int distanceBetween(ArrayList<CityNode> cities) throws NoRouterException {

        if (cities.size() < 2) {
            //如果给出的节点个数小于2直接返回0
            return 0;
        }

        int distance, depth, i;
        distance = depth = i = 0;

		/* For each city in the list,
         * we check if entry exists in our
		 * hash table.
		 */
        /**
         * 检查list中的节点检查是否存在于创建的图表中
         */
        while (i < cities.size() - 1) {
            if (this.routeTable.containsKey(cities.get(i))) {
                Edge route = this.routeTable.get(cities.get(i));
				/*If key exists, we check if route from key to next
				 * city exists. We add the distance, and maintain a
				 * depth count
				 */
                //存在：采用深度优先算法遍历节点并且权值相加
                while (route != null) {
                    if (route.destination.equals(cities.get(i + 1))) {
                        distance += route.weight;
                        depth++;
                        break;
                    }
                    route = route.next;
                }
            } else {
                //找不到对应路线
                throw new NoRouterException("NO SUCH ROUTE");
            }
            i++;
        }
		/*If edge depth is not equal to vertex - 1,
		 * then it is safe to assume that one ore more
		 * routes do not exist
		 */
        //遍历深度不等于城市节点长度减一则路线不存在
        if (depth != cities.size() - 1) {
            throw new NoRouterException("NO SUCH ROUTE");
        }
        return distance;
    }

    /*
     * Number of stops;
     * Wrapper for recursive function
     */
    public int numStops(CityNode start, CityNode end, int maxStops) throws NoRouterException {
        //Wrapper to maintain depth of traversal

        return findRoutes(start, end, 0, maxStops);
    }

    /*
     * Finds number of stops from start to end,
     * with a maximum of maxStops and the depth
     * limit.
     */

    /**
     * 给定起点和终点查找总共存在多少站
     * 并且用最大站点和遍历递归深度进行约束
     * @param start
     * @param end
     * @param depth
     * @param maxStops
     * @return
     * @throws NoRouterException
     */
    private int findRoutes(CityNode start, CityNode end, int depth, int maxStops) throws NoRouterException {
        int routes = 0;
        //Check if start and end nodes exists in route table
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			/*
			 * If start node exists then traverse all possible
			 * routes and for each, check if it is destination
			 * If destination, and number of stops within
			 * allowed limits, count it as possible route.
			 */
            depth++;
            if (depth > maxStops) {
                return 0;
            }        //Check if depth level is within limits
            start.visited = true;        //Mark start node as visited
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
				/* If destination matches, we increment route
				 * count, then continue to next node at same depth
				 */
                if (edge.destination.equals(end)) {
                    routes++;
                    edge = edge.next;
                    continue;
                } else if (!edge.destination.visited) {
                    // If destination does not match, and
                    // destination node has not yet been visited,
                    // we recursively traverse destination node
                    routes += findRoutes(edge.destination, end, depth, maxStops);
                    depth--;
                }
                edge = edge.next;
            }
        } else {
            throw new NoRouterException("NO SUCH ROUTE");
        }

		/*
		 * Before exiting this recursive stack level,
		 * we mark the start node as visited.
		 */
        start.visited = false;
        return routes;
    }

    /*
     * Shortest route;
     * Wrapper for recursive function
     */
    public int shortestRoute(CityNode start, CityNode end) throws NoRouterException {
        //Wrapper to maintain weight
        return findShortestRoute(start, end, 0, 0);

    }

    /*
     * Finds the shortest route between two nodes
     */
    private int findShortestRoute(CityNode start, CityNode end, int weight, int shortestRoute) throws NoRouterException {
        //Check if start and end nodes exists in route table
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			/*
			 * If start node exists then traverse all possible
			 * routes and for each, check if it is destination
			 */
            start.visited = true;        //Mark start node as visited
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                //If node not already visited, or is the destination, increment weight
                if (edge.destination == end || !edge.destination.visited)
                    weight += edge.weight;

				/* If destination matches, we compare
				 * weight of this route to shortest route
				 * so far, and make appropriate switch
				 */
                if (edge.destination.equals(end)) {
                    if (shortestRoute == 0 || weight < shortestRoute)
                        shortestRoute = weight;
                    start.visited = false;
                    return shortestRoute;            //Unvisit node and return shortest route
                }
				/* If destination does not match, and
				 * destination node has not yet been visited,
				 * we recursively traverse destination node
				 */
                else if (!edge.destination.visited) {
                    shortestRoute = findShortestRoute(edge.destination, end, weight, shortestRoute);
                    //Decrement weight as we backtrack
                    weight -= edge.weight;
                }
                edge = edge.next;
            }
        } else {
            throw new NoRouterException("NO SUCH ROUTE");
        }

		/*
		 * Before exiting this recursive stack level,
		 * we mark the start node as visited.
		 */
        start.visited = false;
        return shortestRoute;

    }

    /*
     * Shortest route;
     * Wrapper for recursive function
     */
    public int numRoutesWithin(CityNode start, CityNode end, int maxDistance) throws NoRouterException {
        //Wrapper to maintain weight
        return findnumRoutesWithin(start, end, 0, maxDistance);
    }

    /*
     * Finds the shortest route between two nodes
     */
    private int findnumRoutesWithin(CityNode start, CityNode end, int weight, int maxDistance) throws NoRouterException {
        int routes = 0;
        //Check if start and end nodes exists in route table
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			/*
			 * If start node exists then traverse all possible
			 * routes and for each, check if it is destination
			 */
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                weight += edge.weight;
				/* If distance is under max, keep traversing
				 * even if match is found until distance is > max
				 */
                if (weight <= maxDistance) {
                    if (edge.destination.equals(end)) {
                        routes++;
                        routes += findnumRoutesWithin(edge.destination, end, weight, maxDistance);
                        edge = edge.next;
                        continue;
                    } else {
                        routes += findnumRoutesWithin(edge.destination, end, weight, maxDistance);
                        weight -= edge.weight;    //Decrement weight as we backtrack
                    }
                } else {
                    weight -= edge.weight;
                }
                edge = edge.next;
            }
        } else {
            throw new NoRouterException("NO SUCH ROUTE");
        }
        return routes;

    }
}
