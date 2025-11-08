package com.sun.jna;

import android.support.v4.media.session.a;
import androidx.constraintlayout.core.state.b;
import androidx.exifinterface.media.ExifInterface;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Structure;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Native implements Version {
    public static final int BOOL_SIZE = sizeof(4);
    static final int CB_HAS_INITIALIZER = 1;
    static final int CB_OPTION_DIRECT = 1;
    static final int CB_OPTION_IN_DLL = 2;
    private static final int CVT_ARRAY_BOOLEAN = 13;
    private static final int CVT_ARRAY_BYTE = 6;
    private static final int CVT_ARRAY_CHAR = 8;
    private static final int CVT_ARRAY_DOUBLE = 12;
    private static final int CVT_ARRAY_FLOAT = 11;
    private static final int CVT_ARRAY_INT = 9;
    private static final int CVT_ARRAY_LONG = 10;
    private static final int CVT_ARRAY_SHORT = 7;
    private static final int CVT_BOOLEAN = 14;
    private static final int CVT_BUFFER = 5;
    private static final int CVT_BYTE = 29;
    private static final int CVT_CALLBACK = 15;
    private static final int CVT_DEFAULT = 0;
    private static final int CVT_FLOAT = 16;
    private static final int CVT_INTEGER_TYPE = 21;
    private static final int CVT_JNIENV = 27;
    private static final int CVT_NATIVE_MAPPED = 17;
    private static final int CVT_NATIVE_MAPPED_STRING = 18;
    private static final int CVT_NATIVE_MAPPED_WSTRING = 19;
    private static final int CVT_OBJECT = 26;
    private static final int CVT_POINTER = 1;
    private static final int CVT_POINTER_TYPE = 22;
    private static final int CVT_SHORT = 28;
    private static final int CVT_STRING = 2;
    private static final int CVT_STRUCTURE = 3;
    private static final int CVT_STRUCTURE_BYVAL = 4;
    private static final int CVT_TYPE_MAPPER = 23;
    private static final int CVT_TYPE_MAPPER_STRING = 24;
    private static final int CVT_TYPE_MAPPER_WSTRING = 25;
    private static final int CVT_UNSUPPORTED = -1;
    private static final int CVT_WSTRING = 20;
    public static final boolean DEBUG_JNA_LOAD;
    private static final Level DEBUG_JNA_LOAD_LEVEL;
    public static final boolean DEBUG_LOAD = Boolean.getBoolean("jna.debug_load");
    public static final Charset DEFAULT_CHARSET;
    public static final String DEFAULT_ENCODING;
    private static final Callback.UncaughtExceptionHandler DEFAULT_HANDLER;
    static final String JNA_TMPLIB_PREFIX = "jna";
    /* access modifiers changed from: private */
    public static final Logger LOG = Logger.getLogger(Native.class.getName());
    public static final int LONG_DOUBLE_SIZE = sizeof(5);
    public static final int LONG_SIZE;
    static final int MAX_ALIGNMENT;
    static final int MAX_PADDING;
    public static final int POINTER_SIZE = sizeof(0);
    public static final int SIZE_T_SIZE = sizeof(3);
    private static final int TYPE_BOOL = 4;
    private static final int TYPE_LONG = 1;
    private static final int TYPE_LONG_DOUBLE = 5;
    private static final int TYPE_SIZE_T = 3;
    private static final int TYPE_VOIDP = 0;
    private static final int TYPE_WCHAR_T = 2;
    public static final int WCHAR_SIZE = sizeof(2);
    private static final String _OPTION_ENCLOSING_LIBRARY = "enclosing-library";
    private static Callback.UncaughtExceptionHandler callbackExceptionHandler;
    private static final Object finalizer = new Object() {
        public void finalize() throws Throwable {
            Native.dispose();
            super.finalize();
        }
    };
    static String jnidispatchPath = null;
    private static final Map<Class<?>, Reference<?>> libraries = Collections.synchronizedMap(new WeakHashMap());
    private static final ThreadLocal<Memory> nativeThreadTerminationFlag = new ThreadLocal<Memory>() {
        public Memory initialValue() {
            Memory memory = new Memory(4);
            memory.clear();
            return memory;
        }
    };
    private static final Map<Thread, Pointer> nativeThreads = Collections.synchronizedMap(new WeakHashMap());
    private static final Map<Class<?>, long[]> registeredClasses = new WeakHashMap();
    private static final Map<Class<?>, NativeLibrary> registeredLibraries = new WeakHashMap();
    private static final Map<Class<?>, Map<String, Object>> typeOptions = Collections.synchronizedMap(new WeakHashMap());

    public static class AWT {
        private AWT() {
        }

        public static long getComponentID(Object obj) throws HeadlessException {
            if (!GraphicsEnvironment.isHeadless()) {
                Component component = (Component) obj;
                if (component.isLightweight()) {
                    throw new IllegalArgumentException("Component must be heavyweight");
                } else if (!component.isDisplayable()) {
                    throw new IllegalStateException("Component must be displayable");
                } else if (!Platform.isX11() || !System.getProperty("java.version").startsWith("1.4") || component.isVisible()) {
                    return Native.getWindowHandle0(component);
                } else {
                    throw new IllegalStateException("Component must be visible");
                }
            } else {
                throw new HeadlessException("No native windows when headless");
            }
        }

        public static long getWindowID(Window window) throws HeadlessException {
            return getComponentID(window);
        }
    }

    public static class Buffers {
        private Buffers() {
        }

        public static boolean isBuffer(Class<?> cls) {
            return Buffer.class.isAssignableFrom(cls);
        }
    }

    public interface ffi_callback {
        void invoke(long j2, long j3, long j4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    static {
        /*
            java.lang.Class<com.sun.jna.Native> r0 = com.sun.jna.Native.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            LOG = r0
            java.lang.String r0 = "native.encoding"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r1 = 0
            if (r0 == 0) goto L_0x002a
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r0)     // Catch:{ Exception -> 0x001a }
            goto L_0x002b
        L_0x001a:
            r2 = move-exception
            java.util.logging.Logger r3 = LOG
            java.util.logging.Level r4 = java.util.logging.Level.WARNING
            java.lang.String r5 = "Failed to get charset for native.encoding value : '"
            java.lang.String r6 = "'"
            java.lang.String r0 = A.a.l(r5, r0, r6)
            r3.log(r4, r0, r2)
        L_0x002a:
            r0 = r1
        L_0x002b:
            if (r0 != 0) goto L_0x0031
            java.nio.charset.Charset r0 = java.nio.charset.Charset.defaultCharset()
        L_0x0031:
            DEFAULT_CHARSET = r0
            java.lang.String r0 = r0.name()
            DEFAULT_ENCODING = r0
            java.lang.String r0 = "jna.debug_load"
            boolean r0 = java.lang.Boolean.getBoolean(r0)
            DEBUG_LOAD = r0
            java.lang.String r0 = "jna.debug_load.jna"
            boolean r0 = java.lang.Boolean.getBoolean(r0)
            DEBUG_JNA_LOAD = r0
            if (r0 == 0) goto L_0x004e
            java.util.logging.Level r0 = java.util.logging.Level.INFO
            goto L_0x0050
        L_0x004e:
            java.util.logging.Level r0 = java.util.logging.Level.FINE
        L_0x0050:
            DEBUG_JNA_LOAD_LEVEL = r0
            jnidispatchPath = r1
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            java.util.Map r0 = java.util.Collections.synchronizedMap(r0)
            typeOptions = r0
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            java.util.Map r0 = java.util.Collections.synchronizedMap(r0)
            libraries = r0
            com.sun.jna.Native$1 r0 = new com.sun.jna.Native$1
            r0.<init>()
            DEFAULT_HANDLER = r0
            callbackExceptionHandler = r0
            loadNativeDispatchLibrary()
            java.lang.String r0 = "7.0.4"
            java.lang.String r1 = getNativeVersion()
            boolean r0 = isCompatibleVersion(r0, r1)
            if (r0 != 0) goto L_0x00ec
            java.lang.String r0 = java.lang.System.lineSeparator()
            java.lang.Error r1 = new java.lang.Error
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r0)
            java.lang.String r3 = "There is an incompatible JNA native library installed on this system"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = "Expected: 7.0.4"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = "Found:    "
            r2.append(r3)
            java.lang.String r3 = getNativeVersion()
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = jnidispatchPath
            if (r3 == 0) goto L_0x00c6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "(at "
            r3.<init>(r4)
            java.lang.String r4 = jnidispatchPath
            java.lang.String r5 = ")"
            java.lang.String r3 = A.a.n(r3, r4, r5)
            goto L_0x00cc
        L_0x00c6:
            java.lang.String r3 = "java.library.path"
            java.lang.String r3 = java.lang.System.getProperty(r3)
        L_0x00cc:
            java.lang.String r4 = "."
            java.lang.String r5 = "To resolve this issue you may do one of the following:"
            androidx.constraintlayout.core.state.b.w(r2, r3, r4, r0, r5)
            java.lang.String r3 = " - remove or uninstall the offending library"
            java.lang.String r4 = " - set the system property jna.nosys=true"
            androidx.constraintlayout.core.state.b.w(r2, r0, r3, r0, r4)
            java.lang.String r3 = " - set jna.boot.library.path to include the path to the version of the "
            java.lang.String r4 = "   jnidispatch library included with the JNA jar file you are using"
            androidx.constraintlayout.core.state.b.w(r2, r0, r3, r0, r4)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x00ec:
            r0 = 0
            int r0 = sizeof(r0)
            POINTER_SIZE = r0
            r0 = 1
            int r1 = sizeof(r0)
            LONG_SIZE = r1
            r2 = 2
            int r2 = sizeof(r2)
            WCHAR_SIZE = r2
            r2 = 3
            int r2 = sizeof(r2)
            SIZE_T_SIZE = r2
            r2 = 4
            int r2 = sizeof(r2)
            BOOL_SIZE = r2
            r2 = 5
            int r2 = sizeof(r2)
            LONG_DOUBLE_SIZE = r2
            initIDs()
            java.lang.String r2 = "jna.protected"
            boolean r2 = java.lang.Boolean.getBoolean(r2)
            if (r2 == 0) goto L_0x0124
            setProtected(r0)
        L_0x0124:
            boolean r0 = com.sun.jna.Platform.isSPARC()
            r2 = 8
            if (r0 != 0) goto L_0x0162
            boolean r0 = com.sun.jna.Platform.isWindows()
            if (r0 != 0) goto L_0x0162
            boolean r0 = com.sun.jna.Platform.isLinux()
            if (r0 == 0) goto L_0x0150
            boolean r0 = com.sun.jna.Platform.isARM()
            if (r0 != 0) goto L_0x0162
            boolean r0 = com.sun.jna.Platform.isPPC()
            if (r0 != 0) goto L_0x0162
            boolean r0 = com.sun.jna.Platform.isMIPS()
            if (r0 != 0) goto L_0x0162
            boolean r0 = com.sun.jna.Platform.isLoongArch()
            if (r0 != 0) goto L_0x0162
        L_0x0150:
            boolean r0 = com.sun.jna.Platform.isAIX()
            if (r0 != 0) goto L_0x0162
            boolean r0 = com.sun.jna.Platform.isAndroid()
            if (r0 == 0) goto L_0x0163
            boolean r0 = com.sun.jna.Platform.isIntel()
            if (r0 != 0) goto L_0x0163
        L_0x0162:
            r1 = r2
        L_0x0163:
            MAX_ALIGNMENT = r1
            boolean r0 = com.sun.jna.Platform.isMac()
            if (r0 == 0) goto L_0x0172
            boolean r0 = com.sun.jna.Platform.isPPC()
            if (r0 == 0) goto L_0x0172
            goto L_0x0173
        L_0x0172:
            r2 = r1
        L_0x0173:
            MAX_PADDING = r2
            java.lang.String r0 = "jna.loaded"
            java.lang.String r1 = "true"
            java.lang.System.setProperty(r0, r1)
            com.sun.jna.Native$2 r0 = new com.sun.jna.Native$2
            r0.<init>()
            finalizer = r0
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            registeredClasses = r0
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            registeredLibraries = r0
            com.sun.jna.Native$7 r0 = new com.sun.jna.Native$7
            r0.<init>()
            nativeThreadTerminationFlag = r0
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            java.util.Map r0 = java.util.Collections.synchronizedMap(r0)
            nativeThreads = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Native.<clinit>():void");
    }

    private Native() {
    }

    private static native long _getDirectBufferPointer(Buffer buffer);

    private static native long _getPointer(long j2);

    private static Map<String, Object> cacheOptions(Class<?> cls, Map<String, ?> map, Object obj) {
        HashMap hashMap = new HashMap(map);
        hashMap.put(_OPTION_ENCLOSING_LIBRARY, cls);
        typeOptions.put(cls, hashMap);
        if (obj != null) {
            libraries.put(cls, new WeakReference(obj));
        }
        if (!cls.isInterface()) {
            Class<Library> cls2 = Library.class;
            if (cls2.isAssignableFrom(cls)) {
                Class[] interfaces = cls.getInterfaces();
                int length = interfaces.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Class cls3 = interfaces[i3];
                    if (cls2.isAssignableFrom(cls3)) {
                        cacheOptions(cls3, hashMap, obj);
                        break;
                    }
                    i3++;
                }
            }
        }
        return hashMap;
    }

    public static native void close(long j2);

    public static native synchronized long createNativeCallback(Callback callback, Method method, Class<?>[] clsArr, Class<?> cls, int i3, int i4, String str);

    public static boolean deleteLibrary(File file) {
        if (file.delete()) {
            return true;
        }
        markTemporaryFile(file);
        return false;
    }

    public static void detach(boolean z2) {
        Thread currentThread = Thread.currentThread();
        if (z2) {
            nativeThreads.remove(currentThread);
            Pointer pointer = nativeThreadTerminationFlag.get();
            setDetachState(true, 0);
            return;
        }
        Map<Thread, Pointer> map = nativeThreads;
        if (!map.containsKey(currentThread)) {
            Pointer pointer2 = nativeThreadTerminationFlag.get();
            map.put(currentThread, pointer2);
            setDetachState(false, pointer2.peer);
        }
    }

    /* access modifiers changed from: private */
    public static void dispose() {
        CallbackReference.disposeAll();
        Memory.disposeAll();
        NativeLibrary.disposeAll();
        unregisterAll();
        jnidispatchPath = null;
        System.setProperty("jna.loaded", "false");
    }

    public static File extractFromResourcePath(String str) throws IOException {
        return extractFromResourcePath(str, (ClassLoader) null);
    }

    public static native void ffi_call(long j2, long j3, long j4, long j5);

    public static native void ffi_free_closure(long j2);

    public static native long ffi_prep_cif(int i3, int i4, long j2, long j3);

    public static native long ffi_prep_closure(long j2, ffi_callback ffi_callback2);

    public static Class<?> findDirectMappedClass(Class<?> cls) {
        for (Method modifiers : cls.getDeclaredMethods()) {
            if ((modifiers.getModifiers() & 256) != 0) {
                return cls;
            }
        }
        int lastIndexOf = cls.getName().lastIndexOf("$");
        if (lastIndexOf != -1) {
            try {
                return findDirectMappedClass(Class.forName(cls.getName().substring(0, lastIndexOf), true, cls.getClassLoader()));
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new IllegalArgumentException(b.l("Can't determine class with native methods from the current context (", cls, ")"));
    }

    public static Class<?> findEnclosingLibraryClass(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        Map map = typeOptions.get(cls);
        if (map != null) {
            Class<?> cls2 = (Class) map.get(_OPTION_ENCLOSING_LIBRARY);
            return cls2 != null ? cls2 : cls;
        } else if (Library.class.isAssignableFrom(cls)) {
            return cls;
        } else {
            if (Callback.class.isAssignableFrom(cls)) {
                cls = CallbackReference.findCallbackClass(cls);
            }
            Class<?> findEnclosingLibraryClass = findEnclosingLibraryClass(cls.getDeclaringClass());
            return findEnclosingLibraryClass != null ? findEnclosingLibraryClass : findEnclosingLibraryClass(cls.getSuperclass());
        }
    }

    public static native long findSymbol(long j2, String str);

    public static native void free(long j2);

    public static native synchronized void freeNativeCallback(long j2);

    private static NativeMapped fromNative(Class<?> cls, Object obj) {
        return (NativeMapped) NativeMappedConverter.getInstance(cls).fromNative(obj, new FromNativeContext(cls));
    }

    private static native String getAPIChecksum();

    public static native byte getByte(Pointer pointer, long j2, long j3);

    public static byte[] getBytes(String str) {
        return getBytes(str, getDefaultStringEncoding());
    }

    public static Callback.UncaughtExceptionHandler getCallbackExceptionHandler() {
        return callbackExceptionHandler;
    }

    public static Class<?> getCallingClass() {
        Class<?>[] classContext = new SecurityManager() {
            public Class<?>[] getClassContext() {
                return super.getClassContext();
            }
        }.getClassContext();
        if (classContext == null) {
            throw new IllegalStateException("The SecurityManager implementation on this platform is broken; you must explicitly provide the class to register");
        } else if (classContext.length >= 4) {
            return classContext[3];
        } else {
            throw new IllegalStateException("This method must be called from the static initializer of a class");
        }
    }

    public static native char getChar(Pointer pointer, long j2, long j3);

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.charset.Charset getCharset(java.lang.String r3) {
        /*
            if (r3 == 0) goto L_0x0019
            java.nio.charset.Charset r3 = java.nio.charset.Charset.forName(r3)     // Catch:{ IllegalCharsetNameException | UnsupportedCharsetException -> 0x0007 }
            goto L_0x001a
        L_0x0007:
            r0 = move-exception
            java.util.logging.Logger r1 = LOG
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r0}
            java.lang.String r0 = "JNA Warning: Encoding ''{0}'' is unsupported ({1})"
            r1.log(r2, r0, r3)
        L_0x0019:
            r3 = 0
        L_0x001a:
            if (r3 != 0) goto L_0x0028
            java.util.logging.Logger r3 = LOG
            java.util.logging.Level r0 = java.util.logging.Level.WARNING
            java.nio.charset.Charset r1 = DEFAULT_CHARSET
            java.lang.String r2 = "JNA Warning: Using fallback encoding {0}"
            r3.log(r0, r2, r1)
            r3 = r1
        L_0x0028:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Native.getCharset(java.lang.String):java.nio.charset.Charset");
    }

    public static long getComponentID(Component component) throws HeadlessException {
        return AWT.getComponentID(component);
    }

    public static Pointer getComponentPointer(Component component) throws HeadlessException {
        return new Pointer(AWT.getComponentID(component));
    }

    private static int getConversion(Class<?> cls, TypeMapper typeMapper, boolean z2) {
        if (cls == Void.class) {
            cls = Void.TYPE;
        }
        Class<WString> cls2 = WString.class;
        Class<String> cls3 = String.class;
        if (typeMapper != null) {
            FromNativeConverter fromNativeConverter = typeMapper.getFromNativeConverter(cls);
            ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(cls);
            if (fromNativeConverter != null) {
                Class<?> nativeType = fromNativeConverter.nativeType();
                if (nativeType == cls3) {
                    return 24;
                }
                return nativeType == cls2 ? 25 : 23;
            } else if (toNativeConverter != null) {
                Class<?> nativeType2 = toNativeConverter.nativeType();
                if (nativeType2 == cls3) {
                    return 24;
                }
                return nativeType2 == cls2 ? 25 : 23;
            }
        }
        if (Pointer.class.isAssignableFrom(cls)) {
            return 1;
        }
        if (cls3 == cls) {
            return 2;
        }
        if (cls2.isAssignableFrom(cls)) {
            return 20;
        }
        if (Platform.HAS_BUFFERS && Buffers.isBuffer(cls)) {
            return 5;
        }
        if (Structure.class.isAssignableFrom(cls)) {
            return Structure.ByValue.class.isAssignableFrom(cls) ? 4 : 3;
        }
        if (cls.isArray()) {
            char charAt = cls.getName().charAt(1);
            if (charAt == 'F') {
                return 11;
            }
            if (charAt == 'S') {
                return 7;
            }
            if (charAt == 'Z') {
                return 13;
            }
            if (charAt == 'I') {
                return 9;
            }
            if (charAt == 'J') {
                return 10;
            }
            switch (charAt) {
                case 'B':
                    return 6;
                case 'C':
                    return 8;
                case 'D':
                    return 12;
            }
        }
        if (cls.isPrimitive()) {
            return cls == Boolean.TYPE ? 14 : 0;
        }
        if (Callback.class.isAssignableFrom(cls)) {
            return 15;
        }
        if (IntegerType.class.isAssignableFrom(cls)) {
            return 21;
        }
        if (PointerType.class.isAssignableFrom(cls)) {
            return 22;
        }
        if (NativeMapped.class.isAssignableFrom(cls)) {
            Class<?> nativeType3 = NativeMappedConverter.getInstance(cls).nativeType();
            if (nativeType3 == cls3) {
                return 18;
            }
            return nativeType3 == cls2 ? 19 : 17;
        } else if (JNIEnv.class == cls) {
            return 27;
        } else {
            return z2 ? 26 : -1;
        }
    }

    public static String getDefaultStringEncoding() {
        return System.getProperty("jna.encoding", DEFAULT_ENCODING);
    }

    public static Pointer getDirectBufferPointer(Buffer buffer) {
        long _getDirectBufferPointer = _getDirectBufferPointer(buffer);
        if (_getDirectBufferPointer == 0) {
            return null;
        }
        return new Pointer(_getDirectBufferPointer);
    }

    public static native ByteBuffer getDirectByteBuffer(Pointer pointer, long j2, long j3, long j4);

    public static native double getDouble(Pointer pointer, long j2, long j3);

    public static native float getFloat(Pointer pointer, long j2, long j3);

    public static native int getInt(Pointer pointer, long j2, long j3);

    public static native int getLastError();

    public static Map<String, Object> getLibraryOptions(Class<?> cls) {
        Map map;
        Map<Class<?>, Map<String, Object>> map2 = typeOptions;
        Map<String, Object> map3 = map2.get(cls);
        if (map3 != null) {
            return map3;
        }
        Class<?> findEnclosingLibraryClass = findEnclosingLibraryClass(cls);
        if (findEnclosingLibraryClass != null) {
            loadLibraryInstance(findEnclosingLibraryClass);
        } else {
            findEnclosingLibraryClass = cls;
        }
        Map<String, Object> map4 = map2.get(findEnclosingLibraryClass);
        if (map4 != null) {
            map2.put(cls, map4);
            return map4;
        }
        try {
            Field field = findEnclosingLibraryClass.getField("OPTIONS");
            field.setAccessible(true);
            map = (Map) field.get((Object) null);
            if (map != null) {
                HashMap hashMap = new HashMap(map);
                if (!hashMap.containsKey(Library.OPTION_TYPE_MAPPER)) {
                    hashMap.put(Library.OPTION_TYPE_MAPPER, lookupField(findEnclosingLibraryClass, "TYPE_MAPPER", TypeMapper.class));
                }
                if (!hashMap.containsKey(Library.OPTION_STRUCTURE_ALIGNMENT)) {
                    hashMap.put(Library.OPTION_STRUCTURE_ALIGNMENT, lookupField(findEnclosingLibraryClass, "STRUCTURE_ALIGNMENT", Integer.class));
                }
                if (!hashMap.containsKey(Library.OPTION_STRING_ENCODING)) {
                    hashMap.put(Library.OPTION_STRING_ENCODING, lookupField(findEnclosingLibraryClass, "STRING_ENCODING", String.class));
                }
                Map<String, Object> cacheOptions = cacheOptions(findEnclosingLibraryClass, hashMap, (Object) null);
                if (cls != findEnclosingLibraryClass) {
                    typeOptions.put(cls, cacheOptions);
                }
                return cacheOptions;
            }
            throw new IllegalStateException("Null options field");
        } catch (NoSuchFieldException unused) {
            map = Collections.emptyMap();
        } catch (Exception e3) {
            throw new IllegalArgumentException("OPTIONS must be a public field of type java.util.Map (" + e3 + "): " + findEnclosingLibraryClass);
        }
    }

    public static native long getLong(Pointer pointer, long j2, long j3);

    public static NativeLibrary getNativeLibrary(Library library) {
        if (library == null) {
            throw new IllegalArgumentException("null passed to getNativeLibrary");
        } else if (Proxy.isProxyClass(library.getClass())) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(library);
            if (invocationHandler instanceof Library.Handler) {
                return ((Library.Handler) invocationHandler).getNativeLibrary();
            }
            throw new IllegalArgumentException("Object is not a properly initialized Library interface instance");
        } else {
            throw new IllegalArgumentException("library object passed to getNativeLibrary in not a proxy");
        }
    }

    public static int getNativeSize(Class<?> cls, Object obj) {
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            if (length > 0) {
                return getNativeSize(cls.getComponentType(), Array.get(obj, 0)) * length;
            }
            throw new IllegalArgumentException(b.k("Arrays of length zero not allowed: ", cls));
        } else if (Structure.class.isAssignableFrom(cls) && !Structure.ByReference.class.isAssignableFrom(cls)) {
            return Structure.size(cls, (Structure) obj);
        } else {
            try {
                return getNativeSize(cls);
            } catch (IllegalArgumentException e3) {
                throw new IllegalArgumentException("The type \"" + cls.getName() + "\" is not supported: " + e3.getMessage());
            }
        }
    }

    private static native String getNativeVersion();

    public static Pointer getPointer(long j2) {
        long _getPointer = _getPointer(j2);
        if (_getPointer == 0) {
            return null;
        }
        return new Pointer(_getPointer);
    }

    public static native short getShort(Pointer pointer, long j2, long j3);

    public static String getSignature(Class<?> cls) {
        if (cls.isArray()) {
            return "[" + getSignature(cls.getComponentType());
        }
        if (cls.isPrimitive()) {
            if (cls == Void.TYPE) {
                return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
            }
            if (cls == Boolean.TYPE) {
                return "Z";
            }
            if (cls == Byte.TYPE) {
                return "B";
            }
            if (cls == Short.TYPE) {
                return ExifInterface.LATITUDE_SOUTH;
            }
            if (cls == Character.TYPE) {
                return "C";
            }
            if (cls == Integer.TYPE) {
                return "I";
            }
            if (cls == Long.TYPE) {
                return "J";
            }
            if (cls == Float.TYPE) {
                return "F";
            }
            if (cls == Double.TYPE) {
                return "D";
            }
        }
        return "L" + replace(JwtUtilsKt.JWT_DELIMITER, "/", cls.getName()) + ";";
    }

    public static String getString(Pointer pointer, long j2) {
        return getString(pointer, j2, getDefaultStringEncoding());
    }

    public static native byte[] getStringBytes(Pointer pointer, long j2, long j3);

    public static String getStringEncoding(Class<?> cls) {
        String str = (String) getLibraryOptions(cls).get(Library.OPTION_STRING_ENCODING);
        return str != null ? str : getDefaultStringEncoding();
    }

    public static int getStructureAlignment(Class<?> cls) {
        Integer num = (Integer) getLibraryOptions(cls).get(Library.OPTION_STRUCTURE_ALIGNMENT);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static File getTempDir() throws IOException {
        File file;
        File file2;
        String property = System.getProperty("jna.tmpdir");
        if (property != null) {
            file = new File(property);
            file.mkdirs();
        } else {
            file = new File(System.getProperty("java.io.tmpdir"));
            if (Platform.isMac()) {
                file2 = new File(System.getProperty("user.home"), "Library/Caches/JNA/temp");
            } else if (Platform.isLinux() || Platform.isSolaris() || Platform.isAIX() || Platform.isDragonFlyBSD() || Platform.isFreeBSD() || Platform.isNetBSD() || Platform.isOpenBSD() || Platform.iskFreeBSD()) {
                String str = System.getenv("XDG_CACHE_HOME");
                file2 = new File((str == null || str.trim().isEmpty()) ? new File(System.getProperty("user.home"), ".cache") : new File(str), "JNA/temp");
            } else {
                file2 = new File(file, "jna-" + System.getProperty("user.name").hashCode());
            }
            file2.mkdirs();
            if (file2.exists() && file2.canWrite()) {
                file = file2;
            }
        }
        if (!file.exists()) {
            throw new IOException("JNA temporary directory '" + file + "' does not exist");
        } else if (file.canWrite()) {
            return file;
        } else {
            throw new IOException("JNA temporary directory '" + file + "' is not writable");
        }
    }

    public static Pointer getTerminationFlag(Thread thread) {
        return nativeThreads.get(thread);
    }

    public static TypeMapper getTypeMapper(Class<?> cls) {
        return (TypeMapper) getLibraryOptions(cls).get(Library.OPTION_TYPE_MAPPER);
    }

    public static String getWebStartLibraryPath(String str) {
        if (System.getProperty("javawebstart.version") == null) {
            return null;
        }
        try {
            String str2 = (String) ((Method) AccessController.doPrivileged(new PrivilegedAction<Method>() {
                public Method run() {
                    try {
                        Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLibrary", new Class[]{String.class});
                        declaredMethod.setAccessible(true);
                        return declaredMethod;
                    } catch (Exception unused) {
                        return null;
                    }
                }
            })).invoke(Native.class.getClassLoader(), new Object[]{str});
            if (str2 != null) {
                return new File(str2).getParent();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static native String getWideString(Pointer pointer, long j2, long j3);

    public static native long getWindowHandle0(Component component);

    public static long getWindowID(Window window) throws HeadlessException {
        return AWT.getWindowID(window);
    }

    public static Pointer getWindowPointer(Window window) throws HeadlessException {
        return new Pointer(AWT.getWindowID(window));
    }

    public static native long indexOf(Pointer pointer, long j2, long j3, byte b3);

    private static native void initIDs();

    public static native int initialize_ffi_type(long j2);

    public static native double invokeDouble(Function function, long j2, int i3, Object[] objArr);

    public static native float invokeFloat(Function function, long j2, int i3, Object[] objArr);

    public static native int invokeInt(Function function, long j2, int i3, Object[] objArr);

    public static native long invokeLong(Function function, long j2, int i3, Object[] objArr);

    public static native Object invokeObject(Function function, long j2, int i3, Object[] objArr);

    public static native long invokePointer(Function function, long j2, int i3, Object[] objArr);

    public static Structure invokeStructure(Function function, long j2, int i3, Object[] objArr, Structure structure) {
        invokeStructure(function, j2, i3, objArr, structure.getPointer().peer, structure.getTypeInfo().peer);
        return structure;
    }

    private static native void invokeStructure(Function function, long j2, int i3, Object[] objArr, long j3, long j4);

    public static native void invokeVoid(Function function, long j2, int i3, Object[] objArr);

    public static boolean isCompatibleVersion(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split.length < 3 || split2.length < 3) {
            return false;
        }
        return Integer.parseInt(split[0]) == Integer.parseInt(split2[0]) && Integer.parseInt(split[1]) <= Integer.parseInt(split2[1]);
    }

    public static native synchronized boolean isProtected();

    public static boolean isSupportedNativeType(Class<?> cls) {
        if (Structure.class.isAssignableFrom(cls)) {
            return true;
        }
        try {
            return getNativeSize(cls) != 0;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean isUnpacked(File file) {
        return file.getName().startsWith(JNA_TMPLIB_PREFIX);
    }

    public static <T extends Library> T load(Class<T> cls) {
        return load((String) null, cls);
    }

    @Deprecated
    public static <T> T loadLibrary(Class<T> cls) {
        return loadLibrary((String) null, cls);
    }

    private static void loadLibraryInstance(Class<?> cls) {
        if (cls != null && !libraries.containsKey(cls)) {
            try {
                Field[] fields = cls.getFields();
                int i3 = 0;
                while (i3 < fields.length) {
                    Field field = fields[i3];
                    if (field.getType() != cls || !Modifier.isStatic(field.getModifiers())) {
                        i3++;
                    } else {
                        field.setAccessible(true);
                        libraries.put(cls, new WeakReference(field.get((Object) null)));
                        return;
                    }
                }
            } catch (Exception e3) {
                throw new IllegalArgumentException("Could not access instance of " + cls + " (" + e3 + ")");
            }
        }
    }

    private static void loadNativeDispatchLibrary() {
        if (!Boolean.getBoolean("jna.nounpack")) {
            try {
                removeTemporaryFiles();
            } catch (IOException e3) {
                LOG.log(Level.WARNING, "JNA Warning: IOException removing temporary files", e3);
            }
        }
        String property = System.getProperty("jna.boot.library.name", "jnidispatch");
        String property2 = System.getProperty("jna.boot.library.path");
        if (property2 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property2, File.pathSeparator);
            while (stringTokenizer.hasMoreTokens()) {
                File file = new File(new File(stringTokenizer.nextToken()), System.mapLibraryName(property).replace(".dylib", ".jnilib"));
                String absolutePath = file.getAbsolutePath();
                Logger logger = LOG;
                Level level = DEBUG_JNA_LOAD_LEVEL;
                logger.log(level, "Looking in {0}", absolutePath);
                if (file.exists()) {
                    try {
                        logger.log(level, "Trying {0}", absolutePath);
                        System.setProperty("jnidispatch.path", absolutePath);
                        System.load(absolutePath);
                        jnidispatchPath = absolutePath;
                        logger.log(level, "Found jnidispatch at {0}", absolutePath);
                        return;
                    } catch (UnsatisfiedLinkError unused) {
                    }
                }
                if (Platform.isMac()) {
                    String str = "dylib";
                    String str2 = "jnilib";
                    if (!absolutePath.endsWith(str)) {
                        String str3 = str2;
                        str2 = str;
                        str = str3;
                    }
                    String str4 = absolutePath.substring(0, absolutePath.lastIndexOf(str)) + str2;
                    Logger logger2 = LOG;
                    Level level2 = DEBUG_JNA_LOAD_LEVEL;
                    logger2.log(level2, "Looking in {0}", str4);
                    if (new File(str4).exists()) {
                        try {
                            logger2.log(level2, "Trying {0}", str4);
                            System.setProperty("jnidispatch.path", str4);
                            System.load(str4);
                            jnidispatchPath = str4;
                            logger2.log(level2, "Found jnidispatch at {0}", str4);
                            return;
                        } catch (UnsatisfiedLinkError e4) {
                            Logger logger3 = LOG;
                            Level level3 = Level.WARNING;
                            StringBuilder w2 = a.w("File found at ", str4, " but not loadable: ");
                            w2.append(e4.getMessage());
                            logger3.log(level3, w2.toString(), e4);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (!Boolean.parseBoolean(System.getProperty("jna.nosys", "true")) || Platform.isAndroid()) {
            try {
                Logger logger4 = LOG;
                Level level4 = DEBUG_JNA_LOAD_LEVEL;
                logger4.log(level4, "Trying (via loadLibrary) {0}", property);
                System.loadLibrary(property);
                logger4.log(level4, "Found jnidispatch on system path");
                return;
            } catch (UnsatisfiedLinkError unused2) {
            }
        }
        if (!Boolean.getBoolean("jna.noclasspath")) {
            loadNativeDispatchLibraryFromClasspath();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to locate JNA native support library");
    }

    private static void loadNativeDispatchLibraryFromClasspath() {
        try {
            String replace = System.mapLibraryName("jnidispatch").replace(".dylib", ".jnilib");
            if (Platform.isAIX()) {
                replace = "libjnidispatch.a";
            }
            File extractFromResourcePath = extractFromResourcePath("/com/sun/jna/" + Platform.RESOURCE_PREFIX + "/" + replace, Native.class.getClassLoader());
            if (extractFromResourcePath == null) {
                if (extractFromResourcePath == null) {
                    throw new UnsatisfiedLinkError("Could not find JNA native support");
                }
            }
            Logger logger = LOG;
            Level level = DEBUG_JNA_LOAD_LEVEL;
            logger.log(level, "Trying {0}", extractFromResourcePath.getAbsolutePath());
            System.setProperty("jnidispatch.path", extractFromResourcePath.getAbsolutePath());
            System.load(extractFromResourcePath.getAbsolutePath());
            String absolutePath = extractFromResourcePath.getAbsolutePath();
            jnidispatchPath = absolutePath;
            logger.log(level, "Found jnidispatch at {0}", absolutePath);
            if (isUnpacked(extractFromResourcePath) && !Boolean.getBoolean("jnidispatch.preserve")) {
                deleteLibrary(extractFromResourcePath);
            }
        } catch (IOException e3) {
            throw new UnsatisfiedLinkError(e3.getMessage());
        }
    }

    private static Object lookupField(Class<?> cls, String str, Class<?> cls2) {
        try {
            Field field = cls.getField(str);
            field.setAccessible(true);
            return field.get((Object) null);
        } catch (NoSuchFieldException unused) {
            return null;
        } catch (Exception e3) {
            StringBuilder q2 = A.a.q(str, " must be a public field of type ");
            q2.append(cls2.getName());
            q2.append(" (");
            q2.append(e3);
            q2.append("): ");
            q2.append(cls);
            throw new IllegalArgumentException(q2.toString());
        }
    }

    public static void main(String[] strArr) {
        Package packageR = Native.class.getPackage();
        String str = "Java Native Access (JNA)";
        String specificationTitle = packageR != null ? packageR.getSpecificationTitle() : str;
        if (specificationTitle != null) {
            str = specificationTitle;
        }
        String str2 = Version.VERSION;
        String specificationVersion = packageR != null ? packageR.getSpecificationVersion() : str2;
        if (specificationVersion != null) {
            str2 = specificationVersion;
        }
        String n2 = a.n(str, " API Version ", str2);
        PrintStream printStream = System.out;
        printStream.println(n2);
        String str3 = "5.17.0 (package information missing)";
        String implementationVersion = packageR != null ? packageR.getImplementationVersion() : str3;
        if (implementationVersion != null) {
            str3 = implementationVersion;
        }
        printStream.println("Version: ".concat(str3));
        printStream.println(" Native: " + getNativeVersion() + " (" + getAPIChecksum() + ")");
        StringBuilder sb = new StringBuilder(" Prefix: ");
        sb.append(Platform.RESOURCE_PREFIX);
        printStream.println(sb.toString());
    }

    public static native long malloc(long j2);

    public static void markTemporaryFile(File file) {
        try {
            File parentFile = file.getParentFile();
            new File(parentFile, file.getName() + ".x").createNewFile();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    private static Class<?> nativeType(Class<?> cls) {
        return NativeMappedConverter.getInstance(cls).nativeType();
    }

    public static long open(String str) {
        return open(str, -1);
    }

    public static native long open(String str, int i3);

    public static native void read(Pointer pointer, long j2, long j3, byte[] bArr, int i3, int i4);

    public static native void read(Pointer pointer, long j2, long j3, char[] cArr, int i3, int i4);

    public static native void read(Pointer pointer, long j2, long j3, double[] dArr, int i3, int i4);

    public static native void read(Pointer pointer, long j2, long j3, float[] fArr, int i3, int i4);

    public static native void read(Pointer pointer, long j2, long j3, int[] iArr, int i3, int i4);

    public static native void read(Pointer pointer, long j2, long j3, long[] jArr, int i3, int i4);

    public static native void read(Pointer pointer, long j2, long j3, short[] sArr, int i3, int i4);

    public static void register(String str) {
        register(findDirectMappedClass(getCallingClass()), str);
    }

    private static native long registerMethod(Class<?> cls, String str, String str2, int[] iArr, long[] jArr, long[] jArr2, int i3, long j2, long j3, Method method, long j4, int i4, boolean z2, ToNativeConverter[] toNativeConverterArr, FromNativeConverter fromNativeConverter, String str3);

    public static boolean registered(Class<?> cls) {
        boolean containsKey;
        Map<Class<?>, long[]> map = registeredClasses;
        synchronized (map) {
            containsKey = map.containsKey(cls);
        }
        return containsKey;
    }

    public static void removeTemporaryFiles() throws IOException {
        File[] listFiles = getTempDir().listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.endsWith(".x") && str.startsWith(Native.JNA_TMPLIB_PREFIX);
            }
        });
        int i3 = 0;
        while (listFiles != null && i3 < listFiles.length) {
            File file = listFiles[i3];
            File file2 = new File(file.getParentFile(), b.y(file.getName(), 2, 0));
            if (!file2.exists() || file2.delete()) {
                file.delete();
            }
            i3++;
        }
    }

    public static String replace(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int indexOf = str3.indexOf(str);
            if (indexOf == -1) {
                sb.append(str3);
                return sb.toString();
            }
            com.appsamurai.storyly.exoplayer2.core.drm.b.c(str3, 0, indexOf, sb, str2);
            str3 = str3.substring(str.length() + indexOf);
        }
    }

    public static native void setByte(Pointer pointer, long j2, long j3, byte b3);

    public static void setCallbackExceptionHandler(Callback.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            uncaughtExceptionHandler = DEFAULT_HANDLER;
        }
        callbackExceptionHandler = uncaughtExceptionHandler;
    }

    public static void setCallbackThreadInitializer(Callback callback, CallbackThreadInitializer callbackThreadInitializer) {
        CallbackReference.setCallbackThreadInitializer(callback, callbackThreadInitializer);
    }

    public static native void setChar(Pointer pointer, long j2, long j3, char c3);

    private static native void setDetachState(boolean z2, long j2);

    public static native void setDouble(Pointer pointer, long j2, long j3, double d2);

    public static native void setFloat(Pointer pointer, long j2, long j3, float f2);

    public static native void setInt(Pointer pointer, long j2, long j3, int i3);

    public static native void setLastError(int i3);

    public static native void setLong(Pointer pointer, long j2, long j3, long j4);

    public static native void setMemory(Pointer pointer, long j2, long j3, long j4, byte b3);

    public static native void setPointer(Pointer pointer, long j2, long j3, long j4);

    public static native synchronized void setProtected(boolean z2);

    public static native void setShort(Pointer pointer, long j2, long j3, short s3);

    public static native void setWideString(Pointer pointer, long j2, long j3, String str);

    private static native int sizeof(int i3);

    public static Library synchronizedLibrary(final Library library) {
        Class<?> cls = library.getClass();
        if (Proxy.isProxyClass(cls)) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(library);
            if (invocationHandler instanceof Library.Handler) {
                final Library.Handler handler = (Library.Handler) invocationHandler;
                return (Library) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new InvocationHandler() {
                    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                        Object invoke;
                        synchronized (Library.Handler.this.getNativeLibrary()) {
                            invoke = Library.Handler.this.invoke(library, method, objArr);
                        }
                        return invoke;
                    }
                });
            }
            throw new IllegalArgumentException("Unrecognized proxy handler: " + invocationHandler);
        }
        throw new IllegalArgumentException("Library must be a proxy class");
    }

    public static byte[] toByteArray(String str) {
        return toByteArray(str, getDefaultStringEncoding());
    }

    public static char[] toCharArray(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[(charArray.length + 1)];
        System.arraycopy(charArray, 0, cArr, 0, charArray.length);
        return cArr;
    }

    private static Object toNative(ToNativeConverter toNativeConverter, Object obj) {
        return toNativeConverter.toNative(obj, new ToNativeContext());
    }

    public static String toString(byte[] bArr) {
        return toString(bArr, getDefaultStringEncoding());
    }

    public static List<String> toStringList(char[] cArr) {
        return toStringList(cArr, 0, cArr.length);
    }

    public static void unregister() {
        unregister(findDirectMappedClass(getCallingClass()));
    }

    private static native void unregister(Class<?> cls, long[] jArr);

    private static void unregisterAll() {
        Map<Class<?>, long[]> map = registeredClasses;
        synchronized (map) {
            try {
                for (Map.Entry next : map.entrySet()) {
                    unregister((Class) next.getKey(), (long[]) next.getValue());
                }
                registeredClasses.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static native void write(Pointer pointer, long j2, long j3, byte[] bArr, int i3, int i4);

    public static native void write(Pointer pointer, long j2, long j3, char[] cArr, int i3, int i4);

    public static native void write(Pointer pointer, long j2, long j3, double[] dArr, int i3, int i4);

    public static native void write(Pointer pointer, long j2, long j3, float[] fArr, int i3, int i4);

    public static native void write(Pointer pointer, long j2, long j3, int[] iArr, int i3, int i4);

    public static native void write(Pointer pointer, long j2, long j3, long[] jArr, int i3, int i4);

    public static native void write(Pointer pointer, long j2, long j3, short[] sArr, int i3, int i4);

    /* JADX WARNING: Can't wrap try/catch for region: R(6:82|83|84|85|86|103) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:84:0x01bd */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01ea A[SYNTHETIC, Splitter:B:95:0x01ea] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File extractFromResourcePath(java.lang.String r10, java.lang.ClassLoader r11) throws java.io.IOException {
        /*
            boolean r0 = DEBUG_LOAD
            if (r0 != 0) goto L_0x0014
            boolean r0 = DEBUG_JNA_LOAD
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = "jnidispatch"
            boolean r0 = r10.contains(r0)
            if (r0 == 0) goto L_0x0011
            goto L_0x0014
        L_0x0011:
            java.util.logging.Level r0 = java.util.logging.Level.FINE
            goto L_0x0016
        L_0x0014:
            java.util.logging.Level r0 = java.util.logging.Level.INFO
        L_0x0016:
            if (r11 != 0) goto L_0x0028
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r11 = r11.getContextClassLoader()
            if (r11 != 0) goto L_0x0028
            java.lang.Class<com.sun.jna.Native> r11 = com.sun.jna.Native.class
            java.lang.ClassLoader r11 = r11.getClassLoader()
        L_0x0028:
            java.util.logging.Logger r1 = LOG
            java.lang.String r2 = "Looking in classpath from {0} for {1}"
            java.lang.Object[] r3 = new java.lang.Object[]{r11, r10}
            r1.log(r0, r2, r3)
            java.lang.String r2 = "/"
            boolean r3 = r10.startsWith(r2)
            if (r3 == 0) goto L_0x003d
            r3 = r10
            goto L_0x0041
        L_0x003d:
            java.lang.String r3 = com.sun.jna.NativeLibrary.mapSharedLibraryName(r10)
        L_0x0041:
            boolean r4 = r10.startsWith(r2)
            if (r4 == 0) goto L_0x0049
            r4 = r10
            goto L_0x0054
        L_0x0049:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = com.sun.jna.Platform.RESOURCE_PREFIX
            java.lang.String r4 = androidx.camera.camera2.internal.C0118y.j(r4, r5, r2, r3)
        L_0x0054:
            boolean r5 = r4.startsWith(r2)
            r6 = 1
            if (r5 == 0) goto L_0x005f
            java.lang.String r4 = r4.substring(r6)
        L_0x005f:
            java.net.URL r5 = r11.getResource(r4)
            if (r5 != 0) goto L_0x00e1
            java.lang.String r7 = com.sun.jna.Platform.RESOURCE_PREFIX
            boolean r8 = r4.startsWith(r7)
            if (r8 == 0) goto L_0x0097
            java.lang.String r2 = "darwin"
            boolean r2 = r7.startsWith(r2)
            if (r2 == 0) goto L_0x0090
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "darwin/"
            r2.<init>(r5)
            int r5 = r7.length()
            int r5 = r5 + r6
            java.lang.String r5 = r4.substring(r5)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.net.URL r5 = r11.getResource(r2)
        L_0x0090:
            if (r5 != 0) goto L_0x00e1
            java.net.URL r5 = r11.getResource(r3)
            goto L_0x00e1
        L_0x0097:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "com/sun/jna/"
            r8.<init>(r9)
            r8.append(r7)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            boolean r2 = r4.startsWith(r2)
            if (r2 == 0) goto L_0x00e1
            java.lang.String r2 = "com/sun/jna/darwin"
            boolean r8 = r7.startsWith(r2)
            if (r8 == 0) goto L_0x00db
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r9)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            int r2 = r2.length()
            int r2 = r2 + r6
            java.lang.String r2 = r4.substring(r2)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            java.net.URL r5 = r11.getResource(r2)
        L_0x00db:
            if (r5 != 0) goto L_0x00e1
            java.net.URL r5 = r11.getResource(r3)
        L_0x00e1:
            if (r5 != 0) goto L_0x010b
            java.lang.String r10 = "java.class.path"
            java.lang.String r10 = java.lang.System.getProperty(r10)
            boolean r0 = r11 instanceof java.net.URLClassLoader
            if (r0 == 0) goto L_0x00fb
            java.net.URLClassLoader r11 = (java.net.URLClassLoader) r11
            java.net.URL[] r10 = r11.getURLs()
            java.util.List r10 = java.util.Arrays.asList(r10)
            java.lang.String r10 = r10.toString()
        L_0x00fb:
            java.io.IOException r11 = new java.io.IOException
            java.lang.String r0 = "Native library ("
            java.lang.String r1 = ") not found in resource path ("
            java.lang.String r2 = ")"
            java.lang.String r10 = androidx.camera.camera2.internal.C0118y.g(r0, r4, r1, r10, r2)
            r11.<init>(r10)
            throw r11
        L_0x010b:
            java.lang.String r11 = "Found library resource at {0}"
            r1.log(r0, r11, r5)
            java.lang.String r11 = r5.getProtocol()
            java.lang.String r11 = r11.toLowerCase()
            java.lang.String r2 = "file"
            boolean r11 = r11.equals(r2)
            if (r11 == 0) goto L_0x0164
            java.io.File r10 = new java.io.File     // Catch:{ URISyntaxException -> 0x012f }
            java.net.URI r11 = new java.net.URI     // Catch:{ URISyntaxException -> 0x012f }
            java.lang.String r1 = r5.toString()     // Catch:{ URISyntaxException -> 0x012f }
            r11.<init>(r1)     // Catch:{ URISyntaxException -> 0x012f }
            r10.<init>(r11)     // Catch:{ URISyntaxException -> 0x012f }
            goto L_0x0138
        L_0x012f:
            java.io.File r10 = new java.io.File
            java.lang.String r11 = r5.getPath()
            r10.<init>(r11)
        L_0x0138:
            java.util.logging.Logger r11 = LOG
            java.lang.String r1 = "Looking in {0}"
            java.lang.String r2 = r10.getAbsolutePath()
            r11.log(r0, r1, r2)
            boolean r11 = r10.exists()
            if (r11 == 0) goto L_0x014b
            goto L_0x01fb
        L_0x014b:
            java.io.IOException r10 = new java.io.IOException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "File URL "
            r11.<init>(r0)
            r11.append(r5)
            java.lang.String r0 = " could not be properly decoded"
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x0164:
            java.lang.String r11 = "jna.nounpack"
            boolean r11 = java.lang.Boolean.getBoolean(r11)
            r2 = 0
            if (r11 != 0) goto L_0x01fa
            java.io.InputStream r11 = r5.openStream()
            if (r11 == 0) goto L_0x01ee
            java.io.File r3 = getTempDir()     // Catch:{ IOException -> 0x0184 }
            java.lang.String r4 = "jna"
            boolean r5 = com.sun.jna.Platform.isWindows()     // Catch:{ IOException -> 0x0184 }
            if (r5 == 0) goto L_0x0186
            java.lang.String r5 = ".dll"
            goto L_0x0187
        L_0x0182:
            r10 = move-exception
            goto L_0x01e5
        L_0x0184:
            r0 = move-exception
            goto L_0x01c2
        L_0x0186:
            r5 = r2
        L_0x0187:
            java.io.File r3 = java.io.File.createTempFile(r4, r5, r3)     // Catch:{ IOException -> 0x0184 }
            java.lang.String r4 = "jnidispatch.preserve"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch:{ IOException -> 0x0184 }
            if (r4 != 0) goto L_0x0196
            r3.deleteOnExit()     // Catch:{ IOException -> 0x0184 }
        L_0x0196:
            java.lang.String r4 = "Extracting library to {0}"
            java.lang.String r5 = r3.getAbsolutePath()     // Catch:{ IOException -> 0x0184 }
            r1.log(r0, r4, r5)     // Catch:{ IOException -> 0x0184 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0184 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0184 }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r1]     // Catch:{ IOException -> 0x01b6, all -> 0x01b3 }
        L_0x01a8:
            r4 = 0
            int r5 = r11.read(r2, r4, r1)     // Catch:{ IOException -> 0x01b6, all -> 0x01b3 }
            if (r5 <= 0) goto L_0x01ba
            r0.write(r2, r4, r5)     // Catch:{ IOException -> 0x01b6, all -> 0x01b3 }
            goto L_0x01a8
        L_0x01b3:
            r10 = move-exception
            r2 = r0
            goto L_0x01e5
        L_0x01b6:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x01c2
        L_0x01ba:
            r11.close()     // Catch:{ IOException -> 0x01bd }
        L_0x01bd:
            r0.close()     // Catch:{ IOException -> 0x01c0 }
        L_0x01c0:
            r10 = r3
            goto L_0x01fb
        L_0x01c2:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0182 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0182 }
            r3.<init>()     // Catch:{ all -> 0x0182 }
            java.lang.String r4 = "Failed to create temporary file for "
            r3.append(r4)     // Catch:{ all -> 0x0182 }
            r3.append(r10)     // Catch:{ all -> 0x0182 }
            java.lang.String r10 = " library: "
            r3.append(r10)     // Catch:{ all -> 0x0182 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ all -> 0x0182 }
            r3.append(r10)     // Catch:{ all -> 0x0182 }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x0182 }
            r1.<init>(r10)     // Catch:{ all -> 0x0182 }
            throw r1     // Catch:{ all -> 0x0182 }
        L_0x01e5:
            r11.close()     // Catch:{ IOException -> 0x01e8 }
        L_0x01e8:
            if (r2 == 0) goto L_0x01ed
            r2.close()     // Catch:{ IOException -> 0x01ed }
        L_0x01ed:
            throw r10
        L_0x01ee:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r11 = "Can't obtain InputStream for "
            java.lang.String r11 = androidx.browser.trusted.c.a(r11, r4)
            r10.<init>(r11)
            throw r10
        L_0x01fa:
            r10 = r2
        L_0x01fb:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Native.extractFromResourcePath(java.lang.String, java.lang.ClassLoader):java.io.File");
    }

    private static NativeMapped fromNative(Method method, Object obj) {
        Class<?> returnType = method.getReturnType();
        return (NativeMapped) NativeMappedConverter.getInstance(returnType).fromNative(obj, new MethodResultContext(returnType, (Function) null, (Object[]) null, method));
    }

    public static byte[] getBytes(String str, String str2) {
        return getBytes(str, getCharset(str2));
    }

    public static String getString(Pointer pointer, long j2, String str) {
        byte[] stringBytes = getStringBytes(pointer, pointer.peer, j2);
        if (str != null) {
            try {
                return new String(stringBytes, str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new String(stringBytes);
    }

    public static <T extends Library> T load(Class<T> cls, Map<String, ?> map) {
        return load((String) null, cls, map);
    }

    @Deprecated
    public static <T> T loadLibrary(Class<T> cls, Map<String, ?> map) {
        return loadLibrary((String) null, cls, map);
    }

    public static void register(NativeLibrary nativeLibrary) {
        register(findDirectMappedClass(getCallingClass()), nativeLibrary);
    }

    public static byte[] toByteArray(String str, String str2) {
        return toByteArray(str, getCharset(str2));
    }

    public static String toString(byte[] bArr, String str) {
        return toString(bArr, getCharset(str));
    }

    public static List<String> toStringList(char[] cArr, int i3, int i4) {
        ArrayList arrayList = new ArrayList();
        int i5 = i4 + i3;
        int i6 = i3;
        while (i3 < i5) {
            if (cArr[i3] == 0) {
                if (i6 == i3) {
                    return arrayList;
                }
                arrayList.add(new String(cArr, i6, i3 - i6));
                i6 = i3 + 1;
            }
            i3++;
        }
        if (i6 < i5) {
            arrayList.add(new String(cArr, i6, i5 - i6));
        }
        return arrayList;
    }

    public static void unregister(Class<?> cls) {
        Map<Class<?>, long[]> map = registeredClasses;
        synchronized (map) {
            try {
                long[] jArr = map.get(cls);
                if (jArr != null) {
                    unregister(cls, jArr);
                    map.remove(cls);
                    registeredLibraries.remove(cls);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static byte[] getBytes(String str, Charset charset) {
        return str.getBytes(charset);
    }

    public static <T extends Library> T load(String str, Class<T> cls) {
        return load(str, cls, Collections.emptyMap());
    }

    @Deprecated
    public static <T> T loadLibrary(String str, Class<T> cls) {
        return loadLibrary(str, cls, Collections.emptyMap());
    }

    public static void register(Class<?> cls, String str) {
        register(cls, NativeLibrary.getInstance(str, (Map<String, ?>) Collections.singletonMap(Library.OPTION_CLASSLOADER, cls.getClassLoader())));
    }

    public static byte[] toByteArray(String str, Charset charset) {
        byte[] bytes = getBytes(str, charset);
        byte[] bArr = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return bArr;
    }

    public static String toString(byte[] bArr, Charset charset) {
        int length = bArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            } else if (bArr[i3] == 0) {
                length = i3;
                break;
            } else {
                i3++;
            }
        }
        if (length == 0) {
            return "";
        }
        return new String(bArr, 0, length, charset);
    }

    private static Object fromNative(FromNativeConverter fromNativeConverter, Object obj, Method method) {
        return fromNativeConverter.fromNative(obj, new MethodResultContext(method.getReturnType(), (Function) null, (Object[]) null, method));
    }

    public static <T extends Library> T load(String str, Class<T> cls, Map<String, ?> map) {
        if (Library.class.isAssignableFrom(cls)) {
            Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new Library.Handler(str, cls, map));
            cacheOptions(cls, map, newProxyInstance);
            return (Library) cls.cast(newProxyInstance);
        }
        throw new IllegalArgumentException("Interface (" + cls.getSimpleName() + ") of library=" + str + " does not extend Library");
    }

    @Deprecated
    public static <T> T loadLibrary(String str, Class<T> cls, Map<String, ?> map) {
        if (Library.class.isAssignableFrom(cls)) {
            Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new Library.Handler(str, cls, map));
            cacheOptions(cls, map, newProxyInstance);
            return cls.cast(newProxyInstance);
        }
        throw new IllegalArgumentException("Interface (" + cls.getSimpleName() + ") of library=" + str + " does not extend Library");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00cc, code lost:
        r22 = r10;
        r24 = com.sun.jna.Structure.FFIType.get(com.sun.jna.Pointer.class).getPointer().peer;
        r9 = com.sun.jna.Structure.FFIType.get(com.sun.jna.NativeMappedConverter.getInstance(r2).nativeType()).getPointer().peer;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0246 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0235  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void register(java.lang.Class<?> r33, com.sun.jna.NativeLibrary r34) {
        /*
            r0 = r33
            r15 = r34
            java.lang.reflect.Method[] r1 = r33.getDeclaredMethods()
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.Map r2 = r34.getOptions()
            java.lang.String r3 = "type-mapper"
            java.lang.Object r3 = r2.get(r3)
            r14 = r3
            com.sun.jna.TypeMapper r14 = (com.sun.jna.TypeMapper) r14
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            java.lang.String r4 = "allow-objects"
            java.lang.Object r4 = r2.get(r4)
            boolean r12 = r3.equals(r4)
            r10 = 0
            cacheOptions(r0, r2, r10)
            int r2 = r1.length
            r20 = 0
            r3 = r20
        L_0x002f:
            if (r3 >= r2) goto L_0x0041
            r4 = r1[r3]
            int r5 = r4.getModifiers()
            r5 = r5 & 256(0x100, float:3.59E-43)
            if (r5 == 0) goto L_0x003e
            r13.add(r4)
        L_0x003e:
            int r3 = r3 + 1
            goto L_0x002f
        L_0x0041:
            int r11 = r13.size()
            long[] r8 = new long[r11]
            r9 = r20
        L_0x0049:
            if (r9 >= r11) goto L_0x0301
            java.lang.Object r1 = r13.get(r9)
            r7 = r1
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            java.lang.String r1 = "("
            java.lang.Class r2 = r7.getReturnType()
            java.lang.Class[] r3 = r7.getParameterTypes()
            int r4 = r3.length
            long[] r6 = new long[r4]
            int r4 = r3.length
            long[] r5 = new long[r4]
            int r4 = r3.length
            int[] r4 = new int[r4]
            int r10 = r3.length
            com.sun.jna.ToNativeConverter[] r10 = new com.sun.jna.ToNativeConverter[r10]
            r17 = r8
            int r8 = getConversion(r2, r14, r12)
            r18 = r1
            r1 = -1
            if (r8 == r1) goto L_0x02cf
            r1 = 3
            r21 = r9
            r9 = 4
            if (r8 == r1) goto L_0x0095
            if (r8 == r9) goto L_0x00f1
            switch(r8) {
                case 17: goto L_0x00cc;
                case 18: goto L_0x00cc;
                case 19: goto L_0x00cc;
                default: goto L_0x007e;
            }
        L_0x007e:
            switch(r8) {
                case 21: goto L_0x00cc;
                case 22: goto L_0x00cc;
                case 23: goto L_0x0099;
                case 24: goto L_0x0099;
                case 25: goto L_0x0099;
                case 26: goto L_0x0095;
                default: goto L_0x0081;
            }
        L_0x0081:
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r2)
            com.sun.jna.Pointer r1 = r1.getPointer()
            r22 = r10
            long r9 = r1.peer
        L_0x008d:
            r24 = r9
            r26 = r24
        L_0x0091:
            r28 = 0
            goto L_0x011a
        L_0x0095:
            r22 = r10
            goto L_0x010c
        L_0x0099:
            r22 = r10
            com.sun.jna.FromNativeConverter r1 = r14.getFromNativeConverter(r2)
            boolean r9 = r2.isPrimitive()
            if (r9 == 0) goto L_0x00a7
            r9 = r2
            goto L_0x00a9
        L_0x00a7:
            java.lang.Class<com.sun.jna.Pointer> r9 = com.sun.jna.Pointer.class
        L_0x00a9:
            com.sun.jna.Structure$FFIType r9 = com.sun.jna.Structure.FFIType.get(r9)
            com.sun.jna.Pointer r9 = r9.getPointer()
            long r9 = r9.peer
            java.lang.Class r24 = r1.nativeType()
            com.sun.jna.Structure$FFIType r24 = com.sun.jna.Structure.FFIType.get(r24)
            r25 = r1
            com.sun.jna.Pointer r1 = r24.getPointer()
            r26 = r9
            long r9 = r1.peer
            r28 = r25
            r24 = r26
            r26 = r9
            goto L_0x011a
        L_0x00cc:
            r22 = r10
            java.lang.Class<com.sun.jna.Pointer> r1 = com.sun.jna.Pointer.class
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r1)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r9 = r1.peer
            com.sun.jna.NativeMappedConverter r1 = com.sun.jna.NativeMappedConverter.getInstance(r2)
            java.lang.Class r1 = r1.nativeType()
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r1)
            com.sun.jna.Pointer r1 = r1.getPointer()
            r24 = r9
            long r9 = r1.peer
        L_0x00ee:
            r26 = r9
            goto L_0x0091
        L_0x00f1:
            r22 = r10
            java.lang.Class<com.sun.jna.Pointer> r1 = com.sun.jna.Pointer.class
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r1)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r9 = r1.peer
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r2)
            com.sun.jna.Pointer r1 = r1.getPointer()
            r24 = r9
            long r9 = r1.peer
            goto L_0x00ee
        L_0x010c:
            java.lang.Class<com.sun.jna.Pointer> r1 = com.sun.jna.Pointer.class
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r1)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r9 = r1.peer
            goto L_0x008d
        L_0x011a:
            r1 = r18
            r9 = r20
        L_0x011e:
            int r10 = r3.length
            if (r9 >= r10) goto L_0x0215
            r10 = r3[r9]
            java.lang.StringBuilder r1 = A.a.p(r1)
            r18 = r3
            java.lang.String r3 = getSignature(r10)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            int r3 = getConversion(r10, r14, r12)
            r4[r9] = r3
            r29 = r1
            r1 = -1
            if (r3 == r1) goto L_0x01ea
            r1 = 17
            if (r3 == r1) goto L_0x0163
            r1 = 18
            if (r3 == r1) goto L_0x0163
            r1 = 19
            if (r3 == r1) goto L_0x0163
            r1 = 21
            if (r3 != r1) goto L_0x0150
            goto L_0x0163
        L_0x0150:
            r1 = 23
            if (r3 == r1) goto L_0x015c
            r1 = 24
            if (r3 == r1) goto L_0x015c
            r1 = 25
            if (r3 != r1) goto L_0x016b
        L_0x015c:
            com.sun.jna.ToNativeConverter r1 = r14.getToNativeConverter(r10)
            r22[r9] = r1
            goto L_0x016b
        L_0x0163:
            com.sun.jna.NativeMappedConverter r1 = com.sun.jna.NativeMappedConverter.getInstance(r10)
            java.lang.Class r10 = r1.nativeType()
        L_0x016b:
            if (r3 == 0) goto L_0x01d0
            r1 = 4
            if (r3 == r1) goto L_0x01b3
            switch(r3) {
                case 17: goto L_0x01b3;
                case 18: goto L_0x01b3;
                case 19: goto L_0x01b3;
                default: goto L_0x0173;
            }
        L_0x0173:
            switch(r3) {
                case 21: goto L_0x01b3;
                case 22: goto L_0x01b3;
                case 23: goto L_0x0189;
                case 24: goto L_0x0189;
                case 25: goto L_0x0189;
                default: goto L_0x0176;
            }
        L_0x0176:
            java.lang.Class<com.sun.jna.Pointer> r3 = com.sun.jna.Pointer.class
            com.sun.jna.Structure$FFIType r3 = com.sun.jna.Structure.FFIType.get(r3)
            com.sun.jna.Pointer r3 = r3.getPointer()
            r23 = r2
            long r1 = r3.peer
            r6[r9] = r1
            r5[r9] = r1
            goto L_0x01e0
        L_0x0189:
            r23 = r2
            boolean r1 = r10.isPrimitive()
            if (r1 == 0) goto L_0x0192
            goto L_0x0194
        L_0x0192:
            java.lang.Class<com.sun.jna.Pointer> r10 = com.sun.jna.Pointer.class
        L_0x0194:
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r10)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r1 = r1.peer
            r5[r9] = r1
            r1 = r22[r9]
            java.lang.Class r1 = r1.nativeType()
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r1)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r1 = r1.peer
            r6[r9] = r1
            goto L_0x01e0
        L_0x01b3:
            r23 = r2
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r10)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r1 = r1.peer
            r6[r9] = r1
            java.lang.Class<com.sun.jna.Pointer> r1 = com.sun.jna.Pointer.class
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r1)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r1 = r1.peer
            r5[r9] = r1
            goto L_0x01e0
        L_0x01d0:
            r23 = r2
            com.sun.jna.Structure$FFIType r1 = com.sun.jna.Structure.FFIType.get(r10)
            com.sun.jna.Pointer r1 = r1.getPointer()
            long r1 = r1.peer
            r6[r9] = r1
            r5[r9] = r1
        L_0x01e0:
            int r9 = r9 + 1
            r3 = r18
            r2 = r23
            r1 = r29
            goto L_0x011e
        L_0x01ea:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r3 = " is not a supported argument type (in method "
            r2.append(r3)
            java.lang.String r3 = r7.getName()
            r2.append(r3)
            java.lang.String r3 = " in "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = ")"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0215:
            r23 = r2
            java.lang.String r2 = ")"
            java.lang.String r1 = android.support.v4.media.session.a.m(r1, r2)
            java.lang.StringBuilder r1 = A.a.p(r1)
            java.lang.String r2 = getSignature(r23)
            r1.append(r2)
            java.lang.String r10 = r1.toString()
            java.lang.Class[] r1 = r7.getExceptionTypes()
            r2 = r20
        L_0x0232:
            int r3 = r1.length
            if (r2 >= r3) goto L_0x0246
            java.lang.Class<com.sun.jna.LastErrorException> r3 = com.sun.jna.LastErrorException.class
            r9 = r1[r2]
            boolean r3 = r3.isAssignableFrom(r9)
            if (r3 == 0) goto L_0x0243
            r1 = 1
            r18 = r1
            goto L_0x0248
        L_0x0243:
            int r2 = r2 + 1
            goto L_0x0232
        L_0x0246:
            r18 = r20
        L_0x0248:
            java.lang.String r1 = r7.getName()
            com.sun.jna.Function r1 = r15.getFunction((java.lang.String) r1, (java.lang.reflect.Method) r7)
            java.lang.String r2 = r7.getName()     // Catch:{ NoSuchMethodError -> 0x029f }
            r19 = r13
            r29 = r14
            long r13 = r1.peer     // Catch:{ NoSuchMethodError -> 0x029f }
            int r23 = r1.getCallingConvention()     // Catch:{ NoSuchMethodError -> 0x029f }
            java.lang.String r9 = r1.encoding     // Catch:{ NoSuchMethodError -> 0x029f }
            r1 = r33
            r3 = r10
            r30 = r7
            r7 = r8
            r31 = r9
            r0 = r17
            r8 = r24
            r32 = r10
            r24 = r11
            r17 = r22
            r22 = 0
            r10 = r26
            r25 = r12
            r12 = r30
            r26 = r19
            r27 = r29
            r15 = r23
            r16 = r18
            r18 = r28
            r19 = r31
            long r1 = registerMethod(r1, r2, r3, r4, r5, r6, r7, r8, r10, r12, r13, r15, r16, r17, r18, r19)     // Catch:{ NoSuchMethodError -> 0x02a3 }
            r0[r21] = r1     // Catch:{ NoSuchMethodError -> 0x02a3 }
            int r9 = r21 + 1
            r15 = r34
            r8 = r0
            r10 = r22
            r11 = r24
            r12 = r25
            r13 = r26
            r14 = r27
            r0 = r33
            goto L_0x0049
        L_0x029f:
            r30 = r7
            r32 = r10
        L_0x02a3:
            java.lang.UnsatisfiedLinkError r0 = new java.lang.UnsatisfiedLinkError
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "No method "
            r1.<init>(r2)
            java.lang.String r2 = r30.getName()
            r1.append(r2)
            java.lang.String r2 = " with signature "
            r1.append(r2)
            r2 = r32
            r1.append(r2)
            java.lang.String r2 = " in "
            r1.append(r2)
            r2 = r33
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02cf:
            r23 = r2
            r30 = r7
            r2 = r0
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r3 = r23
            r1.append(r3)
            java.lang.String r3 = " is not a supported return type (in method "
            r1.append(r3)
            java.lang.String r3 = r30.getName()
            r1.append(r3)
            java.lang.String r3 = " in "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0301:
            r2 = r0
            r0 = r8
            java.util.Map<java.lang.Class<?>, long[]> r1 = registeredClasses
            monitor-enter(r1)
            r1.put(r2, r0)     // Catch:{ all -> 0x0312 }
            java.util.Map<java.lang.Class<?>, com.sun.jna.NativeLibrary> r0 = registeredLibraries     // Catch:{ all -> 0x0312 }
            r3 = r34
            r0.put(r2, r3)     // Catch:{ all -> 0x0312 }
            monitor-exit(r1)     // Catch:{ all -> 0x0312 }
            return
        L_0x0312:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0312 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Native.register(java.lang.Class, com.sun.jna.NativeLibrary):void");
    }

    public static String toString(char[] cArr) {
        int length = cArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            } else if (cArr[i3] == 0) {
                length = i3;
                break;
            } else {
                i3++;
            }
        }
        if (length == 0) {
            return "";
        }
        return new String(cArr, 0, length);
    }

    public static NativeLibrary getNativeLibrary(Class<?> cls) {
        NativeLibrary nativeLibrary;
        if (cls != null) {
            Class<?> findDirectMappedClass = findDirectMappedClass(cls);
            synchronized (registeredClasses) {
                try {
                    nativeLibrary = registeredLibraries.get(findDirectMappedClass);
                    if (nativeLibrary == null) {
                        throw new IllegalArgumentException("Class " + cls.getName() + " is not currently registered");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return nativeLibrary;
        }
        throw new IllegalArgumentException("null passed to getNativeLibrary");
    }

    public static int getNativeSize(Class<?> cls) {
        if (NativeMapped.class.isAssignableFrom(cls)) {
            cls = NativeMappedConverter.getInstance(cls).nativeType();
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return 4;
        }
        if (cls == Byte.TYPE || cls == Byte.class) {
            return 1;
        }
        if (cls == Short.TYPE || cls == Short.class) {
            return 2;
        }
        if (cls == Character.TYPE || cls == Character.class) {
            return WCHAR_SIZE;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return 4;
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return 8;
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return 4;
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return 8;
        }
        if (Structure.class.isAssignableFrom(cls)) {
            if (Structure.ByValue.class.isAssignableFrom(cls)) {
                return Structure.size(cls);
            }
            return POINTER_SIZE;
        } else if (Pointer.class.isAssignableFrom(cls) || ((Platform.HAS_BUFFERS && Buffers.isBuffer(cls)) || Callback.class.isAssignableFrom(cls) || String.class == cls || WString.class == cls)) {
            return POINTER_SIZE;
        } else {
            throw new IllegalArgumentException(b.g(cls, new StringBuilder("Native size for type \""), "\" is unknown"));
        }
    }
}
