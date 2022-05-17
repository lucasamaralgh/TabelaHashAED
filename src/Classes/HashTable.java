package classes;

public class HashTable {

    /** Inicializa as variaveis
     *
     */
    public static int tam = 0;   //tamanho da tabela
    public static entradaWords[] dados;
    public static int[] pesos;

    /** Metodo para preencher a tabela com entradas null no tamanho "tam"
     * @param n
     */
    public HashTable(int n){
        this.tam = n;
        this.dados = new entradaWords[tam];
        this.preencherPesos();

        for(int i = 0; i < this.tam; i++)
            dados[i] = new entradaWords();   //preenche a tabela com entradas null
    }

    /** Metodo para calcular o codigo da chave
     * @param chave
     * @return codeFinal
     */
    public static int calcularCodigo(String chave){
        int codeFinal=0;
        for (int i =0; i<chave.length(); i++) {
            int j=i;
            if(i >= pesos.length){
                j=0;
            }
            int code = chave.charAt(i);

            code *= pesos[j];

            codeFinal += code;
        }
        return codeFinal;
    }

    /** Metodo para preencher pesos
     */
    public void preencherPesos(){
        this.pesos = new  int[5];
        for (int i = 0; i <pesos.length ; i++) {
            this.pesos[i] = (i += 2);
        }
    }

    /** Metodo para calcular o segundoHash
     * @param code
     * @return
     */
    public static int segundoHash(int code){
        int newwcode = code/32;
        return newwcode;
    }

    /** Metodo para mapear
     * @param codigo
     * @return codigoMapeado
     */
    public static int mapear(int codigo){
        return codigo % tam;    //posicao no mapa e o resto da divisao do codigo pelo tamanho
    }

    /** Metodo para localizacao da Key
     * @param key
     * @return pos
     */
    public static int localizar(String key){
        int calcHash = calcularCodigo(key);
        int pos = mapear(calcHash);//descobre a posicao
        int indiceSondagem = 1; //indice para iniciar a sondagem quadratica
        while(dados[pos].valido && !key.equals(dados[pos].chave)){
            pos = mapear(pos + (indiceSondagem *segundoHash(calcHash)));
            indiceSondagem++;   //indice de sondagem soma + 1
        }
        return pos; //quando acha uma posicao vazia ou com a chave igual, retorna essa posicao
    }

    /** Metodo para insercao da palavra
     * @param chave
     * @param novo
     */
    public static void inserir(String chave, String novo){
        entradaWords nova = new entradaWords(chave, novo);    //cria nova entrada
        int pos = localizar(chave); //localiza a posicao
        dados[pos] = nova;  //posiciona a entrada na respectiva posicao
    }

    /** Metodo para busca da chave
     * @param chave
     * @return palavra
     */
    public static String buscar(String chave){
        int pos = localizar(chave); //localiza a posicao da chave
        return dados[pos].palavra;   //retorna o dado dentro da entrada
    }

    /** Metodo para buscar a palavra referente a posicao (key)
     * @param posicao
     * @return palavra
     */
    public static String buscarKey(int posicao){
        return dados[posicao].palavra;   //retorna a palavra na posiçao inserida
    }

}

