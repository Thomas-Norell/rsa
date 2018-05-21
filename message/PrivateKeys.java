package message;
import math.FermatPrime;
import math.Prime;

import java.io.Serializable;
import java.util.List;

import java.math.BigInteger;

public class PrivateKeys implements Serializable{
    public BigInteger d;
    public Prime p;
    public Prime q;
    public BigInteger lambda;
    public BigInteger n;
    public BigInteger e;
    public PrivateKeys(math.Keys key){
        d = key.d;
        p = key.p;
        q = key.q;
        lambda = key.lambda;
        n = key.n;
        e = key.e;
    }
    public PrivateKeys(String d, String p, String q, String lambda, String n, String e){
        this.d = new BigInteger(d);
        this.p = new FermatPrime(p);
        this.q = new FermatPrime(q);
        this.lambda = new BigInteger(lambda);
        this.n = new BigInteger(n);
        this.e = new BigInteger(e);
    }
    public PrivateKeys(List<String> priv){
        this.d = new BigInteger(priv.get(1));
        this.p = new FermatPrime(priv.get(2));
        this.q = new FermatPrime(priv.get(3));
        this.lambda = new BigInteger(priv.get(4));
        this.n = new BigInteger(priv.get(5));
        this.e = new BigInteger(priv.get(6));
    }
}
