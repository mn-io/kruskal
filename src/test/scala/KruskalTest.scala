
class KruskalTest extends MainTest {

  "Kruskal" should "find shortest path" in {
    val graph: Graph = new Graph(4)
    Kruskal.findShortestPath(graph)
  }
}
