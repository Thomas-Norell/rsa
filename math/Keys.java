package math;

import java.math.BigInteger;

import java.util.Random;


public class Keys {
    int numDigits;
    public Prime p;
    public Prime q;
    public BigInteger n;
    public BigInteger lambda;
    public BigInteger e = BigInteger.valueOf((int) Math.pow(2, 16) + 1);
    public BigInteger d;
    Random random = new Random();

    public Keys(int nDigits){
        numDigits = nDigits;
        //TODO: Issues with random function.
        //TODO: Implement secure random
        int digi_difference = random.nextInt(2); //No more than 4 digits between p and q
        p = new FermatPrime(nDigits/2 - digi_difference);
        q = new FermatPrime(nDigits/2 + digi_difference);
        n = p.getValue().multiply(q.getValue());
        lambda = Tools.lcm(p.getValue().subtract(new BigInteger("1")), q.getValue().subtract(new BigInteger("1")));
        d = Tools.modInverse(e, lambda);
    }

    public String toString(){
        return "Private Keys: p = " + p.toString() + ", q = " + q.toString() + ", Lambda = " +lambda +
                "\nPublic Keys: n = " + n.toString() + ", d = " + d.toString() + ", e = " + e.toString();
    }
}
