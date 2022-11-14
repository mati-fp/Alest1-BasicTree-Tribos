public class App {
    public static void main(String[] args) throws Exception {
        
        ReadFile arquivo = new ReadFile();

        arquivo.loadProgram("triboViking.txt");

        arquivo.passaHeranca();

        arquivo.imprime();

    }
}
