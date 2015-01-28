package sk.tuke.magsa.personalistika;

import sk.tuke.magsa.framework.*;
import sk.tuke.magsa.personalistika.dao.*;
import sk.tuke.magsa.personalistika.dao_impl.*;
import sk.tuke.magsa.personalistika.ui.*;

public class Application {
private static Application instance;

private final ConnectionPool pool = new ConnectionPool();


private final DepartmentDao departmentDao = new DepartmentDaoImpl(pool);

public DepartmentDao getDepartmentDao() {
        return departmentDao;
        }

private final EmployeeDao employeeDao = new EmployeeDaoImpl(pool);

public EmployeeDao getEmployeeDao() {
        return employeeDao;
        }

public static Application getInstance() {
        return instance;
        }

private void menu() {
        System.out.println("Running the application...");

        int selection = 0;
        do {
                    System.out.println("(1) Odeleinia");
                        System.out.println("(2) Employees");
                    System.out.println("(3) Exit");
        System.out.println("Enter selection: ");

        try {
        selection = Integer.parseInt(Utilities.readLine());
        } catch (NumberFormatException e) {
        }

        switch (selection) {
                    case 1:
            new DepartmentTable().menu();
            break;
                        case 2:
            new EmployeeTable().menu();
            break;
                    }
        } while (selection != 3);
        }

public static void main(String[] args) {
        instance = new Application();
        instance.menu();
        }
        }
