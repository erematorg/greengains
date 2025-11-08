package com.google.firebase.sessions;

import com.google.firebase.encoders.annotations.Encodable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/google/firebase/sessions/SessionEvent;", "", "eventType", "Lcom/google/firebase/sessions/EventType;", "sessionData", "Lcom/google/firebase/sessions/SessionInfo;", "applicationInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "(Lcom/google/firebase/sessions/EventType;Lcom/google/firebase/sessions/SessionInfo;Lcom/google/firebase/sessions/ApplicationInfo;)V", "getApplicationInfo", "()Lcom/google/firebase/sessions/ApplicationInfo;", "getEventType", "()Lcom/google/firebase/sessions/EventType;", "getSessionData", "()Lcom/google/firebase/sessions/SessionInfo;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Encodable
public final class SessionEvent {
    @NotNull
    private final ApplicationInfo applicationInfo;
    @NotNull
    private final EventType eventType;
    @NotNull
    private final SessionInfo sessionData;

    public SessionEvent(@NotNull EventType eventType2, @NotNull SessionInfo sessionInfo, @NotNull ApplicationInfo applicationInfo2) {
        Intrinsics.checkNotNullParameter(eventType2, "eventType");
        Intrinsics.checkNotNullParameter(sessionInfo, "sessionData");
        Intrinsics.checkNotNullParameter(applicationInfo2, "applicationInfo");
        this.eventType = eventType2;
        this.sessionData = sessionInfo;
        this.applicationInfo = applicationInfo2;
    }

    public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, EventType eventType2, SessionInfo sessionInfo, ApplicationInfo applicationInfo2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            eventType2 = sessionEvent.eventType;
        }
        if ((i3 & 2) != 0) {
            sessionInfo = sessionEvent.sessionData;
        }
        if ((i3 & 4) != 0) {
            applicationInfo2 = sessionEvent.applicationInfo;
        }
        return sessionEvent.copy(eventType2, sessionInfo, applicationInfo2);
    }

    @NotNull
    public final EventType component1() {
        return this.eventType;
    }

    @NotNull
    public final SessionInfo component2() {
        return this.sessionData;
    }

    @NotNull
    public final ApplicationInfo component3() {
        return this.applicationInfo;
    }

    @NotNull
    public final SessionEvent copy(@NotNull EventType eventType2, @NotNull SessionInfo sessionInfo, @NotNull ApplicationInfo applicationInfo2) {
        Intrinsics.checkNotNullParameter(eventType2, "eventType");
        Intrinsics.checkNotNullParameter(sessionInfo, "sessionData");
        Intrinsics.checkNotNullParameter(applicationInfo2, "applicationInfo");
        return new SessionEvent(eventType2, sessionInfo, applicationInfo2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionEvent)) {
            return false;
        }
        SessionEvent sessionEvent = (SessionEvent) obj;
        return this.eventType == sessionEvent.eventType && Intrinsics.areEqual((Object) this.sessionData, (Object) sessionEvent.sessionData) && Intrinsics.areEqual((Object) this.applicationInfo, (Object) sessionEvent.applicationInfo);
    }

    @NotNull
    public final ApplicationInfo getApplicationInfo() {
        return this.applicationInfo;
    }

    @NotNull
    public final EventType getEventType() {
        return this.eventType;
    }

    @NotNull
    public final SessionInfo getSessionData() {
        return this.sessionData;
    }

    public int hashCode() {
        int hashCode = this.sessionData.hashCode();
        return this.applicationInfo.hashCode() + ((hashCode + (this.eventType.hashCode() * 31)) * 31);
    }

    @NotNull
    public String toString() {
        return "SessionEvent(eventType=" + this.eventType + ", sessionData=" + this.sessionData + ", applicationInfo=" + this.applicationInfo + ')';
    }
}
