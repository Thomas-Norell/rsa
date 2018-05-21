package message;

import math.Tools;

import java.math.BigInteger;

//TODO: Implement more padding schemes
//TODO: Support for all unicode characters to allow for encoding of any media (New padding mathod)
//TODO: File should be written in whatever encoding format to keep file size down.
public interface PadScheme {
    BigInteger pad(String chunk);

    String dePad(BigInteger padded);

    String[] doChunking(String content, PublicKeys pubKeys);


    default BigInteger encrypt(String chunk, PublicKeys pubKeys) {
        BigInteger paddedPlain = pad(chunk);
        return Tools.modExponent(paddedPlain, pubKeys.e, pubKeys.n);
    }

    default String decrypt(BigInteger chunk, PrivateKeys privKeys) {
        BigInteger decrypted = Tools.modExponent(chunk, privKeys.d, privKeys.n);
        return dePad(decrypted);
    }
}
