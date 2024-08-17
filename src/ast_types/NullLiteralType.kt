package ast_types

interface NullLiteralType : Expr {
    override val kind: NodeType
        get() = NodeType.NullLiteral
    val value: String
        get() = "null"
}