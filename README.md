# FindHome 🏠

FindHome is an Android application developed as a Master's degree project to simplify finding, exploring, and listing rental properties.

## Features

- **Property Listings & Search**: Browse available properties with detailed descriptions, pricing, and amenities.
- **Interactive Maps**: Explore property locations and surrounding areas powered by Google Maps and Google Places API.
- **Photo Upload & Galleries**: Upload property photos with Cloudinary integration and view high-resolution image galleries with custom animations.
- **User Authentication**: Secure authentication supporting Google Sign-In and Firebase Authentication.
- **Real-Time Data**: Firebase Realtime Database integration for real-time property listings and updates.
- **Housing Data Aggregation**: Integrated web parser using jsoup for retrieving rental listings.

## Tech Stack & Architecture

- **Platform**: Android (minSdkVersion 21, targetSdkVersion 30)
- **Language**: Java 8+
- **Backend & Cloud**:
  - Google Firebase (Authentication, Realtime Database, Cloud Storage, Analytics)
  - Cloudinary (Media upload & management)
  - Google Play Services (Maps, Location, Places, Auth)
- **UI Components & Libraries**:
  - AndroidX & Material Design Components
  - Picasso (Image loading & caching)
  - RecyclerView Animators
  - AnimatedEditText & MaterialShowcaseView

## Building & Running

### Prerequisites
- Android Studio (or Android SDK with build-tools 30.0.3+)
- JDK 11 (e.g. Eclipse Temurin or OpenJDK 11)

### Build Commands
```bash
# Clone the repository
git clone https://github.com/fl0wsam/FindHome.git
cd FindHome

# Build Debug APK
./gradlew assembleDebug

# Run Unit Tests
./gradlew test
```
The generated APK will be located at `app/build/outputs/apk/debug/app-debug.apk`.
