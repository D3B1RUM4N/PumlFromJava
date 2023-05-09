package pumlFromJava;

import jdk.internal.icu.text.UnicodeSet;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.*;

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
    private String fileName = "classUML.puml";
    private String dir = "./";
    @Override
    public Set<? extends Option> getSupportedOptions() {

        HashSet<Option> options = new HashSet<Option>();
        options.add(new Option() {

            @Override
            public int getArgumentCount() {
                return 1;
            }

            @Override
            public String getDescription() {
                return "choose the .puml name";
            }

            @Override
            public Kind getKind() {
                return Kind.EXTENDED;
            }

            @Override
            public List<String> getNames() {
                List<String> names = new ArrayList<>();
                names.add("-out");
                return names;
            }

            @Override
            public String getParameters() {
                return "name";
            }

            @Override
            public boolean process(String option, List<String> arguments) {
                if(arguments.get(0).isEmpty()){
                    fileName = "classUML";
                    System.out.println("askip je suis empty " + arguments.get(0));
                }
                else if(!arguments.get(0).contains(".puml")) fileName = arguments.get(0) + ".puml";
                else fileName = arguments.get(0);

                return true;
            }
        });
        options.add(new Option() {
            @Override
            public int getArgumentCount() {
                return 1;
            }

            @Override
            public String getDescription() {
                return "for the .puml Directory";
            }

            @Override
            public Kind getKind() {
                return Kind.STANDARD;
            }

            @Override
            public List<String> getNames() {
                List<String> names = new ArrayList<String>();
                names.add("-d");
                names.add("-directory");
                return names;
            }

            @Override
            public String getParameters() {
                return "directory";
            }

            @Override
            public boolean process(String option, List<String> arguments) {

                if(!arguments.get(0).endsWith("/")) dir = arguments.get(0) + "/";
                else dir = arguments.get(0);

                return true;
            }
        });


        return options;
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
        System.out.println("///////////////:3+");
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
        if(havePackage) ecriture += "}";

        ecriture += lineSeparator() +
                "@enduml" + lineSeparator();

        //System.out.println(ecriture);


        PumlDiagram uml = new PumlDiagram();
        uml.ecriturePUML(ecriture, fileName, dir);

        System.out.println(dir+fileName);

        return true;
    }

    private boolean newPackage = false;
    private boolean havePackage = false;
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
        if(element.getKind() == ElementKind.PACKAGE){
            havePackage = true;
            if(newPackage) {
                res+= lineSeparator() + "}";
                newPackage = false;
            }
            res += lineSeparator()+
                    element.getKind() + " " + element.getSimpleName() + "{" + lineSeparator();
            newPackage = true;
        }
        else{
            if(element.getKind() != ElementKind.MODULE){
                res += "\t" + element.getKind() + " " + element.getSimpleName() + "{" + lineSeparator();
                for(Element methode : element.getEnclosedElements()){
                    if(methode.getKind() == ElementKind.FIELD){
                        res += "\t\t";
                        res += methode + lineSeparator();
                    }
                }
                res += "\t" + "}" + lineSeparator();
            }
        }


        /*for(Element methode : element.getEnclosedElements()){
            System.out.println("methode " + methode + methode.getKind() + lineSeparator());
        }*/


        return res;

    }
}
