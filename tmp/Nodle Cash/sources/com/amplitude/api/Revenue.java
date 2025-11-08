package com.amplitude.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Revenue {
    public static final String TAG = "com.amplitude.api.Revenue";
    private static AmplitudeLog logger = AmplitudeLog.getLogger();
    protected Double price = null;
    protected String productId = null;
    protected JSONObject properties = null;
    protected int quantity = 1;
    protected String receipt = null;
    protected String receiptSig = null;
    protected String revenueType = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Revenue revenue = (Revenue) obj;
        if (this.quantity != revenue.quantity) {
            return false;
        }
        String str = this.productId;
        if (str == null ? revenue.productId != null : !str.equals(revenue.productId)) {
            return false;
        }
        Double d2 = this.price;
        if (d2 == null ? revenue.price != null : !d2.equals(revenue.price)) {
            return false;
        }
        String str2 = this.revenueType;
        if (str2 == null ? revenue.revenueType != null : !str2.equals(revenue.revenueType)) {
            return false;
        }
        String str3 = this.receipt;
        if (str3 == null ? revenue.receipt != null : !str3.equals(revenue.receipt)) {
            return false;
        }
        String str4 = this.receiptSig;
        if (str4 == null ? revenue.receiptSig != null : !str4.equals(revenue.receiptSig)) {
            return false;
        }
        JSONObject jSONObject = this.properties;
        if (jSONObject != null) {
            if (Utils.compareJSONObjects(jSONObject, revenue.properties)) {
                return true;
            }
        } else if (revenue.properties == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.productId;
        int i3 = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.quantity) * 31;
        Double d2 = this.price;
        int hashCode2 = (hashCode + (d2 != null ? d2.hashCode() : 0)) * 31;
        String str2 = this.revenueType;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.receipt;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.receiptSig;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        JSONObject jSONObject = this.properties;
        if (jSONObject != null) {
            i3 = jSONObject.hashCode();
        }
        return hashCode5 + i3;
    }

    public boolean isValidRevenue() {
        if (this.price != null) {
            return true;
        }
        logger.w(TAG, "Invalid revenue, need to set price");
        return false;
    }

    public Revenue setEventProperties(JSONObject jSONObject) {
        this.properties = Utils.cloneJSONObject(jSONObject);
        return this;
    }

    public Revenue setPrice(double d2) {
        this.price = Double.valueOf(d2);
        return this;
    }

    public Revenue setProductId(String str) {
        if (Utils.isEmptyString(str)) {
            logger.w(TAG, "Invalid empty productId");
            return this;
        }
        this.productId = str;
        return this;
    }

    public Revenue setQuantity(int i3) {
        this.quantity = i3;
        return this;
    }

    public Revenue setReceipt(String str, String str2) {
        this.receipt = str;
        this.receiptSig = str2;
        return this;
    }

    public Revenue setRevenueProperties(JSONObject jSONObject) {
        logger.w(TAG, "setRevenueProperties is deprecated, please use setEventProperties instead");
        return setEventProperties(jSONObject);
    }

    public Revenue setRevenueType(String str) {
        this.revenueType = str;
        return this;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = this.properties;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put(Constants.AMP_REVENUE_PRODUCT_ID, this.productId);
            jSONObject.put(Constants.AMP_REVENUE_QUANTITY, this.quantity);
            jSONObject.put(Constants.AMP_REVENUE_PRICE, this.price);
            jSONObject.put(Constants.AMP_REVENUE_REVENUE_TYPE, this.revenueType);
            jSONObject.put(Constants.AMP_REVENUE_RECEIPT, this.receipt);
            jSONObject.put(Constants.AMP_REVENUE_RECEIPT_SIG, this.receiptSig);
        } catch (JSONException e3) {
            AmplitudeLog amplitudeLog = logger;
            String obj = e3.toString();
            amplitudeLog.e(TAG, "Failed to convert revenue object to JSON: " + obj);
        }
        return jSONObject;
    }
}
