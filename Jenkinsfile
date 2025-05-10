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
        checkout scmGit(branches: [[name: 'feat/*']], extensions: [], userRemoteConfigs: [[credentialsId: 'git-org-token', url: 'https://github.com/BHARATH-PRACTICE/customer-app-deployment-practice.git']])
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
