package com.reown.android.di;

import com.getkeepsafe.relinker.ReLinker;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/reown/android/di/CoreStorageModuleKt$loadSqlCipherLibrary$1", "Lcom/getkeepsafe/relinker/ReLinker$LoadListener;", "success", "", "failure", "t", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreStorageModuleKt$loadSqlCipherLibrary$1 implements ReLinker.LoadListener {
    final /* synthetic */ UnsatisfiedLinkError $e;

    public CoreStorageModuleKt$loadSqlCipherLibrary$1(UnsatisfiedLinkError unsatisfiedLinkError) {
        this.$e = unsatisfiedLinkError;
    }

    public void failure(Throwable th) {
        throw this.$e;
    }

    public void success() {
    }
}
