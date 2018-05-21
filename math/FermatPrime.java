package math;

import java.math.BigInteger;
import java.util.Random;

public class FermatPrime implements Prime {
    int numDigits;
    private BigInteger value;

    @Override
    public int numDigits(){
        return numDigits;
    }

    @Override
    public BigInteger getValue() {
        return value;
    }

    public FermatPrime(int numD) {
        numDigits = numD;
        value = randomPrime();
    }
    public FermatPrime(String numStr){ //To load in primes from a file
        numDigits = numStr.length();
        value = new BigInteger(numStr);
    }

    @Override
    public BigInteger randomPrime() {
        Random random = new Random();

        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String randomString = "";
        while (randomString.length() < numDigits) {
            randomString += numbers[random.nextInt(10)];
        }
        BigInteger randomBig = new BigInteger(randomString);

        BigInteger bound = randomBig.multiply(new BigInteger("1000"));

        for (BigInteger i = randomBig; i.compareTo(bound) < 0; i = i.add(new BigInteger("1"))) {
            if (Tools.modExponent(new BigInteger("2"), i, i).equals(new BigInteger("2"))) {
                return i;
            }
        }
        return new BigInteger("-1");
    }
    @Override
    public String toString(){
        return getValue().toString();
    }
}
