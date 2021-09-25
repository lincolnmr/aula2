package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cadastro;

/* 

metodo recuperar todos 


*/

public class DAOcadastro {

    public static void insert(Cadastro obj) {
        Connection conexao = Fabrica.getConexaoSINGLETON();
        try {
            PreparedStatement stSQL = conexao.prepareStatement( "INSERT INTO TBL_CADASTRO (codigo, descricao , ativo) VALUES (?,?,?)");
            stSQL.setInt(1, obj.getCodigo());
            stSQL.setString(2, obj.getDescricao());
            stSQL.setBoolean(3, obj.isAtivo());

            stSQL.execute();
            System.out.println("Inserido \n");
        } catch (SQLException ex) {
            System.out.println("Não inserido - erro: \\n" + ex.getMessage());
        }
    }
    
    public static void insert(Cadastro obj, Connection conexao) {
        
        try {
            PreparedStatement stSQL = conexao.prepareStatement( "INSERT INTO TBL_CADASTRO (codigo, descricao , ativo) VALUES (?,?,?)");
            stSQL.setInt(1, obj.getCodigo());
            stSQL.setString(2, obj.getDescricao());
            stSQL.setBoolean(3, obj.isAtivo());

            stSQL.execute();
            System.out.println("Inserido \n");
        } catch (SQLException ex) {
            System.out.println("Não inserido - erro: \\n" + ex.getMessage());
        }
    }

    public static void update(Cadastro objCadastro) {

        Connection conexao = Fabrica.getConexaoSINGLETON();
        try {
            String sqlInsert = "UPDATE TBL_CADASTRO SET descricao = ?, ativo = ? WHERE codigo = ?";

            PreparedStatement sql = conexao.prepareStatement(sqlInsert);

            sql.setString(1, objCadastro.getDescricao());
            sql.setBoolean(2, objCadastro.isAtivo());
            sql.setInt(3, objCadastro.getCodigo());
            
            sql.executeUpdate();
            sql.close();

            System.out.println("Atualizado \n");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar contato \n" + ex.getMessage());
        }
    }
    
    public static void update(Cadastro objCadastro, Connection conexao) {

        try {
            String sqlInsert = "UPDATE TBL_CADASTRO SET descricao = ?, ativo = ? WHERE codigo = ?";

            PreparedStatement sql = conexao.prepareStatement(sqlInsert);

            sql.setString(1, objCadastro.getDescricao());
            sql.setBoolean(2, objCadastro.isAtivo());
            sql.setInt(3, objCadastro.getCodigo());
            
            sql.executeUpdate();
            sql.close();

            System.out.println("Atualizado \n");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar contato \n" + ex.getMessage());
        }
    }

    public static boolean excluir(int codigo) {
        
        Connection conexao = Fabrica.getConexaoSINGLETON();

        String sql = "DELETE FROM TBL_CADASTRO WHERE codigo = ?";

        try {
            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement(sql);

            ST.setInt(1, codigo);
            ST.execute();

            System.out.println("Excluido");
            return true;
        } catch (SQLException err) {
            System.err.println("Erro ao excluir: " + err.getMessage());
        }
        return false;
    }
    
    public static boolean excluir(int codigo, Connection conexao) {
        
        String sql = "DELETE FROM TBL_CADASTRO WHERE codigo = ?";

        try {
            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement(sql);

            ST.setInt(1, codigo);
            ST.execute();

            System.out.println("Excluido");
            return true;
        } catch (SQLException err) {
            System.err.println("Erro ao excluir: " + err.getMessage());
        }
        return false;
    }
    

    public static Cadastro recuperar(int chave) {

        Connection conexao = Fabrica.getConexaoSINGLETON();

        String sql = "SELECT * FROM TBL_CADASTRO where codigo  = ?";

        Cadastro obj = null;

        try {
            PreparedStatement ST = conexao.prepareStatement(sql);
            ST.setInt(1, chave);
            ResultSet objResultSet = ST.executeQuery();
            
            objResultSet.next();
            
            obj = new Cadastro();
            obj.setCodigo(objResultSet.getInt("codigo"));
            obj.setDescricao(objResultSet.getString("descricao"));
            obj.setAtivo(objResultSet.getBoolean("ativo"));

        } catch (Exception e) {
            System.err.println("Erro ao recuperar" + e.getMessage());
        }

        return obj;
    }
    
     public static ArrayList<Cadastro> recuperarTodos(Connection conexao) {

        String sql = "SELECT codigo, descricao, ativo FROM TBL_CADASTRO";
        Cadastro obj = null;
        ArrayList<Cadastro> lista = new ArrayList<>();

        try {

            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement(sql);

            ResultSet objResultSet = ST.executeQuery();

            while(objResultSet.next()) {

                obj = new Cadastro();
                obj.setCodigo(objResultSet.getInt("codigo"));
                obj.setDescricao(objResultSet.getString("descricao"));
                obj.setAtivo(objResultSet.getBoolean("ativo"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar lista \n" + ex.getMessage());
        }
        return null;
    }
    
    public static boolean verificar(int chave) {

        Connection conexao = Fabrica.getConexaoSINGLETON();
        
        String sql = "SELECT * FROM TBL_CADASTRO where codigo  = ?";
            
        try {
            PreparedStatement ST = conexao.prepareStatement(sql);
            ST.setInt(1, chave);
            ResultSet objResultSet = ST.executeQuery();
            
            if(!objResultSet.next()) return false;
            
        } catch (Exception e) {
            System.err.println("Erro ao verificar" + e.getMessage());
        }
        return true;
    }
    
    /*
    public static void transacaoChupaCabra() throws SQLException{
        Connection cnx = Fabrica.getConexaoNOVA();
        try {
            PreparedStatement ST = cnx.prepareStatement("select max(codigo) from tbl_cadastro");
            ResultSet objResultSet = ST.executeQuery();
            objResultSet.next();
            int maxCod = objResultSet.getInt(1);
            maxCod++;
            for (int i = 0; i < 5; i++) {
                ST = cnx.prepareStatement("INSERT INTO TBL_CADASTRO (codigo, descricao , ativo) VALUES (?,?,?)");
                ST.setInt(1, maxCod+i);
                ST.setString(2, "MENGAO CAMPEAO: " + (maxCod+i));
                ST.setBoolean(3, true);
                ST.executeUpdate();
            }           
            ST = cnx.prepareStatement("update TBL_CADASTRO set ativo = null where codigo >= ?");  
            ST.setInt(1, maxCod);
            ST.executeUpdate();
            cnx.commit();
            System.out.println("A transação chupa cabra executou com sucesso!");
        } catch(SQLException ex) {
            cnx.rollback();
            System.out.println("A transação chupa cabra deu ruim!\n" + ex.getMessage());            
        }
    }*/    
}
