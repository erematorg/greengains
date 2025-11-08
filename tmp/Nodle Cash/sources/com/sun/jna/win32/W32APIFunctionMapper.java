package com.sun.jna.win32;

import androidx.exifinterface.media.ExifInterface;
import com.sun.jna.FunctionMapper;
import com.sun.jna.NativeLibrary;
import java.lang.reflect.Method;

public class W32APIFunctionMapper implements FunctionMapper {
    public static final FunctionMapper ASCII = new W32APIFunctionMapper(false);
    public static final FunctionMapper UNICODE = new W32APIFunctionMapper(true);
    private final String suffix;

    public W32APIFunctionMapper(boolean z2) {
        this.suffix = z2 ? ExifInterface.LONGITUDE_WEST : ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
    }

    public String getFunctionName(NativeLibrary nativeLibrary, Method method) {
        String name = method.getName();
        if (name.endsWith(ExifInterface.LONGITUDE_WEST) || name.endsWith(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
            return name;
        }
        try {
            return nativeLibrary.getFunction(name + this.suffix, 63).getName();
        } catch (UnsatisfiedLinkError unused) {
            return name;
        }
    }
}
