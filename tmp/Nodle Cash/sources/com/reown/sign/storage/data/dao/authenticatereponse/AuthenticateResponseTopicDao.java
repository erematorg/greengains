package com.reown.sign.storage.data.dao.authenticatereponse;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDao;", "", "id", "", "pairingTopic", "", "responseTopic", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getPairingTopic", "()Ljava/lang/String;", "getResponseTopic", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthenticateResponseTopicDao {
    private final long id;
    @NotNull
    private final String pairingTopic;
    @NotNull
    private final String responseTopic;

    public AuthenticateResponseTopicDao(long j2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "responseTopic");
        this.id = j2;
        this.pairingTopic = str;
        this.responseTopic = str2;
    }

    public static /* synthetic */ AuthenticateResponseTopicDao copy$default(AuthenticateResponseTopicDao authenticateResponseTopicDao, long j2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = authenticateResponseTopicDao.id;
        }
        if ((i3 & 2) != 0) {
            str = authenticateResponseTopicDao.pairingTopic;
        }
        if ((i3 & 4) != 0) {
            str2 = authenticateResponseTopicDao.responseTopic;
        }
        return authenticateResponseTopicDao.copy(j2, str, str2);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.pairingTopic;
    }

    @NotNull
    public final String component3() {
        return this.responseTopic;
    }

    @NotNull
    public final AuthenticateResponseTopicDao copy(long j2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "responseTopic");
        return new AuthenticateResponseTopicDao(j2, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticateResponseTopicDao)) {
            return false;
        }
        AuthenticateResponseTopicDao authenticateResponseTopicDao = (AuthenticateResponseTopicDao) obj;
        return this.id == authenticateResponseTopicDao.id && Intrinsics.areEqual((Object) this.pairingTopic, (Object) authenticateResponseTopicDao.pairingTopic) && Intrinsics.areEqual((Object) this.responseTopic, (Object) authenticateResponseTopicDao.responseTopic);
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getPairingTopic() {
        return this.pairingTopic;
    }

    @NotNull
    public final String getResponseTopic() {
        return this.responseTopic;
    }

    public int hashCode() {
        return this.responseTopic.hashCode() + a.i(this.pairingTopic, Long.hashCode(this.id) * 31, 31);
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.pairingTopic;
        return C0118y.j(androidx.work.impl.a.v(j2, "AuthenticateResponseTopicDao(id=", ", pairingTopic=", str), ", responseTopic=", this.responseTopic, ")");
    }
}
