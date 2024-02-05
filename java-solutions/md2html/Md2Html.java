package md2html;


import java.io.*;
import java.util.List;

import static md2html.Parser.parser;


public class Md2Html {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]), "utf-8"
                    )
            );
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]), "utf-8"
                    )
            );
            try {
                List<HtmlText> ht = parser(in);
                StringBuilder sb = new StringBuilder();
                for (HtmlText element : ht) {
                    element.toHtml(sb);
                }
                try {
                    out.write(sb.toString());
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}

