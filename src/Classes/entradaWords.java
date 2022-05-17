package classes;

/** Classe para construir a entradaWord
 */
public class entradaWords {

    public String chave;
    public String palavra;
    public boolean valido;

    public entradaWords(){
        this.chave = "";
        this.palavra = null;
        this.unsetValido();
    }
    public entradaWords(String palavra, String palavra1){
        this.chave = palavra;
        this.palavra = palavra1;
        this.setValido();
    }


    public boolean isValido() {
        return valido;
    }

    public void setValido(){
        this.valido = true;
    }
    public void unsetValido(){
        this.valido = false;
    }

}