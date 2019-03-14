import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

/**
 * created by wenpengkun
 * 数据可以动态加载
 */
public class Graph implements GraphInter{

    private static Graph graph = null;

    private HashMap<TownsNode, Edge> routeTable;

    private static final String PATH = "/Users/wensir/mydemo/transTest/target/classes/data.txt";


    public HashMap<TownsNode, Edge> getRouteTable() {
        return routeTable;
    }

    /**
     * 图的数据生成
     * @return
     */
    private Graph() {
        this.routeTable = new HashMap<TownsNode, Edge>();

    }

    /**
     * 获取图
     * @return
     */
    public static Graph getGraph() throws Exception {
        if (null == graph){
            graph = new Graph();
            graph.init();
        }
        return graph;

    }

    /**
     * 图的初始化
     * @throws Exception
     */
    private void init() throws Exception {
        String[] inputData = getData().split("\\|");
        List<String> inputDataList = Arrays.asList(inputData);
        Map<String, TownsNode> townsNodeMap = getAllNode(inputData);
        townsNodeMap.forEach((k,v) ->{
            inputDataList.forEach(data -> {
                if (data.startsWith(k)) {
                    if (graph.routeTable.get(v) == null){
                        graph.routeTable.put(v,
                                new Edge(v, //始点
                                        townsNodeMap.get(data.charAt(1) + ""),//目标节点
                                        Integer.parseInt(data.substring(2, data.length()) + "")));
                    }else{
                        //递归遍历创建对应的图
                        createGraph(graph.routeTable.get(v),
                                data,
                                v,
                                townsNodeMap);

                    }
                }
            });
        });

    }

    /**
     * 创建图
     * @param edge
     * @param inputDatum
     * @param value
     * @param townsNodeMap
     */
    private void createGraph(Edge edge,
                             String inputDatum,
                             TownsNode value,
                             Map<String, TownsNode> townsNodeMap) {
        if(edge.next == null){
            edge.next(new Edge(value,
                    townsNodeMap.get(inputDatum.charAt(1) + ""),//目标节点
                    Integer.parseInt(inputDatum.substring(2, inputDatum.length()) + "")
            ));
        }else{
            createGraph( edge.next,
                     inputDatum,
                     value,
                     townsNodeMap);
        }
    }

    /**
     * 获取所有节点
     * @return
     * @throws Exception
     */
    private Map<String,TownsNode> getAllNode(String[] inputData) throws Exception {
        validateData(inputData);
        Set<String> nodeSetName = new LinkedHashSet<String>();
        //获取所有节点名称
        for (String inputDatum : inputData) {
            //所有节点
            nodeSetName.add(inputDatum.charAt(0)+"");
            nodeSetName.add(inputDatum.charAt(1)+"");
        }
        //为所有节点创建对象存到容器中
        Map townsNodeMap = new HashMap();
        nodeSetName.forEach((String nodeName) -> townsNodeMap.put(nodeName,new TownsNode(nodeName)));

        return townsNodeMap;
    }

    /**
     * 读取输入数据
     * @return
     */
    private  String getData(){
        StringBuilder result = new StringBuilder();

        try{

            String data = null;
            BufferedReader br = new BufferedReader(new FileReader(PATH));//构造一个BufferedReader类来读取文件
            while((data = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(data);
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return String.valueOf(result);
    }


    /**
     * 数据校验
     * @param inputData
     * @throws InputException
     */
    private void validateData(String[] inputData) throws InputException {
        if(null == inputData || inputData.length <= 0) {
            throw new InputException("数据输入不合法请检查数据");
        }

        for (String data : inputData) {
            String regx = "[A-Z][A-Z]";
            if (data.substring(0,2).matches(regx)) { //前两位是否合法
                try {
                    Integer.parseInt(data.substring(2,data.length()));//权重是否合法
                }catch (Exception e){
                    throw new InputException("数据输入不合法请检查数据");
                }
            } else {
                throw new InputException("数据输入不合法请检查数据");
            }
        }

    }


}
