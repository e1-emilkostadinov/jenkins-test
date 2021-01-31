#!/usr/bin/env groovy

pipelineJob('custom-repo-test') {
    displayName('Custom repo test')

    configure { project ->
        project / 'properties' / 'org.jenkinsci.plugins.workflow.job.properties.DurabilityHintJobProperty' {
            hint('PERFORMANCE_OPTIMIZED')
        }
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/e1-emilkostadinov/jenkins-test.git')
                        credentials('jenkins-operator')
                    }
                    branches('*/master')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}
