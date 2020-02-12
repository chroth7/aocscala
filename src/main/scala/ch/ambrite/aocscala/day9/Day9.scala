package ch.ambrite
package aocscala
package day9
package core

import program.IntProgram

case object Day9 {
  type OpProgram = Seq[Int]
  type Inputs = Seq[Int]
  type Phases = Seq[Int]
  type Index = Int
  type Output = Int
  type OpOutput = (Output, Index, OpProgram)

  val input_day9_1 = Seq(3,8,1001,8,10,8,105,1,0,0,21,34,51,68,89,98,179,260,341,422,99999,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,1002,9,5,9,1001,9,2,9,1002,9,2,9,4,9,99,3,9,1001,9,3,9,102,3,9,9,101,4,9,9,4,9,99,3,9,102,2,9,9,101,2,9,9,1002,9,5,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99)
  val input_day9_2 = Seq(3,8,1001,8,10,8,105,1,0,0,21,34,51,68,89,98,179,260,341,422,99999,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,1002,9,5,9,1001,9,2,9,1002,9,2,9,4,9,99,3,9,1001,9,3,9,102,3,9,9,101,4,9,9,4,9,99,3,9,102,2,9,9,101,2,9,9,1002,9,5,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99)


  def sequencer(program: IntProgram, phases: Phases): Int = {
    val IntProgram(prevOutput, prevProgram, _, _) = program
    if (phases.length > 0) {
      val newProgram = IntProgram(0, prevProgram, 0, Seq(phases.head, prevOutput)).processAdvancedOps()
      sequencer(newProgram, phases.tail)
    } else {
      prevOutput
    }
  }

  def trialAndError(program: OpProgram): Int = {
    val permutations = (0 to 4).permutations
    // permutations.foldLeft(0) { (a, v) => Math.max(a, sequencer(program, v, (0, 0, Seq()))) }
    permutations.foldLeft(0) { (a, v) => Math.max(a, sequencer(IntProgram(0, program, 0,  Seq()), v)) }
  }

  var uhoh: Int = 0
  def statefulSequencer(programs: Seq[IntProgram], phases: Phases, thisTransformerIndex: Int): Int = {
    val prevTransformerIndex = (programs.length + thisTransformerIndex - 1) % programs.length
    val prev = programs(prevTransformerIndex)
    val IntProgram(prevOutput, _, _, _) = prev
    if (uhoh > 10000) {
      // avoid infinite loops
      println("OVERFLOW!")
      prevOutput
    } else {
      uhoh = uhoh + 1
      val IntProgram(_, program, entryIndex, _) = programs(thisTransformerIndex)
      val inputs = if (entryIndex == 0) Seq(phases(thisTransformerIndex), prevOutput) else Seq(prevOutput)
      val updatedProgram@IntProgram(_, _, index, _) = IntProgram(0, program, entryIndex, inputs).processAdvancedOps()
      val newPrograms = programs.updated(thisTransformerIndex, updatedProgram)
      val nextTransformer = (1 + thisTransformerIndex) % programs.length
      if (index == -99) {
        prevOutput
      } else {
        statefulSequencer(newPrograms, phases, nextTransformer)
      }
    }
  }


  def trialAndErrorStateful(program: OpProgram): Int = {
    val permutations = (5 to 9).permutations
    val intProgram = IntProgram(0, program, 0, Seq())
    val programs = Seq.fill(5)(intProgram)

    permutations.foldLeft(0) { (a, v) => Math.max(a, statefulSequencer(programs, v, 0)) }
    // permutations.foldLeft(0) { (a, v) => Math.max(a, statefulSequencer(programs, v, indices, 0, (0, 0, Seq()))) }
  }

  def day9_1(): Int = {
    trialAndError(input_day9_1)
  }

  def day9_2(): Int = {
    trialAndErrorStateful(input_day9_2)
  }

}
