package runtime

import ast_types.*
import runtime.types.*
import kotlin.system.exitProcess

class Interpreter {
    fun evaluate(astNode: Statement): RuntimeValType {
        return when (astNode.kind){
            NodeType.NumericLiteral ->
                NumberVal(ValueType.Number, (astNode as NumericLiteral).value)

            NodeType.Identifier -> TODO()
            NodeType.BinaryExpr -> this.evalBinaryExpr(astNode as BinaryExpr)
            NodeType.Program -> this.evalProgram(astNode as Program)
            NodeType.NullLiteral ->
                NullVal(ValueType.Null, (astNode as NullLiteral).value)
            else -> {
                println("This AST Node has not yet been setup for interpretation. $astNode")
                exitProcess(69)
            }
        }
    }

    fun evalBinaryExpr(binop: BinaryExpr): RuntimeValType {
        val lhs = this.evaluate(binop.left)
        val rhs = this.evaluate(binop.right)

        if(lhs.type == ValueType.Number && rhs.type == ValueType.Number) {
            return this.evalNumericBinaryExpr((lhs as NumberVal), (rhs as NumberVal), binop.operator)
        }
        return NullVal(ValueType.Null, "null")
    }

    fun evalNumericBinaryExpr(lhs: NumberVal, rhs: NumberVal, operator: String): NumberVal {
        var result: Double = 0.0
        if(operator == "+")
            result = lhs.value + rhs.value
        else if(operator == "-")
            result = lhs.value - rhs.value
        else if(operator == "*")
            result = lhs.value * rhs.value
        else if (operator == "/")
            result = lhs.value / rhs.value
        else
            result = lhs.value % rhs.value

        return NumberVal(ValueType.Number, result)
    }

    fun evalProgram(program: Program): RuntimeValType {
        var lastEvaluated: RuntimeValType = NullVal(ValueType.Null, "null")

        for (statement in program.body) {
            lastEvaluated = this.evaluate(statement)
        }

        return lastEvaluated
    }
}