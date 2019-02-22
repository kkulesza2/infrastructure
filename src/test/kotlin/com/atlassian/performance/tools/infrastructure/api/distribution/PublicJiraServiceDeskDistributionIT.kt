package com.atlassian.performance.tools.infrastructure.api.distribution

import com.atlassian.performance.tools.infrastructure.docker.SshUbuntuContainer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PublicJiraServiceDeskDistributionIT {

    @Test
    fun shouldDownloadJiraServiceDesk() {
        SshUbuntuContainer().run { ssh ->
            val serviceDeskDistribution: ProductDistribution = PublicJiraServiceDeskDistribution("4.0.1")
            val targetFolder = "test"
            ssh.execute("mkdir $targetFolder")

            val remoteDirectory = serviceDeskDistribution
                .install(ssh, targetFolder)

            val directories = ssh.execute("ls $remoteDirectory").output
            assertThat(directories).contains("atlassian-jira")
        }
    }
}