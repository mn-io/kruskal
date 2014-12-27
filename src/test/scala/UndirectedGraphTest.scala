
class UndirectedGraphTest extends MainTest {

  "Graph" should "contain weighted connections" in {
    val max = 200
    val graph = new UndirectedGraph(5)

    graph.randomFill(max)

  }
}
