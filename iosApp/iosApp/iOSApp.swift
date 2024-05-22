import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        initShared()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
    
    private func initShared() {
        let isDebug: Bool
                #if Debug
                isDebug = true
                #else
                isDebug = false
                #endif
        
        let config = Configuration(
            isDebug: isDebug,
            isLoggingEnabled: isDebug
        )
        
        PlatformSdk().doInit(conf: config)
    }
}
