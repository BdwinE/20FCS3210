/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 07 - Syntax Analyzer
 */

/*
expression  = term expression'
expression' = ( ´+´  | ´-´ ) term expression' | epsilon
term        = factor term'
term'       = ( ´*´ | ´/´ ) factor term' | epsilon
factor      = identifier | literal | ´(´ expression ´)´
identifier  = letter { ( letter | digit ) }
letter      = ´a´ | ´b´ | ´c´ | ´d´ | ´e´ | ´f´ | ´g´ | ´h´ | ´i´ | ´j´ | ´k´ | ´l´ | ´m´
| ´n´ | ´o´ | ´p´ | ´q´ | ´r´ | ´s´ | ´t´ | ´u´ | ´v´ | ´w´ | ´x´ | ´y´ | ´z´
literal     = digit { digit }
digit       = ´0´ | ´1´ | ´2´ | ´3´ | ´4´ | ´5´ | ´6´ | ´7´ | ´8´ | ´9´
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
    val tree = new Tree("expression")

    // TODO: call getLexemeUnit
    getLexemeUnit()

    // TODO: if token is NOT EOF, add result of "parseTerm" and "parseExpressionPrime" as new branches
    if (lexemeUnit.getToken() != Token.EOF) {
      tree.add(parseTerm())
      tree.add(parseExpressionPrime())
    }

    // TODO: return the tree
    tree
  }

  // expression' = ( ´+´  | ´-´ ) term expression' | epsilon
  private def parseExpressionPrime(): Tree = {
    // TODO: create a tree with label "expression'"
    val tree = new Tree("expression'")

    // TODO: call getLexemeUnit
    getLexemeUnit()

    // TODO: if token is NOT EOF
    if (lexemeUnit.getToken() != Token.EOF) {
      // TODO: if token is "+" or "-", add token as new branch and reset lexemeUnit;
      //  then add result of "parseTerm" and "parseExpressionPrime" as new branches
      if (lexemeUnit.getToken() == Token.ADD_OP || lexemeUnit.getToken() == Token.SUB_OP) {
        tree.add(new Tree(lexemeUnit.getLexeme()))
        lexemeUnit = null // always set lexemeUnit to null after consuming it
        tree.add(parseTerm())
        tree.add(parseExpressionPrime())
      }
      // else means "epsilon" production
    }

    // TODO: return the tree
    tree
  }

  // term = factor term'
  private def parseTerm() = {
    // TODO: create a tree with label "term"
    val tree = new Tree("term")

    // TODO: call getLexemeUnit
    getLexemeUnit()

    // TODO: if token is NOT EOF, add result of "parseFactor" and "parseTermPrime" as new branches
    if (lexemeUnit.getToken() != Token.EOF) {
      tree.add(parseFactor())
      tree.add(parseTermPrime())
    }
    // TODO: otherwise, throw an exception saying that "factor" was expected
    else
      throw new Exception("Syntax Analyzer Error: factor expected!")

    // TODO: return the tree
    tree
  }

  // term' = ( ´*´ | ´/´ ) factor term' | epsilon
  private def parseTermPrime(): Tree = {
    // TODO: create a tree with label "term'"
    val tree = new Tree("term'")

    // TODO: call getLexemeUnit
    getLexemeUnit()

    // TODO: if token is NOT EOF
    if (lexemeUnit.getToken() != Token.EOF) {

      // TODO: if token is "*" or "/", add token as new branch and reset lexemeUnit;
      //  then add result of "parseFactor" and "parseTermPrime" as new branches
      if (lexemeUnit.getToken() == Token.MUL_OP || lexemeUnit.getToken() == Token.DIV_OP) {
        tree.add(new Tree(lexemeUnit.getLexeme()))
        lexemeUnit = null // always set lexemeUnit to null after consuming it
        tree.add(parseFactor())
        tree.add(parseTermPrime())
      }
      // else means "epsilon" production
    }

    // TODO: return the tree
    tree
  }

  // factor = identifier | literal | ´(´ expression ´)´
  private def parseFactor(): Tree = {
    // TODO: create a tree with label "factor"
    val tree = new Tree("factor")

    // TODO: call getLexemeUnit
    getLexemeUnit()

    // TODO: if token is NOT EOF
    if (lexemeUnit.getToken() != Token.EOF) {
      // TODO: if token is an identifier, add result of "parseIdentifier" as new branch and reset lexemeUnit
      if (lexemeUnit.getToken() == Token.IDENTIFIER) {
        tree.add(parseIdentifier())
        lexemeUnit = null // always set lexemeUnit to null after consuming it
      }
      // TODO: if token is a literal, add result of "parseLiteral" as new branch and reset lexemeUnit
      else if (lexemeUnit.getToken() == Token.LITERAL) {
        tree.add(parseLiteral())
        lexemeUnit = null // always set lexemeUnit to null after consuming it
      }
      // TODO: if token is an "opening parenthesis", add it as new branch and reset lexemeUnit;
      // then add result of "parseExpression" as new branches;
      // after that, if token is an "closing parenthesis", add it as new branch and reset lexemeUnit
      else if (lexemeUnit.getToken() == Token.OPEN_PAR) {
        tree.add(new Tree(lexemeUnit.getLexeme()))
        lexemeUnit = null // always set lexemeUnit to null after consuming it
        tree.add(parseExpression())
        if (lexemeUnit.getToken() == Token.CLOSE_PAR) {
          tree.add(new Tree(lexemeUnit.getLexeme()))
          lexemeUnit = null // always set lexemeUnit to null after consuming it
        }
        // TODO: otherwise, throw an exception saying that "closing parenthesis" was expected
        else
          throw new Exception("Syntax Analyzer Error: \"closing parenthesis\" expected!")
      }
      // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
      else
        throw new Exception("Syntax Analyzer Error: identifier, literal or \"opening parenthesis\" expected!")
    }
    // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
    else
      throw new Exception("Syntax Analyzer Error: identifier, literal or \"opening parenthesis\" expected!")

    // TODO: return the tree
    tree
  }

  // identifier = letter { ( letter | digit ) }
  // TODO: return a new tree with the label "identifier" followed by the actual lexeme
  private def parseIdentifier() = new Tree("identifier: '" + lexemeUnit.getLexeme() + "'")

  // literal = digit { digit }
  // TODO: return a new tree with the label "literal" followed by the actual lexeme
  private def parseLiteral() = new Tree("literal: '" + lexemeUnit.getLexeme() + "'")
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


/*
def parse(): Tree = {
    parseA()
  }

  // A = x Y z
  private def parseA() = {
    val tree = new Tree("A")
    getLexemeUnit()
    if (lexemeUnit.getToken() == Token.x) {

      // process "x" (a terminal)
      tree.add(new Tree(lexemeUnit.getLexeme()))
      lexemeUnit = null
      getLexemeUnit()

      // process "Y" (a non-terminal)
      tree.add(parseY())

      if (lexemeUnit.getToken() == Token.z) {
        // process "z" (a terminal)
        tree.add(new Tree(lexemeUnit.getLexeme()))
        lexemeUnit = null
        getLexemeUnit()
      }
      else
        throw new Exception("Syntax Analyzer Error: \"z\" expected!")
    }
    else
      throw new Exception("Syntax Analyzer Error: \"x\" expected!")

    // return tree
    tree
  }

 */