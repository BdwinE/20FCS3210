

/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 14 - Functional Programming
 */

object FP {

  // TODO #1: write function *isPrime* that takes an integer and returns true/false whether the input is a prime number or not.
  def isPrimeV1(n: Int): Boolean = {
    for (d <- 2 to n - 1)
      if (n % d == 0)
        return false
    true
  }

  def isPrimeV2(n: Int): Boolean = {
    if (n <= 1)
      false
    else
      (2 to n - 1).map(n % _ != 0).reduceLeft(_ && _)
  }

  // TODO #2: version 2 of isPrime
  def isPrimeV3(n: Int): Boolean = {
    if (n <= 1)
      false
    else
      (2 to math.sqrt(n).toInt).map(n % _ != 0).reduceLeft(_ && _)
  }

  // TODO #3: write function *gcd* that takes two integers and returns the GCD (Greatest Common Divisor) of the two inputs.
  // hint: use the Euclidean algorithm!
  def gcd(a: Int, b: Int): Int = {
    if (a % b == 0)
      b
    else
      gcd(b, a % b)
  }

  // TODO #4: rewrite *gcd* as *gcdC* using currying notation.
  def gcdC(a: Int) = (b: Int) => {
    if (a % b == 0)
      b
    else
      gcd(b, a % b)
  }

  // TODO #5: write function *coprime* that takes two integers and returns true/false whether the numbers are coprimes (their GCD equals to 1).

  // TODO #6: write function *totientPhi* that takes an integer m and returns the number of positive integers r (1 <= r < m) that are coprime to m.


  // TODO #7: version 2 of *totientPhi*

  // TODO #8: write function *primeFactors* that takes an integer and returns a flat list with the prime factors of the given number in ascending order.

  // TODO (optional): returns the number of times a number a can be evenly divided by a number b


  // TODO #9: write function *primeFactorsMult* similar to *primeFactors* but with the prime factors and their multiplicity.

  // TODO #10: write function *primesRange* that takes a range of integers and returns a list of all prime numbers within that range.

  // TODO #11: Goldbach's conjecture says that every positive even number greater than 2 is the sum of two prime numbers; example: 28 = 5 + 23; it is one of the most famous facts in number theory that has not been proved to be correct in the general case; it has been numerically confirmed up to very large numbers; write function *goldbach* that takes an integer and returns the two prime numbers that sum up to it.


  // TODO #12: write the function *golbachList* that takes a range of integers and returns a list of all even numbers and their Goldbach composition.

  def main(args: Array[String]): Unit = {
    println(gcdC(18)(12))
  }

}
