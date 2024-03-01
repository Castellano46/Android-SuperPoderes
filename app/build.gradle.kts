import java.util.Properties
import java.io.FileInputStream


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.superpoderes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.superpoderes"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        if (rootProject.file("local.properties").canRead()) {
            FileInputStream(rootProject.file("local.properties")).use {
                properties.load(it)
            }
        }

        buildConfigField("String", "HASH", properties.getProperty("HASH", "\"2101aa6ae87af4901488b0e34e59493d\""))
        buildConfigField("String", "APIKEY", properties.getProperty("APIKEY", "\"00419db460a4abaf5d3b4fd97fa83ff1\""))
        buildConfigField("String", "TS", properties.getProperty("TS", "\"1\""))
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

    packaging {
        resources {
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2022.12.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material:1.6.1")
    implementation("androidx.compose.material:material-icons-extended:1.6.1")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-compiler:2.46.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")


    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.2.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("com.google.truth:truth:1.0") //Alternativa: HAMCREST
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("com.google.dagger:hilt-android-testing:2.45")
    kaptTest("com.google.dagger:hilt-compiler:2.47")
    testImplementation("org.robolectric:robolectric:4.10.3")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}