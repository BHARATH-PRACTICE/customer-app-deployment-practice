pipeline {
  agent any

  environment {
    MAVEN_HOME = tool 'maven-3.9.9'
    JAVA_HOME = tool name: 'jenkins-jdk', type: 'hudson.model.JDK'
    DB_URL = "jdbc:mysql://localhost:3306/bharath_gcp_deployment_practice_db"
    DB_USERNAME = "root"
    DB_PASSWORD = "root"
    SERVER_PORT = "9872"
    JPA_SHOW_SQL = "true"
    JPA_FORMAT_SQL = "true"
    CREATE_TABLE_AUTOMATIC = "update"
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
        bat """
          set DB_URL=${env.DB_URL}
          set DB_USERNAME=${env.DB_USERNAME}
          set DB_PASSWORD=${env.DB_PASSWORD}
          set SERVER_PORT=${env.SERVER_PORT}
          set JPA_SHOW_SQL=${env.JPA_SHOW_SQL}
          set JPA_FORMAT_SQL=${env.JPA_FORMAT_SQL}
          set CREATE_TABLE_AUTOMATIC=${env.CREATE_TABLE_AUTOMATIC}
          set "PATH=%JAVA_HOME%\\bin;%MAVEN_HOME%\\bin;%PATH%"
          mvn clean install
        """
      }
    }

    stage('Deploy to Nexus') {
      steps {
        echo '📦 Deploying artifact to Nexus...'
        withCredentials([usernamePassword(credentialsId: 'nexus-repository', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
          writeFile file: 'settings.xml', text: """
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">
  <servers>
    <server>
      <id>nexus-releases</id>
      <username>${NEXUS_USER}</username>
      <password>${NEXUS_PASS}</password>
    </server>
    <server>
      <id>nexus-snapshots</id>
      <username>${NEXUS_USER}</username>
      <password>${NEXUS_PASS}</password>
    </server>
  </servers>
</settings>
          """
          bat """
            set "PATH=%MAVEN_HOME%\\bin;%PATH%"
            mvn deploy -s settings.xml
          """
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
