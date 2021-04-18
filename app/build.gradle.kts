plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion(Config.buildToolsVersion)

    defaultConfig {
        applicationId("com.example.baseproject")
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode(Config.versionCode)
        versionName(Config.versionName)

        testInstrumentationRunner(Config.androidTestInstrumentationRunner)
    }

    buildTypes {
        getByName(BuildType.Release.getName()) {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${AppModuleVersions.AndroidX.coreKtx}")
    implementation("androidx.appcompat:appcompat:${AppModuleVersions.AndroidX.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${AppModuleVersions.AndroidX.constraintLayout}")

    implementation("androidx.navigation:navigation-fragment-ktx:${AppModuleVersions.AndroidX.navigationComponent}")
    implementation("androidx.navigation:navigation-ui-ktx:${AppModuleVersions.AndroidX.navigationComponent}")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${AppModuleVersions.AndroidX.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${AppModuleVersions.AndroidX.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-common-java8-ktx:${AppModuleVersions.AndroidX.lifecycle}")

    implementation("com.google.android.material:material:${AppModuleVersions.materialComponentsVersion}")

    testImplementation("androidx.arch.core:core-testing:${AppModuleVersions.AndroidX.testArch}")

    //TODO dependencies for testing
}