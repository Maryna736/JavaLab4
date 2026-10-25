import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

/** Завдання 4: частота HTML-тегів на сторінці за URL. */
public class TagFrequency {
    private static final Pattern TAG = Pattern.compile("<([a-zA-Z][a-zA-Z0-9-]*)");

    public static Map<String, Integer> count(String url) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = TAG.matcher(line);
                while (m.find()) map.merge(m.group(1).toLowerCase(), 1, Integer::sum);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть URL (напр. https://example.com): ");
        String url = sc.nextLine().trim();
        try {
            Map<String, Integer> freq = count(url);

            System.out.println("\n== Лексикографічний порядок ==");
            new TreeMap<>(freq).forEach((t, c) -> System.out.println(t + " : " + c));

            System.out.println("\n== За зростанням частоти ==");
            List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
            list.sort(Map.Entry.<String, Integer>comparingByValue()
                    .thenComparing(Map.Entry.comparingByKey()));
            for (Map.Entry<String, Integer> e : list)
                System.out.println(e.getKey() + " : " + e.getValue());
        } catch (java.net.MalformedURLException e) {
            System.out.println("Некоректний URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
    }
}
