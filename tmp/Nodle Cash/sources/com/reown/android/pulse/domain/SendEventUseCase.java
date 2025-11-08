package com.reown.android.pulse.domain;

import I1.C0237a;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.pulse.data.PulseService;
import com.reown.android.pulse.model.SDKType;
import com.reown.android.pulse.model.properties.Props;
import com.reown.foundation.util.Logger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.QualifierKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ1\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/reown/android/pulse/domain/SendEventUseCase;", "Lcom/reown/android/pulse/domain/SendEventInterface;", "pulseService", "Lcom/reown/android/pulse/data/PulseService;", "logger", "Lcom/reown/foundation/util/Logger;", "bundleId", "", "<init>", "(Lcom/reown/android/pulse/data/PulseService;Lcom/reown/foundation/util/Logger;Ljava/lang/String;)V", "enableW3MAnalytics", "", "getEnableW3MAnalytics", "()Z", "enableW3MAnalytics$delegate", "Lkotlin/Lazy;", "send", "", "props", "Lcom/reown/android/pulse/model/properties/Props;", "sdkType", "Lcom/reown/android/pulse/model/SDKType;", "timestamp", "", "id", "(Lcom/reown/android/pulse/model/properties/Props;Lcom/reown/android/pulse/model/SDKType;Ljava/lang/Long;Ljava/lang/Long;)V", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSendEventUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SendEventUseCase.kt\ncom/reown/android/pulse/domain/SendEventUseCase\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,47:1\n124#2,4:48\n142#3:52\n*S KotlinDebug\n*F\n+ 1 SendEventUseCase.kt\ncom/reown/android/pulse/domain/SendEventUseCase\n*L\n22#1:48,4\n22#1:52\n*E\n"})
public final class SendEventUseCase implements SendEventInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final String bundleId;
    @NotNull
    private final Lazy enableW3MAnalytics$delegate = LazyKt.lazy(new C0237a(28));
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final PulseService pulseService;

    public SendEventUseCase(@NotNull PulseService pulseService2, @NotNull Logger logger2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(pulseService2, "pulseService");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(str, "bundleId");
        this.pulseService = pulseService2;
        this.logger = logger2;
        this.bundleId = str;
    }

    /* access modifiers changed from: private */
    public static final boolean enableW3MAnalytics_delegate$lambda$0() {
        Koin koin = KoinApplicationKt.getWcKoinApp().getKoin();
        return ((Boolean) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Boolean.class), QualifierKt.named(AndroidCommonDITags.ENABLE_WEB_3_MODAL_ANALYTICS), (Function0<? extends ParametersHolder>) null)).booleanValue();
    }

    private final boolean getEnableW3MAnalytics() {
        return ((Boolean) this.enableW3MAnalytics$delegate.getValue()).booleanValue();
    }

    public void send(@NotNull Props props, @NotNull SDKType sDKType, @Nullable Long l2, @Nullable Long l3) {
        Intrinsics.checkNotNullParameter(props, "props");
        Intrinsics.checkNotNullParameter(sDKType, "sdkType");
        if (getEnableW3MAnalytics()) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SendEventUseCase$send$1(this, l2, l3, props, sDKType, (Continuation<? super SendEventUseCase$send$1>) null), 3, (Object) null);
        }
    }
}
