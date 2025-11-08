package com.reown.android.internal.common.di;

import com.reown.foundation.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import timber.log.Timber;

@SourceDebugExtension({"SMAP\nCoreCommonModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreCommonModule.kt\ncom/reown/android/internal/common/di/CoreCommonModuleKt$coreCommonModule$1$4$1\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,69:1\n138#2,5:70\n138#2,5:75\n138#2,5:80\n138#2,5:85\n*S KotlinDebug\n*F\n+ 1 CoreCommonModule.kt\ncom/reown/android/internal/common/di/CoreCommonModuleKt$coreCommonModule$1$4$1\n*L\n53#1:70,5\n57#1:75,5\n61#1:80,5\n65#1:85,5\n*E\n"})
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\n"}, d2 = {"com/reown/android/internal/common/di/CoreCommonModuleKt$coreCommonModule$1$4$1", "Lcom/reown/foundation/util/Logger;", "log", "", "logMsg", "", "throwable", "", "error", "errorMsg", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreCommonModuleKt$coreCommonModule$1$4$1 implements Logger {
    final /* synthetic */ Scope $this_single;

    public CoreCommonModuleKt$coreCommonModule$1$4$1(Scope scope) {
        this.$this_single = scope;
    }

    public void error(String str) {
        ((Timber.Forest) this.$this_single.get(Reflection.getOrCreateKotlinClass(Timber.Forest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).e(str, new Object[0]);
    }

    public void log(String str) {
        ((Timber.Forest) this.$this_single.get(Reflection.getOrCreateKotlinClass(Timber.Forest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).d(str, new Object[0]);
    }

    public void error(Throwable th) {
        ((Timber.Forest) this.$this_single.get(Reflection.getOrCreateKotlinClass(Timber.Forest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).e(th);
    }

    public void log(Throwable th) {
        ((Timber.Forest) this.$this_single.get(Reflection.getOrCreateKotlinClass(Timber.Forest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).d(th);
    }
}
