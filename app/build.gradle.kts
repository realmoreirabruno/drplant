plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.doctorplant"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.doctorplant"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.9.5")

    // Lifecycle e ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")

    //Retrofit + Gson
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.2.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.2.1")

    // Room
    implementation ("androidx.room:room-runtime:2.8.2")
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.lifecycle)
    ksp ("androidx.room:room-compiler:2.8.2")
    implementation ("androidx.room:room-ktx:2.8.2")

    // Coil (para carregar imagens)
    implementation ("io.coil-kt:coil-compose:2.7.0")

    // Koin for Android
    implementation(platform("io.insert-koin:koin-bom:4.1.1"))
    implementation ("io.insert-koin:koin-core")
    implementation ("io.insert-koin:koin-android")
    implementation ("io.insert-koin:koin-androidx-compose")

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}