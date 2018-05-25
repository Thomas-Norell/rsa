/*
Thomas Norell
 */

package math;

import java.math.BigInteger;

public class Tools {

    /*
    Extended Euclidean algorithm, returns solution to
    ax + by = gcd(a,b) in the form [gcd(a,b), x, y]
    Pseudocode from wikipedia
     */
    static int[] egcd(int a, int b) {
        int[] arr = new int[3];
        int s = 0;
        int oldS = 1;
        int t = 1;
        int oldT = 0;
        int r = b;
        int oldR = a;
        int quotient;
        int temp;
        while (r != 0) {
            quotient = oldR / r;
            temp = r;
            r = oldR - quotient * r;
            oldR = temp;
            temp = s;
            s = oldS - quotient * s;
            oldS = temp;
            temp = t;
            t = oldT - quotient * t;
            oldT = temp;

        }
        arr[0] = oldR;
        arr[1] = oldS;
        arr[2] = oldT;
        return arr;

    }

    static BigInteger[] egcd(BigInteger a, BigInteger b) {
        BigInteger[] arr = new BigInteger[3];
        BigInteger s = new BigInteger("0");
        BigInteger oldS = new BigInteger("1");
        BigInteger t = new BigInteger("1");
        BigInteger oldT = new BigInteger("1");
        BigInteger r = b;
        BigInteger oldR = a;
        BigInteger quotient;
        BigInteger temp;
        while (!r.equals(new BigInteger("0"))) {
            quotient = oldR.divide(r);
            temp = r;
            r = oldR.subtract(quotient.multiply(r));
            oldR = temp;
            temp = s;
            s = oldS.subtract(quotient.multiply(s));
            oldS = temp;
            temp = t;
            t = oldT.subtract(quotient.multiply(t));
            oldT = temp;

        }
        arr[0] = oldR;
        arr[1] = oldS;
        arr[2] = oldT;
        return arr;

    }

    //Returns a^-1 (base modulus)
    public static int modInverse(int a, int modulus) {
        int[] arr = egcd(a, modulus);
        //ax + by = gcd(a,b)->[gcd(a,b), x, y]
        if (arr[0] != 1) {
            throw new RuntimeException("Modular Inverse Does Not Exist!");
        } else {
            while (arr[1] < 0){
                arr[1] += modulus;
            }
            return arr[1] % modulus;
        }
    }
    public static BigInteger modInverse(BigInteger a, BigInteger modulus) {
        BigInteger[] arr = egcd(a, modulus);
        //ax + by = gcd(a,b)->[gcd(a,b), x, y]
        if (!arr[0].equals(new BigInteger("1"))) {
            throw new RuntimeException("Modular Inverse Does Not Exist!");
        } else {
            return arr[1].mod(modulus);
        }
    }

    public static int lcm(int x, int y){
        return x*y/egcd(x, y)[0];
    }

    public static BigInteger lcm(BigInteger x, BigInteger y){
        return (x.multiply(y)).divide(x.gcd(y));
    }

    /*
    Efficient modular exponentiation.
    Performs exponentiation in efficient route through
    binary decomposition. Original work.
     */

    //TODO: This could be rewritten to do fewer operations if performance becomes an issue
    //TODO: Performance is an issue. Necessitates rewrite at some point.
    //TODO: Write a subroutine in C
    public static BigInteger modExponent(BigInteger base, BigInteger exponent, BigInteger modulus) {
        String bits = exponent.toString(2);
        BigInteger[] powerList = new BigInteger[bits.length()];
        java.util.Arrays.fill(powerList, base);
        BigInteger result = new BigInteger("1");
        for (int i = 1; i < bits.length(); i++) {
            powerList[i] = (powerList[i - 1].multiply(powerList[i - 1])).mod(modulus);
        }
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                result = (result.multiply(powerList[bits.length() - i - 1])).mod(modulus);
            }
        }
        return result;
    }


    //Orignal implementation

    public static int modExponent(int base, int exponent, int modulus) {
        String bits = Long.toBinaryString(exponent);
        int[] powerList = new int[bits.length()];
        java.util.Arrays.fill(powerList, base);
        int result = 1;
        for (int i = 1; i < bits.length(); i++) {
            powerList[i] = (powerList[i - 1] * powerList[i - 1]) % modulus;
        }
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                result = (result * powerList[bits.length() - i - 1]) % modulus;
            }
        }

        return result;
    }

}
