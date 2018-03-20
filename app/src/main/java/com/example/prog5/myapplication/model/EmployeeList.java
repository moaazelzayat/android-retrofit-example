package com.example.prog5.myapplication.model;

import com.example.prog5.myapplication.model.Employee;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prog5 on 3/20/2018.
 */

public class EmployeeList {
    @SerializedName("employeeList")
    private ArrayList<Employee> employeeList;

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
