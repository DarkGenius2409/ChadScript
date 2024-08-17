package ast_types

data class IdentifierType(override val kind: NodeType, override val symbol: String) : Identifier
