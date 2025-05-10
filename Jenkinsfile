pipeline {
  agent any

  options {
    skipDefaultCheckout(true)
    timestamps()
    timeout(time: 20, unit: 'MINUTES')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scmGit(
          branches: [[name: 'origin/feat/*']],
          extensions: [],
          userRemoteConfigs: [[
            credentialsId: 'git-org-token',
            url: 'https://github.com/BHARATH-PRACTICE/customer-app-deployment-practice.git'
          ]]
        )
      }
    }

    stage('Build') {
      steps {
        echo '🔧 Simulating build process...'
        //sh 'echo Building the project...'
      }
    }

    stage('Test') {
      steps {
        echo '🧪 Simulating test process...'
        //sh 'echo Running tests...'
      }
    }

    stage('Deploy') {
      steps {
        echo '🚀 Simulating deployment process...'
        //sh 'echo Deploying application...'
      }
    }
  }

  post {
    success {
      echo "✅ Build and deployment succeeded!"
    }
    failure {
      echo "❌ Build or deployment failed!"
    }
    always {
      cleanWs()
    }
  }
}
