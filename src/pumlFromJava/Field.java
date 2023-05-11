package pumlFromJava;

import javax.lang.model.element.Element;
import java.io.FileDescriptor;

public class Field {


    Element methode;

    public Field(Element methode){
        this.methode = methode;
    }

    public String toDCA(){
        return methode.getSimpleName().toString();
    }

}
