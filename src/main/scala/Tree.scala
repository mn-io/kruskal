
class Tree {
  var left : Tree = null
  var right : Tree = null
  var node : Tuple3[Int, Int, Int] = null

  override def toString = {
    var result = ""
    if(left != null) {
      result += left
    }
    if(node != null) {
      result += node
    }
    if(right != null) {
      result += right
    }
    " [" + result + "] "
  }
}
