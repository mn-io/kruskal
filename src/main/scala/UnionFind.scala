import scala.collection._

class UnionFind(elements: immutable.Set[Int]) {

  private val sets = makeSet(elements)

  def makeSet(elements: Set[Int]) = {
    val map = mutable.HashMap[Int, Set[Int]]()
    for (element: Int <- elements) {
      var set = mutable.HashSet[Int]()
      set += element
      map += (element -> set)
    }
    map
  }

  def union(elementX: Int, elementY: Int): Unit = {
    if (elementX == elementY) return

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
    throw new IllegalArgumentException(UnionFind.NO_SET_FOUND + element)
  }

  def getSet = {
    sets.toMap
  }
}

object UnionFind {
  val NO_SET_FOUND: String = "Element does not belong to any set: "


  def apply(elements: immutable.Set[Int]) = {
    new UnionFind(elements)
  }
}
