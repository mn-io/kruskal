

object Kruskal {
  def findShortestPath(graph: Graph): Graph = {
    val availableEdges = graph.getSortedEdges
    if(availableEdges.length <= 1) {
      return graph
    }

    val result = new Graph(graph.size)
    val unionFind = new UnionFind(graph.getNodes)
    for (edge <- availableEdges) {
      if (unionFind.find(edge._1) != unionFind.find(edge._2)) {
        result.set(edge)
        unionFind.union(edge._1, edge._2)
      }
    }

    result
  }
}
