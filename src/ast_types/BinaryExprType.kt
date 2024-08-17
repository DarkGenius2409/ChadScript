package ast_types

interface BinaryExprType : Expr {
    override val kind: NodeType
        get() = NodeType.BinaryExpr
    val left: Expr
    val right: Expr
    val operator: String
}