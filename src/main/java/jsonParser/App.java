package jsonParser;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.*;
import java.util.Objects;

public class App {
    public static void main(String[] args) throws IOException {


        String configJsonFile = args[0];
        String fileToChange = args[1];

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(new FileReader(configJsonFile)).getAsJsonObject();
        String suffix = root.get("suffix").toString();
        suffix = suffix.replaceAll("\"","");
        System.out.println("suffix = " + suffix);


        File[] filesList;
        File filesPath = new File(fileToChange);

        filesList = filesPath.listFiles();

        for (int i = 0; i < Objects.requireNonNull(filesList).length; i++) {
            String buf = filesList[i].getName();
            System.out.print(buf + " -> ");

            String[] fileName = buf.split("\\.");
            buf = fileName[0]+suffix+"."+fileName[1];
            System.out.println(buf);



            filesList[i].renameTo(new File(fileToChange + buf));

        //mvn exec:java -Dexec.args="C:\Users\Wizzi\Desktop\forExample\Conf.json C:\Users\Wizzi\Desktop\filesForChenge\ "
        }

    }
}
