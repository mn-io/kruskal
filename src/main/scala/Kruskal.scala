object Kruskal {

  def findShortestPath(graph: Graph): Graph = {
    if (graph.getEdges.size <= 1) return graph

    val result = Graph(graph.getNodes.size)
    val unionFind = UnionFind(graph.getNodes)

    var edges = graph.getSortedEdges          // O(e * log(e))

    while (edges.size > 0) {
      val edge = edges(0)

      val isMSTConstraint = result.getEdges.size >= graph.getNodes.size - 1
      //val isMSTConstraint = unionFind.getSet.size == 1
      //nur (n-1)-mal union() möglich, bis Mengen nicht mehr disjunkt sind (Anzahl Mengen = size == 1)
      if (isMSTConstraint) return result

      val edgesConnected = unionFind.find(edge._1) == unionFind.find(edge._2)
      if (!edgesConnected) {
        result.set(edge)
        unionFind.union(edge._1, edge._2)
      }

      edges = edges.tail
    }

    return result
  }
}
