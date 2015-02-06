class Graph(nodes: Int) {
  require(nodes > 0)

  private val matrix = initUndirectedMatrix

  def initUndirectedMatrix = {
    val matrix = Array.ofDim[Array[Int]](nodes)
    for (i <- 0 to matrix.length - 1) {
      matrix(i) = Array.ofDim[Int](i)
    }
    matrix
  }

  def get(m: Int, n: Int) = {
    val sorted = swap(m, n)
    matrix(sorted._1)(sorted._2)
  }

  def unset(m: Int, n: Int) = set((m, n, Graph.NO_CONNECTION_WEIGHT))

  def set(edge: (Int, Int, Int)) = {
    val sorted = swap(edge._1, edge._2)
    matrix(sorted._1)(sorted._2) = edge._3
  }

  def getEdges = {
    var edges = Set.newBuilder[(Int, Int, Int)]
    for (i <- 0 to matrix.length - 1; j <- 0 to matrix(i).length - 1) {
      val weight = get(j, i)
      if (weight > Graph.NO_CONNECTION_WEIGHT) {
        edges += ((j, i, weight))
      }
    }
    edges.result
  }

  def getSortedEdges = {
    val array: Array[(Int, Int, Int)] = getEdges.to[Array]
    scala.util.Sorting.stableSort(array, sortEdgeAscending)
    array.toList
  }

  def getNodes = List.range(0, matrix.length).toSet

  def fill(callback: () => Int) = {
    for (i <- 0 to matrix.length - 1; j <- 0 to matrix(i).length - 1) {
      set((i, j, callback()))
    }
  }

  def randomFill(max: Int) = {
    fill(() => scala.util.Random.nextInt(max) + 1)
  }

  override def toString = {
    var out = "    " + 0.to(getNodes.size - 1).mkString(" ") + "\n"
    out += "   " + "-" * getNodes.size * 2 + "\n"

    var i = 0

    for (m <- matrix) {
      out += i + " |"

      for (n <- m) {
        out += " "
        if (n == Graph.NO_CONNECTION_WEIGHT) {
          out += " "
        } else {
          out += n
        }
      }

      out += "\n"
      i = i + 1
    }
    out
  }

  private def swap(m: Int, n: Int) = {
    if (m > n) (m, n)
    else (n, m)
  }

  private def sortEdgeAscending = (e1: (Int, Int, Int), e2: (Int, Int, Int)) => e1._3 < e2._3
}

object Graph {
  val NO_CONNECTION_WEIGHT = 0

  def apply(nodes: Int) = {
    new Graph(nodes)
  }
}