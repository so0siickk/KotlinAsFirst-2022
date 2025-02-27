@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson10.task1.parseExpr
import ru.spbstu.wheels.out
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.lang.IllegalArgumentException
import java.util.*

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    TODO()
}//    val outputFile = File(outputName).bufferedWriter()
//    for (line in File(inputName).readLines()) {
//        if (line.isNotEmpty()) {
//            if (line[0].toString() != "_")
//                outputFile.use {
//                    println(line)
//                    println(outputFile.readLines())
//                    it.write(line + "\n")
//                }
//                println(outputFile.readLines())
//                File(outputName).bufferedWriter().use { out -> out.newLine() }
//                outputFile.write(line)
//                outputFile.newLine()
//            }
//        } else outputFile.use { it.write("\n") }
//            outputFile.newLine()
//    }
//}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val answer = mutableMapOf<String, Int>()
    var countOfChar: Int
    val startSubstrings = substrings.toSet()
    var substringLow: String
    var currentList: List<String>
    for (substring in startSubstrings) {
        answer[substring] = 0
    }
    var lines: List<String> = (File(inputName).readLines()).map { it.lowercase(Locale.getDefault()) }
    for (line in lines) {
        for (substring in startSubstrings) {
            countOfChar = 0
            substringLow = substring.lowercase(Locale.getDefault())
            currentList = line.windowed(substringLow.length, 1)
            for (window in currentList) {
                if (window == substringLow) countOfChar++
            }
            if (answer.keys.contains(substring)) answer[substring] = answer[substring]!! + countOfChar
            else answer[substring] = countOfChar
        }
    }
    return answer
}


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    val outputFile = File(outputName).bufferedWriter()
    val newDictionary = mutableMapOf<Char, String>()
    var newDictionaryString: String
    for ((key, value) in dictionary) {
        if (key == key.uppercaseChar()) newDictionary[key.lowercaseChar()] = value
        else newDictionary[key] = value
    }
    for (line in File(inputName).readLines()) {
        for (char in line) {
            if (newDictionary.keys.contains(char.lowercaseChar())) {
                newDictionaryString = newDictionary[char.lowercaseChar()]?.lowercase(Locale.getDefault()).toString()
                if ((char == char.uppercaseChar()) && (((char.code >= ('A').code) && (char.code <= ('Z').code))
                            || ((char >= 'А') && (char.code <= ('я').code)) ||
                            ((char.code >= ('a').code) && (char.code <= ('z').code))
                            )
                ) {
                    outputFile.write(
                        newDictionaryString.capitalize()
                    )
                } else outputFile.write(newDictionaryString)
            } else {
                outputFile.write(char.toString())
            }
        }
        outputFile.newLine()
    }
    outputFile.close()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

//Экзамен
//fun highestScoreTeam(inputName: String, outputName: String) {
//    val startData = File(inputName).readLines()
//    val outputFile = File(outputName).bufferedWriter()
//    val testRegex = Regex("""(\w+ )+(\|[0-2]-[0-2])+""")
//    val listOfTeams = mutableMapOf<String, Pair<Int, Int>>()
//    var summPoints: Int
//    var countWins: Int
//    var points: String
//    var status = true
//    for (currentString in startData) {
//        summPoints = 0
//        countWins = 0
//        if (!currentString.matches(testRegex)) throw IllegalArgumentException()
//        points = currentString.replace(Regex("""(\w+ )+"""), "")
//        for (score in 1..points.length step 4) {
//            summPoints += points[score].toString().toInt()
//            if (points[score].toString().toInt() == 2) countWins++
//        }
//        listOfTeams[currentString.replace(Regex("""(\|[0-2]-[0-2])+"""), "")] =
//            Pair(summPoints, countWins)
//    }
//    val newTeams: MutableList<Pair<String, Pair<Int, Int>>> =
//        listOfTeams.toList() as MutableList<Pair<String, Pair<Int, Int>>>
//    while (status) {
//        status = false
//        for (numberTeam in 1 until newTeams.size) {
//            if (newTeams[numberTeam].second.first > newTeams[numberTeam - 1].second.first) {
//                Collections.swap(newTeams, numberTeam, numberTeam - 1)
//                status = true
//            }
//            if (newTeams[numberTeam].second.first == newTeams[numberTeam - 1].second.first) {
//                if (newTeams[numberTeam].second.second > newTeams[numberTeam - 1].second.second) {
//                    Collections.swap(newTeams, numberTeam, numberTeam - 1)
//                    status = true
//                }
//                if (newTeams[numberTeam].second.second == newTeams[numberTeam - 1].second.second) {
//                    if (newTeams[numberTeam].first[0].toLowerCase().code <
//                        newTeams[numberTeam - 1].first[0].toLowerCase().code
//                    ) {
//                        Collections.swap(newTeams, numberTeam, numberTeam - 1)
//                        status = true
//                    }
//                }
//            }
//        }
//    }
//    outputFile.use { it.write(newTeams.toString()) }
//}


//fun square(inputName: String, outputName: String, range: String) {
//    val testRegex = Regex("""[A-Z]{1}\d+-[A-Z]{1}\d+""")
//    val txtRegex = Regex("""((|-)\d*\.\d*(, |))+""")
//    val startData = File(inputName).readLines()
//    val outputFile = File(outputName).bufferedWriter()
//    val codeLetterA = 65
//    var listOfNumber: List<String>
//    var summ = 0.0
//    var count = 0
//    if (!range.matches(testRegex)) throw IllegalArgumentException()
//    for ((countOfString, currentString) in startData.withIndex()) {
//        if (!currentString.matches(txtRegex)) throw IllegalArgumentException()
//        if ((countOfString >= (range.replace(Regex("""\d"""), "").substringBefore("-")
//                .toCharArray()[0].code - codeLetterA)) &&
//            (countOfString <= (range.replace(Regex("""\d"""), "").substringAfter("-")
//                .toCharArray()[0].code - codeLetterA))
//        ) {
//            listOfNumber = currentString.split(",")
//            println(listOfNumber)
//            for (number in listOfNumber.indices) {
//                if ((number >= range.replace(Regex("""[A-Z]"""), "").substringBefore("-").toInt() - 1) &&
//                    number <= range.replace(Regex("""[A-Z]"""), "").substringAfter("-").toInt() - 1
//                ) {
//                    summ += listOfNumber[number].toDouble()
//                    count++
//                }
//            }
//        }
//
//    }
//    println(summ)
//    println(count)
//    outputFile.use { it.write((summ / count).toString()) }
//}

//fun stream(inputName: String, outputName: String) {
//    val startData = File(inputName).readLines()
//    val answer = File(outputName).bufferedWriter()
//    val testRegex = Regex("""(\w+|[А-я]*): (\d*h:\d*m|\d*m), \d*""")
//    val streamersStatistics = mutableMapOf<String, Double>()
//    var time = 0.0
//    var listStreamers = listOf<Pair<String, Double>>()
//    var currentkey: String
//    for (currentString in startData) {
//        if (!currentString.contains(testRegex)) throw IllegalArgumentException()
//        if (Regex("""\d*h:\d*m""") in currentString) time =
//            currentString.replace(Regex("""(\w+|[А-я]*): """), "").replaceAfter("h", "").replace("h", "").toDouble() +
//                    currentString.replace(Regex("""(\w+|[А-я]*): \d*h:"""), "").replaceAfter("m", "").replace("m", "")
//                        .toDouble() / 60
//        currentkey = currentString.replace(Regex(""": (\d*h:\d*m|\d*m), \d*"""), "")
//        if (!streamersStatistics.keys.contains(currentkey))
//            streamersStatistics[currentkey] =
//                time * currentString.replaceBefore(',', "").replace(", ", "").toDouble()
//        else streamersStatistics[currentkey] =
//            streamersStatistics[currentkey]!! + time * currentString.replaceBefore(',', "").replace(", ", "").toDouble()
//        listStreamers = streamersStatistics.toList()
//        println(listStreamers.sortedBy { it.second }.reversed())
//    }
//    answer.use {
//        for (streamer in listStreamers) {
//            it.write("${streamer.first} -> ${streamer.second} \n")
//        }
//    }
//}


fun dokaTournament(inputName: String): List<String> {
    val inputFile = File(inputName).readLines()
    val testRegex = Regex("""(\w* )*(\|[0-2]*-([0-2]+| |))+""")
    var listOfTeams = mutableListOf<Pair<String, Pair<Int, Int>>>()
    val teamsString = mutableListOf<String>()
    val listOfGames = mutableListOf<Pair<String, Pair<Int, Int>>>()
    var numberGame = 0
    var teamName: String
    var gameResults: MutableList<String>
    var count = true
    var teamScore: Int
    var teamWins: Int
    for ((countString, currentString) in inputFile.withIndex()) {
        teamScore = 0
        teamWins = 0
        numberGame = 0
        if (!currentString.contains(testRegex)) throw IllegalStateException()
        gameResults = currentString.split('|') as MutableList<String>
        teamName = gameResults[0]
        gameResults.removeAt(0)
        for (result in gameResults) {
            numberGame++
//                countGames += 1
            if (result[0] == '2') {
                listOfGames += Pair("Win", Pair(countString + 1, numberGame))
                teamScore += 2
                teamWins++
            }
            if (result[0] == '1') {
                teamScore++
                listOfGames += Pair("Draw", Pair(countString + 1, numberGame))
            }
            if (result[0] == '0') listOfGames += Pair("Lose", Pair(countString + 1, numberGame))
            if (result[0] == '-') listOfGames += Pair("Empty", Pair(countString + 1, numberGame))
        }
        listOfTeams.add(0, Pair(teamName, Pair(teamScore, teamWins)))
    }
    for (place in listOfGames) {
        if (place.second.first == place.second.second) {
            if (place.first != "Empty") throw java.lang.IllegalStateException()
        }
        if (place.first == "Draw") {
            if (!listOfGames.contains(Pair("Draw", place.second.reverse()))) throw java.lang.IllegalStateException()
        }
        if (place.first == "Win") {
            if (!listOfGames.contains(Pair("Lose", place.second.reverse()))) throw java.lang.IllegalStateException()
        }
        if (place.first == "Lose") {
            if (!listOfGames.contains(Pair("Win", place.second.reverse()))) throw java.lang.IllegalStateException()
        }
        if (place.first == "Empty") {
            if ((!listOfGames.contains(Pair("Empty", place.second.reverse())))) throw java.lang.IllegalStateException()
        }
    }
    listOfTeams = listOfTeams.sortedBy { it.second.first }.reversed() as MutableList<Pair<String, Pair<Int, Int>>>
    println(listOfTeams)
    while (count) {
        println(listOfTeams)
        count = false
        for (numberteam in 1 until listOfTeams.size) {
            if (listOfTeams[numberteam - 1].second.first == listOfTeams[numberteam].second.first) {
                if (listOfTeams[numberteam - 1].second.second < listOfTeams[numberteam].second.second) {
                    Collections.swap(
                        listOfTeams,
                        numberteam - 1,
                        numberteam
                    )
                    count = true
                }
                if (listOfTeams[numberteam - 1].second.second == listOfTeams[numberteam].second.second) {
                    if (listOfTeams[numberteam - 1].first > listOfTeams[numberteam].first) {
                        Collections.swap(
                            listOfTeams,
                            numberteam - 1,
                            numberteam
                        )
                        count = true
                    }
                }
            }
        }

    }
    for (teams in listOfTeams) {
        teamsString += teams.first
    }
    println(teamsString)
    return teamsString
}

fun <A, B> Pair<A, B>.reverse() = Pair(second, first)