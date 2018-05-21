package message;
import java.nio.charset.Charset;

import java.math.BigInteger;

import java.util.Random;

//Prototypical padding scheme. Not secure.
//Should be able to support media
public class PadUnicode16 implements PadScheme {

    public PadUnicode16() {
    }


    @Override
    public BigInteger pad(String chunk){
        Random random = new Random();
        byte[] unicodeBytes = chunk.getBytes(Charset.forName("UTF-16"));
        String tempString = "";
        for (byte i: unicodeBytes){
            String thisByte = Integer.toString(i);
            while (thisByte.length() < 5) {
                thisByte = "0" + thisByte;
            }
            tempString += thisByte;
        }
        return new BigInteger(Integer.toString(random.nextInt(9)) + tempString);
    }

    @Override
    public String dePad(BigInteger padded) {
        String paddedStr = padded.toString();
        String dePadded = "";
        for (int i = 1; i <= paddedStr.length() - 5; i = i + 5) {
            byte[] uniByte = {(byte) Integer.parseInt(paddedStr.substring(i, i + 5))};
            dePadded += new String(uniByte, Charset.forName("UTF-16"));
        }
        return dePadded;
    }


    @Override
    public String[] doChunking(String content, PublicKeys pubKeys) {
        int chunkSize = (pubKeys.n.toString().length()) / 5 - 1; //ASCII characters can be 3 digits
        String[] chunks = new String[content.length()*5 / chunkSize + 1];
        String chunkString = "";
        int chunkPos = 0;
        for (int i = 0; i < content.length(); i++) {
            chunkString += content.charAt(i);
            if (chunkString.length()*3 >= chunkSize || i == content.length() - 1) {
                chunks[chunkPos] = chunkString; //Just loads plaintext, no encryption
                chunkPos++;
                chunkString = "";
            }
        }
        if (chunks[chunks.length - 1] == null) {
            String[] correctChunks = new String[chunks.length - 1];
            System.arraycopy(chunks, 0, correctChunks, 0, correctChunks.length);
            return correctChunks;
        }
        return chunks;
    }
}
