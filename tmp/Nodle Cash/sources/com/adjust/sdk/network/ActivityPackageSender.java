package com.adjust.sdk.network;

import android.content.Context;
import android.net.Uri;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.constraintlayout.core.state.b;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.adjust.sdk.ActivityKind;
import com.adjust.sdk.ActivityPackage;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustFactory;
import com.adjust.sdk.AdjustSigner;
import com.adjust.sdk.ILogger;
import com.adjust.sdk.ResponseData;
import com.adjust.sdk.TrackingState;
import com.adjust.sdk.Util;
import com.adjust.sdk.network.IActivityPackageSender;
import com.adjust.sdk.network.UtilNetworking;
import com.adjust.sdk.scheduler.SingleThreadCachedScheduler;
import com.adjust.sdk.scheduler.ThreadExecutor;
import com.amplitude.api.Constants;
import com.google.common.net.HttpHeaders;
import com.reown.android.push.notifications.PushMessagingService;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityPackageSender implements IActivityPackageSender {
    private String basePath;
    private String clientSdk;
    private UtilNetworking.IConnectionOptions connectionOptions;
    private Context context;
    private ThreadExecutor executor = new SingleThreadCachedScheduler("ActivityPackageSender");
    private String gdprPath;
    private UtilNetworking.IHttpsURLConnectionProvider httpsURLConnectionProvider;
    private ILogger logger = AdjustFactory.getLogger();
    private String purchaseVerificationPath;
    private String subscriptionPath;
    private UrlStrategy urlStrategy;

    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ IActivityPackageSender.ResponseDataCallbackSubscriber f3322a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ ActivityPackage f3323b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Map f3324c;

        public a(IActivityPackageSender.ResponseDataCallbackSubscriber responseDataCallbackSubscriber, ActivityPackage activityPackage, Map map) {
            this.f3322a = responseDataCallbackSubscriber;
            this.f3323b = activityPackage;
            this.f3324c = map;
        }

        public final void run() {
            this.f3322a.onResponseDataCallback(ActivityPackageSender.this.sendActivityPackageSync(this.f3323b, this.f3324c));
        }
    }

    public ActivityPackageSender(String str, String str2, String str3, String str4, String str5, String str6, Context context2) {
        this.basePath = str2;
        this.gdprPath = str3;
        this.subscriptionPath = str4;
        this.purchaseVerificationPath = str5;
        this.clientSdk = str6;
        this.context = context2;
        this.urlStrategy = new UrlStrategy(AdjustFactory.getBaseUrl(), AdjustFactory.getGdprUrl(), AdjustFactory.getSubscriptionUrl(), AdjustFactory.getPurchaseVerificationUrl(), str);
        this.httpsURLConnectionProvider = AdjustFactory.getHttpsURLConnectionProvider();
        this.connectionOptions = AdjustFactory.getConnectionOptions();
    }

    private String buildAndExtractAuthorizationHeader(Map<String, String> map, ActivityKind activityKind) {
        String activityKind2 = activityKind.toString();
        String extractAdjSigningId = extractAdjSigningId(map);
        String extractSecretId = extractSecretId(map);
        String extractHeadersId = extractHeadersId(map);
        String extractSignature = extractSignature(map);
        String extractAlgorithm = extractAlgorithm(map);
        String extractNativeVersion = extractNativeVersion(map);
        String buildAuthorizationHeaderV2WithAdjSigningId = buildAuthorizationHeaderV2WithAdjSigningId(extractSignature, extractAdjSigningId, extractHeadersId, extractAlgorithm, extractNativeVersion);
        if (buildAuthorizationHeaderV2WithAdjSigningId != null) {
            return buildAuthorizationHeaderV2WithAdjSigningId;
        }
        String buildAuthorizationHeaderV2WithSecretId = buildAuthorizationHeaderV2WithSecretId(extractSignature, extractSecretId, extractHeadersId, extractAlgorithm, extractNativeVersion);
        return buildAuthorizationHeaderV2WithSecretId != null ? buildAuthorizationHeaderV2WithSecretId : buildAuthorizationHeaderV1(map, extractAppSecret(map), extractSecretId, activityKind2);
    }

    private String buildAuthorizationHeaderV1(Map<String, String> map, String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Map<String, String> signature = getSignature(map, str3, str);
        String formatString = Util.formatString("Signature %s,%s,%s,%s", Util.formatString("secret_id=\"%s\"", str2), Util.formatString("signature=\"%s\"", Util.sha256(signature.get("clear_signature"))), Util.formatString("algorithm=\"%s\"", "sha256"), Util.formatString("headers=\"%s\"", signature.get("fields")));
        this.logger.verbose("authorizationHeader: %s", formatString);
        return formatString;
    }

    private String buildAuthorizationHeaderV2WithAdjSigningId(String str, String str2, String str3, String str4, String str5) {
        if (str2 == null || str == null || str3 == null) {
            return null;
        }
        String formatString = Util.formatString("signature=\"%s\"", str);
        String formatString2 = Util.formatString("adj_signing_id=\"%s\"", str2);
        String formatString3 = Util.formatString("headers_id=\"%s\"", str3);
        if (str4 == null) {
            str4 = "adj1";
        }
        String formatString4 = Util.formatString("algorithm=\"%s\"", str4);
        if (str5 == null) {
            str5 = "";
        }
        String formatString5 = Util.formatString("Signature %s,%s,%s,%s,%s", formatString, formatString2, formatString4, formatString3, Util.formatString("native_version=\"%s\"", str5));
        this.logger.verbose("authorizationHeader: %s", formatString5);
        return formatString5;
    }

    private String buildAuthorizationHeaderV2WithSecretId(String str, String str2, String str3, String str4, String str5) {
        if (str2 == null || str == null || str3 == null) {
            return null;
        }
        String formatString = Util.formatString("signature=\"%s\"", str);
        String formatString2 = Util.formatString("secret_id=\"%s\"", str2);
        String formatString3 = Util.formatString("headers_id=\"%s\"", str3);
        if (str4 == null) {
            str4 = "adj1";
        }
        String formatString4 = Util.formatString("algorithm=\"%s\"", str4);
        if (str5 == null) {
            str5 = "";
        }
        String formatString5 = Util.formatString("Signature %s,%s,%s,%s,%s", formatString, formatString2, formatString4, formatString3, Util.formatString("native_version=\"%s\"", str5));
        this.logger.verbose("authorizationHeader: %s", formatString5);
        return formatString5;
    }

    private DataOutputStream configConnectionForGET(HttpsURLConnection httpsURLConnection) {
        httpsURLConnection.setRequestMethod(ShareTarget.METHOD_GET);
        return null;
    }

    private DataOutputStream configConnectionForPOST(HttpsURLConnection httpsURLConnection, Map<String, String> map, Map<String, String> map2) {
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        String generatePOSTBodyString = generatePOSTBodyString(map, map2);
        this.logger.verbose("Post body: %s", generatePOSTBodyString);
        if (generatePOSTBodyString == null) {
            return null;
        }
        DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
        dataOutputStream.writeBytes(generatePOSTBodyString);
        return dataOutputStream;
    }

    private String errorMessage(Throwable th, String str, ActivityPackage activityPackage) {
        return Util.formatString("%s. (%s)", activityPackage.getFailureMessage(), Util.getReasonString(str, th));
    }

    private static String extractAdjSigningId(Map<String, String> map) {
        return map.remove("adj_signing_id");
    }

    private static String extractAlgorithm(Map<String, String> map) {
        return map.remove("algorithm");
    }

    private static String extractAppSecret(Map<String, String> map) {
        return map.remove("app_secret");
    }

    private static String extractHeadersId(Map<String, String> map) {
        return map.remove("headers_id");
    }

    private static String extractNativeVersion(Map<String, String> map) {
        return map.remove("native_version");
    }

    private static String extractSecretId(Map<String, String> map) {
        return map.remove("secret_id");
    }

    private static String extractSignature(Map<String, String> map) {
        return map.remove("signature");
    }

    private String generatePOSTBodyString(Map<String, String> map, Map<String, String> map2) {
        if (map.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        injectParametersToPOSTStringBuilder(map, sb);
        injectParametersToPOSTStringBuilder(map2, sb);
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '&') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String generateUrlStringForGET(ActivityKind activityKind, String str, Map<String, String> map, Map<String, String> map2) {
        URL url = new URL(urlWithExtraPathByActivityKind(activityKind, this.urlStrategy.targetUrlByActivityKind(activityKind)));
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(url.getProtocol());
        builder.encodedAuthority(url.getAuthority());
        builder.path(url.getPath());
        builder.appendPath(str);
        for (Map.Entry next : map.entrySet()) {
            builder.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        if (map2 != null) {
            for (Map.Entry next2 : map2.entrySet()) {
                builder.appendQueryParameter((String) next2.getKey(), (String) next2.getValue());
            }
        }
        this.logger.debug("Making request to url: %s", builder.toString());
        return builder.build().toString();
    }

    private String generateUrlStringForPOST(ActivityKind activityKind, String str) {
        String formatString = Util.formatString("%s%s", urlWithExtraPathByActivityKind(activityKind, this.urlStrategy.targetUrlByActivityKind(activityKind)), str);
        this.logger.debug("Making request to url : %s", formatString);
        return formatString;
    }

    private Map<String, String> getSignature(Map<String, String> map, String str, String str2) {
        String validIdentifier = getValidIdentifier(map);
        String str3 = map.get("source");
        String str4 = map.get("payload");
        HashMap hashMap = new HashMap();
        hashMap.put("app_secret", str2);
        hashMap.put("created_at", map.get("created_at"));
        hashMap.put("activity_kind", str);
        hashMap.put(validIdentifier, map.get(validIdentifier));
        if (str3 != null) {
            hashMap.put("source", str3);
        }
        if (str4 != null) {
            hashMap.put("payload", str4);
        }
        String str5 = "";
        String str6 = str5;
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry.getValue() != null) {
                str5 = A.a.n(A.a.p(str5), (String) entry.getKey(), StringUtils.SPACE);
                StringBuilder p2 = A.a.p(str6);
                p2.append((String) entry.getValue());
                str6 = p2.toString();
            }
        }
        String y2 = b.y(str5, 1, 0);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("clear_signature", str6);
        hashMap2.put("fields", y2);
        return hashMap2;
    }

    private String getValidIdentifier(Map<String, String> map) {
        if (map.get("gps_adid") != null) {
            return "gps_adid";
        }
        if (map.get("fire_adid") != null) {
            return "fire_adid";
        }
        if (map.get("android_id") != null) {
            return "android_id";
        }
        if (map.get("android_uuid") != null) {
            return "android_uuid";
        }
        return null;
    }

    private void injectParametersToPOSTStringBuilder(Map<String, String> map, StringBuilder sb) {
        if (map != null && !map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                String encode = URLEncoder.encode((String) next.getKey(), "UTF-8");
                String str = (String) next.getValue();
                b.w(sb, encode, StickyVariantProvider.KEY_VALUE_DELIMITER, str != null ? URLEncoder.encode(str, "UTF-8") : "", "&");
            }
        }
    }

    private void localError(Throwable th, String str, ResponseData responseData, int i3) {
        String errorMessage = errorMessage(th, str, responseData.activityPackage);
        this.logger.error(errorMessage, new Object[0]);
        responseData.message = errorMessage;
        responseData.willRetry = false;
        responseData.activityPackage.addError(i3);
    }

    private void parseResponse(ResponseData responseData, String str) {
        JSONObject jSONObject;
        if (str.length() == 0) {
            this.logger.error("Empty response string", new Object[0]);
            return;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e3) {
            this.logger.error(errorMessage(e3, "Failed to parse JSON response", responseData.activityPackage), new Object[0]);
            jSONObject = null;
        }
        if (jSONObject != null) {
            responseData.jsonResponse = jSONObject;
            responseData.message = UtilNetworking.extractJsonString(jSONObject, PushMessagingService.KEY_MESSAGE);
            responseData.adid = UtilNetworking.extractJsonString(jSONObject, Constants.AMP_TRACKING_OPTION_ADID);
            responseData.timestamp = UtilNetworking.extractJsonString(jSONObject, "timestamp");
            String extractJsonString = UtilNetworking.extractJsonString(jSONObject, "tracking_state");
            if (extractJsonString != null && extractJsonString.equals("opted_out")) {
                responseData.trackingState = TrackingState.OPTED_OUT;
            }
            responseData.askIn = UtilNetworking.extractJsonLong(jSONObject, "ask_in");
            responseData.retryIn = UtilNetworking.extractJsonLong(jSONObject, "retry_in");
            responseData.continueIn = UtilNetworking.extractJsonLong(jSONObject, "continue_in");
            responseData.attribution = AdjustAttribution.fromJson(jSONObject.optJSONObject("attribution"), responseData.adid, Util.getSdkPrefixPlatform(this.clientSdk));
            responseData.resolvedDeeplink = UtilNetworking.extractJsonString(jSONObject, "resolved_click_url");
        }
    }

    private void remoteError(Throwable th, String str, ResponseData responseData, Integer num) {
        String n2 = A.a.n(new StringBuilder(), errorMessage(th, str, responseData.activityPackage), " Will retry later");
        this.logger.error(n2, new Object[0]);
        responseData.message = n2;
        responseData.willRetry = true;
        responseData.activityPackage.addError(num.intValue());
    }

    private boolean shouldRetryToSend(ResponseData responseData) {
        if (!responseData.willRetry) {
            this.logger.debug("Will not retry with current url strategy", new Object[0]);
            this.urlStrategy.resetAfterSuccess();
            return false;
        }
        boolean shouldRetryAfterFailure = this.urlStrategy.shouldRetryAfterFailure(responseData.activityKind);
        ILogger iLogger = this.logger;
        if (shouldRetryAfterFailure) {
            iLogger.error("Failed with current url strategy, but it will retry with new", new Object[0]);
            return true;
        }
        iLogger.error("Failed with current url strategy and it will not retry", new Object[0]);
        return false;
    }

    private void tryToGetResponse(ResponseData responseData) {
        DataOutputStream dataOutputStream = null;
        try {
            ActivityPackage activityPackage = responseData.activityPackage;
            HashMap hashMap = new HashMap(activityPackage.getParameters());
            Map<String, String> map = responseData.sendingParameters;
            String buildAndExtractAuthorizationHeader = buildAndExtractAuthorizationHeader(hashMap, activityPackage.getActivityKind());
            boolean z2 = true;
            boolean z3 = responseData.activityPackage.getActivityKind() == ActivityKind.ATTRIBUTION;
            HttpsURLConnection generateHttpsURLConnection = this.httpsURLConnectionProvider.generateHttpsURLConnection(new URL(z3 ? generateUrlStringForGET(activityPackage.getActivityKind(), activityPackage.getPath(), hashMap, map) : generateUrlStringForPOST(activityPackage.getActivityKind(), activityPackage.getPath())));
            this.connectionOptions.applyConnectionOptions(generateHttpsURLConnection, activityPackage.getClientSdk());
            if (buildAndExtractAuthorizationHeader != null) {
                generateHttpsURLConnection.setRequestProperty(HttpHeaders.AUTHORIZATION, buildAndExtractAuthorizationHeader);
            }
            DataOutputStream configConnectionForGET = z3 ? configConnectionForGET(generateHttpsURLConnection) : configConnectionForPOST(generateHttpsURLConnection, hashMap, map);
            Integer readConnectionResponse = readConnectionResponse(generateHttpsURLConnection, responseData);
            responseData.success = responseData.jsonResponse != null && responseData.retryIn == null && readConnectionResponse != null && readConnectionResponse.intValue() == 200;
            JSONObject jSONObject = responseData.jsonResponse;
            if (jSONObject != null) {
                if (responseData.retryIn == null) {
                    z2 = false;
                }
            }
            responseData.willRetry = z2;
            if (jSONObject == null) {
                responseData.activityPackage.addError(1000);
            } else if (responseData.retryIn != null) {
                responseData.activityPackage.addError(1001);
            }
            if (configConnectionForGET != null) {
                try {
                    configConnectionForGET.flush();
                    configConnectionForGET.close();
                } catch (IOException e3) {
                    this.logger.error(errorMessage(e3, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (UnsupportedEncodingException e4) {
            localError(e4, "Failed to encode parameters", responseData, 1002);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e5) {
                    this.logger.error(errorMessage(e5, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (MalformedURLException e6) {
            localError(e6, "Malformed URL", responseData, 1003);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e7) {
                    this.logger.error(errorMessage(e7, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (ProtocolException e8) {
            localError(e8, "Protocol Error", responseData, 1004);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e9) {
                    this.logger.error(errorMessage(e9, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (SocketTimeoutException e10) {
            remoteError(e10, "Request timed out", responseData, 1005);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e11) {
                    this.logger.error(errorMessage(e11, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (SSLHandshakeException e12) {
            remoteError(e12, "Certificate failed", responseData, 1006);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e13) {
                    this.logger.error(errorMessage(e13, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (IOException e14) {
            remoteError(e14, "Request failed", responseData, 1007);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e15) {
                    this.logger.error(errorMessage(e15, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
        } catch (Throwable th) {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e16) {
                    this.logger.error(errorMessage(e16, "Flushing and closing connection output stream", responseData.activityPackage), new Object[0]);
                }
            }
            throw th;
        }
    }

    private String urlWithExtraPathByActivityKind(ActivityKind activityKind, String str) {
        if (activityKind == ActivityKind.GDPR) {
            if (this.gdprPath == null) {
                return str;
            }
            StringBuilder p2 = A.a.p(str);
            p2.append(this.gdprPath);
            return p2.toString();
        } else if (activityKind == ActivityKind.SUBSCRIPTION) {
            if (this.subscriptionPath == null) {
                return str;
            }
            StringBuilder p3 = A.a.p(str);
            p3.append(this.subscriptionPath);
            return p3.toString();
        } else if (activityKind == ActivityKind.PURCHASE_VERIFICATION) {
            if (this.purchaseVerificationPath == null) {
                return str;
            }
            StringBuilder p4 = A.a.p(str);
            p4.append(this.purchaseVerificationPath);
            return p4.toString();
        } else if (this.basePath == null) {
            return str;
        } else {
            StringBuilder p5 = A.a.p(str);
            p5.append(this.basePath);
            return p5.toString();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (r7 == null) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer readConnectionResponse(javax.net.ssl.HttpsURLConnection r7, com.adjust.sdk.ResponseData r8) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            r2 = 0
            r7.connect()     // Catch:{ IOException -> 0x001e }
            int r3 = r7.getResponseCode()     // Catch:{ IOException -> 0x001e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x001e }
            r4 = 400(0x190, float:5.6E-43)
            if (r3 < r4) goto L_0x0020
            java.io.InputStream r3 = r7.getErrorStream()     // Catch:{ IOException -> 0x001e }
            goto L_0x0024
        L_0x001b:
            r6 = move-exception
            goto L_0x009d
        L_0x001e:
            r3 = move-exception
            goto L_0x0038
        L_0x0020:
            java.io.InputStream r3 = r7.getInputStream()     // Catch:{ IOException -> 0x001e }
        L_0x0024:
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x001e }
            r4.<init>(r3)     // Catch:{ IOException -> 0x001e }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x001e }
            r3.<init>(r4)     // Catch:{ IOException -> 0x001e }
        L_0x002e:
            java.lang.String r4 = r3.readLine()     // Catch:{ IOException -> 0x001e }
            if (r4 == 0) goto L_0x0049
            r0.append(r4)     // Catch:{ IOException -> 0x001e }
            goto L_0x002e
        L_0x0038:
            java.lang.String r4 = "Connecting and reading response"
            com.adjust.sdk.ActivityPackage r5 = r8.activityPackage     // Catch:{ all -> 0x001b }
            java.lang.String r3 = r6.errorMessage(r3, r4, r5)     // Catch:{ all -> 0x001b }
            com.adjust.sdk.ILogger r4 = r6.logger     // Catch:{ all -> 0x001b }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ all -> 0x001b }
            r4.error(r3, r5)     // Catch:{ all -> 0x001b }
            if (r7 == 0) goto L_0x004c
        L_0x0049:
            r7.disconnect()
        L_0x004c:
            int r7 = r0.length()
            if (r7 != 0) goto L_0x005c
            com.adjust.sdk.ILogger r6 = r6.logger
            java.lang.Object[] r7 = new java.lang.Object[r1]
            java.lang.String r8 = "Empty response string buffer"
            r6.error(r8, r7)
            return r2
        L_0x005c:
            int r7 = r2.intValue()
            r3 = 429(0x1ad, float:6.01E-43)
            if (r7 != r3) goto L_0x006e
            com.adjust.sdk.ILogger r6 = r6.logger
            java.lang.Object[] r7 = new java.lang.Object[r1]
            java.lang.String r8 = "Too frequent requests to the endpoint (429)"
            r6.error(r8, r7)
            return r2
        L_0x006e:
            java.lang.String r7 = r0.toString()
            com.adjust.sdk.ILogger r0 = r6.logger
            java.lang.Object[] r1 = new java.lang.Object[]{r7}
            java.lang.String r3 = "Response string: %s"
            r0.debug(r3, r1)
            r6.parseResponse(r8, r7)
            java.lang.String r7 = r8.message
            if (r7 != 0) goto L_0x0085
            return r2
        L_0x0085:
            int r8 = r2.intValue()
            r0 = 200(0xc8, float:2.8E-43)
            java.lang.String r1 = "Response message: %s"
            com.adjust.sdk.ILogger r6 = r6.logger
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            if (r8 != r0) goto L_0x0099
            r6.info(r1, r7)
            goto L_0x009c
        L_0x0099:
            r6.error(r1, r7)
        L_0x009c:
            return r2
        L_0x009d:
            if (r7 == 0) goto L_0x00a2
            r7.disconnect()
        L_0x00a2:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.network.ActivityPackageSender.readConnectionResponse(javax.net.ssl.HttpsURLConnection, com.adjust.sdk.ResponseData):java.lang.Integer");
    }

    public void sendActivityPackage(ActivityPackage activityPackage, Map<String, String> map, IActivityPackageSender.ResponseDataCallbackSubscriber responseDataCallbackSubscriber) {
        this.executor.submit(new a(responseDataCallbackSubscriber, activityPackage, map));
    }

    public ResponseData sendActivityPackageSync(ActivityPackage activityPackage, Map<String, String> map) {
        ResponseData buildResponseData;
        AdjustSigner.sign(activityPackage.getParameters(), activityPackage.getActivityKind().toString(), activityPackage.getClientSdk(), this.context, this.logger);
        do {
            buildResponseData = ResponseData.buildResponseData(activityPackage, map);
            tryToGetResponse(buildResponseData);
        } while (shouldRetryToSend(buildResponseData));
        return buildResponseData;
    }
}
