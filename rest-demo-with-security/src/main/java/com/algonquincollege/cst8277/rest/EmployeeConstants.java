package com.algonquincollege.cst8277.rest;

public interface EmployeeConstants {

    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String EMPLOYEE_RESOURCE_NAME =  "employee";
    public static final String EMPLOYEE_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String EMPLOYEE_RESOURCE_PATH_ID_PATH =  "/{" + EMPLOYEE_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_EMPLOYEES_OP_DESC = "Retrieves list of Employees";
    public static final String GET_EMPLOYEES_OP_200_DESC = "Successful, returning employees";
    public static final String GET_EMPLOYEES_OP_403_DESC = "Only admin's can list all employees";
    public static final String GET_EMPLOYEES_OP_404_DESC = "Could not find employees";
    public static final String GET_EMPLOYEES_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_EMPLOYEES_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_EMPLOYEE_BY_ID_OP_DESC = "Retrieve specific Employee";
    public static final String GET_EMPLOYEE_BY_ID_OP_200_DESC = "Successful, returning requested employee";
    public static final String GET_EMPLOYEE_BY_ID_OP_403_DESC = "Only user's can retrieve a specific employee";
    public static final String GET_EMPLOYEE_BY_ID_OP_404_DESC = "Requested employee not found";
    public static final String GET_EMPLOYEES_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_EMPLOYEE_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;

}