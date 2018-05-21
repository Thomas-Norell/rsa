package message;

import java.io.Serializable;
import java.math.BigInteger;

public class PublicKeys implements Serializable{
    public BigInteger n;
    public BigInteger e;
    public PublicKeys(BigInteger n, BigInteger e){
        this.n = n;
        this.e = e;
    }
    public PublicKeys(PrivateKeys keys){
        n = keys.n;
        e = keys.e;
    }
}
