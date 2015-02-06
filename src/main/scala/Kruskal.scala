object Kruskal {

  def findShortestPath(graph: Graph): Graph = {
    if (graph.getEdges.size <= 1) return graph
    
    val result = Graph(graph.getNodes.size)
    val unionFind = UnionFind(graph.getNodes)         // init: O(n)

    val edges = graph.getSortedEdges                  // O(e * log(e))
    
    for (edge <- edges) {                             // O(n), da siehe val isMSTConstraint
      //val isMSTConstraint = result.getEdges.size >= graph.getNodes.size // return bei graph.getNodes.size (=n)
      val isMSTConstraint = unionFind.getSet.size == 1                    // nur n mal union() möglich, bis Mengen nicht mehr disjunkt sind (Anzahl Mengen = size == 1)
      if (isMSTConstraint) return result
      
      val edgesConnected = unionFind.find(edge._1) == unionFind.find(edge._2)
      if (!edgesConnected) {
        result.set(edge)
        unionFind.union(edge._1, edge._2)
      }
    }

    result
  }
}
