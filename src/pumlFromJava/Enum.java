package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.Optional;

import static java.lang.System.lineSeparator;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;


public class Enum {
    Element object;
    /**
     * @pumlType enum
     */
    public Enum(Element object){
        this.object = object;
    }

    /**
     * @pumlType 1..1
     */
    public String toDCA(){
        String res ="\t" + object.getKind()+ " " + object.getSimpleName() + "<<enum>>";

        TypeElement tObject = (TypeElement) object;

        /*if(!tObject.getSuperclass().toString().contains("java.lang.Object")){
            //System.out.println("SuperClass " + tObject.getSuperclass());
            res += " extends " + tObject.getSuperclass();
        }*/
        for(TypeMirror intrfc : tObject.getInterfaces()){
            //System.out.println("interface " + intrfc.toString());
            res += " implements " + intrfc.toString();
        }







        res += "{" + lineSeparator();

        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCA() + lineSeparator();
            }else if(methode.getKind() == ElementKind.CLASS) {
                System.out.println(object.getKind() + " " + object.getSimpleName() + " " + methode.getSimpleName());
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/

        }

        return res + lineSeparator() +
                "\t}";
    }


    /**
     * @pumlComposition
     * @pumlAggregation
     * @pumlAssociation
     */
    public String toDCC(){
        String res ="\t" + object.getKind()+ " " + object.getSimpleName() + "<<enum>>";

        TypeElement tObject = (TypeElement) object;

        /*if(!tObject.getSuperclass().toString().contains("java.lang.Object")){
            //System.out.println("SuperClass " + tObject.getSuperclass());
            res += " extends " + tObject.getSuperclass();
        }*/
        for(TypeMirror intrfc : tObject.getInterfaces()){
            //System.out.println("interface " + intrfc.toString());
            res += " implements " + intrfc.toString();
        }







        res += "{" + lineSeparator();

        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCC() + lineSeparator();
            }else if(methode.getKind() == ElementKind.CLASS) {
                System.out.println(object.getKind() + " " + object.getSimpleName() + " " + methode.getSimpleName());
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/

        }

        return res + lineSeparator() +
                "\t}";
    }
}
