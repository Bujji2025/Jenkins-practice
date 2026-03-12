pipeline {
    agent any

    environment {
        REGISTRY_URL = 'docker-host:5000'
        IMAGE_NAME   = 'jenkins-practice'
        IMAGE_TAG    = "${env.BUILD_NUMBER}"
    }

    stages {

        stage('checkout') {
            steps {
                echo '========== Stage 1: Checkout =========='
                checkout scm
                echo 'Code checked out successfully'
            }
        }

        stage('Build') {
            steps {

                echo '========== Stage 2: Build JAR =========='
                sh 'mvn clean package -DskipTests'
                echo 'JAR built successfully'

            }

        }

        stage('Test') {

            steps {
                echo '========== Stage 3: Run Tests =========='
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
                success { echo 'All tests passed!' }
                failure { echo 'Tests failed!' }
            }

        }

        stage('Docker Build') {
            steps {
                echo '========== Stage 4: Build Docker Image =========='
                sh """
                    docker login docker-host:5000 \
                        -u dock_user \
                        -p dock_password
                    docker build \
                      -t ${REGISTRY_URL}/${IMAGE_NAME}:${IMAGE_TAG} \
                      -t ${REGISTRY_URL}/${IMAGE_NAME}:latest \
                      .
                """
                echo "Image built: ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }

        stage('Push to Registry') {
            steps {
                echo '========== Stage 5: Push to Registry =========='
                sh "docker push ${REGISTRY_URL}/${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker push ${REGISTRY_URL}/${IMAGE_NAME}:latest"
                echo 'Image pushed successfully'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo '========== Stage 6: Deploy to Kubernetes =========='
                sh 'kubectl apply -f k8s/deployment.yaml --validate=false'
                sh 'kubectl apply -f k8s/service.yaml --validate=false'
                sh 'kubectl rollout status deployment/jenkins-practice'
            }
        }

    }

    post {
        success {
            echo '========================================='
            echo 'PIPELINE SUCCEEDED - App is live on K8s!'
            echo '========================================='
        }
        failure {
            echo '========================================='
            echo 'PIPELINE FAILED - Check console output'
            echo '========================================='
        }
    }
}







            