import scala.collection.mutable.ListBuffer

object Kruskal {
  def findShortestPath(graph: Graph): Graph = {
    val result = new Graph(graph.size)

    var availableEdges = graph.getSortedEdges.toBuffer
    var availableNodes = graph.getNodes.toBuffer
    var visitedNodes = new ListBuffer[Int]

    val currentEdge = availableEdges.head
    println("Init with:" + currentEdge)

    result.set(currentEdge)
    visitedNodes += currentEdge._1
    visitedNodes += currentEdge._2
    availableNodes = availableNodes.diff(visitedNodes)
    availableEdges = availableEdges.tail

    result

    for(currentEdge <- availableEdges) {
      println("Continue with:" + currentEdge)
      if(visitedNodes.indexOf(currentEdge._1) >= 0 && visitedNodes.indexOf(currentEdge._2) >= 0) {
        println("Ooops, loop detected with " + currentEdge)
      } else {
        result.set(currentEdge)
        visitedNodes += currentEdge._1
        visitedNodes += currentEdge._2
        visitedNodes = visitedNodes.distinct
        availableNodes = availableNodes.diff(visitedNodes)
      }
    }

    result
  }

//  def getDirectNodesFromNode(node: Int, edges: List[(Int, Int, Int)]): List[Int] = {
//    var result = List.newBuilder[Int]
//    for (edge <- edges) {
//      if (edge._3 != Graph.NO_CONNECTION_VALUE && (edge._1 == node || edge._2 == node)) {
//        if (edge._1 == node) {
//          result += edge._2
//        } else if (edge._2 == node) {
//          result += edge._1
//        }
//      }
//    }
//    result.result
//  }

  //  def getAllNodesFromNode(startNode: Int, allEdges: List[(Int, Int, Int)]): List[Int] = {
  //    var neighbours = getDirectNodesFromNode(startNode, allEdges)
  //    var connections = List.newBuilder[Int]
  //
  //    println("start: " + startNode + " with neighbours: " + neighbours)
  //    for (currentNode <- neighbours) {
  //      connections ++= getAllNodesFromNodeHelper(currentNode, startNode, allEdges)
  //      println("found: " + currentNode + " with neighbours: " + connections)
  //    }
  //    neighbours ++= connections.result
  //    neighbours
  //  }
  //
  //  private def getAllNodesFromNodeHelper(currentNode: Int, prevNode: Int, edges: List[(Int, Int, Int)]): List[Int] = {
  //    var neighbours: List[Int] = getDirectNodesFromNode(currentNode, edges)
  //    for(neighbour <- neighbours) {
  //      if(neighbour == prevNode) {
  //        neighbours = neighbours.diff(List(neighbour))
  //      }
  //    }
  //    neighbours
  //  }
}
