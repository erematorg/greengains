package com.reown.android.cacao.signature;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u0000 \u000b2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/reown/android/cacao/signature/SignatureType;", "Lcom/reown/android/cacao/signature/ISignatureType;", "", "header", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getHeader", "()Ljava/lang/String;", "EIP191", "EIP1271", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum SignatureType implements ISignatureType {
    EIP191("eip191"),
    EIP1271("eip1271");
    
    @NotNull
    public static final Companion Companion = null;
    @NotNull
    private final String header;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/cacao/signature/SignatureType$Companion;", "", "<init>", "()V", "headerOf", "Lcom/reown/android/cacao/signature/SignatureType;", "header", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SignatureType headerOf(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "header");
            SignatureType signatureType = SignatureType.EIP191;
            if (!Intrinsics.areEqual((Object) str, (Object) signatureType.getHeader())) {
                signatureType = SignatureType.EIP1271;
                if (!Intrinsics.areEqual((Object) str, (Object) signatureType.getHeader())) {
                    throw new Throwable("Header not supported");
                }
            }
            return signatureType;
        }

        private Companion() {
        }
    }

    static {
        SignatureType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
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
