package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGraph implements GraphInterface, Cloneable
{
    private int numberOfVertices;
    private List<Vertex> vertices;

    protected AbstractGraph(List<Vertex> vertices)
    {
        numberOfVertices = vertices.size();
        setVertices(vertices);
    }

    protected AbstractGraph()
    {
        vertices = new ArrayList<>();
        numberOfVertices = 0;
    }

    @Override
    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
        numberOfVertices = vertices.size();
    }

    public int getNumberOfVertices()
    {
        return numberOfVertices;
    }

    public void setNumberOfVertices(int numberOfVertices)
    {
        this.numberOfVertices = numberOfVertices;
    }

    public List<Vertex> getVertices()
    {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices)
    {
        this.vertices = vertices;
        this.numberOfVertices = vertices.size();
    }

    public void addEdge(Vertex source, Vertex destination)
    {
        addEdge(source, destination, 1);
    }

    public Vertex getCentermostVertex(float[][] distanceMatrix)
    {
        float[] maxDistanceInCollumn = getMaxDistanceInCollumn(distanceMatrix);
        int vertexIndex = getMinDistanceIndexInCollumn(maxDistanceInCollumn);
        return vertices.get(vertexIndex);
    }

    public Vertex getOuterMostVertex(float[][] distanceMatrix) {
        float[] maxDistanceInCollumn = getMaxDistanceInCollumn(distanceMatrix);
        int vertexIndex = getMaxDistanceIndexInCollumn(maxDistanceInCollumn);
        return vertices.get(vertexIndex);
    }

    public Vertex getMostDistantVertex(float[][] distanceMatrix, Vertex outerMost) {
        int outerMostVertexIndex = vertices.indexOf(outerMost);
        int mostDistantOuterMostVertexIndex = 0;
        float distance = distanceMatrix[0][outerMostVertexIndex];
        for(int i = 1; i < distanceMatrix.length; i++)
        {
            if(distance < distanceMatrix[i][outerMostVertexIndex])
            {
                mostDistantOuterMostVertexIndex = i;
                distance = distanceMatrix[i][outerMostVertexIndex];
            }
        }

        return vertices.get(mostDistantOuterMostVertexIndex);
    }

    private float[] getMaxDistanceInCollumn(float[][] distanceMatrix) {
        float[] maxDistanceInCollumn = new float[distanceMatrix.length];
        Arrays.fill(maxDistanceInCollumn, Float.NEGATIVE_INFINITY);
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                if (maxDistanceInCollumn[i] < distanceMatrix[i][j]) {
                    maxDistanceInCollumn[i] = distanceMatrix[i][j];
                }
            }
        }
        return maxDistanceInCollumn;
    }

    private int getMinDistanceIndexInCollumn(float[] distanceArray)
    {
        int minIndex = 0;
        float minDistance = distanceArray[0];
        for (int i = 1; i < distanceArray.length; i++)
        {
            if(minDistance > distanceArray[i])
            {
                minDistance = distanceArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int getMaxDistanceIndexInCollumn(float[] distanceArray)
    {
        int maxIndex = 0;
        double maxDistance = distanceArray[0];
        for (int i = 1; i < distanceArray.length; i++)
        {
            if(maxDistance < distanceArray[i])
            {
                maxDistance = distanceArray[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int getIndexOfVertex(Vertex vertex)
    {
        return getVertices().indexOf(vertex);
    }

    @Override
    protected AbstractGraph clone() throws CloneNotSupportedException
    {
        AbstractGraph graphClone = (AbstractGraph)super.clone();
        graphClone.setNumberOfVertices(this.getNumberOfVertices());
        graphClone.setVertices(new ArrayList<>(this.getVertices()));
        return graphClone;
    }
}
