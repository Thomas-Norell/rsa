package user;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import math.Keys;
import java.nio.charset.Charset;
import java.util.List;
public class Setup {
    public static  void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.next();

        Path userPrivate = Paths.get("private.txt");
        Path userContact = Paths.get(username + "_contact.txt");

        //Keygen happens now

        Keys usersKeys = new Keys(200); //Arbitrary choice of 200-digit primes

        String[] tempLines = new String[7]; //Fields for username and private keys
        tempLines[0] = username;
        tempLines[1] = usersKeys.d.toString();
        tempLines[2] = usersKeys.p.toString();
        tempLines[3] = usersKeys.q.toString();
        tempLines[4] = usersKeys.lambda.toString();
        tempLines[5] = usersKeys.n.toString();
        tempLines[6] = usersKeys.e.toString();
        List<String> lines = Arrays.asList(tempLines);

        tempLines = new String[3];
        tempLines[0] = username;
        tempLines[1] = usersKeys.n.toString();
        tempLines[2] = usersKeys.e.toString();
        List<String> newLines = Arrays.asList(tempLines);

        try{
            Files.write(userPrivate, lines, Charset.forName("UTF-8"));
            Files.write(userContact, newLines, Charset.forName("UTF-8"));
        }
        catch (IOException e){
            throw new RuntimeException("Failed to setup!");
        }
    }
}
