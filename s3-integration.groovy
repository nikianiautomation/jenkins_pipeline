pipeline {
    agent any
    stages {
        stage('pull from git') {
          steps {

    
          echo 'calling Repo'
          sh "rm -rf PROJECT1"
                sh "git clone https://github.com/nikianiautomation/PROJECT1.git"
                sh "pwd"
                
                echo 'End'  
          }
        }
        stage('Upload to S3') {
            steps {
               dir('PROJECT1/s3') {
                sh "pwd"
                withAWS(credentials: 'niki-s3', region: 'us-east-2') {
                s3Upload(
                    bucket: 'niki-ani1',
                    file: 's3-terraform.tf',
                    path: 'my-app2/'
                )
            }
               }
        }
        }
    }
}
