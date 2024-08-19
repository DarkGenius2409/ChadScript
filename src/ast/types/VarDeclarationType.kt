package ast.types

interface VarDeclarationType : Statement {
    override val kind: NodeType
        get() = NodeType.VarDeclaration
    val constant: Boolean
    val identifier: String
    val value: Expr?
}