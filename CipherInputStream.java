import java.io.*;

/** Дешифрує: від кожного байта віднімається код ключового символу. */
public class CipherInputStream extends FilterInputStream {
    private final int key;

    public CipherInputStream(InputStream in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        return b == -1 ? -1 : (b - key) & 0xFF;
    }

    @Override
    public int read(byte[] buf, int off, int len) throws IOException {
        int n = in.read(buf, off, len);
        for (int i = off; i < off + n && n > 0; i++)
            buf[i] = (byte) ((buf[i] - key) & 0xFF);
        return n;
    }
}
