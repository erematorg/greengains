package com.reown.android.internal.common.signing.signature;

import androidx.constraintlayout.core.state.b;
import com.reown.android.cacao.signature.SignatureType;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.signing.eip6492.EIP6492Verifier;
import com.reown.util.UtilFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Sign;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0001\u001a\f\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\u0000\u001a4\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0000¨\u0006\u000e"}, d2 = {"toSignature", "Lcom/reown/android/internal/common/signing/signature/Signature;", "Lorg/web3j/crypto/Sign$SignatureData;", "toHexSignature", "", "toSignatureData", "verify", "", "originalMessage", "address", "chainId", "type", "projectId", "Lcom/reown/android/internal/common/model/ProjectId;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class SignatureKt {
    @NotNull
    public static final String toHexSignature(@NotNull Signature signature) {
        Intrinsics.checkNotNullParameter(signature, "<this>");
        return b.m(Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>"), UtilFunctionsKt.bytesToHex(signature.getR()), UtilFunctionsKt.bytesToHex(signature.getS()), UtilFunctionsKt.bytesToHex(signature.getV()));
    }

    public static final /* synthetic */ Signature toSignature(Sign.SignatureData signatureData) {
        Intrinsics.checkNotNullParameter(signatureData, "<this>");
        byte[] v2 = signatureData.getV();
        Intrinsics.checkNotNullExpressionValue(v2, "getV(...)");
        byte[] r2 = signatureData.getR();
        Intrinsics.checkNotNullExpressionValue(r2, "getR(...)");
        byte[] s3 = signatureData.getS();
        Intrinsics.checkNotNullExpressionValue(s3, "getS(...)");
        return new Signature(v2, r2, s3);
    }

    public static final /* synthetic */ boolean verify(Signature signature, String str, String str2, String str3, String str4, ProjectId projectId) {
        Intrinsics.checkNotNullParameter(signature, "<this>");
        Intrinsics.checkNotNullParameter(str, "originalMessage");
        Intrinsics.checkNotNullParameter(str2, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str3, "chainId");
        Intrinsics.checkNotNullParameter(str4, "type");
        Intrinsics.checkNotNullParameter(projectId, "projectId");
        EIP6492Verifier eIP6492Verifier = EIP6492Verifier.INSTANCE;
        eIP6492Verifier.init(str3, projectId.getValue());
        if (Intrinsics.areEqual((Object) str4, (Object) SignatureType.EIP191.getHeader())) {
            return eIP6492Verifier.verify6492(str, str2, toHexSignature(signature));
        }
        if (Intrinsics.areEqual((Object) str4, (Object) SignatureType.EIP1271.getHeader())) {
            return eIP6492Verifier.verify6492(str, str2, toHexSignature(signature));
        }
        throw new RuntimeException("Invalid signature type");
    }
}
