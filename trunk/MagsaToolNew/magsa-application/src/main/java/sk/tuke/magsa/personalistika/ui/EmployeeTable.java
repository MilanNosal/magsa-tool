package sk.tuke.magsa.personalistika.ui;

import sk.tuke.magsa.framework.ui.TableDialog;
import sk.tuke.magsa.personalistika.entity.Employee;
import sk.tuke.magsa.personalistika.Application;

public class EmployeeTable extends TableDialog<Employee>{
    public EmployeeTable() {
        super(Application.getInstance().getEmployeeDao());
    }

    protected EmployeeForm createFormDialogForInsert() {
        return new EmployeeForm();
    }

    protected EmployeeForm createFormDialogForEdit(Employee entity) {
        return new EmployeeForm(entity);
    }

    protected void printHeader() {
        System.out.print(String.format("|%5s", "ID"));
        System.out.print(String.format("|%10s", "Surname"));
        System.out.print(String.format("|%10s", "Name"));
        System.out.print(String.format("|%10s", "Age"));
        System.out.print(String.format("|%10s", "Department"));
        System.out.println();
    }

    protected void printRow(Employee entity) {
        System.out.print(String.format("|%5d", entity.getIdent()));
        System.out.print(String.format("|%10s", entity.getSurname()));
        System.out.print(String.format("|%10s", entity.getName()));
        System.out.print(String.format("|%10s", entity.getAge()));
        sk.tuke.magsa.personalistika.entity.Department department = Application.getInstance().getDepartmentDao().find(entity.getDepartment());
        System.out.print(String.format("|%10s", (department != null ? department.getName() : "")));
        System.out.println();
    }
}
