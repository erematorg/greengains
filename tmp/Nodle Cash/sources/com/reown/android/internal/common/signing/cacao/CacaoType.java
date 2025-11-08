package com.reown.android.internal.common.signing.cacao;

import com.reown.android.internal.common.signing.cacao.Cacao;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\f"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/CacaoType;", "", "header", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getHeader", "()Ljava/lang/String;", "EIP4361", "CAIP222", "toHeader", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Header;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum CacaoType {
    EIP4361("eip4361"),
    CAIP222("caip222");
    
    @NotNull
    private final String header;

    static {
        CacaoType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    private CacaoType(String str) {
        this.header = str;
    }

    @NotNull
    public static EnumEntries<CacaoType> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getHeader() {
        return this.header;
    }

    @NotNull
    public final Cacao.Header toHeader() {
        return new Cacao.Header(this.header);
    }
}
