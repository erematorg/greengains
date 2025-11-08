package com.phrase.android.sdk.repo;

import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001:\u0001\u001cBU\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u0002\u0012\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003J\u001b\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u0002HÆ\u0003J!\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0002HÆ\u0003J]\u0010\u000b\u001a\u00020\u00002\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u00022 \b\u0002\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0002HÆ\u0001J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R)\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R/\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015¨\u0006\u001d"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseData;", "", "", "", "component1", "", "component2", "component3", "strings", "arrays", "plurals", "copy", "toString", "", "hashCode", "other", "", "equals", "a", "Ljava/util/Map;", "getStrings", "()Ljava/util/Map;", "b", "getArrays", "c", "getPlurals", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "Builder", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f7277a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Map<String, List<String>> f7278b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Map<String, Map<String, String>> f7279c;

    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u0016\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u000b\u001a\u00020\n¨\u0006\u000e"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseData$Builder;", "", "", "key", "value", "", "putString", "putArrayItem", "quantity", "putPluralItem", "Lcom/phrase/android/sdk/repo/PhraseData;", "build", "<init>", "()V", "sdk_release"}, k = 1, mv = {1, 7, 1})
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final HashMap<String, String> f7280a = new HashMap<>();
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public final HashMap<String, ArrayList<String>> f7281b = new HashMap<>();
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public final HashMap<String, HashMap<String, String>> f7282c = new HashMap<>();

        @NotNull
        public final PhraseData build() {
            return new PhraseData(this.f7280a, this.f7281b, this.f7282c);
        }

        public final void putArrayItem(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
            Intrinsics.checkNotNullParameter(str2, "value");
            ArrayList arrayList = this.f7281b.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f7281b.put(str, arrayList);
            }
            arrayList.add(str2);
        }

        public final void putPluralItem(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.QUANTITY);
            Intrinsics.checkNotNullParameter(str3, "value");
            HashMap hashMap = this.f7282c.get(str);
            if (hashMap == null) {
                hashMap = new HashMap();
                this.f7282c.put(str, hashMap);
            }
            hashMap.put(str2, str3);
        }

        public final void putString(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
            Intrinsics.checkNotNullParameter(str2, "value");
            this.f7280a.put(str, str2);
        }
    }

    public PhraseData(@NotNull Map<String, String> map, @NotNull Map<String, ? extends List<String>> map2, @NotNull Map<String, ? extends Map<String, String>> map3) {
        Intrinsics.checkNotNullParameter(map, "strings");
        Intrinsics.checkNotNullParameter(map2, "arrays");
        Intrinsics.checkNotNullParameter(map3, "plurals");
        this.f7277a = map;
        this.f7278b = map2;
        this.f7279c = map3;
    }

    public static /* synthetic */ PhraseData copy$default(PhraseData phraseData, Map<String, String> map, Map<String, List<String>> map2, Map<String, Map<String, String>> map3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            map = phraseData.f7277a;
        }
        if ((i3 & 2) != 0) {
            map2 = phraseData.f7278b;
        }
        if ((i3 & 4) != 0) {
            map3 = phraseData.f7279c;
        }
        return phraseData.copy(map, map2, map3);
    }

    @NotNull
    public final Map<String, String> component1() {
        return this.f7277a;
    }

    @NotNull
    public final Map<String, List<String>> component2() {
        return this.f7278b;
    }

    @NotNull
    public final Map<String, Map<String, String>> component3() {
        return this.f7279c;
    }

    @NotNull
    public final PhraseData copy(@NotNull Map<String, String> map, @NotNull Map<String, ? extends List<String>> map2, @NotNull Map<String, ? extends Map<String, String>> map3) {
        Intrinsics.checkNotNullParameter(map, "strings");
        Intrinsics.checkNotNullParameter(map2, "arrays");
        Intrinsics.checkNotNullParameter(map3, "plurals");
        return new PhraseData(map, map2, map3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhraseData)) {
            return false;
        }
        PhraseData phraseData = (PhraseData) obj;
        return Intrinsics.areEqual((Object) this.f7277a, (Object) phraseData.f7277a) && Intrinsics.areEqual((Object) this.f7278b, (Object) phraseData.f7278b) && Intrinsics.areEqual((Object) this.f7279c, (Object) phraseData.f7279c);
    }

    @NotNull
    public final Map<String, List<String>> getArrays() {
        return this.f7278b;
    }

    @NotNull
    public final Map<String, Map<String, String>> getPlurals() {
        return this.f7279c;
    }

    @NotNull
    public final Map<String, String> getStrings() {
        return this.f7277a;
    }

    public int hashCode() {
        return this.f7279c.hashCode() + b.d(this.f7278b, this.f7277a.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        return "PhraseData(strings=" + this.f7277a + ", arrays=" + this.f7278b + ", plurals=" + this.f7279c + ')';
    }
}
