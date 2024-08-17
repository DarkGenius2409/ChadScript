package ast_types

interface Identifier : Expr {
    override val kind: NodeType
        get() = NodeType.Identifier
    val symbol: String
}