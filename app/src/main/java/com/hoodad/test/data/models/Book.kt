package com.hoodad.test.data.models

import com.hoodad.test.data.models.responses.Price

class Book {
    var id : Int = 0
    var Type : String? = null
    var TypeTitle : String? = null
    var Title : String? = null
    var BookTitle : String? = null
    var PhotoUrl : String? = null
    var Content : String? = null
    var SyncUrl : String? = null
    var SharingLink : String? = null
    var SharingText : String? = null
    var Price : Price? = null
}