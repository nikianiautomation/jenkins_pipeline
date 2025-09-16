pipeline {
    agent any
    stages {
        stage('Upload to S3') {
            steps {
                withAWS(credentials: 'niki-s3', region: 'us-east-2') {
                    sh 'echo "Hello from Jenkins with withAWS" > hello.txt'
                    s3Upload(
                        bucket: 'niki-ani1',
                        file: 'hello.txt',
                        path: 'hello.txt'
                    )
                }
            }
        }
    }
}
