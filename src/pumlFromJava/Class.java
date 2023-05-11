package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.Optional;

import static java.lang.System.lineSeparator;

public class Class {

    Element object;
    public Class(Element object){
        this.object = object;
    }

    @Override
    public String toString(){
        String res = "\t" + object.getKind() + " " + object.getSimpleName() + "{" + lineSeparator();


        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toString() + lineSeparator();
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/
        }

        res += "\t}" + lineSeparator();

        return res;
    }

}
