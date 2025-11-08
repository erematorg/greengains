package com.sun.jna;

import A.a;
import com.sun.jna.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringArray extends Memory implements Function.PostCallRead {
    private String encoding;
    private List<NativeString> natives;
    private Object[] original;

    public StringArray(String[] strArr) {
        this(strArr, false);
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [com.sun.jna.WString] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void read() {
        /*
            r7 = this;
            java.lang.Object[] r0 = r7.original
            boolean r0 = r0 instanceof com.sun.jna.WString[]
            java.lang.String r1 = "--WIDE-STRING--"
            java.lang.String r2 = r7.encoding
            boolean r1 = r1.equals(r2)
            r2 = 0
        L_0x000d:
            java.lang.Object[] r3 = r7.original
            int r3 = r3.length
            if (r2 >= r3) goto L_0x003c
            int r3 = com.sun.jna.Native.POINTER_SIZE
            int r3 = r3 * r2
            long r3 = (long) r3
            com.sun.jna.Pointer r3 = r7.getPointer(r3)
            if (r3 == 0) goto L_0x0034
            r4 = 0
            if (r1 == 0) goto L_0x0025
            java.lang.String r3 = r3.getWideString(r4)
            goto L_0x002b
        L_0x0025:
            java.lang.String r6 = r7.encoding
            java.lang.String r3 = r3.getString(r4, r6)
        L_0x002b:
            if (r0 == 0) goto L_0x0035
            com.sun.jna.WString r4 = new com.sun.jna.WString
            r4.<init>(r3)
            r3 = r4
            goto L_0x0035
        L_0x0034:
            r3 = 0
        L_0x0035:
            java.lang.Object[] r4 = r7.original
            r4[r2] = r3
            int r2 = r2 + 1
            goto L_0x000d
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.StringArray.read():void");
    }

    public String toString() {
        StringBuilder p2 = a.p("--WIDE-STRING--".equals(this.encoding) ? "const wchar_t*[]" : "const char*[]");
        p2.append(Arrays.asList(this.original));
        return p2.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StringArray(String[] strArr, boolean z2) {
        this((Object[]) strArr, z2 ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }

    public StringArray(String[] strArr, String str) {
        this((Object[]) strArr, str);
    }

    public StringArray(WString[] wStringArr) {
        this((Object[]) wStringArr, "--WIDE-STRING--");
    }

    private StringArray(Object[] objArr, String str) {
        super((long) ((objArr.length + 1) * Native.POINTER_SIZE));
        this.natives = new ArrayList();
        this.original = objArr;
        this.encoding = str;
        int i3 = 0;
        while (true) {
            Pointer pointer = null;
            if (i3 < objArr.length) {
                Object obj = objArr[i3];
                if (obj != null) {
                    NativeString nativeString = new NativeString(obj.toString(), str);
                    this.natives.add(nativeString);
                    pointer = nativeString.getPointer();
                }
                setPointer((long) (Native.POINTER_SIZE * i3), pointer);
                i3++;
            } else {
                setPointer((long) (Native.POINTER_SIZE * objArr.length), (Pointer) null);
                return;
            }
        }
    }
}
