

class Graph(var nodes: Int) {
  require(nodes > 0)

  private val matrix = initUndirectedMatrix

  def initUndirectedMatrix = {
    var j = 0 // first element of matrix is intended to be blank on purpose to keep logical indexes
    val matrix = Array.ofDim[Array[Int]](nodes)
    for (i <- 0 to matrix.length - 1) {
      matrix(i) = Array.ofDim[Int](j)
      j += 1
    }
    matrix
  }

  def get(m: Int, n: Int) = {
    val sorted = swap(m, n)
    matrix(sorted._1)(sorted._2)
  }

  def unset(m: Int, n: Int) = set((m, n, Graph.NO_CONNECTION_VALUE))

  def set(edge: (Int, Int, Int)) = {
    val sorted = swap(edge._1, edge._2)
    matrix(sorted._1)(sorted._2) = edge._3
  }

  def getEdges = {
    var edges = Set.newBuilder[(Int, Int, Int)]
    for (i <- 0 to matrix.length - 1) {
      for (j <- 0 to matrix(i).length - 1) {
        val node = get(j, i)
        if (node > Graph.NO_CONNECTION_VALUE) {
          edges += ((j, i, node))
        }
      }
    }
    edges.result
  }

  def getSortedEdges = {
    val array: Array[(Int, Int, Int)] = getEdges.to[Array]
    scala.util.Sorting.stableSort(array, sortEdgeAscending)
    array.toList
  }

  def getNodes: Set[Int] = List.range(0, matrix.length).toSet

  def fill(callback: () => Int) = {
    for (i <- 0 to matrix.length - 1) {
      for (j <- 0 to matrix(i).length - 1) {
        set((i, j, callback()))
      }
    }
  }

  def randomFill(max: Int) = {
    fill(() => scala.util.Random.nextInt(max) + 1)
  }

  def size = matrix.length

  override def toString = {
    var out = ""
    for (m <- matrix) {
      for (n <- m) {
        out += " " + n
      }
      out += "\n"
    }
    out
  }

  private def swap(m: Int, n: Int) = {
    if (m > n) {
      (m, n)
    } else {
      (n, m)
    }
  }

  private def sortEdgeAscending = (e1: (Int, Int, Int), e2: (Int, Int, Int)) => e1._3 < e2._3
}

object Graph {
  val NO_CONNECTION_VALUE = 0
}