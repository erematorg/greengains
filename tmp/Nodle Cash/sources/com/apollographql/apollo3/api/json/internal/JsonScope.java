package com.apollographql.apollo3.api.json.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J9\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0010¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo3/api/json/internal/JsonScope;", "", "()V", "CLOSED", "", "DANGLING_NAME", "EMPTY_ARRAY", "EMPTY_DOCUMENT", "EMPTY_OBJECT", "NONEMPTY_ARRAY", "NONEMPTY_DOCUMENT", "NONEMPTY_OBJECT", "getPath", "", "stackSize", "stack", "", "pathNames", "", "", "pathIndices", "(I[I[Ljava/lang/String;[I)Ljava/util/List;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonScope.kt\ncom/apollographql/apollo3/api/json/internal/JsonScope\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1#2:63\n*E\n"})
public final class JsonScope {
    public static final int CLOSED = 8;
    public static final int DANGLING_NAME = 4;
    public static final int EMPTY_ARRAY = 1;
    public static final int EMPTY_DOCUMENT = 6;
    public static final int EMPTY_OBJECT = 3;
    @NotNull
    public static final JsonScope INSTANCE = new JsonScope();
    public static final int NONEMPTY_ARRAY = 2;
    public static final int NONEMPTY_DOCUMENT = 7;
    public static final int NONEMPTY_OBJECT = 5;

    private JsonScope() {
    }

    @NotNull
    public final List<Object> getPath(int i3, @NotNull int[] iArr, @NotNull String[] strArr, @NotNull int[] iArr2) {
        String str;
        Intrinsics.checkNotNullParameter(iArr, "stack");
        Intrinsics.checkNotNullParameter(strArr, "pathNames");
        Intrinsics.checkNotNullParameter(iArr2, "pathIndices");
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            if (i5 == 1 || i5 == 2) {
                arrayList.add(Integer.valueOf(iArr2[i4]));
            } else if ((i5 == 3 || i5 == 4 || i5 == 5) && (str = strArr[i4]) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
