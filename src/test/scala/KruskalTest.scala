
class KruskalTest extends MainTest {

  "Kruskal" should "find shortest path from 0 to 100 nodes size" in {
    a[IllegalArgumentException] should be thrownBy {
      Graph(0)
    }

    var totalTime = 0.0;
    
    for (i <- 1 to 100) {
      val graph: Graph = Graph(i)
      graph.uniqueCompleteFill()
      
      val startTime = System.nanoTime
      Kruskal.findShortestPath(graph)
      val currentTime = (System.nanoTime - startTime) / 1e6
      totalTime = totalTime + currentTime
      println("Time: " + currentTime + "ms - " + graph.getEdges.size + " Edges, " + graph.getNodes.size + " Nodes")
    }
    println("=======================================")
    println("Total time: " + totalTime + "ms" )
  }

  "Kruskal" should "find shortest path like in wikipedia" in {
    // Data taken from https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
    val graph = Graph(7)
    graph.set((0, 1, 7))
    graph.set((0, 3, 5))

    graph.set((1, 2, 8))
    graph.set((1, 3, 9))
    graph.set((1, 4, 7))

    graph.set((2, 4, 5))

    graph.set((3, 4, 15))
    graph.set((3, 5, 6))

    graph.set((4, 5, 8))
    graph.set((4, 6, 9))

    graph.set((5, 6, 11))

    val path = Kruskal.findShortestPath(graph)

    path.get(0, 1) should be(7)
    path.get(0, 3) should be(5)

    path.get(1, 2) should be(Graph.NO_CONNECTION_WEIGHT)
    path.get(1, 3) should be(Graph.NO_CONNECTION_WEIGHT)
    path.get(1, 4) should be(7)

    path.get(2, 4) should be(5)

    path.get(3, 4) should be(Graph.NO_CONNECTION_WEIGHT)
    path.get(3, 5) should be(6)

    path.get(4, 5) should be(Graph.NO_CONNECTION_WEIGHT)
    path.get(4, 6) should be(9)

    path.get(5, 6) should be(Graph.NO_CONNECTION_WEIGHT)
  }
}
