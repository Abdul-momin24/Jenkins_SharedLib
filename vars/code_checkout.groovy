def call(String GitUrl, String GitBranch) {
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${GitBranch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [
            [$class: 'CleanBeforeCheckout'],
            [$class: 'CheckoutOption', timeout: 20],
            [$class: 'CloneOption', noTags: false, shallow: false, depth: 0, timeout: 20]
        ],
        userRemoteConfigs: [[url: GitUrl]]
    ])

    // Ensure we're on the branch, not detached
    sh "git checkout ${GitBranch}"
    sh 'echo "✅ Checked out commit:" && git rev-parse HEAD && git branch'
}
