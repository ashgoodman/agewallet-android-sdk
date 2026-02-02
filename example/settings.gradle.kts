pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AgeWallet SDK Demo"
include(":app")
include(":agewallet-sdk")
project(":agewallet-sdk").projectDir = file("../agewallet-sdk")
