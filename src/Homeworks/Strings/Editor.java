package Homeworks.Strings;

import java.io.*;
import java.util.*;

public class Editor {
    static class Node {
        Node[] children = new Node[26];
        Node fail;
        Node dict; // Быстрая ссылка на следующий узел-слово (оптимизация)
        List<Integer> wordIndices = new ArrayList<>(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int q = Integer.parseInt(br.readLine());

        Node root = new Node();
        String[] queries = new String[q];

        // 1. Строим бор
        for (int i = 0; i < q; i++) {
            queries[i] = br.readLine();
            Node current = root;
            for (int j = 0; j < queries[i].length(); j++) {
                int c = queries[i].charAt(j) - 'a';
                if (current.children[c] == null) {
                    current.children[c] = new Node();
                }
                current = current.children[c];
            }
            current.wordIndices.add(i);
        }

        // 2. Строим fail-ссылки и dict-ссылки (BFS)
        Queue<Node> queue = new LinkedList<>();
        root.fail = root;

        for (int c = 0; c < 26; c++) {
            if (root.children[c] != null) {
                root.children[c].fail = root;
                queue.add(root.children[c]);
            } else {
                root.children[c] = root; // Замыкаем на корень для автомата
            }
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Прокидываем dict-ссылку для мгновенного поиска вхождений
            if (!current.fail.wordIndices.isEmpty()) {
                current.dict = current.fail;
            } else {
                current.dict = current.fail.dict;
            }

            for (int c = 0; c < 26; c++) {
                if (current.children[c] != null && current.children[c] != root) {
                    Node child = current.children[c];
                    child.fail = current.fail.children[c];
                    queue.add(child);
                } else {
                    current.children[c] = current.fail.children[c];
                }
            }
        }

        // 3. Поиск по тексту (сделано по-красоте)
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            results.add(new ArrayList<>());
        }

        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i) - 'a';
            assert current != null;
            current = current.children[c];

            Node temp = current;
            while (temp != null && temp != root) {
                for (int wordIdx : temp.wordIndices) {
                    // Обращаемся через .get() к нужному списку
                    results.get(wordIdx).add(i - queries[wordIdx].length() + 1);
                }
                temp = temp.dict;
            }
        }

        // 4. Вывод результата
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < q; i++) {
            List<Integer> currentResult = results.get(i);
            out.append(currentResult.size());
            for (int pos : currentResult) {
                out.append(" ").append(pos);
            }
            out.append("\n");
        }
        System.out.print(out);
    }
}