
class KruskalTest extends MainTest {

  "Kruskal" should "find shortest path" in {
    val graph: Graph = new Graph(4)
    graph.randomFill(9)
    print(graph)
    val result = Kruskal.findShortestPath(graph)
    print(result)
  }
}
