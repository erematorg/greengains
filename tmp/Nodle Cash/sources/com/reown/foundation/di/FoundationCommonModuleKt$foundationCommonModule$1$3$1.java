package com.reown.foundation.di;

import com.reown.foundation.util.Logger;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\n"}, d2 = {"com/reown/foundation/di/FoundationCommonModuleKt$foundationCommonModule$1$3$1", "Lcom/reown/foundation/util/Logger;", "log", "", "logMsg", "", "throwable", "", "error", "errorMsg", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FoundationCommonModuleKt$foundationCommonModule$1$3$1 implements Logger {
    public void error(String str) {
        System.out.println(str);
    }

    public void log(String str) {
        System.out.println(str);
    }

    public void error(Throwable th) {
        String str;
        if (th == null || (str = ExceptionsKt.stackTraceToString(th)) == null) {
            str = th != null ? th.getMessage() : null;
            Intrinsics.checkNotNull(str);
        }
        System.out.println(str);
    }

    public void log(Throwable th) {
        String str;
        if (th == null || (str = ExceptionsKt.stackTraceToString(th)) == null) {
            str = th != null ? th.getMessage() : null;
            Intrinsics.checkNotNull(str);
        }
        System.out.println(str);
    }
}
