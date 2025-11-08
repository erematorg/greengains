package com.reown.android.internal.common.explorer.data.network.model;

import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B1\u0012\u0014\b\u0001\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\u0014\b\u0003\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/ProjectListingDTO;", "", "projects", "", "", "Lcom/reown/android/internal/common/explorer/data/network/model/ProjectDTO;", "count", "", "total", "<init>", "(Ljava/util/Map;II)V", "getProjects", "()Ljava/util/Map;", "getCount", "()I", "getTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProjectListingDTO {
    private final int count;
    @NotNull
    private final Map<String, ProjectDTO> projects;
    private final int total;

    public ProjectListingDTO(@NotNull @Json(name = "projects") Map<String, ProjectDTO> map, @Json(name = "count") int i3, @Json(name = "total") int i4) {
        Intrinsics.checkNotNullParameter(map, "projects");
        this.projects = map;
        this.count = i3;
        this.total = i4;
    }

    public static /* synthetic */ ProjectListingDTO copy$default(ProjectListingDTO projectListingDTO, Map<String, ProjectDTO> map, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            map = projectListingDTO.projects;
        }
        if ((i5 & 2) != 0) {
            i3 = projectListingDTO.count;
        }
        if ((i5 & 4) != 0) {
            i4 = projectListingDTO.total;
        }
        return projectListingDTO.copy(map, i3, i4);
    }

    @NotNull
    public final Map<String, ProjectDTO> component1() {
        return this.projects;
    }

    public final int component2() {
        return this.count;
    }

    public final int component3() {
        return this.total;
    }

    @NotNull
    public final ProjectListingDTO copy(@NotNull @Json(name = "projects") Map<String, ProjectDTO> map, @Json(name = "count") int i3, @Json(name = "total") int i4) {
        Intrinsics.checkNotNullParameter(map, "projects");
        return new ProjectListingDTO(map, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProjectListingDTO)) {
            return false;
        }
        ProjectListingDTO projectListingDTO = (ProjectListingDTO) obj;
        return Intrinsics.areEqual((Object) this.projects, (Object) projectListingDTO.projects) && this.count == projectListingDTO.count && this.total == projectListingDTO.total;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final Map<String, ProjectDTO> getProjects() {
        return this.projects;
    }

    public final int getTotal() {
        return this.total;
    }

    public int hashCode() {
        return Integer.hashCode(this.total) + a.c(this.count, this.projects.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        Map<String, ProjectDTO> map = this.projects;
        int i3 = this.count;
        int i4 = this.total;
        StringBuilder sb = new StringBuilder("ProjectListingDTO(projects=");
        sb.append(map);
        sb.append(", count=");
        sb.append(i3);
        sb.append(", total=");
        return A.a.m(sb, ")", i4);
    }
}
