package sk.tuke.magsa.personalistika.dao_impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import sk.tuke.magsa.framework.CRUDDaoImpl;
import sk.tuke.magsa.framework.ValidatorException;
import sk.tuke.magsa.framework.ConnectionPool;
import sk.tuke.magsa.personalistika.entity.Zamestnanec;
import sk.tuke.magsa.personalistika.dao.ZamestnanecDao;

public class ZamestnanecDaoImpl extends CRUDDaoImpl<Zamestnanec> implements ZamestnanecDao {
    public ZamestnanecDaoImpl(ConnectionPool pool) {
        super(pool);
    }

    protected PreparedStatement prepareInsertStatement(Connection connection, Zamestnanec object) throws SQLException {
        String query = "INSERT INTO Zamestnanec (meno, priezvisko, vek) VALUES (?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, object.getMeno());
        pstm.setString(2, object.getPriezvisko());
        pstm.setInt(3, object.getVek());
        return pstm;
    }

    protected PreparedStatement prepareUpdateStatement(Connection connection, Zamestnanec object) throws SQLException {
        String query = "UPDATE Zamestnanec SET meno = ?, priezvisko = ?, vek = ? WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, object.getMeno());
        pstm.setString(2, object.getPriezvisko());
        pstm.setInt(3, object.getVek());
        pstm.setInt(4, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareDeleteStatement(Connection connection, Zamestnanec object) throws SQLException {
        String query = "DELETE FROM Zamestnanec WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareFindStatement(Connection connection, Integer id) throws SQLException {
        String query = "SELECT ident, meno, priezvisko, vek FROM Zamestnanec WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

    protected PreparedStatement prepareSelectStatement(Connection connection) throws SQLException {
        String query = "SELECT ident, meno, priezvisko, vek FROM Zamestnanec";
        return connection.prepareStatement(query);
    }

    protected Zamestnanec createFromResultSet(ResultSet rs) throws SQLException {
        Zamestnanec result = new Zamestnanec();
        result.setIdent(rs.getInt("ident"));
        result.setMeno(rs.getString("meno"));
        result.setPriezvisko(rs.getString("priezvisko"));
        result.setVek(rs.getInt("vek"));
        return result;
    }

    @Override
    protected void test(Zamestnanec object) {
    }
}
