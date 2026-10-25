import java.io.*;
import java.util.Scanner;

/** Завдання 3: шифрування та дешифрування через Filter-потоки. */
public class CaesarCipher {

    public static void encrypt(String inPath, String outPath, char key) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(inPath));
             OutputStream out = new CipherOutputStream(new FileOutputStream(outPath), key)) {
            copy(in, out);
        }
    }

    public static void decrypt(String inPath, String outPath, char key) throws IOException {
        try (InputStream in = new CipherInputStream(new FileInputStream(inPath), key);
             OutputStream out = new BufferedOutputStream(new FileOutputStream(outPath))) {
            copy(in, out);
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[4096];
        int n;
        while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("1 - шифрувати, 2 - дешифрувати: ");
            int mode = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Вхідний файл: ");  String in = sc.nextLine().trim();
            System.out.print("Вихідний файл: "); String out = sc.nextLine().trim();
            System.out.print("Ключовий символ: ");
            String k = sc.nextLine();
            if (k.isEmpty()) throw new IllegalArgumentException("Ключ не введено");

            if (mode == 1) encrypt(in, out, k.charAt(0));
            else if (mode == 2) decrypt(in, out, k.charAt(0));
            else throw new IllegalArgumentException("Невідомий режим");
            System.out.println("Готово.");
        } catch (NumberFormatException e) {
            System.out.println("Режим має бути числом 1 або 2.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка файлу: " + e.getMessage());
        }
    }
}
