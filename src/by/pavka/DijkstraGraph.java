package by.pavka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstraGraph {
  private final int[][] distanceMatrix;
  private Vertex[] vertices;
  private int start;

  public DijkstraGraph(int[][] distanceMatrix) {
    this.distanceMatrix = distanceMatrix;
    vertices = new Vertex[distanceMatrix.length];
    for (int i = 0; i < vertices.length; i++) {
      vertices[i] = new Vertex(i);
    }
    start = -1;
  }

  public void fixStart(int start) {
    vertices = new Vertex[distanceMatrix.length];
    for (int i = 0; i < vertices.length; i++) {
      vertices[i] = new Vertex(i);
    }
    this.start = start;
    vertices[start].setConstantMark(0);
    Vertex current = vertices[start];
    buildPaths(current);
  }

  private void buildPaths(Vertex current) {
    int[] distances = distanceMatrix[current.getId()];
    for (int i = 0; i < distances.length; i++) {
      if (i != current.getId() && distances[i] != Vertex.MAX) {
        vertices[i].checkPredecessor(current, distances[i]);
      }
    }
    Vertex newCurrent = findMin();
    if (newCurrent == null) {
      return;
    }
    newCurrent.setConstantMark(newCurrent.getTempMark());
    newCurrent.setBestPredecessor(newCurrent.getTempPredecessor());
    buildPaths(newCurrent);
  }

  private Vertex findMin() {
    int minMark = Vertex.MAX;
    int index = -1;
    for (int i = 0; i < vertices.length; i++) {
      if (vertices[i].getConstantMark() == Vertex.MAX && vertices[i].getTempMark() < minMark) {
        minMark = vertices[i].getTempMark();
        index = i;
      }
    }
    if (index != -1) {
      return vertices[index];
    } else {
      return null;
    }
  }

  public List<Vertex> getPathBack(int end) {
    List<Vertex> path = new ArrayList<>();
    path.add(vertices[end]);
    if (start != -1 && start != end) {
      int current = end;
      while (vertices[current].getBestPredecessor() != null) {
        current = vertices[current].getBestPredecessor().getId();
        path.add(vertices[current]);
      }
    }
    return path;
  }

  public List<Vertex> getPathForward(int end) {
    List<Vertex> list = getPathBack(end);
    Collections.reverse(list);
    return list;
  }
}
