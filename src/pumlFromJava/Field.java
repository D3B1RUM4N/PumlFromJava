package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.io.FileDescriptor;

public class Field {


    Element methode;


    public Field(Element methode){
        this.methode = methode;
    }

    public String toDCA(){
        return methode.getSimpleName().toString();
    }


    public String toDCC(){
        String res = "";
        Modifier mod = new Modifier(methode.getModifiers());
        res += getModifier() + methode.getSimpleName().toString() + " : ";

        // on recupe le Type, et on doit le nettoyer
        VariableElement variableElement = (VariableElement) methode;
        String s1;
        if(variableElement.asType().toString().contains(">")){
            String s = variableElement.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            s1 = s1.substring(0, s1.length()-1) + "[]";
            System.out.println("s1 apres brico : " + s1);
        }else{
            String s = variableElement.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            //System.out.println("s1 apres brico : " + s1);
        }
        res += s1;
        return res;
    }

    public String getModifier(){
        Modifier vis = new Modifier(methode.getModifiers());
        return vis.getModifierString();
    }



    public String agreg(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEGggggggggggggggg");
        VariableElement variableElement = (VariableElement) methode;

        //on gere la multiplicité
        if(variableElement.asType().toString().contains(">")){
            String s1;
            String s = variableElement.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            s1 =" \\n0..*\"" + s1.substring(0, s1.length()-1);
            //System.out.println("s1 apres brico : " + s1);
            return s1;
        }

        if(!methode.asType().toString().contains("java") && !methode.asType().toString().contains("int") && !methode.asType().toString().contains("bool")){
            String s = methode.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            return "\"" + s1;
        }

        System.out.println(methode.getSimpleName() + " " + methode.asType());
        if(!methode.asType().toString().contains("java") && !methode.asType().toString().contains("int") && !methode.asType().toString().contains("bool")){
            String s = methode.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            return s1;
        }
        return null;
    }



    public String agregDCA(){

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
