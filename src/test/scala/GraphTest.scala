
class GraphTest extends MainTest {

  "Graph" should "initialize with nodes" in {
    val numberOfNodes = 3
    val graph = new Graph(numberOfNodes)
    graph.length should be (numberOfNodes)
    print(graph)
  }
}
