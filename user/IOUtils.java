package user;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtils {
    public static List<String> read(String dir){

        List<String> content = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(dir));
            String line;
            while ((line = reader.readLine()) != null)
            {
                content.add(line);
            }
            reader.close();
        }
        catch (Exception e){
            throw new RuntimeException("Could not find file at " + dir);
        }
        return content;
    }
    public static void write(String dir, List<String> content){
        Path out = Paths.get(dir);
        try{
            Files.write(out, content, Charset.forName("UTF-8"));
        }
        catch (IOException e){
            throw new RuntimeException("Failed to setup!");
        }

    }

    public static String prompt(String question){
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.next();
    }
}
