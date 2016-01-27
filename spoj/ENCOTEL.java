import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

class Main {

    private static Map<Character, Character> map = new HashMap<Character, Character>();
    static {
        // ABC 2
        map.put('A', '2');
        map.put('B', '2');
        map.put('C', '2');
        // DEF 3
        map.put('D', '3');
        map.put('E', '3');
        map.put('F', '3');
        // GHI 4
        map.put('G', '4');
        map.put('H', '4');
        map.put('I', '4');
        // JKL 5
        map.put('J', '5');
        map.put('K', '5');
        map.put('L', '5');
        // MNO 6
        map.put('M', '6');
        map.put('N', '6');
        map.put('O', '6');
        // PQRS    7
        map.put('P', '7');
        map.put('Q', '7');
        map.put('R', '7');
        map.put('S', '7');
        // TUV 8
        map.put('T', '8');
        map.put('U', '8');
        map.put('V', '8');
        // WXYZ    9
        map.put('W', '9');
        map.put('X', '9');
        map.put('Y', '9');
        map.put('Z', '9');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test/ENCOTEL")));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (reader.ready()) {
            String l = reader.readLine();
            StringBuilder b = new StringBuilder(l);
            t(b);

            writer.append(b + "\n");
        }

        writer.flush();
    }

    private static void t(StringBuilder l) {
        for (int i = 0; i < l.length(); i++) {
            char charAt = l.charAt(i);
            if (map.containsKey(charAt)) {
                l.setCharAt(i, map.get(charAt));
            }
        }
    }

}
