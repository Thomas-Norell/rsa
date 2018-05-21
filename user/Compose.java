package user;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import message.*;

public class Compose {
    public static void main(String[] args){
        String recipient = IOUtils.prompt("Enter the username of recipient: ");
        String fileName = IOUtils.prompt("Enter the filename to encrypt: ");
        List<String> content = IOUtils.read("outbox/plaintext/" + fileName);
        List<String> contact = IOUtils.read("contacts/" + recipient + "_contact.txt");
        for (int i = 0; i < content.size(); i++){
            content.set(i, content.get(i) + '\n'); //Append newline to everything;
        }
        PublicKeys contactKeys = new PublicKeys(new BigInteger(contact.get(1)), new BigInteger(contact.get(2)));
        List<String> encryptedContent = encrypt(content, contactKeys, new PadUnicode8()); //TODO: Allow for more padding schemes
        IOUtils.write("outbox/encrypted/" + fileName, encryptedContent);
    }
    public static List<String> encrypt(List<String> message, PublicKeys pubKeys, PadScheme scheme){
        List<String> chunks = new ArrayList<>();
        for (int i = 0; i < message.size(); i++){
            String[] chunked = scheme.doChunking(message.get(i), pubKeys);
            for (int j = 0; j < chunked.length; j++){
                chunks.add(scheme.encrypt(chunked[j], pubKeys).toString());

            }
        }
        return chunks;
    }
}
