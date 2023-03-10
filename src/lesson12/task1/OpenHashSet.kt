@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import java.util.Objects

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    private val emptyObject = Any()
    internal var elements = Array<Any?>(capacity) { emptyObject }


    /**
     * Число элементов в хеш-таблице
     */
    var size: Int = 0

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean = size == 0

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        val startIndex = getIndex(element)
        var index = startIndex
        return if ((size == capacity) || (elements[index] == element)) false
        else {
            if (elements[index] != emptyObject) {
                index = (index + 1) % capacity
                while (index != startIndex) {
                    if (elements[index] == element) return false
                    if (elements[index] == emptyObject) {
                        elements[index] = element
                        size++
                        return true
                    }
                    index = (index + 1) % capacity
                }
            }
            elements[index] = element
            size++
            true
        }
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean {
        val startIndex = getIndex(element)
        var index = startIndex
        return if (elements[index] == element) true
        else {
            if (elements[index] == emptyObject) return false
            index = (index + 1) % capacity
            while (startIndex != index) {
                if (elements[index] == element) return true
                if (elements[index] == emptyObject) return false
                index = (index + 1) % capacity
            }
            false
        }
    }


    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (other is OpenHashSet<*>) {
            if (other.size != this.size) return false
            for (element in this.elements) {
                if (element != emptyObject) {
                    if (!other.elements.contains(element)) {
                        return false
                    }
                }
            }
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        var hash = 0
        for (element in elements) {
            if (element != emptyObject) {
                hash += Objects.hash(element)
            }
        }
        return hash
    }

    private fun getIndex(element: T): Int = (element.hashCode() % capacity)
}