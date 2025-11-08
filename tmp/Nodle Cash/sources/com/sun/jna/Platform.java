package com.sun.jna;

import android.support.v4.media.session.a;
import com.appsamurai.storyly.util.ui.blur.c;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.komputing.kbip44.BIP44Kt;

public final class Platform {
    public static final int AIX = 7;
    public static final int ANDROID = 8;
    public static final String ARCH;
    public static final String C_LIBRARY_NAME;
    public static final int DRAGONFLYBSD = 12;
    public static final int FREEBSD = 4;
    public static final int GNU = 9;
    public static final boolean HAS_AWT;
    public static final boolean HAS_BUFFERS;
    public static final boolean HAS_DLL_CALLBACKS;
    public static final boolean HAS_JAWT;
    public static final int KFREEBSD = 10;
    public static final int LINUX = 1;
    public static final int MAC = 0;
    public static final String MATH_LIBRARY_NAME;
    public static final int NETBSD = 11;
    public static final int OPENBSD = 5;
    public static final String RESOURCE_PREFIX = getNativeLibraryResourcePrefix();
    public static final boolean RO_FIELDS;
    public static final int SOLARIS = 3;
    public static final int UNSPECIFIED = -1;
    public static final int WINDOWS = 2;
    public static final int WINDOWSCE = 6;
    private static final int osType;

    static {
        boolean z2;
        String property = System.getProperty("os.name");
        boolean z3 = false;
        if (property.startsWith("Linux")) {
            if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
                osType = 8;
                System.setProperty("jna.nounpack", "true");
            } else {
                osType = 1;
            }
        } else if (property.startsWith("AIX")) {
            osType = 7;
        } else if (property.startsWith("Mac") || property.startsWith("Darwin")) {
            osType = 0;
        } else if (property.startsWith("Windows CE")) {
            osType = 6;
        } else if (property.startsWith("Windows")) {
            osType = 2;
        } else if (property.startsWith("Solaris") || property.startsWith("SunOS")) {
            osType = 3;
        } else if (property.startsWith("FreeBSD")) {
            osType = 4;
        } else if (property.startsWith("OpenBSD")) {
            osType = 5;
        } else if (property.equalsIgnoreCase("gnu")) {
            osType = 9;
        } else if (property.equalsIgnoreCase("gnu/kfreebsd")) {
            osType = 10;
        } else if (property.equalsIgnoreCase("netbsd")) {
            osType = 11;
        } else if (property.equalsIgnoreCase("dragonflybsd")) {
            osType = 12;
        } else {
            osType = -1;
        }
        try {
            Class.forName("java.nio.Buffer");
            z2 = true;
        } catch (ClassNotFoundException unused) {
            z2 = false;
        }
        int i3 = osType;
        boolean z4 = (i3 == 6 || i3 == 8 || i3 == 7) ? false : true;
        HAS_AWT = z4;
        HAS_JAWT = z4 && i3 != 0;
        HAS_BUFFERS = z2;
        RO_FIELDS = i3 != 6;
        String str = "coredll";
        C_LIBRARY_NAME = i3 == 2 ? "msvcrt" : i3 == 6 ? str : c.f6372c;
        if (i3 == 2) {
            str = "msvcrt";
        } else if (i3 != 6) {
            str = BIP44Kt.BIP44_PREFIX;
        }
        MATH_LIBRARY_NAME = str;
        String canonicalArchitecture = getCanonicalArchitecture(System.getProperty("os.arch"), i3);
        ARCH = canonicalArchitecture;
        if (i3 == 2 && !canonicalArchitecture.startsWith("aarch")) {
            z3 = true;
        }
        HAS_DLL_CALLBACKS = z3;
    }

    private Platform() {
    }

    public static String getCanonicalArchitecture(String str, int i3) {
        String trim = str.toLowerCase().trim();
        if ("powerpc".equals(trim)) {
            trim = "ppc";
        } else if ("powerpc64".equals(trim)) {
            trim = "ppc64";
        } else if ("i386".equals(trim) || "i686".equals(trim)) {
            trim = "x86";
        } else if ("x86_64".equals(trim) || "amd64".equals(trim)) {
            trim = "x86-64";
        } else if ("zarch_64".equals(trim)) {
            trim = "s390x";
        }
        if ("ppc64".equals(trim) && "little".equals(System.getProperty("sun.cpu.endian"))) {
            trim = "ppc64le";
        }
        return (!"arm".equals(trim) || i3 != 1 || !isSoftFloat()) ? trim : "armel";
    }

    public static String getNativeLibraryResourcePrefix() {
        String property = System.getProperty("jna.prefix");
        if (property != null) {
            return property;
        }
        return getNativeLibraryResourcePrefix(getOSType(), System.getProperty("os.arch"), System.getProperty("os.name"));
    }

    public static final int getOSType() {
        return osType;
    }

    public static final boolean hasRuntimeExec() {
        return !isWindowsCE() || !"J9".equals(System.getProperty("java.vm.name"));
    }

    public static final boolean is64Bit() {
        String property = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        if (property != null) {
            return "64".equals(property);
        }
        String str = ARCH;
        return "x86-64".equals(str) || "ia64".equals(str) || "ppc64".equals(str) || "ppc64le".equals(str) || "sparcv9".equals(str) || "mips64".equals(str) || "mips64el".equals(str) || "loongarch64".equals(str) || "amd64".equals(str) || "aarch64".equals(str) || Native.POINTER_SIZE == 8;
    }

    public static final boolean isAIX() {
        return osType == 7;
    }

    public static final boolean isARM() {
        String str = ARCH;
        return str.startsWith("arm") || str.startsWith("aarch");
    }

    public static final boolean isAndroid() {
        return osType == 8;
    }

    public static final boolean isDragonFlyBSD() {
        return osType == 12;
    }

    public static final boolean isFreeBSD() {
        return osType == 4;
    }

    public static final boolean isGNU() {
        return osType == 9;
    }

    public static final boolean isIntel() {
        return ARCH.startsWith("x86");
    }

    public static final boolean isLinux() {
        return osType == 1;
    }

    public static final boolean isLoongArch() {
        return ARCH.startsWith("loongarch");
    }

    public static final boolean isMIPS() {
        String str = ARCH;
        return str.equals("mips") || str.equals("mips64") || str.equals("mipsel") || str.equals("mips64el");
    }

    public static final boolean isMac() {
        return osType == 0;
    }

    public static final boolean isNetBSD() {
        return osType == 11;
    }

    public static final boolean isOpenBSD() {
        return osType == 5;
    }

    public static final boolean isPPC() {
        return ARCH.startsWith("ppc");
    }

    public static final boolean isSPARC() {
        return ARCH.startsWith("sparc");
    }

    public static boolean isSoftFloat() {
        Class<Platform> cls = Platform.class;
        try {
            File file = new File("/proc/self/exe");
            if (file.exists()) {
                return !ELFAnalyser.analyse(file.getCanonicalPath()).isArmHardFloat();
            }
            return false;
        } catch (IOException e3) {
            Logger.getLogger(cls.getName()).log(Level.INFO, "Failed to read '/proc/self/exe' or the target binary.", e3);
            return false;
        } catch (SecurityException e4) {
            Logger.getLogger(cls.getName()).log(Level.INFO, "SecurityException while analysing '/proc/self/exe' or the target binary.", e4);
            return false;
        }
    }

    public static final boolean isSolaris() {
        return osType == 3;
    }

    public static final boolean isWindows() {
        int i3 = osType;
        return i3 == 2 || i3 == 6;
    }

    public static final boolean isWindowsCE() {
        return osType == 6;
    }

    public static final boolean isX11() {
        return !isWindows() && !isMac();
    }

    public static final boolean iskFreeBSD() {
        return osType == 10;
    }

    public static String getNativeLibraryResourcePrefix(int i3, String str, String str2) {
        String canonicalArchitecture = getCanonicalArchitecture(str, i3);
        switch (i3) {
            case 0:
                return androidx.browser.trusted.c.a("darwin-", canonicalArchitecture);
            case 1:
                return androidx.browser.trusted.c.a("linux-", canonicalArchitecture);
            case 2:
                return androidx.browser.trusted.c.a("win32-", canonicalArchitecture);
            case 3:
                return androidx.browser.trusted.c.a("sunos-", canonicalArchitecture);
            case 4:
                return androidx.browser.trusted.c.a("freebsd-", canonicalArchitecture);
            case 5:
                return androidx.browser.trusted.c.a("openbsd-", canonicalArchitecture);
            case 6:
                return androidx.browser.trusted.c.a("w32ce-", canonicalArchitecture);
            case 8:
                if (canonicalArchitecture.startsWith("arm")) {
                    canonicalArchitecture = "arm";
                }
                return "android-".concat(canonicalArchitecture);
            case 10:
                return androidx.browser.trusted.c.a("kfreebsd-", canonicalArchitecture);
            case 11:
                return androidx.browser.trusted.c.a("netbsd-", canonicalArchitecture);
            case 12:
                return androidx.browser.trusted.c.a("dragonflybsd-", canonicalArchitecture);
            default:
                String lowerCase = str2.toLowerCase();
                int indexOf = lowerCase.indexOf(StringUtils.SPACE);
                if (indexOf != -1) {
                    lowerCase = lowerCase.substring(0, indexOf);
                }
                return a.n(lowerCase, "-", canonicalArchitecture);
        }
    }
}
