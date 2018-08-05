package ca.nick.rxaacfun.data

sealed class TheEntityChange {
    object Insert : TheEntityChange()
    object DeleteAll : TheEntityChange()
}