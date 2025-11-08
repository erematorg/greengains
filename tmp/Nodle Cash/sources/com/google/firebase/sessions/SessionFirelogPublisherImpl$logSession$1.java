package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.firebase.sessions.SessionFirelogPublisherImpl$logSession$1", f = "SessionFirelogPublisher.kt", i = {2}, l = {63, 64, 70}, m = "invokeSuspend", n = {"installationId"}, s = {"L$0"})
public final class SessionFirelogPublisherImpl$logSession$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SessionDetails $sessionDetails;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ SessionFirelogPublisherImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionFirelogPublisherImpl$logSession$1(SessionFirelogPublisherImpl sessionFirelogPublisherImpl, SessionDetails sessionDetails, Continuation<? super SessionFirelogPublisherImpl$logSession$1> continuation) {
        super(2, continuation);
        this.this$0 = sessionFirelogPublisherImpl;
        this.$sessionDetails = sessionDetails;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SessionFirelogPublisherImpl$logSession$1(this.this$0, this.$sessionDetails, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0094 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0045
            if (r1 == r4) goto L_0x0041
            if (r1 == r3) goto L_0x003d
            if (r1 != r2) goto L_0x0035
            java.lang.Object r0 = r10.L$5
            com.google.firebase.sessions.settings.SessionsSettings r0 = (com.google.firebase.sessions.settings.SessionsSettings) r0
            java.lang.Object r1 = r10.L$4
            com.google.firebase.sessions.SessionDetails r1 = (com.google.firebase.sessions.SessionDetails) r1
            java.lang.Object r2 = r10.L$3
            com.google.firebase.FirebaseApp r2 = (com.google.firebase.FirebaseApp) r2
            java.lang.Object r3 = r10.L$2
            com.google.firebase.sessions.SessionEvents r3 = (com.google.firebase.sessions.SessionEvents) r3
            java.lang.Object r4 = r10.L$1
            com.google.firebase.sessions.SessionFirelogPublisherImpl r4 = (com.google.firebase.sessions.SessionFirelogPublisherImpl) r4
            java.lang.Object r10 = r10.L$0
            com.google.firebase.sessions.InstallationId r10 = (com.google.firebase.sessions.InstallationId) r10
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r4
            r8 = r3
            r3 = r0
            r0 = r8
            r9 = r2
            r2 = r1
            r1 = r9
            goto L_0x009c
        L_0x0035:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x006c
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0053
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)
            com.google.firebase.sessions.SessionFirelogPublisherImpl r11 = r10.this$0
            r10.label = r4
            java.lang.Object r11 = r11.shouldLogSession(r10)
            if (r11 != r0) goto L_0x0053
            return r0
        L_0x0053:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00ae
            com.google.firebase.sessions.InstallationId$Companion r11 = com.google.firebase.sessions.InstallationId.Companion
            com.google.firebase.sessions.SessionFirelogPublisherImpl r1 = r10.this$0
            com.google.firebase.installations.FirebaseInstallationsApi r1 = r1.firebaseInstallations
            r10.label = r3
            java.lang.Object r11 = r11.create(r1, r10)
            if (r11 != r0) goto L_0x006c
            return r0
        L_0x006c:
            com.google.firebase.sessions.InstallationId r11 = (com.google.firebase.sessions.InstallationId) r11
            com.google.firebase.sessions.SessionFirelogPublisherImpl r4 = r10.this$0
            com.google.firebase.sessions.SessionEvents r3 = com.google.firebase.sessions.SessionEvents.INSTANCE
            com.google.firebase.FirebaseApp r1 = r4.firebaseApp
            com.google.firebase.sessions.SessionDetails r5 = r10.$sessionDetails
            com.google.firebase.sessions.SessionFirelogPublisherImpl r6 = r10.this$0
            com.google.firebase.sessions.settings.SessionsSettings r6 = r6.sessionSettings
            com.google.firebase.sessions.api.FirebaseSessionsDependencies r7 = com.google.firebase.sessions.api.FirebaseSessionsDependencies.INSTANCE
            r10.L$0 = r11
            r10.L$1 = r4
            r10.L$2 = r3
            r10.L$3 = r1
            r10.L$4 = r5
            r10.L$5 = r6
            r10.label = r2
            java.lang.Object r10 = r7.getRegisteredSubscribers$com_google_firebase_firebase_sessions(r10)
            if (r10 != r0) goto L_0x0095
            return r0
        L_0x0095:
            r0 = r3
            r7 = r4
            r2 = r5
            r3 = r6
            r8 = r11
            r11 = r10
            r10 = r8
        L_0x009c:
            r4 = r11
            java.util.Map r4 = (java.util.Map) r4
            java.lang.String r5 = r10.getFid()
            java.lang.String r6 = r10.getAuthToken()
            com.google.firebase.sessions.SessionEvent r10 = r0.buildSession(r1, r2, r3, r4, r5, r6)
            r7.attemptLoggingSessionEvent(r10)
        L_0x00ae:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.SessionFirelogPublisherImpl$logSession$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SessionFirelogPublisherImpl$logSession$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
