package ch.ambrite
package aocscala

object Day4 {
  def followingDigits(digits: Seq[Int]): Boolean = {
    val pairs = (digits tail) zip (digits)
    pairs.foldLeft(false) { (a, v) => a || v._1 == v._2 } 
  }

  def hasExactlyTwoOfAKind(digits: Seq[Int]): Boolean = {
    val groups = digits groupBy identity
    val counts = groups.values.map(_.size).toSeq
    counts.indexOf(2) >= 0
  }

  def increasingDigits(digits: Seq[Int]): Boolean = {
    val pairs = (digits tail) zip (digits)
    pairs.foldLeft(true) { (a, v) => a && v._1 >= v._2 } 
  }

  def testValidPassword1(password: Int): Boolean = {
    val digits: Seq[Int] = password.toString.map(_.asDigit)
    followingDigits(digits) && increasingDigits(digits)
  }

  def testValidPassword2(password: Int): Boolean = {
    val digits: Seq[Int] = password.toString.map(_.asDigit)
    followingDigits(digits) && increasingDigits(digits) && hasExactlyTwoOfAKind(digits)
  }

  def day4_1(low: Int, high: Int): Int = {
    (low to high).foldLeft(0) { (a, v) => a + (if (testValidPassword1(v)) 1 else 0) }
  }

  def day4_2(low: Int, high: Int): Int = {
    (low to high).foldLeft(0) { (a, v) => a + (if (testValidPassword2(v)) 1 else 0) }
  }

}
