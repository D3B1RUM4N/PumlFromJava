package pumlFromJava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.lineSeparator;

public class PumlDiagram {

    public PumlDiagram() {}

    private String stringVal = "@startuml" + lineSeparator() +
            lineSeparator() +
            "'UML GENERE PAR CODE :)" + lineSeparator() +
            lineSeparator() +
            "skinparam style strictuml" + lineSeparator() +
            "skinparam classAttributeIconSize 0" + lineSeparator() +
            "skinparam classFontStyle Bold" + lineSeparator() +
            "hide empty members" + lineSeparator() +
            lineSeparator();

    public void ecriturePUML_DCA(String val, String name, String dir)
    {
        stringVal += val;
        stringVal += lineSeparator() +
                "@enduml" + lineSeparator();


        Path path = Paths.get(dir+"DCA_"+name);
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

    public void ecriturePUML_DCC(String val, String name, String dir)
    {
        stringVal += val;
        stringVal += lineSeparator() +
                "@enduml" + lineSeparator();


        Path path = Paths.get(dir+"DCC_"+name);
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
