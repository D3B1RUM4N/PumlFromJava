package pumlFromJava;

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
    private boolean doDCC = true;
    private boolean doDCA = true;
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

        options.add(new Option() {

            @Override
            public int getArgumentCount() {
                return 1;
            }

            @Override
            public String getDescription() {
                return "choose the if you want a DCC (c) or a DCA (a). Nothing if both";
            }

            @Override
            public Kind getKind() {
                return Kind.EXTENDED;
            }

            @Override
            public List<String> getNames() {
                List<String> names = new ArrayList<>();
                names.add("-precise");
                return names;
            }

            @Override
            public String getParameters() {
                return "type";
            }

            @Override
            public boolean process(String option, List<String> arguments) {
                if(!arguments.get(0).isEmpty()){
                    if(arguments.get(0) == "a"){
                        doDCC = false;
                        doDCA = true;
                    }else if(arguments.get(0) == "c"){
                        doDCC = true;
                        doDCA = false;
                    }
                }
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




        if(doDCA) {
            String ecritureDCA = "";
            System.out.println("LE DCAAAA///////////////////////////////////////////////////////////////////////");
            for (Element element : environment.getIncludedElements()) {
                if (element.getKind() == ElementKind.PACKAGE) {
                    Package pack = new Package(element);
                    ecritureDCA += pack.toDCA();
                }
            }
            PumlDiagram uml = new PumlDiagram();
            uml.ecriturePUML_DCA(ecritureDCA, fileName, dir);
        }
        if(doDCC){
            String ecritureDCC = "";
            System.out.println("LE DCCCCCCC///////////////////////////////////////////////////////////////////////");
            for (Element element : environment.getIncludedElements()) {
                if (element.getKind() == ElementKind.PACKAGE) {
                    Package pack = new Package(element);
                    ecritureDCC += pack.toDCC();
                }
            }
            PumlDiagram uml = new PumlDiagram();
            uml.ecriturePUML_DCC(ecritureDCC, fileName, dir);
        }
        //System.out.println(ecriture);



        System.out.println(dir+fileName);

        return true;
    }

}
