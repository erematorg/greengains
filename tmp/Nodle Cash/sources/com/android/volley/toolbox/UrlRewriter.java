package com.android.volley.toolbox;

import androidx.annotation.Nullable;

public interface UrlRewriter {
    @Nullable
    String rewriteUrl(String str);
}
