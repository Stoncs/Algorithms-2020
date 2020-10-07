@file:Suppress("UNUSED_PARAMETER")

package lesson2

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 *
 * Общий комментарий: решение из Википедии для этой задачи принимается,
 * но приветствуется попытка решить её самостоятельно.
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
fun longestCommonSubstring(first: String, second: String): String {
    //Трудоемкость = O(NM)
    //Ресурсоемкость = O(NM)
    //где N - длина первой строки, M - длина второй строки
    val array = Array(first.length) { Array(second.length) { 0 } }
    var result = ""
    var maxLength = 1
    for (letter1 in first.indices) {
        for (letter2 in second.indices) {
            if (array[letter1][letter2] != 0) continue
            if (first[letter1] == second[letter2]) {
                array[letter1][letter2] = 1
                var count = 1
                var inc = 1
                while (true) {
                    if (letter1 + inc > first.length - 1 || letter2 + inc > second.length - 1) break
                    if (first[letter1 + inc] == second[letter2 + inc]) {
                        count++
                        array[letter1 + inc][letter2 + inc] = count
                        if (count > maxLength) maxLength = count
                    } else
                        break
                    inc++
                }
            }
        }
    }
    for (i in first.indices){
        for (j in second.indices) {
            if (array[i][j] == maxLength) {
                var k = maxLength - 1
                while (k >= 0) {
                    result += first[i - k]
                    k--
                }
                return result
            }
        }
    }
    return result
}


/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    //Трудоемкость = O(N log log N)
    //Ресурсоемкость = O(N)
    if (limit <= 1) return 0
    var count = 0
    val numbers = mutableListOf(false, false)
    for (i in 2..limit) numbers.add(i, true)
    var i = 2
    while (i * i <= limit) {
        var l = i * i
        if (numbers[i]) {
            count++
            while (l <= limit) {
                numbers[l] = false
                l += i
            }
        }
        i++
    }
    for (k in i..limit)
        if (numbers[k]) count++
    return count
}