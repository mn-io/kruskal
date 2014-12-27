
class TreeTest extends MainTest {
  "Tree" should "be working" in {
    var nodes: List[(Int, Int, Int)] = new Graph(3).getNodes()
    val root = new Tree()
    root.node = nodes.head
    nodes = nodes.tail;

    root.left = new Tree()
    root.left.node = nodes.head
    nodes = nodes.tail

    root.right = new Tree()
    root.right.node = nodes.head
    nodes = nodes.tail

    print(root)
  }
}
