package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakq  reason: invalid package */
final class zzakq {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    public static String zza(zzakp zzakp, String str) {
        StringBuilder q2 = a.q("# ", str);
        zza(zzakp, q2, 0);
        return q2.toString();
    }

    private static void zza(int i3, StringBuilder sb) {
        while (i3 > 0) {
            char[] cArr = zza;
            int length = i3 > cArr.length ? cArr.length : i3;
            sb.append(cArr, 0, length);
            i3 -= length;
        }
    }

    public static void zza(StringBuilder sb, int i3, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zza2 : (List) obj) {
                zza(sb, i3, str, zza2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zza3 : ((Map) obj).entrySet()) {
                zza(sb, i3, str, zza3);
            }
        } else {
            sb.append(10);
            zza(i3, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i4 = 1; i4 < str.length(); i4++) {
                    char charAt = str.charAt(i4);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzaly.zza(zzaho.zza((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzaho) {
                sb.append(": \"");
                sb.append(zzaly.zza((zzaho) obj));
                sb.append('\"');
            } else if (obj instanceof zzaje) {
                sb.append(" {");
                zza((zzaje) obj, sb, i3 + 2);
                sb.append("\n");
                zza(i3, sb);
                sb.append(StringSubstitutor.DEFAULT_VAR_END);
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i5 = i3 + 2;
                zza(sb, i5, JwtUtilsKt.DID_METHOD_KEY, entry.getKey());
                zza(sb, i5, "value", entry.getValue());
                sb.append("\n");
                zza(i3, sb);
                sb.append(StringSubstitutor.DEFAULT_VAR_END);
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x014d, code lost:
        if (r5.containsKey("get" + androidx.constraintlayout.core.state.b.y(r9, 5, 0)) == false) goto L_0x014f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0175, code lost:
        if (((java.lang.Boolean) r7).booleanValue() == false) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0188, code lost:
        if (((java.lang.Integer) r7).intValue() == 0) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x019a, code lost:
        if (java.lang.Float.floatToRawIntBits(((java.lang.Float) r7).floatValue()) == 0) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01b0, code lost:
        if (java.lang.Double.doubleToRawLongBits(((java.lang.Double) r7).doubleValue()) == 0) goto L_0x0177;
     */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.p002firebaseauthapi.zzakp r20, java.lang.StringBuilder r21, int r22) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.util.TreeMap r5 = new java.util.TreeMap
            r5.<init>()
            java.lang.Class r6 = r20.getClass()
            java.lang.reflect.Method[] r6 = r6.getDeclaredMethods()
            int r7 = r6.length
            r8 = 0
            r9 = r8
        L_0x0020:
            java.lang.String r10 = "get"
            java.lang.String r11 = "has"
            java.lang.String r12 = "set"
            r13 = 3
            if (r9 >= r7) goto L_0x0088
            r14 = r6[r9]
            int r15 = r14.getModifiers()
            boolean r15 = java.lang.reflect.Modifier.isStatic(r15)
            if (r15 != 0) goto L_0x0085
            java.lang.String r15 = r14.getName()
            int r15 = r15.length()
            if (r15 < r13) goto L_0x0085
            java.lang.String r13 = r14.getName()
            boolean r12 = r13.startsWith(r12)
            if (r12 == 0) goto L_0x0051
            java.lang.String r10 = r14.getName()
            r3.add(r10)
            goto L_0x0085
        L_0x0051:
            int r12 = r14.getModifiers()
            boolean r12 = java.lang.reflect.Modifier.isPublic(r12)
            if (r12 == 0) goto L_0x0085
            java.lang.Class[] r12 = r14.getParameterTypes()
            int r12 = r12.length
            if (r12 != 0) goto L_0x0085
            java.lang.String r12 = r14.getName()
            boolean r11 = r12.startsWith(r11)
            if (r11 == 0) goto L_0x0074
            java.lang.String r10 = r14.getName()
            r4.put(r10, r14)
            goto L_0x0085
        L_0x0074:
            java.lang.String r11 = r14.getName()
            boolean r10 = r11.startsWith(r10)
            if (r10 == 0) goto L_0x0085
            java.lang.String r10 = r14.getName()
            r5.put(r10, r14)
        L_0x0085:
            int r9 = r9 + 1
            goto L_0x0020
        L_0x0088:
            java.util.Set r6 = r5.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0090:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x01fd
            java.lang.Object r7 = r6.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r9 = r7.getKey()
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r9 = r9.substring(r13)
            java.lang.String r14 = "List"
            boolean r15 = r9.endsWith(r14)
            if (r15 == 0) goto L_0x00e0
            java.lang.String r15 = "OrBuilderList"
            boolean r15 = r9.endsWith(r15)
            if (r15 != 0) goto L_0x00e0
            boolean r14 = r9.equals(r14)
            if (r14 != 0) goto L_0x00e0
            java.lang.Object r14 = r7.getValue()
            java.lang.reflect.Method r14 = (java.lang.reflect.Method) r14
            if (r14 == 0) goto L_0x00e0
            java.lang.Class r15 = r14.getReturnType()
            java.lang.Class<java.util.List> r13 = java.util.List.class
            boolean r13 = r15.equals(r13)
            if (r13 == 0) goto L_0x00e0
            r7 = 4
            java.lang.String r7 = androidx.constraintlayout.core.state.b.y(r9, r7, r8)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.android.gms.internal.p002firebaseauthapi.zzaje.zza((java.lang.reflect.Method) r14, (java.lang.Object) r0, (java.lang.Object[]) r9)
            zza(r1, r2, r7, r9)
            r13 = 3
            goto L_0x0090
        L_0x00e0:
            java.lang.String r13 = "Map"
            boolean r14 = r9.endsWith(r13)
            if (r14 == 0) goto L_0x0125
            boolean r13 = r9.equals(r13)
            if (r13 != 0) goto L_0x0125
            java.lang.Object r13 = r7.getValue()
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r13 == 0) goto L_0x0125
            java.lang.Class r14 = r13.getReturnType()
            java.lang.Class<java.util.Map> r15 = java.util.Map.class
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0125
            java.lang.Class<java.lang.Deprecated> r14 = java.lang.Deprecated.class
            boolean r14 = r13.isAnnotationPresent(r14)
            if (r14 != 0) goto L_0x0125
            int r14 = r13.getModifiers()
            boolean r14 = java.lang.reflect.Modifier.isPublic(r14)
            if (r14 == 0) goto L_0x0125
            r14 = 3
            java.lang.String r7 = androidx.constraintlayout.core.state.b.y(r9, r14, r8)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.android.gms.internal.p002firebaseauthapi.zzaje.zza((java.lang.reflect.Method) r13, (java.lang.Object) r0, (java.lang.Object[]) r9)
            zza(r1, r2, r7, r9)
        L_0x0122:
            r13 = r14
            goto L_0x0090
        L_0x0125:
            r14 = 3
            java.lang.String r13 = r12.concat(r9)
            boolean r13 = r3.contains(r13)
            if (r13 == 0) goto L_0x0122
            java.lang.String r13 = "Bytes"
            boolean r13 = r9.endsWith(r13)
            if (r13 == 0) goto L_0x014f
            r13 = 5
            java.lang.String r13 = androidx.constraintlayout.core.state.b.y(r9, r13, r8)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r10)
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            boolean r13 = r5.containsKey(r13)
            if (r13 != 0) goto L_0x0122
        L_0x014f:
            java.lang.Object r7 = r7.getValue()
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            java.lang.String r13 = r11.concat(r9)
            java.lang.Object r13 = r4.get(r13)
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r7 == 0) goto L_0x0122
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Object r7 = com.google.android.gms.internal.p002firebaseauthapi.zzaje.zza((java.lang.reflect.Method) r7, (java.lang.Object) r0, (java.lang.Object[]) r15)
            if (r13 != 0) goto L_0x01ea
            boolean r13 = r7 instanceof java.lang.Boolean
            r15 = 1
            if (r13 == 0) goto L_0x017d
            r13 = r7
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L_0x017a
        L_0x0177:
            r13 = r15
            goto L_0x01e5
        L_0x017a:
            r13 = r8
            goto L_0x01e5
        L_0x017d:
            boolean r13 = r7 instanceof java.lang.Integer
            if (r13 == 0) goto L_0x018b
            r13 = r7
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            if (r13 != 0) goto L_0x017a
            goto L_0x0177
        L_0x018b:
            boolean r13 = r7 instanceof java.lang.Float
            if (r13 == 0) goto L_0x019d
            r13 = r7
            java.lang.Float r13 = (java.lang.Float) r13
            float r13 = r13.floatValue()
            int r13 = java.lang.Float.floatToRawIntBits(r13)
            if (r13 != 0) goto L_0x017a
            goto L_0x0177
        L_0x019d:
            boolean r13 = r7 instanceof java.lang.Double
            if (r13 == 0) goto L_0x01b3
            r13 = r7
            java.lang.Double r13 = (java.lang.Double) r13
            double r16 = r13.doubleValue()
            long r16 = java.lang.Double.doubleToRawLongBits(r16)
            r18 = 0
            int r13 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r13 != 0) goto L_0x017a
            goto L_0x0177
        L_0x01b3:
            boolean r13 = r7 instanceof java.lang.String
            if (r13 == 0) goto L_0x01be
            java.lang.String r13 = ""
            boolean r13 = r7.equals(r13)
            goto L_0x01e5
        L_0x01be:
            boolean r13 = r7 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaho
            if (r13 == 0) goto L_0x01c9
            com.google.android.gms.internal.firebase-auth-api.zzaho r13 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza
            boolean r13 = r7.equals(r13)
            goto L_0x01e5
        L_0x01c9:
            boolean r13 = r7 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzakp
            if (r13 == 0) goto L_0x01d7
            r13 = r7
            com.google.android.gms.internal.firebase-auth-api.zzakp r13 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r13
            com.google.android.gms.internal.firebase-auth-api.zzakp r13 = r13.zzh()
            if (r7 != r13) goto L_0x017a
            goto L_0x0177
        L_0x01d7:
            boolean r13 = r7 instanceof java.lang.Enum
            if (r13 == 0) goto L_0x017a
            r13 = r7
            java.lang.Enum r13 = (java.lang.Enum) r13
            int r13 = r13.ordinal()
            if (r13 != 0) goto L_0x017a
            goto L_0x0177
        L_0x01e5:
            if (r13 != 0) goto L_0x01e8
            goto L_0x01f6
        L_0x01e8:
            r15 = r8
            goto L_0x01f6
        L_0x01ea:
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Object r13 = com.google.android.gms.internal.p002firebaseauthapi.zzaje.zza((java.lang.reflect.Method) r13, (java.lang.Object) r0, (java.lang.Object[]) r15)
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r15 = r13.booleanValue()
        L_0x01f6:
            if (r15 == 0) goto L_0x0122
            zza(r1, r2, r9, r7)
            goto L_0x0122
        L_0x01fd:
            boolean r3 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaje.zzb
            if (r3 == 0) goto L_0x0223
            r3 = r0
            com.google.android.gms.internal.firebase-auth-api.zzaje$zzb r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje.zzb) r3
            com.google.android.gms.internal.firebase-auth-api.zzaix<com.google.android.gms.internal.firebase-auth-api.zzaje$zze> r3 = r3.zzc
            java.util.Iterator r3 = r3.zzd()
            boolean r4 = r3.hasNext()
            if (r4 != 0) goto L_0x0211
            goto L_0x0223
        L_0x0211:
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r0 = r0.getKey()
            com.google.android.gms.internal.firebase-auth-api.zzaje$zze r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje.zze) r0
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0223:
            com.google.android.gms.internal.firebase-auth-api.zzaje r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje) r0
            com.google.android.gms.internal.firebase-auth-api.zzamf r0 = r0.zzb
            if (r0 == 0) goto L_0x022c
            r0.zza((java.lang.StringBuilder) r1, (int) r2)
        L_0x022c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakq.zza(com.google.android.gms.internal.firebase-auth-api.zzakp, java.lang.StringBuilder, int):void");
    }
}
