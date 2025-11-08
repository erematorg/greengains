package com.adjust.sdk;

import java.util.Map;
import org.json.JSONObject;

public class ResponseData {
    public ActivityKind activityKind;
    public ActivityPackage activityPackage;
    public String adid;
    public Long askIn;
    public AdjustAttribution attribution;
    public Long continueIn;
    public JSONObject jsonResponse;
    public String message;
    public String resolvedDeeplink;
    public Long retryIn;
    public Map<String, String> sendingParameters;
    public boolean success = false;
    public String timestamp;
    public TrackingState trackingState;
    public boolean willRetry = false;

    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3306a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.adjust.sdk.ActivityKind[] r0 = com.adjust.sdk.ActivityKind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f3306a = r0
                com.adjust.sdk.ActivityKind r1 = com.adjust.sdk.ActivityKind.SESSION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f3306a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.adjust.sdk.ActivityKind r1 = com.adjust.sdk.ActivityKind.CLICK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f3306a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.adjust.sdk.ActivityKind r1 = com.adjust.sdk.ActivityKind.ATTRIBUTION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f3306a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.adjust.sdk.ActivityKind r1 = com.adjust.sdk.ActivityKind.EVENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f3306a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.adjust.sdk.ActivityKind r1 = com.adjust.sdk.ActivityKind.PURCHASE_VERIFICATION     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.ResponseData.a.<clinit>():void");
        }
    }

    public static ResponseData buildResponseData(ActivityPackage activityPackage2, Map<String, String> map) {
        ActivityKind activityKind2 = activityPackage2.getActivityKind();
        int i3 = a.f3306a[activityKind2.ordinal()];
        ResponseData responseData = i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? i3 != 5 ? new ResponseData() : new PurchaseVerificationResponseData() : new EventResponseData(activityPackage2) : new AttributionResponseData() : new SdkClickResponseData() : new SessionResponseData(activityPackage2);
        responseData.activityKind = activityKind2;
        responseData.activityPackage = activityPackage2;
        responseData.sendingParameters = map;
        return responseData;
    }

    public String toString() {
        return Util.formatString("message:%s timestamp:%s json:%s", this.message, this.timestamp, this.jsonResponse);
    }
}
