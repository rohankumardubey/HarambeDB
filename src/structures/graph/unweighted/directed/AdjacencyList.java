package structures.graph.unweighted.directed;

import structures.graph.unweighted.UnweightedGraph;
import structures.list.ArrayLinearList;
import structures.list.LinkedList;
import structures.graph.Vertex;
import structures.graph.Graph;

public class AdjacencyList implements UnweightedGraph<ALVertex> {
  ArrayLinearList<ALVertex> matrix;
  private int vertexCount;
  private static final long serialVersionUID = 34l;

  public AdjacencyList(int vertexCount) {
    this.vertexCount = vertexCount;
    this.matrix = new ArrayLinearList<>();
    for (int i = 0; i < vertexCount; i++) {
      this.matrix.add(i, new ALVertex(i));
    }
  }

  public void addEdge(int from, int to) {
    if (from >= 0 && from < vertexCount && to >= 0 && to < vertexCount) {
      if (from == to) return;
      this.matrix.get(from).connectVertex(this.matrix.get(to));
    } else throw new IndexOutOfBoundsException();
  }

  public void addVertex() {
    this.matrix.add(vertexCount, new ALVertex(vertexCount++));
  }

  public void removeEdge(int from, int to) {
    if (from >= 0 && from < vertexCount && to > 0 && to < vertexCount) {
      if (from == to) return;
      this.matrix.get(from).removeAdjacentVertex(this.matrix.get(to));
    } else throw new IndexOutOfBoundsException();
  }

  public ALVertex getVertex(int vertex) {
    if (vertex >= 0 && vertex < vertexCount) {
      return this.matrix.get(vertex);
    } else throw new IndexOutOfBoundsException();
  }

  public int vertexCount() {
    return this.vertexCount;
  }

  public ALVertex[] getGraph() {
    return this.matrix.toArray();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('{').append('\n');
    for (ALVertex v : matrix) {
      sb.append("  ").append(v).append('\n');
    }
    return sb.append('}').toString();
  }

  public static void main(String[] args) {
    UnweightedGraph<ALVertex> g = new AdjacencyList(10);
    g.addEdge(1, 4);
    g.addEdge(1, 6);
    g.addEdge(4, 6);
    g.addEdge(0, 9);
    g.addEdge(4, 9);
    g.addEdge(8, 3);
    g.addEdge(7, 2);
    g.addEdge(6, 5);
    System.out.println(g);
    Graph.BFS(g, 4);
    System.out.println(g);
    Graph.DFS(g, 4);
    System.out.println(g);
  }
}

class ALVertex extends Vertex<ALVertex> {
  LinkedList<ALVertex> adjacentVertices;
  private static final long serialVersionUID = 31l;

  public ALVertex(int value) {
    this.value = value;
    this.adjacentVertices = new LinkedList<>();
  }

  public ALVertex(int value, LinkedList<ALVertex> adjacentVertices) {
    this.value = value;
    this.adjacentVertices = adjacentVertices;
  }

  public void connectVertex(ALVertex other) {
    this.adjacentVertices.add(0, other);
  }

  public void removeAdjacentVertex(ALVertex other) {
    this.adjacentVertices.remove(adjacentVertices.indexOf(other));
  }

  public LinkedList<ALVertex> adjacentVertices() {
    return this.adjacentVertices;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append(this.value).append(": [ ");
    for (ALVertex v : adjacentVertices) {
      sb.append(v.value).append(' ');
    }
    return sb.append(']').toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof ALVertex)) return false;
    return this.value == ((ALVertex)other).value;
  }

  @Override
  public int hashCode() {
    return ((Integer)this.value).hashCode();
  }
}
