package com.reown.android.internal.common.storage.metadata;

import com.reown.android.internal.common.model.AppMetaData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class MetadataStorageRepository$getByTopicAndType$1 extends FunctionReferenceImpl implements Function7<String, String, String, List<? extends String>, String, String, Boolean, AppMetaData> {
    public MetadataStorageRepository$getByTopicAndType$1(Object obj) {
        super(7, obj, MetadataStorageRepository.class, "toMetadata", "toMetadata(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/internal/common/model/AppMetaData;", 0);
    }

    public final AppMetaData invoke(String str, String str2, String str3, List<String> list, String str4, String str5, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "p0");
        Intrinsics.checkNotNullParameter(str2, "p1");
        Intrinsics.checkNotNullParameter(str3, "p2");
        Intrinsics.checkNotNullParameter(list, "p3");
        return ((MetadataStorageRepository) this.receiver).toMetadata(str, str2, str3, list, str4, str5, bool);
    }
}
