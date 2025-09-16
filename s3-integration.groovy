pipeline {
    agent any
    stages {
        stage('Upload to S3') {
            steps {
                sh 'echo "Hello with full S3 Publisher config" > hello.txt'
                withAWS(credentials: 'niki-s3', region: 'us-east-2') {
                s3Upload(
                    bucket: 'niki-ani1',
                    file: 'hello.txt',
                    path: 'my-app/'
                    cacheControl: 'max-age=3600',
                    metadata: [key1: 'value1', key2: 'value2']
                )
            }
        }
        }
    }
}
