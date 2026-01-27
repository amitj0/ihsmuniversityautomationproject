pipeline {
    agent any

    tools {
        maven 'MAVEN1'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'github-pat',
                    url: 'https://github.com/amitj0/ihsmuniversityautomationproject.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'reports',
                    reportFiles: '*.html',
                    reportName: 'IHSM University Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/*.html', allowEmptyArchive: true
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }

        success {
            emailext (
                to: 'ajangra@ismedusoftsol.com',
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                mimeType: 'text/html',
                attachLog: true,
                body: """
                <html><body>
                <p>Hello Team,</p>
                <p><span style="color:green;"><b>SUCCESS</b></span> - Build completed successfully!</p>
                <p><b>Job:</b> ${env.JOB_NAME} #${env.BUILD_NUMBER}</p>
                <p><b>URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                <p><b>IHSM Report:</b>
                <a href="${env.BUILD_URL}IHSM%20University%20Report/">Click here</a></p>
                <p>Regards,<br/>Automation Team</p>
                </body></html>
                """
            )
        }

        failure {
            emailext (
                to: 'ajangra@ismedusoftsol.com',
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                mimeType: 'text/html',
                attachLog: true,
                body: """
                <html><body>
                <p>Hello Team,</p>
                <p><b style="color:red;">FAILED</b> - Build failed. Please check immediately.</p>
                <p><b>Job:</b> ${env.JOB_NAME} #${env.BUILD_NUMBER}</p>
                <p><b>URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                <p>Regards,<br/>Automation Team</p>
                </body></html>
                """
            )
        }
    }
}
