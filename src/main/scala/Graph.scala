

class Graph(var nodes: Int) {
  require(nodes > 0)

  private val matrix = initUndirectedMatrix

  def initUndirectedMatrix = {
    var j = 1
    val matrix = Array.ofDim[Array[Int]](nodes - 1)
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
    for (i <- 1 to matrix.length) {
      for (j <- 0 to i - 1) {
        // i-1 == matrix(i-1).length-1)
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
      (m - 1, n)
    } else {
      (n - 1, m)
    }
  }

  def length = {
    matrix.length + 1
  }

  def getNodes() = {
    var nodes = List.newBuilder[Tuple3[Int, Int, Int]]
    for (i <- 1 to matrix.length) {
      for (j <- 0 to i - 1) {
        nodes += Tuple3(j, i, get(j, i))
      }
    }
    val array = nodes.result().to[Array]

    scala.util.Sorting.stableSort(array, (e1: Tuple3[Int, Int, Int], e2: Tuple3[Int, Int, Int]) => e1._3 < e2._3)
    array.to[List]
  }
}