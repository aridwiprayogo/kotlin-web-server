package fr.iim.iwm.a5.kelnerowski.florian.kotlin

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTests {
    @Test
    fun testArticleFound() {
        val model = mock<Model> {
            on  { getArticle(42) } doReturn Article(42, "super titre", "text text text")
        }

        val articleController = ArticleControllerImpl(model)

        val userSession = UserSession("flo")

        val result = articleController.startFM(userSession,42)
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testArticleNotFound() {
        val articleController = ArticleControllerImpl(FakeModel())
        val userSession = UserSession("flo")

        val result = articleController.startFM(userSession,55)
        assertEquals(HttpStatusCode.NotFound, result)
    }
}