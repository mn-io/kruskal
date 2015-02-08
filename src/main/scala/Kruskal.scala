object Kruskal {

  def findShortestPath(graph: Graph): Graph = {
    val result = Graph(graph.getNodes.size)
    val unionFind = UnionFind(graph.getNodes)

    var edges = graph.getSortedEdges

    for (edge <- edges) {
      //if(unionFind.size <= 1) return result
      val hasMaximumEdges = result.getEdges.size >= graph.getNodes.size - 1
      if (hasMaximumEdges) return result

      val edgesConnected =
        unionFind.find(edge._1) == unionFind.find(edge._2)
      if (!edgesConnected) {
        result.set(edge)
        unionFind.union(edge._1, edge._2)
      }
    }

    result
  }
}
