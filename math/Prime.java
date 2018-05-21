package math;

import java.math.BigInteger;

//TODO: Add implementation for more types of pseudoprimes. Fermat's Little Theorem is not great
public interface Prime {
    BigInteger getValue();

    BigInteger randomPrime();

    int numDigits();

}
