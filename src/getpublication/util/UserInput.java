package getpublication.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
    public static String getInput(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try {
            string = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("error to get user input");
            return "";
        }
        
        return string;
    }
}
