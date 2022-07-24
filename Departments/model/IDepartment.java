package Departments.model;

public interface IDepartment {

    boolean registerDepartment();
    void closeDepartment();

    static DepartmentModel getDepartment(String department) {
        return null;
    }
}
