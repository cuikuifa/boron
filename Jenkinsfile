pipeline {
	agent any
	environment {
            git_branche = 'main'
            git_credential    = '69d175a4-1db0-4222-af23-8b1f60570fb9'
            git_url    = 'https://github.com/cuikuifa/boron.git'
            docker_harbor_url    = '121.5.46.11/boron'
            harbor_username    = 'admin'
            harbor_pwd    = 'harbor'
            project_name    = 'boron'
            docker_tag    = '${BUILD_NUMBER}'
            project_port    = '8088'
            rancher_credential    = 'f2f5e5a7-b736-4f74-a641-08a45579f08e'
            rancher_workload    = '/project/c-4hzx8:p-8vz4z/workloads/deployment:boron:example-helloserver'
        }
	stages {
		stage('git代码拉取') {
			//拉代码
			steps {
				checkout([$class:'GitSCM',branches:[[name:'main']],extensions:[],userRemoteConfigs:[[credentialsId:'69d175a4-1db0-4222-af23-8b1f60570fb9',url:'https://github.com/cuikuifa/boron.git']]])
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
				sh "cp dockerfile target && docker build -t $docker_harbor_url/$project_name:$docker_tag --build-arg JAR_FILE_NAME=$project_name-0.0.1-SNAPSHOT.jar --build-arg JAR_PORT=$project_port ./target"
			}
		}
		stage('推送镜像到仓库') {
			// 推送镜像
			steps {
				sh "docker login -u $harbor_username -p $harbor_pwd $docker_harbor_url && docker push $docker_harbor_url/$project_name:$docker_tag"
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