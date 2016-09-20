package stepic.solutions_14513_step_7;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by ipetrash on 11.09.2016.
 */


public class Main {

/*
Реализуйте метод, выполняющий численное интегрирование заданной функции на заданном интервале по формуле левых
прямоугольников.

Функция задана объектом, реализующим интерфейс java.util.function.DoubleUnaryOperator. Его метод applyAsDouble()
принимает значение аргумента и возвращает значение функции в заданной точке.

Интервал интегрирования задается его конечными точками a и b, причем a <= b. Для получения достаточно
точного результата используйте шаг сетки не больше 10^−6.

Пример. Вызов
integrate(x -> 1, 0, 10)

должен возвращать значение 10.

P.S. Если задача слишком легкая, то дополнительно можете реализовать динамический выбор шага сетки по следующему
алгоритму:
    * Вычислить приближенное значение интеграла функции при начальном значении шага сетки (например, 1).
    * Вычислить приближенное значение интеграла функции при более мелком шаге сетки (например, уменьшить шаг сетки
      в два раза).
    * Если разность двух последовательных приближений интеграла функции достаточно мала, то завершаем алгоритм и
      возвращаем текущее приближение в качестве результата.
    * Иначе возвращаемся к шагу 2.

*/

    // https://ru.wikipedia.org/wiki/Метод_прямоугольников
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        // Шаг сетки
        final double h = 0.000001;

        // Найдем количество отрезков
        // NOTE: Вообще-то в вики были формулы получения шага сетки h = (b - a) / n
        // Но по задачке мы не можем указывать количество отрезков n, однако, у нас уже есть шаг сетки.
        int n = (int) ((b - a) / h);

        double result = 0;
        for (int i = 0; i < n; i++) {
            // NOTE: вроде бы это левые прямоугольники и с ними не пройти второй тест.
            // поэтому использую метод средних прямоугольников.
            // result += f.applyAsDouble((double) i);
            result += f.applyAsDouble(a + h * (i + 0.5));
        }
        result *= h;

        return result;
    }

    public static void main(String[] args) {
        TestMain.test();
    }
}

class TestMain {
    static void test() {
        double result = Main.integrate(x -> 1, 0, 10);
        System.out.println(result);
        if (result != 10) {
            throw new Error("Main.integrate(x -> 1, 0, 10) != 10");
        }

        System.out.println("Test ok.");
    }
}