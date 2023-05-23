package pumlFromJava;

import javax.lang.model.element.Element;

public class Constructor {

    Element methode;
    public Constructor(Element methode) {
        this.methode = methode;
    }

    public String toDCC() {
        String res = "";
        Modifier mod = new Modifier(methode.getModifiers());
        res += mod.getModifierString() + "<<create>>" + methode.getEnclosingElement().getSimpleName() + "(";
        Parameters param = new Parameters(methode.asType());
        res += param.getParameters();

        return res;
    }
}
