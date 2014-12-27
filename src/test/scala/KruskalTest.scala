
class KruskalTest extends MainTest {

  "Kruskal" should "find shortest path" in {
    val graph: Graph = new Graph(4)
    graph.randomFill(9)
    val tree: Tree = Kruskal.findShortestPath(graph)
    print(tree)
  }
}
