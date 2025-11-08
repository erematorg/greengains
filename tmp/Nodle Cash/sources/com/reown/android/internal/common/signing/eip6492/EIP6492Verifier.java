package com.reown.android.internal.common.signing.eip6492;

import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;
import uniffi.yttrium.Erc6492Client;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/reown/android/internal/common/signing/eip6492/EIP6492Verifier;", "", "<init>", "()V", "erc6492Client", "Luniffi/yttrium/Erc6492Client;", "init", "", "chainId", "", "projectId", "verify6492", "", "originalMessage", "address", "signature", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EIP6492Verifier {
    @NotNull
    public static final EIP6492Verifier INSTANCE = new EIP6492Verifier();
    /* access modifiers changed from: private */
    public static Erc6492Client erc6492Client;

    private EIP6492Verifier() {
    }

    public final void init(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "chainId");
        Intrinsics.checkNotNullParameter(str2, "projectId");
        try {
            erc6492Client = new Erc6492Client("https://rpc.walletconnect.com/v1?chainId=" + str + "&projectId=" + str2);
        } catch (Exception e3) {
            System.out.println(C0118y.e("init error: ", e3));
        }
    }

    public final boolean verify6492(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, "originalMessage");
        Intrinsics.checkNotNullParameter(str2, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str3, "signature");
        try {
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return ((Boolean) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new EIP6492Verifier$verify6492$1(str3, str2, Numeric.toHexString(Sign.getEthereumMessageHash(bytes)), (Continuation<? super EIP6492Verifier$verify6492$1>) null), 1, (Object) null)).booleanValue();
        } catch (Exception e3) {
            System.out.println(c.a("Error in verify6492: ", e3.getMessage()));
            e3.printStackTrace();
            return false;
        }
    }
}
