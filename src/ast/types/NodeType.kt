package ast.types

enum class NodeType {
    // STATEMENTS
    Program,
    VarDeclaration,

    // EXPRESSIONS
    NumericLiteral,
    Identifier,
    BinaryExpr,
    AssignmentExpr
}