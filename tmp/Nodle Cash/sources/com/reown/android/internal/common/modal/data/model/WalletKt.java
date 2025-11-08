package com.reown.android.internal.common.modal.data.model;

import android.net.Uri;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0002¨\u0006\u0002"}, d2 = {"extractPackage", "", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class WalletKt {
    /* access modifiers changed from: private */
    public static final String extractPackage(String str) {
        return Uri.parse(str).getQueryParameter(TtmlNode.ATTR_ID);
    }
}
