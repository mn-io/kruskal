object Kruskal {

  def findShortestPath(graph: Graph): Graph = {
    if (graph.getEdges.size <= 1) return graph
    
    val result = Graph(graph.getNodes.size)
    val unionFind = UnionFind(graph.getNodes)         // init: O(n)

    val edges = graph.getSortedEdges                  // O(e * log(e))
    
    for (edge <- edges) { //if result.getNodes.size <= graph.getEdges.size
      val edgesConnected = unionFind.find(edge._1) == unionFind.find(edge._2)
      if (!edgesConnected) {
        result.set(edge)
        unionFind.union(edge._1, edge._2)
      }
    }

    result
  }
}
