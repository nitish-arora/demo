pipeline {
	agent any
	tools {
		maven 'Maven_auto'
	}
	options {
		timestamps()
		timeout(time: 1 unit: 'HOURS')
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
					id: 'deployer'
					serverId: '3150808@nitish'
					releaseRepo: 'nagp-practice'
					snapshotRepo: 'nagp-practice'
				)
				rtMavenRun (
					pom: 'pom.xml'
					goals: 'clean install'
					deployerId: 'deployer'
				)
			}
		}
	}	
}