# AgeWallet Android SDK

Native Android SDK for AgeWallet age verification via OIDC/PKCE.

## Installation

Add the JitPack repository to your root `build.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.ashgoodman:agewallet-android-sdk:1.0.0")
}
```

## Usage

### 1. Initialize the SDK

```kotlin
import io.agewallet.sdk.AgeWallet
import io.agewallet.sdk.AgeWalletConfig

class MainActivity : ComponentActivity() {
    private lateinit var ageWallet: AgeWallet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ageWallet = AgeWallet(
            context = this,
            config = AgeWalletConfig(
                clientId = "your-client-id",
                redirectUri = "https://agewallet-sdk-demo.netlify.app/callback"
            )
        )
    }
}
```

### 2. Check Verification Status

```kotlin
if (ageWallet.isVerified()) {
    // User is verified
} else {
    // Show age gate
}
```

### 3. Start Verification

```kotlin
ageWallet.startVerification(this)
```

### 4. Handle Deep Link Callback

Configure your `AndroidManifest.xml`:

```xml
<activity android:name=".MainActivity"
    android:exported="true"
    android:launchMode="singleTask">
    <intent-filter android:autoVerify="true">
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data
            android:scheme="https"
            android:host="agewallet-sdk-demo.netlify.app"
            android:pathPrefix="/callback" />
    </intent-filter>
</activity>
```

Handle the callback in your activity:

```kotlin
override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    lifecycleScope.launch {
        val success = ageWallet.handleCallback(intent)
        if (success) {
            // Verification successful
        }
    }
}
```

### 5. Clear Verification (Logout)

```kotlin
ageWallet.clearVerification()
```

## Custom Endpoints

Override default AgeWallet endpoints if needed:

```kotlin
val config = AgeWalletConfig(
    clientId = "your-client-id",
    redirectUri = "https://yourapp.com/callback",
    endpoints = AgeWalletEndpoints(
        auth = "https://custom.agewallet.io/user/authorize",
        token = "https://custom.agewallet.io/user/token",
        userinfo = "https://custom.agewallet.io/user/userinfo"
    )
)
```

## Requirements

- Android API 24+
- AndroidX
- Kotlin 1.9+

## Security

- Uses Chrome Custom Tabs for secure OAuth flow
- PKCE (S256) for public client security
- Tokens stored in EncryptedSharedPreferences (Android Keystore)

## License

MIT
