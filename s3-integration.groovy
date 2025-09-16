            pipeline {
              agent any
              stages {
                stage('Build') {
                  steps {
                    // Your build steps to generate artifacts
                    sh 'echo "This is a test artifact." > artifact.txt'
                    sh "pwd"
                  }
                }
              stage('Upload to S3') {
                steps {
                  withAWS(credentials: "AKIA6HMLXTAQUBOYVI4Y", region: "us-east-2") {
                    s3Upload(
                      file: "artifact.txt",
                      bucket: "niki-ani1",
                      path: "artifact.txt", // Optional: path within the S3 bucket
                      acl: 'Private' // Example: 'PublicRead', 'Private', etc.
                    )
                  }
                }
              }
            }
        }