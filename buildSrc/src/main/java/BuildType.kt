private const val RELEASE = "release"
private const val DEBUG = "debug"

sealed class BuildType() {

    abstract fun getName(): String

    object Release : BuildType() {
        override fun getName(): String {
            return RELEASE
        }
    }

    object Debug : BuildType() {
        override fun getName(): String {
            return DEBUG
        }
    }
}