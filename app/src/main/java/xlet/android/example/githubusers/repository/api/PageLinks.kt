package xlet.android.example.githubusers.repository.api

import retrofit2.Response


data class PageLinks(
    val first: String?,
    val last: String?,
    val next: String?,
    val prev: String?
) {
    companion object {
        private const val DELIM_LINKS = ","
        private const val DELIM_LINK_PARAM = ";"
        private const val HEADER_LINK = "Link"
        private const val HEADER_NEXT = "X-Next"
        private const val HEADER_LAST = "X-Last"
        private const val META_REL = "rel"
        private const val META_LAST = "last"
        private const val META_NEXT = "next"
        private const val META_FIRST = "first"
        private const val META_PREV = "prev"

        // Ref: https://github.com/eclipse/egit-github/blob/master/org.eclipse.egit.github.core/src/org/eclipse/egit/github/core/client/PageLinks.java
        fun <T> parsePageLinks(response: Response<T>): PageLinks {
            var first: String? = null
            var last: String? = null
            var next: String? = null
            var prev: String? = null

            val linkHeader = response.headers()[HEADER_LINK]
            if (linkHeader != null) {
                val links = linkHeader.split(DELIM_LINKS)
                for (link in links) {
                    val segments = link.split(DELIM_LINK_PARAM)
                    if (segments.size < 2) continue
                    var linkPart = segments[0].trim { it <= ' ' }
                    if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                        continue
                    linkPart = linkPart.substring(1, linkPart.length - 1)
                    for (i in 1 until segments.size) {
                        val rel = segments[i].trim { it <= ' ' }.split("=")
                        if (rel.size < 2 || META_REL != rel[0]) continue
                        var relValue = rel[1]
                        if (relValue.startsWith("\"") && relValue.endsWith("\""))
                            relValue = relValue.substring(1, relValue.length - 1)

                        when (relValue) {
                            META_FIRST -> first = linkPart
                            META_LAST -> last = linkPart
                            META_NEXT -> next = linkPart
                            META_PREV -> prev = linkPart
                        }
                    }
                }
            } else {
                next = response.headers()[HEADER_NEXT]
                last = response.headers()[HEADER_LAST]
            }
            return PageLinks(first, last, next, prev)
        }
    }
}