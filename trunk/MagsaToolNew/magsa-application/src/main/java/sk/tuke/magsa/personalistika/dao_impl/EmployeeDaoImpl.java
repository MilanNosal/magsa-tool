package sk.tuke.magsa.personalistika.dao_impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import sk.tuke.magsa.framework.CRUDDaoImpl;
import sk.tuke.magsa.framework.ValidatorException;
import sk.tuke.magsa.framework.ConnectionPool;
import sk.tuke.magsa.personalistika.entity.Employee;
import sk.tuke.magsa.personalistika.dao.EmployeeDao;

public class EmployeeDaoImpl extends CRUDDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(ConnectionPool pool) {
        super(pool);
    }

    protected PreparedStatement prepareInsertStatement(Connection connection, Employee object) throws SQLException {
        String query = "INSERT INTO Employee (name, surname, age, ident_Department) VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, object.getName());
        pstm.setString(2, object.getSurname());
        pstm.setInt(3, object.getAge());
        pstm.setInt(4, object.getDepartment());
        return pstm;
    }

    protected PreparedStatement prepareUpdateStatement(Connection connection, Employee object) throws SQLException {
        String query = "UPDATE Employee SET name = ?, surname = ?, age = ?, ident_Department = ? WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, object.getName());
        pstm.setString(2, object.getSurname());
        pstm.setInt(3, object.getAge());
        pstm.setInt(4, object.getDepartment());
        pstm.setInt(5, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareDeleteStatement(Connection connection, Employee object) throws SQLException {
        String query = "DELETE FROM Employee WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, object.getIdent());
        return pstm;
    }

    protected PreparedStatement prepareFindStatement(Connection connection, Integer id) throws SQLException {
        String query = "SELECT ident, name, surname, age, ident_Department FROM Employee WHERE ident = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

    protected PreparedStatement prepareSelectStatement(Connection connection) throws SQLException {
        String query = "SELECT ident, name, surname, age, ident_Department FROM Employee";
        return connection.prepareStatement(query);
    }

    protected Employee createFromResultSet(ResultSet rs) throws SQLException {
        Employee result = new Employee();
        result.setIdent(rs.getInt("ident"));
        result.setName(rs.getString("name"));
        result.setSurname(rs.getString("surname"));
        result.setAge(rs.getInt("age"));
        result.setDepartment(rs.getInt("ident_Department"));
        return result;
    }

    @Override
    protected void test(Employee object) {
        if(object.getName() == null) {
            throw new ValidatorException("Property 'name' is required!");
        }
        if(object.getName() != null) {
            if(object.getName().length() < 2 || object.getName().length() > 30) {
                throw new ValidatorException("Property 'name' has length out of range!");
            }
        }
        if(object.getSurname() == null) {
            throw new ValidatorException("Property 'surname' is required!");
        }
        if(object.getSurname() != null) {
            if(object.getSurname().length() < 2 || object.getSurname().length() > 30) {
                throw new ValidatorException("Property 'surname' has length out of range!");
            }
        }
    }
}
