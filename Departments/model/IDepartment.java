package Departments.model;

public interface IDepartment {

    boolean registerDepartment(String departmentID);
    void closeDepartment();

    static DepartmentModel getDepartment(String department) {
        return null;
    }
}
