package Departments.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ModelDepartment implements IDepartment {

    private static final Map<String, ModelDepartment> DEPARTMENTS = new HashMap<>();

    private String name;

    public ModelDepartment(String name) {
        this.name = name;
    }

    public ModelDepartment() {}

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

    public static ModelDepartment getDepartment(String departmentID) {
        Collection<ModelDepartment> departmentCollection = getDepartmentsList();
        AtomicReference<ModelDepartment> departmentModel = new AtomicReference<>();
        departmentCollection.forEach(d -> {
            if(d.getName().equals(departmentID)) {
                departmentModel.set(d);
            }
        });
        return departmentModel.get();
    }

     public static List<ModelDepartment> getDepartmentsList() {
         Collection<ModelDepartment> departmentCollection = getDepartments().values();

         return departmentCollection.stream().toList();
     }

    public static Map<String, ModelDepartment> getDepartments() {
        return DEPARTMENTS;
    }
}
