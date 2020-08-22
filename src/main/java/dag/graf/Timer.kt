package dag.graf

fun time( f: () -> Unit ) : Int {
    val start = System.currentTimeMillis()
    f()
    return (System.currentTimeMillis() - start).toInt()
}