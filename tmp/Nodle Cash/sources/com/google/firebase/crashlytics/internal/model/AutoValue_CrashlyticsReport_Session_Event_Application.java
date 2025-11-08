package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_Application extends CrashlyticsReport.Session.Event.Application {
    private final List<CrashlyticsReport.Session.Event.Application.ProcessDetails> appProcessDetails;
    private final Boolean background;
    private final CrashlyticsReport.Session.Event.Application.ProcessDetails currentProcessDetails;
    private final List<CrashlyticsReport.CustomAttribute> customAttributes;
    private final CrashlyticsReport.Session.Event.Application.Execution execution;
    private final List<CrashlyticsReport.CustomAttribute> internalKeys;
    private final int uiOrientation;

    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Builder {
        private List<CrashlyticsReport.Session.Event.Application.ProcessDetails> appProcessDetails;
        private Boolean background;
        private CrashlyticsReport.Session.Event.Application.ProcessDetails currentProcessDetails;
        private List<CrashlyticsReport.CustomAttribute> customAttributes;
        private CrashlyticsReport.Session.Event.Application.Execution execution;
        private List<CrashlyticsReport.CustomAttribute> internalKeys;
        private byte set$0;
        private int uiOrientation;

        public CrashlyticsReport.Session.Event.Application build() {
            CrashlyticsReport.Session.Event.Application.Execution execution2;
            if (this.set$0 == 1 && (execution2 = this.execution) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application(execution2, this.customAttributes, this.internalKeys, this.background, this.currentProcessDetails, this.appProcessDetails, this.uiOrientation);
            }
            StringBuilder sb = new StringBuilder();
            if (this.execution == null) {
                sb.append(" execution");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" uiOrientation");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Event.Application.Builder setAppProcessDetails(@Nullable List<CrashlyticsReport.Session.Event.Application.ProcessDetails> list) {
            this.appProcessDetails = list;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Builder setBackground(@Nullable Boolean bool) {
            this.background = bool;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Builder setCurrentProcessDetails(@Nullable CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails) {
            this.currentProcessDetails = processDetails;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Builder setCustomAttributes(List<CrashlyticsReport.CustomAttribute> list) {
            this.customAttributes = list;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Builder setExecution(CrashlyticsReport.Session.Event.Application.Execution execution2) {
            if (execution2 != null) {
                this.execution = execution2;
                return this;
            }
            throw new NullPointerException("Null execution");
        }

        public CrashlyticsReport.Session.Event.Application.Builder setInternalKeys(List<CrashlyticsReport.CustomAttribute> list) {
            this.internalKeys = list;
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Builder setUiOrientation(int i3) {
            this.uiOrientation = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public Builder() {
        }

        private Builder(CrashlyticsReport.Session.Event.Application application) {
            this.execution = application.getExecution();
            this.customAttributes = application.getCustomAttributes();
            this.internalKeys = application.getInternalKeys();
            this.background = application.getBackground();
            this.currentProcessDetails = application.getCurrentProcessDetails();
            this.appProcessDetails = application.getAppProcessDetails();
            this.uiOrientation = application.getUiOrientation();
            this.set$0 = 1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r1 = r4.internalKeys;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        r1 = r4.background;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        r1 = r4.currentProcessDetails;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        r1 = r4.appProcessDetails;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r1 = r4.customAttributes;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
            r2 = 0
            if (r1 == 0) goto L_0x008b
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application r5 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application) r5
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution r1 = r4.execution
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution r3 = r5.getExecution()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
            java.util.List<com.google.firebase.crashlytics.internal.model.CrashlyticsReport$CustomAttribute> r1 = r4.customAttributes
            if (r1 != 0) goto L_0x0022
            java.util.List r1 = r5.getCustomAttributes()
            if (r1 != 0) goto L_0x0089
            goto L_0x002c
        L_0x0022:
            java.util.List r3 = r5.getCustomAttributes()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x002c:
            java.util.List<com.google.firebase.crashlytics.internal.model.CrashlyticsReport$CustomAttribute> r1 = r4.internalKeys
            if (r1 != 0) goto L_0x0037
            java.util.List r1 = r5.getInternalKeys()
            if (r1 != 0) goto L_0x0089
            goto L_0x0041
        L_0x0037:
            java.util.List r3 = r5.getInternalKeys()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x0041:
            java.lang.Boolean r1 = r4.background
            if (r1 != 0) goto L_0x004c
            java.lang.Boolean r1 = r5.getBackground()
            if (r1 != 0) goto L_0x0089
            goto L_0x0056
        L_0x004c:
            java.lang.Boolean r3 = r5.getBackground()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x0056:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$ProcessDetails r1 = r4.currentProcessDetails
            if (r1 != 0) goto L_0x0061
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$ProcessDetails r1 = r5.getCurrentProcessDetails()
            if (r1 != 0) goto L_0x0089
            goto L_0x006b
        L_0x0061:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$ProcessDetails r3 = r5.getCurrentProcessDetails()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x006b:
            java.util.List<com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$ProcessDetails> r1 = r4.appProcessDetails
            if (r1 != 0) goto L_0x0076
            java.util.List r1 = r5.getAppProcessDetails()
            if (r1 != 0) goto L_0x0089
            goto L_0x0080
        L_0x0076:
            java.util.List r3 = r5.getAppProcessDetails()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x0080:
            int r4 = r4.uiOrientation
            int r5 = r5.getUiOrientation()
            if (r4 != r5) goto L_0x0089
            goto L_0x008a
        L_0x0089:
            r0 = r2
        L_0x008a:
            return r0
        L_0x008b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application.equals(java.lang.Object):boolean");
    }

    @Nullable
    public List<CrashlyticsReport.Session.Event.Application.ProcessDetails> getAppProcessDetails() {
        return this.appProcessDetails;
    }

    @Nullable
    public Boolean getBackground() {
        return this.background;
    }

    @Nullable
    public CrashlyticsReport.Session.Event.Application.ProcessDetails getCurrentProcessDetails() {
        return this.currentProcessDetails;
    }

    @Nullable
    public List<CrashlyticsReport.CustomAttribute> getCustomAttributes() {
        return this.customAttributes;
    }

    @NonNull
    public CrashlyticsReport.Session.Event.Application.Execution getExecution() {
        return this.execution;
    }

    @Nullable
    public List<CrashlyticsReport.CustomAttribute> getInternalKeys() {
        return this.internalKeys;
    }

    public int getUiOrientation() {
        return this.uiOrientation;
    }

    public int hashCode() {
        int hashCode = (this.execution.hashCode() ^ 1000003) * 1000003;
        List<CrashlyticsReport.CustomAttribute> list = this.customAttributes;
        int i3 = 0;
        int hashCode2 = (hashCode ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List<CrashlyticsReport.CustomAttribute> list2 = this.internalKeys;
        int hashCode3 = (hashCode2 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        Boolean bool = this.background;
        int hashCode4 = (hashCode3 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails = this.currentProcessDetails;
        int hashCode5 = (hashCode4 ^ (processDetails == null ? 0 : processDetails.hashCode())) * 1000003;
        List<CrashlyticsReport.Session.Event.Application.ProcessDetails> list3 = this.appProcessDetails;
        if (list3 != null) {
            i3 = list3.hashCode();
        }
        return this.uiOrientation ^ ((hashCode5 ^ i3) * 1000003);
    }

    public CrashlyticsReport.Session.Event.Application.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Application{execution=");
        sb.append(this.execution);
        sb.append(", customAttributes=");
        sb.append(this.customAttributes);
        sb.append(", internalKeys=");
        sb.append(this.internalKeys);
        sb.append(", background=");
        sb.append(this.background);
        sb.append(", currentProcessDetails=");
        sb.append(this.currentProcessDetails);
        sb.append(", appProcessDetails=");
        sb.append(this.appProcessDetails);
        sb.append(", uiOrientation=");
        return A.a.m(sb, StringSubstitutor.DEFAULT_VAR_END, this.uiOrientation);
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application(CrashlyticsReport.Session.Event.Application.Execution execution2, @Nullable List<CrashlyticsReport.CustomAttribute> list, @Nullable List<CrashlyticsReport.CustomAttribute> list2, @Nullable Boolean bool, @Nullable CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails, @Nullable List<CrashlyticsReport.Session.Event.Application.ProcessDetails> list3, int i3) {
        this.execution = execution2;
        this.customAttributes = list;
        this.internalKeys = list2;
        this.background = bool;
        this.currentProcessDetails = processDetails;
        this.appProcessDetails = list3;
        this.uiOrientation = i3;
    }
}
