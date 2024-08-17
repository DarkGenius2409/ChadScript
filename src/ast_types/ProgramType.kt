package ast_types

interface Program : Statement {
    override val kind: NodeType
        get() = NodeType.Program
    val body: MutableList<Statement>
}