package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.zip.Adler32;

@RequiresApi(api = 21)
public class JobInfoScheduler implements WorkScheduler {
    static final String ATTEMPT_NUMBER = "attemptNumber";
    static final String BACKEND_NAME = "backendName";
    static final String EVENT_PRIORITY = "priority";
    static final String EXTRAS = "extras";
    private static final String LOG_TAG = "JobInfoScheduler";
    private final SchedulerConfig config;
    private final Context context;
    private final EventStore eventStore;

    public JobInfoScheduler(Context context2, EventStore eventStore2, SchedulerConfig schedulerConfig) {
        this.context = context2;
        this.eventStore = eventStore2;
        this.config = schedulerConfig;
    }

    private boolean isJobServiceOn(JobScheduler jobScheduler, int i3, int i4) {
        for (JobInfo next : jobScheduler.getAllPendingJobs()) {
            int i5 = next.getExtras().getInt(ATTEMPT_NUMBER);
            if (next.getId() == i3) {
                return i5 >= i4;
            }
        }
        return false;
    }

    @VisibleForTesting
    public int getJobId(TransportContext transportContext) {
        Adler32 adler32 = new Adler32();
        adler32.update(this.context.getPackageName().getBytes(Charset.forName("UTF-8")));
        adler32.update(transportContext.getBackendName().getBytes(Charset.forName("UTF-8")));
        adler32.update(ByteBuffer.allocate(4).putInt(PriorityMapping.toInt(transportContext.getPriority())).array());
        if (transportContext.getExtras() != null) {
            adler32.update(transportContext.getExtras());
        }
        return (int) adler32.getValue();
    }

    public void schedule(TransportContext transportContext, int i3) {
        schedule(transportContext, i3, false);
    }

    public void schedule(TransportContext transportContext, int i3, boolean z2) {
        ComponentName componentName = new ComponentName(this.context, JobInfoSchedulerService.class);
        JobScheduler jobScheduler = (JobScheduler) this.context.getSystemService("jobscheduler");
        int jobId = getJobId(transportContext);
        if (z2 || !isJobServiceOn(jobScheduler, jobId, i3)) {
            long nextCallTime = this.eventStore.getNextCallTime(transportContext);
            JobInfo.Builder configureJob = this.config.configureJob(new JobInfo.Builder(jobId, componentName), transportContext.getPriority(), nextCallTime, i3);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putInt(ATTEMPT_NUMBER, i3);
            persistableBundle.putString(BACKEND_NAME, transportContext.getBackendName());
            persistableBundle.putInt(EVENT_PRIORITY, PriorityMapping.toInt(transportContext.getPriority()));
            if (transportContext.getExtras() != null) {
                persistableBundle.putString(EXTRAS, Base64.encodeToString(transportContext.getExtras(), 0));
            }
            configureJob.setExtras(persistableBundle);
            Logging.d(LOG_TAG, "Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", transportContext, Integer.valueOf(jobId), Long.valueOf(this.config.getScheduleDelay(transportContext.getPriority(), nextCallTime, i3)), Long.valueOf(nextCallTime), Integer.valueOf(i3));
            jobScheduler.schedule(configureJob.build());
            return;
        }
        Logging.d(LOG_TAG, "Upload for context %s is already scheduled. Returning...", (Object) transportContext);
    }
}
