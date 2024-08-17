package ast_types

interface IdentifierType : Expr {
    override val kind: NodeType
        get() = NodeType.Identifier
    val symbol: String
}