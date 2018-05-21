package message;
import math.Prime;

import java.io.Serializable;

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
}
