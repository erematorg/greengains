package com.benasher44.uuid;

import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\"\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038FX\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007*<\b\u0007\u0010\b\"\u0002`\u00032\u00060\u0002j\u0002`\u0003B*\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u001c\b\f\u0012\u0018\b\u000bB\u0014\b\r\u0012\u0006\b\u000e\u0012\u0002\b\f\u0012\b\b\u000f\u0012\u0004\b\b(\u0010¨\u0006\u0000"}, d2 = {"uuid", "", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "getUuid$annotations", "(Ljava/util/UUID;)V", "getUuid", "(Ljava/util/UUID;)[B", "UUID", "Lkotlin/Deprecated;", "message", "Use `Uuid` instead.", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "Uuid"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class DeprecationsKt {
    @Deprecated(message = "Use `Uuid` instead.", replaceWith = @ReplaceWith(expression = "Uuid", imports = {}))
    public static /* synthetic */ void UUID$annotations() {
    }

    @NotNull
    public static final byte[] getUuid(@NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return UuidKt.getBytes(uuid);
    }

    @Deprecated(message = "Use uuidFrom() instead. This will be removed in the next release.", replaceWith = @ReplaceWith(expression = "Uuid.bytes", imports = {}))
    public static /* synthetic */ void getUuid$annotations(UUID uuid) {
    }
}
