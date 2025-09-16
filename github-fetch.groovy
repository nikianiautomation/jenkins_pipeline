pipeline {
    agent any 
    stages {
        stage('github_fetch') { 
            steps {
                echo 'calling Repo'
                sh "wget -O s3-terraform.tf https://github.com/nikianiautomation/PROJECT1/blob/main/s3/s3-terraform.tf"
                sh "cat s3-terraform.tf"
                echo 'End'
            }
        }
    }
}