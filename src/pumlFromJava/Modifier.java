package pumlFromJava;

import java.util.Set;

public class Modifier {

    private Set<javax.lang.model.element.Modifier> modifiers;
    public Modifier(Set<javax.lang.model.element.Modifier> modifiers){
        this.modifiers = modifiers;
    }

    public String getModifierString(){
        String res = "";

        for(javax.lang.model.element.Modifier mods : modifiers){
            if(mods == javax.lang.model.element.Modifier.PRIVATE){
                res += "-";
            }else if(mods == javax.lang.model.element.Modifier.PUBLIC){
                res += "+";
            }
            if(mods == javax.lang.model.element.Modifier.STATIC){
                res += "{static}";
            }if(mods == javax.lang.model.element.Modifier.ABSTRACT){
                res+= "{abstract}";
            }if(mods == javax.lang.model.element.Modifier.PROTECTED){
                res += "#";
            }
        }

        return res;
    }
}
