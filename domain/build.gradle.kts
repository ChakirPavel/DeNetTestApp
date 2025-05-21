plugins {
    alias(libs.plugins.kotlin)
}

kotlin {
    jvmToolchain(18)
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(18))
}


dependencies {
    implementation(libs.kotlinx.coroutines.core)
}