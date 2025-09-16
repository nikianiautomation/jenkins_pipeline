pipeline {
    agent any
    stages {
        stage('pull from git') {
          steps {

    
          echo 'calling Repo'
          sh "rm -rf PROJECT1"
                sh "git clone https://github.com/nikianiautomation/PROJECT1.git"
                
                
                echo 'End'  
          }
        }
        stage('Upload to S3') {
            steps {
               sh "cd PROJECT1"
                sh "cd s3" 
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
