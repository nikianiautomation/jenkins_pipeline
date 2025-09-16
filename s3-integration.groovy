pipeline {
    agent any
    stages {
        stage('pull from git'){
          echo 'calling Repo'
                sh "wget -O s3-terraform.tf https://github.com/nikianiautomation/PROJECT1/blob/main/s3/s3-terraform.tf"
                
                echo 'End'  
        }
        stage('Upload to S3') {
            steps {
                
                withAWS(credentials: 'niki-s3', region: 'us-east-2') {
                s3Upload(
                    bucket: 'niki-ani1',
                    file: 's3-terraform.tf',
                    path: 'my-app/'
                )
            }
        }
        }
    }
}
