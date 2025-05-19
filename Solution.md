# Solution

This project has been migrated from a KMP with separate iOs and Android UIs to  **Compose Multiplatform**  on top of Kotlin Multiplatform (KMP), enabling a single, shared UI implementation that runs on both Android and iOS. All business logic, networking and resource loading lives in the shared `shared` module.


## Key Highlights

1. **Single Shared UI with Compose Multiplatform**
    - All screens and UI components are implemented in the `shared` module using Jetpack Compose.
    - No platform-specific XML or SwiftUI code for the main UI—one codebase for both Android and iOS.

2. **Shared Logic, Networking & Resource Loading**
    - Networking is abstracted via a pluggable `NetworkApi` interface; by default a dummy web datasource can be swapped out seamlessly.

3. **Extensible MVI Architecture**
    - Model–View–Intent pattern: `Intent` classes capture user actions, `State` objects represent UI state, `Event` represents one-time events that would trigger specific actions (like navigation) and `ViewModel` processes intents, updates its state and emits `Event`
    - Base MVI components are generic and easily applied to any new feature. (Note that an optional Domain layer can be easily added too, if we want to follow Clean Architecture on complex projects)

4. **Navigation with Compose Navigation & Koin**
    - Navigation system built on `androidx.navigation:navigation-compose` (compatible with Compose MPP).
    - Each screen’s `ViewModel` is injected and scoped to its destination using Koin, including route arguments. (Check the `registerDestination` extension as an example)
   ```
   fun NavGraphScope.sakesNavigation() {
    registerDestination<SakesListViewModel>(SakesRoute.SakesList::class) { sakesViewModel ->
        SakeListScreen(
            state = sakesViewModel.state,
            events = sakesViewModel.events,
            onIntent = sakesViewModel::onIntent
        )
    }
   ```
    - No global singletons: view models are created per-screen and per-argument instance.

5. **Pluggable DataSources**
    - Repository interfaces (e.g. `SakesRepository`) define the data layer.
    - Default implementation reads from a local JSON file; a real web service client can be substituted without changes to business logic.

6. **Multiplatform Support for External Services**
    - **Web links**: abstracted opening URLs via a shared API that delegates to `CustomTabs` (Android) or `SFSafariViewController` (iOS).
    - **Maps**: embedded platform maps (`GoogleMap` on Android, `MKMapView` on iOS) through a common `PlatformMapView` composable.

8. **Testability**
   - All the provided classes (networking layer, repositories, ViewModels) are easily testable thanks to the DI framework and the MVI implementation

9. **No extra KMP Shared VM libraries:**

 Multiple libraries exist to offer SharedViewModel on KMP. I purposedly choosed to not use them and just use a regular class instead. The scoping of the VM to the specific compose destination is handled by this framework leveraging Koin scopes, which would yield the same instances for the same destinations if the arguments doesn't change with the app on the foreground, for simplicity I didn't dig too much on this, but the logic itself can be improved. Also avoided global VMs on purpose.

For state persistance accross config changes (Android) and process kills (both), we can use expect/actual SavedStateHandler objects on Android and iOs, for example, to preserve user inputs in the UI. For simplicity, didn't add that to this excercise, but can be done relatively easily with KMP data storage + @Serializable states.



## Additional notes

**Add Your Google Maps API Key**  
   Define `GOOGLE_MAP_KEY` in one of the following:
    - `local.properties`
    - `gradle.properties`
    - Pass as a Gradle property on the command line

   `GOOGLE_MAP_KEY=YOUR_ACTUAL_KEY_HERE`

   Or, build the project from the command line and pass `PGOOGLE_MAP_KEY={api_key}`

   If none of these works in the running environment, please kindly replace the value in the maniffest itself.

   Due to hardware limitations, I'm unable to test it an provide an iOs screen recording, but should work as expected. Please feel free to report any issues you might find.

## Future considerations
   
   Having a MainViewModel scoped to the main UI to process external Intents (like Push Notifications or BroadcastReceiver (Android) ) and navigate to the appropriate screen to take care of them.
   
   Navigating to native Android or iOs views should also be possible with a similar mechanism.
   
