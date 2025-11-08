package com.airbnb.lottie.network;

import androidx.annotation.NonNull;
import java.io.File;

public interface LottieNetworkCacheProvider {
    @NonNull
    File getCacheDir();
}
