package Departments.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class DepartmentModel implements IDepartment {

    private static final Map<String, DepartmentModel> DEPARTMENTS = new HashMap<>();

    private String name;

    public DepartmentModel(String name) {
        this.name = name;
    }

    public DepartmentModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean registerDepartment() {
        DEPARTMENTS.put(getName(), this);
        System.out.printf("The new department %s was added%n", getName());
        return true;
    }

    @Override
    public void closeDepartment() {
        if(getDepartment(getName()) == null) return;
        System.out.printf("The department %s was closed%n", getName());
        DEPARTMENTS.remove(getName());
    }

    public static DepartmentModel getDepartment(String departmentID) {
        Collection<DepartmentModel> departmentCollection = getDepartmentsList();
        AtomicReference<DepartmentModel> departmentModel = new AtomicReference<>();
        departmentCollection.forEach(d -> {
            if(d.getName().equals(departmentID)) {
                departmentModel.set(d);
            }
        });
        return departmentModel.get();
    }

     public static List<DepartmentModel> getDepartmentsList() {
         Collection<DepartmentModel> departmentCollection = getDepartments().values();

         return departmentCollection.stream().toList();
     }

    public static Map<String, DepartmentModel> getDepartments() {
        return DEPARTMENTS;
    }
}
