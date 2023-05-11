package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import static java.lang.System.lineSeparator;

public class Package {
    private Element pack;
    public Package(Element pack){
        this.pack = pack;
    }

    @Override
    public String toString(){
        String res = pack.getKind() + " "  + pack.getSimpleName() + "{" + lineSeparator();

        for(Element object : pack.getEnclosedElements()){
            //System.out.println(object + " / " + object.getSimpleName() + " : " + object.getKind());
            if(object.getKind() == ElementKind.CLASS){
                Class objectClass = new Class(object);
                res += objectClass.toString() + lineSeparator() +
                        lineSeparator();
            } /*else if (object.getKind() == ElementKind.INTERFACE) {

            } else if (object.getKind() == ElementKind.ENUM) {

            } else {            //pour les ENUM

            }*/
        }
        res += lineSeparator() +
                "}";

        return res ;

    }
}
