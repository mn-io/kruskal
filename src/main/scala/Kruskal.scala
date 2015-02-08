object Kruskal {

  def findShortestPath(graph: Graph): Graph = {             // def: n = |Nodes|, e = |Edges|
    val result = Graph(graph.getNodes.size)                 // n
    val unionFind = UnionFind(graph.getNodes)               // n

    var edges = graph.getSortedEdges                        // e * log(e)
    
    for(edge <- edges) {                                    // e*(
      //if(unionFind.size <= 1) return result
      val hasMaximumEdges = result.getEdges.size >= graph.getNodes.size - 1 //3
      if(hasMaximumEdges) return result                     //   1

      val edgesConnected = 
        unionFind.find(edge._1) == unionFind.find(edge._2)  //   2e
      if (!edgesConnected) {                                //   1
        result.set(edge)                                    //   1
        unionFind.union(edge._1, edge._2)                   //   2e
      }
    }                                                       // )

    result
  }
}
