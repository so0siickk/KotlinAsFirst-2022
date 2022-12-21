package lesson6.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun twoDigitStr() {
        assertEquals("00", twoDigitStr(0))
        assertEquals("09", twoDigitStr(9))
        assertEquals("10", twoDigitStr(10))
        assertEquals("99", twoDigitStr(99))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }

    @Test
    @Tag("4")
    fun dateStrToDigit() {
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("32 сентября 2011"))
        assertEquals("", dateStrToDigit("29 февраля 1993"))
    }

    @Test
    @Tag("4")
    fun dateDigitToStr() {
        assertEquals("15 июля 2016", dateDigitToStr("15.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
        assertEquals("", dateDigitToStr("32.09.2011"))
        assertEquals("", dateDigitToStr("29.02.1993"))
    }

    @Test
    @Tag("4")
    fun flattenPhoneNumber() {
        assertEquals("+79211234567", flattenPhoneNumber("+7 (921) 123-45-67"))
        assertEquals("123456798", flattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals("+12345", flattenPhoneNumber("+12 (3) 4-5"))
        assertEquals("", flattenPhoneNumber("+12 () 4-5"))
        assertEquals("+425667", flattenPhoneNumber("+42 56 -- 67"))
        assertEquals("+42566789", flattenPhoneNumber("+42(56 -- 67)89"))
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("", flattenPhoneNumber("134_+874"))
    }

    @Test
    @Tag("5")
    fun bestLongJump() {
        assertEquals(717, bestLongJump("706 % - 717 - 703"))
        assertEquals(-1, bestLongJump("% - - % -"))
        assertEquals(754, bestLongJump("700 717 707 % 754"))
        assertEquals(-1, bestLongJump("700 + 700"))

    }

    @Test
    @Tag("6")
    fun bestHighJump() {
        assertEquals(226, bestHighJump("226 +"))
        assertEquals(-1, bestHighJump("???"))
        assertEquals(230, bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
    }

    @Test
    @Tag("6")
    fun plusMinus() {
        assertEquals(0, plusMinus("0"))
        assertEquals(4, plusMinus("2 + 2"))
        assertEquals(6, plusMinus("2 + 31 - 40 + 13"))
        assertEquals(-1, plusMinus("0 - 1"))
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+ 4") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - -2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("44 - - 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - + 12") }
    }

    @Test
    @Tag("6")
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет"))
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу"))
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю"))
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин"))
    }

    @Test
    @Tag("6")
    fun mostExpensive() {
        assertEquals("j", mostExpensive("j 0"))
        assertEquals("a", mostExpensive("j 0.01; a 0.02"))
        assertEquals("", mostExpensive(""))
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))
    }

    @Test
    @Tag("6")
    fun fromRoman() {
        assertEquals(-1, lesson6.task1.fromRoman("VIV"))
        assertEquals(-1, fromRoman("Ma"))
        assertEquals(1, fromRoman("I"))
        assertEquals(3000, fromRoman("MMM"))
        assertEquals(1978, fromRoman("MCMLXXVIII"))
        assertEquals(694, fromRoman("DCXCIV"))
        assertEquals(49, fromRoman("XLIX"))
        assertEquals(-1, fromRoman("Z"))
    }

    @Test
    @Tag("7")
    fun computeDeviceCells() {
        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1), computeDeviceCells(10, "+>+>+>+>+", 10000))
        assertEquals(listOf(-1, -1, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 10000))
        assertEquals(listOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 10000))
        assertEquals(
            listOf(0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0),
            computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 10000)
        )

        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), computeDeviceCells(10, "+>+>+>+>+", 4))
        assertEquals(listOf(0, 0, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 6))
        assertEquals(listOf(1, 1, 1, 0, 0, -1, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 17))
        assertEquals(
            listOf(0, 6, 5, 4, 3, 2, 1, 0, -1, -1, -2),
            computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 256)
        )
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "===", 3) }
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "+>+>[+>", 3) }
        assertThrows(IllegalStateException::class.java) { computeDeviceCells(20, ">>>>>>>>>>>>>", 12) }
    }

    //Тест
//    @Test
//
//    fun gasStation() {
//        assertThrows(IllegalArgumentException::class.java) {
//            lesson6.task1.gasStation(
//                mapOf("faf" to "fafaf", "fafaf" to " fafaf"), "e. - 5%; else - 25%",
//            )
//        }
//        assertEquals(
//            "Lada Vesta - Газпром" +
//                    "Lada Niva - Shell" +
//                    "BMW M5 - Лукойл" +
//                    "Копейка - Газпром" +
//                    "Трактор - Лукойл", lesson6.task1.gasStation(
//                mapOf(
//                    "Lada Vesta" to "бензин 98",
//                    "Lada Niva" to "дизель",
//                    "BMW M5" to "бензин 95",
//                    "Копейка" to "бензин 88",
//                    "Трактор" to "солярка"
//                ),
//                "Лукойл: бензин 95 - 44.66; дизель - 60.76; солярка - 10;" +
//                        "Газпром: бензин 98 - 50.00; бензин 88 - 34.30;" +
//                        "Shell: бензин 66 - 23.00; дизель - 55.50;",
//            )
//        )
//        assertThrows(java.lang.IllegalStateException::class.java) {
//            lesson6.task1.gasStation(
//                mapOf(
//                    "Lada Vesta" to "бензин 98",
//                    "Lada Niva" to "дизель",
//                    "BMW M5" to "бензин 95",
//                    "Копейка" to "бензин 88",
//                    "Трактор" to "солярка"
//                ),
//                "Лукойл: бензин 95 - 44.66; дизель - 60.76; солярка - 10;" +
//                        "Газпром: бензин 98 - 50.00; бензин 88 - 34.30;" +
//                        "Shell: бензин 66 - 23.00; дизель - 55.50;",
//            )
//        }
//
//    }

//    fun taxCounter() {
//        assertThrows(IllegalArgumentException::class.java) {
//            lesson6.task1.taxCounter(
//                " y.e. - 0%; 40000 y.e. - 5%; else - 25%",
//                40000
//            )
//        }
//        assertEquals(1000, lesson6.task1.taxCounter("20000 y.e. - 0%; 40000 y.e. - 5%; else - 25%", 40000))
//    }

    @Test
    fun placesNames() {
        assertEquals(
            mapOf(
                "Вася" to listOf(1, 2),
                "Петя" to listOf(1)
            ), lesson6.task1.placesNames(
                listOf(
                    listOf(true, false, false, false, true, false),
                    listOf(true, false, true, false)
                ) as MutableList<MutableList<Boolean>>,
                mapOf(
                    "Вася" to Pair(0, 2),
                    "Петя" to Pair(1, 1)
                )
            )
        )
        assertEquals(
            mapOf(
                "Вася" to listOf(),
                "Петя" to listOf(1)
            ), lesson6.task1.placesNames(
                listOf(
                    listOf(true, false, false, false, true, false),
                    listOf(true, false, true, false)
                ) as MutableList<MutableList<Boolean>>,
                mapOf(
                    "Вася" to Pair(0, 0),
                    "Петя" to Pair(1, 1)
                )
            )
        )
        assertThrows(IllegalStateException::class.java) {
            lesson6.task1.placesNames(
                listOf(
                    listOf(true, false, true, true, true),
                    listOf(false, false, false, false, false)
                ) as MutableList<MutableList<Boolean>>,
                mapOf(
                    "Вася" to Pair(1, 5),
                    "Петя" to Pair(1, 1)
                )
            )
        }
        assertThrows(IllegalStateException::class.java) {
            lesson6.task1.placesNames(
                listOf(
                    listOf(),
                    listOf(false, false, false, false, false)
                ) as MutableList<MutableList<Boolean>>,
                mapOf(
                    "Вася" to Pair(1, 5),
                    "Петя" to Pair(1, 1)
                )
            )
        }
        assertThrows(IllegalStateException::class.java) {
            lesson6.task1.placesNames(
                listOf(
                    listOf(true, false, false, true),
                    listOf(false, false, false, false, false)
                ) as MutableList<MutableList<Boolean>>,
                mapOf(
                    "Вася" to Pair(1, -2),
                    "Петя" to Pair(1, 1)
                )
            )
        }
    }
}