package ch.ambrite
package aocscala

import scala.io.Source

object Day1 {
  def calc(n: Int): Int = n / 3 - 2 
  def reccalc(n: Int): Int = {
    val direct = calc(n)
    if (direct > 0) direct + reccalc(direct) else Math.max(0, direct)
  }

  def day1_1(): Int = {
    val source = Source.fromFile("inputs/inputDay1.txt")
    val individuals = for (line <- source.getLines()) yield calc(line toInt)
    individuals.fold(0)(_ + _);
  }
  def day1_2(): Int = {
    val source = Source.fromFile("inputs/inputDay1.txt")
    val individuals = for (line <- source.getLines()) yield reccalc(line toInt)
    individuals.fold(0)(_ + _);
  }
}
