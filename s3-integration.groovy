pipeline {
    agent any
    stages {
        stage('Upload to S3') {
            steps {
                withAWS(credentials: 'AKIA6HMLXTAQUBOYVI4Y', region: 'us-east-2') {
                    // Create a sample file (optional)
                    sh 'echo "Hello from Jenkins" > hello.txt'
                    // Upload the file to your S3 bucket
                    s3Upload(bucket: 'niki-ani1', file: 'hello.txt', path: 'hello.txt')
                }
            }
        }
    }
}
