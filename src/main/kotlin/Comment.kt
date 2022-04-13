data class Comment(
    var commentId: Int = 0,
    var noteId: Int,
    var replyTo: Int,
    var message: String,
    var deleted: Boolean = false,
    var date: Int,
    var sort: Int,
    var offset: Int,
    var count: Int
)

