package Departments.model;

public interface IDepartment {

    boolean registerDepartment();
    void closeDepartment();

    static ModelDepartment getDepartment(String department) {
        return null;
    }
}
