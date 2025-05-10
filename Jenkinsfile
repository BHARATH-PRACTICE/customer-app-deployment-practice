pipeline {
  agent any

  options {
    skipDefaultCheckout(true)
    timestamps()
    timeout(time: 20, unit: 'MINUTES')
  }

  environment {
    IMAGE_NAME = "your-org/your-app"
    REGISTRY_URL = "gcr.io" // or Docker Hub / ECR / GitHub Container Registry
    TAG = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    DOCKER_CREDENTIALS_ID = 'docker-creds'
    DEPLOY_ENV = "dev" // You can make this dynamic if needed
  }

  stages {
    stage('Checkout') {
      steps {
        echo "Checking out branch: ${env.BRANCH_NAME}"
        checkout scm
      }
    }

    stage('Scan') {
      steps {
        echo "Running static code analysis..."
        // Example tools:
        // sh 'mvn sonar:sonar'
        // sh 'snyk test'
      }
    }

    stage('Build') {
      steps {
        echo "Building the application..."
        // sh 'mvn clean package -DskipTests'
        // Or for Node.js: sh 'npm install && npm run build'
      }
    }

    stage('Push Docker Image') {
      steps {
        script {
         // docker.withRegistry("https://${REGISTRY_URL}", "${DOCKER_CREDENTIALS_ID}") {
           // def image = docker.build("${IMAGE_NAME}:${TAG}")
           // image.push()
           echo "Building an image"
           echo "image pushed to doker hub"
          }
        }
      }
    }

    stage('Deploy') {
      steps {
        echo "Deploying to environment: ${DEPLOY_ENV}"
        // Example for Kubernetes:
        // sh "kubectl set image deployment/your-app your-app=${IMAGE_NAME}:${TAG} -n your-namespace"
        // Or for cloud-specific CLI tools
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
