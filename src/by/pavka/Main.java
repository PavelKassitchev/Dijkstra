package by.pavka;

import java.util.List;

import static by.pavka.Vertex.MAX;

public class Main {

  public static void main(String[] args) {

    int[][] vertices = {
      {0, 4, 1, MAX, 2, MAX, MAX},
      {MAX, 0, MAX, 3, MAX, MAX, 2},
      {MAX, 6, 0, 5, 3, MAX, MAX},
      {MAX, 2, MAX, 0, MAX, 5, 4},
      {MAX, MAX, 4, MAX, 0, 4, MAX},
      {MAX, MAX, MAX, MAX, MAX, 0, 6},
      {MAX, MAX, MAX, MAX, MAX, MAX, 0}
    };
    DijkstraGraph dijkstraGraph = new DijkstraGraph(vertices);
    dijkstraGraph.fixStart(0);
    List<Vertex> path = dijkstraGraph.getPathForward(6);
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
