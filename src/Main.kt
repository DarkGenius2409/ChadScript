import kotlin.system.exitProcess
import java.io.File
import java.io.InputStream

val KEYWORDS = mapOf(
    "ong" to TokenType.Let,
)

fun main() {
    val inputStream: InputStream = File("src/test.txt").inputStream()
    val source = inputStream.bufferedReader().use { it.readText() }
    val lexer = Lexer()
    println(lexer.tokenize(source))
}