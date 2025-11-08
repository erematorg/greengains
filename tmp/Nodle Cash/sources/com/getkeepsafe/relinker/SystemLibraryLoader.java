package com.getkeepsafe.relinker;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.constraintlayout.core.state.b;
import com.getkeepsafe.relinker.ReLinker;

final class SystemLibraryLoader implements ReLinker.LibraryLoader {
    public void loadLibrary(String str) {
        System.loadLibrary(str);
    }

    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    public void loadPath(String str) {
        System.load(str);
    }

    public String mapLibraryName(String str) {
        return (!str.startsWith("lib") || !str.endsWith(".so")) ? System.mapLibraryName(str) : str;
    }

    public String[] supportedAbis() {
        String[] strArr = Build.SUPPORTED_ABIS;
        if (strArr.length > 0) {
            return strArr;
        }
        String str = Build.CPU_ABI2;
        return !TextUtils.isEmpty(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }

    public String unmapLibraryName(String str) {
        return b.y(str, 3, 3);
    }
}
