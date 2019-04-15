package com.algonquincollege.cst8277.rest;

public interface CartConstants {
    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String CART_RESOURCE_NAME =  "cart";
    public static final String CART_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String CART_RESOURCE_PATH_ID_PATH =  "/{" + CART_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_CARTS_OP_DESC = "Retrieves list of CARTs";
    public static final String GET_CARTS_OP_200_DESC = "Successful, returning CARTs";
    public static final String GET_CARTS_OP_403_DESC = "Only admin's can list all CARTs";
    public static final String GET_CARTS_OP_404_DESC = "Could not find CARTs";
    public static final String GET_CARTS_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_CARTS_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_CART_BY_ID_OP_DESC = "Retrieve specific CART";
    public static final String GET_CART_BY_ID_OP_200_DESC = "Successful, returning requested CART";
    public static final String GET_CART_BY_ID_OP_403_DESC = "Only user's can retrieve a specific CART";
    public static final String GET_CART_BY_ID_OP_404_DESC = "Requested CART not found";
    public static final String GET_CARTS_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_CART_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
}
