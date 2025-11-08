package com.reown.sign.storage.sequence;

import com.reown.android.internal.common.model.TransportType;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class SessionStorageRepository$getListOfSessionVOsWithoutMetadata$1 extends FunctionReferenceImpl implements Function13<Long, String, Long, String, String, String, String, String, Boolean, String, Map<String, ? extends String>, TransportType, Map<String, ? extends String>, SessionVO> {
    public SessionStorageRepository$getListOfSessionVOsWithoutMetadata$1(Object obj) {
        super(13, obj, SessionStorageRepository.class, "mapSessionDaoToSessionVO", "mapSessionDaoToSessionVO(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/Map;Lcom/reown/android/internal/common/model/TransportType;Ljava/util/Map;)Lcom/reown/sign/common/model/vo/sequence/SessionVO;", 0);
    }

    public final SessionVO invoke(long j2, String str, long j3, String str2, String str3, String str4, String str5, String str6, boolean z2, String str7, Map<String, String> map, TransportType transportType, Map<String, String> map2) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "p1");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "p3");
        String str10 = str5;
        Intrinsics.checkNotNullParameter(str10, "p6");
        String str11 = str7;
        Intrinsics.checkNotNullParameter(str11, "p9");
        return ((SessionStorageRepository) this.receiver).mapSessionDaoToSessionVO(j2, str8, j3, str9, str3, str4, str10, str6, z2, str11, map, transportType, map2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        return invoke(((Number) obj).longValue(), (String) obj2, ((Number) obj3).longValue(), (String) obj4, (String) obj5, (String) obj6, (String) obj7, (String) obj8, ((Boolean) obj9).booleanValue(), (String) obj10, (Map<String, String>) (Map) obj11, (TransportType) obj12, (Map<String, String>) (Map) obj13);
    }
}
