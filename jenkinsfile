pipeline {
	agent any
	environment {
            git_branche = 'main'
            git_credential    = 'f2f5e5a7-b736-4f74-a641-08a45579f08e'
            git_url    = 'https://github.com/cuikuifa/boron.git'
            docker_harbor_url    = '121.5.46.11/boron'
            project_name    = 'boron'
            docker_tag    = ${env.BUILD_NUMBER}
            project_port    = '8088'
            rancher_credential    = 'f2f5e5a7-b736-4f74-a641-08a45579f08e'
            rancher_workload    = '/project/c-4hzx8:p-8vz4z/workloads/deployment:boron:example-helloserver'
        }
	stages {
		stage('git代码拉取') {
			//拉代码
			steps {
				checkout([$class:'GitSCM',branches:[[name:'$git_branche']],extensions:[],userRemoteConfigs:[[credentialsId:'$git_credential',url:'$git_url']]])
			}
		}
		stage('maven打包') {
			//打包
			steps {
				sh "mvn clean -U package -Dmaven.test.skip=true"
			}
		}
		stage('打docker镜像') {
			// 打镜像
			steps {
				// sh "mvn dockerfile:build"
				sh "docker build -t $docker_harbor_url/$project_name:$docker_tag --build-arg JAR_FILE_NAME=$project_name-0.0.1-SNAPSHOT.jar --build-arg JAR_PORT=$project_port ./target"
			}
		}
		stage('推送镜像到仓库') {
			// 推送镜像
			steps {
				// sh "mvn dockerfile:build"
				sh "docker push $docker_harbor_url/$project_name:$docker_tag"
			}
		}
		stage('部署服务') {
			// 通知rancher部署服务
			steps {
				rancherRedeploy alwaysPull: true, credential: '$rancher_credential', images: '$docker_harbor_url/$project_name:$docker_tag', workload: '$rancher_workload'
			}
		}
	}
}