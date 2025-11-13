pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "ItCourses"
include(":app")
include(":core")
include("feature:auth")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:main")
include(":feature:main:api")
include(":feature:main:impl")
include(":feature:buttom_nav:api")
include(":feature:buttom_nav:impl")
include(":feature:favorites:api")
include(":feature:favorites:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
