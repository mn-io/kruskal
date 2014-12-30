
class GraphTest extends MainTest {

  "Graph" should "initialize with nodes" in {
    val numberOfNodes = 3
    val graph = new Graph(numberOfNodes)
    graph.size should be(numberOfNodes)
  }

  "Graph" should "allow get and set to edges" in {
    val graph = new Graph(4)
    graph.get(0, 1) should be(0)

    graph.set((0, 1, 19))
    graph.get(0, 1) should be(19)

    intercept[IndexOutOfBoundsException] {
      graph.get(0, 5)
    }
    intercept[IndexOutOfBoundsException] {
      graph.get(5, 1)
    }

    graph.set((0, 1, 19))
    graph.set((0, 2, 19))
    graph.set((0, 3, 19))

    graph.set((1, 2, 19))
    graph.set((1, 3, 19))

    graph.set((2, 3, 19))
  }

  "Graph" should "be directed" in {
    val graph = new Graph(4)
    graph.set((1, 3, 100))
    graph.get(3, 1) should be(100)

    graph.set((3, 1, 99))
    graph.get(3, 1) should be(99)
    graph.get(1, 3) should be(99)
  }

  "Graph" should "fill with values" in {
    val graph = new Graph(4)
    graph.fill(() => {
      3
    })
    graph.get(3, 0) should be(3)
    graph.get(0, 3) should be(3)
  }

  "Graph" should "fill with random values" in {
    val graph = new Graph(4)
    graph.randomFill(9)
    graph.get(0, 3) should not be (0)
  }

  "Graph" should "get all edges as tuple" in {
    val graph = new Graph(4)
    graph.getSortedEdges.length should be(0)

    graph.randomFill(9)
    graph.getSortedEdges.length should be(6)
  }

  "Graph" should "get all edges as tuple sorted" in {
    val graph = new Graph(4)
    graph.randomFill(9)
    val edges: List[(Int, Int, Int)] = graph.getSortedEdges
    var prev = edges.head
    for (edge <- edges) {
      if (prev._3 > edge._3) {
        fail("Not sorted");
      }
      prev = edge
    }
  }

  "Graph" should "get all nodes" in {
    val graph = new Graph(4)
    graph.getNodes.size should be(graph.size)
  }
}
