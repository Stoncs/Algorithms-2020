@file:Suppress("UNUSED_PARAMETER")

package lesson7

import kotlin.collections.mutableListOf as mu

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    //Время O(first.length * second.length)
    //Память O(first.length * second.length)
    var result = ""
    val matrix = Array(first.length + 1) { IntArray(second.length + 1) }
    for (i in first.length - 1 downTo 0) {
        for (j in second.length - 1 downTo 0) {
            matrix[i][j] = (if (first[i] == second[j]) matrix[i + 1][j + 1] + 1
            else maxOf(matrix[i + 1][j], matrix[i][j + 1]))
        }
    }
    var i = 0
    var j = 0
    while (i < first.length && j < second.length) {
        if (first[i] == second[j]) {
            result += first[i]
            i++
            j++
        } else {
            if (matrix[i + 1][j] >= matrix[i][j + 1]) i++
            else j++
        }
    }
    return result
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val counts = MutableList(list.size) { 0 }
    val previous = MutableList(list.size) { 0 }
    var maxIndex = 0
    var max = 0
    if (list.size <= 1) return list
    for (j in list.indices) {
        counts[j] = 0
        previous[j] = -1
        var i = 0
        while (i < j) {
            if (list[i] < list[j] && counts[i] + 1 > counts[j]) {
                counts[j] = counts[i] + 1
                previous[j] = i
                if (counts[j] > max) {
                    max = counts[j]
                    maxIndex = j
                }
            }
            i++
        }
    }
    val result = mu<Int>()
    var position = maxIndex
    while (position != -1) {
        result.add(list[position])
        position = previous[position]
    }
    return result.reversed()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5