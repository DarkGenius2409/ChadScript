package ast_types

data class Identifier(override val kind: NodeType, override val symbol: String) : IdentifierType
