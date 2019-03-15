import exception.NoSuchRouterException;
import pojo.Edge;
import pojo.TownsNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wenpengkun on 2017/12/09.
 */
public class GraphRouter {

    private HashMap<TownsNode, Edge> routeTable;

    public GraphRouter() throws Exception {
        this.routeTable = Graph.getGraph().getRouteTable();
    }

    /**
     * 给出节点计算之间的距离
     *
     * @param cities
     * @return
     * @throws Exception
     */
    public int distanceBetween(ArrayList<TownsNode> cities) throws NoSuchRouterException {

        if (cities.size() < 2) {
            //如果给出的节点个数小于2直接返回0
            return 0;
        }

        int distance, depth, i;
        distance = depth = i = 0;

        /**
         * 检查list中的节点检查是否存在于创建的图表中
         */
        while (i < cities.size() - 1) {
            if (this.routeTable.containsKey(cities.get(i))) {
                Edge route = this.routeTable.get(cities.get(i));

                //存在：遍历节点并且权值相加
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
                throw new NoSuchRouterException();
            }
            i++;
        }

        //遍历深度不等于城市节点长度减一则路线不存在
        if (depth != cities.size() - 1) {
            throw new NoSuchRouterException();
        }
        return distance;
    }

    /**
     * 计算两个城市的站点数量
     * maxStop:最大站点数量
     * @param start
     * @param end
     * @param maxStops
     * @return
     * @throws NoSuchRouterException
     */
    public int numStops(TownsNode start, TownsNode end, int maxStops)
            throws NoSuchRouterException {

        return findRoutes(start, end, 0, maxStops);
    }


    /**
     * 给定起点和终点查找总共存在多少站
     * 并且用最大站点和遍历递归深度进行约束
     * @param start
     * @param end
     * @param depth
     * @param maxStops
     * @return
     * @throws NoSuchRouterException
     */
    private int findRoutes(TownsNode start, TownsNode end, int depth, int maxStops)
            throws NoSuchRouterException {
        int routes = 0;
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            /**
             * 如果给定节点均存在则依次遍历所有可能的节点，
             * 遍历过程中如果满足给定起始节点和终止节点并且
             * 在规定的站数范围则加入可能的路线。
             */
            depth++;
            if (depth > maxStops) {
                return 0;
            }
            start.visited = true;        //起始节点
            Edge edge = this.routeTable.get(start);
            while (edge != null) {

                if (edge.destination.equals(end)) {
                    //符合条件，路线数量加1，继续遍历
                    routes++;
                    edge = edge.next;
                    continue;
                } else if (!edge.destination.visited) {
                    //不符合条件，并且终点节点未被访问
                    //进行递归遍历
                    routes += findRoutes(edge.destination, end, depth, maxStops);
                    depth--;
                }
                edge = edge.next;
            }
        } else {
            throw new NoSuchRouterException();
        }

		//退出递归前重置起始节点访问状态
        start.visited = false;
        return routes;
    }

    /**
     * 给定开始节点和终止节点算出两个节点
     * 在有向带权图中的最短路径
     * 采用 Dijkstra算法
     * @param start
     * @param end
     * @return
     * @throws NoSuchRouterException
     */
    public int shortestRoute(TownsNode start, TownsNode end) throws NoSuchRouterException {
        return findShortestRoute(start, end, 0, 0);

    }

    /**
     * Dijkstra算法具体实现
     * @param start
     * @param end
     * @param weight
     * @param shortestRoute
     * @return
     * @throws NoSuchRouterException
     */
    private int findShortestRoute(TownsNode start, TownsNode end, int weight, int shortestRoute)
            throws NoSuchRouterException {

        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			//依次遍历所有可能的节点，并且检查是否为目的地
            start.visited = true;        //设置开始节点为已访问
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                //节点未访问或为终点,增加权重
                if (edge.destination == end || !edge.destination.visited)
                    weight += edge.weight;
                //判断是否为目的地节点，如果是则与当前已经遍历到的最短路径权重比较
                //并且更新最新的最短路径
                if (edge.destination.equals(end)) { //递归出口
                    if (shortestRoute == 0 || weight < shortestRoute)
                        shortestRoute = weight;
                    start.visited = false;
                    return shortestRoute;
                }else if (!edge.destination.visited) {
                    //继续访问
                    shortestRoute = findShortestRoute(edge.destination, end, weight, shortestRoute);
                    weight -= edge.weight;
                }
                edge = edge.next;
            }
        } else {
            throw new NoSuchRouterException();
        }

        //退出递归前重置起始节点访问状态
        start.visited = false;
        return shortestRoute;

    }

    /**
     * 给定起始节点，终止节点，最大路径
     * 返回所有可能的路线数量
     * @param start
     * @param end
     * @param maxDistance
     * @return
     * @throws NoSuchRouterException
     */
    public int numRoutesWithin(TownsNode start, TownsNode end, int maxDistance)
            throws NoSuchRouterException {
        return findNumRoutesWithin(start, end, 0, maxDistance);
    }

    /**
     * 实现算法
     * @param start
     * @param end
     * @param weight
     * @param maxDistance
     * @return
     * @throws NoSuchRouterException
     */
    private int findNumRoutesWithin(TownsNode start, TownsNode end, int weight, int maxDistance)
            throws NoSuchRouterException {
        int routes = 0;
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {

            //依次遍历所有可能的路线并且检查是否为终结点
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                weight += edge.weight;
				//路线权重小于限定值继续进行遍历直到超过限定值
                if (weight <= maxDistance) {
                    if (edge.destination.equals(end)) {
                        routes++;
                        routes += findNumRoutesWithin(edge.destination, end, weight, maxDistance);
                        edge = edge.next;
                        continue;
                    } else {
                        routes += findNumRoutesWithin(edge.destination, end, weight, maxDistance);
                        weight -= edge.weight;
                    }
                } else {
                    weight -= edge.weight;
                }
                edge = edge.next;
            }
        } else {
            throw new NoSuchRouterException();
        }
        return routes;

    }
}
