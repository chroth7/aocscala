package ch.ambrite
package aocscala
package day7
package core

import operations.Day7Operations._

case object Day7 {
  type OpProgram = Seq[Int]
  type Inputs = Seq[Int]
  type Phases = Seq[Int]

  val input_day7_1 = Seq(3,8,1001,8,10,8,105,1,0,0,21,34,51,68,89,98,179,260,341,422,99999,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,1002,9,5,9,1001,9,2,9,1002,9,2,9,4,9,99,3,9,1001,9,3,9,102,3,9,9,101,4,9,9,4,9,99,3,9,102,2,9,9,101,2,9,9,1002,9,5,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99)

  final case class Opmode(code: Int, param1: Boolean, param2: Boolean, param3: Boolean) {
  }

  def getOpmode(opcode: Int): Opmode = {
    val digitsReversed: Seq[Int] = opcode.toString.map(_.asDigit).reverse
    def safeGet(seq: Seq[Int], idx: Int): Int = if (seq.length > idx) seq(idx) else 0
    def getDigit(idx: Int): Int = safeGet(digitsReversed, idx)
    def makeBoolFromDigit(idx: Int): Boolean = if (getDigit(idx) == 1) true else false
    Opmode(getDigit(0) + getDigit(1) * 10,makeBoolFromDigit(2),makeBoolFromDigit(3),makeBoolFromDigit(4))
  }

  def processAdvancedOps(seq: OpProgram, index: Int, input: Inputs): Int = {
    if (seq(index) != 99 && index > seq.length - 3) -1 else {
      val op = seq(index)
      val Opmode(code, p1, p2, p3) = getOpmode(op)
      code match {
        case 1 => processOp(seq, input, 4, p1, p2, p3, index, _ + _)
        case 2 => processOp(seq, input, 4, p1, p2, p3, index, _ * _)
        case 3 => processInput(seq, input, 2, index)
        case 4 => processOutput(seq, input,  2, p1, index)
        case 5 => processJump(seq, input, _ != 0,  3, p1, p2, index)
        case 6 => processJump(seq, input, _ == 0,  3, p1, p2, index)
        case 7 => processSetAtPosition(seq, input,  _ < _,  4, p1, p2, index)
        case 8 => processSetAtPosition(seq, input, _ == _,  4, p1, p2, index)
        case 99 => seq.head
        case opcode => {
          println("Error opCode: " + opcode)
          -2
        }
      }
    }
  }

  def sequencer(program: OpProgram, phases: Phases, prevOutput: Int): Int = {
    if (phases.length > 0) {
      val output = processAdvancedOps(program, 0, Seq(phases.head, prevOutput))
      sequencer(program, phases.tail, output)
    } else {
      prevOutput
    }
  }

  def trialAndError(program: OpProgram): Int = {
    val permutations = (0 to 4).permutations
    permutations.foldLeft(0) { (a, v) => Math.max(a, sequencer(program, v, 0)) }
  }

  def day7_1(): Int = {
    trialAndError(input_day7_1)
  }

  def day7_2(): Int = {
    processAdvancedOps(input_day7_1, 0, Seq(5))
  }


}
