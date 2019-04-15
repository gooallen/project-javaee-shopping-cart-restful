//package com.algonquincollege.cst8277.rest;
//
//public interface ProductConstants {
//    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
//    public static final String SUFFIX_JSON_MSG = "\"}";
//
//    public static final String PRIMARY_KEY_DESC = "primary key";
//    public static final String PRODUCT_RESOURCE_NAME =  "product";
//    public static final String PRODUCT_RESOURCE_PATH_ID_ELEMENT =  "id";
//    public static final String PRODUCT_RESOURCE_PATH_ID_PATH =  "/{" + PRODUCT_RESOURCE_PATH_ID_ELEMENT + "}";
//
//    public static final String GET_PRODUCT_OP_DESC = "Retrieves list of product";
//    public static final String GET_PRODUCT_OP_200_DESC = "Successful, returning product";
//    public static final String GET_PRODUCT_OP_403_DESC = "Only admin's can list all product";
//    public static final String GET_PRODUCT_OP_404_DESC = "Could not find product";
//    public static final String GET_PRODUCT_OP_403_JSON_MSG =
//        PREFIX_JSON_MSG + GET_PRODUCT_OP_403_DESC + SUFFIX_JSON_MSG;
//
//    public static final String GET_PRODUCT_BY_ID_OP_DESC = "Retrieve specific product";
//    public static final String GET_PRODUCT_BY_ID_OP_200_DESC = "Successful, returning requested product";
//    public static final String GET_PRODUCT_BY_ID_OP_403_DESC = "Only user's can retrieve a specific product";
//    public static final String GET_PRODUCT_BY_ID_OP_404_DESC = "Requested product not found";
//    public static final String GET_PRODUCT_OP_403_DESC_JSON_MSG =
//        PREFIX_JSON_MSG + GET_PRODUCT_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
//}
package com.algonquincollege.cst8277.rest;

public interface ProductConstants {
    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String PRODUCT_RESOURCE_NAME =  "product";
    public static final String PRODUCT_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String PRODUCT_RESOURCE_PATH_ID_PATH =  "/{" + PRODUCT_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_PRODUCT_OP_DESC = "Retrieves list of product";
    public static final String GET_PRODUCT_OP_200_DESC = "Successful, returning product";
    public static final String GET_PRODUCT_OP_403_DESC = "Only admin's can list all product";
    public static final String GET_PRODUCT_OP_404_DESC = "Could not find product";
    public static final String GET_PRODUCT_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_PRODUCT_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_PRODUCT_BY_ID_OP_DESC = "Retrieve specific product";
    public static final String GET_PRODUCT_BY_ID_OP_200_DESC = "Successful, returning requested product";
    public static final String GET_PRODUCT_BY_ID_OP_403_DESC = "Only user's can retrieve a specific product";
    public static final String GET_PRODUCT_BY_ID_OP_404_DESC = "Requested product not found";
    public static final String GET_PRODUCT_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_PRODUCT_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
}