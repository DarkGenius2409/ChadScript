package lexer

import KEYWORDS

class Lexer {
    private fun isAlpha (str: String) : Boolean {
        return str.uppercase() != str.lowercase()
    }

    private fun isInt(str: String) : Boolean {
        val c = str.codePointAt(str.indexOf(str.first()))
        val bounds = mutableListOf("0".codePointAt(0), "9".codePointAt(0))
        return (c >= bounds[0] && c <= bounds[1])
    }

    private fun isSkippable(str: String) : Boolean {
        return str == " " || str == "\n" || str == "\t"
    }

    private fun createToken(value: String, type: TokenType) : Token {
        return Token(value, type)
    }

    fun tokenize(sourceCode: String) : MutableList<Token> {
        val tokens = mutableListOf<Token>()
        val src = sourceCode.split("").toMutableList()
        src.removeFirst()
        src.removeLast()

        // Build each token until EOF
        while (src.isNotEmpty()) {
            if (src.first() == "(") {
                tokens.add(this.createToken(src.removeFirst(), TokenType.OpenParen))
            } else if (src.first() == ")") {
                tokens.add(this.createToken(src.removeFirst(), TokenType.CloseParen))
            } else if (src.first() == "+" || src.first() == "-" || src.first() == "*" || src.first() == "/" || src.first() == "%") {
                tokens.add(this.createToken(src.removeFirst(), TokenType.BinaryOperator))
            } else {
                // Handle multi-character tokens
                if (this.isInt(src.first())) {
                    var num = ""
                    while(src.size > 0 && isInt(src.first())) {
                        num += src.removeFirst()
                    }
                    tokens.add(this.createToken(num, TokenType.Number))
                }
                else if (this.isAlpha(src.first())) {
                    var ident = ""
                    while(src.size > 0 && isAlpha(src[0])) {
                        ident += src.removeFirst()
                    }
                    // check for reserved keywords
                    val reserved = KEYWORDS[ident];
                    if(reserved is TokenType){
                        tokens.add(this.createToken(ident, reserved))
                    } else {
                        tokens.add(this.createToken(ident, TokenType.Identifier))
                    }
                }
                else if (this.isSkippable(src.first())) {
                    src.removeFirst() // Skip current character
                } else {
                    throw Error("Unrecognized character found in source: ${src.first()}")
                }
            }
        }
        tokens.add(this.createToken("EndOfFile", TokenType.EOF))
        return tokens
    }
}