import ru.netology.Notes

class NoteService {
    private var notes = mutableListOf<Notes>()
    private var comments = mutableListOf<Comment>()

    class NoteOrCommentException(message: String) : RuntimeException(message)

    
    fun add(
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: String,
        privacyComment: String,
        needWiki: Boolean,
        canComment: Boolean,
        userId: Int,
        offset: Int,
        count: Int,
        sort: Int
    ): Int {
        val noteId = if (notes.isNotEmpty() && notes.last().noteId > 0) {
            notes.last().noteId + 1
        } else {
            1
        }
        val note = Notes(
            noteId, title, text, privacy, commentPrivacy, privacyView, privacyComment,
            needWiki, canComment, userId, offset, count, sort
        )

        notes += note
        return noteId
    }

    fun createComment(
        noteId: Int, replyTo: Int, message: String, deleted: Boolean = false,
        date: Int, sort: Int, offset: Int, count: Int
    ): Int {
        val commentId = if (comments.isNotEmpty() && comments.last().commentId > 0) {
            comments.last().commentId + 1
        } else {
            1
        }
        val comment = Comment(commentId, noteId, replyTo, message, deleted, date, sort, offset, count)
        comments += comment
        return comment.commentId
    }

    fun delete(noteId: Int): Boolean {
        val it = comments.iterator()
        while (it.hasNext()) {
            if (it.next().noteId == noteId) {
                it.remove()
            }
        }
        for (note in notes) {
            if (note.noteId == noteId) {
                notes.remove(note)
                return true
            }
        }
        throw NoteOrCommentException("Нет заметок для id: $noteId")
    }


    fun editComment(commentId: Int, message: String): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId && !comment.deleted) {
                comment.message = message
                return true
            }
        }
        throw NoteOrCommentException("Нет комментариев с id:$commentId")
    }

    fun deleteComment(commentId: Int, flagOfFullDelete: Boolean = false): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                if (comment.deleted) {
                    throw NoteOrCommentException("Комментарий с id: $commentId удален")
                } else if (flagOfFullDelete) {
                    comments.remove(comment)
                } else {
                    comment.deleted = true
                }
                return true
            }
        }
        throw NoteOrCommentException("Нет комментариев с id: $commentId")
    }

    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: String,
        privacyComment: String
    ): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                note.title = title
                note.text = text
                note.privacy = privacy
                note.commentPrivacy = commentPrivacy
                note.privacyView = privacyView
                note.privacyComment = privacyComment
                return true
            }
        }
        return false
    }

    fun get(noteIds: List<Int>, userId: Int, offset: Int, count: Int, sort: Int): List<Notes>? {
        val getNotes = mutableListOf<Notes>()
        for (note in notes) {
            if (noteIds.contains(note.noteId)) {
                getNotes += note
            }
        }
        return if (getNotes.isEmpty()) {
            println("Не удалось найти заметки")
            null
        } else
            getNotes
    }

    fun getComments(noteId: Int, sort: Int, offset: Int, count: Int): Array<Comment>? {
        var getComments = arrayOf<Comment>()
        for (comment in comments) {
            if (comment.noteId == noteId && !comment.deleted) {
                getComments += comment
            }
        }

        return if (getComments.isNotEmpty())
            getComments
        else
            null
    }

    fun getById(noteId: Int, needWiki: Boolean): Notes {
        for (note in notes) {
            if (note.noteId == noteId)
                return note
        }
        throw NoteOrCommentException("Нет заметок с id:$noteId")
    }

    fun restoreComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId && comment.deleted) {
                comment.deleted = false
                return true
            } else if (comment.commentId == commentId && !comment.deleted) {
                throw NoteOrCommentException("Комментарий с id: $commentId не может быть восстановлен, так как он не был удален")
            }
        }
        throw NoteOrCommentException("Нет комментария с id: $commentId")
    }
}
