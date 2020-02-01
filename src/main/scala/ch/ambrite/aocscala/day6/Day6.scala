package ch.ambrite
package aocscala
import scala.collection.immutable.HashMap
import scala.io.Source

object Day6 {
  def formatInput(raw: String): Seq[(String, String)] = raw.linesIterator.toSeq.map(splitOrbit)

  def splitOrbit(raw: String): (String, String) = {
    val split = raw.split("\\)")
    (split(0), split(1))
  }
  
  def buildHashmap(hm: HashMap[String, Int], orbit: (String, String)): HashMap[String, Int] = {
    val (k, v) = orbit
    val parentOrbits: Int = if (hm.contains(k)) hm(k) else 0
    hm + (v -> (parentOrbits + 1))
  }

  def countOrbits(hm: HashMap[String, Int]): Int = hm.values.foldLeft(0) { _+_ }

  def buildFullMap(singleInstructions: Seq[(String, String)]): HashMap[String, Int] = {
    val emptyMap: HashMap[String, Int] = new HashMap()
    singleInstructions.foldLeft(emptyMap){ (a, v) => buildHashmap(a, v) }
  }

  def computeInstructions(allInstructions: String): Int = {
    val unsortedInstructions = formatInput(allInstructions)
    // val unsortedOrbits: Seq[(String, String)] = unsortedInstructions.map(splitOrbit)
    val finalMap = buildFullMap(unsortedInstructions)
    countOrbits(finalMap)
  }

  def day6_1(): Int = {
    val source = Source.fromFile("inputs/inputDay6.txt").mkString
    computeInstructions(source)
  }
}
