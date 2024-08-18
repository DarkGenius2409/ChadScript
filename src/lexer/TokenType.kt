package lexer

enum class TokenType {
    // Literal Types
    Number,
    Identifier,
    String,

    // Grouping * Operators
    Equals,
    OpenParen, CloseParen,
    BinaryOperator,
    EOF, // End of File

    // Keywords
    Let,
    Const
}