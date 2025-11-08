package com.reown.sign.storage.proposal;

import com.reown.sign.common.model.vo.proposal.ProposalVO;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class ProposalStorageRepository$getProposalByKey$1 extends FunctionReferenceImpl implements Function13<Long, String, String, String, String, List<? extends String>, String, String, String, Map<String, ? extends String>, String, Long, Map<String, ? extends String>, ProposalVO> {
    public ProposalStorageRepository$getProposalByKey$1(Object obj) {
        super(13, obj, ProposalStorageRepository.class, "mapProposalDaoToProposalVO", "mapProposalDaoToProposalVO(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;)Lcom/reown/sign/common/model/vo/proposal/ProposalVO;", 0);
    }

    public final ProposalVO invoke(long j2, String str, String str2, String str3, String str4, List<String> list, String str5, String str6, String str7, Map<String, String> map, String str8, Long l2, Map<String, String> map2) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "p1");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "p2");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "p3");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "p4");
        List<String> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "p5");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "p6");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "p8");
        String str15 = str8;
        Intrinsics.checkNotNullParameter(str15, "p10");
        return ((ProposalStorageRepository) this.receiver).mapProposalDaoToProposalVO(j2, str9, str10, str11, str12, list2, str13, str6, str14, map, str15, l2, map2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        return invoke(((Number) obj).longValue(), (String) obj2, (String) obj3, (String) obj4, (String) obj5, (List<String>) (List) obj6, (String) obj7, (String) obj8, (String) obj9, (Map<String, String>) (Map) obj10, (String) obj11, (Long) obj12, (Map<String, String>) (Map) obj13);
    }
}
