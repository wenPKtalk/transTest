public abstract class GraphDecorator implements GraphInter {
    private GraphInter graph;
    public GraphDecorator(GraphInter _graph){
        this.graph = _graph;
    }

}
