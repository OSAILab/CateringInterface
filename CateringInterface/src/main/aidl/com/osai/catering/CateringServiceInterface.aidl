package com.osai.catering;
import com.osai.catering.CateringServiceCallback;

interface CateringServiceInterface {

    void show();

    void hide();

    void addProduct(in String sku,in String price, in String name);

    void deleteProduct(in String sku);

    void clearProduct();

    void setCallback(CateringServiceCallback callback);
}