object Demo extends App {
  override def main(args: Array[String]): Unit = {
    println("Graph and data taken from https://en.wikipedia.org/wiki/Kruskal%27s_algorithm")

    val graph = generateGraph

    println("adjacency matrix before:")
    println(graph)

    val path = Kruskal.findShortestPath(graph)

    println("adjacency matrix of shortest path found by kruskal:")
    println(path)
  }

  def generateGraph = {
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

    graph
  }
}
