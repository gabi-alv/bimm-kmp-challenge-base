import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        DependencyGraphKt.buildDependencies()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}