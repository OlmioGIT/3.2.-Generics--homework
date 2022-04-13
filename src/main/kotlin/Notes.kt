package ru.netology

data class Notes(
    var noteId: Int = 0,
    var title: String,
    var text: String,
    var privacy: Int = 0,
    var commentPrivacy: Int = 0,
    var privacyView: String,
    var privacyComment: String,
    var needWiki: Boolean = false,
    var canComment: Boolean = false,
    var userId: Int,
    var offset: Int,
    var count: Int,
    var sort: Int
)

