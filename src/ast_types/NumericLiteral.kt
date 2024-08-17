package ast_types

data class NumericLiteralType(override val kind: NodeType, override val value: Double) : NumericLiteral
