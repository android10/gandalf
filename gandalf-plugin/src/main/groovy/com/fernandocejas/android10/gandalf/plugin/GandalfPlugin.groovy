package com.fernandocejas.android10.gandalf.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskInstantiationException

class GandalfPlugin implements Plugin<Project> {

  @Override void apply(Project project) {
    verifyRequiredPlugins project
  }

  private static void verifyRequiredPlugins(Project project) {
    def hasAppPlugin = project.plugins.hasPlugin(AppPlugin)
    def hasLibraryPlugin = project.plugins.hasPlugin(LibraryPlugin)
    if (!hasAppPlugin || !hasLibraryPlugin) {
      throw new TaskInstantiationException(
          "'android' or 'android-library' plugin has to be applied before")
    }
  }
}