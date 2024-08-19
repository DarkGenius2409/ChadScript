package runtime

import ast.types.*
import runtime.types.*

class Interpreter {
    inner class Statements {
        fun evalProgram(program: Program, env: Environment): RuntimeVal {
            var lastEvaluated: RuntimeVal = NullVal()

            for (statement in program.body) {
                lastEvaluated = evaluate(statement, env)
            }

            return lastEvaluated
        }

        fun evalVarDeclaration(declaration: VarDeclaration, env: Environment): RuntimeVal {
            val value: RuntimeVal = if(declaration.value != null) evaluate(declaration.value, env) else NullVal()
            return env.declareVar(declaration.identifier, value, declaration.constant)
        }
    }

    inner class Expressions {
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

        fun evalBinaryExpr(binop: BinaryExpr, env: Environment): RuntimeVal {
            val lhs = evaluate(binop.left, env)
            val rhs = evaluate(binop.right, env)

            if(lhs.type == ValueType.Number && rhs.type == ValueType.Number) {
                return this.evalNumericBinaryExpr((lhs as NumberVal), (rhs as NumberVal), binop.operator)
            }
            return NullVal()
        }

        fun evalIdentifier(ident: Identifier, env: Environment): RuntimeVal {
            val value = env.lookupVar(ident.symbol)
            return value
        }
    }

    fun evaluate(astNode: Statement, env: Environment): RuntimeVal {
        return when (astNode.kind){
            NodeType.NumericLiteral ->
                NumberVal((astNode as NumericLiteral).value)

            NodeType.Identifier -> return this.Expressions().evalIdentifier((astNode as Identifier), env)
            NodeType.BinaryExpr -> this.Expressions().evalBinaryExpr((astNode as BinaryExpr), env)
            NodeType.Program -> this.Statements().evalProgram((astNode as Program), env)

            // Handle Statements
            NodeType.VarDeclaration -> this.Statements().evalVarDeclaration(astNode as VarDeclaration, env)

            // Handle unimplemented AST types as error
            else -> {
                throw Error("This AST Node has not yet been setup for interpretation. $astNode")
            }
        }
    }
}