plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "fr.itii"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "fr.itii"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
        viewBinding = true
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
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.play.services.mlkit.text.recognition.common)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.benchmark.traceprocessor)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    // Google Maps pour Compose
    implementation("com.google.maps.android:maps-compose:4.3.3")
    // Services Google Play pour la localisation
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.1.0")
    // Ajoute ceci pour avoir accès à toutes les icônes Material
    implementation("androidx.compose.material:material-icons-extended")

    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Import du Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.x.x"))
    // SDK Firebase Auth
    implementation("com.google.firebase:firebase-auth")
    // Utilise une version précise (ex: 33.1.0)
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // Pour Firestore, plus besoin de mettre de version si tu utilises le BoM
    // Et attention : le suffixe "-ktx" est devenu optionnel/obsolète dans les dernières versions
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-auth")
    // 1. Firebase BoM (Bill of Materials) - Gère les versions pour toi


    // 2. Bibliothèques Firebase (pas besoin de versions grâce au BoM)
    implementation("com.google.firebase:firebase-auth")      // Authentification
    implementation("com.google.firebase:firebase-firestore") // Base de données

    // 3. Google Maps pour Jetpack Compose
    implementation("com.google.maps.android:maps-compose:4.4.1")
    // Optionnel : Utilitaires Maps (Clustering, etc.)
    implementation("com.google.maps.android:maps-compose-utils:4.4.1")
    // Le SDK Google Maps de base
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // 4. Lifecycle & ViewModel pour Compose (Indispensable pour MapsViewModel)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
}