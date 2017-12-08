import java.util.*;

/**
 * Created by Liang on 2017/12/7.
 */
public class TrainsProblem {

    private int [][] m_routeGraph;
    public static final int MAX = 1000000; //define a max constant figure used to initialize
    private Map<String, Integer> m_townsid; //the series of towns

    private class Note{
        public int townId;public int deep;public int distance;
        Note(int townId, int deep){this.townId = townId; this.deep = deep;this.distance=0;}
        Note(int townId, int deep, int distance){this.townId = townId; this.deep = deep; this.distance = distance;}
    };

    public void saveRouteGraphToMemomy(String towns, String routes) {
        String[] temptowns = towns.split(",");
        m_townsid = new HashMap<String, Integer>();
        for(int i=0; i< temptowns.length; i++) {
            String temp = temptowns[i].trim();
            m_townsid.put(temp, i);
        }

        String[] temproutes = routes.split(",");
        for(int i=0; i< temproutes.length; i++)
            temproutes[i] = temproutes[i].trim();
        //build route graph
        m_routeGraph = new int[temptowns.length][temptowns.length];
        for(int i=0; i<temptowns.length; i++)
            for(int j=0; j<temptowns.length; j++)
                m_routeGraph[i][j] = MAX;
        for(String elem: temproutes) {
            String start = elem.substring(0,1);
            String end = elem.substring(1,2);
            String distance = elem.substring(2,3);
            m_routeGraph[m_townsid.get(start)][m_townsid.get(end)] = Integer.valueOf(distance);
        }
    }

    public int getDistance(String route) {
        String[] towns = route.split("-");
        int distance = 0;
        for(int i=0; i< towns.length-1; i++) {
            String start = towns[i];
            String end = towns[i+1];
            int curDistance = m_routeGraph[m_townsid.get(start)][m_townsid.get(end)];
            if (curDistance == MAX)
                return MAX;
            else
                distance += curDistance;
        }
        return distance;
    }

    public int getShortestRoute(String route) {
        // dijkstra algorithm, but allow start town is also end town
        String[] towns = route.split("-");
        String start, end;
        start = towns[0].trim();
        end = towns[1].trim();
        int numbertowns = m_townsid.size();
        int[] distance = m_routeGraph[m_townsid.get(start)];

        int startindex = m_townsid.get(start);
        int endindex = m_townsid.get(end);
        HashSet<Integer> unvisitedtowns = new HashSet<Integer>();
        for(int i=0; i< numbertowns; i++) unvisitedtowns.add(i);
        int curIndex = startindex;
        int curMinDistance = 0;
        int steps = 1;
        while(unvisitedtowns.size() > 0) {
            if (steps != 1) // in order to allow start town can also be the end town
                unvisitedtowns.remove(curIndex);
            steps++;
            int min=MAX, minindex=numbertowns;
            for(int elem: unvisitedtowns){
                if(m_routeGraph[curIndex][elem] + curMinDistance < distance[elem]) {
                    distance[elem] = m_routeGraph[curIndex][elem] + curMinDistance;
                }
                if(distance[elem] < min) {
                    min = distance[elem];
                    minindex = elem;
                }
            }

            curIndex = minindex;
            curMinDistance = min;
            if(curIndex == numbertowns)
                break;
        }
        if (endindex == numbertowns)
            return MAX;
        else
            return distance[endindex];
    }

    public int getNumberOfRoutesLessThanADistance(String route, int distance) {
        // BFS
        String[] towns = route.split("-");
        String start, end;
        start = towns[0].trim();
        end = towns[1].trim();
        int startIndex = m_townsid.get(start);
        int endIndex = m_townsid.get(end);
        Queue<Note> townsQueue = new LinkedList<Note>();
        Note tempNote = new Note(startIndex, 0,0);
        townsQueue.add(tempNote);
        int resultnumber = 0;
        int numbertowns = m_townsid.size();
        while(townsQueue.size()>0){
            Note curNote = townsQueue.poll();
            int curDistance = curNote.distance;
            int curTownid = curNote.townId;
            if (curDistance < distance && curDistance > 0 && curTownid == endIndex)
                    resultnumber++;

            for(int i=0; i< numbertowns; i++){
                int elem = m_routeGraph[curTownid][i];
                int newDistance = curDistance + elem;
                if(elem != MAX && newDistance < distance){
                    townsQueue.add(new Note(i, 0, curDistance+elem));
                }
            }
        }
        return resultnumber;
    }

    public int getNumberOfRoutesByStops(String route, int stops, boolean lessOrCertain){
        //BFS
        String[] towns = route.split("-");
        String start, end;
        start = towns[0].trim();
        end = towns[1].trim();
        int startIndex = m_townsid.get(start);
        int endIndex = m_townsid.get(end);
        Queue<Note> townsQueue = new LinkedList<Note>();
        Note tempNote = new Note(startIndex, 0);
        townsQueue.add(tempNote);
        int resultnumber = 0;
        int numbertowns = m_townsid.size();
        while(townsQueue.size()>0){
            Note curNote = townsQueue.poll();
            int curDeep = curNote.deep;
            int curTownid = curNote.townId;
            if(lessOrCertain) {
                if (curDeep <= stops && curDeep > 0 && curTownid == endIndex)
                    resultnumber++;
            }
            else {
                if (curDeep == stops && curTownid == endIndex)
                    resultnumber++;
            }
            if(curDeep > stops)
                break;
            for(int i=0; i< numbertowns; i++){
                int elem = m_routeGraph[curTownid][i];
                if(elem != MAX){
                    townsQueue.add(new Note(i, curDeep+1));
                }
            }
        }
        return resultnumber;
    }

    private String input(String information){
        System.out.print(information);
        Scanner scanner = new Scanner(System.in);
        String route = scanner.nextLine();
        route = route.trim();
        return route;
    }
    public static void main(String[] args){
        TrainsProblem trainsProblem = new TrainsProblem();
        System.out.print("please input the names of town: ");
        Scanner scan = new Scanner(System.in);
        String townsdata = scan.nextLine();
        System.out.print("please input all routes and distance(like AB5): ");
        String routesdata = scan.nextLine();
        if(false)
            trainsProblem.saveRouteGraphToMemomy(townsdata, routesdata);
        else //test
            trainsProblem.saveRouteGraphToMemomy("A, B, C, D, E", "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        String control = "Y";
        while (control.equals("Y")) {
            int choice = 3;
            switch (choice) {
                case 1://get distance
                    String route = trainsProblem.input("please input route: ");
                    int distance = trainsProblem.getDistance(route);
                    if (distance == MAX)
                        System.out.println("NO SUCH ROUTE");
                    else
                        System.out.println(route + " distance: " + distance);
                    break;
                case 2://get shortest distance
                    String routeinfo = trainsProblem.input("please input route: ");
                    int shortestdistance = trainsProblem.getShortestRoute(routeinfo);
                    if(shortestdistance == MAX)
                        System.out.println("NO SUCH ROUTE");
                    else
                        System.out.println(routeinfo+" shortest distance: "+shortestdistance);
                    break;
                case 3:
                    String select = trainsProblem.input("distance limit(0) or certain stops limit(1) or max steps limit(2): ");
                    if(0 == Integer.valueOf(select)) {
                        String curroute = trainsProblem.input("please input route: ");
                        String dist = trainsProblem.input("please input max distance: ");
                        int result = trainsProblem.getNumberOfRoutesLessThanADistance(curroute, Integer.valueOf(dist));
                        System.out.println(curroute+" less than distance "+dist+ " numbers: "+ result);
                    }
                    else if(1 == Integer.valueOf(select)){
                        String curroute = trainsProblem.input("please input route: ");
                        String stops = trainsProblem.input("please input certain stops: ");
                        int result = trainsProblem.getNumberOfRoutesByStops(curroute, Integer.valueOf(stops), false);
                        System.out.println(curroute+" stops "+stops+ " numbers: "+ result);
                    }
                    else if(2 == Integer.valueOf(select)){
                        String curroute = trainsProblem.input("please input route: ");
                        String stops = trainsProblem.input("please input max stops: ");
                        int result = trainsProblem.getNumberOfRoutesByStops(curroute, Integer.valueOf(stops), true);
                        System.out.println(curroute+" less than "+stops+ " numbers: "+ result);
                    }
                    break;
            }
            System.out.print("continue?(Y/N): ");
            Scanner scanner = new Scanner(System.in);
            control = scanner.next();
        }
    }
}
