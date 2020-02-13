package ch.ambrite
package aocscala
package day9

import core.Day9._
// import operations.Day9Operations._
import program._

class Day9Suite extends TestSuite {
  test("Op modes") {
    IntProgram.getOpmode(1002) shouldBe Opmode(2,false,true,false)
    IntProgram.getOpmode(1102) shouldBe Opmode(2,true,true,false)
    IntProgram.getOpmode(11102) shouldBe Opmode(2,true,true,true)
    IntProgram.getOpmode(99) shouldBe Opmode(99,false,false,false)
    IntProgram.getOpmode(104) shouldBe Opmode(4,true,false,false)
  }
  test("Test ops") {
    val IntProgram(o1, _, i1, _) = IntProgram(Seq(99), 0, Seq(1)).processAdvancedOps()
    o1 shouldBe 99
    i1 shouldBe -99

    val IntProgram(o2, _, i2, _) = IntProgram(Seq(1002,4,3,4,33), 0, Seq(1)).processAdvancedOps()
    o2 shouldBe 1002
    i2 shouldBe -99
    
    val IntProgram(o3, _, i3, _) = IntProgram(Seq(1002,4,2,0,99), 0, Seq(1)).processAdvancedOps()
    o3 shouldBe 198
    i3 shouldBe -99
    
    // op mode 3
    val IntProgram(o4, _, i4, _) = IntProgram(Seq(3,3,99,0), 0, Seq(1)).processAdvancedOps()
    o4 shouldBe 3
    i4 shouldBe -99

    val IntProgram(o5, _, i5, _) = IntProgram(Seq(3,0,99), 0, Seq(1)).processAdvancedOps()
    o5 shouldBe 1
    i5 shouldBe -99

    // op mode 4 - changed in Day9
    val IntProgram(o6, _, i6, _) = IntProgram(Seq(4,3,99,0), 0, Seq(1)).processAdvancedOps()
    o6 shouldBe 0
    i6 shouldBe 2

    val IntProgram(o7, _, i7, _) = IntProgram(Seq(4,5,1002,6,2,0,99), 0, Seq(1)).processAdvancedOps()
    o7 shouldBe 0
    i7 shouldBe 2

    // add modes 5 6 7 8
    val IntProgram(o8, _, i8, _) = IntProgram(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, Seq(7)).processAdvancedOps()
    o8 shouldBe 999
    i8 shouldBe 33

    val IntProgram(o9, _, i9, _) = IntProgram(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, Seq(8)).processAdvancedOps()
    o9 shouldBe 1000
    i9 shouldBe 28

    val IntProgram(o10, _, i10, _) = IntProgram(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, Seq(9)).processAdvancedOps()
    o10 shouldBe 1001
    i10 shouldBe 42
  }

  val input1 = Seq(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
  val input2 = Seq(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0)
  val input3 = Seq(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0)
  test("Sequencer") {
    val IntProgram(o11, _, i11, _) = IntProgram(input1, 0, Seq(4,0)).processAdvancedOps()
    o11 shouldBe 4
    i11 shouldBe 14

    val IntProgram(o12, _, i12, _) = IntProgram(input1, 0, Seq(3,4)).processAdvancedOps()
    o12 shouldBe 43
    i12 shouldBe 14

    val IntProgram(o13, _, i13, _) = IntProgram(input1, 0, Seq(2,43)).processAdvancedOps()
    o13 shouldBe 432
    i13 shouldBe 14

    val IntProgram(o14, _, i14, _) = IntProgram(input1, 0, Seq(1,432)).processAdvancedOps()
    o14 shouldBe 4321
    i14 shouldBe 14

    val IntProgram(o15, _, i15, _) = IntProgram(input1, 0, Seq(0,4321)).processAdvancedOps()
    o15 shouldBe 43210
    i15 shouldBe 14

    val ip1 = IntProgram(input1, 0, Seq())
    sequencer(ip1, Seq(4,3,2,1,0)) shouldBe 43210

    val ip2 = IntProgram(input2, 0, Seq())
    sequencer(ip2, Seq(0,1,2,3,4)) shouldBe 54321
    
    val ip3 = IntProgram(input3, 0, Seq())
    sequencer(ip3, Seq(1,0,4,3,2)) shouldBe 65210
  }

  val input4 = Seq(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5)
  val input5 = Seq(3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10)
  test("Stateful Sequencer") {
    val ip4 = IntProgram(input4, 0, Seq())
    val ip4s = Seq.fill(5)(ip4)
    statefulSequencer(ip4s, Seq(9,8,7,6,5), 0) shouldBe 139629729

    val ip5 = IntProgram(input5, 0, Seq())
    val ip5s = Seq.fill(5)(ip5)
    statefulSequencer(ip5s, Seq(9,7,8,5,6), 0) shouldBe 18216
  }

  test("Trial and Error") {
    trialAndError(input1) shouldBe 43210
    trialAndError(input2) shouldBe 54321
    trialAndError(input3) shouldBe 65210

    // stateful
    trialAndErrorStateful(input4) shouldBe 139629729
  }
}
