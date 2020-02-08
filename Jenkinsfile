node{

    stage("checkout repo") {
        git branch: "master",
        credentialsId: "162c12f2-ba14-4ed2-8464-a11c1a12a7ce",
        url: "https://github.com/romankryzhevskykh/api-training"
    }

    stage("build"){
        sh "./gradlew clean api-test:assemble"
    }

    stage("run api test"){
        sh "./gradlew test"
    }
}