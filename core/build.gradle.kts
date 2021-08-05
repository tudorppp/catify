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

    //Dagger
    implementation("org.koin:koin-core:${AppModuleVersions.koin}")
    api("org.koin:koin-android:${AppModuleVersions.koin}")

    //RxJava
    api("io.reactivex.rxjava3:rxjava:${CoreModuleVersions.RXJava}")

    //Networking
    implementation("com.squareup.retrofit2:retrofit:${CoreModuleVersions.retrofit}")
    implementation("com.squareup.okhttp3:okhttp:${CoreModuleVersions.okHttp}")
    implementation("com.squareup.retrofit2:converter-moshi:${CoreModuleVersions.moshiConverter}")
}