package test.chapter3.spaces

fun foo(a: (Int) -> Int, b: Int) {
    foo( {x: Int -> x}, 5)
}
