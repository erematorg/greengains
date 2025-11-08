package com.reown.android.push.client;

import I1.C0237a;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.push.PushInterface;
import com.reown.android.push.network.PushService;
import com.reown.android.push.network.model.PushBody;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.koin.core.Koin;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001b0\"H\u0016J*\u0010$\u001a\u00020\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001b0\"H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8VX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/reown/android/push/client/PushClient;", "Lcom/reown/android/push/PushInterface;", "<init>", "()V", "pushService", "Lcom/reown/android/push/network/PushService;", "getPushService", "()Lcom/reown/android/push/network/PushService;", "pushService$delegate", "Lkotlin/Lazy;", "clientId", "", "getClientId", "()Ljava/lang/String;", "clientId$delegate", "projectId", "Lcom/reown/android/internal/common/model/ProjectId;", "getProjectId", "()Lcom/reown/android/internal/common/model/ProjectId;", "projectId$delegate", "pushMessagesRepository", "Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "getPushMessagesRepository", "()Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "pushMessagesRepository$delegate", "SUCCESS_STATUS", "register", "", "firebaseAccessToken", "enableEncrypted", "", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "unregister", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushClient.kt\ncom/reown/android/push/client/PushClient\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,71:1\n124#2,4:72\n124#2,4:77\n124#2,4:82\n124#2,4:87\n142#3:76\n142#3:81\n142#3:86\n142#3:91\n*S KotlinDebug\n*F\n+ 1 PushClient.kt\ncom/reown/android/push/client/PushClient\n*L\n19#1:72,4\n20#1:77,4\n21#1:82,4\n22#1:87,4\n19#1:76\n20#1:81\n21#1:86\n22#1:91\n*E\n"})
public final class PushClient implements PushInterface {
    @NotNull
    public static final PushClient INSTANCE = new PushClient();
    @NotNull
    private static final String SUCCESS_STATUS = "SUCCESS";
    @NotNull
    private static final Lazy clientId$delegate = LazyKt.lazy(new a(0));
    @NotNull
    private static final Lazy projectId$delegate = LazyKt.lazy(new a(1));
    @NotNull
    private static final Lazy pushMessagesRepository$delegate = LazyKt.lazy(new a(2));
    @NotNull
    private static final Lazy pushService$delegate = LazyKt.lazy(new C0237a(29));

    private PushClient() {
    }

    /* access modifiers changed from: private */
    public static final String clientId_delegate$lambda$1() {
        Koin koin = KoinApplicationKt.getWcKoinApp().getKoin();
        return (String) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public final ProjectId getProjectId() {
        return (ProjectId) projectId$delegate.getValue();
    }

    private final PushMessagesRepository getPushMessagesRepository() {
        return (PushMessagesRepository) pushMessagesRepository$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final PushService getPushService() {
        return (PushService) pushService$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final ProjectId projectId_delegate$lambda$2() {
        return (ProjectId) KoinApplicationKt.getWcKoinApp().getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(ProjectId.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public static final PushMessagesRepository pushMessagesRepository_delegate$lambda$3() {
        return (PushMessagesRepository) KoinApplicationKt.getWcKoinApp().getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(PushMessagesRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public static final PushService pushService_delegate$lambda$0() {
        return (PushService) KoinApplicationKt.getWcKoinApp().getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(PushService.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    @NotNull
    public String getClientId() {
        return (String) clientId$delegate.getValue();
    }

    public void register(@NotNull String str, boolean z2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Function0<Unit> function02 = function0;
        Function1<? super Throwable, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(str, "firebaseAccessToken");
        Intrinsics.checkNotNullParameter(function02, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        if (z2) {
            getPushMessagesRepository().enablePushNotifications();
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), Dispatchers.getIO(), (CoroutineStart) null, new PushClient$register$1(new PushBody(getClientId(), str, (String) null, Boolean.valueOf(z2), 4, (DefaultConstructorMarker) null), function02, function12, (Continuation<? super PushClient$register$1>) null), 2, (Object) null);
    }

    public void unregister(@NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onError");
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), Dispatchers.getIO(), (CoroutineStart) null, new PushClient$unregister$1(function0, function1, (Continuation<? super PushClient$unregister$1>) null), 2, (Object) null);
    }
}
