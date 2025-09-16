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
                stage("Upload") {
                  steps {
                    withAWS(region: "us-east-2", credentials: "AKIA6HMLXTAQUBOYVI4Y"){
                      s3Upload(bucket: "niki-ani1",entries: [[file: "artifact.txt", s3Prefix: "artifact/path/"]])
                    }
                  }
                }
              }
            }