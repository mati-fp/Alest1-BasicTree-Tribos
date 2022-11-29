public class App {
    public static void main(String[] args) throws Exception {
        
        ReadFile arquivo = new ReadFile();

        arquivo.loadProgram("TriboEDSON.txt");
        // arquivo.loadProgram("TriboTESTE1.txt");
        // arquivo.loadProgram("TriboTESTE2.txt");
        // arquivo.loadProgram("TriboTESTE3.txt");
        //arquivo.loadProgram("TriboTESTE4.txt");

        arquivo.passaHeranca();

        arquivo.imprime();

    }
}
