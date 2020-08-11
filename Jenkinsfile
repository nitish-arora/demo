pipeline {
	agent any
	tools {
		maven 'Maven_auto'
		jdk 'Java'
	}
	options {
		timestamps()
		timeout(time: 1, unit: 'HOURS')
		skipDefaultCheckout()
		buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
		disableConcurrentBuilds()
	}
	stages {
		stage ('Checkout') {
			steps {
				echo 'getting clone'
				checkout scm
			}
		}
		stage ('Build') {
			steps {
				echo 'getting mvn install'
				bat 'mvn install'
			}
		}
		stage ('Unit Testing') {
			steps {
				echo 'running unit test cases maven'
				bat 'mvn test'
			}
		}
		stage ('Sonar Analysis') {
			steps {
				withSonarQubeEnv('Sonar') {
					bat 'mvn sonar:sonar'
				}
			}
		}
		stage ('Upload to artifactory') {
			steps {
				rtMavenDeployer (
					id: 'deployer',
					serverId: '3150808@nitish',
					releaseRepo: 'nagp-practice',
					snapshotRepo: 'nagp-practice'
				)
				rtMavenRun (
					pom: 'pom.xml',
					goals: 'clean install',
					deployerId: 'deployer'
				)
				rtPublishBuildInfo (
					serverId: '3150808@nitish'
				)
			}
		}
		stage ('Get Docker Image') {
			steps {
				bat 'docker build -t nitisharora31/nagp:nagp-%BUILD_NUMBER% --no-cache -f Dockerfile .'
			}
		}
		stage ('Push to Dockerhub') {
			steps {
				withDockerRegistry ([ credentialsId: '98c3992e-c86d-4ab0-ba52-05958281fd8d', url:"https://hub.docker.com/"]) {
					bat 'docker push nitisharora31/nagp:nagp-%BUILD_NUMBER%'
				}				
			}
		}
		stage ('Docker Deployment') {
			steps {
				bat 'docker run --name nagp-pipeline -d -p 8085:8085 nitisharora31/nagp:nagp-%BUILD_NUMBER%'
			}
		}
	}
	post {
		always {
			junit '**/*.xml'		
		}
	}
}