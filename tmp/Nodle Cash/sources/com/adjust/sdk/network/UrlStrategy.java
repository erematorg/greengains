package com.adjust.sdk.network;

import com.adjust.sdk.ActivityKind;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UrlStrategy {
    private static final String BASE_URL_CHINA = "https://app.adjust.world";
    private static final String BASE_URL_CN = "https://app.adjust.cn";
    private static final String BASE_URL_EU = "https://app.eu.adjust.com";
    private static final String BASE_URL_INDIA = "https://app.adjust.net.in";
    private static final String BASE_URL_TR = "https://app.tr.adjust.com";
    private static final String BASE_URL_US = "https://app.us.adjust.com";
    private static final String GDPR_URL_CHINA = "https://gdpr.adjust.world";
    private static final String GDPR_URL_CN = "https://gdpr.adjust.cn";
    private static final String GDPR_URL_EU = "https://gdpr.eu.adjust.com";
    private static final String GDPR_URL_INDIA = "https://gdpr.adjust.net.in";
    private static final String GDPR_URL_TR = "https://gdpr.tr.adjust.com";
    private static final String GDPR_URL_US = "https://gdpr.us.adjust.com";
    private static final String PURCHASE_VERIFICATION_URL_CHINA = "https://ssrv.adjust.world";
    private static final String PURCHASE_VERIFICATION_URL_CN = "https://ssrv.adjust.cn";
    private static final String PURCHASE_VERIFICATION_URL_EU = "https://ssrv.eu.adjust.com";
    private static final String PURCHASE_VERIFICATION_URL_INDIA = "https://ssrv.adjust.net.in";
    private static final String PURCHASE_VERIFICATION_URL_TR = "https://ssrv.tr.adjust.com";
    private static final String PURCHASE_VERIFICATION_URL_US = "https://ssrv.us.adjust.com";
    private static final String SUBSCRIPTION_URL_CHINA = "https://subscription.adjust.world";
    private static final String SUBSCRIPTION_URL_CN = "https://subscription.adjust.cn";
    private static final String SUBSCRIPTION_URL_EU = "https://subscription.eu.adjust.com";
    private static final String SUBSCRIPTION_URL_INDIA = "https://subscription.adjust.net.in";
    private static final String SUBSCRIPTION_URL_TR = "https://subscription.tr.adjust.com";
    private static final String SUBSCRIPTION_URL_US = "https://subscription.us.adjust.com";
    final List<String> baseUrlChoicesList;
    private final String baseUrlOverwrite;
    int choiceIndex = 0;
    final List<String> gdprUrlChoicesList;
    private final String gdprUrlOverwrite;
    final List<String> purchaseVerificationUrlChoicesList;
    private final String purchaseVerificationUrlOverwrite;
    int startingChoiceIndex = 0;
    final List<String> subscriptionUrlChoicesList;
    private final String subscriptionUrlOverwrite;
    boolean wasLastAttemptSuccess = false;
    boolean wasLastAttemptWithOverwrittenUrl = false;

    public UrlStrategy(String str, String str2, String str3, String str4, String str5) {
        this.baseUrlOverwrite = str;
        this.gdprUrlOverwrite = str2;
        this.subscriptionUrlOverwrite = str3;
        this.purchaseVerificationUrlOverwrite = str4;
        this.baseUrlChoicesList = baseUrlChoices(str5);
        this.gdprUrlChoicesList = gdprUrlChoices(str5);
        this.subscriptionUrlChoicesList = subscriptionUrlChoices(str5);
        this.purchaseVerificationUrlChoicesList = purchaseVerificationUrlChoices(str5);
    }

    private static List<String> baseUrlChoices(String str) {
        return AdjustConfig.URL_STRATEGY_INDIA.equals(str) ? Arrays.asList(new String[]{BASE_URL_INDIA, Constants.BASE_URL}) : AdjustConfig.URL_STRATEGY_CHINA.equals(str) ? Arrays.asList(new String[]{BASE_URL_CHINA, Constants.BASE_URL}) : AdjustConfig.URL_STRATEGY_CN.equals(str) ? Arrays.asList(new String[]{BASE_URL_CN, Constants.BASE_URL}) : AdjustConfig.URL_STRATEGY_CN_ONLY.equals(str) ? Arrays.asList(new String[]{BASE_URL_CN}) : AdjustConfig.DATA_RESIDENCY_EU.equals(str) ? Collections.singletonList(BASE_URL_EU) : AdjustConfig.DATA_RESIDENCY_TR.equals(str) ? Collections.singletonList(BASE_URL_TR) : AdjustConfig.DATA_RESIDENCY_US.equals(str) ? Collections.singletonList(BASE_URL_US) : Arrays.asList(new String[]{Constants.BASE_URL, BASE_URL_INDIA, BASE_URL_CHINA});
    }

    private static List<String> gdprUrlChoices(String str) {
        return AdjustConfig.URL_STRATEGY_INDIA.equals(str) ? Arrays.asList(new String[]{GDPR_URL_INDIA, Constants.GDPR_URL}) : AdjustConfig.URL_STRATEGY_CHINA.equals(str) ? Arrays.asList(new String[]{GDPR_URL_CHINA, Constants.GDPR_URL}) : AdjustConfig.URL_STRATEGY_CN.equals(str) ? Arrays.asList(new String[]{GDPR_URL_CN, Constants.GDPR_URL}) : AdjustConfig.URL_STRATEGY_CN_ONLY.equals(str) ? Arrays.asList(new String[]{GDPR_URL_CN}) : AdjustConfig.DATA_RESIDENCY_EU.equals(str) ? Collections.singletonList(GDPR_URL_EU) : AdjustConfig.DATA_RESIDENCY_TR.equals(str) ? Collections.singletonList(GDPR_URL_TR) : AdjustConfig.DATA_RESIDENCY_US.equals(str) ? Collections.singletonList(GDPR_URL_US) : Arrays.asList(new String[]{Constants.GDPR_URL, GDPR_URL_INDIA, GDPR_URL_CHINA});
    }

    private static List<String> purchaseVerificationUrlChoices(String str) {
        return AdjustConfig.URL_STRATEGY_INDIA.equals(str) ? Arrays.asList(new String[]{PURCHASE_VERIFICATION_URL_INDIA, Constants.PURCHASE_VERIFICATION_URL}) : AdjustConfig.URL_STRATEGY_CHINA.equals(str) ? Arrays.asList(new String[]{PURCHASE_VERIFICATION_URL_CHINA, Constants.PURCHASE_VERIFICATION_URL}) : AdjustConfig.URL_STRATEGY_CN.equals(str) ? Arrays.asList(new String[]{PURCHASE_VERIFICATION_URL_CN, Constants.PURCHASE_VERIFICATION_URL}) : AdjustConfig.URL_STRATEGY_CN_ONLY.equals(str) ? Arrays.asList(new String[]{PURCHASE_VERIFICATION_URL_CN}) : AdjustConfig.DATA_RESIDENCY_EU.equals(str) ? Collections.singletonList(PURCHASE_VERIFICATION_URL_EU) : AdjustConfig.DATA_RESIDENCY_TR.equals(str) ? Collections.singletonList(PURCHASE_VERIFICATION_URL_TR) : AdjustConfig.DATA_RESIDENCY_US.equals(str) ? Collections.singletonList(PURCHASE_VERIFICATION_URL_US) : Arrays.asList(new String[]{Constants.PURCHASE_VERIFICATION_URL, PURCHASE_VERIFICATION_URL_INDIA, PURCHASE_VERIFICATION_URL_CHINA});
    }

    private static List<String> subscriptionUrlChoices(String str) {
        return AdjustConfig.URL_STRATEGY_INDIA.equals(str) ? Arrays.asList(new String[]{SUBSCRIPTION_URL_INDIA, Constants.SUBSCRIPTION_URL}) : AdjustConfig.URL_STRATEGY_CHINA.equals(str) ? Arrays.asList(new String[]{SUBSCRIPTION_URL_CHINA, Constants.SUBSCRIPTION_URL}) : AdjustConfig.URL_STRATEGY_CN.equals(str) ? Arrays.asList(new String[]{SUBSCRIPTION_URL_CN, Constants.SUBSCRIPTION_URL}) : AdjustConfig.URL_STRATEGY_CN_ONLY.equals(str) ? Arrays.asList(new String[]{SUBSCRIPTION_URL_CN}) : AdjustConfig.DATA_RESIDENCY_EU.equals(str) ? Collections.singletonList(SUBSCRIPTION_URL_EU) : AdjustConfig.DATA_RESIDENCY_TR.equals(str) ? Collections.singletonList(SUBSCRIPTION_URL_TR) : AdjustConfig.DATA_RESIDENCY_US.equals(str) ? Collections.singletonList(SUBSCRIPTION_URL_US) : Arrays.asList(new String[]{Constants.SUBSCRIPTION_URL, SUBSCRIPTION_URL_INDIA, SUBSCRIPTION_URL_CHINA});
    }

    public void resetAfterSuccess() {
        this.startingChoiceIndex = this.choiceIndex;
        this.wasLastAttemptSuccess = true;
    }

    public boolean shouldRetryAfterFailure(ActivityKind activityKind) {
        this.wasLastAttemptSuccess = false;
        if (this.wasLastAttemptWithOverwrittenUrl) {
            return false;
        }
        int size = (this.choiceIndex + 1) % (activityKind == ActivityKind.GDPR ? this.gdprUrlChoicesList : activityKind == ActivityKind.SUBSCRIPTION ? this.subscriptionUrlChoicesList : activityKind == ActivityKind.PURCHASE_VERIFICATION ? this.purchaseVerificationUrlChoicesList : this.baseUrlChoicesList).size();
        this.choiceIndex = size;
        return size != this.startingChoiceIndex;
    }

    public String targetUrlByActivityKind(ActivityKind activityKind) {
        List<String> list;
        if (activityKind == ActivityKind.GDPR) {
            String str = this.gdprUrlOverwrite;
            if (str != null) {
                this.wasLastAttemptWithOverwrittenUrl = true;
                return str;
            }
            this.wasLastAttemptWithOverwrittenUrl = false;
            list = this.gdprUrlChoicesList;
        } else if (activityKind == ActivityKind.SUBSCRIPTION) {
            String str2 = this.subscriptionUrlOverwrite;
            if (str2 != null) {
                this.wasLastAttemptWithOverwrittenUrl = true;
                return str2;
            }
            this.wasLastAttemptWithOverwrittenUrl = false;
            list = this.subscriptionUrlChoicesList;
        } else if (activityKind == ActivityKind.PURCHASE_VERIFICATION) {
            String str3 = this.purchaseVerificationUrlOverwrite;
            if (str3 != null) {
                this.wasLastAttemptWithOverwrittenUrl = true;
                return str3;
            }
            this.wasLastAttemptWithOverwrittenUrl = false;
            list = this.purchaseVerificationUrlChoicesList;
        } else {
            String str4 = this.baseUrlOverwrite;
            if (str4 != null) {
                this.wasLastAttemptWithOverwrittenUrl = true;
                return str4;
            }
            this.wasLastAttemptWithOverwrittenUrl = false;
            list = this.baseUrlChoicesList;
        }
        return list.get(this.choiceIndex);
    }
}
