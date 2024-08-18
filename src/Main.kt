import ast.Parser
import lexer.TokenType
import runtime.Environment
import runtime.Interpreter
import runtime.types.BooleanVal
import runtime.types.NullVal
import runtime.types.NumberVal
import runtime.types.ValueType
import kotlin.system.exitProcess
import java.io.File
import java.io.InputStream

val KEYWORDS = mapOf(
    "ong" to TokenType.Let,
    "faxx" to TokenType.Const
)

fun interpretFile() {
    val inputStream: InputStream = File("src/test.txt").inputStream()
    val source = inputStream.bufferedReader().use { it.readText() }

    val parser = Parser()
    val interpreter = Interpreter()
    val env = Environment(null)
    env.declareVar("ohio", NullVal())
    env.declareVar("sigma", BooleanVal(true))
    env.declareVar("skibidi", BooleanVal(false))

    val program = parser.produceAST(source)
    val result = interpreter.evaluate(program, env)
    println(result)
}

fun repl() {
    val parser = Parser()
    val interpreter = Interpreter()
    val env = Environment(null)
    env.declareVar("ohio", NullVal())
    env.declareVar("sigma", BooleanVal(true))
    env.declareVar("skibidi", BooleanVal(false))
    println("\nSkibidi Repl v0.1\n")
    while(true) {
        print(">>> ")
        val input = readln()
        if(input.isEmpty() || input.contains("ick")) {
            exitProcess(69)
        }

        val program = parser.produceAST(input)
        val result = interpreter.evaluate(program, env)
        println(result)
    }
}

fun main() {
    repl()
}