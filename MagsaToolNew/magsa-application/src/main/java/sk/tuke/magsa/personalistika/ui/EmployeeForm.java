package sk.tuke.magsa.personalistika.ui;

import sk.tuke.magsa.framework.Utilities;
import sk.tuke.magsa.framework.ValidatorException;
import sk.tuke.magsa.framework.ui.FormDialog;
import sk.tuke.magsa.personalistika.entity.Employee;

public class EmployeeForm extends FormDialog<Employee>{
    public EmployeeForm() {
        super(new Employee());
    }

    public EmployeeForm(Employee entity) {
        super(entity);
    }

    public Employee show() {
        String input;


        String surname = entity.getSurname();
        do {
            try {
                System.out.print(String.format("Enter surname [%s]: ", entity.getSurname()));
                input = Utilities.readLine();
                if(!"".equals(input)) {
                    surname = input;
                }
        if(surname == null) {
            throw new ValidatorException("Property 'surname' is required!");
        }
        if(surname != null) {
            if(surname.length() < 2 || surname.length() > 30) {
                throw new ValidatorException("Property 'surname' has length out of range!");
            }
        }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Cannot parse the entered value!");
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        entity.setSurname(surname);


        String name = entity.getName();
        do {
            try {
                System.out.print(String.format("Enter name [%s]: ", entity.getName()));
                input = Utilities.readLine();
                if(!"".equals(input)) {
                    name = input;
                }
        if(name == null) {
            throw new ValidatorException("Property 'name' is required!");
        }
        if(name != null) {
            if(name.length() < 2 || name.length() > 30) {
                throw new ValidatorException("Property 'name' has length out of range!");
            }
        }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Cannot parse the entered value!");
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        entity.setName(name);


        Integer age = entity.getAge();
        do {
            try {
                System.out.print(String.format("Enter age [%s]: ", entity.getAge()));
                input = Utilities.readLine();
                if(!"".equals(input)) {
                    age = Integer.valueOf(input);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Cannot parse the entered value!");
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        entity.setAge(age);

        entity.setDepartment(new sk.tuke.magsa.personalistika.ui.DepartmentTable().selection().getIdent());

        return entity;
    }
}
