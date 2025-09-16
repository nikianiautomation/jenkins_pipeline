pipeline {
    agent any
    stages {
        stage('Upload to S3') {
            steps {
                // Create a sample file (optional)
                sh 'echo "Hello from Jenkins S3 Publisher Plugin" > hello.txt'
                // Upload using S3 Publisher Plugin syntax
                s3Upload(
                    entries: [
                        [bucket: 'niki-ani1', sourceFile: 'hello.txt', target: 'hello.txt']
                    ]
                )
            }
        }
    }
}
