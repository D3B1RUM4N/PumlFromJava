package pumlFromJava;



import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.Optional;



import static java.lang.System.lineSeparator;

public class Class {

    Element object;
    ArrayList<String> agregations = new ArrayList<String>();
    public Class(Element object){
        this.object = object;
    }


    public String toDCA(){
        String res = "\t" + object.getKind() + " " + object.getSimpleName() + " ";
        //pour les interface implementée et class herité :


        TypeElement tObject = (TypeElement) object;

        if(!tObject.getSuperclass().toString().contains("java.lang.Object")){
            //System.out.println("SuperClass " + tObject.getSuperclass());
            int lastIndex = tObject.getSuperclass().toString().lastIndexOf('.');
            String s1 = (lastIndex != -1) ? tObject.getSuperclass().toString().substring(lastIndex + 1) : tObject.getSuperclass().toString();
            res += " extends " + s1;
        }
        for(TypeMirror intrfc : tObject.getInterfaces()){
            //System.out.println("interface " + intrfc.toString());
            int lastIndex = intrfc.toString().lastIndexOf('.');
            String s1 = (lastIndex != -1) ? intrfc.toString().substring(lastIndex + 1) : intrfc.toString();
            res += " implements " + s1;
        }


        res += " {" + lineSeparator();

        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCA() + lineSeparator();
                if(field.agreg() != null){
                    agregations.add(field.agreg());
                }
            }/*else if(methode.getKind() == ElementKind.CONSTRUCTOR){

            }else{

            }*/

        }

        res += "\t}" + lineSeparator();

        for(String agreg : agregations){
            //System.out.println(object.getSimpleName() + " -- " + agreg + lineSeparator());
            res += object.getSimpleName() + " -- " + agreg + lineSeparator();
        }



        return res;
    }







    public String toDCC(){
        String res = "\t" + object.getKind() + " " + object.getSimpleName() + " ";
        //pour les interface implementée et class herité :


        TypeElement tObject = (TypeElement) object;

        if(!tObject.getSuperclass().toString().contains("java.lang.Object")){
            //System.out.println("SuperClass " + tObject.getSuperclass());
            int lastIndex = tObject.getSuperclass().toString().lastIndexOf('.');
            String s1 = (lastIndex != -1) ? tObject.getSuperclass().toString().substring(lastIndex + 1) : tObject.getSuperclass().toString();
            res += " extends " + s1;
        }
        for(TypeMirror intrfc : tObject.getInterfaces()){
            //System.out.println("interface " + intrfc.toString());
            int lastIndex = intrfc.toString().lastIndexOf('.');
            String s1 = (lastIndex != -1) ? intrfc.toString().substring(lastIndex + 1) : intrfc.toString();
            res += " implements " + s1;
        }


        res += " {" + lineSeparator();

        for(Element methode : object.getEnclosedElements()){
            //System.out.println(methode + " / " + methode.getSimpleName() + " : " + methode.getKind());

            if(methode.getKind() == ElementKind.FIELD){
                Field field = new Field(methode);
                res += "\t\t" + field.toDCC() + lineSeparator();
                if(field.agreg() != null){
                    agregations.add(field.agreg());
                }
            }else if(methode.getKind() == ElementKind.CONSTRUCTOR){
                Constructor constructor = new Constructor(methode);
                res += "\t\t" + constructor.toDCC() + lineSeparator();
            }/*else{

            }*/

        }

        res += "\t}" + lineSeparator();

        for(String agreg : agregations){
            //System.out.println(object.getSimpleName() + " -- " + agreg + lineSeparator());
            res += object.getSimpleName() + " -- " + agreg + lineSeparator();
        }



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
