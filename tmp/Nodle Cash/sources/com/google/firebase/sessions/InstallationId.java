package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/google/firebase/sessions/InstallationId;", "", "fid", "", "authToken", "(Ljava/lang/String;Ljava/lang/String;)V", "getAuthToken", "()Ljava/lang/String;", "getFid", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InstallationId {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String TAG = "InstallationId";
    @NotNull
    private final String authToken;
    @NotNull
    private final String fid;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/google/firebase/sessions/InstallationId$Companion;", "", "()V", "TAG", "", "create", "Lcom/google/firebase/sessions/InstallationId;", "firebaseInstallations", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "(Lcom/google/firebase/installations/FirebaseInstallationsApi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0089 A[Catch:{ Exception -> 0x0034 }, RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object create(@org.jetbrains.annotations.NotNull com.google.firebase.installations.FirebaseInstallationsApi r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.google.firebase.sessions.InstallationId> r9) {
            /*
                r7 = this;
                boolean r0 = r9 instanceof com.google.firebase.sessions.InstallationId$Companion$create$1
                if (r0 == 0) goto L_0x0013
                r0 = r9
                com.google.firebase.sessions.InstallationId$Companion$create$1 r0 = (com.google.firebase.sessions.InstallationId$Companion$create$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.label = r1
                goto L_0x0018
            L_0x0013:
                com.google.firebase.sessions.InstallationId$Companion$create$1 r0 = new com.google.firebase.sessions.InstallationId$Companion$create$1
                r0.<init>(r7, r9)
            L_0x0018:
                java.lang.Object r7 = r0.result
                java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r0.label
                java.lang.String r2 = ""
                java.lang.String r3 = "InstallationId"
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L_0x0048
                if (r1 == r5) goto L_0x003e
                if (r1 != r4) goto L_0x0036
                java.lang.Object r8 = r0.L$0
                java.lang.String r8 = (java.lang.String) r8
                kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ Exception -> 0x0034 }
                goto L_0x008a
            L_0x0034:
                r7 = move-exception
                goto L_0x0093
            L_0x0036:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L_0x003e:
                java.lang.Object r8 = r0.L$0
                com.google.firebase.installations.FirebaseInstallationsApi r8 = (com.google.firebase.installations.FirebaseInstallationsApi) r8
                kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ Exception -> 0x0046 }
                goto L_0x0060
            L_0x0046:
                r7 = move-exception
                goto L_0x006f
            L_0x0048:
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = 0
                com.google.android.gms.tasks.Task r7 = r8.getToken(r7)     // Catch:{ Exception -> 0x0046 }
                java.lang.String r1 = "firebaseInstallations.getToken(false)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)     // Catch:{ Exception -> 0x0046 }
                r0.L$0 = r8     // Catch:{ Exception -> 0x0046 }
                r0.label = r5     // Catch:{ Exception -> 0x0046 }
                java.lang.Object r7 = kotlinx.coroutines.tasks.TasksKt.await(r7, r0)     // Catch:{ Exception -> 0x0046 }
                if (r7 != r9) goto L_0x0060
                return r9
            L_0x0060:
                com.google.firebase.installations.InstallationTokenResult r7 = (com.google.firebase.installations.InstallationTokenResult) r7     // Catch:{ Exception -> 0x0046 }
                java.lang.String r7 = r7.getToken()     // Catch:{ Exception -> 0x0046 }
                java.lang.String r1 = "{\n          firebaseInst…).await().token\n        }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)     // Catch:{ Exception -> 0x0046 }
                r6 = r8
                r8 = r7
                r7 = r6
                goto L_0x0076
            L_0x006f:
                java.lang.String r1 = "Error getting authentication token."
                android.util.Log.w(r3, r1, r7)
                r7 = r8
                r8 = r2
            L_0x0076:
                com.google.android.gms.tasks.Task r7 = r7.getId()     // Catch:{ Exception -> 0x0034 }
                java.lang.String r1 = "firebaseInstallations.id"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)     // Catch:{ Exception -> 0x0034 }
                r0.L$0 = r8     // Catch:{ Exception -> 0x0034 }
                r0.label = r4     // Catch:{ Exception -> 0x0034 }
                java.lang.Object r7 = kotlinx.coroutines.tasks.TasksKt.await(r7, r0)     // Catch:{ Exception -> 0x0034 }
                if (r7 != r9) goto L_0x008a
                return r9
            L_0x008a:
                java.lang.String r9 = "{\n          firebaseInst…ions.id.await()\n        }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)     // Catch:{ Exception -> 0x0034 }
                java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x0034 }
                r2 = r7
                goto L_0x0098
            L_0x0093:
                java.lang.String r9 = "Error getting Firebase installation id ."
                android.util.Log.w(r3, r9, r7)
            L_0x0098:
                com.google.firebase.sessions.InstallationId r7 = new com.google.firebase.sessions.InstallationId
                r9 = 0
                r7.<init>(r2, r8, r9)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.InstallationId.Companion.create(com.google.firebase.installations.FirebaseInstallationsApi, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private Companion() {
        }
    }

    public /* synthetic */ InstallationId(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    @NotNull
    public final String getAuthToken() {
        return this.authToken;
    }

    @NotNull
    public final String getFid() {
        return this.fid;
    }

    private InstallationId(String str, String str2) {
        this.fid = str;
        this.authToken = str2;
    }
}
