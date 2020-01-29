package ch.ambrite
package aocscala

import Day1._
import Day2._
import Day3._

object Main extends App {
  def divider() =  println("-" * 10)
  divider()

  println("Day1_1: " + day1_1())
  println("Day1_2: " + day1_2())

  divider()

  println("Day2_1: " + day2_1(input_day2_1))

  println("Day2_2: " + day2_2())

  divider()

  println("Day3_1: " + day3_1(wire1Input, wire2Input))
  println("Day3_2: " + day3_2(wire1Input, wire2Input))

  divider()
}