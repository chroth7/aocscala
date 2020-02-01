package ch.ambrite
package aocscala
import scala.collection.immutable.HashMap
import scala.io.Source

// NOTE: when first doing this exercise, I thought the orbits come in order, hence the implementation
// As I noticed that the can come out of order, I monkey-patched with a sort procedure
// In hindsight, a tree like structure might have been way more elegant
// (note written after part one/day 6)

object Day6 {
  type Orbit = (String, String)
  type Orbits = List[Orbit]

  def formatInput(raw: String): Orbits = raw.linesIterator.toList.map(splitOrbit)

  def sortInput(unsorted: Orbits): Orbits = {
    def processKeySort(done: Orbits, todo: Orbits, keys: Seq[String]): (Orbits, Orbits) = {
      if (todo.size > 0) {
        val connectedOrbits: Orbits = todo.filter(orbit => keys.exists(_ == orbit._1))
        val unconnectedOrbits: Orbits = todo.filter(orbit => !keys.exists(_ == orbit._1))
        val newKeys: Seq[String] = connectedOrbits.map(orbit => orbit._2)

        processKeySort(done ++ connectedOrbits, unconnectedOrbits, newKeys)
      } else {
        (done, todo) 
      }
    }
    
    processKeySort(List(), unsorted, Seq("COM"))._1
  }

  def splitOrbit(raw: String): Orbit = {
    val split = raw.split("\\)")
    (split(0), split(1))
  }
  
  def buildHashmap(hm: HashMap[String, Int], orbit: Orbit): HashMap[String, Int] = {
    val (k, v) = orbit
    val parentOrbits: Int = if (hm.contains(k)) hm(k) else 0
    hm + (v -> (parentOrbits + 1))
  }

  def countOrbits(hm: HashMap[String, Int]): Int = hm.values.foldLeft(0) { _+_ }

  def buildFullMap(singleInstructions: Orbits): HashMap[String, Int] = {
    val emptyMap: HashMap[String, Int] = new HashMap()
    singleInstructions.foldLeft(emptyMap){ (a, v) => buildHashmap(a, v) }
  }

  def computeInstructions(allInstructions: String): Int = {
    val unsortedInstructions = formatInput(allInstructions)
    val sortedInstructions = sortInput(unsortedInstructions)
    // val unsortedOrbits: List[(String, String)] = unsortedInstructions.map(splitOrbit)
    val finalMap = buildFullMap(sortedInstructions)
    countOrbits(finalMap)
  }

  def day6_1(): Int = {
    val source = Source.fromFile("inputs/inputDay6.txt").mkString
    computeInstructions(source)
  }
}
