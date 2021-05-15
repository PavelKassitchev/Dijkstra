package by.anastacia.dijkstra;

import java.util.List;

import static by.anastacia.dijkstra.Vertex.MAX;

public class Main {

  public static void main(String[] args) {

    int[][] vertices = {
      {0, 1, MAX, 5, 2, MAX, MAX},
      {MAX, 0, 1, MAX, MAX, MAX, MAX},
      {MAX, MAX, 0, MAX, 1, MAX, 4},
      {MAX, 1, 1, 0, MAX, 4, 8},
      {MAX, MAX, MAX, MAX, 0, 9, MAX},
      {MAX, MAX, MAX, MAX, MAX, 0, 4},
      {MAX, MAX, MAX, MAX, MAX, MAX, 0}
    };
    DijkstraGraph dijkstraGraph = new DijkstraGraph(vertices);
    dijkstraGraph.setMarks(0);
    List<Vertex> path = dijkstraGraph.getPathForward(5);
    showPath(path);
  }

  private static void showPath(List<Vertex> path) {
    int fullDistance = path.get(path.size() - 1).getConstantMark();
    String distance = fullDistance == MAX ? "Unreachable Point" : String.valueOf(fullDistance);
    System.out.println("Full Distance from Vertex No." + path.get(0).getId() + " to Vertex No."
            + path.get(path.size() - 1).getId() + " = " + distance);
    System.out.print("Path: ");
    if (fullDistance == MAX) {
      System.out.println("No way");
    } else {
      for (Vertex vertex : path) {
        System.out.print("Vertex No." + vertex.getId() + "  ");
      }
    }
  }
}
