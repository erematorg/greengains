package com.reown.android.internal.common.signing.message;

import com.reown.android.cacao.signature.SignatureType;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.signing.signature.Signature;
import com.reown.android.internal.common.signing.signature.SignatureKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/reown/android/internal/common/signing/message/MessageSignatureVerifier;", "", "projectId", "Lcom/reown/android/internal/common/model/ProjectId;", "<init>", "(Lcom/reown/android/internal/common/model/ProjectId;)V", "verify", "", "signature", "", "originalMessage", "address", "chainId", "type", "Lcom/reown/android/cacao/signature/SignatureType;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MessageSignatureVerifier {
    @NotNull
    private final ProjectId projectId;

    public MessageSignatureVerifier(@NotNull ProjectId projectId2) {
        Intrinsics.checkNotNullParameter(projectId2, "projectId");
        this.projectId = projectId2;
    }

    public final boolean verify(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull SignatureType signatureType) {
        Intrinsics.checkNotNullParameter(str, "signature");
        Intrinsics.checkNotNullParameter(str2, "originalMessage");
        Intrinsics.checkNotNullParameter(str3, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str4, "chainId");
        Intrinsics.checkNotNullParameter(signatureType, "type");
        return SignatureKt.verify(Signature.Companion.fromString(str), str2, str3, str4, signatureType.getHeader(), this.projectId);
    }
}
