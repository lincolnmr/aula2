package aulaRevisao;

import DAO.Fabrica;
import java.sql.SQLException;
import view.Tela;

public class Revisao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        (new Tela()).setVisible(true);
        
        Fabrica.getConexaoSINGLETON();
    }
}
