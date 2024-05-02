plugins{
    `kotlin-dsl`
}

dependencies{
    implementation(plugin(libs.plugins.kotlin.android))
    implementation(plugin(libs.plugins.android.library))
    implementation(plugin(libs.plugins.kotlin.serialization))
    implementation(plugin(libs.plugins.android.application))
    implementation(plugin(libs.plugins.kotlin.symbolProcessing))
}

kotlin{
    jvmToolchain(11)
}

fun plugin(plugin: Provider<PluginDependency>) = plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }