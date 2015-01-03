import scala.collection._

class UnionFindTest extends MainTest {

  "UnionFind" should "init" in {
    val set = mutable.HashSet[Int]()
    set += 1
    set += 2
    set += 3

    val size: Int = 3

    set.size should be(size)
    val uf: UnionFind = UnionFind(set.toSet)

    uf.getSet.size should be(size)
  }

  "UnionFind" should "find root element" in {
    val set = mutable.HashSet[Int]()
    set += 1
    set += 2
    set += 3
    val uf: UnionFind = UnionFind(set.toSet)
    uf.find(2) should be(2)
  }

  "UnionFind" should "union two sets" in {
    val set = mutable.HashSet[Int]()
    set += 1
    set += 2
    set += 3
    val uf: UnionFind = UnionFind(set.toSet)

    uf.getSet.size should be(3)

    uf.union(2, 3)

    uf.getSet.size should be(2)
    uf.getSet(1).size should be(1)
    uf.getSet(2).size should be(2)
  }

  "UnionFind" should "find non root element after union" in {
    val set = mutable.HashSet[Int]()
    set += 1
    set += 2
    set += 3
    val uf: UnionFind = UnionFind(set.toSet)

    uf.union(2, 3)

    uf.find(2) should be(2)
    uf.find(3) should be(2)
  }

  "UnionFind" should "union all elements to first set" in {
    val set = mutable.HashSet[Int]()
    val range: List[Int] = List.range(0, 20)
    for (i <- range) {
      set += i
    }

    val uf: UnionFind = UnionFind(set.toSet)

    for (i <- range.tail) {
      uf.union(i - 1, i)
      uf.getSet.size should be(20 - i)
      uf.find(i) should be(0)
    }
    uf.getSet.size should be(1)
  }

  "UnionFind" should "union all elements to random set" in {
    val set = mutable.HashSet[Int]()
    val range: List[Int] = List.range(0, 20)
    for (i <- range) {
      set += i
    }

    val uf: UnionFind = UnionFind(set.toSet)

    for (i <- range.tail) {
      var rnd = scala.util.Random.nextInt(20)
      while (rnd == i) {
        rnd = scala.util.Random.nextInt(20)
      }
      uf.union(rnd, i)
      uf.find(i) should be(uf.find(rnd))
    }
  }

  "UnionFind" should "throw exception if element not found" in {
    val set = mutable.HashSet[Int]()
    set += 1
    set += 2
    set += 3

    val uf: UnionFind = UnionFind(set.toSet)

    a[IllegalArgumentException] should be thrownBy {
      uf.find(4)
    }
  }
}
