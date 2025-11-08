package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.amplitude.api.Constants;
import com.google.android.gms.internal.measurement.zzic;
import com.google.android.gms.internal.measurement.zzio;
import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.DateUtils;

public final class zzbj {
    public static final zzfz<Long> zza = zzb("measurement.ad_id_cache_time", 10000L, new zzbl());
    public static final zzfz<Long> zzaa = zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000L, new zzdc());
    public static final zzfz<Long> zzab = zzb("measurement.upload.initial_upload_delay_time", 15000L, new zzdf());
    public static final zzfz<Long> zzac = zzb("measurement.upload.retry_time", Long.valueOf(Constants.SESSION_TIMEOUT_MILLIS), new zzde());
    public static final zzfz<Integer> zzad = zzb("measurement.upload.retry_count", 6, new zzdh());
    public static final zzfz<Long> zzae = zzb("measurement.upload.max_queue_time", 2419200000L, new zzdi());
    public static final zzfz<Long> zzaf = zzb("measurement.upload.google_sginal_max_queue_time", 300000L, new zzdl());
    public static final zzfz<Integer> zzag = zzb("measurement.lifetimevalue.max_currency_tracked", 4, new zzdk());
    public static final zzfz<Integer> zzah = zzb("measurement.audience.filter_result_max_count", 200, new zzdn());
    public static final zzfz<Integer> zzai = zza("measurement.upload.max_public_user_properties", 25);
    public static final zzfz<Integer> zzaj = zza("measurement.upload.max_event_name_cardinality", 500);
    public static final zzfz<Integer> zzak = zza("measurement.upload.max_public_event_params", 25);
    public static final zzfz<Long> zzal = zzb("measurement.service_client.idle_disconnect_millis", 5000L, new zzdm());
    public static final zzfz<Boolean> zzam;
    public static final zzfz<String> zzan = zzb("measurement.test.string_flag", "---", new zzdo());
    public static final zzfz<Long> zzao = zzb("measurement.test.long_flag", -1L, new zzdr());
    public static final zzfz<Integer> zzap = zzb("measurement.test.int_flag", -2, new zzdt());
    public static final zzfz<Double> zzaq = zzb("measurement.test.double_flag", Double.valueOf(-3.0d), new zzdu());
    public static final zzfz<Integer> zzar = zzb("measurement.experiment.max_ids", 50, new zzdx());
    public static final zzfz<Integer> zzas = zzb("measurement.upload.max_item_scoped_custom_parameters", 27, new zzdw());
    public static final zzfz<Integer> zzat = zza("measurement.upload.max_event_parameter_value_length", 100, new zzdz());
    public static final zzfz<Integer> zzau = zzb("measurement.max_bundles_per_iteration", 100, new zzdy());
    public static final zzfz<Long> zzav = zzb("measurement.sdk.attribution.cache.ttl", 604800000L, new zzeb());
    public static final zzfz<Long> zzaw = zzb("measurement.redaction.app_instance_id.ttl", 7200000L, new zzea());
    public static final zzfz<Integer> zzax = zzb("measurement.rb.attribution.client.min_ad_services_version", 7, new zzed());
    public static final zzfz<Integer> zzay = zzb("measurement.dma_consent.max_daily_dcu_realtime_events", 1, new zzec());
    public static final zzfz<String> zzaz = zzb("measurement.rb.attribution.uri_scheme", com.adjust.sdk.Constants.SCHEME, new zzee());
    public static final zzfz<Long> zzb;
    public static final zzfz<String> zzba = zzb("measurement.rb.attribution.uri_authority", "google-analytics.com", new zzeg());
    public static final zzfz<String> zzbb = zzb("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion", new zzej());
    public static final zzfz<Long> zzbc;
    public static final zzfz<String> zzbd = zzb("measurement.rb.attribution.app_allowlist", "com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.kleinanzeigen,de.wetteronline.wetterapp,com.game.shape.shift,com.champion.cubes,bubbleshooter.orig,com.wolt.android,com.master.hotelmaster,com.games.bus.arrival,com.playstrom.dop2,com.huuuge.casino.slots,com.ig.spider.fighting,com.jura.coloring.page,com.rikkogame.ragdoll2,com.ludo.king,com.sigma.prank.sound.haircut,com.crazy.block.robo.monster.cliffs.craft,com.fugo.wow,com.maps.locator.gps.gpstracker.phone,com.gamovation.tileclub,com.pronetis.ironball2,com.meesho.supply,pdf.pdfreader.viewer.editor.free,com.dino.race.master,com.ig.moto.racing,ai.photo.enhancer.photoclear,com.duolingo,com.candle.magic_piano,com.free.vpn.super.hotspot.open,sg.bigo.live,com.cdg.tictactoe,com.zhiliaoapp.musically.go,com.wildspike.wormszone,com.mast.status.video.edit,com.vyroai.photoeditorone,com.pujiagames.deeeersimulator,com.superbinogo.jungleboyadventure,com.trustedapp.pdfreaderpdfviewer,com.artimind.aiart.artgenerator.artavatar,de.cellular.ottohybrid,com.zeptolab.cats.google,in.crossy.daily_crossword", new zzel());
    public static final zzfz<String> zzbe = zzb("measurement.rb.attribution.user_properties", "_npa,npa", new zzek());
    public static final zzfz<String> zzbf = zzb("measurement.rb.attribution.event_params", "value|currency", new zzen());
    public static final zzfz<String> zzbg = zzb("measurement.rb.attribution.query_parameters_to_remove", "", new zzem());
    public static final zzfz<Long> zzbh = zzb("measurement.rb.attribution.max_queue_time", 1209600000L, new zzep());
    public static final zzfz<Boolean> zzbi;
    public static final zzfz<Boolean> zzbj;
    public static final zzfz<Boolean> zzbk;
    public static final zzfz<Boolean> zzbl;
    public static final zzfz<Boolean> zzbm;
    public static final zzfz<Boolean> zzbn;
    public static final zzfz<Boolean> zzbo;
    public static final zzfz<Boolean> zzbp;
    public static final zzfz<Boolean> zzbq;
    public static final zzfz<Boolean> zzbr;
    public static final zzfz<Integer> zzbs = zzb("measurement.service.storage_consent_support_version", 203600, new zzfa());
    public static final zzfz<Boolean> zzbt;
    public static final zzfz<Boolean> zzbu;
    public static final zzfz<Boolean> zzbv;
    public static final zzfz<Boolean> zzbw;
    public static final zzfz<Boolean> zzbx;
    public static final zzfz<String> zzby = zza("measurement.sgtm.app_allowlist", "de.zalando.mobile.internal,de.zalando.mobile.internal.debug,de.zalando.lounge.dev,grit.storytel.app,com.rbc.mobile.android,com.rbc.mobile.android,com.dylvian.mango.activities,com.home24.android,com.home24.android.staging,se.lf.mobile.android,se.lf.mobile.android.beta,se.lf.mobile.android.rc,se.lf.mobile.android.test,se.lf.mobile.android.test.debug,com.boots.flagship.android,com.boots.flagshiproi.android,de.zalando.mobile,com.trivago,com.getyourguide.android,es.mobail.meliarewards,se.nansen.coop.debug,se.nansen.coop,se.coop.coop.qa,com.booking,com.google.firebaseengage,com.mse.mseapp.dev,com.mse.mseapp,pl.eobuwie.eobuwieapp,br.com.eventim.mobile.app.Android,ch.ticketcorner.mobile.app.Android,de.eventim.mobile.app.Android,dk.billetlugen.mobile.app.Android,nl.eventim.mobile.app.Android,com.asos.app,com.blueshieldca.prod,dk.magnetix.tivoliapp,matas.matas.internal,nl.omoda,com.thetrainline,com.simo.androidtest,de.aboutyou.mobile.app,com.hometogo,de.casamundo.casamundomobile,it.casevacanz,eu.coolblue.shop,com.stihl.app,com.indeed.android.jobsearch,com.homeretailgroup.argos.android,com.dylvian.mango.activities.pre,se.nansen.coop.qa", new zzfj());
    public static final zzfz<Boolean> zzbz;
    public static final zzfz<Long> zzc = zzb("measurement.monitoring.sample_period_millis", 86400000L, new zzdg());
    public static final zzfz<Boolean> zzca;
    public static final zzfz<Boolean> zzcb;
    public static final zzfz<Boolean> zzcc;
    public static final zzfz<Boolean> zzcd;
    public static final zzfz<Boolean> zzce;
    public static final zzfz<Boolean> zzcf;
    public static final zzfz<Boolean> zzcg;
    public static final zzfz<Boolean> zzch;
    public static final zzfz<Boolean> zzci;
    public static final zzfz<Boolean> zzcj;
    public static final zzfz<Boolean> zzck;
    public static final zzfz<Boolean> zzcl;
    public static final zzfz<Boolean> zzcm;
    public static final zzfz<Boolean> zzcn;
    public static final zzfz<Boolean> zzco;
    public static final zzfz<Boolean> zzcp;
    public static final zzfz<Boolean> zzcq;
    public static final zzfz<Boolean> zzcr;
    public static final zzfz<Boolean> zzcs;
    public static final zzfz<Boolean> zzct;
    public static final zzfz<Boolean> zzcu;
    public static final zzfz<Boolean> zzcv;
    public static final zzfz<Boolean> zzcw;
    public static final zzfz<Boolean> zzcx;
    public static final zzfz<Boolean> zzcy;
    public static final zzfz<Boolean> zzcz;
    public static final zzfz<Long> zzd;
    public static final zzfz<Boolean> zzda;
    public static final zzfz<Boolean> zzdb;
    public static final zzfz<Boolean> zzdc;
    public static final zzfz<Boolean> zzdd;
    public static final zzfz<Boolean> zzde;
    public static final zzfz<Boolean> zzdf;
    public static final zzfz<Boolean> zzdg;
    public static final zzfz<Boolean> zzdh;
    public static final zzfz<Boolean> zzdi;
    public static final zzfz<Boolean> zzdj;
    public static final zzfz<Boolean> zzdk;
    public static final zzfz<Boolean> zzdl;
    public static final zzfz<Boolean> zzdm;
    public static final zzfz<Boolean> zzdn;
    /* access modifiers changed from: private */
    public static final List<zzfz<?>> zzdo = Collections.synchronizedList(new ArrayList());
    public static final zzfz<String> zze = zzb("measurement.config.url_scheme", com.adjust.sdk.Constants.SCHEME, new zzeh());
    public static final zzfz<String> zzf = zzb("measurement.config.url_authority", "app-measurement.com", new zzet());
    public static final zzfz<Integer> zzg = zzb("measurement.upload.max_bundles", 100, new zzff());
    public static final zzfz<Integer> zzh = zzb("measurement.upload.max_batch_size", 65536, new zzfr());
    public static final zzfz<Integer> zzi = zzb("measurement.upload.max_bundle_size", 65536, new zzbq());
    public static final zzfz<Integer> zzj = zzb("measurement.upload.max_events_per_bundle", 1000, new zzcc());
    public static final zzfz<Integer> zzk = zzb("measurement.upload.max_events_per_day", 100000, new zzcf());
    public static final zzfz<Integer> zzl = zzb("measurement.upload.max_error_events_per_day", 1000, new zzck());
    public static final zzfz<Integer> zzm = zzb("measurement.upload.max_public_events_per_day", 50000, new zzcm());
    public static final zzfz<Integer> zzn = zzb("measurement.upload.max_conversions_per_day", 10000, new zzcp());
    public static final zzfz<Integer> zzo = zzb("measurement.upload.max_realtime_events_per_day", 10, new zzco());
    public static final zzfz<Integer> zzp = zzb("measurement.store.max_stored_events_per_app", 100000, new zzcr());
    public static final zzfz<String> zzq = zzb("measurement.upload.url", "https://app-measurement.com/a", new zzcq());
    public static final zzfz<String> zzr = zzb("measurement.sgtm.google_signal.url", "https://app-measurement.com/s", new zzct());
    public static final zzfz<Long> zzs = zzb("measurement.upload.backoff_period", 43200000L, new zzcs());
    public static final zzfz<Long> zzt;
    public static final zzfz<Long> zzu;
    public static final zzfz<Long> zzv = zzb("measurement.upload.realtime_upload_interval", 10000L, new zzcz());
    public static final zzfz<Long> zzw = zzb("measurement.upload.debug_upload_interval", 1000L, new zzcy());
    public static final zzfz<Long> zzx = zzb("measurement.upload.minimum_delay", 500L, new zzdb());
    public static final zzfz<Long> zzy = zzb("measurement.alarm_manager.minimum_interval", 60000L, new zzda());
    public static final zzfz<Long> zzz = zzb("measurement.upload.stale_data_deletion_interval", 86400000L, new zzdd());

    static {
        Collections.synchronizedSet(new HashSet());
        Long valueOf = Long.valueOf(DateUtils.MILLIS_PER_HOUR);
        zzb = zzb("measurement.app_uninstalled_additional_ad_id_cache_time", valueOf, new zzcu());
        zzd = zza("measurement.config.cache_time", 86400000L, valueOf, new zzdv(), false);
        zzt = zzb("measurement.upload.window_interval", valueOf, new zzcv());
        zzu = zzb("measurement.upload.interval", valueOf, new zzcx());
        Boolean bool = Boolean.FALSE;
        zzam = zzb("measurement.test.boolean_flag", bool, new zzdp());
        zza("measurement.test.cached_long_flag", -1L, new zzdq());
        zzbc = zzb("measurement.session.engagement_interval", valueOf, new zzei());
        Boolean bool2 = Boolean.TRUE;
        zzbi = zzb("measurement.config.bundle_for_all_apps_on_backgrounded", bool2, new zzer());
        zzbj = zzb("measurement.config.notify_trigger_uris_on_backgrounded", bool2, new zzeq());
        zzbk = zzb("measurement.collection.log_event_and_bundle_v2", bool2, new zzes());
        zzbl = zza("measurement.quality.checksum", bool);
        zzbm = zzb("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", bool, new zzev());
        zzbn = zzb("measurement.audience.refresh_event_count_filters_timestamp", bool, new zzeu());
        zzbo = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", bool, new zzex());
        zzbp = zzb("measurement.sdk.collection.last_deep_link_referrer_campaign2", bool, new zzew());
        zzbq = zzb("measurement.integration.disable_firebase_instance_id", bool, new zzez());
        zzbr = zzb("measurement.collection.service.update_with_analytics_fix", bool, new zzey());
        zzbt = zzb("measurement.service.store_null_safelist", bool2, new zzfd());
        zzbu = zzb("measurement.service.store_safelist", bool2, new zzfc());
        zzbv = zzb("measurement.session_stitching_token_enabled", bool, new zzfe());
        zzbw = zza("measurement.sgtm.service", bool2, new zzfh());
        zzbx = zza("measurement.sgtm.preview_mode_enabled", bool2, new zzfg());
        zzbz = zzb("measurement.sgtm.upload_queue", bool, new zzfi());
        zzca = zzb("measurement.sgtm.google_signal.enable", bool, new zzfl());
        zzcb = zzb("measurement.gmscore_feature_tracking", bool2, new zzfn());
        zzcc = zzb("measurement.gmscore_network_migration", bool, new zzfm());
        zzcd = zza("measurement.fix_health_monitor_stack_trace", bool2, new zzfp());
        zzce = zza("measurement.item_scoped_custom_parameters.client", bool2, new zzfo());
        zzcf = zzb("measurement.item_scoped_custom_parameters.service", bool2, new zzfq());
        zzcg = zza("measurement.rb.attribution.service", bool2, new zzft());
        zzch = zza("measurement.rb.attribution.client2", bool2, new zzfs());
        zzci = zzb("measurement.rb.attribution.uuid_generation", bool2, new zzfv());
        zzcj = zzb("measurement.rb.attribution.enable_trigger_redaction", bool2, new zzfu());
        zzb("measurement.rb.attribution.followup1.service", bool, new zzbn());
        zzck = zzb("measurement.rb.attribution.retry_disposition", bool, new zzbm());
        zzcl = zzb("measurement.rb.attribution.ad_campaign_info", bool, new zzbp());
        zzcm = zza("measurement.rb.attribution.improved_retry", bool2, new zzbo());
        zzcn = zzb("measurement.client.sessions.enable_fix_background_engagement", bool, new zzbr());
        zzco = zzb("measurement.client.sessions.enable_pause_engagement_in_background", bool2, new zzbt());
        zzcp = zzb("measurement.dma_consent.service_dcu_event2", bool2, new zzbs());
        zzcq = zzb("measurement.dma_consent.services_database_update_fix", bool2, new zzbv());
        zzcr = zza("measurement.service.deferred_first_open", bool2, new zzbu());
        zzcs = zza("measurement.gbraid_campaign.gbraid.client", bool, new zzbw());
        zzct = zza("measurement.gbraid_campaign.gbraid.service", bool, new zzbz());
        zzcu = zza("measurement.increase_param_lengths", bool2, new zzby());
        zzcv = zzb("measurement.disable_npa_for_dasher_and_unicorn", bool2, new zzcb());
        zzcw = zza("measurement.consent_regional_defaults.service", bool2, new zzca());
        zzcx = zza("measurement.consent_regional_defaults.client2", bool2, new zzcd());
        zzcy = zzb("measurement.service.consent.pfo_on_fx", bool2, new zzcn());
        zzcz = zzb("measurement.service.consent.params_on_fx", bool2, new zzcw());
        zzda = zzb("measurement.service.consent.app_start_fix", bool2, new zzdj());
        zzdb = zza("measurement.consent.stop_reset_on_storage_denied.client", bool2, new zzds());
        zzdc = zza("measurement.consent.stop_reset_on_storage_denied.service", bool2, new zzef());
        zzdd = zzb("measurement.consent.scrub_audience_data_analytics_consent", bool2, new zzeo());
        zzde = zzb("measurement.consent.fix_first_open_count_from_snapshot", bool2, new zzfb());
        zzdf = zzb("measurement.fix_engagement_on_reset_analytics_data", bool2, new zzfk());
        zzdg = zzb("measurement.rb.attribution.service.bundle_on_backgrounded", bool, new zzbk());
        zzdh = zzb("measurement.rb.attribution.client.bundle_on_backgrounded", bool, new zzbx());
        zzdi = zzb("measurement.set_default_event_parameters_propagate_clear.service.dev", bool, new zzce());
        zzdj = zzb("measurement.set_default_event_parameters_propagate_clear.client.dev", bool, new zzch());
        zzdk = zzb("measurement.set_default_event_parameters_with_backfill.service", bool, new zzcg());
        zzdl = zzb("measurement.set_default_event_parameters_with_backfill.client.dev", bool, new zzcj());
        zzdm = zzb("measurement.fix_origin_in_upload_utils.service", bool2, new zzci());
        zzdn = zzb("measurement.service.ad_impression.convert_value_to_double", bool2, new zzcl());
    }

    @VisibleForTesting
    private static <V> zzfz<V> zza(String str, V v2, zzfx<V> zzfx) {
        return zza(str, v2, v2, zzfx, true);
    }

    @VisibleForTesting
    private static <V> zzfz<V> zzb(String str, V v2, zzfx<V> zzfx) {
        return zza(str, v2, v2, zzfx, false);
    }

    @VisibleForTesting
    private static <V> zzfz<V> zza(String str, V v2, V v3, zzfx<V> zzfx, boolean z2) {
        zzfz zzfz = new zzfz(str, v2, v3, zzfx, z2);
        if (z2) {
            zzdo.add(zzfz);
        }
        return zzfz;
    }

    @VisibleForTesting
    private static <V> zzfz<V> zza(String str, V v2) {
        return zza(str, v2, v2, (zzfx) null, false);
    }

    public static Map<String, String> zza(Context context) {
        zzic zza2 = zzic.zza(context.getContentResolver(), zzio.zza("com.google.android.gms.measurement"), new zzbi());
        return zza2 == null ? Collections.emptyMap() : zza2.zza();
    }
}
