# M1NX VIP Proxy

Android VPN/Proxy app với UI đẹp. Build tự động qua GitHub Actions.

## Build APK

Mỗi lần push vào `main`, GitHub Actions tự động:
1. Build debug APK
2. Ký với debug keystore  
3. Upload artifact `M1NX-VIP-APK`

**Tải APK:** Actions tab → workflow run mới nhất → `M1NX-VIP-APK`

## Stack
- Android API 24+ (Android 7.0+)
- WebView + Java bridge
- UI: HTML/CSS/JS in assets
