package Homeworks.Algorithms;

import java.util.Arrays;
/**
 * ===========================================================
 * ЗАДАЧА: Сортировка массива слиянием (Merge Sort)
 * ===========================================================
 * <p>
 * Условие:
 * Дан массив целых чисел.
 * Нужно отсортировать его по возрастанию методом слияния.
 * <p>
 * Тема:
 * - Merge Sort
 * - Разделяй и властвуй
 * - Рекурсия
 * <p>
 * Идея:
 * 1) Делим массив на две половины.
 * 2) Рекурсивно сортируем левую и правую часть.
 * 3) Сливаем две отсортированные части в temp.
 * 4) Копируем результат обратно в исходный массив.
 * <p>
 * Почему работает:
 * - База рекурсии: массив длины 0 или 1 уже отсортирован.
 * - Если две половины отсортированы, их можно корректно слить в один отсортированный отрезок.
 * <p>
 * Асимптотика:
 * Время: O(n log n)
 * Память: O(n)
 * <p>
 * Важно:
 * - Используются полуинтервалы [l, r), поэтому r не включается.
 * - temp создаётся один раз и переиспользуется во всех merge.
 * - Сортировка стабильная, так как при array[i] <= array[j] сначала берётся левый элемент.
 * ===========================================================
 */
public class MyMergeSort {
    public static void main(String[] args) {
        int[] array = randomArray(10);

        System.out.println(Arrays.toString(array));

        mergeSort(array);

        System.out.println(Arrays.toString(array));

        int[] array2 = new int[array.length];

        System.arraycopy(array, 0, array2, 0, array.length);

        System.out.println(Arrays.toString(array2));
    }

    public static void mergeSort(int[] array) {
        if (array.length <= 1) return;
        int l =  0, r = array.length;
        int[] temp = new int[array.length];
        mergeSort(array, temp, l, r);
    }

    public static void mergeSort(int[] array, int[] temp, int l, int r) {
        if (r - l <= 1) return;
        int m = (l + r) >>> 1;
        mergeSort(array, temp, l, m);
        mergeSort(array, temp, m, r);
        merge(array, temp, l, m, r);
    }

    public static void merge(int[] array, int[] temp, int l, int m, int r) {
        int i = l, j = m, k = l;
        while (i < m && j < r) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i < m) temp[k++] = array[i++];
        while (j < r) temp[k++] = array[j++];

        System.arraycopy(temp, l, array, l, r - l);
    }

    public static int[] randomArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * length);
        }
        return array;
    }
}
