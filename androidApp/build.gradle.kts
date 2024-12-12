plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.traveldiary.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.traveldiary.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.appcompat)
//    implementation(libs.custom.indicator)
//    implementation(libs.android.viewpagerindicator)
    implementation(libs.koin.android)
    implementation(libs.android.location)
    implementation(libs.play.services.auth)
    implementation(libs.glide)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.navigation.fragment)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    implementation(libs.androidx.databinding.common)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serializationJson)
    implementation(libs.google.gson)
    implementation(libs.circleimageview)
    implementation(libs.cardstackview)
    debugImplementation(libs.compose.ui.tooling)
}