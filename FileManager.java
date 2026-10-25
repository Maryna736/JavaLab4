import java.io.*;
import java.util.ArrayList;
import java.util.List;

/** Робота з файлами винесена в окремий клас; використовуються об'єктні потоки. */
public class FileManager {

    public static void save(List<Book> books, String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)))) {
            oos.writeObject(new ArrayList<>(books));
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path)))) {
            return (List<Book>) ois.readObject();
        }
    }
}
