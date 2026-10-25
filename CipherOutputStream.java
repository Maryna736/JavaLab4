import java.io.*;

/** Шифрує: кожен байт збільшується на код ключового символу. */
public class CipherOutputStream extends FilterOutputStream {
    private final int key;

    public CipherOutputStream(OutputStream out, char key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        out.write((b + key) & 0xFF);
    }

    @Override
    public void write(byte[] buf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) write(buf[i]);
    }
}
