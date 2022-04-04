pipeline {
    agent any
    options { buildDiscarder(logRotator(numToKeepStr: '720')) }
    environment {
        GIT_REPO="https://github.com/SuperBigBear/SpiderWiki.git"
        BRANCH="main"
        GIT_AUTH="xxxxxxx"
        DOCKER_REGISTRY_AUTH="xxxx"
        IMG_NAME="voith/spiderwiki:v1.0"
    }
    stages {
        stage('Pulling from Git') {
            checkout([$class: 'GitSCM', branches: [[name: '${BRANCH}']], userRemoteConfigs: [[credentialsId: "${GIT_AUTH}", url: "${GIT_REPO}"]]])
        }

        stage('Compiling SpiderWiki'){
          sh 'sudo su root -c "mvn clean package -Dmaven.test.skip=true"'
      }

      stage('Building SpiderWiki Image'){
          withCredentials([usernamePassword(credentialsId: "${DOCKER_REGISTRY_AUTH}", passwordVariable: 'PASSWORD', usernameVariable: 'USER_NAME')]) {
            sh """
              
              echo '
                FROM openjdk:8-jre-alpine
                MAINTAINER ming.lu
                USER root
                RUN mkdir -p /home/SpiderWiki
                WORKDIR /home/SpiderWiki
                COPY target/SpiderWiki-1.0-SNAPSHOT.jar SpiderWiki-1.0-SNAPSHOT.jar
                EXPOSE 8888
                ENTRYPOINT ["java","-jar", "SpiderWiki-1.0-SNAPSHOT.jar"]
              '> Dockerfile
              docker build -t ${IMG_NAME} .
              docker login -u ${USER_NAME} -p '${PASSWORD}'
              docker push ${IMG_NAME}
            """
            }
      }
      stage('Deplying to K8S'){
          sh """
                 #!/bin/bash
                 sudo su root -c "kubectl apply -f Ingress-SpiderWiki.yml"
          """
      }

        stage('Verifying') {
            sh """
            echo "checking if it's running"
            """
            }
        }

        post {
        success {
            echo 'All activities have been performed successfully!'
        }
        failure {
            echo 'At least one activity has failed!'
            echo "Build time: ${new Date()}"
            echo "Build ID: ${env.BUILD_ID}"
            echo "Build URL: ${env.BUILD_URL}"
            emailext body: ""
        }
        unstable {
            echo 'The pipeline has been marked as unstable!'
        }
        changed {
            echo 'The state of the Pipeline has changed!'
        }
    }
    }
}

