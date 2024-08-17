package ast_types

interface NumericLiteralType : Expr {
    override val kind: NodeType
        get() = NodeType.NumericLiteral
    val value: Double
}