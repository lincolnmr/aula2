package model;

public class Cadastro {

    private int codigo;
    private String descricao;
    private boolean ativo;

    public Cadastro() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Cadastro(int codigo, String descricao, boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public String[] toArray() {

        String[] array = new String[3];

        array[0] = String.valueOf(getCodigo());
        array[1] = getDescricao();
        array[2] = String.valueOf(isAtivo());

        return array;
    }
}
