package Departments.list;

import Departments.model.ModelDepartment;

public class Example {

    private final String name;
    private final ModelDepartment[] departments;
    private int iDepartments;

    public Example(String name) {
        this.name = name;
        this.departments = new ModelDepartment[15];
        this.iDepartments = 0;
    }

    public void createDepartment() {
        if(iDepartments <= 15) {
            departments[iDepartments] = new ModelDepartment(getName());
            departments[iDepartments].registerDepartment();

            System.out.printf("The new %s department was created!", getName());
            iDepartments++;
        } else {
            System.out.println("Error when create the new departments.");
        }
    }

    public String getName() {
        return name;
    }
}
