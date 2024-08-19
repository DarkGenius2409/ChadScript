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
    Semicolon,
    EOF, // End of File

    // Keywords
    Let,
    Const
}