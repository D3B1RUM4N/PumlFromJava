package pumlFromJava;

import javax.lang.model.element.Element;
import java.io.FileDescriptor;

public class Field {


    Element methode;

    public Field(Element methode){
        this.methode = methode;
    }

    @Override
    public String toString(){
        return methode.getSimpleName().toString();
    }

}
