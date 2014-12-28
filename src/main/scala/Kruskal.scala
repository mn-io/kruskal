
object Kruskal {
  def findShortestPath(graph : Graph) : Tree = {
    var result = new Tree
    var nodes: List[(Int, Int, Int)] = graph.getSortedEdges
    result.node = nodes.head
    nodes = nodes.tail
    for(node <- nodes) {
      //Todo: code goes here for inserting nodes into tree(s)
    }
    result
  }
}
