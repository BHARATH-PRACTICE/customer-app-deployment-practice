pipeline {
  agent any

  stages {
    stage('Load Shared Library & Run') {
      steps {
        script {
          // Load shared library dynamically with modernSCM
          library(
            identifier: 'springBoot@main',
            retriever: modernSCM([
              $class: 'GitSCMSource',
              remote: 'https://github.com/BHARATH-PRACTICE/jenkins-pipelines-shared-lib.git',
              credentialsId: 'git-org-token'
            ])
          )

          // Call your shared library method with parameters
          springBoot appName: 'customer-app', repoName: 'customer-app-deployment-practice'
        }
      }
    }
  }
}
