package Departments;

import Departments.model.DepartmentModel;

public class Main {

    public static void main(String[] args) {
        DepartmentModel department = new DepartmentModel("Sample");
        department.registerDepartment("DS");

        department.setName("None");
        department.closeDepartment();
    }
}
