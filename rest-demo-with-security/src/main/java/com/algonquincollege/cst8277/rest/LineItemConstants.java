package com.algonquincollege.cst8277.rest;

public interface LineItemConstants {
    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String LINEITEM_RESOURCE_NAME =  "lineitem";
    public static final String LINEITEM_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String LINEITEM_RESOURCE_PATH_ID_PATH =  "/{" + LINEITEM_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_LINEITEMS_OP_DESC = "Retrieves list of LINEITEMs";
    public static final String GET_LINEITEMS_OP_200_DESC = "Successful, returning LINEITEMs";
    public static final String GET_LINEITEMS_OP_403_DESC = "Only admin's can list all LINEITEMs";
    public static final String GET_LINEITEMS_OP_404_DESC = "Could not find LINEITEMs";
    public static final String GET_LINEITEMS_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_LINEITEMS_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_LINEITEM_BY_ID_OP_DESC = "Retrieve specific LINEITEM";
    public static final String GET_LINEITEM_BY_ID_OP_200_DESC = "Successful, returning requested LINEITEM";
    public static final String GET_LINEITEM_BY_ID_OP_403_DESC = "Only user's can retrieve a specific LINEITEM";
    public static final String GET_LINEITEM_BY_ID_OP_404_DESC = "Requested LINEITEM not found";
    public static final String GET_LINEITEMS_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_LINEITEM_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
}
