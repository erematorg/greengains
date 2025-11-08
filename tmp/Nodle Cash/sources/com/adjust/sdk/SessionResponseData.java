package com.adjust.sdk;

import org.json.JSONObject;

public class SessionResponseData extends ResponseData {
    private String sdkPlatform;

    public SessionResponseData(ActivityPackage activityPackage) {
        this.sdkPlatform = Util.getSdkPrefixPlatform(activityPackage.getClientSdk());
    }

    public AdjustSessionFailure getFailureResponseData() {
        JSONObject jSONObject;
        if (this.success) {
            return null;
        }
        AdjustSessionFailure adjustSessionFailure = new AdjustSessionFailure();
        if ("unity".equals(this.sdkPlatform)) {
            String str = this.message;
            String str2 = "";
            if (str == null) {
                str = str2;
            }
            adjustSessionFailure.message = str;
            String str3 = this.timestamp;
            if (str3 == null) {
                str3 = str2;
            }
            adjustSessionFailure.timestamp = str3;
            String str4 = this.adid;
            if (str4 != null) {
                str2 = str4;
            }
            adjustSessionFailure.adid = str2;
            adjustSessionFailure.willRetry = this.willRetry;
            jSONObject = this.jsonResponse;
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
        } else {
            adjustSessionFailure.message = this.message;
            adjustSessionFailure.timestamp = this.timestamp;
            adjustSessionFailure.adid = this.adid;
            adjustSessionFailure.willRetry = this.willRetry;
            jSONObject = this.jsonResponse;
        }
        adjustSessionFailure.jsonResponse = jSONObject;
        return adjustSessionFailure;
    }

    public AdjustSessionSuccess getSuccessResponseData() {
        JSONObject jSONObject;
        if (!this.success) {
            return null;
        }
        AdjustSessionSuccess adjustSessionSuccess = new AdjustSessionSuccess();
        if ("unity".equals(this.sdkPlatform)) {
            String str = this.message;
            String str2 = "";
            if (str == null) {
                str = str2;
            }
            adjustSessionSuccess.message = str;
            String str3 = this.timestamp;
            if (str3 == null) {
                str3 = str2;
            }
            adjustSessionSuccess.timestamp = str3;
            String str4 = this.adid;
            if (str4 != null) {
                str2 = str4;
            }
            adjustSessionSuccess.adid = str2;
            jSONObject = this.jsonResponse;
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
        } else {
            adjustSessionSuccess.message = this.message;
            adjustSessionSuccess.timestamp = this.timestamp;
            adjustSessionSuccess.adid = this.adid;
            jSONObject = this.jsonResponse;
        }
        adjustSessionSuccess.jsonResponse = jSONObject;
        return adjustSessionSuccess;
    }
}
