package pumlFromJava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PumlDiagram {

    public PumlDiagram() {}


    public void ecriturePUML(String val)
    {
        String stringVal = val;
        Path path = Paths.get("classUML.puml");
        try {
            //String str = "Test creation \n Hello W0rld";
            byte[] bs = stringVal.getBytes();
            Path writtenFilePath = Files.write(path, bs);
            System.out.println("Written content in file:\n"+ new String(Files.readAllBytes(writtenFilePath)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
