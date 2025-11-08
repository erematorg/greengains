package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.PlaybackStateCompat;
import com.adjust.sdk.Constants;
import org.apache.commons.lang3.time.DateUtils;

public final class zzoy implements zzov {
    private static final zzir<Long> zza;
    private static final zzir<Long> zzaa;
    private static final zzir<Long> zzab;
    private static final zzir<String> zzac;
    private static final zzir<Long> zzad;
    private static final zzir<String> zzae;
    private static final zzir<Long> zzaf;
    private static final zzir<String> zzag;
    private static final zzir<String> zzah;
    private static final zzir<String> zzai;
    private static final zzir<Long> zzaj;
    private static final zzir<Long> zzak;
    private static final zzir<Long> zzal;
    private static final zzir<Long> zzam;
    private static final zzir<Long> zzan;
    private static final zzir<Long> zzao;
    private static final zzir<Long> zzap;
    private static final zzir<Long> zzaq;
    private static final zzir<Long> zzar;
    private static final zzir<Long> zzas;
    private static final zzir<Long> zzat;
    private static final zzir<Long> zzau;
    private static final zzir<Long> zzav;
    private static final zzir<Long> zzaw;
    private static final zzir<Long> zzax;
    private static final zzir<Long> zzay;
    private static final zzir<Long> zzaz;
    private static final zzir<Long> zzb;
    private static final zzir<String> zzba;
    private static final zzir<Long> zzbb;
    private static final zzir<String> zzbc;
    private static final zzir<Boolean> zzc;
    private static final zzir<Long> zzd;
    private static final zzir<Long> zze;
    private static final zzir<String> zzf;
    private static final zzir<String> zzg;
    private static final zzir<Long> zzh;
    private static final zzir<Long> zzi;
    private static final zzir<String> zzj;
    private static final zzir<Long> zzk;
    private static final zzir<String> zzl;
    private static final zzir<Long> zzm;
    private static final zzir<Long> zzn;
    private static final zzir<Long> zzo;
    private static final zzir<Long> zzp;
    private static final zzir<Long> zzq;
    private static final zzir<Long> zzr;
    private static final zzir<Long> zzs;
    private static final zzir<Long> zzt;
    private static final zzir<Long> zzu;
    private static final zzir<Long> zzv;
    private static final zzir<Long> zzw;
    private static final zzir<Boolean> zzx;
    private static final zzir<String> zzy;
    private static final zzir<Long> zzz;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.ad_id_cache_time", 10000);
        zzb = zza2.zza("measurement.app_uninstalled_additional_ad_id_cache_time", (long) DateUtils.MILLIS_PER_HOUR);
        zzc = zza2.zza("measurement.config.bundle_for_all_apps_on_backgrounded", true);
        zzd = zza2.zza("measurement.max_bundles_per_iteration", 100);
        zze = zza2.zza("measurement.config.cache_time", 86400000);
        zza2.zza("measurement.log_tag", "FA");
        zza2.zza("measurement.id.config.experiment_id", 0);
        zzf = zza2.zza("measurement.config.url_authority", "app-measurement.com");
        zzg = zza2.zza("measurement.config.url_scheme", Constants.SCHEME);
        zzh = zza2.zza("measurement.upload.debug_upload_interval", 1000);
        zzi = zza2.zza("measurement.session.engagement_interval", (long) DateUtils.MILLIS_PER_HOUR);
        zzj = zza2.zza("measurement.rb.attribution.event_params", "value|currency");
        zza2.zza("measurement.id.rb.attribution.app_allowlist", 0);
        zzk = zza2.zza("measurement.upload.google_signal_max_queue_time", 605000);
        zzl = zza2.zza("measurement.sgtm.google_signal.url", "https://app-measurement.com/s");
        zzm = zza2.zza("measurement.lifetimevalue.max_currency_tracked", 4);
        zzn = zza2.zza("measurement.dma_consent.max_daily_dcu_realtime_events", 1);
        zzo = zza2.zza("measurement.upload.max_event_parameter_value_length", 100);
        zzp = zza2.zza("measurement.store.max_stored_events_per_app", 100000);
        zzq = zza2.zza("measurement.experiment.max_ids", 50);
        zzr = zza2.zza("measurement.audience.filter_result_max_count", 200);
        zzs = zza2.zza("measurement.upload.max_item_scoped_custom_parameters", 27);
        zzt = zza2.zza("measurement.rb.attribution.client.min_ad_services_version", 7);
        zzu = zza2.zza("measurement.alarm_manager.minimum_interval", 60000);
        zzv = zza2.zza("measurement.upload.minimum_delay", 500);
        zzw = zza2.zza("measurement.monitoring.sample_period_millis", 86400000);
        zzx = zza2.zza("measurement.config.notify_trigger_uris_on_backgrounded", true);
        zzy = zza2.zza("measurement.rb.attribution.app_allowlist", "com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.kleinanzeigen,de.wetteronline.wetterapp,com.game.shape.shift,com.champion.cubes,bubbleshooter.orig,com.wolt.android,com.master.hotelmaster,com.games.bus.arrival,com.playstrom.dop2,com.huuuge.casino.slots,com.ig.spider.fighting,com.jura.coloring.page,com.rikkogame.ragdoll2,com.ludo.king,com.sigma.prank.sound.haircut,com.crazy.block.robo.monster.cliffs.craft,com.fugo.wow,com.maps.locator.gps.gpstracker.phone,com.gamovation.tileclub,com.pronetis.ironball2,com.meesho.supply,pdf.pdfreader.viewer.editor.free,com.dino.race.master,com.ig.moto.racing,ai.photo.enhancer.photoclear,com.duolingo,com.candle.magic_piano,com.free.vpn.super.hotspot.open,sg.bigo.live,com.cdg.tictactoe,com.zhiliaoapp.musically.go,com.wildspike.wormszone,com.mast.status.video.edit,com.vyroai.photoeditorone,com.pujiagames.deeeersimulator,com.superbinogo.jungleboyadventure,com.trustedapp.pdfreaderpdfviewer,com.artimind.aiart.artgenerator.artavatar,de.cellular.ottohybrid,com.zeptolab.cats.google,in.crossy.daily_crossword");
        zzz = zza2.zza("measurement.upload.realtime_upload_interval", 10000);
        zzaa = zza2.zza("measurement.upload.refresh_blacklisted_config_interval", 604800000);
        zza2.zza("measurement.config.cache_time.service", (long) DateUtils.MILLIS_PER_HOUR);
        zzab = zza2.zza("measurement.service_client.idle_disconnect_millis", 5000);
        zza2.zza("measurement.log_tag.service", "FA-SVC");
        zzac = zza2.zza("measurement.sgtm.app_allowlist", "de.zalando.mobile.internal,de.zalando.mobile.internal.debug,de.zalando.lounge.dev,grit.storytel.app,com.rbc.mobile.android,com.rbc.mobile.android,com.dylvian.mango.activities,com.home24.android,com.home24.android.staging,se.lf.mobile.android,se.lf.mobile.android.beta,se.lf.mobile.android.rc,se.lf.mobile.android.test,se.lf.mobile.android.test.debug,com.boots.flagship.android,com.boots.flagshiproi.android,de.zalando.mobile,com.trivago,com.getyourguide.android,es.mobail.meliarewards,se.nansen.coop.debug,se.nansen.coop,se.coop.coop.qa,com.booking,com.google.firebaseengage,com.mse.mseapp.dev,com.mse.mseapp,pl.eobuwie.eobuwieapp,br.com.eventim.mobile.app.Android,ch.ticketcorner.mobile.app.Android,de.eventim.mobile.app.Android,dk.billetlugen.mobile.app.Android,nl.eventim.mobile.app.Android,com.asos.app,com.blueshieldca.prod,dk.magnetix.tivoliapp,matas.matas.internal,nl.omoda,com.thetrainline,com.simo.androidtest,de.aboutyou.mobile.app,com.hometogo,de.casamundo.casamundomobile,it.casevacanz,eu.coolblue.shop,com.stihl.app,com.indeed.android.jobsearch,com.homeretailgroup.argos.android,com.dylvian.mango.activities.pre,se.nansen.coop.qa");
        zzad = zza2.zza("measurement.upload.stale_data_deletion_interval", 86400000);
        zzae = zza2.zza("measurement.rb.attribution.uri_authority", "google-analytics.com");
        zzaf = zza2.zza("measurement.rb.attribution.max_queue_time", 1209600000);
        zzag = zza2.zza("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion");
        zzah = zza2.zza("measurement.rb.attribution.query_parameters_to_remove", "");
        zzai = zza2.zza("measurement.rb.attribution.uri_scheme", Constants.SCHEME);
        zzaj = zza2.zza("measurement.sdk.attribution.cache.ttl", 604800000);
        zzak = zza2.zza("measurement.redaction.app_instance_id.ttl", 7200000);
        zzal = zza2.zza("measurement.upload.backoff_period", 43200000);
        zzam = zza2.zza("measurement.upload.initial_upload_delay_time", 15000);
        zzan = zza2.zza("measurement.upload.interval", (long) DateUtils.MILLIS_PER_HOUR);
        zzao = zza2.zza("measurement.upload.max_bundle_size", (long) PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzap = zza2.zza("measurement.upload.max_bundles", 100);
        zzaq = zza2.zza("measurement.upload.max_conversions_per_day", 500);
        zzar = zza2.zza("measurement.upload.max_error_events_per_day", 1000);
        zzas = zza2.zza("measurement.upload.max_events_per_bundle", 1000);
        zzat = zza2.zza("measurement.upload.max_events_per_day", 100000);
        zzau = zza2.zza("measurement.upload.max_public_events_per_day", 50000);
        zzav = zza2.zza("measurement.upload.max_queue_time", 2419200000L);
        zzaw = zza2.zza("measurement.upload.max_realtime_events_per_day", 10);
        zzax = zza2.zza("measurement.upload.max_batch_size", (long) PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzay = zza2.zza("measurement.upload.retry_count", 6);
        zzaz = zza2.zza("measurement.upload.retry_time", (long) com.amplitude.api.Constants.SESSION_TIMEOUT_MILLIS);
        zzba = zza2.zza("measurement.upload.url", "https://app-measurement.com/a");
        zzbb = zza2.zza("measurement.upload.window_interval", (long) DateUtils.MILLIS_PER_HOUR);
        zzbc = zza2.zza("measurement.rb.attribution.user_properties", "_npa,npa");
    }

    public final long zza() {
        return zza.zza().longValue();
    }

    public final long zzaa() {
        return zzam.zza().longValue();
    }

    public final long zzab() {
        return zzan.zza().longValue();
    }

    public final long zzac() {
        return zzao.zza().longValue();
    }

    public final long zzad() {
        return zzap.zza().longValue();
    }

    public final long zzae() {
        return zzaq.zza().longValue();
    }

    public final long zzaf() {
        return zzar.zza().longValue();
    }

    public final long zzag() {
        return zzas.zza().longValue();
    }

    public final long zzah() {
        return zzat.zza().longValue();
    }

    public final long zzai() {
        return zzau.zza().longValue();
    }

    public final long zzaj() {
        return zzav.zza().longValue();
    }

    public final long zzak() {
        return zzaw.zza().longValue();
    }

    public final long zzal() {
        return zzax.zza().longValue();
    }

    public final long zzam() {
        return zzay.zza().longValue();
    }

    public final long zzan() {
        return zzaz.zza().longValue();
    }

    public final long zzao() {
        return zzbb.zza().longValue();
    }

    public final String zzap() {
        return zzf.zza();
    }

    public final String zzaq() {
        return zzg.zza();
    }

    public final String zzar() {
        return zzj.zza();
    }

    public final String zzas() {
        return zzl.zza();
    }

    public final String zzat() {
        return zzy.zza();
    }

    public final String zzau() {
        return zzac.zza();
    }

    public final String zzav() {
        return zzae.zza();
    }

    public final String zzaw() {
        return zzag.zza();
    }

    public final String zzax() {
        return zzah.zza();
    }

    public final String zzay() {
        return zzai.zza();
    }

    public final String zzaz() {
        return zzba.zza();
    }

    public final long zzb() {
        return zzb.zza().longValue();
    }

    public final String zzba() {
        return zzbc.zza();
    }

    public final boolean zzbb() {
        return zzc.zza().booleanValue();
    }

    public final boolean zzbc() {
        return zzx.zza().booleanValue();
    }

    public final long zzc() {
        return zzd.zza().longValue();
    }

    public final long zzd() {
        return zze.zza().longValue();
    }

    public final long zze() {
        return zzh.zza().longValue();
    }

    public final long zzf() {
        return zzi.zza().longValue();
    }

    public final long zzg() {
        return zzk.zza().longValue();
    }

    public final long zzh() {
        return zzm.zza().longValue();
    }

    public final long zzi() {
        return zzn.zza().longValue();
    }

    public final long zzj() {
        return zzo.zza().longValue();
    }

    public final long zzk() {
        return zzp.zza().longValue();
    }

    public final long zzl() {
        return zzq.zza().longValue();
    }

    public final long zzm() {
        return zzr.zza().longValue();
    }

    public final long zzn() {
        return zzs.zza().longValue();
    }

    public final long zzo() {
        return zzt.zza().longValue();
    }

    public final long zzp() {
        return zzu.zza().longValue();
    }

    public final long zzq() {
        return zzv.zza().longValue();
    }

    public final long zzr() {
        return zzw.zza().longValue();
    }

    public final long zzs() {
        return zzz.zza().longValue();
    }

    public final long zzt() {
        return zzaa.zza().longValue();
    }

    public final long zzu() {
        return zzab.zza().longValue();
    }

    public final long zzv() {
        return zzad.zza().longValue();
    }

    public final long zzw() {
        return zzaf.zza().longValue();
    }

    public final long zzx() {
        return zzaj.zza().longValue();
    }

    public final long zzy() {
        return zzak.zza().longValue();
    }

    public final long zzz() {
        return zzal.zza().longValue();
    }
}
