package pumlFromJava;

import javax.lang.model.element.Element;

public class Methode {
    Element methode;

    /**
     * @pumlType utility
     * @pumlAssociation name="uses"
     */
    public Methode(Element methode) {
        this.methode = methode;
    }

    /**
     * @pumlType 1..1
     */
    public String toDCC() {
        String res = "";
        Modifier mod = new Modifier(methode.getModifiers());
        res += mod.getModifierString() + methode.getSimpleName() + "(";
        Parameters param = new Parameters(methode);
        res += param.getParameters() + ")";
        if(param.getRetour().length() > 0){
            res+= " : " + param.getRetour();
        }

        return res;
    }
}
