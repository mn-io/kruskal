object Kruskal {

  def findShortestPath(graph: Graph): Graph = {
    if (graph.getEdges.size <= 1) return graph

    val result = Graph(graph.getNodes.size)
    val unionFind = UnionFind(graph.getNodes)

    var edges = graph.getSortedEdges                      // O(e * log(e))

    while (unionFind.getSet.size > 1 && edges.size > 0 ) { // O(n)
      val edge = edges(0)

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
