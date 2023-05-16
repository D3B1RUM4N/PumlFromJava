package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.Optional;

import static java.lang.System.lineSeparator;

import javax.lang.model.element.Element;


public class Enum {
    Element object;
    public Enum(Element object){
        this.object = object;
    }

    public String toDCA(){
        String res ="\t" + object.getKind()+ " " + object.getSimpleName() + "<<enum>>" + "{" + lineSeparator();

        for(Element methode : object.getEnclosedElements()){
            System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCA() + lineSeparator();
            }else if(methode.getKind() == ElementKind.CLASS) {
                System.out.println(object.getKind() + " " + object.getSimpleName() + " " + methode.getSimpleName());
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/

        }

        return res;
    }
}
