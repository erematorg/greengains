# Android 16 - Foreground Services Sample

This sample app demonstrates how to use the foreground service on Android 16.
It is meant as a companion to the **[Guide to Foreground Services on Android 14](https://medium.com/@domen.lanisnik/guide-to-foreground-services-on-android-9d0127dc8f9a)** blog post
and **[Guide to Foreground Services on Android](https://appdevcon.nl/session/guide-to-foreground-services-on-android/)** talk at AppDevCon 2025 and droidcon Berlin 2024.

It shows two approaches on how to create and start a foreground service:
1. The standard way of creating a standard Service and promoting it to a foreground service.
2. The new way of using a long-running worker using WorkManager that runs as a foreground service under the hood.


Included are the following functionalities:
 - Starting a foreground service with a declared foreground service type of location
 - Requesting location permissions before service is started
 - Binding to the foreground service from Activity to display the service status and receive location updates
 - Stopping the service from the activity
 - Requesting notification permission to show foreground service notification
 - Using WorkManager to run a long-running foreground service task

**Previews**

<p float="left">
  <img src="/previews/preview.png" width="19%" />
  <img src="/previews/preview0.png" width="19%" /> 
  <img src="/previews/preview1.png" width="19%" />
  <img src="/previews/preview2.png" width="19%" />
</p>
<p float="left">
  <img src="/previews/preview3.png" width="19%" />
  <img src="/previews/preview4.png" width="19%" /> 
  <img src="/previews/preview5.png" width="19%" />
</p>

**References**
- https://medium.com/@domen.lanisnik/guide-to-foreground-services-on-android-9d0127dc8f9a
- https://appdevcon.nl/session/guide-to-foreground-services-on-android/
- https://developer.android.com/develop/background-work/services/foreground-services
