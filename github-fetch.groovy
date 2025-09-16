pipeline {
    agent any 
    stages {
        stage('github_fetch') { 
            steps {
                echo 'calling Repo'
                wget https://github.com/nikianiautomation/PROJECT1/blob/main/s3/s3-terraform.tf
                cat s3-terraform.tf
                echo 'End'
            }
        }
    }
}