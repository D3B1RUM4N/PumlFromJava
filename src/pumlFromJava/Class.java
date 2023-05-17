package pumlFromJava;



import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.Optional;



import static java.lang.System.lineSeparator;

public class Class {

    Element object;
    public Class(Element object){
        this.object = object;
    }


    public String toDCA(){
        String res = "\t" + object.getKind() + " " + object.getSimpleName() + " ";
        //pour les interface implementée et class herité :


        TypeElement tObject = (TypeElement) object;

        if(!tObject.getSuperclass().toString().contains("java.lang.Object")){
            //System.out.println("SuperClass " + tObject.getSuperclass());
            res += " extends " + tObject.getSuperclass();
        }
        for(TypeMirror intrfc : tObject.getInterfaces()){
            //System.out.println("interface " + intrfc.toString());
            res += " implements " + intrfc.toString();
        }


        res += " {" + lineSeparator();

        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCA() + lineSeparator();
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/

        }

        res += "\t}" + lineSeparator();

        return res;
    }

}



/*if (object instanceof TypeElement) {

            TypeElement typeElement = (TypeElement) object;

            // Obtenez la classe parente
            TypeMirror superClass = typeElement.getSuperclass();
            if (!superClass.toString().equals("java.lang.Object")) {
                TypeElement superTypeElement = (TypeElement) ((DeclaredType) superClass).asElement();
                System.out.println("Classe parente : " + superTypeElement.getQualifiedName());
            }


            // Obtenez les interfaces implémentées
            for (TypeMirror interfaceType : typeElement.getInterfaces()) {
                System.out.println(object.getKind() + " " + object.getSimpleName() + "implements " + interfaceType.toString());
                res += "implements " + interfaceType.toString();
            }
        }*/
