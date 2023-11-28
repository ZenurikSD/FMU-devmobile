package me.material;

public class Despesa{
    private int id;
    private int categoriaIndex;
    private String descricao;
    private double valor;
    private String data;
    private int recorrencia;

    public Despesa(int categoriaIndex, double valor) {
        this.categoriaIndex = categoriaIndex;
        this.valor = valor;
        this.data = "04/12/23";
        this.recorrencia = 1;
    }

    public Despesa(int categoriaIndex, double valor, String descricao, String data, int recorrencia) {
        this.categoriaIndex = categoriaIndex;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.recorrencia = recorrencia;
    }

    public int getId() {
        return id;
    }

    public int getCategoriaIndex() {
        return categoriaIndex;
    }
    public void setCategoriaIndex(int categoriaIndex) {
        this.categoriaIndex = categoriaIndex;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public int getRecorrencia() {
        return recorrencia;
    }
    public void setRecorrencia(int recorrencia) {
        this.recorrencia = recorrencia;
    }

    @Override
    public String toString(){
        return
            "ID #" + id +
            "\nCategoria: " + this.categoriaIndex +
            "\nDescrição: " + this.descricao +
            "\nR$ " + this.valor +
            "\nData: " + this.data +
            "\nRecorrência: " + this.recorrencia;
    }
}

