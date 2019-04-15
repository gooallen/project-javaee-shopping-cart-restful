package com.algonquincollege.cst8277.rest;

public interface MemberConstants {
    
    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String MEMBER_RESOURCE_NAME  =  "member";
    public static final String MEMBER_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String MEMBER_RESOURCE_PATH_ID_PATH =  "/{" + MEMBER_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_MEMBERS_OP_DESC = "Retrieves list of Member";
    public static final String GET_MEMBERS_OP_200_DESC = "Successful, returning Member";
    public static final String GET_MEMBERS_OP_403_DESC = "Only admin's can list all Customers";
    public static final String GET_MEMBERS_OP_404_DESC = "Could not find Members";
    public static final String GET_MEMBERS_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_MEMBERS_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_MEMBER_BY_ID_OP_DESC = "Retrieve specific MEMBER";
    public static final String GET_MEMBER_BY_ID_OP_200_DESC = "Successful, returning requested MEMBER";
    public static final String GET_MEMBER_BY_ID_OP_403_DESC = "Only user's can retrieve a specific MEMBER";
    public static final String GET_MEMBER_BY_ID_OP_404_DESC = "Requested MEMBER not found";
    public static final String GET_MEMBER_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_MEMBER_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
    
    
    public static final String PUT_MEMBER_BY_ID_DESC = "Update a specific MEMBER";
    public static final String PUT_MEMBER_BY_ID_200_DESC = "Successfully, updated a MEMBER";
    
    
}
