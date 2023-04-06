plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.gausslab.labelingstorage"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.gausslab.labelingstorage"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.camera:camera-core:1.2.2")
    implementation("androidx.camera:camera-lifecycle:1.2.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.8.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.8.1")

    //Navigation
    implementation("androidx.navigation:navigation-compose")

    //Hilt
    implementation ("com.google.dagger:hilt-android:2.42")
    kapt ("com.google.dagger:hilt-compiler:2.42")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    //Material3
    implementation ("androidx.compose.material3:material3:1.1.0-beta02")
    implementation ("androidx.compose.material3:material3-window-size-class:1.1.0-beta02")
    implementation("androidx.compose.material:material-icons-extended:1.4.1")

    //Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    //DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    //Ludwig
    implementation ("com.github.baec23:ludwig:0.0.22")

    //Google Play Services
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    implementation("com.google.api-client:google-api-client-android:+") {
        exclude( group = "org.apache.httpcomponents")
    }
    implementation("com.google.apis:google-api-services-sheets:+") {
        exclude(group = "org.apache.httpcomponents")
        exclude(group = "com.google.guava")
    }
}