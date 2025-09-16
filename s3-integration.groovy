pipeline {
    agent any
    stages {
        stage('Upload to S3') {
            steps {
                sh 'echo "Hello with full S3 Publisher config" > hello.txt'
                s3Upload(
                    entries: [
                        [bucket: 'niki-ani1', sourceFile: 'hello.txt', target: 'hello.txt']
                    ],
                    profileName: 'your-aws-profile',                        // AWS CLI config profile name or Jenkins credential name
                    userMetadata: [                                         // Required metadata key-value pairs
                        [key: 'uploadedBy', value: 'jenkins'],
                        [key: 'buildNumber', value: "${env.BUILD_NUMBER}"]
                    ],
                    dontWaitForConcurrentBuildCompletion: false,          // Boolean, usually false
                    consoleLogLevel: 'INFO',                               // Options: INFO, WARN, ERROR, DEBUG
                    pluginFailureResultConstraint: 'FAILURE',             // Action on failure, e.g., FAILURE
                    dontSetBuildResultOnFailure: false                     // Whether to continue build on failure
                )
            }
        }
    }
}
