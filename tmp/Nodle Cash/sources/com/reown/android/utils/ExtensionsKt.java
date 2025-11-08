package com.reown.android.utils;

import android.net.Uri;
import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import com.reown.android.internal.common.exception.GenericException;
import com.reown.android.internal.common.exception.InvalidProjectIdException;
import com.reown.android.internal.common.exception.ProjectIdDoesNotExistException;
import com.reown.android.internal.common.exception.UnableToConnectToWebsocketException;
import com.reown.android.internal.common.exception.WalletConnectException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0001H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u000f\u001a\u00020\u0010*\u0004\u0018\u00010\u0011\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\n\u001a\u00020\u000b*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"strippedUrl", "", "isValidRelayServerUrl", "", "projectId", "toWalletConnectException", "Lcom/reown/android/internal/common/exception/WalletConnectException;", "", "getToWalletConnectException", "(Ljava/lang/Throwable;)Lcom/reown/android/internal/common/exception/WalletConnectException;", "DefaultId", "", "Lkotlin/Int$Companion;", "getDefaultId", "(Lkotlin/jvm/internal/IntCompanionObject;)I", "toClient", "Lcom/reown/android/Core$Model$AppMetaData;", "Lcom/reown/android/internal/common/model/AppMetaData;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class ExtensionsKt {
    public static final /* synthetic */ WalletConnectException getToWalletConnectException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        String message = th.getMessage();
        if (message != null && StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) "401", false, 2, (Object) null)) {
            return new UnableToConnectToWebsocketException(a.m(th.getMessage(), ". It's possible that JWT has expired. Try initializing the CoreClient again."));
        }
        String message2 = th.getMessage();
        if (message2 != null && StringsKt__StringsKt.contains$default((CharSequence) message2, (CharSequence) "404", false, 2, (Object) null)) {
            return new ProjectIdDoesNotExistException(c.a("Project ID doesn't exist: ", th.getMessage()));
        }
        String message3 = th.getMessage();
        return (message3 == null || !StringsKt__StringsKt.contains$default((CharSequence) message3, (CharSequence) "403", false, 2, (Object) null)) ? new GenericException(c.a("Error while connecting, please check your Internet connection or contact support: ", th.getMessage())) : new InvalidProjectIdException(c.a("Invalid project ID: ", th.getMessage()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        r2 = (r2 = android.net.Uri.parse(r2)).getQueryParameter("projectId");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ boolean isValidRelayServerUrl(java.lang.String r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            boolean r0 = kotlin.text.StringsKt.isBlank(r2)
            if (r0 != 0) goto L_0x0034
            android.net.Uri r2 = android.net.Uri.parse(r2)
            if (r2 == 0) goto L_0x0034
            java.lang.String r0 = "wss"
            java.lang.String r1 = "ws"
            java.lang.String[] r0 = new java.lang.String[]{r0, r1}
            java.lang.String r1 = r2.getScheme()
            boolean r0 = kotlin.collections.ArraysKt.contains((T[]) r0, r1)
            if (r0 == 0) goto L_0x0034
            java.lang.String r0 = "projectId"
            java.lang.String r2 = r2.getQueryParameter(r0)
            if (r2 == 0) goto L_0x0034
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r2 = 1
            goto L_0x0035
        L_0x0034:
            r2 = 0
        L_0x0035:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.utils.ExtensionsKt.isValidRelayServerUrl(java.lang.String):boolean");
    }

    public static final /* synthetic */ String projectId(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Uri parse = Uri.parse(str);
        Intrinsics.checkNotNull(parse);
        String queryParameter = parse.getQueryParameter("projectId");
        Intrinsics.checkNotNull(queryParameter);
        return queryParameter;
    }

    public static final /* synthetic */ String strippedUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Uri parse = Uri.parse(str);
        return a.n(parse.getScheme(), "://", parse.getAuthority());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        r5 = r12.getRedirect();
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.reown.android.Core.Model.AppMetaData toClient(@org.jetbrains.annotations.Nullable com.reown.android.internal.common.model.AppMetaData r12) {
        /*
            com.reown.android.Core$Model$AppMetaData r11 = new com.reown.android.Core$Model$AppMetaData
            if (r12 == 0) goto L_0x000d
            java.lang.String r0 = r12.getName()
            if (r0 != 0) goto L_0x000b
            goto L_0x000d
        L_0x000b:
            r1 = r0
            goto L_0x0014
        L_0x000d:
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            goto L_0x000b
        L_0x0014:
            if (r12 == 0) goto L_0x001f
            java.lang.String r0 = r12.getDescription()
            if (r0 != 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r2 = r0
            goto L_0x0026
        L_0x001f:
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            goto L_0x001d
        L_0x0026:
            if (r12 == 0) goto L_0x0031
            java.lang.String r0 = r12.getUrl()
            if (r0 != 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r3 = r0
            goto L_0x0038
        L_0x0031:
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            goto L_0x002f
        L_0x0038:
            if (r12 == 0) goto L_0x0043
            java.util.List r0 = r12.getIcons()
            if (r0 != 0) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r4 = r0
            goto L_0x0048
        L_0x0043:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x0041
        L_0x0048:
            r0 = 0
            if (r12 == 0) goto L_0x0056
            com.reown.android.internal.common.model.Redirect r5 = r12.getRedirect()
            if (r5 == 0) goto L_0x0056
            java.lang.String r5 = r5.getNative()
            goto L_0x0057
        L_0x0056:
            r5 = r0
        L_0x0057:
            if (r12 == 0) goto L_0x0063
            com.reown.android.internal.common.model.Redirect r6 = r12.getRedirect()
            if (r6 == 0) goto L_0x0063
            java.lang.String r0 = r6.getUniversal()
        L_0x0063:
            r6 = r0
            if (r12 == 0) goto L_0x0072
            com.reown.android.internal.common.model.Redirect r12 = r12.getRedirect()
            if (r12 == 0) goto L_0x0072
            boolean r12 = r12.getLinkMode()
        L_0x0070:
            r7 = r12
            goto L_0x0074
        L_0x0072:
            r12 = 0
            goto L_0x0070
        L_0x0074:
            r9 = 128(0x80, float:1.794E-43)
            r10 = 0
            r8 = 0
            r0 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.utils.ExtensionsKt.toClient(com.reown.android.internal.common.model.AppMetaData):com.reown.android.Core$Model$AppMetaData");
    }
}
