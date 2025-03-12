fun getGitDiff(): String {
    val process = ProcessBuilder("git", "diff")
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    val output = process.inputStream.bufferedReader().use { it.readText() }
    val error = process.errorStream.bufferedReader().use { it.readText() }
    
    process.waitFor()
    
    return if (error.isNotEmpty()) {
        "Error: $error"
    } else if (output.isEmpty()) {
        "No changes detected"
    } else {
        output
    }
}

fun main() {
    println("Git differences in the repository:")
    println("----------------------------------")
    println(getGitDiff())
}
