package ch.ambrite
package aocscala
package day9
package core

import operations.Day9Operations._

case object Day9 {
  type OpProgram = Seq[Int]
  type Inputs = Seq[Int]
  type Phases = Seq[Int]
  type OpOutput = (Int, Int, OpProgram)

  val input_day9_1 = Seq(3,8,1001,8,10,8,105,1,0,0,21,34,51,68,89,98,179,260,341,422,99999,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,1002,9,5,9,1001,9,2,9,1002,9,2,9,4,9,99,3,9,1001,9,3,9,102,3,9,9,101,4,9,9,4,9,99,3,9,102,2,9,9,101,2,9,9,1002,9,5,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99)
  val input_day9_2 = Seq(3,8,1001,8,10,8,105,1,0,0,21,34,51,68,89,98,179,260,341,422,99999,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,1002,9,5,9,1001,9,2,9,1002,9,2,9,4,9,99,3,9,1001,9,3,9,102,3,9,9,101,4,9,9,4,9,99,3,9,102,2,9,9,101,2,9,9,1002,9,5,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99)

  final case class Opmode(code: Int, param1: Boolean, param2: Boolean, param3: Boolean) {
  }

  def getOpmode(opcode: Int): Opmode = {
    val digitsReversed: Seq[Int] = opcode.toString.map(_.asDigit).reverse
    def safeGet(seq: Seq[Int], idx: Int): Int = if (seq.length > idx) seq(idx) else 0
    def getDigit(idx: Int): Int = safeGet(digitsReversed, idx)
    def makeBoolFromDigit(idx: Int): Boolean = if (getDigit(idx) == 1) true else false
    Opmode(getDigit(0) + getDigit(1) * 10,makeBoolFromDigit(2),makeBoolFromDigit(3),makeBoolFromDigit(4))
  }

  def processAdvancedOps(seq: OpProgram, index: Int, input: Inputs): OpOutput = {
    if (index >= seq.length) {
      (-1000, -1000, seq) 
    } else {
      val op = seq(index)
      val Opmode(code, p1, p2, p3) = getOpmode(op)
      code match {
        case 1 => processOp(seq, input, 4, p1, p2, p3, index, _ + _)
        case 2 => processOp(seq, input, 4, p1, p2, p3, index, _ * _)
        case 3 => processInput(seq, input, 2, index)
        case 4 => processOutput(seq, 2, p1, index)
        case 5 => processJump(seq, input, _ != 0,  3, p1, p2, index)
        case 6 => processJump(seq, input, _ == 0,  3, p1, p2, index)
        case 7 => processSetAtPosition(seq, input,  _ < _,  4, p1, p2, index)
        case 8 => processSetAtPosition(seq, input, _ == _,  4, p1, p2, index)
        case 99 => (seq.head, -99, seq)
        case opcode => {
          println("Error opCode: " + opcode)
          (-2, -2, seq)
        }
      }
    }
  }

  def sequencer(program: OpProgram, phases: Phases, prev: OpOutput): Int = {
    val (prevOutput, _, _) = prev
    if (phases.length > 0) {
      val output = processAdvancedOps(program, 0, Seq(phases.head, prevOutput))
      sequencer(program, phases.tail, output)
    } else {
      prevOutput
    }
  }

  var uhoh: Int = 0
  def statefulSequencer(programs: Seq[OpProgram], phases: Phases, indexState: Seq[Int], thisTransformerIndex: Int, prev: OpOutput): Int = {
    val (prevOutput, _, _) = prev
    if (uhoh > 10000000) {
      // avoid infinite loops
      prevOutput
    } else {
      uhoh = uhoh + 1
      val entryIndex = indexState(thisTransformerIndex)
      val program = programs(thisTransformerIndex)
      val inputs = if (entryIndex == 0) Seq(phases(thisTransformerIndex), prevOutput) else Seq(prevOutput)
      val (output, index, updatedProgram) = processAdvancedOps(program, entryIndex, inputs)
      val newIndexState = indexState.updated(thisTransformerIndex, index)
      val newPrograms = programs.updated(thisTransformerIndex, updatedProgram)
      val nextTransformer = (1 + thisTransformerIndex) % indexState.length
      if (index == -99) {
        prevOutput
      } else {
        statefulSequencer(newPrograms, phases, newIndexState, nextTransformer, (output, index, updatedProgram))
      }
    }
  }

  def trialAndError(program: OpProgram): Int = {
    val permutations = (0 to 4).permutations
    permutations.foldLeft(0) { (a, v) => Math.max(a, sequencer(program, v, (0, 0, Seq()))) }
  }

  def trialAndErrorStateful(program: OpProgram): Int = {
    val permutations = (5 to 9).permutations
    val programs = Seq.fill(5)(program)
    val indices = Seq.fill(5)(0)

    permutations.foldLeft(0) { (a, v) => Math.max(a, statefulSequencer(programs, v, indices, 0, (0, 0, Seq()))) }
  }

  def day9_1(): Int = {
    trialAndError(input_day9_1)
  }

  def day9_2(): Int = {
    trialAndErrorStateful(input_day9_2)
  }

}
