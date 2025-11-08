package com.reown.sign.client.utils;

import com.reown.android.utils.cacao.CacaoSignerInterface;
import com.reown.sign.client.Sign;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/reown/sign/client/utils/CacaoSigner;", "Lcom/reown/android/utils/cacao/CacaoSignerInterface;", "Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CacaoSigner implements CacaoSignerInterface<Sign.Model.Cacao.Signature> {
    @NotNull
    public static final CacaoSigner INSTANCE = new CacaoSigner();

    private CacaoSigner() {
    }
}
