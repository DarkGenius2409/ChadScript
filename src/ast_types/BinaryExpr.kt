package ast_types

data class BinaryExprType(override val kind: NodeType, override val left: Expr, override val right: Expr, override val operator: String) : BinaryExpr
