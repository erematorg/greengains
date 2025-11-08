package com.reown.android.verify.client;

import com.reown.android.verify.domain.VerifyResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J2\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\nH&J@\u0010\f\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\nH&JH\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\nH&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/reown/android/verify/client/VerifyInterface;", "", "initialize", "", "register", "attestationId", "", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "resolve", "metadataUrl", "Lcom/reown/android/verify/domain/VerifyResult;", "resolveV2", "attestationJWT", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VerifyInterface {
    void initialize();

    void register(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    void resolve(@NotNull String str, @NotNull String str2, @NotNull Function1<? super VerifyResult, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12);

    void resolveV2(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Function1<? super VerifyResult, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12);
}
