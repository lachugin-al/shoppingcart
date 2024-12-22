plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin") // Hilt плагин
    kotlin("kapt")
//    id("io.gitlab.arturbosch.detekt") version "1.23.7"
}

android {
    namespace = "com.example.shoppingcart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shoppingcart"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Coil for image loading
    implementation(libs.coil.compose)

    // Coroutines for async operations
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.ui.test.junit4.android)

    // Hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Hilt for Compose
    implementation(libs.hilt.navigation.compose)

//    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

//tasks {
//    withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
//        reports {
//            html.required.set(true)
//            xml.required.set(true)
//            txt.required.set(false)
//        }
//    }
//}
//
//tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
//    jvmTarget = "17"
//}