@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var results = ""
    val arrayGod = arrayOf(1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 121, 131, 141, 151, 161, 171, 181, 191)
    val arrayGoda = intArrayOf(2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74,
            82, 83, 84, 92, 93, 94, 102, 103, 104, 122, 123, 124, 132, 133, 134, 142, 143, 144, 152, 153, 154, 162, 163, 164, 172,
            173, 174, 182, 183, 184, 192, 193, 194)
    val arrayLet = (5..20).toList() + (25..30).toList() + (35..40).toList() + (45..50).toList() + (55..60).toList()+ (65..70).toList()+
            (75..80).toList() + (85..90).toList()+ (105..119).toList() + (125..130).toList() + (135..140).toList()+ (145..150).toList() +
            (155..160).toList() + (165..170).toList() + (175..180).toList()+ (185..190).toList()+ (195..200).toList()
    if (age < 0 || age > 200){
        if(age < 0) {
            results = "Age is less than 0. Enter correct age"
        }
        else {
            results = "Age is more than 200. Enter correct age"
        }
    }
    else{
        when(age){
            in arrayGod -> results  = "$age год"
            in arrayGoda -> results = "$age года"
            in arrayLet -> results = "$age лет"
            else -> results = "Something went wrong"
        }
    }
    return results
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */


fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    var result = 0.0
    val halfWay = (t1*v1 + t2*v2 + t3*v3)/2

    if((halfWay - v1*t1) > 0) {
        result = result + t1;
        if(((halfWay - v1*t1) - v2*t2) > 0) {
            result = result + t2;
//            if((((halfWay - v1*t1) - v2*t2) - v3*t3) == 0.0) {
//                result = +t3
//                return result
//            }
            if((((halfWay - v1*t1) - v2*t2) - v3*t3) < 0) {
                result = result + ((halfWay - v1*t1) - v2*t2)/v3;
                return result
            }
        }
        if(((halfWay - v1*t1) - v2*t2) == 0.0) {
            result = result + t2
            return result
        }
        if(((halfWay - v1*t1) - v2*t2) < 0) {
            result = result +(halfWay - v1*t1)/v2;
            return result
        }
    }
    if((halfWay - v1*t1) == 0.0) {
        result = t1
        return result
    }
    if((halfWay - v1*t1) < 0) {
        result = halfWay/v1;
        return result
    }
    return result
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int = TODO()

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int = TODO()

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int = TODO()

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    var result = 0
    if (b >= a && d >= c) {

        if (a in c..d){
            if(b in c..d){
                result = b - a;
            }
            else{
                result=d - a
            }
        }
        else{
            if(b in c..d){
                result = b - c;
            }
            else{
                if(c in a..b){
                    if(d in a..b){
                        result = d - c
                    }
                    else{
                        result = b - c
                    }
                }
                else{
                    result=-1
                }

            }
        }


    }
    return result
}
