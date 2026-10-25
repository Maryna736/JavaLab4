import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Завдання 2: меню з додаванням, пошуком, збереженням у файл і читанням з файлу. */
public class BookApp {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1 - Додати книгу\n2 - Показати всі\n3 - Пошук за автором"
                    + "\n4 - Зберегти у файл\n5 - Прочитати з файлу\n0 - Вихід");
            System.out.print("Ваш вибір: ");
            switch (sc.nextLine().trim()) {
                case "1": add(); break;
                case "2": show(books); break;
                case "3": search(); break;
                case "4": save(); break;
                case "5": load(); break;
                case "0": return;
                default: System.out.println("Невірна команда.");
            }
        }
    }

    private static void add() {
        try {
            System.out.print("Назва: ");  String t = sc.nextLine().trim();
            System.out.print("Автор: ");  String a = sc.nextLine().trim();
            System.out.print("Рік: ");    int y = Integer.parseInt(sc.nextLine().trim());
            if (t.isEmpty() || a.isEmpty()) throw new IllegalArgumentException("Порожнє поле");
            books.add(new Book(t, a, y));
            System.out.println("Додано.");
        } catch (NumberFormatException e) {
            System.out.println("Рік має бути цілим числом.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        }
    }

    private static void search() {
        System.out.print("Введіть автора (або його частину): ");
        String q = sc.nextLine().trim().toLowerCase();
        List<Book> res = new ArrayList<>();
        for (Book b : books)
            if (b.getAuthor().toLowerCase().contains(q)) res.add(b);
        show(res);
    }

    private static void show(List<Book> list) {
        if (list.isEmpty()) { System.out.println("Список порожній."); return; }
        for (int i = 0; i < list.size(); i++)
            System.out.println((i + 1) + ". " + list.get(i));
    }

    private static void save() {
        System.out.print("Шлях і ім'я файлу для збереження: ");
        String path = sc.nextLine().trim();
        try {
            FileManager.save(books, path);
            System.out.println("Збережено: " + books.size() + " об'єкт(ів).");
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    private static void load() {
        System.out.print("Шлях і ім'я файлу для читання: ");
        String path = sc.nextLine().trim();
        try {
            books = FileManager.load(path);
            System.out.println("Прочитано: " + books.size() + " об'єкт(ів).");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
    }
}
