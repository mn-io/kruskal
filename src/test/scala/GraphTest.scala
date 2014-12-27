
class GraphTest extends MainTest {

  "Graph" should "initialize with nodes" in {
    val numberOfNodes = 3
    val graph = new Graph(numberOfNodes)
    graph.length should be (numberOfNodes)
    //print(graph)
  }

  "Graph" should "allow get and set to nodes" in {
    val graph = new Graph(4)
    graph.get(0,1) should be (0)
    graph.set(0,1,19)
    graph.get(0,1) should be (19)

    intercept[IndexOutOfBoundsException] {
      graph.get(0,5)
    }
    intercept[IndexOutOfBoundsException] {
      graph.get(5,1)
    }
  }

  "Graph" should "fill with values" in {
    val graph = new Graph(4)
    graph.fill(() => {
      3
    })
    graph.get(0,0) should be (3)
    graph.get(3,0) should be (3)
    graph.get(0,3) should be (3)
    graph.get(3,3) should be (3)
  }

  "Graph" should "fill with random values" in {
    val graph = new Graph(4)
    graph.randomFill(9)
    graph.get(0,0) should not be (0)
    graph.get(0,3) should not be (0)
    graph.get(3,3) should not be (0)
  }
}
