package ast.types

data class Program(override val body: MutableList<Statement>) : ProgramType
