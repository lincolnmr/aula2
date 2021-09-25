package controller;

import DAO.DAOcadastro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cadastro;

public class CtrlCadastro {

    public static void inserir(String[] dados) {

        try {
            
            Cadastro obj = new Cadastro();
            obj.setCodigo(Integer.parseInt(dados[0]));
            obj.setDescricao(dados[1]);
            obj.setAtivo(Boolean.parseBoolean(dados[2]));

            if (!verifica(obj.getCodigo())){
                DAO.DAOcadastro.insert(obj);
            } else {
                DAO.DAOcadastro.update(obj);
            }
             
        } catch (Exception f) {
            System.out.println("Erro ao gravar objeto na classe controle: " + f.getMessage());
        }
    }
    
    public static void inserir(String[] dados, Connection conexao) {

        try {
            
            Cadastro obj = new Cadastro();
            obj.setCodigo(Integer.parseInt(dados[0]));
            obj.setDescricao(dados[1]);
            obj.setAtivo(Boolean.parseBoolean(dados[2]));

            if (!verifica(obj.getCodigo())){
                DAO.DAOcadastro.insert(obj);
            } else {
                DAO.DAOcadastro.update(obj);
            }
             
        } catch (Exception f) {
            System.out.println("Erro ao gravar objeto na classe controle: " + f.getMessage());
        }
    }

    public static boolean excluir(int codigo) {
        return DAOcadastro.excluir(codigo);
    }
    
    public static boolean excluir(int codigo, Connection conexao) {
        return DAOcadastro.excluir(codigo);
    }

    public static String[] recuperar(int codigo) {

        Cadastro objContato = DAOcadastro.recuperar(codigo);

        return objContato.toArray();
    }
    
     
    public static String[][] recuperarTodos(Connection conexao){

        ArrayList<Cadastro> lista = DAOcadastro.recuperarTodos(conexao);

        String[][] matrizReturn = null;
        matrizReturn = new String[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            matrizReturn[i] = lista.get(i).toArray();
        }
        return matrizReturn;
    }

    
    public static boolean verifica(int codigo) {
        return DAOcadastro.verificar(codigo); 
    }
    
    public static void chamarChupaCabra(){
        try {
            DAOcadastro.transacaoChupaCabra();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
