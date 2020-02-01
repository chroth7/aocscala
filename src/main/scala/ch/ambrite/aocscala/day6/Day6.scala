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
  type OrbitMap = HashMap[String, Int]

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
  
  def buildHashmap(hm: OrbitMap, orbit: Orbit): OrbitMap = {
    val (k, v) = orbit
    val parentOrbits: Int = if (hm.contains(k)) hm(k) else 0
    hm + (v -> (parentOrbits + 1))
  }

  def countOrbits(hm: OrbitMap): Int = hm.values.foldLeft(0) { _+_ }

  def buildFullMap(singleInstructions: Orbits): OrbitMap = {
    val emptyMap: OrbitMap = new HashMap()
    singleInstructions.foldLeft(emptyMap){ (a, v) => buildHashmap(a, v) }
  }

  def computeOrbitMap(allInstructions: String): OrbitMap = {
    val unsortedInstructions = formatInput(allInstructions)
    val sortedInstructions = sortInput(unsortedInstructions)
    buildFullMap(sortedInstructions)
  }


  def day6_1(): Int = {
    val source = Source.fromFile("inputs/inputDay6.txt").mkString
    val orbitMap = computeOrbitMap(source)
    countOrbits(orbitMap)
  }

  def day6_2(): Int = {
    val source = Source.fromFile("inputs/inputDay6.txt").mkString
    val orbitMap = computeOrbitMap(source)
    orbitMap.size
  }
}
