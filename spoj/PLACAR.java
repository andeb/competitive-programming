import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(new File("test/PLACAR.txt")));

        int instancia = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            int num = Integer.parseInt(line);

            int menorNum = 11;
            List<String> nomes = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                String aluno = reader.readLine();
                String[] split = aluno.split(" ");

                int questoes = Integer.parseInt(split[1]);
                if (questoes < menorNum) {
                    nomes.clear();
                    nomes.add(split[0]);
                    menorNum = questoes;
                } else if (questoes == menorNum) {
                    nomes.add(split[0]);
                }
            }

            String reprovado = identifyGreaterName(nomes);

            System.out.println("Instancia " + (++instancia));
            System.out.println(reprovado);
            System.out.println();
        }

    }

    private static String identifyGreaterName(List<String> nomes) {
        String ret = null;
        for (String string : nomes) {
            if (ret == null) {
                ret = string;
                continue;
            }
            if (string.compareTo(ret) > 0) {
                ret = string;
            }
            //int menorLenght = string.length() < ret.length() ? string.length() : ret.length();
            //for (int i = 0; i < menorLenght; i++) {
            //    if (string.charAt(i) > ret.charAt(i)) {
            //        ret = string;
            //        break;
            //    } else if (string.charAt(i) == ret.charAt(i)) {
            //        continue;
            //    } else {
            //        break;
            //    }
            //}
            //ret = string;
        }
        return ret;
    }

}
