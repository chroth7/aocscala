package ch.ambrite
package aocscala
package day8

import Day8._

class Day8Suite extends TestSuite {
  test("Layers!") {
    makeLayers("12341234", 2) shouldBe Seq(Seq(1,2), Seq(3,4), Seq(1,2), Seq(3,4))
    makeLayers("12341234", 4) shouldBe Seq(Seq(1,2,3,4), Seq(1,2,3,4))

    val layers = makeLayers("000100200123", 3)
    findMinZeroLayer(layers) shouldBe Seq(1,2,3)

    val layers2 = makeLayers("330100200123", 3)
    findMinZeroLayer(layers2) shouldBe Seq(1,2,3)
  }

  test("Colors!") {
    overlayLayer(Seq(0,1,2,2,1,0), Seq(3,3,3,3,3,3)) shouldBe Seq(0,1,3,3,1,0)
    finalColors("0222112222120000", 4) shouldBe "0110"
    finalColors("1222112222120000", 4) shouldBe "1110"
    finalColors("2222112222120000", 4) shouldBe "1110"
  }
}
