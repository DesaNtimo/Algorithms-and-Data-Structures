package Practice.Algorithms;
import java.util.Arrays;
/**
 * ============================================================
 * ЗАДАЧА: Проверка наличия общего элемента в двух массивах
 * ============================================================
 *
 * Условие:
 * Даны два отсортированных по возрастанию массива.
 * Определить, есть ли хотя бы один общий элемент.
 *
 * Тема:
 * - Two Pointers (два указателя)
 * - Работа с отсортированными массивами
 *
 * Идея:
 * 1) Указатели i и j стоят в начале массивов.
 * 2) Если элементы равны → найден общий → true.
 * 3) Если arr1[i] меньше → двигаем i.
 * 4) Если arr2[j] меньше → двигаем j.
 * 5) Если один массив закончился → общего нет.
 *
 * Почему работает:
 * - Массивы отсортированы.
 * - Мы всегда двигаем указатель у меньшего элемента,
 *   тем самым не пропуская возможные совпадения.
 *
 * Асимптотика:
 * Время: O(n + m)
 * Память: O(1)
 *
 * Важно:
 * - Метод корректен только для отсортированных массивов.
 * - generateRandomArray генерирует нестрого возрастающую последовательность
 *   через Math.max(random, prev) (гарантия неубывания).
 * - Если массивы не отсортированы — алгоритм работать не будет.
 * ============================================================
 */
@SuppressWarnings("ALL")
public class HasCommonElement {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(100);
        int[] arr2 = generateRandomArray(100);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));

        System.out.println(hasCommonElement(arr, arr2));
    }

    public static boolean hasCommonElement(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) return true;
            else if (arr1[i] > arr2[j]) j++;
            else i++;
        }
        return false;
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        arr[0] = 0;
        for (int i = 1; i < size; i++) {
            int random = (int) (Math.random() * size);
            arr[i] = Math.max(random, arr[i - 1]);;
        }
        return arr;
    }
}
