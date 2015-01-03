

object Kruskal {

  def findShortestPath(graph: Graph): Graph = {
    val edges = graph.getSortedEdges
    if (edges.length <= 1) return graph

    val result = Graph(graph.size)
    val unionFind = UnionFind(graph.getNodes)

    for (edge <- edges) {
      val edgesConnected = unionFind.find(edge._1) == unionFind.find(edge._2)
      if (!edgesConnected) {
        result.set(edge)
        unionFind.union(edge._1, edge._2)
      }
    }

    result
  }

}
