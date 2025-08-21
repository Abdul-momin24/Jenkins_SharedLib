def call(String GitUrl, String GitBranch) {
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${GitBranch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [
            [$class: 'CleanBeforeCheckout'],   // 🔑 wipes old workspace before checkout
            [$class: 'CheckoutOption', timeout: 20],
            [$class: 'CloneOption', noTags: false, shallow: false, depth: 0, timeout: 20]
        ],
        userRemoteConfigs: [[url: GitUrl]]
    ])

    // Debug: print the commit being built
    sh 'echo "✅ Checked out commit:" && git rev-parse HEAD'
}
