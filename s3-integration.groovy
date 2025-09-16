pipeline {
    agent any
    stages {
        stage('Upload to S3') {
            steps {
                sh 'echo "Hello with full S3 Publisher config" > hello.txt'
                withAWS(credentials: 'niki-s3', region: 'us-east-2') {
                s3Upload(
                    entries: [
                        [bucket: 'niki-ani1', sourceFile: 'hello.txt', target: 'hello.txt']
                    ],
                    userMetadata: [                                         // Required metadata key-value pairs
                        [key: 'uploadedBy', value: 'jenkins'],
                        [key: 'buildNumber', value: "${env.BUILD_NUMBER}"]
                    ],
                    profileName: 'AWS_DEFAULT_PROFILE',
                    dontWaitForConcurrentBuildCompletion: false,          // Boolean, usually false
                    consoleLogLevel: 'INFO',                               // Options: INFO, WARN, ERROR, DEBUG
                    pluginFailureResultConstraint: 'FAILURE',             // Action on failure, e.g., FAILURE
                    dontSetBuildResultOnFailure: false                     // Whether to continue build on failure
                )
            }
        }
        }
    }
}
