@file:Suppress("UnstableApiUsage")

package me.vennen.spyglassmc

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.execution.configurations.GeneralCommandLine
import me.vennen.spyglassmc.SpyglassLspServerSupportProvider.Companion.supportedFileTypes

class SpyglassLspServerSupportProvider : LspServerSupportProvider {
    companion object {
        val supportedFileTypes = arrayOf("mcfunction", "json")
    }

    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (supportedFileTypes.contains(file.extension)) {
            serverStarter.ensureServerStarted(SpyglassLspServerDescriptor(project))
        }
    }
}

private class SpyglassLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "SpyglassMC") {
    override fun isSupportedFile(file: VirtualFile) = supportedFileTypes.contains(file.extension)
    override fun createCommandLine() = GeneralCommandLine("npx", "@spyglassmc/language-server", "--stdio")
}
