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

    public String agreg(){
        System.out.println(methode.getSimpleName() + " " + methode.asType());
        if(!methode.asType().toString().contains("java") && !methode.asType().toString().contains("int") && !methode.asType().toString().contains("bool")){
            String s = methode.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            return s1;
        }
        return null;
    }

}
