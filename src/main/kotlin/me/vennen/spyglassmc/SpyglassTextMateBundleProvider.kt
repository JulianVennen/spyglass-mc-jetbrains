package me.vennen.spyglassmc

import com.intellij.openapi.application.PluginPathManager
import com.intellij.openapi.project.DumbAware
import org.jetbrains.plugins.textmate.api.TextMateBundleProvider
import kotlin.io.path.exists

class SpyglassTextMateBundleProvider : TextMateBundleProvider, DumbAware {
    override fun getBundles(): List<TextMateBundleProvider.PluginBundle> {
        val bundle = PluginPathManager.getPluginResource(javaClass, "lang/")?.toPath()

        if (bundle == null || !bundle.exists()) {
            return emptyList()
        }

        return listOf(
            TextMateBundleProvider.PluginBundle("McMeta (SpyglassMC)", bundle)
        )
    }
}
