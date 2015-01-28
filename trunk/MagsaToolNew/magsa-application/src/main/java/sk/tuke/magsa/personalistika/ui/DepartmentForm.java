package sk.tuke.magsa.personalistika.ui;

import sk.tuke.magsa.framework.Utilities;
import sk.tuke.magsa.framework.ValidatorException;
import sk.tuke.magsa.framework.ui.FormDialog;
import sk.tuke.magsa.personalistika.entity.Department;

public class DepartmentForm extends FormDialog<Department>{
    public DepartmentForm() {
        super(new Department());
    }

    public DepartmentForm(Department entity) {
        super(entity);
    }

    public Department show() {
        String input;


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


        String code = entity.getCode();
        do {
            try {
                System.out.print(String.format("Enter code [%s]: ", entity.getCode()));
                input = Utilities.readLine();
                if(!"".equals(input)) {
                    code = input;
                }
        if(code == null) {
            throw new ValidatorException("Property 'code' is required!");
        }
        if(code != null) {
            if(code.length() < 1 || code.length() > 4) {
                throw new ValidatorException("Property 'code' has length out of range!");
            }
        }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Cannot parse the entered value!");
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        entity.setCode(code);


        Double level = entity.getLevel();
        do {
            try {
                System.out.print(String.format("Enter level [%s]: ", entity.getLevel()));
                input = Utilities.readLine();
                if(!"".equals(input)) {
                    level = Double.valueOf(input);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Cannot parse the entered value!");
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        entity.setLevel(level);

        return entity;
    }
}
