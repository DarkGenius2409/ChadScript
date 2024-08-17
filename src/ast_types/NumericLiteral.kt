package ast_types

interface NumericLiteral : Expr {
    override val kind: NodeType
        get() = NodeType.NumericLiteral
    val value: Int
}