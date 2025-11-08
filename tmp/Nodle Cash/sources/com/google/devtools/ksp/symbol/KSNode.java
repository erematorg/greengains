package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J5\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u000e0\u00112\u0006\u0010\u0012\u001a\u0002H\u000fH&¢\u0006\u0002\u0010\u0013R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSNode;", "", "location", "Lcom/google/devtools/ksp/symbol/Location;", "getLocation", "()Lcom/google/devtools/ksp/symbol/Location;", "origin", "Lcom/google/devtools/ksp/symbol/Origin;", "getOrigin", "()Lcom/google/devtools/ksp/symbol/Origin;", "parent", "getParent", "()Lcom/google/devtools/ksp/symbol/KSNode;", "accept", "R", "D", "visitor", "Lcom/google/devtools/ksp/symbol/KSVisitor;", "data", "(Lcom/google/devtools/ksp/symbol/KSVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSNode {
    <D, R> R accept(@NotNull KSVisitor<D, R> kSVisitor, D d2);

    @NotNull
    Location getLocation();

    @NotNull
    Origin getOrigin();

    @Nullable
    KSNode getParent();
}
