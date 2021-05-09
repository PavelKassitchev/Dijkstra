package by.pavka;

import java.util.List;

import static by.pavka.Vertex.MAX;

public class Main {

  public static void main(String[] args) {

    int[][] vertices = {
      {0, MAX, 10, MAX, 30, 100},
      {MAX, 0, 5, MAX, MAX, MAX},
      {MAX, MAX, 0, 50, MAX, MAX},
      {MAX, MAX, MAX, 0, 20, 10},
      {MAX, MAX, MAX, MAX, 0, 60},
      {MAX, MAX, MAX, MAX, MAX, 0}
    };
    DijkstraGraph dijkstraGraph = new DijkstraGraph(vertices);
    dijkstraGraph.fixStart(0);
    List<Vertex> path = dijkstraGraph.getPathForward(5);
    showPath(path);
  }

  private static void showPath(List<Vertex> path) {
    int fullDistance = path.get(path.size() - 1).getConstantMark();
    String distance = fullDistance == MAX ? "Unreachable Point" : String.valueOf(fullDistance);
    System.out.println("Full Distance = " + distance);
    if (fullDistance == MAX) {
      System.out.println("No way");
    } else {
      for (Vertex vertex : path) {
        System.out.print("Vertex No." + vertex.getId() + "  ");
      }
    }
  }
}
