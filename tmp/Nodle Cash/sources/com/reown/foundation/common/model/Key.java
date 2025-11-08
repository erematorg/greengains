package com.reown.foundation.common.model;

import com.reown.util.UtilFunctionsKt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/reown/foundation/common/model/Key;", "", "keyAsHex", "", "getKeyAsHex", "()Ljava/lang/String;", "keyAsBytes", "", "getKeyAsBytes", "()[B", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Key {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @NotNull
        @Deprecated
        public static byte[] getKeyAsBytes(@NotNull Key key) {
            return Key.super.getKeyAsBytes();
        }
    }

    @NotNull
    byte[] getKeyAsBytes() {
        return UtilFunctionsKt.hexToBytes(getKeyAsHex());
    }

    @NotNull
    String getKeyAsHex();
}
