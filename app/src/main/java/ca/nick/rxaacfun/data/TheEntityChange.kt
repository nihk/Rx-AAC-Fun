package ca.nick.rxaacfun.data

sealed class TheEntityChange
object InsertChange : TheEntityChange()
object DeleteAllChange : TheEntityChange()
data class DeleteChange(val id: Long) : TheEntityChange()