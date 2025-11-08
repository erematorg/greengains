package com.reown.sign.client.utils;

import com.reown.android.cacao.signature.ISignatureType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/reown/sign/client/utils/SignatureType;", "Lcom/reown/android/cacao/signature/ISignatureType;", "", "header", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getHeader", "()Ljava/lang/String;", "EIP191", "EIP1271", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(message = "Moved to android-core module, as other SDKs also need CACAO.", replaceWith = @ReplaceWith(expression = "com.reown.android.internal.common.cacao.signature.SignatureType", imports = {}))
public enum SignatureType implements ISignatureType {
    EIP191("eip191"),
    EIP1271("eip1271");
    
    @NotNull
    private final String header;

    static {
        SignatureType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    private SignatureType(String str) {
        this.header = str;
    }

    @NotNull
    public static EnumEntries<SignatureType> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public String getHeader() {
        return this.header;
    }
}
