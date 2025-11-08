package com.reown.android.internal.utils;

import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.reown.android.internal.common.model.AccountId;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0005\u001a\u00020\u0001*\u00020\u0006¢\u0006\u0004\b\u0007\u0010\b\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\n\u001a\u0011\u0010\u000b\u001a\u00020\u0001*\u00020\u0006¢\u0006\u0004\b\f\u0010\b\u001a\u0016\u0010\r\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"SELF_PARTICIPANT_CONTEXT", "", "SELF_INVITE_PUBLIC_KEY_CONTEXT", "SELF_IDENTITY_PUBLIC_KEY_CONTEXT", "SELF_PEER_PUBLIC_KEY_CONTEXT", "getInviteTag", "Lcom/reown/android/internal/common/model/AccountId;", "getInviteTag-PaLCHi0", "(Ljava/lang/String;)Ljava/lang/String;", "getParticipantTag", "Lcom/reown/foundation/common/model/Topic;", "getIdentityTag", "getIdentityTag-PaLCHi0", "getPeerTag", "Lkotlin/Pair;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ContextKt {
    @NotNull
    private static final String SELF_IDENTITY_PUBLIC_KEY_CONTEXT = "self_identityKey/";
    @NotNull
    private static final String SELF_INVITE_PUBLIC_KEY_CONTEXT = "self_inviteKey/";
    @NotNull
    private static final String SELF_PARTICIPANT_CONTEXT = "self_participant/";
    @NotNull
    private static final String SELF_PEER_PUBLIC_KEY_CONTEXT = "self_peerKey/";

    @NotNull
    /* renamed from: getIdentityTag-PaLCHi0  reason: not valid java name */
    public static final String m8807getIdentityTagPaLCHi0(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "$this$getIdentityTag");
        return SELF_IDENTITY_PUBLIC_KEY_CONTEXT + str;
    }

    @NotNull
    /* renamed from: getInviteTag-PaLCHi0  reason: not valid java name */
    public static final String m8808getInviteTagPaLCHi0(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "$this$getInviteTag");
        return SELF_INVITE_PUBLIC_KEY_CONTEXT + str;
    }

    @NotNull
    public static final String getParticipantTag(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, "<this>");
        return c.a(SELF_PARTICIPANT_CONTEXT, topic.getValue());
    }

    @NotNull
    public static final String getPeerTag(@NotNull Pair<AccountId, Topic> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        return C0118y.f(SELF_PEER_PUBLIC_KEY_CONTEXT, pair.getFirst().m8756unboximpl(), "/", pair.getSecond().getValue());
    }
}
