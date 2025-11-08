package com.reown.android.internal.common.modal.domain.usecase;

import com.reown.android.internal.common.modal.AppKitApiRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/modal/domain/usecase/EnableAnalyticsUseCase;", "Lcom/reown/android/internal/common/modal/domain/usecase/EnableAnalyticsUseCaseInterface;", "repository", "Lcom/reown/android/internal/common/modal/AppKitApiRepository;", "<init>", "(Lcom/reown/android/internal/common/modal/AppKitApiRepository;)V", "fetchAnalyticsConfig", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EnableAnalyticsUseCase implements EnableAnalyticsUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final AppKitApiRepository repository;

    public EnableAnalyticsUseCase(@NotNull AppKitApiRepository appKitApiRepository) {
        Intrinsics.checkNotNullParameter(appKitApiRepository, "repository");
        this.repository = appKitApiRepository;
    }

    public boolean fetchAnalyticsConfig() {
        return ((Boolean) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new EnableAnalyticsUseCase$fetchAnalyticsConfig$1(this, (Continuation<? super EnableAnalyticsUseCase$fetchAnalyticsConfig$1>) null), 1, (Object) null)).booleanValue();
    }
}
