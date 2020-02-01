package ch.ambrite
package aocscala

import Day6._
import scala.collection.immutable.HashMap

class Day6Suite extends TestSuite {
  val testInput1 = """COM)B
B)C
D)E
B)G
E)F
J)K
G)H
C)D
D)I
E)J
K)L"""

  test("Orbits!") {
    val emptyHM: HashMap[String, Int] = new HashMap()
    val oneInstruction = buildHashmap(emptyHM, formatInput("A)B")(0))
    val twoInstructions = buildHashmap(oneInstruction, formatInput("B)C")(0))

    formatInput(testInput1).size shouldBe 11
    splitOrbit("A)B") shouldBe ("A", "B")
    oneInstruction.size shouldBe 1
    twoInstructions.size shouldBe 2
    twoInstructions("B") shouldBe 1
    twoInstructions("C") shouldBe 2

    countOrbits(emptyHM) shouldBe 0
    countOrbits(oneInstruction) shouldBe 1
    countOrbits(twoInstructions) shouldBe 3

    val fullMap = buildFullMap(sortInput(formatInput(testInput1)))
    fullMap("D") shouldBe 3
    fullMap("L") shouldBe 7

    computeInstructions(testInput1) shouldBe 42
  }

}
