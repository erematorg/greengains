package com.appsamurai.storyly.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONObject;

public final class i extends Lambda implements Function1<Integer, JSONObject> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ JSONArray f6340a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(JSONArray jSONArray) {
        super(1);
        this.f6340a = jSONArray;
    }

    public Object invoke(Object obj) {
        Object obj2 = this.f6340a.get(((Number) obj).intValue());
        if (obj2 instanceof JSONObject) {
            return (JSONObject) obj2;
        }
        return null;
    }
}
