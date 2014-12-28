

class Graph(var nodes: Int) {
  require(nodes > 0)

  private val matrix = initUndirectedMatrix

  def initUndirectedMatrix = {
    var j = 0 // first element of matirx is intended to be blank on purpose to keep logical indexes
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
        set(i, j, callback())
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

  def set(m: Int, n: Int, value: Int) = {
    val sorted: (Int, Int) = swap(m, n)
    matrix(sorted._1)(sorted._2) = value
  }

  private def swap(m: Int, n: Int) = {
    if (m > n) {
      (m, n)
    } else {
      (n, m)
    }
  }

  def length = {
    matrix.length
  }

  def getSortedEdges = {
    var edges = List.newBuilder[(Int, Int, Int)]
    for (i <- 0 to matrix.length - 1) {
      for (j <- 0 to matrix(i).length - 1) {
        edges += Tuple3(j, i, get(j, i))
      }
    }
    val array = edges.result.to[Array]
    scala.util.Sorting.stableSort(array, (e1: (Int, Int, Int), e2: (Int, Int, Int)) => e1._3 < e2._3)
    array.to[List]
  }

  def getNodes = {
    var nodes = List.newBuilder[Int]
    for (i <- 0 to matrix.length - 1) {
      nodes += i
    }
    nodes.result
  }
}