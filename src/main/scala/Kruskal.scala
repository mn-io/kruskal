
object Kruskal {
  def findShortestPath(graph: Graph): Graph = {
    var result = new Graph(graph.size)
    var edges: List[(Int, Int, Int)] = graph.getSortedEdges
    var nodes: List[Int] = graph.getNodes
    for (edge <- edges) {
      result.set(edge)
    }
    result
  }
}
