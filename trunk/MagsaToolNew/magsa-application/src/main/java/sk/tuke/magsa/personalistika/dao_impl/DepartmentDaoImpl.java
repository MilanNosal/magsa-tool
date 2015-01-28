package sk.tuke.magsa.personalistika.dao_impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import sk.tuke.magsa.framework.CRUDDaoImpl;
import sk.tuke.magsa.framework.ValidatorException;
import sk.tuke.magsa.framework.ConnectionPool;
import sk.tuke.magsa.personalistika.entity.Department;
import sk.tuke.magsa.personalistika.dao.DepartmentDao;

public class DepartmentDaoImpl extends CRUDDaoImpl<Department> implements DepartmentDao {
    public DepartmentDaoImpl(ConnectionPool pool) {
        super(pool);
    }

    protected PreparedStatement prepareInsertStatement(Connection connection, Department object) throws SQLException {
        String query = "INSERT INTO Department (name, code, level) VALUES (?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, object.getName());
        pstm.setString(2, object.getCode());
        pstm.setDouble(3, object.getLevel());
        return pstm;
    }

    protected PreparedStatement prepareUpdateStatement(Connection connection, Department object) throws SQLException {
        String query = "UPDATE Department SET name = ?, code = ?, level = ? WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, object.getName());
        pstm.setString(2, object.getCode());
        pstm.setDouble(3, object.getLevel());
        pstm.setInt(4, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareDeleteStatement(Connection connection, Department object) throws SQLException {
        String query = "DELETE FROM Department WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareFindStatement(Connection connection, Integer id) throws SQLException {
        String query = "SELECT ident, name, code, level FROM Department WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

    protected PreparedStatement prepareSelectStatement(Connection connection) throws SQLException {
        String query = "SELECT ident, name, code, level FROM Department";
        return connection.prepareStatement(query);
    }

    protected Department createFromResultSet(ResultSet rs) throws SQLException {
        Department result = new Department();
        result.setIdent(rs.getInt("ident"));
        result.setName(rs.getString("name"));
        result.setCode(rs.getString("code"));
        result.setLevel(rs.getDouble("level"));
        return result;
    }

    @Override
    protected void test(Department object) {
        if(object.getName() == null) {
            throw new ValidatorException("Property 'name' is required!");
        }
        if(object.getName() != null) {
            if(object.getName().length() < 2 || object.getName().length() > 30) {
                throw new ValidatorException("Property 'name' has length out of range!");
            }
        }
        if(object.getCode() == null) {
            throw new ValidatorException("Property 'code' is required!");
        }
        if(object.getCode() != null) {
            if(object.getCode().length() < 1 || object.getCode().length() > 4) {
                throw new ValidatorException("Property 'code' has length out of range!");
            }
        }
    }
}
