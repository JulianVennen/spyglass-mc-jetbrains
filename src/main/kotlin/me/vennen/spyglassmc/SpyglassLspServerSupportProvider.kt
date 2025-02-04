@file:Suppress("UnstableApiUsage")

package me.vennen.spyglassmc

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor

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

private class SpyglassLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "SpyglassMC") {
    override fun isSupportedFile(file: VirtualFile) = arrayOf("mcfunction", "json").contains(file.extension)
    override fun createCommandLine() = GeneralCommandLine("node", "--inspect-brk=9229", "/home/julian/workspace/Spyglass/packages/language-server/bin/server.js", "--stdio")
}
