package pumlFromJava;

import java.util.spi.ToolProvider;

public class Java2Puml
{
    //commentaire

    public static void main(String[] args)
    {
        System.out.println("/////////////// 1");
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(toolProvider.name());
        System.out.println("/////2");
        //System.out.println(args);

/*
    javadoc -private -sourcepath <src> -doclet pumlFromJava.FirstDoclet -docletpath out/production/<projet>
      <package> ... <fichiers>
 */

        /* MES PARAMETTRES :
            -sourcepath
            src/
            -out
            pumlCode.puml
            -d
            ../
            -doclet
            pumlFromJava.PumlDoclet
            -docletpath
            out/production/p21_projet/
            western
         */
        toolProvider.run(System.out, System.err, args);
    }
}
