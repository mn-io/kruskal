
class Graph(nodes: Int) {

  private val matrix = Array.ofDim[Int](nodes,nodes)

  override def toString = {
    var out = ""
    for(m <- matrix) {
      for(n <- m) {
        out += n
      }
      out += "\n"
    }
    out
  }

  def length = {
    matrix.length
  }
}
