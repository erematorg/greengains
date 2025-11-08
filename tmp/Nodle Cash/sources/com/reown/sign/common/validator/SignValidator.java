package com.reown.sign.common.validator;

import android.annotation.SuppressLint;
import android.support.v4.media.session.a;
import androidx.core.app.NotificationCompat;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.SymmetricKey;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.ValidationError;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0018\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b\rJM\u0010\u000e\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u00072\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b\u0013JM\u0010\u0014\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u00072\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b\u0015J9\u0010\u0016\u001a\u00020\u00052\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b\u0018J$\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u001cH\u0002JI\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b!JI\u0010\"\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b$J-\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b(J-\u0010)\u001a\u00020\u00052\u0006\u0010#\u001a\u00020*2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b+J5\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bH\bø\u0001\u0000¢\u0006\u0002\b0J\u0017\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\bH\u0000¢\u0006\u0002\b4J.\u00105\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J<\u00107\u001a\u00020\u001a2\u0018\u00108\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\u00072\u0018\u00109\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\u0007H\u0002J.\u0010:\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0003J<\u0010;\u001a\u00020\u001a2\u0018\u0010<\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\u00072\u0018\u0010=\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\u0007H\u0002J5\u0010>\u001a\u00020\u001a2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u00072\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0007H\u0000¢\u0006\u0002\b?J\u001c\u0010@\u001a\u00020\u001a2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J\u001a\u0010A\u001a\u00020\u001a2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007J\u001c\u0010B\u001a\u00020\u001a2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u0007H\u0002J\u001c\u0010C\u001a\u00020\u001a2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u0007H\u0002J\u001c\u0010D\u001a\u00020\u001a2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J\u001c\u0010E\u001a\u00020\u001a2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J\u001c\u0010F\u001a\u00020\u001a2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J\u001c\u0010G\u001a\u00020\u001a2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J(\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J\u0015\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020\bH\u0000¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0000¢\u0006\u0002\bM\u0002\u0007\n\u0005\b20\u0001¨\u0006N"}, d2 = {"Lcom/reown/sign/common/validator/SignValidator;", "", "<init>", "()V", "validateProposalNamespaces", "", "namespaces", "", "", "Lcom/reown/android/internal/common/model/Namespace;", "onError", "Lkotlin/Function1;", "Lcom/reown/sign/engine/model/ValidationError;", "validateProposalNamespaces$sign_release", "validateSessionNamespace", "sessionNamespaces", "Lcom/reown/android/internal/common/model/Namespace$Session;", "requiredNamespaces", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "validateSessionNamespace$sign_release", "validateSupportedNamespace", "validateSupportedNamespace$sign_release", "validateProperties", "properties", "validateProperties$sign_release", "areAllNamespacesApproved", "", "sessionNamespacesKeys", "", "proposalNamespacesKeys", "validateChainIdWithMethodAuthorisation", "chainId", "method", "validateChainIdWithMethodAuthorisation$sign_release", "validateChainIdWithEventAuthorisation", "event", "validateChainIdWithEventAuthorisation$sign_release", "validateSessionRequest", "request", "Lcom/reown/sign/engine/model/EngineDO$Request;", "validateSessionRequest$sign_release", "validateEvent", "Lcom/reown/sign/engine/model/EngineDO$Event;", "validateEvent$sign_release", "validateSessionExtend", "newExpiry", "", "currentExpiry", "validateSessionExtend$sign_release", "validateWCUri", "Lcom/reown/sign/engine/model/EngineDO$WalletConnectUri;", "uri", "validateWCUri$sign_release", "allMethodsWithChains", "", "areAllMethodsApproved", "allApprovedMethodsWithChains", "allRequiredMethodsWithChains", "allEventsWithChains", "areAllEventsApproved", "allApprovedEventsWithChains", "allRequiredEventsWithChains", "areAllChainsApproved", "areAllChainsApproved$sign_release", "areNamespacesKeysProperlyFormatted", "isNamespaceKeyRegexCompliant", "areAccountIdsValid", "areAccountsInMatchingNamespaceAndChains", "areChainsDefined", "areChainsNotEmpty", "areChainIdsValid", "areChainsInMatchingNamespace", "getValidNamespaces", "getChainFromAccount", "accountId", "getChainFromAccount$sign_release", "getNamespaceKeyFromChainId", "getNamespaceKeyFromChainId$sign_release", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSignValidator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 6 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,326:1\n44#1,13:327\n1193#2,2:340\n1267#2,4:342\n1563#2:357\n1634#2,2:358\n1636#2:367\n1563#2:381\n1634#2,2:382\n1636#2:391\n1563#2:405\n1634#2,3:406\n1252#2,4:428\n1563#2:444\n1634#2,2:445\n1636#2:454\n1563#2:468\n1634#2,2:469\n1636#2:478\n1563#2:492\n1634#2,3:493\n1252#2,4:515\n1563#2:529\n1634#2,3:530\n1740#2,3:542\n1740#2,3:548\n1740#2,3:551\n1740#2,3:575\n1740#2,3:580\n1#3:346\n536#4:347\n521#4,6:348\n382#4,7:360\n536#4:371\n521#4,6:372\n382#4,7:384\n536#4:395\n521#4,6:396\n382#4,7:416\n463#4:426\n413#4:427\n536#4:434\n521#4,6:435\n382#4,7:447\n536#4:458\n521#4,6:459\n382#4,7:471\n536#4:482\n521#4,6:483\n382#4,7:503\n463#4:513\n413#4:514\n536#4:521\n521#4,6:522\n536#4:555\n521#4,6:556\n536#4:562\n521#4,6:563\n536#4:584\n521#4,6:585\n77#5:354\n97#5,2:355\n99#5,3:368\n77#5:378\n97#5,2:379\n99#5,3:392\n77#5:402\n97#5,2:403\n99#5,3:409\n216#5,2:432\n77#5:441\n97#5,2:442\n99#5,3:455\n77#5:465\n97#5,2:466\n99#5,3:479\n77#5:489\n97#5,2:490\n99#5,3:496\n216#5,2:519\n216#5:528\n217#5:533\n168#5,3:534\n168#5,3:537\n168#5,2:540\n170#5:545\n168#5,2:546\n170#5:554\n77#5:569\n97#5,5:570\n168#5,2:578\n170#5:583\n1011#6:412\n1045#6,3:413\n1048#6,3:423\n1011#6:499\n1045#6,3:500\n1048#6,3:510\n*S KotlinDebug\n*F\n+ 1 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n64#1:327,13\n150#1:340,2\n150#1:342,4\n174#1:357\n174#1:358,2\n174#1:367\n184#1:381\n184#1:382,2\n184#1:391\n193#1:405\n193#1:406,3\n199#1:428,4\n221#1:444\n221#1:445,2\n221#1:454\n231#1:468\n231#1:469,2\n231#1:478\n240#1:492\n240#1:493,3\n246#1:515,4\n266#1:529\n266#1:530,3\n278#1:542,3\n283#1:548,3\n285#1:551,3\n300#1:575,3\n304#1:580,3\n172#1:347\n172#1:348,6\n175#1:360,7\n182#1:371\n182#1:372,6\n185#1:384,7\n192#1:395\n192#1:396,6\n198#1:416,7\n199#1:426\n199#1:427\n219#1:434\n219#1:435,6\n222#1:447,7\n229#1:458\n229#1:459,6\n232#1:471,7\n239#1:482\n239#1:483,6\n245#1:503,7\n246#1:513\n246#1:514\n264#1:521\n264#1:522,6\n290#1:555\n290#1:556,6\n295#1:562\n295#1:563,6\n307#1:584\n307#1:585,6\n173#1:354\n173#1:355,2\n173#1:368,3\n183#1:378\n183#1:379,2\n183#1:392,3\n193#1:402\n193#1:403,2\n193#1:409,3\n206#1:432,2\n220#1:441\n220#1:442,2\n220#1:455,3\n230#1:465\n230#1:466,2\n230#1:479,3\n240#1:489\n240#1:490,2\n240#1:496,3\n253#1:519,2\n265#1:528\n265#1:533\n272#1:534,3\n275#1:537,3\n278#1:540,2\n278#1:545\n281#1:546,2\n281#1:554\n300#1:569\n300#1:570,5\n304#1:578,2\n304#1:583\n198#1:412\n198#1:413,3\n198#1:423,3\n245#1:499\n245#1:500,3\n245#1:510,3\n*E\n"})
public final class SignValidator {
    @NotNull
    public static final SignValidator INSTANCE = new SignValidator();

    private SignValidator() {
    }

    /* access modifiers changed from: private */
    @SuppressLint({"SuspiciousIndentation"})
    public final Map<String, List<String>> allEventsWithChains(Map<String, ? extends Namespace> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Namespace namespace = (Namespace) next.getValue();
            if (CoreValidator.INSTANCE.isNamespaceRegexCompliant((String) next.getKey()) && namespace.getChains() != null) {
                linkedHashMap2.put(next.getKey(), next.getValue());
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry value : linkedHashMap2.entrySet()) {
            Namespace namespace2 = (Namespace) value.getValue();
            Iterable<String> events = namespace2.getEvents();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(events, 10));
            for (String str : events) {
                Object obj = linkedHashMap.get(str);
                if (obj == null) {
                    obj = new ArrayList();
                    linkedHashMap.put(str, obj);
                }
                List<String> chains = namespace2.getChains();
                Intrinsics.checkNotNull(chains);
                arrayList2.add(Boolean.valueOf(((List) obj).addAll(chains)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        for (Map.Entry next2 : map.entrySet()) {
            Namespace namespace3 = (Namespace) next2.getValue();
            if (CoreValidator.INSTANCE.isChainIdCAIP2Compliant((String) next2.getKey()) && namespace3.getChains() == null) {
                linkedHashMap4.put(next2.getKey(), next2.getValue());
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : linkedHashMap4.entrySet()) {
            String str2 = (String) entry.getKey();
            Iterable<String> events2 = ((Namespace) entry.getValue()).getEvents();
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(events2, 10));
            for (String str3 : events2) {
                Object obj2 = linkedHashMap3.get(str3);
                if (obj2 == null) {
                    obj2 = new ArrayList();
                    linkedHashMap3.put(str3, obj2);
                }
                arrayList4.add(Boolean.valueOf(((List) obj2).add(str2)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, arrayList4);
        }
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        for (Map.Entry next3 : map.entrySet()) {
            String str4 = (String) next3.getKey();
            Namespace namespace4 = (Namespace) next3.getValue();
            if ((namespace4 instanceof Namespace.Session) && CoreValidator.INSTANCE.isNamespaceRegexCompliant(str4) && ((Namespace.Session) namespace4).getChains() == null) {
                linkedHashMap5.put(next3.getKey(), next3.getValue());
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Map.Entry value2 : linkedHashMap5.entrySet()) {
            Namespace namespace5 = (Namespace) value2.getValue();
            Intrinsics.checkNotNull(namespace5, "null cannot be cast to non-null type com.reown.android.internal.common.model.Namespace.Session");
            Namespace.Session session = (Namespace.Session) namespace5;
            Iterable<String> events3 = session.getEvents();
            ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(events3, 10));
            for (String str5 : events3) {
                Iterable<String> accounts = session.getAccounts();
                ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(accounts, 10));
                for (String chainFromAccount$sign_release : accounts) {
                    arrayList7.add(INSTANCE.getChainFromAccount$sign_release(chainFromAccount$sign_release));
                }
                arrayList6.add(TuplesKt.to(str5, arrayList7));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList5, arrayList6);
        }
        Sequence<Map.Entry> distinct = SequencesKt.distinct(SequencesKt.plus(SequencesKt.plus(MapsKt.asSequence(linkedHashMap), MapsKt.asSequence(linkedHashMap3)), MapsKt.asSequence(MapsKt.toMap(arrayList5))));
        LinkedHashMap linkedHashMap6 = new LinkedHashMap();
        for (Map.Entry entry2 : distinct) {
            String str6 = (String) entry2.getKey();
            Object obj3 = linkedHashMap6.get(str6);
            if (obj3 == null) {
                obj3 = new ArrayList();
                linkedHashMap6.put(str6, obj3);
            }
            ((List) obj3).add((List) entry2.getValue());
        }
        LinkedHashMap linkedHashMap7 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap6.size()));
        for (Map.Entry entry3 : linkedHashMap6.entrySet()) {
            linkedHashMap7.put(entry3.getKey(), CollectionsKt.flatten((List) entry3.getValue()));
        }
        return linkedHashMap7;
    }

    /* access modifiers changed from: private */
    public final Map<String, List<String>> allMethodsWithChains(Map<String, ? extends Namespace> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Namespace namespace = (Namespace) next.getValue();
            if (CoreValidator.INSTANCE.isNamespaceRegexCompliant((String) next.getKey()) && namespace.getChains() != null) {
                linkedHashMap2.put(next.getKey(), next.getValue());
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry value : linkedHashMap2.entrySet()) {
            Namespace namespace2 = (Namespace) value.getValue();
            Iterable<String> methods = namespace2.getMethods();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(methods, 10));
            for (String str : methods) {
                Object obj = linkedHashMap.get(str);
                if (obj == null) {
                    obj = new ArrayList();
                    linkedHashMap.put(str, obj);
                }
                List<String> chains = namespace2.getChains();
                Intrinsics.checkNotNull(chains);
                arrayList2.add(Boolean.valueOf(((List) obj).addAll(chains)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        for (Map.Entry next2 : map.entrySet()) {
            Namespace namespace3 = (Namespace) next2.getValue();
            if (CoreValidator.INSTANCE.isChainIdCAIP2Compliant((String) next2.getKey()) && namespace3.getChains() == null) {
                linkedHashMap4.put(next2.getKey(), next2.getValue());
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : linkedHashMap4.entrySet()) {
            String str2 = (String) entry.getKey();
            Iterable<String> methods2 = ((Namespace) entry.getValue()).getMethods();
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(methods2, 10));
            for (String str3 : methods2) {
                Object obj2 = linkedHashMap3.get(str3);
                if (obj2 == null) {
                    obj2 = new ArrayList();
                    linkedHashMap3.put(str3, obj2);
                }
                arrayList4.add(Boolean.valueOf(((List) obj2).add(str2)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, arrayList4);
        }
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        for (Map.Entry next3 : map.entrySet()) {
            String str4 = (String) next3.getKey();
            Namespace namespace4 = (Namespace) next3.getValue();
            if ((namespace4 instanceof Namespace.Session) && CoreValidator.INSTANCE.isNamespaceRegexCompliant(str4) && ((Namespace.Session) namespace4).getChains() == null) {
                linkedHashMap5.put(next3.getKey(), next3.getValue());
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Map.Entry value2 : linkedHashMap5.entrySet()) {
            Namespace namespace5 = (Namespace) value2.getValue();
            Intrinsics.checkNotNull(namespace5, "null cannot be cast to non-null type com.reown.android.internal.common.model.Namespace.Session");
            Namespace.Session session = (Namespace.Session) namespace5;
            Iterable<String> methods3 = session.getMethods();
            ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(methods3, 10));
            for (String str5 : methods3) {
                Iterable<String> accounts = session.getAccounts();
                ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(accounts, 10));
                for (String chainFromAccount$sign_release : accounts) {
                    arrayList7.add(INSTANCE.getChainFromAccount$sign_release(chainFromAccount$sign_release));
                }
                arrayList6.add(TuplesKt.to(str5, arrayList7));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList5, arrayList6);
        }
        Sequence<Map.Entry> distinct = SequencesKt.distinct(SequencesKt.plus(SequencesKt.plus(MapsKt.asSequence(linkedHashMap), MapsKt.asSequence(linkedHashMap3)), MapsKt.asSequence(MapsKt.toMap(arrayList5))));
        LinkedHashMap linkedHashMap6 = new LinkedHashMap();
        for (Map.Entry entry2 : distinct) {
            String str6 = (String) entry2.getKey();
            Object obj3 = linkedHashMap6.get(str6);
            if (obj3 == null) {
                obj3 = new ArrayList();
                linkedHashMap6.put(str6, obj3);
            }
            ((List) obj3).add((List) entry2.getValue());
        }
        LinkedHashMap linkedHashMap7 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap6.size()));
        for (Map.Entry entry3 : linkedHashMap6.entrySet()) {
            linkedHashMap7.put(entry3.getKey(), CollectionsKt.flatten((List) entry3.getValue()));
        }
        return linkedHashMap7;
    }

    /* access modifiers changed from: private */
    public final boolean areAccountIdsValid(Map<String, Namespace.Session> map) {
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<String, Namespace.Session> value : map.entrySet()) {
            Iterable<String> accounts = ((Namespace.Session) value.getValue()).getAccounts();
            if (!(accounts instanceof Collection) || !((Collection) accounts).isEmpty()) {
                for (String isAccountIdCAIP10Compliant : accounts) {
                    if (!CoreValidator.INSTANCE.isAccountIdCAIP10Compliant(isAccountIdCAIP10Compliant)) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final boolean areAccountsInMatchingNamespaceAndChains(Map<String, Namespace.Session> map) {
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Namespace.Session session = (Namespace.Session) next.getValue();
            if (!CoreValidator.INSTANCE.isNamespaceRegexCompliant(str) || session.getChains() == null) {
                Iterable<String> accounts = session.getAccounts();
                if (!(accounts instanceof Collection) || !((Collection) accounts).isEmpty()) {
                    for (String s3 : accounts) {
                        if (!StringsKt__StringsKt.contains$default((CharSequence) s3, (CharSequence) str, false, 2, (Object) null)) {
                        }
                    }
                    continue;
                }
            } else {
                Iterable<String> accounts2 = session.getAccounts();
                if (!(accounts2 instanceof Collection) || !((Collection) accounts2).isEmpty()) {
                    for (String str2 : accounts2) {
                        if (StringsKt__StringsKt.contains$default((CharSequence) str2, (CharSequence) str, false, 2, (Object) null)) {
                            List<String> chains = session.getChains();
                            Intrinsics.checkNotNull(chains);
                            if (!chains.contains(INSTANCE.getChainFromAccount$sign_release(str2))) {
                            }
                        }
                    }
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean areAllEventsApproved(java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>> r3, java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>> r4) {
        /*
            r2 = this;
            java.util.Set r2 = r4.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0008:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0033
            java.lang.Object r4 = r2.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r0 = r4.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r4 = r4.getValue()
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r0 = r3.get(r0)
            java.util.List r0 = (java.util.List) r0
            r1 = 0
            if (r0 != 0) goto L_0x002a
            return r1
        L_0x002a:
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r0.containsAll(r4)
            if (r4 != 0) goto L_0x0008
            return r1
        L_0x0033:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.validator.SignValidator.areAllEventsApproved(java.util.Map, java.util.Map):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean areAllMethodsApproved(java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>> r3, java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>> r4) {
        /*
            r2 = this;
            java.util.Set r2 = r4.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0008:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0033
            java.lang.Object r4 = r2.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r0 = r4.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r4 = r4.getValue()
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r0 = r3.get(r0)
            java.util.List r0 = (java.util.List) r0
            r1 = 0
            if (r0 != 0) goto L_0x002a
            return r1
        L_0x002a:
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r0.containsAll(r4)
            if (r4 != 0) goto L_0x0008
            return r1
        L_0x0033:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.validator.SignValidator.areAllMethodsApproved(java.util.Map, java.util.Map):boolean");
    }

    /* access modifiers changed from: private */
    public final boolean areAllNamespacesApproved(Set<String> set, Set<String> set2) {
        return set.containsAll(set2);
    }

    /* access modifiers changed from: private */
    public final boolean areChainIdsValid(Map<String, ? extends Namespace> map) {
        Map<String, Namespace> validNamespaces = getValidNamespaces(map);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Namespace> value : validNamespaces.entrySet()) {
            List<String> chains = ((Namespace) value.getValue()).getChains();
            Intrinsics.checkNotNull(chains);
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, chains);
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!CoreValidator.INSTANCE.isChainIdCAIP2Compliant((String) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final boolean areChainsDefined(Map<String, ? extends Namespace> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Namespace namespace = (Namespace) next.getValue();
            if (CoreValidator.INSTANCE.isNamespaceRegexCompliant((String) next.getKey()) && namespace.getChains() == null) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        return linkedHashMap.isEmpty();
    }

    /* access modifiers changed from: private */
    public final boolean areChainsInMatchingNamespace(Map<String, ? extends Namespace> map) {
        Map<String, Namespace> validNamespaces = getValidNamespaces(map);
        if (validNamespaces.isEmpty()) {
            return true;
        }
        for (Map.Entry next : validNamespaces.entrySet()) {
            String str = (String) next.getKey();
            List<String> chains = ((Namespace) next.getValue()).getChains();
            Intrinsics.checkNotNull(chains);
            Iterable<String> iterable = chains;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (String q2 : iterable) {
                    if (!StringsKt__StringsKt.contains((CharSequence) q2, (CharSequence) str, true)) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final boolean areChainsNotEmpty(Map<String, ? extends Namespace> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Namespace namespace = (Namespace) next.getValue();
            if (CoreValidator.INSTANCE.isNamespaceRegexCompliant((String) next.getKey()) && namespace.getChains() != null) {
                List<String> chains = namespace.getChains();
                Intrinsics.checkNotNull(chains);
                if (chains.isEmpty()) {
                    linkedHashMap.put(next.getKey(), next.getValue());
                }
            }
        }
        return linkedHashMap.isEmpty();
    }

    /* access modifiers changed from: private */
    public final boolean areNamespacesKeysProperlyFormatted(Map<String, ? extends Namespace> map) {
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<String, ? extends Namespace> key : map.entrySet()) {
            String str = (String) key.getKey();
            CoreValidator coreValidator = CoreValidator.INSTANCE;
            if (!coreValidator.isNamespaceRegexCompliant(str) && !coreValidator.isChainIdCAIP2Compliant(str)) {
                return false;
            }
        }
        return true;
    }

    private final Map<String, Namespace> getValidNamespaces(Map<String, ? extends Namespace> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Namespace namespace = (Namespace) next.getValue();
            if (namespace.getChains() != null) {
                List<String> chains = namespace.getChains();
                Intrinsics.checkNotNull(chains);
                if (!chains.isEmpty()) {
                    linkedHashMap.put(next.getKey(), next.getValue());
                }
            }
        }
        return linkedHashMap;
    }

    public final boolean areAllChainsApproved$sign_release(@NotNull Map<String, Namespace.Session> map, @NotNull Map<String, Namespace.Proposal> map2) {
        Intrinsics.checkNotNullParameter(map, "sessionNamespaces");
        Intrinsics.checkNotNullParameter(map2, "requiredNamespaces");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map2.entrySet()) {
            Namespace.Proposal proposal = (Namespace.Proposal) next.getValue();
            if (!CoreValidator.INSTANCE.isChainIdCAIP2Compliant((String) next.getKey()) && proposal.getChains() != null) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Namespace.Proposal proposal2 = (Namespace.Proposal) entry.getValue();
            Namespace.Session session = map.get((String) entry.getKey());
            List<String> accounts = session != null ? session.getAccounts() : null;
            Intrinsics.checkNotNull(accounts);
            Iterable<String> iterable = accounts;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (String chainFromAccount$sign_release : iterable) {
                arrayList.add(INSTANCE.getChainFromAccount$sign_release(chainFromAccount$sign_release));
            }
            List<String> chains = proposal2.getChains();
            Intrinsics.checkNotNull(chains);
            if (!arrayList.containsAll(chains)) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ String getChainFromAccount$sign_release(String str) {
        Intrinsics.checkNotNullParameter(str, "accountId");
        List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
        return (Z2.isEmpty() || Z2.size() != 3) ? str : a.n((String) Z2.get(0), ":", (String) Z2.get(1));
    }

    public final /* synthetic */ String getNamespaceKeyFromChainId$sign_release(String str) {
        Intrinsics.checkNotNullParameter(str, "chainId");
        List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
        return (Z2.isEmpty() || Z2.size() != 2) ? str : (String) Z2.get(0);
    }

    public final boolean isNamespaceKeyRegexCompliant(@NotNull Map<String, ? extends Namespace> map) {
        Intrinsics.checkNotNullParameter(map, "namespaces");
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<String, ? extends Namespace> key : map.entrySet()) {
            if (!CoreValidator.INSTANCE.isNamespaceRegexCompliant((String) key.getKey())) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ void validateChainIdWithEventAuthorisation$sign_release(String str, String str2, Map<String, Namespace.Session> map, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "chainId");
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(map, "namespaces");
        Intrinsics.checkNotNullParameter(function1, "onError");
        Map access$allEventsWithChains = allEventsWithChains(map);
        if (access$allEventsWithChains.get(str2) != null) {
            Object obj = access$allEventsWithChains.get(str2);
            Intrinsics.checkNotNull(obj);
            if (((List) obj).contains(str)) {
                return;
            }
        }
        function1.invoke(ValidationError.UnauthorizedEvent.INSTANCE);
    }

    public final /* synthetic */ void validateChainIdWithMethodAuthorisation$sign_release(String str, String str2, Map<String, Namespace.Session> map, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "chainId");
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(map, "namespaces");
        Intrinsics.checkNotNullParameter(function1, "onError");
        Map access$allMethodsWithChains = allMethodsWithChains(map);
        if (access$allMethodsWithChains.get(str2) != null) {
            Object obj = access$allMethodsWithChains.get(str2);
            Intrinsics.checkNotNull(obj);
            if (((List) obj).contains(str)) {
                return;
            }
        }
        function1.invoke(ValidationError.UnauthorizedMethod.INSTANCE);
    }

    public final /* synthetic */ void validateEvent$sign_release(EngineDO.Event event, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(function1, "onError");
        if (event.getData().length() == 0 || event.getName().length() == 0 || event.getChainId().length() == 0 || !CoreValidator.INSTANCE.isChainIdCAIP2Compliant(event.getChainId())) {
            function1.invoke(ValidationError.InvalidEvent.INSTANCE);
        }
    }

    public final /* synthetic */ void validateProperties$sign_release(Map<String, String> map, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(map, StringLookupFactory.KEY_PROPERTIES);
        Intrinsics.checkNotNullParameter(function1, "onError");
        if (map.isEmpty()) {
            function1.invoke(ValidationError.InvalidSessionProperties.INSTANCE);
        }
    }

    public final /* synthetic */ void validateProposalNamespaces$sign_release(Map<String, ? extends Namespace> map, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(map, "namespaces");
        Intrinsics.checkNotNullParameter(function1, "onError");
        if (!areNamespacesKeysProperlyFormatted(map)) {
            function1.invoke(ValidationError.UnsupportedNamespaceKey.INSTANCE);
        } else if (!areChainsDefined(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE));
        } else if (!areChainsNotEmpty(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE));
        } else if (!areChainIdsValid(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE));
        } else if (!areChainsInMatchingNamespace(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE));
        }
    }

    public final /* synthetic */ void validateSessionExtend$sign_release(long j2, long j3, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "onError");
        long j4 = j2 - j3;
        long weekInSeconds = Time.getWeekInSeconds();
        if (j2 <= j3 || j4 > weekInSeconds) {
            function1.invoke(ValidationError.InvalidExtendRequest.INSTANCE);
        }
    }

    public final /* synthetic */ void validateSessionNamespace$sign_release(Map<String, Namespace.Session> map, Map<String, Namespace.Proposal> map2, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(map, "sessionNamespaces");
        Intrinsics.checkNotNullParameter(map2, "requiredNamespaces");
        Intrinsics.checkNotNullParameter(function1, "onError");
        if (map.isEmpty()) {
            function1.invoke(ValidationError.EmptyNamespaces.INSTANCE);
        } else if (!areNamespacesKeysProperlyFormatted(map)) {
            function1.invoke(ValidationError.UnsupportedNamespaceKey.INSTANCE);
        } else if (!areChainsNotEmpty(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE));
        } else if (!areChainIdsValid(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE));
        } else if (!areChainsInMatchingNamespace(map)) {
            function1.invoke(new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE));
        } else if (!areAccountIdsValid(map)) {
            function1.invoke(new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_CAIP_10_MESSAGE));
        } else if (!areAccountsInMatchingNamespaceAndChains(map)) {
            function1.invoke(new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_WRONG_NAMESPACE_MESSAGE));
        } else if (!areAllNamespacesApproved(map.keySet(), map2.keySet())) {
            function1.invoke(ValidationError.UserRejected.INSTANCE);
        } else if (!areAllMethodsApproved(allMethodsWithChains(map), allMethodsWithChains(map2))) {
            function1.invoke(ValidationError.UserRejectedMethods.INSTANCE);
        } else if (!areAllEventsApproved(allEventsWithChains(map), allEventsWithChains(map2))) {
            function1.invoke(ValidationError.UserRejectedEvents.INSTANCE);
        }
    }

    public final /* synthetic */ void validateSessionRequest$sign_release(EngineDO.Request request, Function1<? super ValidationError, Unit> function1) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(function1, "onError");
        if (request.getParams().length() == 0 || request.getMethod().length() == 0 || request.getChainId().length() == 0 || request.getTopic().length() == 0 || !CoreValidator.INSTANCE.isChainIdCAIP2Compliant(request.getChainId())) {
            function1.invoke(ValidationError.InvalidSessionRequest.INSTANCE);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void validateSupportedNamespace$sign_release(java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace.Session> r4, java.util.Map<java.lang.String, com.reown.android.internal.common.model.Namespace.Proposal> r5, kotlin.jvm.functions.Function1<? super com.reown.sign.engine.model.ValidationError, kotlin.Unit> r6) {
        /*
            r3 = this;
            java.lang.String r0 = "sessionNamespaces"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "requiredNamespaces"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "onError"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            boolean r0 = r4.isEmpty()
            java.lang.String r1 = "Accounts must be defined in matching namespace"
            if (r0 == 0) goto L_0x001e
            com.reown.sign.engine.model.ValidationError$EmptyNamespaces r0 = com.reown.sign.engine.model.ValidationError.EmptyNamespaces.INSTANCE
        L_0x0019:
            r6.invoke(r0)
            goto L_0x009f
        L_0x001e:
            boolean r0 = r3.areNamespacesKeysProperlyFormatted(r4)
            if (r0 != 0) goto L_0x0027
            com.reown.sign.engine.model.ValidationError$UnsupportedNamespaceKey r0 = com.reown.sign.engine.model.ValidationError.UnsupportedNamespaceKey.INSTANCE
            goto L_0x0019
        L_0x0027:
            boolean r0 = r3.areChainsNotEmpty(r4)
            if (r0 != 0) goto L_0x0035
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains
            java.lang.String r2 = "Chains must not be empty"
            r0.<init>(r2)
            goto L_0x0019
        L_0x0035:
            boolean r0 = r3.areChainIdsValid(r4)
            if (r0 != 0) goto L_0x0043
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains
            java.lang.String r2 = "Chains must be CAIP-2 compliant"
            r0.<init>(r2)
            goto L_0x0019
        L_0x0043:
            boolean r0 = r3.areChainsInMatchingNamespace(r4)
            if (r0 != 0) goto L_0x0051
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains
            java.lang.String r2 = "Chains must be defined in matching namespace"
            r0.<init>(r2)
            goto L_0x0019
        L_0x0051:
            boolean r0 = r3.areAccountIdsValid(r4)
            if (r0 != 0) goto L_0x005f
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r0 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains
            java.lang.String r2 = "Accounts must be CAIP-10 compliant"
            r0.<init>(r2)
            goto L_0x0019
        L_0x005f:
            boolean r0 = r3.areAccountsInMatchingNamespaceAndChains(r4)
            if (r0 != 0) goto L_0x006b
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r0 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains
            r0.<init>(r1)
            goto L_0x0019
        L_0x006b:
            java.util.Set r0 = r4.keySet()
            java.util.Set r2 = r5.keySet()
            boolean r0 = r3.areAllNamespacesApproved(r0, r2)
            if (r0 != 0) goto L_0x007c
            com.reown.sign.engine.model.ValidationError$UserRejected r0 = com.reown.sign.engine.model.ValidationError.UserRejected.INSTANCE
            goto L_0x0019
        L_0x007c:
            java.util.Map r0 = r3.allMethodsWithChains(r4)
            java.util.Map r2 = r3.allMethodsWithChains(r5)
            boolean r0 = r3.areAllMethodsApproved(r0, r2)
            if (r0 != 0) goto L_0x008d
            com.reown.sign.engine.model.ValidationError$UserRejectedMethods r0 = com.reown.sign.engine.model.ValidationError.UserRejectedMethods.INSTANCE
            goto L_0x0019
        L_0x008d:
            java.util.Map r0 = r3.allEventsWithChains(r4)
            java.util.Map r2 = r3.allEventsWithChains(r5)
            boolean r0 = r3.areAllEventsApproved(r0, r2)
            if (r0 != 0) goto L_0x009f
            com.reown.sign.engine.model.ValidationError$UserRejectedEvents r0 = com.reown.sign.engine.model.ValidationError.UserRejectedEvents.INSTANCE
            goto L_0x0019
        L_0x009f:
            boolean r3 = r3.areAllChainsApproved$sign_release(r4, r5)
            if (r3 != 0) goto L_0x00ad
            com.reown.sign.engine.model.ValidationError$UserRejectedChains r3 = new com.reown.sign.engine.model.ValidationError$UserRejectedChains
            r3.<init>(r1)
            r6.invoke(r3)
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.validator.SignValidator.validateSupportedNamespace$sign_release(java.util.Map, java.util.Map, kotlin.jvm.functions.Function1):void");
    }

    public final /* synthetic */ EngineDO.WalletConnectUri validateWCUri$sign_release(String str) {
        Intrinsics.checkNotNullParameter(str, "uri");
        if (!StringsKt__StringsJVMKt.startsWith$default(str, "wc:", false, 2, (Object) null)) {
            return null;
        }
        if (!StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "wc://", false, 2, (Object) null)) {
            str = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "wc:/", false, 2, (Object) null) ? StringsKt__StringsJVMKt.replace$default(str, "wc:/", "wc://", false, 4, (Object) null) : StringsKt__StringsJVMKt.replace$default(str, "wc:", "wc://", false, 4, (Object) null);
        }
        try {
            URI uri = new URI(str);
            String userInfo = uri.getUserInfo();
            Intrinsics.checkNotNullExpressionValue(userInfo, "getUserInfo(...)");
            if (userInfo.length() == 0) {
                return null;
            }
            String query = uri.getQuery();
            Intrinsics.checkNotNullExpressionValue(query, "getQuery(...)");
            Iterable<String> Z2 = StringsKt__StringsKt.split$default((CharSequence) query, new String[]{"&"}, false, 0, 6, (Object) null);
            LinkedHashMap linkedHashMap = new LinkedHashMap(androidx.compose.animation.core.a.h(Z2, 16));
            for (String str2 : Z2) {
                Pair pair = TuplesKt.to(StringsKt__StringsKt.substringBefore$default(str2, StickyVariantProvider.KEY_VALUE_DELIMITER, (String) null, 2, (Object) null), StringsKt__StringsKt.substringAfter$default(str2, StickyVariantProvider.KEY_VALUE_DELIMITER, (String) null, 2, (Object) null));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            String str3 = (String) linkedHashMap.get("relay-protocol");
            if (str3 == null || str3.length() == 0) {
                return null;
            }
            String str4 = (String) linkedHashMap.get("relay-data");
            String str5 = (String) linkedHashMap.get("symKey");
            if (str5 == null || str5.length() == 0) {
                return null;
            }
            String userInfo2 = uri.getUserInfo();
            Intrinsics.checkNotNullExpressionValue(userInfo2, "getUserInfo(...)");
            return new EngineDO.WalletConnectUri(new Topic(userInfo2), SymmetricKey.m8778constructorimpl(str5), new RelayProtocolOptions(str3, str4), (String) null, 8, (DefaultConstructorMarker) null);
        } catch (URISyntaxException unused) {
        }
        return null;
    }
}
