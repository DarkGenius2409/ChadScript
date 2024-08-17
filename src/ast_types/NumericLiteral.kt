package ast_types

data class NumericLiteral(override val kind: NodeType, override val value: Double) : NumericLiteralType
