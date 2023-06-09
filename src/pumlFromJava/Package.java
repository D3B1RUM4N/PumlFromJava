package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import static java.lang.System.lineSeparator;

public class Package {
    private Element pack;
    /**
     * @pumlType package
     */
    public Package(Element pack){
        this.pack = pack;
    }


    /**
     * @pumlType method
     * @pumlAssociation Class
     * @pumlAssociation Interface
     * @pumlAssociation Enum
     */
    public String toDCA(){
        String res = pack.getKind() + " "  + pack.getSimpleName() + "{" + lineSeparator();


        for(Element object : pack.getEnclosedElements()){
            //System.out.println(object + " / " + object.getSimpleName() + " : " + object.getKind());
            if(object.getKind() == ElementKind.CLASS){
                Class objectClass = new Class(object);
                res += objectClass.toDCA() + lineSeparator() +
                        lineSeparator();
            } else if (object.getKind() == ElementKind.INTERFACE) {
                Interface objectInterface = new Interface(object);
                res += objectInterface.toDCA() +lineSeparator() +
                        lineSeparator();
            } else if (object.getKind() == ElementKind.ENUM) {
                Enum objectEnum = new Enum(object);
                res += objectEnum.toDCA() + lineSeparator() +
                        lineSeparator();
            }
        }
        res += lineSeparator() +
                "}";

        return res ;

    }





    /**
     * @pumlType method
     * @pumlAssociation Class
     * @pumlAssociation Interface
     * @pumlAssociation Enum
     * @pumlAggregation
     * @pumlComposition
     */
    public String toDCC(){
        String res = pack.getKind() + " "  + pack.getSimpleName() + "{" + lineSeparator();


        for(Element object : pack.getEnclosedElements()){
            //System.out.println(object + " / " + object.getSimpleName() + " : " + object.getKind());
            if(object.getKind() == ElementKind.CLASS){
                Class objectClass = new Class(object);
                res += objectClass.toDCC() + lineSeparator() +
                        lineSeparator();
                //les dependances
                for(String dep : objectClass.getDependences()){
                    res+= object.getSimpleName() + " ..> " + dep + " : \"<<use>>\"" + lineSeparator();
                }
                res+= lineSeparator();
            } else if (object.getKind() == ElementKind.INTERFACE) {
                Interface objectInterface = new Interface(object);
                res += objectInterface.toDCC() +lineSeparator() +
                        lineSeparator();
            } else if (object.getKind() == ElementKind.ENUM) {
                Enum objectEnum = new Enum(object);
                res += objectEnum.toDCC() + lineSeparator() +
                        lineSeparator();
            }
        }
        res += lineSeparator() +
                "}";

        return res ;

    }
}
