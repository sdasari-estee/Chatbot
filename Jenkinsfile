pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
    }
}
