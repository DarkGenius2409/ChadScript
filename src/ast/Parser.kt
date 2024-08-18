package ast

import ast.types.*
import lexer.Lexer
import lexer.Token
import lexer.TokenType

class Parser {
    private var tokens: MutableList<Token> = mutableListOf()
    private val lexer: Lexer = Lexer()

    private fun notEOF() : Boolean {
        return this.tokens[0].type != TokenType.EOF
    }

    private fun currentToken(): Token {
        return this.tokens.first()
    }

    private fun eat(): Token {
        val prev = this.tokens.removeFirst()
        return prev
    }

    private fun expect(type: TokenType, err: String): Token {
        val prev = this.tokens.removeFirst()
        if(prev.type != type) {
            throw Error("ast.Parser Error:\n $err $prev - Expecting: $type")
        }
        return prev
    }

    fun produceAST (sourceCode: String): ProgramType {
        this.tokens = lexer.tokenize(sourceCode)
        val statements: MutableList<Statement> = mutableListOf()
        val programType: ProgramType = Program(statements)

        // Parse until EOF
        while(this.notEOF()) {
            programType.body.add(this.parseStmt())
        }

        return programType
    }

    private fun parseStmt(): Statement {
        // skip to parse_expr
        return this.parseExpr()
    }

    private fun parseExpr(): Expr {
        return this.parseAdditiveExpr()
    }

    private fun parseAdditiveExpr(): Expr {
        var left = this.parseMultiplicativeExpr()

        while(this.currentToken().value == "+" || this.currentToken().value == "-") {
            val operator = this.eat().value
            val right = this.parseMultiplicativeExpr()
            left = BinaryExpr(
                left, right, operator
            )
        }
        return left
    }

    private fun parseMultiplicativeExpr(): Expr {
        var left = this.parsePrimaryExpr()

        while(this.currentToken().value == "/" || this.currentToken().value == "*" || this.currentToken().value == "%") {
            val operator = this.eat().value
            val right = this.parsePrimaryExpr()
            left = BinaryExpr(
                left, right, operator
            )
        }
        return left
    }

    private fun parsePrimaryExpr(): Expr {
        val tk = this.currentToken().type

        when (tk) {
            TokenType.Identifier -> return Identifier(this.eat().value)
            TokenType.Number -> return NumericLiteral(this.eat().value.toDouble())
            TokenType.OpenParen -> {
                this.eat();
                val value = this.parseExpr()
                this.expect(TokenType.CloseParen, "Unexpected token found inside parenthesised expression. Expected closing parenthesis.")
                return value
            }
            else -> {
                throw Error("Unexpected token found during parsing ${this.currentToken()}")
            }
        }
    }
}