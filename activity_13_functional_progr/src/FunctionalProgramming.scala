import scala.collection.mutable.ArrayBuffer
import scala.math

/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 13 - Functional Programming
 */

object FunctionalProgramming {

  /* TODO #1
  Write the function *values* specified below:
  values(fun: (Int) => Int, low: Int, high: Int): Seq[(Int, Int)]
  Function *values* returns a sequence of integer pairs, where the first value ranges from [low, high] and the second value is *fun* of the first value.
  For example:
  values(x => x * x, -2, 3) should produce the sequence: (-2, 4), (-1, 1), (0, 0), (1, 1), (2, 4), (3, 9).
   */


  /* TODO #3
  Using Scala’s foldLeft function, implement the factorial function without an explicit loop.  Hint: use the to function to generate a range.
   */

  def main(args: Array[String]): Unit = {
    // testing *values* function
    // val seq = values(x => x * x, -2, 3)
    // println(seq.mkString(", "))

    /* TODO #2
    Using Scala’s *reduceLeft* function, find the largest element of an array.
    Hint: use Math’s *max* function.
    */
    val array = Array(2, 7, 1, 8, 3)
    // println("Largest element is " + println(array.reduceLeft(math.max(_, _))))

    // testing *factorial* function
    // println("5! = " + factorial(5))
  }
}
