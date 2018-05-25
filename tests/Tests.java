package tests;
import org.junit.Test;
import user.FileUtils;
import user.ObjectWriter;
import java.util.Random;
import java.io.File;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void fullTest() throws Exception {

        String[] args = {"keygen","alice"};
        user.Main.main(args); //Generate Alice's keys
        args[1] = "bob";
        user.Main.main(args); //Generate Bob's keys

        Random r = new Random();
        char[] data = new char[100];
        for (int i = 0; i < data.length; i++){
            data[i] = (char) r.nextInt(Integer.parseInt("FF", 16));
        }

        FileUtils.writeCharArray("tests/aliceMessage.txt", data);


        String[] newArgs = {"compose","tests/aliceMessage.txt","alice.pub"};

        user.Main.main(newArgs); //Write the message to alice


        File file = new File("tests/aliceMessage.txt");
        file.delete();

        newArgs[0] = "read";
        newArgs[1] = "tests/aliceMessage.txt.rsa";
        newArgs[2] = "alice.priv";
        user.Main.main(newArgs);


        char[] decrypted = FileUtils.readFile("tests/aliceMessage.txt");

        assertArrayEquals("Your results did not match the expected results", data, decrypted);


    }
}
