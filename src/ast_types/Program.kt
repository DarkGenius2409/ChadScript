package ast_types

data class Program(override val kind: NodeType, override val body: MutableList<Statement>) : ProgramType
