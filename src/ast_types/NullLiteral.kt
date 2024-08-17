package ast_types

data class NullLiteral(override val kind: NodeType, override val value: String) : NullLiteralType
