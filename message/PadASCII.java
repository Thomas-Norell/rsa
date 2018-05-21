package message;

import math.Tools;

import java.math.BigInteger;

import java.util.Random;

//Prototypical padding scheme. Not secure.
public class PadASCII implements PadScheme {

    public PadASCII() {
    }


    @Override
    public BigInteger pad(String chunk) {
        Random random = new Random();
        String tempString = "";
        for (int i = 0; i < chunk.length(); i++) {
            String asciiChar = Integer.toString((int) chunk.charAt(i));
            while (asciiChar.length() < 3) {
                asciiChar = "0" + asciiChar;
            }
            tempString += asciiChar;
        }
        tempString = Integer.toString(random.nextInt(8) + 1) + tempString; //Appending one digit to the front in the scenario that the first ASCII digt starts with zero
        return new BigInteger(tempString);
    }

    @Override
    public String dePad(BigInteger padded) {
        String paddedStr = padded.toString();
        String dePadded = "";
        for (int i = 1; i <= paddedStr.length() - 3; i = i + 3) {
            dePadded += (char) Integer.parseInt(paddedStr.substring(i, i + 3));
        }
        return dePadded;
    }


    @Override
    public String[] doChunking(String content, PublicKeys pubKeys) {
        int chunkSize = (pubKeys.n.toString().length()) / 3 - 1; //ASCII characters can be 3 digits
        String[] chunks = new String[content.length()*3 / chunkSize + 1];
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
