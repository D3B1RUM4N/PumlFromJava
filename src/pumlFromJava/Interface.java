package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.Optional;

import static java.lang.System.lineSeparator;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;


public class Interface {
    Element object;
    public Interface(Element object){
        this.object = object;
    }

    public String toDCA(){
        String res ="\t" + object.getKind()+ " " + object.getSimpleName()+ " <<interface>> ";

        //pour les implementation et extends
        TypeElement tObject = (TypeElement) object;

        for(TypeMirror intrfc : tObject.getInterfaces()){
            //System.out.println("interface " + intrfc.toString());
            int lastIndex = intrfc.toString().lastIndexOf('.');
            String s1 = (lastIndex != -1) ? intrfc.toString().substring(lastIndex + 1) : intrfc.toString();
            res += " implements " + s1;
        }
        res+=  "{" + lineSeparator();


        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCA() + lineSeparator();
            }else if(methode.getKind() == ElementKind.CLASS) {
                //System.out.println(object.getKind() + " " + object.getSimpleName() + " " + methode.getSimpleName());
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/

        }

        return res + lineSeparator() +
                "\t}";
    }


}
