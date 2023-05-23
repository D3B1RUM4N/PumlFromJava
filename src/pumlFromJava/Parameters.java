package pumlFromJava;

import javax.lang.model.type.TypeMirror;
import java.io.FileDescriptor;

public class Parameters {

    private TypeMirror asType;
    public Parameters(TypeMirror asType) {
        this.asType = asType;
    }

    public String getParameters() {
        String res = "";
        //utilise un split a chaque virgule, mais avant remplace la '(' par ''. par la suite tente un truc avec la parenthese fermante
        System.out.println(asType);
        String[] params = asType.toString().replace("(", "").split(",");
        for(String param : params){
            String s = asType.toString();
            int lastIndex = s.lastIndexOf('.');
            String s1 = (lastIndex != -1) ? s.substring(lastIndex + 1) : s;
            System.out.println(s1);
        }
        return res;
    }
}
