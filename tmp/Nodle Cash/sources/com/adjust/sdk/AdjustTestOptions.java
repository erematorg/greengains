package com.adjust.sdk;

import android.content.Context;

public class AdjustTestOptions {
    public String basePath;
    public String baseUrl;
    public Context context;
    public Boolean disableSigning;
    public Boolean enableSigning;
    public String gdprPath;
    public String gdprUrl;
    public Boolean noBackoffWait;
    public String purchaseVerificationPath;
    public String purchaseVerificationUrl;
    public Long sessionIntervalInMilliseconds;
    public String subscriptionPath;
    public String subscriptionUrl;
    public Long subsessionIntervalInMilliseconds;
    public Boolean teardown;
    public Long timerIntervalInMilliseconds;
    public Long timerStartInMilliseconds;
    public Boolean tryInstallReferrer = Boolean.FALSE;
}
