package com.reown.android.internal.common.signing.cacao;

import com.reown.android.cacao.signature.SignatureType;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.signing.signature.Signature;
import com.reown.android.internal.common.signing.signature.SignatureKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.web3j.utils.Numeric;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/CacaoVerifier;", "", "projectId", "Lcom/reown/android/internal/common/model/ProjectId;", "<init>", "(Lcom/reown/android/internal/common/model/ProjectId;)V", "verify", "", "cacao", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CacaoVerifier {
    @NotNull
    private final ProjectId projectId;

    public CacaoVerifier(@NotNull ProjectId projectId2) {
        Intrinsics.checkNotNullParameter(projectId2, "projectId");
        this.projectId = projectId2;
    }

    public final boolean verify(@NotNull Cacao cacao) {
        Intrinsics.checkNotNullParameter(cacao, "cacao");
        String t2 = cacao.getSignature().getT();
        if (Intrinsics.areEqual((Object) t2, (Object) SignatureType.EIP191.getHeader()) || Intrinsics.areEqual((Object) t2, (Object) SignatureType.EIP1271.getHeader())) {
            String cAIP222Message$default = CacaoKt.toCAIP222Message$default(cacao.getPayload(), (String) null, 1, (Object) null);
            byte[] bytes = CacaoKt.toCAIP222Message$default(cacao.getPayload(), (String) null, 1, (Object) null).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            String hexString = Numeric.toHexString(bytes);
            Issuer issuer = new Issuer(cacao.getPayload().getIss());
            if (SignatureKt.verify(Intrinsics.checkNotNullParameter(cacao.getSignature(), "<this>"), cAIP222Message$default, issuer.getAddress(), issuer.getChainId(), cacao.getSignature().getT(), this.projectId)) {
                return true;
            }
            Signature signature = Intrinsics.checkNotNullParameter(cacao.getSignature(), "<this>");
            Intrinsics.checkNotNull(hexString);
            return SignatureKt.verify(signature, hexString, issuer.getAddress(), issuer.getChainId(), cacao.getSignature().getT(), this.projectId);
        }
        throw new RuntimeException("Invalid header");
    }
}
