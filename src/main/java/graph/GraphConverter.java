package graph;

public class GraphConverter {
    private GraphConverter(){};

    public static AbstractGraph predecessorListToGraph(AbstractGraph graph, int[] predecessorArray)
    {
        // Cria o novo grafo
        AbstractGraph predecessorGraph = new GraphList();
        // Copia todos os vértices do grafo original para o novo
        copyMSTVertices(graph, predecessorGraph);
        // Copia somente as arestas da árvore mínima para o novo grafo
        copyMSTEdges(graph, predecessorArray, predecessorGraph);

        return predecessorGraph;
    }

    private static void copyMSTVertices(AbstractGraph graph, AbstractGraph predecessorGraph) {
        for(int i = 0; i < graph.getNumberOfVertices(); i++)
        {
            predecessorGraph.addVertex(graph.getVertices().get(i));
        }
    }

    private static void copyMSTEdges(AbstractGraph graph, int[] predecessorArray, AbstractGraph predecessorGraph) {
        for(var i = 0; i < predecessorGraph.getNumberOfVertices(); i++)
        {
            for(var j = 0; j < predecessorArray.length; j++)
            {
                if(predecessorArray[j] == i)
                {
                    Vertex source = predecessorGraph.getVertices().get(i);
                    Vertex destination = predecessorGraph.getVertices().get(j);
                    predecessorGraph.addEdge(source, destination, graph.getEdge(source, destination).getWeight());
                }
            }
        }
    }
}
