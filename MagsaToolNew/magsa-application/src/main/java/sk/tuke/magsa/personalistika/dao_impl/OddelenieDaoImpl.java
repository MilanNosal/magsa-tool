package sk.tuke.magsa.personalistika.dao_impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import sk.tuke.magsa.framework.CRUDDaoImpl;
import sk.tuke.magsa.framework.ValidatorException;
import sk.tuke.magsa.framework.ConnectionPool;
import sk.tuke.magsa.personalistika.entity.Oddelenie;
import sk.tuke.magsa.personalistika.dao.OddelenieDao;

public class OddelenieDaoImpl extends CRUDDaoImpl<Oddelenie> implements OddelenieDao {
    public OddelenieDaoImpl(ConnectionPool pool) {
        super(pool);
    }

    protected PreparedStatement prepareInsertStatement(Connection connection, Oddelenie object) throws SQLException {
        String query = "INSERT INTO Oddelenie (nazov, kod, poschodie) VALUES (?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, object.getNazov());
        pstm.setString(2, object.getKod());
        pstm.setInt(3, object.getPoschodie());
        return pstm;
    }

    protected PreparedStatement prepareUpdateStatement(Connection connection, Oddelenie object) throws SQLException {
        String query = "UPDATE Oddelenie SET nazov = ?, kod = ?, poschodie = ? WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, object.getNazov());
        pstm.setString(2, object.getKod());
        pstm.setInt(3, object.getPoschodie());
        pstm.setInt(4, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareDeleteStatement(Connection connection, Oddelenie object) throws SQLException {
        String query = "DELETE FROM Oddelenie WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareFindStatement(Connection connection, Integer id) throws SQLException {
        String query = "SELECT ident, nazov, kod, poschodie FROM Oddelenie WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

    protected PreparedStatement prepareSelectStatement(Connection connection) throws SQLException {
        String query = "SELECT ident, nazov, kod, poschodie FROM Oddelenie";
        return connection.prepareStatement(query);
    }

    protected Oddelenie createFromResultSet(ResultSet rs) throws SQLException {
        Oddelenie result = new Oddelenie();
        result.setIdent(rs.getInt("ident"));
        result.setNazov(rs.getString("nazov"));
        result.setKod(rs.getString("kod"));
        result.setPoschodie(rs.getInt("poschodie"));
        return result;
    }

    @Override
    protected void test(Oddelenie object) {
    }
}
