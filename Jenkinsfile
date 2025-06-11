library(
    identifier: 'sharedLib@main',
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'https://github.com/BHARATH-PRACTICE/jenkins-pipelines-shared-lib.git',
        credentialsId: 'git-org-token'
    ])
)

// Call your shared library method with parameters
sharedLib([
    appName : 'customer-app',
    repoName: 'customer-app-deployment-practice'
])

        