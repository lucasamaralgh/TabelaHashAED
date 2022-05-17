import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import classes.HashTable;

public class hashWordle {

    /**
     *  Cria a tabela Hash com capacidade de 24 mil palavras
     */
    static HashTable hashpalavras = new HashTable(24000);


    /**
     * Este método recebe um arquivo contendo as palavra a serem armazenadas.
     * Carrega o arquivo e se a palavra na linha tiver 5 letras entao armazena na tabelha Hash (hashpalavras)
     * @param arquivo Arquivo a ser carregado
     * @return Tabela Hash com as palavras armazenadas
     */
    public static HashTable carregarPalavras(String arquivo) throws IOException {
        Scanner arquivoLido = new Scanner(new File(arquivo));
        String palavras5letras = ""; //Inicializa a string a ser lida


        while (arquivoLido.hasNextLine()){
            palavras5letras = arquivoLido.nextLine();
            
            if(palavras5letras.length() == 5 ) hashpalavras.inserir(palavras5letras, palavras5letras);
        }

        arquivoLido.close(); //Fecha o scanner
        return hashpalavras; //Retorna a Tabela Hash com as palavras inseridas
    }


    /**
     * Este método recebe uma posição inteira com parâmetro e retorna
     * a palavra nesta posição na tabela. Se a posição não contiver
     * uma palavra, deve retornar a palavra da posição mais próxima.
     * @param posicao Número inteiro representando a posição da tabela.
     * @return Uma palavra constante da tabela
     */
    public static String sortearPalavra(int posicao) {
        String palavraSorteada = HashTable.buscarKey(posicao);


        while (palavraSorteada == null) {

            posicao++;
            palavraSorteada = HashTable.buscarKey(posicao);
        }

        return palavraSorteada;

    }

    /**
     * Este método recebe uma string contendo uma palavra. Ele deve
     * buscar esta palavra na tabela, retornando TRUE se a palavra
     * existe ou FALSE se ela não existir
     * @param palavra A palavra a ser procurada na tabela
     * @return false/false conforme a existência da palavra.
     */
    public static boolean existePalavra(String palavra){
        String teste = hashpalavras.buscar(palavra);
        if(teste != null) {
            return true;
        }
        else{
            return false;
        }

    }


    public static void main(String[] args) throws IOException {

        executar();
    }


    /** Este metodo é somente para teste, onde executa os metodos acima
     *
     */
    public static void executar() throws IOException {
        Random gerador = new Random();

        carregarPalavras("palavras.txt");
        System.out.println(existePalavra("teste"));
        int gerado = gerador.nextInt(24000);
        System.out.println("Palavra sorteada: " + sortearPalavra(gerado));
        System.out.println("Posicao: " + gerado);
    }
}