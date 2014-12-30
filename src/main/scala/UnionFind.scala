import scala.collection._

class UnionFind(elements: immutable.Set[Int]) {

  private val sets = makeSet(elements)

  def union(elementX: Int, elementY: Int): Unit = {
    if(elementX == elementY) {
      return
    }

    val rootX = find(elementX)
    val rootY = find(elementY)

    val setX = sets(rootX)
    val setY = sets(rootY)

    val unionSet = setX | setY
    sets -= rootY
    sets += (rootX -> unionSet)
  }

  def find(element: Int): Int = {
    for (set <- sets if set._2 contains (element)) yield return set._1
    throw new IllegalArgumentException("Element " + element + " does not belong to any set.")
  }

  def makeSet(elements: Set[Int]): mutable.Map[Int, Set[Int]] = {
    val map = new mutable.HashMap[Int, Set[Int]]

    for (element: Int <- elements) {
      var set = new mutable.HashSet[Int]()
      set += element
      map += (element -> set)
    }
    map
  }

  def getSet() = {
    sets.toMap
  }

}
