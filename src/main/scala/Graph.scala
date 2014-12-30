

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

  def fill(callback: () => Int) = {
    for (i <- 0 to matrix.length - 1) {
      for (j <- 0 to matrix(i).length - 1) {
        set((i, j, callback()))
      }
    }
  }

  def randomFill(max: Int) = {
    fill(() => {
      scala.util.Random.nextInt(max) + 1
    })
  }

  def get(m: Int, n: Int): Int = {
    val sorted: (Int, Int) = swap(m, n)
    matrix(sorted._1)(sorted._2)
  }

  def unset(m: Int, n: Int) = {
    set((m, n, Graph.NO_CONNECTION_VALUE))
  }

  def set(edge: (Int, Int, Int)): Unit = {
    val sorted: (Int, Int) = swap(edge._1, edge._2)
    matrix(sorted._1)(sorted._2) = edge._3
  }

  private def swap(m: Int, n: Int) = {
    if (m > n) {
      (m, n)
    } else {
      (n, m)
    }
  }

  def size = {
    matrix.length
  }

  def getEdges = {
    var edges = List.newBuilder[(Int, Int, Int)]
    for (i <- 0 to matrix.length - 1) {
      for (j <- 0 to matrix(i).length - 1) {
        val node = get(j, i)
        if (node > Graph.NO_CONNECTION_VALUE) {
          edges += Tuple3(j, i, node)
        }
      }
    }
    edges.result
  }

  def getSortedEdges = {
    val array: Array[(Int, Int, Int)] = getEdges.to[Array]
    scala.util.Sorting.stableSort(array, (e1: (Int, Int, Int), e2: (Int, Int, Int)) => e1._3 < e2._3)
    array.toList
  }

  def getNodes: Set[Int] = {
    var nodes = Set.newBuilder[Int]
    for (i <- 0 to matrix.length - 1) {
      nodes += i
    }
    nodes.result
  }
}

object Graph {
  val NO_CONNECTION_VALUE = 0
}