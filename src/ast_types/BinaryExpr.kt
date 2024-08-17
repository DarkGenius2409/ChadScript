package ast_types

data class BinaryExpr(override val kind: NodeType, override val left: Expr, override val right: Expr, override val operator: String) : BinaryExprType
