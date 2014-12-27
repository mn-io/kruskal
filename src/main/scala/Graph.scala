
class Graph(nodes: Int) {

  private val matrix = Array.ofDim[Int](nodes, nodes)

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
    var i = 0
    for (m <- matrix) {
      var j = 0
      for (n <- m) {
        set(i, j, callback())
        j += 1
      }
      i += 1
    }
  }

  def randomFill(max : Int) = {
    fill(() => {
      scala.util.Random.nextInt(max)+1
    })
  }

  def get(m: Int, n: Int): Int = {
    matrix(m)(n)
  }

  def set(m: Int, n: Int, value: Int) = {
    matrix(m)(n) = value
  }

  def length = {
    matrix.length
  }
}
