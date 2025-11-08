package com.reown.android.internal.common.explorer.data.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/ProjectListing;", "", "projects", "", "Lcom/reown/android/internal/common/explorer/data/model/Project;", "count", "", "<init>", "(Ljava/util/List;I)V", "getProjects", "()Ljava/util/List;", "getCount", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProjectListing {
    private final int count;
    @NotNull
    private final List<Project> projects;

    public ProjectListing(@NotNull List<Project> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "projects");
        this.projects = list;
        this.count = i3;
    }

    public static /* synthetic */ ProjectListing copy$default(ProjectListing projectListing, List<Project> list, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            list = projectListing.projects;
        }
        if ((i4 & 2) != 0) {
            i3 = projectListing.count;
        }
        return projectListing.copy(list, i3);
    }

    @NotNull
    public final List<Project> component1() {
        return this.projects;
    }

    public final int component2() {
        return this.count;
    }

    @NotNull
    public final ProjectListing copy(@NotNull List<Project> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "projects");
        return new ProjectListing(list, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProjectListing)) {
            return false;
        }
        ProjectListing projectListing = (ProjectListing) obj;
        return Intrinsics.areEqual((Object) this.projects, (Object) projectListing.projects) && this.count == projectListing.count;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final List<Project> getProjects() {
        return this.projects;
    }

    public int hashCode() {
        return Integer.hashCode(this.count) + (this.projects.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        List<Project> list = this.projects;
        int i3 = this.count;
        return "ProjectListing(projects=" + list + ", count=" + i3 + ")";
    }
}
