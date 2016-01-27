import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {

        //        for (int i = 0; i < 100; ++i) {
        //            for (int j = 0; j < 100; ++j) {
        //                System.out.println((i + 1) + " " + (j+ 1));
        //            }
        //        }
        //
        //        if (true) return;

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("test/BATALHA22")));
        String[] sp = r.readLine().split(" ");

        int n = Integer.parseInt(sp[0]);
        int m = Integer.parseInt(sp[1]);
        int l, c;

        char[][] b = new char[n][m];
        for (int i = 0; i < n; ++i) {
            String s = r.readLine();
            for (int j = 0; j < m; ++j) {
                b[i][j] = s.charAt(j);
            }
        }

        //        for (char[] asd : b) {
        //            System.out.println(java.util.Arrays.toString(asd));
        //        }

        int k = Integer.parseInt(r.readLine());
        for (int i = 0; i < k; ++i) {
            sp = r.readLine().split(" ");
            l = Integer.parseInt(sp[0]) - 1;
            c = Integer.parseInt(sp[1]) - 1;
            if (b[l][c] == '#') b[l][c] = '0';
        }

        //        System.out.println();
        //        for (char[] asd : b) {
        //            System.out.println(java.util.Arrays.toString(asd));
        //        }

        int h = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (b[i][j] == '0') {

                    Fila fila = new Fila();
                    fila.insere(i, j);

                    boolean f = true;
                    while (!fila.vazia()) {
                        No no = fila.retira();
                        f &= c(b, n, m, no.i, no.j, fila);
                    }
                    if (f) ++h;
                }
            }
        }

        //        System.out.println();
        //        for (char[] asd : b) {
        //            System.out.println(java.util.Arrays.toString(asd));
        //        }

        System.out.print(h);
    }

    private static boolean c(char[][] b, int n, int m, int i, int j, Fila fila) {
        boolean no = true, su = true, le = true, oe = true, zero = b[i][j] == '0', sharp = b[i][j] == '#';

        if (b[i][j] == '.' || b[i][j] == '1') return true;
        if (zero || sharp) b[i][j] = '1';

        if (i > 0) if (b[i - 1][j] != '.' && b[i - 1][j] != '1') {
            fila.insere(i - 1, j);
        }
        if (i + 1 < n) if (b[i + 1][j] != '.' && b[i + 1][j] != '1') {
            fila.insere(i + 1, j);
        }
        if (j > 0) if (b[i][j - 1] != '.' && b[i][j - 1] != '1') {
            fila.insere(i, j - 1);
        }
        if (j + 1 < m) if (b[i][j + 1] != '.' && b[i][j + 1] != '1') {
            fila.insere(i, j + 1);
        }

        return no && su && le && oe && zero && !sharp;
    }

    static class Fila {

        No ini;
        No fim;

        void insere(int i, int j) {
            if (fim == null) {
                fim = ini = new No(i, j);
            } else {
                fim.prox = new No(i, j, null);
                fim = fim.prox;
            }
        }

        No retira() {
            No ret = ini;
            ini = ini.prox;
            if (ini == null) fim = null;
            return ret;
        }

        boolean vazia() {
            return ini == null;
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            No aux = ini;
            while (aux != null) {
                b.append("[" + aux.i + " - " + aux.j + "]");
                aux = aux.prox;
            }
            return b.toString();
        }

    }

    static class No {

        int i, j;
        No prox;

        No(int i, int j) {
            this(i, j, null);
        }

        No(int i, int j, No prox) {
            this.i = i;
            this.j = j;
            this.prox = prox;
        }

    }

}
