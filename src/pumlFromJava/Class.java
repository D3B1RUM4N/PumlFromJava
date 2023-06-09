package pumlFromJava;



import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;


import static java.lang.System.lineSeparator;

public class Class {

    private Element object;
    private ArrayList<String> agregations = new ArrayList<String>();
    private ArrayList<String> agregationsNoms = new ArrayList<>();

    /**
     * @pumlType constructor
     */
    public Class(Element object){
        this.object = object;
    }

    /**
     * @pumlType method
     * @pumlAssociation
     */
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
                    agregations.add(field.agregDCA());
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





    /**
     * @pumlComposition
     * @pumlAggregation
     * @pumlAssociation
     */

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
                if(field.agreg() != null){
                    agregations.add(field.agreg());
                    agregationsNoms.add((field.toDCA()));
                }else {
                    res += "\t\t" + field.toDCC() + lineSeparator();
                }
            }else if(methode.getKind() == ElementKind.CONSTRUCTOR){
                Constructor constructor = new Constructor(methode);
                res += "\t\t" + constructor.toDCC() + lineSeparator();
            }else if (methode.getKind() == ElementKind.METHOD){
                Methode meth = new Methode(methode);
                res += "\t\t" + meth.toDCC() + lineSeparator();
            }

        }

        res += "\t}" + lineSeparator();

        int i = 0;
        System.out.println("//////////////////////+++++++++++++++++++++++++++++++++++++++++++++++++++++////////////////////////////////");
        for(String agreg : agregations){
            System.out.println(object.getSimpleName() + " -- " + agreg + lineSeparator());
            res += object.getSimpleName() + " o--> " + "\"" +agregationsNoms.get(i) + agreg + lineSeparator();
            i++;
        }



        return res;
    }


    public List<String> getDependences() {
        List<String> dependences = new ArrayList<>();
        TypeElement typeElement = (TypeElement) object;


        // Parcours des éléments dépendants
        for (Element enclosedElement : typeElement.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.METHOD) {
                ExecutableElement methodElement = (ExecutableElement) enclosedElement;
                TypeMirror returnType = methodElement.getReturnType();
                //System.out.println("Dépendance : " + returnType.toString());

                //on gere la multiplicité
                if(returnType.toString().contains(">")){
                    String s1;
                    String s = returnType.toString();
                    int lastIndex = s.lastIndexOf('.');
                    s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
                    s1 = s1.substring(0, s1.length()-1);
                    if(!dependences.contains(s1)){
                        System.out.println("Dependance nett : " + s1);
                        dependences.add(s1);
                    }
                }

                if(!returnType.toString().contains("java") && !returnType.toString().contains("int") && !returnType.toString().contains("bool") && !returnType.toString().contains("void")){
                    String s = returnType.toString();
                    int lastIndex = s.lastIndexOf('.');
                    String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
                    if(!dependences.contains(s1)){
                        System.out.println("Dependance nett : " + s1);
                        dependences.add(s1);
                    }
                }
            }
        }
        return dependences;
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
