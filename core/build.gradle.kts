plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id( "kotlin-kapt")
    id( "kotlin-parcelize")
}

android {
    namespace = "com.turk.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.android.retrofit.adapter)
    implementation(libs.android.retrofit)
    implementation(libs.android.retrofit.convertor.gson)
    implementation(libs.android.square.logging.interceptor)
    implementation(libs.android.coroutine.core)
    implementation(libs.android.coroutine.android)
    implementation(libs.retrofit.coroutine.adapter)
    implementation(libs.android.google.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.glide)
    implementation(libs.androidx.swiperefreshlayout)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}