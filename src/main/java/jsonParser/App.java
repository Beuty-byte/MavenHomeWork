package jsonParser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args){

        logger.info("Start Application");

        String configJsonFile = args[0];
        String fileToChange = args[1];

        JsonParser parser = new JsonParser();
        JsonObject root = null;
        try {
            root = parser.parse(new FileReader(configJsonFile)).getAsJsonObject();
            logger.info("Config file read " + root.toString());
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE,"Exception file not found ",e);
            e.printStackTrace();
        }


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
            logger.info("file rename " + filesList[i].getName());
        //mvn exec:java -Dexec.args="C:\Users\Wizzi\Desktop\forExample\Conf.json C:\Users\Wizzi\Desktop\filesForChenge\ "
        }
        logger.info("Finish application");
    }
}
