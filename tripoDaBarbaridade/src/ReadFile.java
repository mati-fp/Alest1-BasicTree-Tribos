import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class ReadFile {
    
    private Tree basic;

    public ReadFile(){
        basic = new Tree();
    }
    
    public void loadProgram(String narq) {
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir+"\\"+narq;  //windows 
        Path path2 = Paths.get(nameComplete);
        Boolean semPaizao = true;
        int terrasPapaizao = 0;
        try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset()))){
              
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                if (isInteger(tokens[0])){
                    terrasPapaizao = Integer.parseInt(tokens[0]);
                }
                else {
                    if( semPaizao ){
                        basic.add(tokens[0], tokens[0], terrasPapaizao);
                        basic.add(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                        semPaizao = false;
                    }
                    else{
                        basic.add(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                    }
                }
                for(int i = 0; i < tokens.length; i++){
                    System.out.print(tokens[i]);
                    System.out.print(",");
                }
                System.out.println();
            }

        }catch (IOException x){
               System.err.format("Erro de E/S: %s%n", x);
        }

        
    }

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public void passaHeranca(){
        basic.distribuiHeranca();
    }

    public void imprime(){
        basic.doTheString();
    }

}
