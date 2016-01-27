import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

//        while (reader.ready()) {
            String l = reader.readLine();
//            if ("0".equals(l)) {
//                break;
//            }

            boolean isPrime = isPrime(Integer.parseInt(l));

            writer.append(isPrime ? "sim\n" : "nao\n");
//        }

        writer.flush();
    }

    private static boolean isPrime(int NumberToFactor) {
        int NbrFactors;
        int AbsNumberToFactor = Math.abs(NumberToFactor);
        if (AbsNumberToFactor <= 3) {
            NbrFactors = 0;
        } else {
            if (AbsNumberToFactor % 2 == 0) {
                NbrFactors = 1;
            } else {
                NbrFactors = 0;
                for (int Div = 3; Div * Div <= AbsNumberToFactor; Div += 2) {
                    if (AbsNumberToFactor % Div == 0) {
                        NbrFactors = 1;
                        break;
                    }
                }
            }
        }
        if (AbsNumberToFactor == 0 || AbsNumberToFactor == 1) {
            return true;
        } else if (NbrFactors == 0) {
            return true;
        }
        return false;
    }

    //private static boolean isPrime(BigInteger NumberToFactor) {
    //    int NbrFactors;
    //    BigInteger AbsNumberToFactor = NumberToFactor.abs();
    //    if (AbsNumberToFactor.compareTo(BigInt3) <= 0) {
    //        NbrFactors = 0;
    //    } else if (BigInt3.modPow(AbsNumberToFactor.subtract(BigInteger.ONE), AbsNumberToFactor).equals(BigInteger.ONE)) {
    //        long modulus = AbsNumberToFactor.longValue();
    //        if (modulus % 2 == 0) {
    //            NbrFactors = 1;
    //        } else {
    //            NbrFactors = 0;
    //            for (long Div = 3; Div * Div <= modulus; Div += 2) {
    //                if (modulus % Div == 0) {
    //                    NbrFactors = 1;
    //                    break;
    //                }
    //            }
    //        }
    //    } else {
    //        NbrFactors = 1;
    //    }
    //    if (AbsNumberToFactor.signum() == 0) {
    //        return true;
    //    }
    //    if (AbsNumberToFactor.compareTo(BigInteger.ONE) == 0) {
    //        return true;
    //    } else if (NbrFactors == 0) {
    //        return true;
    //    }
    //    return false;

}
