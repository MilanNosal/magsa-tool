package sk.tuke.magsa.personalistika.ui;

import sk.tuke.magsa.framework.ui.TableDialog;
import sk.tuke.magsa.personalistika.entity.Department;
import sk.tuke.magsa.personalistika.Application;

public class DepartmentTable extends TableDialog<Department>{
    public DepartmentTable() {
        super(Application.getInstance().getDepartmentDao());
    }

    protected DepartmentForm createFormDialogForInsert() {
        return new DepartmentForm();
    }

    protected DepartmentForm createFormDialogForEdit(Department entity) {
        return new DepartmentForm(entity);
    }

    protected void printHeader() {
        System.out.print(String.format("|%5s", "ID"));
        System.out.print(String.format("|%10s", "Nazov"));
        System.out.print(String.format("|%10s", "code"));
        System.out.print(String.format("|%10s", "level"));
        System.out.println();
    }

    protected void printRow(Department entity) {
        System.out.print(String.format("|%5d", entity.getIdent()));
        System.out.print(String.format("|%10s", entity.getName()));
        System.out.print(String.format("|%10s", entity.getCode()));
        System.out.print(String.format("|%10s", entity.getLevel()));
        System.out.println();
    }
}
