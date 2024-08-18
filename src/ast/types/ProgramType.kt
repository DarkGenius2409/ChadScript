package ast.types

interface ProgramType : Statement {
    override val kind: NodeType
        get() = NodeType.Program
    val body: MutableList<Statement>
}