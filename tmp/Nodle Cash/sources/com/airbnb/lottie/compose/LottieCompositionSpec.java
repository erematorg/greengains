package com.airbnb.lottie.compose;

import A.a;
import android.net.Uri;
import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007\u0001\u0006\b\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "", "Asset", "ContentProvider", "File", "JsonString", "RawRes", "Url", "Lcom/airbnb/lottie/compose/LottieCompositionSpec$Asset;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec$ContentProvider;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec$File;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec$JsonString;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec$RawRes;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec$Url;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface LottieCompositionSpec {

    @JvmInline
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec$Asset;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "assetName", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getAssetName", "()Ljava/lang/String;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Asset implements LottieCompositionSpec {
        @NotNull
        private final String assetName;

        private /* synthetic */ Asset(String str) {
            this.assetName = str;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ Asset m8158boximpl(String str) {
            return new Asset(str);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static String m8159constructorimpl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "assetName");
            return str;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8160equalsimpl(String str, Object obj) {
            return (obj instanceof Asset) && Intrinsics.areEqual((Object) str, (Object) ((Asset) obj).m8164unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8161equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8162hashCodeimpl(String str) {
            return str.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8163toStringimpl(String str) {
            return a.l("Asset(assetName=", str, ")");
        }

        public boolean equals(Object obj) {
            return m8160equalsimpl(this.assetName, obj);
        }

        @NotNull
        public final String getAssetName() {
            return this.assetName;
        }

        public int hashCode() {
            return m8162hashCodeimpl(this.assetName);
        }

        public String toString() {
            return m8163toStringimpl(this.assetName);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ String m8164unboximpl() {
            return this.assetName;
        }
    }

    @JvmInline
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec$ContentProvider;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "uri", "Landroid/net/Uri;", "constructor-impl", "(Landroid/net/Uri;)Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "equals", "", "other", "", "equals-impl", "(Landroid/net/Uri;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Landroid/net/Uri;)I", "toString", "", "toString-impl", "(Landroid/net/Uri;)Ljava/lang/String;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ContentProvider implements LottieCompositionSpec {
        @NotNull
        private final Uri uri;

        private /* synthetic */ ContentProvider(Uri uri2) {
            this.uri = uri2;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ ContentProvider m8165boximpl(Uri uri2) {
            return new ContentProvider(uri2);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static Uri m8166constructorimpl(@NotNull Uri uri2) {
            Intrinsics.checkNotNullParameter(uri2, "uri");
            return uri2;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8167equalsimpl(Uri uri2, Object obj) {
            return (obj instanceof ContentProvider) && Intrinsics.areEqual((Object) uri2, (Object) ((ContentProvider) obj).m8171unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8168equalsimpl0(Uri uri2, Uri uri3) {
            return Intrinsics.areEqual((Object) uri2, (Object) uri3);
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8169hashCodeimpl(Uri uri2) {
            return uri2.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8170toStringimpl(Uri uri2) {
            return "ContentProvider(uri=" + uri2 + ")";
        }

        public boolean equals(Object obj) {
            return m8167equalsimpl(this.uri, obj);
        }

        @NotNull
        public final Uri getUri() {
            return this.uri;
        }

        public int hashCode() {
            return m8169hashCodeimpl(this.uri);
        }

        public String toString() {
            return m8170toStringimpl(this.uri);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ Uri m8171unboximpl() {
            return this.uri;
        }
    }

    @JvmInline
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec$File;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "fileName", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getFileName", "()Ljava/lang/String;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class File implements LottieCompositionSpec {
        @NotNull
        private final String fileName;

        private /* synthetic */ File(String str) {
            this.fileName = str;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ File m8172boximpl(String str) {
            return new File(str);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static String m8173constructorimpl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "fileName");
            return str;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8174equalsimpl(String str, Object obj) {
            return (obj instanceof File) && Intrinsics.areEqual((Object) str, (Object) ((File) obj).m8178unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8175equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8176hashCodeimpl(String str) {
            return str.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8177toStringimpl(String str) {
            return a.l("File(fileName=", str, ")");
        }

        public boolean equals(Object obj) {
            return m8174equalsimpl(this.fileName, obj);
        }

        @NotNull
        public final String getFileName() {
            return this.fileName;
        }

        public int hashCode() {
            return m8176hashCodeimpl(this.fileName);
        }

        public String toString() {
            return m8177toStringimpl(this.fileName);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ String m8178unboximpl() {
            return this.fileName;
        }
    }

    @JvmInline
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec$JsonString;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "jsonString", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getJsonString", "()Ljava/lang/String;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class JsonString implements LottieCompositionSpec {
        @NotNull
        private final String jsonString;

        private /* synthetic */ JsonString(String str) {
            this.jsonString = str;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ JsonString m8179boximpl(String str) {
            return new JsonString(str);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static String m8180constructorimpl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "jsonString");
            return str;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8181equalsimpl(String str, Object obj) {
            return (obj instanceof JsonString) && Intrinsics.areEqual((Object) str, (Object) ((JsonString) obj).m8185unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8182equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8183hashCodeimpl(String str) {
            return str.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8184toStringimpl(String str) {
            return a.l("JsonString(jsonString=", str, ")");
        }

        public boolean equals(Object obj) {
            return m8181equalsimpl(this.jsonString, obj);
        }

        @NotNull
        public final String getJsonString() {
            return this.jsonString;
        }

        public int hashCode() {
            return m8183hashCodeimpl(this.jsonString);
        }

        public String toString() {
            return m8184toStringimpl(this.jsonString);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ String m8185unboximpl() {
            return this.jsonString;
        }
    }

    @JvmInline
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b@\u0018\u00002\u00020\u0001B\u0014\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec$RawRes;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "resId", "", "constructor-impl", "(I)I", "getResId", "()I", "equals", "", "other", "", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RawRes implements LottieCompositionSpec {
        private final int resId;

        private /* synthetic */ RawRes(@androidx.annotation.RawRes int i3) {
            this.resId = i3;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ RawRes m8186boximpl(int i3) {
            return new RawRes(i3);
        }

        /* renamed from: constructor-impl  reason: not valid java name */
        public static int m8187constructorimpl(@androidx.annotation.RawRes int i3) {
            return i3;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8188equalsimpl(int i3, Object obj) {
            return (obj instanceof RawRes) && i3 == ((RawRes) obj).m8192unboximpl();
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8189equalsimpl0(int i3, int i4) {
            return i3 == i4;
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8190hashCodeimpl(int i3) {
            return Integer.hashCode(i3);
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8191toStringimpl(int i3) {
            return C0118y.c(i3, "RawRes(resId=", ")");
        }

        public boolean equals(Object obj) {
            return m8188equalsimpl(this.resId, obj);
        }

        public final int getResId() {
            return this.resId;
        }

        public int hashCode() {
            return m8190hashCodeimpl(this.resId);
        }

        public String toString() {
            return m8191toStringimpl(this.resId);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ int m8192unboximpl() {
            return this.resId;
        }
    }

    @JvmInline
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/airbnb/lottie/compose/LottieCompositionSpec$Url;", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "url", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Url implements LottieCompositionSpec {
        @NotNull
        private final String url;

        private /* synthetic */ Url(String str) {
            this.url = str;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ Url m8193boximpl(String str) {
            return new Url(str);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static String m8194constructorimpl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            return str;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8195equalsimpl(String str, Object obj) {
            return (obj instanceof Url) && Intrinsics.areEqual((Object) str, (Object) ((Url) obj).m8199unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8196equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8197hashCodeimpl(String str) {
            return str.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8198toStringimpl(String str) {
            return a.l("Url(url=", str, ")");
        }

        public boolean equals(Object obj) {
            return m8195equalsimpl(this.url, obj);
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return m8197hashCodeimpl(this.url);
        }

        public String toString() {
            return m8198toStringimpl(this.url);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ String m8199unboximpl() {
            return this.url;
        }
    }
}
