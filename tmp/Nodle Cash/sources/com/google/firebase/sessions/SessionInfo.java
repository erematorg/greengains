package com.google.firebase.sessions;

import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0006HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/google/firebase/sessions/SessionInfo;", "", "sessionId", "", "firstSessionId", "sessionIndex", "", "eventTimestampUs", "", "dataCollectionStatus", "Lcom/google/firebase/sessions/DataCollectionStatus;", "firebaseInstallationId", "firebaseAuthenticationToken", "(Ljava/lang/String;Ljava/lang/String;IJLcom/google/firebase/sessions/DataCollectionStatus;Ljava/lang/String;Ljava/lang/String;)V", "getDataCollectionStatus", "()Lcom/google/firebase/sessions/DataCollectionStatus;", "getEventTimestampUs", "()J", "getFirebaseAuthenticationToken", "()Ljava/lang/String;", "getFirebaseInstallationId", "getFirstSessionId", "getSessionId", "getSessionIndex", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SessionInfo {
    @NotNull
    private final DataCollectionStatus dataCollectionStatus;
    private final long eventTimestampUs;
    @NotNull
    private final String firebaseAuthenticationToken;
    @NotNull
    private final String firebaseInstallationId;
    @NotNull
    private final String firstSessionId;
    @NotNull
    private final String sessionId;
    private final int sessionIndex;

    public SessionInfo(@NotNull String str, @NotNull String str2, int i3, long j2, @NotNull DataCollectionStatus dataCollectionStatus2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(str2, "firstSessionId");
        Intrinsics.checkNotNullParameter(dataCollectionStatus2, "dataCollectionStatus");
        Intrinsics.checkNotNullParameter(str3, "firebaseInstallationId");
        Intrinsics.checkNotNullParameter(str4, "firebaseAuthenticationToken");
        this.sessionId = str;
        this.firstSessionId = str2;
        this.sessionIndex = i3;
        this.eventTimestampUs = j2;
        this.dataCollectionStatus = dataCollectionStatus2;
        this.firebaseInstallationId = str3;
        this.firebaseAuthenticationToken = str4;
    }

    public static /* synthetic */ SessionInfo copy$default(SessionInfo sessionInfo, String str, String str2, int i3, long j2, DataCollectionStatus dataCollectionStatus2, String str3, String str4, int i4, Object obj) {
        SessionInfo sessionInfo2 = sessionInfo;
        return sessionInfo.copy((i4 & 1) != 0 ? sessionInfo2.sessionId : str, (i4 & 2) != 0 ? sessionInfo2.firstSessionId : str2, (i4 & 4) != 0 ? sessionInfo2.sessionIndex : i3, (i4 & 8) != 0 ? sessionInfo2.eventTimestampUs : j2, (i4 & 16) != 0 ? sessionInfo2.dataCollectionStatus : dataCollectionStatus2, (i4 & 32) != 0 ? sessionInfo2.firebaseInstallationId : str3, (i4 & 64) != 0 ? sessionInfo2.firebaseAuthenticationToken : str4);
    }

    @NotNull
    public final String component1() {
        return this.sessionId;
    }

    @NotNull
    public final String component2() {
        return this.firstSessionId;
    }

    public final int component3() {
        return this.sessionIndex;
    }

    public final long component4() {
        return this.eventTimestampUs;
    }

    @NotNull
    public final DataCollectionStatus component5() {
        return this.dataCollectionStatus;
    }

    @NotNull
    public final String component6() {
        return this.firebaseInstallationId;
    }

    @NotNull
    public final String component7() {
        return this.firebaseAuthenticationToken;
    }

    @NotNull
    public final SessionInfo copy(@NotNull String str, @NotNull String str2, int i3, long j2, @NotNull DataCollectionStatus dataCollectionStatus2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(str2, "firstSessionId");
        DataCollectionStatus dataCollectionStatus3 = dataCollectionStatus2;
        Intrinsics.checkNotNullParameter(dataCollectionStatus3, "dataCollectionStatus");
        String str5 = str3;
        Intrinsics.checkNotNullParameter(str5, "firebaseInstallationId");
        String str6 = str4;
        Intrinsics.checkNotNullParameter(str6, "firebaseAuthenticationToken");
        return new SessionInfo(str, str2, i3, j2, dataCollectionStatus3, str5, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionInfo)) {
            return false;
        }
        SessionInfo sessionInfo = (SessionInfo) obj;
        return Intrinsics.areEqual((Object) this.sessionId, (Object) sessionInfo.sessionId) && Intrinsics.areEqual((Object) this.firstSessionId, (Object) sessionInfo.firstSessionId) && this.sessionIndex == sessionInfo.sessionIndex && this.eventTimestampUs == sessionInfo.eventTimestampUs && Intrinsics.areEqual((Object) this.dataCollectionStatus, (Object) sessionInfo.dataCollectionStatus) && Intrinsics.areEqual((Object) this.firebaseInstallationId, (Object) sessionInfo.firebaseInstallationId) && Intrinsics.areEqual((Object) this.firebaseAuthenticationToken, (Object) sessionInfo.firebaseAuthenticationToken);
    }

    @NotNull
    public final DataCollectionStatus getDataCollectionStatus() {
        return this.dataCollectionStatus;
    }

    public final long getEventTimestampUs() {
        return this.eventTimestampUs;
    }

    @NotNull
    public final String getFirebaseAuthenticationToken() {
        return this.firebaseAuthenticationToken;
    }

    @NotNull
    public final String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @NotNull
    public final String getFirstSessionId() {
        return this.firstSessionId;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    public final int getSessionIndex() {
        return this.sessionIndex;
    }

    public int hashCode() {
        int D2 = a.D(this.eventTimestampUs, a.c(this.sessionIndex, a.i(this.firstSessionId, this.sessionId.hashCode() * 31, 31), 31), 31);
        return this.firebaseAuthenticationToken.hashCode() + a.i(this.firebaseInstallationId, (this.dataCollectionStatus.hashCode() + D2) * 31, 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("SessionInfo(sessionId=");
        sb.append(this.sessionId);
        sb.append(", firstSessionId=");
        sb.append(this.firstSessionId);
        sb.append(", sessionIndex=");
        sb.append(this.sessionIndex);
        sb.append(", eventTimestampUs=");
        sb.append(this.eventTimestampUs);
        sb.append(", dataCollectionStatus=");
        sb.append(this.dataCollectionStatus);
        sb.append(", firebaseInstallationId=");
        sb.append(this.firebaseInstallationId);
        sb.append(", firebaseAuthenticationToken=");
        return a.o(')', this.firebaseAuthenticationToken, sb);
    }
}
