package com.reown.sign.client.utils;

import android.util.Base64;
import androidx.browser.trusted.c;
import com.fasterxml.jackson.core.JsonPointer;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.signing.cacao.CacaoKt;
import com.reown.android.internal.common.signing.cacao.CacaoType;
import com.reown.android.internal.common.signing.cacao.UtilsKt;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.sign.client.Sign;
import com.reown.sign.client.mapper.ClientMapperKt;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.common.validator.SignValidator;
import com.reown.sign.engine.model.ValidationError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a.\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u001a(\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0001H\u0000\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0002\u001a&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0002\u001a&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0002\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0002\u001a\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b\u001a*\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b\u001a\u000e\u0010 \u001a\u0004\u0018\u00010\u0002*\u00020\u0018H\u0002¨\u0006!"}, d2 = {"generateApprovedNamespaces", "", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "proposal", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "supportedNamespaces", "normalizeNamespaces", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "namespaces", "getNamespaceChains", "", "key", "namespace", "Lcom/reown/android/internal/common/model/Namespace;", "normalizeKey", "getChains", "", "normalizedKey", "getMethods", "getEvents", "generateAuthObject", "Lcom/reown/sign/client/Sign$Model$Cacao;", "payload", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "issuer", "signature", "Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "generateAuthPayloadParams", "payloadParams", "supportedChains", "supportedMethods", "getStatement", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\ncom/reown/sign/client/utils/ApprovedNamespacesUtils\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,166:1\n29#2,8:167\n29#2,8:175\n64#2:183\n44#2,23:184\n216#3:207\n217#3:226\n216#3:227\n217#3:246\n216#3,2:247\n774#4:208\n865#4,2:209\n774#4:211\n865#4,2:212\n774#4:214\n865#4,2:215\n1374#4:217\n1460#4,2:218\n774#4:220\n865#4,2:221\n1462#4,3:223\n774#4:228\n865#4,2:229\n774#4:231\n865#4,2:232\n774#4:234\n865#4,2:235\n1374#4:237\n1460#4,2:238\n774#4:240\n865#4,2:241\n1462#4,3:243\n1563#4:249\n1634#4,3:250\n1740#4,3:253\n1740#4,3:256\n1869#4,2:259\n1869#4,2:261\n*S KotlinDebug\n*F\n+ 1 Utils.kt\ncom/reown/sign/client/utils/ApprovedNamespacesUtils\n*L\n31#1:167,8\n32#1:175,8\n33#1:183\n33#1:184,23\n40#1:207\n40#1:226\n51#1:227\n51#1:246\n76#1:247,2\n41#1:208\n41#1:209,2\n42#1:211\n42#1:212,2\n43#1:214\n43#1:215,2\n44#1:217\n44#1:218,2\n45#1:220\n45#1:221,2\n44#1:223,3\n53#1:228\n53#1:229,2\n54#1:231\n54#1:232,2\n55#1:234\n55#1:235,2\n56#1:237\n56#1:238,2\n57#1:240\n57#1:241,2\n56#1:243,3\n112#1:249\n112#1:250,3\n121#1:253,3\n122#1:256,3\n126#1:259,2\n127#1:261,2\n*E\n"})
@JvmName(name = "ApprovedNamespacesUtils")
public final class ApprovedNamespacesUtils {
    @NotNull
    public static final Map<String, Sign.Model.Namespace.Session> generateApprovedNamespaces(@NotNull Sign.Model.SessionProposal sessionProposal, @NotNull Map<String, Sign.Model.Namespace.Session> map) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        List<String> accounts;
        List plus;
        List distinct;
        ArrayList arrayList4;
        List<String> accounts2;
        List<String> events;
        List<String> methods;
        List<String> chains;
        List<String> list;
        ArrayList arrayList5;
        ArrayList arrayList6;
        Iterator<Map.Entry<String, Namespace.Proposal>> it;
        ArrayList arrayList7;
        List<String> accounts3;
        List<String> events2;
        List<String> methods2;
        List<String> chains2;
        Map<String, Sign.Model.Namespace.Session> map2 = map;
        Intrinsics.checkNotNullParameter(sessionProposal, "proposal");
        Intrinsics.checkNotNullParameter(map2, "supportedNamespaces");
        Map sessionNamespacesVO = ClientMapperKt.toSessionNamespacesVO(map);
        Map<String, Namespace.Proposal> normalizeNamespaces = normalizeNamespaces(ClientMapperKt.toProposalNamespacesVO(sessionProposal.getRequiredNamespaces()));
        Map<String, Namespace.Proposal> normalizeNamespaces2 = normalizeNamespaces(ClientMapperKt.toProposalNamespacesVO(sessionProposal.getOptionalNamespaces()));
        SignValidator signValidator = SignValidator.INSTANCE;
        if (!signValidator.areNamespacesKeysProperlyFormatted(normalizeNamespaces)) {
            throw new Exception(ValidationError.UnsupportedNamespaceKey.INSTANCE.getMessage());
        } else if (!signValidator.areChainsDefined(normalizeNamespaces)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE).getMessage());
        } else if (!signValidator.areChainsNotEmpty(normalizeNamespaces)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE).getMessage());
        } else if (!signValidator.areChainIdsValid(normalizeNamespaces)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE).getMessage());
        } else if (!signValidator.areChainsInMatchingNamespace(normalizeNamespaces)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE).getMessage());
        } else if (!signValidator.areNamespacesKeysProperlyFormatted(normalizeNamespaces2)) {
            throw new Exception(ValidationError.UnsupportedNamespaceKey.INSTANCE.getMessage());
        } else if (!signValidator.areChainsDefined(normalizeNamespaces2)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE).getMessage());
        } else if (!signValidator.areChainsNotEmpty(normalizeNamespaces2)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE).getMessage());
        } else if (!signValidator.areChainIdsValid(normalizeNamespaces2)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE).getMessage());
        } else if (!signValidator.areChainsInMatchingNamespace(normalizeNamespaces2)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE).getMessage());
        } else if (sessionNamespacesVO.isEmpty()) {
            throw new Exception(ValidationError.EmptyNamespaces.INSTANCE.getMessage());
        } else if (!signValidator.areNamespacesKeysProperlyFormatted(sessionNamespacesVO)) {
            throw new Exception(ValidationError.UnsupportedNamespaceKey.INSTANCE.getMessage());
        } else if (!signValidator.areChainsNotEmpty(sessionNamespacesVO)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE).getMessage());
        } else if (!signValidator.areChainIdsValid(sessionNamespacesVO)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE).getMessage());
        } else if (!signValidator.areChainsInMatchingNamespace(sessionNamespacesVO)) {
            throw new Exception(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE).getMessage());
        } else if (!signValidator.areAccountIdsValid(sessionNamespacesVO)) {
            throw new Exception(new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_CAIP_10_MESSAGE).getMessage());
        } else if (!signValidator.areAccountsInMatchingNamespaceAndChains(sessionNamespacesVO)) {
            throw new Exception(new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_WRONG_NAMESPACE_MESSAGE).getMessage());
        } else if (!signValidator.areAllNamespacesApproved(sessionNamespacesVO.keySet(), normalizeNamespaces.keySet())) {
            throw new Exception(ValidationError.UserRejected.INSTANCE.getMessage());
        } else if (!signValidator.areAllMethodsApproved(signValidator.allMethodsWithChains(sessionNamespacesVO), signValidator.allMethodsWithChains(normalizeNamespaces))) {
            throw new Exception(ValidationError.UserRejectedMethods.INSTANCE.getMessage());
        } else if (!signValidator.areAllEventsApproved(signValidator.allEventsWithChains(sessionNamespacesVO), signValidator.allEventsWithChains(normalizeNamespaces))) {
            throw new Exception(ValidationError.UserRejectedEvents.INSTANCE.getMessage());
        } else if (!signValidator.areAllChainsApproved$sign_release(sessionNamespacesVO, normalizeNamespaces)) {
            throw new Exception(new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_WRONG_NAMESPACE_MESSAGE).getMessage());
        } else if (sessionProposal.getRequiredNamespaces().isEmpty() && sessionProposal.getOptionalNamespaces().isEmpty()) {
            return ClientMapperKt.toCore(sessionNamespacesVO);
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<Map.Entry<String, Namespace.Proposal>> it2 = normalizeNamespaces.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry next = it2.next();
                String str = (String) next.getKey();
                Namespace.Proposal proposal = (Namespace.Proposal) next.getValue();
                Namespace.Session session = (Namespace.Session) sessionNamespacesVO.get(str);
                if (session == null || (chains2 = session.getChains()) == null) {
                    list = CollectionsKt.emptyList();
                } else {
                    ArrayList arrayList8 = new ArrayList();
                    for (Object next2 : chains2) {
                        List<String> chains3 = proposal.getChains();
                        Intrinsics.checkNotNull(chains3);
                        if (chains3.contains((String) next2)) {
                            arrayList8.add(next2);
                        }
                    }
                    list = arrayList8;
                }
                Sign.Model.Namespace.Session session2 = map2.get(str);
                if (session2 == null || (methods2 = session2.getMethods()) == null) {
                    arrayList5 = CollectionsKt.emptyList();
                } else {
                    ArrayList arrayList9 = new ArrayList();
                    for (Object next3 : methods2) {
                        if (proposal.getMethods().contains((String) next3)) {
                            arrayList9.add(next3);
                        }
                    }
                    arrayList5 = arrayList9;
                }
                Sign.Model.Namespace.Session session3 = map2.get(str);
                if (session3 == null || (events2 = session3.getEvents()) == null) {
                    arrayList6 = CollectionsKt.emptyList();
                } else {
                    ArrayList arrayList10 = new ArrayList();
                    for (Object next4 : events2) {
                        if (proposal.getEvents().contains((String) next4)) {
                            arrayList10.add(next4);
                        }
                    }
                    arrayList6 = arrayList10;
                }
                ArrayList arrayList11 = new ArrayList();
                for (String str2 : list) {
                    Sign.Model.Namespace.Session session4 = map2.get(str);
                    if (session4 == null || (accounts3 = session4.getAccounts()) == null) {
                        it = it2;
                        arrayList7 = CollectionsKt.emptyList();
                    } else {
                        ArrayList arrayList12 = new ArrayList();
                        for (Object next5 : accounts3) {
                            Iterator<Map.Entry<String, Namespace.Proposal>> it3 = it2;
                            if (Intrinsics.areEqual((Object) SignValidator.INSTANCE.getChainFromAccount$sign_release((String) next5), (Object) str2)) {
                                arrayList12.add(next5);
                            }
                            it2 = it3;
                        }
                        it = it2;
                        arrayList7 = arrayList12;
                    }
                    CollectionsKt__MutableCollectionsKt.addAll(arrayList11, arrayList7);
                    it2 = it;
                }
                linkedHashMap.put(str, new Namespace.Session(list, arrayList11, arrayList5, arrayList6));
                it2 = it2;
            }
            for (Map.Entry next6 : normalizeNamespaces2.entrySet()) {
                String str3 = (String) next6.getKey();
                Namespace.Proposal proposal2 = (Namespace.Proposal) next6.getValue();
                if (map2.containsKey(str3)) {
                    Namespace.Session session5 = (Namespace.Session) sessionNamespacesVO.get(str3);
                    if (session5 == null || (chains = session5.getChains()) == null) {
                        arrayList = CollectionsKt.emptyList();
                    } else {
                        ArrayList arrayList13 = new ArrayList();
                        for (Object next7 : chains) {
                            List<String> chains4 = proposal2.getChains();
                            Intrinsics.checkNotNull(chains4);
                            if (chains4.contains((String) next7)) {
                                arrayList13.add(next7);
                            }
                        }
                        arrayList = arrayList13;
                    }
                    Sign.Model.Namespace.Session session6 = map2.get(str3);
                    if (session6 == null || (methods = session6.getMethods()) == null) {
                        arrayList2 = CollectionsKt.emptyList();
                    } else {
                        ArrayList arrayList14 = new ArrayList();
                        for (Object next8 : methods) {
                            if (proposal2.getMethods().contains((String) next8)) {
                                arrayList14.add(next8);
                            }
                        }
                        arrayList2 = arrayList14;
                    }
                    Sign.Model.Namespace.Session session7 = map2.get(str3);
                    if (session7 == null || (events = session7.getEvents()) == null) {
                        arrayList3 = CollectionsKt.emptyList();
                    } else {
                        ArrayList arrayList15 = new ArrayList();
                        for (Object next9 : events) {
                            if (proposal2.getEvents().contains((String) next9)) {
                                arrayList15.add(next9);
                            }
                        }
                        arrayList3 = arrayList15;
                    }
                    Iterable<String> iterable = arrayList;
                    List arrayList16 = new ArrayList();
                    for (String str4 : iterable) {
                        Sign.Model.Namespace.Session session8 = map2.get(str3);
                        if (session8 == null || (accounts2 = session8.getAccounts()) == null) {
                            arrayList4 = CollectionsKt.emptyList();
                        } else {
                            ArrayList arrayList17 = new ArrayList();
                            for (Object next10 : accounts2) {
                                if (Intrinsics.areEqual((Object) SignValidator.INSTANCE.getChainFromAccount$sign_release((String) next10), (Object) str4)) {
                                    arrayList17.add(next10);
                                }
                                Map<String, Sign.Model.Namespace.Session> map3 = map;
                            }
                            arrayList4 = arrayList17;
                        }
                        CollectionsKt__MutableCollectionsKt.addAll(arrayList16, arrayList4);
                        map2 = map;
                    }
                    if (!arrayList.isEmpty()) {
                        Namespace.Session session9 = (Namespace.Session) linkedHashMap.get(str3);
                        List list2 = arrayList;
                        if (session9 != null) {
                            List<String> chains5 = session9.getChains();
                            list2 = arrayList;
                            if (chains5 != null) {
                                List plus2 = CollectionsKt.plus(chains5, iterable);
                                list2 = arrayList;
                                if (plus2 != null) {
                                    List distinct2 = CollectionsKt.distinct(plus2);
                                    list2 = arrayList;
                                    if (distinct2 != null) {
                                        list2 = distinct2;
                                    }
                                }
                            }
                        }
                        Namespace.Session session10 = (Namespace.Session) linkedHashMap.get(str3);
                        List list3 = arrayList2;
                        if (session10 != null) {
                            List<String> methods3 = session10.getMethods();
                            list3 = arrayList2;
                            if (methods3 != null) {
                                List plus3 = CollectionsKt.plus(methods3, arrayList2);
                                list3 = arrayList2;
                                if (plus3 != null) {
                                    List distinct3 = CollectionsKt.distinct(plus3);
                                    list3 = arrayList2;
                                    if (distinct3 != null) {
                                        list3 = distinct3;
                                    }
                                }
                            }
                        }
                        Namespace.Session session11 = (Namespace.Session) linkedHashMap.get(str3);
                        List list4 = arrayList3;
                        if (session11 != null) {
                            List<String> events3 = session11.getEvents();
                            list4 = arrayList3;
                            if (events3 != null) {
                                List plus4 = CollectionsKt.plus(events3, arrayList3);
                                list4 = arrayList3;
                                if (plus4 != null) {
                                    List distinct4 = CollectionsKt.distinct(plus4);
                                    list4 = arrayList3;
                                    if (distinct4 != null) {
                                        list4 = distinct4;
                                    }
                                }
                            }
                        }
                        Namespace.Session session12 = (Namespace.Session) linkedHashMap.get(str3);
                        if (!(session12 == null || (accounts = session12.getAccounts()) == null || (plus = CollectionsKt.plus(accounts, arrayList16)) == null || (distinct = CollectionsKt.distinct(plus)) == null)) {
                            arrayList16 = distinct;
                        }
                        linkedHashMap.put(str3, new Namespace.Session(list2, arrayList16, list3, list4));
                    }
                }
                map2 = map;
            }
            return ClientMapperKt.toCore((Map) linkedHashMap);
        }
    }

    @NotNull
    public static final Sign.Model.Cacao generateAuthObject(@NotNull Sign.Model.PayloadParams payloadParams, @NotNull String str, @NotNull Sign.Model.Cacao.Signature signature) {
        Intrinsics.checkNotNullParameter(payloadParams, "payload");
        Intrinsics.checkNotNullParameter(str, "issuer");
        Intrinsics.checkNotNullParameter(signature, "signature");
        return new Sign.Model.Cacao(new Sign.Model.Cacao.Header(CacaoType.CAIP222.getHeader()), ClientMapperKt.toCacaoPayload(payloadParams, str), signature);
    }

    @NotNull
    public static final Sign.Model.PayloadParams generateAuthPayloadParams(@NotNull Sign.Model.PayloadParams payloadParams, @NotNull List<String> list, @NotNull List<String> list2) {
        Sign.Model.PayloadParams payloadParams2 = payloadParams;
        List<String> list3 = list;
        List<String> list4 = list2;
        Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
        Intrinsics.checkNotNullParameter(list3, "supportedChains");
        Intrinsics.checkNotNullParameter(list4, "supportedMethods");
        String decodeReCaps = UtilsKt.decodeReCaps(payloadParams.getResources());
        if (decodeReCaps == null || decodeReCaps.length() == 0 || !StringsKt__StringsKt.contains$default((CharSequence) decodeReCaps, (CharSequence) "eip155", false, 2, (Object) null)) {
            return payloadParams2;
        }
        Map map = (Map) UtilsKt.parseReCaps(decodeReCaps).get("eip155");
        Intrinsics.checkNotNull(map);
        Iterable<String> keySet = map.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(keySet, 10));
        for (String h02 : keySet) {
            arrayList.add(StringsKt__StringsKt.substringAfter$default(h02, (char) JsonPointer.SEPARATOR, (String) null, 2, (Object) null));
        }
        List distinct = CollectionsKt.distinct(CollectionsKt.toList(CollectionsKt.intersect(payloadParams.getChains(), CollectionsKt.toSet(list3))));
        List<String> distinct2 = CollectionsKt.distinct(CollectionsKt.toList(CollectionsKt.intersect(arrayList, CollectionsKt.toSet(list4))));
        if (distinct.isEmpty()) {
            throw new Exception("Unsupported chains");
        } else if (!distinct2.isEmpty()) {
            Iterable<String> iterable = distinct;
            boolean z2 = iterable instanceof Collection;
            if (!z2 || !((Collection) iterable).isEmpty()) {
                for (String isChainIdCAIP2Compliant : iterable) {
                    if (!CoreValidator.INSTANCE.isChainIdCAIP2Compliant(isChainIdCAIP2Compliant)) {
                        throw new Exception("Chains are not CAIP-2 compliant");
                    }
                }
            }
            if (!z2 || !((Collection) iterable).isEmpty()) {
                for (String namespaceKeyFromChainId$sign_release : iterable) {
                    if (!Intrinsics.areEqual((Object) SignValidator.INSTANCE.getNamespaceKeyFromChainId$sign_release(namespaceKeyFromChainId$sign_release), (Object) "eip155")) {
                        throw new Exception("Only eip155(EVM) is supported");
                    }
                }
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (String put : iterable) {
                jSONArray.put(put);
            }
            for (String a2 : distinct2) {
                jSONObject.put(c.a("request/", a2), new JSONArray().put(0, new JSONObject().put("chains", jSONArray)));
            }
            JSONObject jSONObject2 = new JSONObject(decodeReCaps);
            jSONObject2.getJSONObject(Cacao.Payload.ATT_KEY).put("eip155", jSONObject);
            String jSONObject3 = jSONObject2.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject3, "toString(...)");
            byte[] bytes = StringsKt__StringsJVMKt.replace$default(jSONObject3, "\\/", "/", false, 4, (Object) null).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            String a3 = c.a(Cacao.Payload.RECAPS_PREFIX, Base64.encodeToString(bytes, 3));
            if (payloadParams.getResources() == null) {
                payloadParams2.setResources(CollectionsKt.listOf(a3));
            } else {
                List<String> resources = payloadParams.getResources();
                Intrinsics.checkNotNull(resources);
                payloadParams2.setResources(CollectionsKt.plus(CollectionsKt.dropLast(resources, 1), a3));
            }
            return new Sign.Model.PayloadParams(distinct, payloadParams.getDomain(), payloadParams.getNonce(), payloadParams.getAud(), payloadParams.getType(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getIat(), getStatement(payloadParams), payloadParams.getRequestId(), payloadParams.getResources());
        } else {
            throw new Exception("Unsupported methods");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.getChains();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.lang.String> getChains(java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace.Proposal> r0, java.lang.String r1) {
        /*
            java.lang.Object r0 = r0.get(r1)
            com.reown.android.internal.common.model.Namespace$Proposal r0 = (com.reown.android.internal.common.model.Namespace.Proposal) r0
            if (r0 == 0) goto L_0x000e
            java.util.List r0 = r0.getChains()
            if (r0 != 0) goto L_0x0012
        L_0x000e:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.client.utils.ApprovedNamespacesUtils.getChains(java.util.Map, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.getEvents();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.lang.String> getEvents(java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace.Proposal> r0, java.lang.String r1) {
        /*
            java.lang.Object r0 = r0.get(r1)
            com.reown.android.internal.common.model.Namespace$Proposal r0 = (com.reown.android.internal.common.model.Namespace.Proposal) r0
            if (r0 == 0) goto L_0x000e
            java.util.List r0 = r0.getEvents()
            if (r0 != 0) goto L_0x0012
        L_0x000e:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.client.utils.ApprovedNamespacesUtils.getEvents(java.util.Map, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.getMethods();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.lang.String> getMethods(java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace.Proposal> r0, java.lang.String r1) {
        /*
            java.lang.Object r0 = r0.get(r1)
            com.reown.android.internal.common.model.Namespace$Proposal r0 = (com.reown.android.internal.common.model.Namespace.Proposal) r0
            if (r0 == 0) goto L_0x000e
            java.util.List r0 = r0.getMethods()
            if (r0 != 0) goto L_0x0012
        L_0x000e:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.client.utils.ApprovedNamespacesUtils.getMethods(java.util.Map, java.lang.String):java.util.List");
    }

    private static final List<String> getNamespaceChains(String str, Namespace namespace) {
        if (CoreValidator.INSTANCE.isChainIdCAIP2Compliant(str)) {
            return CollectionsKt.listOf(str);
        }
        List<String> chains = namespace.getChains();
        Intrinsics.checkNotNull(chains);
        return chains;
    }

    private static final String getStatement(Sign.Model.PayloadParams payloadParams) {
        String statement = payloadParams.getStatement();
        return (statement == null || !StringsKt__StringsKt.contains$default((CharSequence) statement, (CharSequence) CacaoKt.RECAPS_STATEMENT, false, 2, (Object) null)) ? CacaoKt.getStatement(new Pair(payloadParams.getStatement(), payloadParams.getResources())) : payloadParams.getStatement();
    }

    private static final String normalizeKey(String str) {
        return CoreValidator.INSTANCE.isChainIdCAIP2Compliant(str) ? SignValidator.INSTANCE.getNamespaceKeyFromChainId$sign_release(str) : str;
    }

    @NotNull
    public static final Map<String, Namespace.Proposal> normalizeNamespaces(@NotNull Map<String, Namespace.Proposal> map) {
        Intrinsics.checkNotNullParameter(map, "namespaces");
        if (SignValidator.INSTANCE.isNamespaceKeyRegexCompliant(map)) {
            return map;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Namespace.Proposal proposal = (Namespace.Proposal) next.getValue();
            String normalizeKey = normalizeKey(str);
            linkedHashMap.put(normalizeKey, new Namespace.Proposal(CollectionsKt.plus(getMethods(linkedHashMap, normalizeKey), proposal.getMethods()), CollectionsKt.plus(getChains(linkedHashMap, normalizeKey), getNamespaceChains(str, proposal)), CollectionsKt.plus(getEvents(linkedHashMap, normalizeKey), proposal.getEvents())));
        }
        return MapsKt.toMap(linkedHashMap);
    }
}
