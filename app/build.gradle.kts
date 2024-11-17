plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.trabalho"
    compileSdk = 35  // Atualizado para 35

    defaultConfig {
        applicationId = "com.example.trabalho"
        minSdk = 24
        targetSdk = 35
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

    // Configurações de compatibilidade com versões específicas do Java e Kotlin
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
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("com.google.android.material:material:1.9.0")





    // Dependências de teste
    testImplementation("junit:junit:4.13.2") // Testes de unidade
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Testes instrumentados
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

// Configuração adicional para garantir a execução dos testes
tasks.withType<Test> {
    useJUnitPlatform() // Certifique-se de que os testes JUnit são suportados
}
