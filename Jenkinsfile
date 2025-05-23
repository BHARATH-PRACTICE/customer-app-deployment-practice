pipeline {
  agent any

  environment {
    MAVEN_HOME = tool 'maven-3.9.9'
    JAVA_HOME = tool name: 'jenkins-jdk', type: 'hudson.model.JDK'
  }

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
        echo '🔧 Building the Maven project...'
        withCredentials([file(credentialsId: 'customer-app-env', variable: 'ENV_FILE')]) {
          bat """
            echo Loading environment variables from %ENV_FILE%
            for /f "usebackq tokens=1,2 delims==" %%A in ("%ENV_FILE%") do set %%A=%%B
            set "PATH=%JAVA_HOME%\\bin;%MAVEN_HOME%\\bin;%PATH%"
            mvn clean install
          """
        }
      }
    }

    stage('Deploy to Nexus') {
      steps {
        echo '📦 Deploying artifact to Nexus...'
        withCredentials([
          file(credentialsId: 'customer-app-env', variable: 'ENV_FILE'),
          usernamePassword(credentialsId: 'nexus-repository', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')
        ]) {
          bat """
            for /f "usebackq tokens=1,2 delims==" %%A in ("%ENV_FILE%") do set %%A=%%B
            echo Writing Maven settings file with Nexus credentials...
            set "PATH=%MAVEN_HOME%\\bin;%PATH%"
            echo ^<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"^> > settings.xml
            echo   ^<servers^> >> settings.xml
            echo     ^<server^> >> settings.xml
            echo       ^<id^>nexus-releases^</id^> >> settings.xml
            echo       ^<username^>%NEXUS_USER%^</username^> >> settings.xml
            echo       ^<password^>%NEXUS_PASS%^</password^> >> settings.xml
            echo     ^</server^> >> settings.xml
            echo     ^<server^> >> settings.xml
            echo       ^<id^>nexus-snapshots^</id^> >> settings.xml
            echo       ^<username^>%NEXUS_USER%^</username^> >> settings.xml
            echo       ^<password^>%NEXUS_PASS%^</password^> >> settings.xml
            echo     ^</server^> >> settings.xml
            echo   ^</servers^> >> settings.xml
            echo ^</settings^> >> settings.xml

            mvn deploy -s settings.xml
          """
        }
      }
    }

    stage('Docker Login & Push') {
      steps {
        script {
          def imageName = "bharathreddy13/customer-app"
          def imageTag = "v${env.BUILD_NUMBER}"

          withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            sh """
              echo "🔐 Logging into Docker registry"
              echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

              echo "🐳 Building image ${imageName}:${imageTag}"
              docker build -t ${imageName}:${imageTag} .

              echo "🚀 Pushing image to registry"
              docker push ${imageName}:${imageTag}
            """
          }
        }
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
