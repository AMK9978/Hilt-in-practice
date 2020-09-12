package com.hoodad.test.data.models.responses

import com.hoodad.test.data.models.Book

class Suggestion {
    var id : Int = 0
    var title : String? = null
    var items: List<Book> = ArrayList()
}