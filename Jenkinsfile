node {
      stage("Git Clone"){

        git branch: 'main', url: 'https://https://github.com/Development456/Claims.git'
      }
   
      stage("Docker build"){
        sh 'docker build -t claims12 .'
        sh 'docker image ls'
      }
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'test', usernameVariable: 'apurva', passwordVariable: 'password']]) {
        sh 'docker login -u apurva -p $password'
      }
      stage("Pushing Image to Docker Hub"){
	sh 'docker tag claims12 apurva/claims12'
	sh 'docker push apurva/claims12'
      }
	stage("SSH Into Server") {
       def remote = [:]
       remote.name = 'DEV-VM'
       remote.host = '20.62.171.46'
       remote.user = 'dev_azureuser'
       remote.password = 'AHTgxKmRGb05'
       remote.allowAnyHosts = true
     }
     stage("Deploy"){
	     sh 'docker stop claims12 || true && docker rm -f claims12 || true'
	     sh 'docker run -d -p 9000:9000 --name claims12 claims12'
     }
    
      }
