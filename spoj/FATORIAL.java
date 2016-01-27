import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    private static long[] cache = new long[1000000 + 1];
    static {
        cache[0] = 1;
        cache[1] = 1;
    }

    private static int lastValueProcessed = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/FATORIAL.txt")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        //long start = System.currentTimeMillis();

        long c = 0;
        while (reader.ready()) {
            String line = reader.readLine();

            byte result = doCalc(Integer.parseInt(line));
            writer.append("Instancia " + (++c) + "\n" + result + "\n");
        }
        writer.flush();

        //System.out.println(System.currentTimeMillis() - start);
    }

    public static byte doCalc(final int value) {
        if (cache[value] != 0) {
            return getLastDigitFromCache(value);
        }
        if (cache[value - 1] != 0) {
            processValue(value);
            return getLastDigitFromCache(value);
        }

        for (int i = lastValueProcessed + 1; i <= value; ++i) {
            long j = cache[i - 1];
            j *= i;

            j = trailZeros(j);
            j = removeUnecessary(j);

            cache[i] = j;
        }
        //processValue(value);
        lastValueProcessed = value;
        return getLastDigitFromCache(value);
    }

    private static void processValue(int value) {
        long l = cache[value - 1];
        long result = l * value;
        result = trailZeros(result);
        result = removeUnecessary(result);
        cache[value] = result;
        lastValueProcessed = value;
    }

    private static long trailZeros(long value) {
        while (value % 10 == 0) {
            value /= 10;
        }
        return value;
    }

    public static int removeUnecessary(long value) {
        if (value < 1000000) {
            return (int) value;
        }
        return (int) (value % 1000000);
    }

    public static byte getLastDigitFromCache(int value) {
        return (byte) (cache[value] % 10);
    }

}
