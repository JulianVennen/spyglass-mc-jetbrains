package me.vennen.spyglassmc.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider

@Suppress("UnstableApiUsage")
class SpyglassLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (arrayOf("mcfunction", "json").contains(file.extension)) {
            serverStarter.ensureServerStarted(SpyglassLspServerDescriptor(project))
        }
    }
}
