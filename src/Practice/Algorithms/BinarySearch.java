package Practice.Algorithms;
import java.util.concurrent.ThreadLocalRandom;
/**
 * ============================================================
 * ЗАДАЧА: Сравнение линейного и бинарного поиска
 * ============================================================
 *
 * Условие:
 * Дан отсортированный массив 1..N.
 * Реализовать linearSearch и binarySearch.
 * Измерить среднее время выполнения.
 *
 * Тема:
 * - Бинарный поиск
 * - Сравнение O(n) vs O(log n)
 *
 * Идея:
 * 1) Генерируем отсортированный массив.
 * 2) Делаем JIT warm-up.
 * 3) 1000 раз ищем случайный элемент и считаем среднее время.
 *
 * Асимптотика:
 * LinearSearch  — O(n)
 * BinarySearch  — O(log n)
 * Память        — O(1)
 *
 * Важно:
 * - Массив должен быть отсортирован.
 * - nanoTime() даёт шумные измерения (для точности — JMH).
 * - mid считаем как left + (right - left)/2 (защита от overflow).
 * ============================================================
 */
public class BinarySearch {
    public static void main(String[] args) {
        int countOfElements = 20_000_000;
        int[] arr = generateArray(countOfElements);
        warmUp(arr, countOfElements); // прогрев JIT

        testLinearSearch(arr, countOfElements);
        testBinarySearch(arr, countOfElements);
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == arr[mid]) return mid;
            else if (target > arr[mid]) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static void warmUp(int[] arr,  int countOfElements) {
        for (int i = 0; i < 1000; i++) {
            int random = ThreadLocalRandom.current().nextInt(1, countOfElements + 1);
            int LinearSearchIndex = linearSearch(arr, random);
            int BinarySearchIndex = binarySearch(arr, random);
        }
    }

    public static void testBinarySearch(int[] arr, int countOfElements) {
        long Sum = 0;
        for (int i = 0; i < 1000; i++) {
            int random = ThreadLocalRandom.current().nextInt(1, countOfElements + 1);
            long start = System.nanoTime();
            int pos = binarySearch(arr, random);
            long end = System.nanoTime();
            Sum += (end - start);
        }

        double average = Sum / 1000.0;
        double averageMs = average / 1_000_000.0;
        System.out.println("BinarySearch Average time: " + averageMs + " ms");
    }

    public static void testLinearSearch(int[] arr, int countOfElements) {
        long Sum = 0;
        for (int i = 0; i < 1000; i++) {
            int random = ThreadLocalRandom.current().nextInt(1, countOfElements + 1);
            long start = System.nanoTime();
            int pos = linearSearch(arr, random);
            long end = System.nanoTime();
            Sum += (end - start);
        }

        double average = Sum / 1000.0;
        double averageMs = average / 1_000_000.0;
        System.out.println("LinearSearch Average time: " + averageMs + " ms");
    }
}
