plugins {
    id("com.android.library")
    id("kotlin-android")
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

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${ProjectLevelVersions.kotlinVersion}")

    //TODO dependencies for testing

}