package ch.ambrite
package aocscala
package day7

import core.Day7._
// import operations.Day7Operations._

class Day7Suite extends TestSuite {
  test("Op modes") {
    getOpmode(1002) shouldBe Opmode(2,false,true,false)
    getOpmode(1102) shouldBe Opmode(2,true,true,false)
    getOpmode(11102) shouldBe Opmode(2,true,true,true)
    getOpmode(99) shouldBe Opmode(99,false,false,false)
    getOpmode(104) shouldBe Opmode(4,true,false,false)
  }
  test("Test ops") {
    processAdvancedOps(Seq(99), 0, Seq(1))._1 shouldBe 99
    processAdvancedOps(Seq(1002,4,3,4,33), 0, Seq(1))._1 shouldBe 1002 
    processAdvancedOps(Seq(1002,4,2,0,99), 0, Seq(1))._1 shouldBe 198 
    // op mode 3
    processAdvancedOps(Seq(3,3,99,0), 0, Seq(1))._1 shouldBe 3 
    processAdvancedOps(Seq(3,0,99), 0, Seq(1))._1 shouldBe 1 
    // op mode 4 - changed in Day7
    processAdvancedOps(Seq(4,3,99,0), 0, Seq(1))._1 shouldBe 0
    processAdvancedOps(Seq(4,5,1002,6,2,0,99), 0, Seq(1))._1 shouldBe 0
    // add modes 5 6 7 8
    processAdvancedOps(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, Seq(7))._1 shouldBe 999 
    processAdvancedOps(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, Seq(8))._1 shouldBe 1000 
    processAdvancedOps(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, Seq(9))._1 shouldBe 1001 
  }

  val input1 = Seq(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
  val input2 = Seq(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0)
  val input3 = Seq(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0)
  test("Sequencer") {
    processAdvancedOps(input1, 0, Seq(4,0))._1 shouldBe 4
    processAdvancedOps(input1, 0, Seq(3,4))._1 shouldBe 43
    processAdvancedOps(input1, 0, Seq(2,43))._1 shouldBe 432
    processAdvancedOps(input1, 0, Seq(1,432))._1 shouldBe 4321
    processAdvancedOps(input1, 0, Seq(0,4321))._1 shouldBe 43210

    sequencer(input1, Seq(4,3,2,1,0), (0,0,Seq())) shouldBe 43210
    sequencer(input2, Seq(0,1,2,3,4), (0,0,Seq())) shouldBe 54321
    sequencer(input3, Seq(1,0,4,3,2), (0,0,Seq())) shouldBe 65210
  }

  val input4 = Seq(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5)
  val input5 = Seq(3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10)
  test("Stateful Sequencer") {

    // stateful, meeeeh
    val idxs = Seq.fill(5)(0)
    statefulSequencer(Seq.fill(5)(input4), Seq(9,8,7,6,5), idxs, 0, (0,0,Seq())) shouldBe 139629729
    statefulSequencer(Seq.fill(5)(input5), Seq(9,7,8,5,6), idxs, 0, (0,0,Seq())) shouldBe 18216
  }

  test("Trial and Error") {
    trialAndError(input1) shouldBe 43210
    trialAndError(input2) shouldBe 54321
    trialAndError(input3) shouldBe 65210

    // stateful
    trialAndErrorStateful(input4) shouldBe 139629729
  }
}
