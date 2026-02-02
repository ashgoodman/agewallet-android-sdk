# AgeWallet Android SDK Demo

A minimal demo app showing the AgeWallet Android SDK in action.

## Setup

1. Open in Android Studio:
   ```bash
   # From the example directory
   studio .
   ```
   Or open Android Studio and select "Open" > navigate to this `example` folder.

2. Configure your client ID in `MainActivity.kt`:
   ```kotlin
   ageWallet = AgeWallet(
       context = this,
       config = AgeWalletConfig(
           clientId = "your-actual-client-id",  // Replace this
           redirectUri = "https://agewallet-sdk-demo.netlify.app/callback"
       )
   )
   ```

3. Build and run on a device or emulator (API 24+).

## Deep Link Testing

The app is configured to handle deep links from:
```
https://agewallet-sdk-demo.netlify.app/callback
```

To test the full flow:
1. Run the app
2. Tap "Verify with AgeWallet"
3. Complete verification in the browser
4. App should receive callback and show verified state

## Manual Deep Link Testing

```bash
adb shell am start -W -a android.intent.action.VIEW \
  -d "https://agewallet-sdk-demo.netlify.app/callback?code=test&state=test" \
  io.agewallet.sdk.demo.android
```

## Requirements

- Android Studio Hedgehog or newer
- Android SDK 34
- Kotlin 2.0+
- Device/emulator running Android 7.0+ (API 24)
