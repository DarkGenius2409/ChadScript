package ast_types

data class ProgramType(override val kind: NodeType, override val body: MutableList<Statement>) : Program
