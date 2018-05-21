package user;
import message.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Read {
    public static void main(String[] args){
        String fileName = IOUtils.prompt("Enter filename: ");
        List<String> content = IOUtils.read("inbox/encrypted/" + fileName);
        PrivateKeys privKeys = new PrivateKeys(IOUtils.read("private.txt"));
        String decryptedContent = decrypt(content, privKeys, new PadUnicode8());
        List <String> contentToWrite = new ArrayList<>();
        String tempString = "";
        for (int i = 0; i < decryptedContent.length(); i++){
            if (decryptedContent.charAt(i) == '\n'){
                contentToWrite.add(tempString);
                tempString = "";
            }
            else {
                tempString += decryptedContent.charAt(i);
            }
        }
        IOUtils.write("inbox/plaintext/" + fileName, contentToWrite);

    }


    public static String decrypt(List<String> message, PrivateKeys privKeys, PadScheme pScheme){
        //TODO: Defaulting to ASCII padding. Need to add capability to switch padding schemes.
        String plaintext = "";
        String[] chunks = new String[message.size()];

        for (int i = 0; i < message.size() && message.get(i) != null; i++){
            chunks[i] = message.get(i);
            plaintext += pScheme.decrypt(new BigInteger(chunks[i]), privKeys);
        }
        return plaintext;
    }
}
