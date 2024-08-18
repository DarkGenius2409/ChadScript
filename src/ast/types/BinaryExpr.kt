package ast.types

data class BinaryExpr(override val left: Expr, override val right: Expr, override val operator: String) : BinaryExprType
