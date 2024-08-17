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
    val parser: Parser = Parser()
    println(parser.produceAST(source))
}

fun repl() {
    val parser: Parser = Parser()
    println("\nSkibidi Repl v0.1\n")
    while(true) {
        print(">>> ")
        val input = readln()
        if(input.isEmpty() || input.contains("ick")) {
            exitProcess(69)
        }

        val program = parser.produceAST(input)
        println(program)
    }
}

fun main() {
    repl()
}