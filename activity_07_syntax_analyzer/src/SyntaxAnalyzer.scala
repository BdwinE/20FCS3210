/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 07 - Syntax Analyzer
 */

class SyntaxAnalyzer(private var source: String) {

  private var it = new LexicalAnalyzer(source).iterator
  private var lexemeUnit: LexemeUnit = null

  private def getLexemeUnit() = {
    if (lexemeUnit == null)
      lexemeUnit = it.next()
  }

  def parse(): Tree = {
    parseExpression()
  }

  // expression = term expression'
  private def parseExpression() = {
    // TODO: create a tree with label "expression"
    val tree = new Tree("")

    // TODO: call getLexemeUnit

    // TODO: if token is NOT EOF, add result of "parseTerm" and "parseExpressionPrime" as new branches


    // TODO: return the tree
    tree
  }

//  // expression' = ( ´+´  | ´-´ ) term expression' | epsilon
//  private def parseExpressionPrime(): Tree = {
//    // TODO: create a tree with label "expression'"
//
//    // TODO: call getLexemeUnit
//
//    // TODO: if token is NOT EOF
//      // TODO: if token is "+" or "-", add token as new branch and reset lexemeUnit;
//      //  then add result of "parseTerm" and "parseExpressionPrime" as new branches
//
//      // else means "epsilon" production
//
//
//    // TODO: return the tree
//
//  }
//
//  // term = factor term'
//  private def parseTerm() = {
//    // TODO: create a tree with label "term"
//
//    // TODO: call getLexemeUnit
//
//    // TODO: if token is NOT EOF, add result of "parseFactor" and "parseTermPrime" as new branches
//
//    // TODO: otherwise, throw an exception saying that "factor" was expected
//
//
//    // TODO: return the tree
//  }
//
//  // term' = ( ´*´ | ´/´ ) factor term' | epsilon
//  private def parseTermPrime(): Tree = {
//    // TODO: create a tree with label "term'"
//
//    // TODO: call getLexemeUnit
//
//    // TODO: if token is NOT EOF
//
//      // TODO: if token is "*" or "/", add token as new branch and reset lexemeUnit;
//      //  then add result of "parseFactor" and "parseTermPrime" as new branches
//
//      // else means "epsilon" production
//    }
//
//    // TODO: return the tree
//  }
//
//  // factor = identifier | literal | ´(´ expression ´)´
//  private def parseFactor(): Tree = {
//    // TODO: create a tree with label "factor"
//
//    // TODO: call getLexemeUnit
//
//    // TODO: if token is NOT EOF
//      // TODO: if token is an identifier, add result of "parseIdentifier" as new branch and reset lexemeUnit
//
//      // TODO: if token is a literal, add result of "parseLiteral" as new branch and reset lexemeUnit
//
//      // TODO: if token is an "opening parenthesis", add it as new branch and reset lexemeUnit;
//      // then add result of "parseExpression" as new branches;
//      // after that, if token is an "closing parenthesis", add it as new branch and reset lexemeUnit
//
//        // TODO: otherwise, throw an exception saying that "closing parenthesis" was expected
//      // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//
//
//    // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//
//
//    // TODO: return the tree
//  }
//
//  // identifier = letter { ( letter | digit ) }
//  // TODO: return a new tree with the label "identifier" followed by the actual lexeme
//  private def parseIdentifier() =
//
//  // literal = digit { digit }
//  // TODO: return a new tree with the label "literal" followed by the actual lexeme
//  private def parseLiteral() =
}

object SyntaxAnalyzer {
  def main(args: Array[String]): Unit = {
    // check if source file was passed through the command-line
    if (args.length != 1) {
      print("Missing source file!")
      System.exit(1)
    }

    val syntaxAnalyzer = new SyntaxAnalyzer(args(0))
    val parseTree = syntaxAnalyzer.parse()
    print(parseTree)
  }
}
