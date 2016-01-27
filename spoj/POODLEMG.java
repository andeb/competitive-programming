import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Arrays;

public class Main {

    public static <T> void main(String... args) throws Exception {
        
        // Reader r = new Reader("t/POODLEMG");
        Reader r = new Reader();
        
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true) {
            int n = r.readInt(), p = r.readInt();
            if (n==0 && p ==0) {
                break;
            }
            
            int s = n / p;
            if (n % p != 0) s++;
            
            if (s >= 20) {
                w.append("Poooooooooooooooodle");
                w.append('\n');
            } else if (s <= 6) {
                w.append("Poodle");
                w.append('\n');
            } else {
                StringBuilder b = new StringBuilder("Poo");                
                s -= 6;
                for (int i = 0; i < s; i++) {
                    b.append('o');
                }
                b.append("dle");
                w.append(b);
                w.append('\n');
            }
        }

        w.flush();
    }

    static class Reader {

        private byte[] buf = new byte[0];
        private int pos;
        private int count;

        Reader() {
            this(System.in);
        }

        Reader(String filePath) {
            this(getStream(filePath));
        }

        private static FileInputStream getStream(String filePath) {
            try {
                return new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        Reader(InputStream stream) {
            try {
                int available;
                while ((available = stream.available()) != 0) {
                    byte[] bytes = new byte[available];
                    stream.read(bytes);

                    int oldCount = count;
                    count += available;
                    buf = Arrays.copyOf(buf, count);
                    System.arraycopy(bytes, 0, buf, oldCount, available);
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        private int readInt() {
            int count = this.count;

            int offset = pos++;
            while (pos < count && isDigit()) {
                ++pos;
            }
            if (pos > count) {
                return 0;
            }
            int v = parseInt(offset);
            ignoreSpaceAndCarriageAndNewLine();
            return v;
        }

        private void ignoreSpaceAndCarriageAndNewLine() {
            int count = this.count;
            byte[] buf = this.buf;

            if (pos < count) {
                byte b = buf[pos];
                while (b == '\n' || b == '\r' || b == ' ') {
                    ++pos;
                    if (pos >= count) {
                        break;
                    }
                    b = buf[pos];
                }
            }
        }

        private boolean isDigit() {
            byte b = buf[pos];
            return b >= 48 && b <= 57;
        }

        private int parseInt(int offset) {
            byte[] buf = this.buf;
            int pos = this.pos;

            int v = 0;
            boolean isNegative = buf[offset] == '-';
            for (int i = isNegative ? offset + 1 : offset; i < pos; ++i) {
                int digit = buf[i] - 48;
                v *= 10;
                v -= digit;
            }
            return isNegative ? v : -v;
        }

    }

}

class PerformanceMonitor {

    private final int availableProcessors = getOperatingSystemMXBean().getAvailableProcessors();
    private long lastSystemTime = 0;
    private long lastProcessCpuTime = 0;

    public synchronized double getCpuUsage() {
        if (lastSystemTime == 0) {
            baselineCounters();
            return -1;
        }

        long systemTime = System.nanoTime();
        long processCpuTime = 0;

        if (getOperatingSystemMXBean() instanceof OperatingSystemMXBean) {
            processCpuTime = ((com.sun.management.OperatingSystemMXBean) getOperatingSystemMXBean()).getProcessCpuTime();
        }

        double cpuUsage = (double) (processCpuTime - lastProcessCpuTime) / (systemTime - lastSystemTime);

        lastSystemTime = systemTime;
        lastProcessCpuTime = processCpuTime;

        return cpuUsage / availableProcessors;
    }

    private OperatingSystemMXBean getOperatingSystemMXBean() {
        return ManagementFactory.getOperatingSystemMXBean();
    }

    private void baselineCounters() {
        lastSystemTime = System.nanoTime();

        if (getOperatingSystemMXBean() instanceof OperatingSystemMXBean) {
            lastProcessCpuTime = ((com.sun.management.OperatingSystemMXBean) getOperatingSystemMXBean()).getProcessCpuTime();
        }
    }

}
