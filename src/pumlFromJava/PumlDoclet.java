package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import static java.lang.System.lineSeparator;

/**
 * Doclets : https://openjdk.org/groups/compiler/processing-code.html
 *
 * Doclets provide code that can be executed by the JDK javadoc tool.
 * Although the tool is primarily designed to support the ability to generate
 * API documentation from element declarations and documentation comments,
 * it is not limited to that purpose, and can run any user-supplied doclet,
 * which can use the Language Model API and Compiler Tree API to analyze the packages,
 * classes and files specified on the command line.
 */

/**
 * A minimal doclet that just prints out the names of the
 * selected elements.
 */
public class PumlDoclet implements Doclet {
    @Override
    public void init(Locale locale, Reporter reporter) {  }

    @Override
    public String getName() {
        // For this doclet, the name of the doclet is just the
        // simple name of the class. The name may be used in
        // messages related to this doclet, such as in command-line
        // help when doclet-specific options are provided.
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        // This doclet does not support any options.
        return Collections.emptySet();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        // This doclet supports all source versions.
        // More sophisticated doclets may use a more
        // specific version, to ensure that they do not
        // encounter more recent language features that
        // they may not be able to handle.
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        // This method is called to perform the work of the doclet.
        // In this case, it just prints out the names of the
        // elements specified on the command line.
        System.out.println(this.getName());
        System.out.println(environment.getSpecifiedElements()); //nom du package
        System.out.println(environment.getIncludedElements());  //toutes les class du package

        //debut ecriture
        String ecriture = "@startuml" + lineSeparator() +
                lineSeparator() +
                "'UML GENERE PAR CODE :)" + lineSeparator() +
                lineSeparator() +
                "skinparam style strictuml" + lineSeparator() +
                "skinparam classAttributeIconSize 0" + lineSeparator() +
                "skinparam classFontStyle Bold" + lineSeparator() +
                "hide empty members" + lineSeparator() +
                lineSeparator();

        for (Element element : environment.getIncludedElements())
        {

            //System.out.println("uwu");
            ecriture += dumpElement(element) + lineSeparator();
        }

        ecriture += lineSeparator() +
                "@enduml" + lineSeparator();

        System.out.println(ecriture);

        PumlDiagram uml = new PumlDiagram();
        uml.ecriturePUML(ecriture);

        return true;
    }

    private String dumpElement(Element element)
    {
        /*System.out.print("---- ");
        System.out.println("element: " + element);
        System.out.println("kind: " + element.getKind());
        System.out.println("simpleName: " + element.getSimpleName());
        System.out.println("enclosingElement: " + element.getEnclosingElement());   //return western
        System.out.println("enclosedElement: " + element.getEnclosedElements());
        System.out.println("modifiers: " + element.getModifiers());
        System.out.println();*/


       String res = "";

        if(element.getKind() != ElementKind.PACKAGE){
            if(element.getKind() != ElementKind.MODULE){
                res += element.getKind() + " " + element.getSimpleName() + "{" + lineSeparator();
                res += "}";
            }
        }


        /*for(Element methode : element.getEnclosedElements()){
            res+= methode + lineSeparator();
        }*/


        return res;

    }
}
