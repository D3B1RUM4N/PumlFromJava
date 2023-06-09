package pumlFromJava;

import javax.lang.model.element.*;
import java.util.List;
import java.util.stream.Collectors;

public class Parameters {

    private Element element;
    /**
     * @pumlType constructor
     */
    public Parameters(Element element) {
        this.element = element;
    }

    /**
     * @pumlType method
     */

    public String getParameters() {
        String res = "";
        //trouve une solution STPPPPP

        ExecutableElement executableElement = (ExecutableElement) element;

        //System.out.println("les type ? : " + executableElement.getParameters());

        String parameters = executableElement.getParameters().stream()
                .map(parameter -> parameter.getSimpleName() + " : " + parameter.asType().toString())
                .collect(Collectors.joining(", "));
        //System.out.println("test : " + parameters);

        // j'ai du aller chez Brico Depot pour se chantier :
        if(parameters.length() > 0) {
            // Diviser la chaîne en paramètres individuels
            String[] lsParam = parameters.split(", ");

            // Transformation de chaque paramètre
            for (int i = 0; i < lsParam.length; i++) {
                String param = lsParam[i];

                // Diviser chaque paramètre en nom et type
                String[] parties = param.split(" : ");

                // Extraire le nom du paramètre
                String nomParam = parties[0].trim();

                // Extraire le type du paramètre
                String typeParam = parties[1].trim();

                // Extraire le nom simple du type
                String typeParamNettoye = typeParam.substring(typeParam.lastIndexOf('.') + 1);
                // On verifie si c'est une Array
                if(typeParamNettoye.contains(">")){
                    // et on remplace ce caractere par les []
                    typeParamNettoye = typeParamNettoye.substring(0, typeParamNettoye.length()-1) + "[]";
                }
                System.out.print("///////////// : " + typeParamNettoye);
                switch (typeParamNettoye) {
                    case "boolean":
                        typeParamNettoye = "Boolean";
                    case "byte":
                        typeParamNettoye = "Byte";
                    case "short":
                        typeParamNettoye = "Short";
                    case "int":
                        typeParamNettoye = "Integer";
                    case "long":
                        typeParamNettoye = "Integer";
                    case "float":
                        typeParamNettoye = "Real";
                    case "double":
                        typeParamNettoye = "Real";
                    default:
                        typeParamNettoye = typeParamNettoye;
                }
                System.out.println("\t///////////// : " + typeParamNettoye);


                // Former le nouveau paramètre avec le nom et le type simplifiés
                String newParam = nomParam + " : " + typeParamNettoye;

                // Remplacer l'ancien paramètre par le nouveau paramètre dans le tableau
                //lsParam[i] = newParam;
                lsParam[i] = newParam;
            }

            // Joindre les paramètres avec une virgule et un espace
            res += String.join(", ", lsParam);


            //System.out.println("test raffiné : " + res);
        }




        return res;
    }


    /**** Cimetiere de test ****/

            /*int i = 0;
        for (VariableElement val : executableElement.getParameters()){
            System.out.println("avant brico : " + val);
            String s1;
            if(val.toString().contains(">")){
                String s = val.toString();
                int lastIndex = s.lastIndexOf('.');
                s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
                s1 = s1.substring(0, s1.length()-1) ;//+ " : " + typeParam.get(i).toString() + "[]";
            }else{
                String s = val.toString();
                int lastIndex = s.lastIndexOf('.');
                s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
                //s1 += " : " + typeParam.get(i).toString();
            }
            System.out.println("s1 : " + s1);
            res += s1 + " ";
        }
        //System.out.println("param : " + executableElement.getParameters() + " // return : " + executableElement.getReturnType());*/



    /*//utilise un split a chaque virgule, mais avant remplace la '(' par ''. par la suite tente un truc avec la parenthese fermante
        System.out.println(asType);
        String input = asType.toString();
        // Extraction des paramètres entre parenthèses
        String parameters = input.substring(input.indexOf('(') + 1, input.indexOf(')'));
        String[] paramArray = parameters.split(",");

        for(String param : paramArray){
            String s = param;
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            res += s1 + " ";
            System.out.println("param : " + s1);
        }

        // Extraction du retour après la parenthèse
        this.retour = input.substring(input.indexOf(')') + 1).trim();
        System.out.println("return : " + retour);*/



    /*String[] params = asType.toString().replace("(", "").split(",");
        for(String param : params){
            String s = param;
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            System.out.println("LE param : " + param + " param[0] : " + param.split("")[0]+".");

            if(param.split("")[0] == ")")
            {
                this.retour = s1;
                System.out.println("retour : " + s1);
            }else {
                if (s1.contains(")")) {
                    String[] retour = s1.split("\\)");
                    res += retour[0];
                    System.out.println("param : " + retour[0]);
                    this.retour = retour[1];
                    System.out.println("retour : " + retour[1]);
                } else {
                    System.out.println("param : " + s1);
                    res += s1 + " ";
                }
            }
        }*/





    public String getRetour(){
        ExecutableElement executableElement = (ExecutableElement) this.element;
        /*DeclaredType declaredType = (DeclaredType) executableElement.getReturnType();
        System.out.println(element.asType());
        System.out.println(declaredType.getTypeArguments());*/

        //System.out.println(executableElement.getReturnType());
        String s1;
        if(executableElement.getReturnType().toString().contains(">")){
            String s = executableElement.getReturnType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            s1 = s1.substring(0, s1.length()-1) + "[]";
            System.out.println("s1 apres brico : " + s1);
        }else{
            String s = executableElement.getReturnType().toString();
            int lastIndex = s.lastIndexOf('.');
            s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            //System.out.println("s1 apres brico : " + s1);
        }
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
            default:
                return s1;
        }
    }
}
