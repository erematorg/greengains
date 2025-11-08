package app.cash.sqldelight;

import java.lang.Enum;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\t\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00040\u0003B\u0017\b\u0001\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010\u000bJ\u0015\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eR\u0018\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u000f"}, d2 = {"Lapp/cash/sqldelight/EnumColumnAdapter;", "T", "", "Lapp/cash/sqldelight/ColumnAdapter;", "", "enumValues", "", "([Ljava/lang/Enum;)V", "[Ljava/lang/Enum;", "decode", "databaseValue", "(Ljava/lang/String;)Ljava/lang/Enum;", "encode", "value", "(Ljava/lang/Enum;)Ljava/lang/String;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEnumColumnAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EnumColumnAdapter.kt\napp/cash/sqldelight/EnumColumnAdapter\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,32:1\n1109#2,2:33\n*S KotlinDebug\n*F\n+ 1 EnumColumnAdapter.kt\napp/cash/sqldelight/EnumColumnAdapter\n*L\n22#1:33,2\n*E\n"})
public final class EnumColumnAdapter<T extends Enum<T>> implements ColumnAdapter<T, String> {
    @NotNull
    private final T[] enumValues;

    @PublishedApi
    public EnumColumnAdapter(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "enumValues");
        this.enumValues = tArr;
    }

    @NotNull
    public T decode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "databaseValue");
        for (T t2 : this.enumValues) {
            if (Intrinsics.areEqual((Object) t2.name(), (Object) str)) {
                return t2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public String encode(@NotNull T t2) {
        Intrinsics.checkNotNullParameter(t2, "value");
        return t2.name();
    }
}
