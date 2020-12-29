import java.util.*
import kotlin.random.Random

val scan = Scanner(System.`in`)

fun main(args: Array<String>) {
    var array = Array<String>(9) {"........."}
    var arrNull = Array<String>(9) {""}
    var arrNothing = Array<String>(9) {"........."}
    val arrayEnd = Array<String>(9) {""}
    println("How many mines do you want on the field?")
    val num = scan.nextInt()
    var temp = 0
    while (temp < num) {
        val index1 = Random.nextInt(9)
        val index2 = Random.nextInt(9)
        var str = ""
        str = array[index1]
        val arr2 = str.toCharArray()
        if (arr2[index2] != 'X') {
            arr2[index2] = 'X'
            temp++
        }
        array[index1] = arr2.joinToString("")
    }
    val arrZ = Array<String>(9) {""}
    for (i in array.indices) {
        arrZ[i] = array[i]
    }

    val obj = Matrix()
    for (i in array.indices) {
        arrNull[i] = array[i]
    }
    arrNull = obj.def(arrNull)
    array = obj.def(array)
    arrNull = set(arrNull)

    for (i in array.indices) {
        arrayEnd[i] = array[i]
    }
    val arHash = Array<String>(9) {""}
    for (i in arrayEnd.indices) {
        arHash[i] = arrayEnd[i]
    }
    for (i in arHash.indices) {
        val str = arHash[i].toCharArray()
        for (j in str.indices) {
            if (str[j] == '.') {
                str[j] = '/'
            }
            arHash[i] = str.joinToString("")
        }
    }
    val oc = Fake()
    oc.searchFake(arHash, num, arrZ)
}

fun setHash(array: Array<String>): Array<String> {
    for (i in array.indices) {
        val str = array[i].toCharArray()
        for (j in str.indices) {
            if (str[j] == '.') {
                str[j] = '/'
                array[i] = str.joinToString("")
            }
        }
    }
    for (i in array.indices) {
        val str = array[i].toCharArray()
        for (j in str.indices) {
            if (str[j] == 'X') {
                str[j] = '.'
                array[i] = str.joinToString("")
            }
        }
    }
    return array
}

fun set(array: Array<String>): Array<String> {
    for (i in array.indices) {
        var str = array[i].toCharArray()
        for (j in str.indices) {
            if (str[j] == 'X') {
                str[j] = '.'
                array[i] = str.joinToString("")
            }
        }
    }
    return array
}

class Matrix {
    fun def(array: Array<String>): Array<String> {
        for (i in array.indices) {
            val upperStr = if (i - 1 < 0) null else array[i - 1]
            val str = array[i]
            val lowerStr = if (i + 1 > 8) null else array[i + 1]
            for (j in str.indices) {
                var temp = 0
                if (str[j] == '.') {
                    if (upperStr == null) {
                        if (j == 0) {
                            if (str[j + 1] == 'X') temp++
                            if (lowerStr!![j] == 'X') temp++
                            if (lowerStr[j + 1] == 'X') temp++
                        } else if (j == 8) {
                            if (str[j - 1] == 'X') temp++
                            if (lowerStr!![j] == 'X') temp++
                            if (lowerStr[j - 1] == 'X') temp++
                        } else {
                            if (str[j - 1] == 'X') temp++
                            if (str[j + 1] == 'X') temp++
                            if (lowerStr!![j - 1] == 'X') temp++
                            if (lowerStr[j] == 'X') temp++
                            if (lowerStr[j + 1] == 'X') temp++
                        }
                    } else if (lowerStr == null) {
                        if (j == 0) {
                            if (str[j + 1] == 'X') temp++
                            if (upperStr[j] == 'X') temp++
                            if (upperStr[j + 1] == 'X') temp++
                        } else if (j == 8) {
                            if (str[j - 1] == 'X') temp++
                            if (upperStr[j] == 'X') temp++
                            if (upperStr[j - 1] == 'X') temp++
                        } else {
                            if (str[j - 1] == 'X') temp++
                            if (str[j + 1] == 'X') temp++
                            if (upperStr[j - 1] == 'X') temp++
                            if (upperStr[j] == 'X') temp++
                            if (upperStr[j + 1] == 'X') temp++
                        }
                    } else {
                        if (j == 0) {
                            if (upperStr[j + 1] == 'X') temp++
                            if (upperStr[j] == 'X') temp++
                            if (str[j + 1] == 'X') temp++
                            if (lowerStr[j] == 'X') temp++
                            if (lowerStr[j + 1] == 'X') temp++
                        } else if (j == 8) {
                            if (upperStr[j - 1] == 'X') temp++
                            if (upperStr[j] == 'X') temp++
                            if (str[j - 1] == 'X') temp++
                            if (lowerStr[j] == 'X') temp++
                            if (lowerStr[j - 1] == 'X') temp++
                        } else {
                            if (upperStr[j - 1] == 'X') temp++
                            if (upperStr[j] == 'X') temp++
                            if (upperStr[j + 1] == 'X') temp++
                            if (str[j - 1] == 'X') temp++
                            if (str[j + 1] == 'X') temp++
                            if (lowerStr[j - 1] == 'X') temp++
                            if (lowerStr[j] == 'X') temp++
                            if (lowerStr[j + 1] == 'X') temp++
                        }
                    }
                }
                if (temp > 0) {
                    val arr2 = array[i].toCharArray()
                    arr2[j] = "$temp".toCharArray()[0]
                    array[i] = arr2.joinToString("")
                }
            }
        }
        return array
    }
}

class Fake {
    var tempAll = 0
    var tempBomb = 0
    var arr = Array<String>(9) { "........." }
    fun searchFake(arrayEnd: Array<String>, bomb: Int, arrBoom: Array<String>) {
        var isTrue = true
        while (isTrue) {
            println(" │123456789│")
            println("—│—————————│")
            println("1│${arr[0]}|")
            println("2│${arr[1]}|")
            println("3│${arr[2]}|")
            println("4│${arr[3]}|")
            println("5│${arr[4]}|")
            println("6│${arr[5]}|")
            println("7│${arr[6]}|")
            println("8│${arr[7]}|")
            println("9│${arr[8]}|")
            println("—│—————————│")
            println("Set/unset mines marks or claim a cell as free:")
            val x = scan.nextInt() - 1
            val y = scan.nextInt() - 1
            val isMines = scan.next()

            if (isMines == "mine") {
                val str = arr[y]
                val str2 = arrayEnd[y]
                if (str[x] == '.' && str2[x] != 'X') {
                    val ar = str.toCharArray()
                    ar[x] = '*'
                    arr[y] = ar.joinToString("")
                    tempAll++
                } else if (str[x] == '.' && str2[x] == 'X') {
                    val ar = str.toCharArray()
                    ar[x] = '*'
                    arr[y] = ar.joinToString("")
                    tempAll++
                    tempBomb++
                } else if (str[x] == '*' && str2[x] != 'X') {
                    val ar = str.toCharArray()
                    ar[x] = '.'
                    arr[y] = ar.joinToString("")
                    tempAll--
                } else if (str[x] == '*' && str2[x] == 'X') {
                    val ar = str.toCharArray()
                    ar[x] = '.'
                    arr[y] = ar.joinToString("")
                    tempAll--
                    tempBomb--
                }
            } else if (isMines == "free") {
                val str = arrayEnd[y]
                if (str[x] == 'X') {
                    isTrue = false
                    for (i in arrBoom.indices) {
                        val str3 = arrBoom[i]
                        for (j in str3.indices) {
                            if (str3[j] == 'X') {
                                val ar = arr[i].toCharArray()
                                ar[j] = str3[j]
                                arr[i] = ar.joinToString("")
                            }
                        }
                    }
                    println(" │123456789│")
                    println("—│—————————│")
                    println("1│${arr[0]}|")
                    println("2│${arr[1]}|")
                    println("3│${arr[2]}|")
                    println("4│${arr[3]}|")
                    println("5│${arr[4]}|")
                    println("6│${arr[5]}|")
                    println("7│${arr[6]}|")
                    println("8│${arr[7]}|")
                    println("9│${arr[8]}|")
                    println("—│—————————│")
                    println("You stepped on a mine and failed!")
                } else {
                    arr = factorial2(arr, y, x, arrayEnd)
                    var n = 0
                    for (i in arr.indices) {
                        for (j in arr[i]) {
                            if (j == '.' || j == '*') n++
                        }
                    }
                    if (n == bomb) {
                        isTrue = false
                        println(" │123456789│")
                        println("—│—————————│")
                        println("1│${arr[0]}|")
                        println("2│${arr[1]}|")
                        println("3│${arr[2]}|")
                        println("4│${arr[3]}|")
                        println("5│${arr[4]}|")
                        println("6│${arr[5]}|")
                        println("7│${arr[6]}|")
                        println("8│${arr[7]}|")
                        println("9│${arr[8]}|")
                        println("—│—————————│")
                        println("Congratulations! You found all the mines!")
                    }
                }
            }
            if (tempAll == bomb && tempBomb == bomb) {
                println(" │123456789│")
                println("—│—————————│")
                println("1│${arr[0]}|")
                println("2│${arr[1]}|")
                println("3│${arr[2]}|")
                println("4│${arr[3]}|")
                println("5│${arr[4]}|")
                println("6│${arr[5]}|")
                println("7│${arr[6]}|")
                println("8│${arr[7]}|")
                println("9│${arr[8]}|")
                println("—│—————————│")
                println("Congratulations! You found all the mines!")
                isTrue = false
            }
        }
    }

    fun factorial2(array: Array<String>, y: Int, x: Int, arrBomb: Array<String>): Array<String> {
        val strBombUpper = if (y > 0) arrBomb[y - 1] else null
        val strBomb = arrBomb[y]
        val strBombLower = if (y < 8) arrBomb[y + 1] else null
        val q = array[y]

        val ar = array[y].toCharArray()
        ar[x] = strBomb[x]
        array[y] = ar.joinToString("")

        return if (strBomb[x] != 'X' && q[x] == '.') {
            if (y == 0) {
                if (x == 0 && strBomb[x + 1] != 'X' && strBombLower!![x] != 'X' && strBombLower[x + 1] != 'X') {
                    factorial2(array, y, x + 1, arrBomb)
                    factorial2(array, y + 1, x, arrBomb)
                    factorial2(array, y + 1, x + 1, arrBomb)
                } else if (x == 8 && strBomb[x - 1] != 'X' && strBombLower!![x] != 'X' && strBombLower[x - 1] != 'X') {
                    factorial2(array, y, x - 1, arrBomb)
                    factorial2(array, y + 1, x, arrBomb)
                    factorial2(array, y + 1, x - 1, arrBomb)
                } else {
                    if (x in 1..7 && strBomb[x - 1] != 'X' && strBomb[x + 1] != 'X' && strBombLower!![x - 1] != 'X' && strBombLower[x] != 'X' && strBombLower[x + 1] != 'X') {
                        factorial2(array, y, x + 1, arrBomb)
                        factorial2(array, y, x - 1, arrBomb)
                        factorial2(array, y + 1, x + 1, arrBomb)
                        factorial2(array, y + 1, x, arrBomb)
                        factorial2(array, y + 1, x - 1, arrBomb)
                    } else return array
                }
            } else if (y == 8) {
                if (x == 0 && strBomb[x + 1] != 'X' && strBombUpper!![x] != 'X' && strBombUpper[x + 1] != 'X') {
                    factorial2(array, y, x + 1, arrBomb)
                    factorial2(array, y - 1, x, arrBomb)
                    factorial2(array, y - 1, x + 1, arrBomb)
                } else if (x == 8 && strBomb[x - 1] != 'X' && strBombUpper!![x] != 'X' && strBombUpper[x - 1] != 'X') {
                    factorial2(array, y, x - 1, arrBomb)
                    factorial2(array, y - 1, x, arrBomb)
                    factorial2(array, y - 1, x - 1, arrBomb)
                } else if (x in 1..7 && strBomb[x - 1] != 'X' && strBomb[x + 1] != 'X' && strBombUpper!![x - 1] != 'X' && strBombUpper[x] != 'X' && strBombUpper[x + 1] != 'X') {
                    factorial2(array, y, x + 1, arrBomb)
                    factorial2(array, y, x - 1, arrBomb)
                    factorial2(array, y - 1, x + 1, arrBomb)
                    factorial2(array, y - 1, x, arrBomb)
                    factorial2(array, y - 1, x - 1, arrBomb)
                } else return array
            } else {
                if (x == 0 && strBomb[x + 1] != 'X' && strBombUpper!![x] != 'X' && strBombUpper[x + 1] != 'X' && strBombLower!![x] != 'X' && strBombLower[x + 1] != 'X') {
                    factorial2(array, y, x + 1, arrBomb)
                    factorial2(array, y - 1, x, arrBomb)
                    factorial2(array, y - 1, x + 1, arrBomb)
                    factorial2(array, y + 1, x, arrBomb)
                    factorial2(array, y + 1, x + 1, arrBomb)
                } else if (x == 8 && strBomb[x - 1] != 'X' && strBombUpper!![x] != 'X' && strBombUpper[x - 1] != 'X' && strBombLower!![x] != 'X' && strBombLower[x - 1] != 'X') {
                    factorial2(array, y, x - 1, arrBomb)
                    factorial2(array, y - 1, x, arrBomb)
                    factorial2(array, y - 1, x - 1, arrBomb)
                    factorial2(array, y + 1, x, arrBomb)
                    factorial2(array, y + 1, x - 1, arrBomb)
                } else if (x in 1..7 && strBomb[x + 1] != 'X' && strBomb[x - 1] != 'X' && strBombUpper!![x - 1] != 'X' && strBombUpper[x] != 'X' && strBombUpper[x + 1] != 'X' && strBombLower!![x - 1] != 'X' && strBombLower[x] != 'X' && strBombLower[x + 1] != 'X') {
                    factorial2(array, y, x + 1, arrBomb)
                    factorial2(array, y, x - 1, arrBomb)
                    factorial2(array, y - 1, x, arrBomb)
                    factorial2(array, y - 1, x + 1, arrBomb)
                    factorial2(array, y - 1, x - 1, arrBomb)
                    factorial2(array, y + 1, x, arrBomb)
                    factorial2(array, y + 1, x + 1, arrBomb)
                    factorial2(array, y + 1, x - 1, arrBomb)
                } else return array
            }
        } else array
    }
}



