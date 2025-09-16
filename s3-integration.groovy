            pipeline {
                agent any
                stages {
                    stage('Build') {
                        steps {
                            // Your build steps to generate artifacts
                            sh 'echo "This is a test artifact." > artifact.txt'
                        }
                    }
                    stage('Upload to S3') {
                        steps {
                            script {
                                s3Upload(file: 'artifact.txt', bucket: 'niki-ani3', path: 'artifacts/build-artifacts/artifact.txt')
                            }
                        }
                    }
                }
            }