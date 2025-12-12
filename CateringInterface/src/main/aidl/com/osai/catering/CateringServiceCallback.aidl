package com.osai.catering;

interface CateringServiceCallback {
    void onShow();
    void onHide();
    void onOutputProduct(String sku);
}