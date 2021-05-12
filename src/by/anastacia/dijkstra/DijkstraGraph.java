package by.anastacia.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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

  /*
   * Основной метод, котрый без рекурсии(!) выполняет всю работу, расставляя метки
   */
  public void setMarks(int start) {
    this.start = start;
    vertices = new Vertex[distanceMatrix.length];
    for (int i = 0; i < vertices.length; i++) {
      vertices[i] = new Vertex(i);
    }
    vertices[start].setConstantMark(0);
    int currentMark = 0;
    Vertex current = vertices[start];
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>(new TempMarkComparator());
    do {
      int[] distances = distanceMatrix[current.getId()];
      for (int i = 0; i < distances.length; i++) {
        System.out.println("Vertex " + current.getId() + " distance " + distances[i]);
        if (i != current.getId() && distances[i] != Vertex.MAX && vertices[i].getConstantMark() == Vertex.MAX) {
          vertexQueue.remove(vertices[i]);
          vertices[i].checkPredecessor(current, distances[i]);
          vertexQueue.add(vertices[i]);
        }
      }
      current = vertexQueue.poll();
      if (current != null) {
        System.out.println("POLLED " + current.getId() + ", MARK " + current.getTempMark() + ", PREDECESSOR " +
                current.getTempPredecessor().getId());
        currentMark = current.getTempMark();
        current.setConstantMark(currentMark);
        current.setBestPredecessor(current.getTempPredecessor());
      }
    } while (current != null);
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
