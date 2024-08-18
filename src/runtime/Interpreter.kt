package runtime

import ast.types.*
import runtime.types.*

class Interpreter {
    private fun evalProgram(program: Program, env: Environment): RuntimeVal {
        var lastEvaluated: RuntimeVal = NullVal()

        for (statement in program.body) {
            lastEvaluated = this.evaluate(statement, env)
        }

        return lastEvaluated
    }

    private fun evalNumericBinaryExpr(lhs: NumberVal, rhs: NumberVal, operator: String): NumberVal {
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

        return NumberVal(result)
    }

    private fun evalBinaryExpr(binop: BinaryExpr, env: Environment): RuntimeVal {
        val lhs = this.evaluate(binop.left, env)
        val rhs = this.evaluate(binop.right, env)

        if(lhs.type == ValueType.Number && rhs.type == ValueType.Number) {
            return this.evalNumericBinaryExpr((lhs as NumberVal), (rhs as NumberVal), binop.operator)
        }
        return NullVal()
    }

    private fun evalIdentifier(ident: Identifier, env: Environment): RuntimeVal {
        val value = env.lookupVar(ident.symbol)
        return value
    }

    fun evaluate(astNode: Statement, env: Environment): RuntimeVal {
        return when (astNode.kind){
            NodeType.NumericLiteral ->
                NumberVal((astNode as NumericLiteral).value)

            NodeType.Identifier -> return evalIdentifier((astNode as Identifier), env)
            NodeType.BinaryExpr -> this.evalBinaryExpr((astNode as BinaryExpr), env)
            NodeType.Program -> this.evalProgram((astNode as Program), env)
            else -> {
                throw Error("This AST Node has not yet been setup for interpretation. $astNode")
            }
        }
    }
}