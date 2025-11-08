package com.reown.android.push;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J<\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000eH&J*\u0010\u0010\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000eH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/reown/android/push/PushInterface;", "", "clientId", "", "getClientId", "()Ljava/lang/String;", "register", "", "firebaseAccessToken", "enableEncrypted", "", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "unregister", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void register$default(PushInterface pushInterface, String str, boolean z2, Function0 function0, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                z2 = false;
            }
            pushInterface.register(str, z2, function0, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: register");
    }

    @NotNull
    String getClientId();

    void register(@NotNull String str, boolean z2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    void unregister(@NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);
}
