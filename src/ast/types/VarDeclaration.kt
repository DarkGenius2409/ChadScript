package ast.types

data class VarDeclaration(override val constant: Boolean, override val identifier: String, override val value: Expr?): VarDeclarationType
