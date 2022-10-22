@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = abs(n)
    do {
        number /= 10
        count++
    } while (number >= 1)
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var numberFirst = 1
    var numberSecond = 1
    var numberSum = 1
    for (i in 3..n) {
        numberSum = numberFirst + numberSecond
        numberSecond = numberFirst
        numberFirst = numberSum
    }
    return numberSum
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n / 2) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var number = x.toDouble()
    while (number != 1.0) {
        if (number % 2 == 0.0) number /= 2
        else number = number * 3 + 1
        count++
    }
    return count
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var checkMax = max(m, n)
    for (i in checkMax until m * n / 2 + 1 step (checkMax)) {
        if ((i % m == 0) && (i % n == 0)) return i
    }
    return m * n
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..min(m, n))
        if ((m % i == 0) && (n % i == 0)) {
            return false
        }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var numbers = mutableListOf<Int>()
    var oldNumber = n
    var newNumber = 0.0
    while (oldNumber > 0) {
        numbers.add(oldNumber % 10)
        oldNumber = oldNumber.div(10)
    }
    for (i in 0 until numbers.count()) newNumber += numbers[i] * 10.0.pow(numbers.count() - 1 - i)
    return newNumber.toInt()
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = if (n <= 2147447412) (n == revert(n)) else false

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val currentNumber = n % 10
    var doubleN = n / 10
    while (doubleN > 0) {
        if (doubleN % 10 != currentNumber) return true
        doubleN /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var oldNumber = 0.0
    var newNumber = x % (2 * PI)
    var countPow = 3
    var count = 1
    while (abs(newNumber - oldNumber) > eps) {
        oldNumber = newNumber
        if (count % 2 == 1) {
            newNumber -= ((x % (2 * PI)).pow(countPow) / factorial(countPow))
        } else {
            newNumber += ((x % (2 * PI)).pow(countPow) / factorial(countPow))
        }
        countPow += 2
        count++
    }
    return newNumber
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var oldNumber = 0.0
    var newNumber = 1.0
    var countPow = 2
    var count = 1
    while (sqr(newNumber - oldNumber) > sqr(eps)) {
        oldNumber = newNumber
        if (count % 2 == 1) newNumber -= ((x % (2 * PI)).pow(countPow) / factorial(countPow))
        else newNumber += ((x % (2 * PI)).pow(countPow) / factorial(countPow))
        countPow += 2
        count++
    }
    return newNumber
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun partNumber(n: Int, number: Int, m: Int): Int {
    var fibNumber = number
    for (i in 1..m - n) {
        fibNumber = fibNumber.div(10)
    }
    return fibNumber
}

fun squareSequenceDigit(n: Int): Int {
    var countNumbers = 0
    var countSquare: Int
    var square = 0
    while (n > countNumbers) {
        square++
        countSquare = sqr(square)
        countNumbers += digitNumber(countSquare)
        if (countNumbers >= n) {
            countSquare = partNumber(n, countSquare, countNumbers)
            return countSquare % 10
        }
    }
    return 0
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun fibSequenceDigit(n: Int): Int {
    var fibFirst: Int
    var fibSecond = 0
    var fibThird = 1
    var fibCount: Int
    var count = 0
    while (n > count) {
        fibFirst = fibSecond + fibThird
        fibThird = fibSecond
        fibSecond = fibFirst
        fibCount = fibFirst
        count += digitNumber(fibCount)
        if (count >= n) {
            fibCount = partNumber(n, fibCount, count)
            return fibCount % 10
        }
    }
    return 0
}
