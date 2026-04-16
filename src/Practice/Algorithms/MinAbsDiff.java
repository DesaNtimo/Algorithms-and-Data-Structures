package Practice.Algorithms;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
/**
 * ============================================================
 * ЗАДАЧА: Минимальная абсолютная разница между элементами двух массивов
 * ============================================================
 *
 * Условие:
 * Даны два отсортированных массива. Найти пару (x из arr1, y из arr2),
 * для которой |x - y| минимально. Вернуть сами значения пары.
 *
 * Тема:
 * - Two Pointers (два указателя)
 * - Отсортированные массивы
 *
 * Идея:
 * i и j в начале массивов.
 * Считаем diff = |arr1[i] - arr2[j]|, обновляем минимум.
 * Двигаем указатель у меньшего элемента (чтобы сблизить значения).
 * Если diff == 0 — это лучший возможный ответ, можно остановиться.
 *
 * Асимптотика:
 * Время: O(n + m)
 * Память: O(1)
 *
 * Важно / pitfalls:
 * - arr1 и arr2 должны быть отсортированы (у тебя так и есть).
 * - diff считаем в long, чтобы не словить переполнение на больших int.
 * - Если один из массивов пустой → bestI/bestJ останутся -1 и будет ошибка доступа.
 *   Для надёжности нужно явно проверять входные данные.
 * ============================================================
 */
@SuppressWarnings("ALL")
public class MinAbsDiff {
    public static void main(String[] args) {
        int[] array = randomSortedArray(20);
        int[] massive = randomSortedArray(20);

        System.out.println(Arrays.toString(massive));
        System.out.println(Arrays.toString(array));

        System.out.println(Arrays.toString(minAbsDiff(array, massive)));
    }

    public static int[] minAbsDiff(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        long min = Long.MAX_VALUE;
        int bestI = -1, bestJ = -1;

        while (i < arr1.length && j < arr2.length) {
            long diff = Math.abs((long) arr1[i] - (long) arr2[j]);

            if (diff < min) {
                min = diff;
                bestI = i; bestJ = j;
                if (diff == 0) break;
            }

            if (arr1[i] > arr2[j]) j++;
            else i++;
        }
        return new int[]{arr1[bestI], arr2[bestJ]};
    }

    public static int[] randomSortedArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(i, length);
        }
        Arrays.sort(arr);
        return arr;
    }
}
