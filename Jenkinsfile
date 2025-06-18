library(
    identifier: 'springBoot@main',
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'https://github.com/BHARATH-PRACTICE/jenkins-pipelines-shared-lib.git',
        credentialsId: 'bharath-practice-git'
    ])
)

// Call your shared library method with parameters
springBoot([
    appName : 'customer-app',
    repoName: 'customer-app-deployment-practice'
])

        