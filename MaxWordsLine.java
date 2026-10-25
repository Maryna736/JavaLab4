import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/** Завдання 1: рядок з максимальною кількістю слів у файлі. */
public class MaxWordsLine {
    static int countWords(String line) {
        String t = line.trim();
        return t.isEmpty() ? 0 : t.split("\\s+").length;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу: ");
        String path = sc.nextLine().trim();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line, best = null;
            int max = -1;
            while ((line = br.readLine()) != null) {
                int n = countWords(line);
                if (n > max) { max = n; best = line; }
            }
            if (best == null) System.out.println("Файл порожній.");
            else System.out.println("Рядок (" + max + " слів): " + best);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + path);
        } catch (IOException e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
    }
}
