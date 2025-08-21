def call(String Project, String ImageTag, String dockerhubuser){
    // Trim variables to remove accidental spaces
    def safeProject = Project?.trim()
    def safeTag = ImageTag?.trim()
    def safeUser = dockerhubuser?.trim()

    if(!safeProject || !safeTag || !safeUser){
        error "Project, ImageTag, and DockerHub username must be non-empty!"
    }

    withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'dockerhubpass', usernameVariable: 'dockerhubuser')]) {
        sh "echo ${dockerhubpass} | docker login -u ${dockerhubuser} --password-stdin"
    }

    // Debug: show images before push
    sh "docker images | grep ${safeProject}"

    // Push the image
    sh "docker push ${safeUser}/${safeProject}:${safeTag}"
}
