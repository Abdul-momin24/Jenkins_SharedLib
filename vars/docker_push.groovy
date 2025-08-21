def call(String Project, String ImageTag, String dockerhubuser) {
    withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'dockerhubpass', usernameVariable: 'dockerhubuser')]) {
        sh '''
            echo "$dockerhubpass" | docker login -u "$dockerhubuser" --password-stdin
            docker images | grep "$Project"
            docker push "$dockerhubuser/$Project:$ImageTag"
        '''
    }
}
