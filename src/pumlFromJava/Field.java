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
        res += vis() + methode.getSimpleName().toString() + " : ";

        // on recupe le Type, et on doit le nettoyer
        VariableElement variableElement = (VariableElement) methode;
        String s1;
        if(variableElement.asType().toString().contains(">")){
            String s = variableElement.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            s1 = s1.substring(0, s1.length()-1) + "[]";
            s1 = getStringPuml(s1);
            System.out.println("s1 apres brico : " + s1);
        }else{
            String s = variableElement.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            //System.out.println("s1 apres brico : " + s1);
            s1 = getStringPuml(s1);
        }

        res += s1;
        return res;
    }

    private String getStringPuml(String s1) {
        System.out.println("s1 avant switch : " + s1);
        switch (s1) {
            case "boolean":
                return "Boolean";
            case "byte":
                return "Byte";
            case "short":
                return "Short";
            case "int", "long":
                return "Integer";
            case "float", "double":
                return "Real";
            case "char":
                return "Char";
            case "void":
                return "";
        }
        System.out.println("s1 apres switch : " + s1);

        return s1;
    }

    public String getModifier(){
        Modifier vis = new Modifier(methode.getModifiers());
        return vis.getModifierString();
    }

    public String vis(){
        Modifier mod = new Modifier(methode.getModifiers());
        return getModifier();
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

        if(!methode.asType().toString().contains("java") && !methode.asType().toString().contains("int") && !methode.asType().toString().contains("bool") && !methode.asType().toString().contains("long")&& !methode.asType().toString().contains("Char") && !methode.asType().toString().contains("Int") && !methode.asType().toString().contains("Double")){
            String s = methode.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            return "\\n1\"" + s1;
        }

        System.out.println(methode.getSimpleName() + " " + methode.asType());
        if(!methode.asType().toString().contains("java") && !methode.asType().toString().contains("int") && !methode.asType().toString().contains("bool") && !methode.asType().toString().contains("long")&& !methode.asType().toString().contains("Char") && !methode.asType().toString().contains("Int") && !methode.asType().toString().contains("Double")){
            String s = methode.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            return "\\n1\"" + s1;
        }
        return null;
    }



    public String agregDCA(){

        System.out.println(methode.getSimpleName() + " " + methode.asType());
        if(!methode.asType().toString().contains("java") && !methode.asType().toString().contains("int") && !methode.asType().toString().contains("bool") && !methode.asType().toString().contains("long")&& !methode.asType().toString().contains("Char") && !methode.asType().toString().contains("Int") && !methode.asType().toString().contains("Double")&& !methode.asType().toString().contains("null")){
            String s = methode.asType().toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            System.out.println(s1);

            return s1;
        }
        return null;
    }

}
