object Kruskal {

  def findShortestPath(graph: Graph): Graph = {             // def: n = |Nodes|, e = |Edges|
    val result = Graph(graph.getNodes.size)                 // n
    val unionFind = UnionFind(graph.getNodes)               // n

    var edges = graph.getSortedEdges                        // e * log(e)

    while (unionFind.getSet.size > 1 && edges.size > 0) {   // n*(
      val edge = edges(0)                                   //   1

      val edgesConnected = 
        unionFind.find(edge._1) == unionFind.find(edge._2)  //   2e
      if (!edgesConnected) {                                //   1
        result.set(edge)                                    //   1
        unionFind.union(edge._1, edge._2)                   //   2e
      }

      edges = edges.tail                                    //   n
    }                                                       // )

    result
  }
}
