package com.hoodad.test.data.models.responses

import com.google.gson.annotations.SerializedName

class Result {
    var BookDuration: Int? = null
    var BookDurationTitle: String? = null
    var EpisodeCount: Int? = null
    var Episodes: ArrayList<Episod> = ArrayList()

    @SerializedName("result")
    var suggestions: ArrayList<Suggestion> = ArrayList()
    var ProducerList: ArrayList<Suggestion> = ArrayList()

    var TypeTitle: String? = null
    var Title: String? = null
    var SubTitle: String? = null
    var BookTitle: String? = null
    var PhotoUrl: String? = null
    var Content: String? = null
    var SyncUrl: String? = null
    var SharingLink: String? = null
    var SharingText: String? = null
    var AverageRate: Float? = null
    var Reviews: ArrayList<Review> = ArrayList()
    var PublisherName: String? = null
    var Language: String? = null
    var Genres: ArrayList<Genre> = ArrayList()
    var SizeDescription: String? = null
    var Price: Price? = null
    override fun toString(): String {
        return "Result(EpisodeCount=$EpisodeCount, Title=$Title, BookTitle=$BookTitle)"
    }


}