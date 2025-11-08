package com.adjust.sdk;

import java.util.HashMap;
import java.util.Map;

public class AdjustThirdPartySharing {
    Map<String, Map<String, String>> granularOptions = new HashMap();
    Boolean isEnabled;
    Map<String, Map<String, Boolean>> partnerSharingSettings = new HashMap();

    public AdjustThirdPartySharing(Boolean bool) {
        this.isEnabled = bool;
    }

    public void addGranularOption(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            AdjustFactory.getLogger().error("Cannot add granular option with any null value", new Object[0]);
            return;
        }
        Map map = this.granularOptions.get(str);
        if (map == null) {
            map = new HashMap();
            this.granularOptions.put(str, map);
        }
        map.put(str2, str3);
    }

    public void addPartnerSharingSetting(String str, String str2, boolean z2) {
        if (str == null || str2 == null) {
            AdjustFactory.getLogger().error("Cannot add partner sharing setting with any null value", new Object[0]);
            return;
        }
        Map map = this.partnerSharingSettings.get(str);
        if (map == null) {
            map = new HashMap();
            this.partnerSharingSettings.put(str, map);
        }
        map.put(str2, Boolean.valueOf(z2));
    }

    public Boolean getEnabled() {
        return this.isEnabled;
    }

    public Map<String, Map<String, String>> getGranularOptions() {
        return this.granularOptions;
    }

    public Map<String, Map<String, Boolean>> getPartnerSharingSettings() {
        return this.partnerSharingSettings;
    }
}
