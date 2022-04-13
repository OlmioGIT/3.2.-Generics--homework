import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import ru.netology.Notes

class NoteServiceTest {

    @Test
    fun add() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val expected = 2
        assertEquals(expected, result)
    }

    @Test
    fun createComment() {
        val service = NoteService()
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)

        val size = service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val expected = 5
        assertEquals(expected, size)
    }

    @Test
    fun deleteComment() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.deleteComment(5, true)
        assertEquals(true, result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun deleteCommentThrow() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.deleteComment(3, false)
        assertEquals(false, result)
    }

    @Test
    fun delete() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.delete(2)
        assertEquals(true, result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun deleteThrow() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.delete(6)
        assertEquals(false, result)
    }

    @Test
    fun edit() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.edit(
            2, "My Edited First Note", "Edited Text", 1, 1, "Privacy View 1", "Privacy Comment 1"
        )
        assertEquals(true, result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun editCommentThrow() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.editComment(6, "My Edited Comment")
        assertEquals(false, result)
    }

    @Test
    fun editNotPassed() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.edit(
            6, "My Edited First Note", "Edited Text", 1, 1, "Privacy View 1", "Privacy Comment 1"
        )
        assertEquals(false, result)
    }

    @Test
    fun editComment() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.editComment(5, "My Edited Comment")
        assertEquals(true, result)
    }

    @Test
    fun get() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.get(listOf(1, 2, 5), 1, 1, 2, 0)
        assertNotNull(result)
    }

    @Test
    fun getNull() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.get(listOf(5, 7, 9), 1, 1, 2, 0)
        assertNull(result)
    }

    @Test
    fun getById() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val expected = Notes(
            1, "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.getById(1, false)
        assertEquals(expected, result)
    }

    @Test
    fun getCommentsNull() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.getComments(4, 0, 1, 2)?.toList()
        assertNull(result)
    }

    @Test
    fun restoreComment() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.restoreComment(3)
        assertEquals(true, result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun getByIdThrow() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val expected = Notes(
            1, "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.getById(6, false)
        assertEquals(expected, result)
    }

    @Test
    fun getComments() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.getComments(2, 0, 1, 2)?.toList()
        assertNotNull(result)
    }


    @Test(expected = NoteService.NoteOrCommentException::class)
    fun restoreCommentThrow() {
        val service = NoteService()
        service.add(
            "Try", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "Try Second ", "text", 0, 0, "-", "-",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "try", deleted = false, 141267414, 1, 2, 3)
        service.createComment(2, 2, "try", deleted = false, 141232414, 0, 2, 4)
        service.createComment(1, 2, "ag try", deleted = true, 141214514, 0, 2, 2)
        service.createComment(2, 2, "ag try", deleted = true, 141267438, 1, 2, 1)
        service.createComment(2, 2, "del??", deleted = false, 141267434, 0, 2, 3)
        val result = service.restoreComment(2)
        assertEquals(false, result)
    }
}
