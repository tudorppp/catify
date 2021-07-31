plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)

        consumerProguardFiles(Config.proguardConsumerRules)
    }

    buildTypes {
        getByName(BuildType.Release.getName()) {
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${ProjectLevelVersions.kotlinVersion}")

    api("com.google.dagger:dagger:${AppModuleVersions.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${AppModuleVersions.daggerVersion}")

    //TODO dependencies for testing
    kaptAndroidTest("com.google.dagger:dagger-compiler:${AppModuleVersions.daggerVersion}")
    kaptTest("com.google.dagger:dagger-compiler:${AppModuleVersions.daggerVersion}")
}