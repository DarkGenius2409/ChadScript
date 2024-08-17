import runtime.Interpreter
import kotlin.system.exitProcess
import java.io.File
import java.io.InputStream

val KEYWORDS = mapOf(
    "ong" to TokenType.Let,
    "ohio" to TokenType.Null
)

fun interpretFile() {
    val inputStream: InputStream = File("src/test.txt").inputStream()
    val source = inputStream.bufferedReader().use { it.readText() }

    val parser = Parser()
    val interpreter = Interpreter()

    val program = parser.produceAST(source)
    val result = interpreter.evaluate(program)
    println(result)
}

fun repl() {
    val parser = Parser()
    val interpreter = Interpreter()
    println("\nSkibidi Repl v0.1\n")
    while(true) {
        print(">>> ")
        val input = readln()
        if(input.isEmpty() || input.contains("ick")) {
            exitProcess(69)
        }

        val program = parser.produceAST(input)
        val result = interpreter.evaluate(program)
        println(result)
    }
}

fun main() {
    repl()
}