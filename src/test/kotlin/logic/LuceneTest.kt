package logic

import kotlin.test.Test
import kotlin.test.assertContentEquals
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute
import org.wltea.analyzer.lucene.IKAnalyzer

class LuceneTest {

    @Test
    fun test() {
        assertContentEquals(listOf("hello", "world"), tokenize("hello world"))
    }

    companion object {
        @JvmStatic
        fun tokenize(s: String): List<String> {
            val tokens = ArrayList<String>()
            try {
                IKAnalyzer().use { analyzer ->
                    val tokenStream = analyzer.tokenStream("text", s)
                    tokenStream.reset()
                    while (tokenStream.incrementToken()) {
                        val charTermAttribute = tokenStream.getAttribute(CharTermAttribute::class.java)
                        val offsetAttribute = tokenStream.getAttribute(OffsetAttribute::class.java)
                        val len = offsetAttribute.endOffset() - offsetAttribute.startOffset()
                        val chars = CharArray(len)
                        System.arraycopy(charTermAttribute.buffer(), 0, chars, 0, len)
                        tokens.add(String(chars))
                    }
                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
            return tokens
        }
    }
}