package com.hoodad.test.data.models.responses

import com.google.gson.annotations.SerializedName

class Result {
    private var BookDuration: Int? = null
    private var BookDurationTitle: String? = null
    private var EpisodeCount: Int? = null
    private var Episods: ArrayList<Episod> = ArrayList()

    @SerializedName("result")
    private var suggestions: ArrayList<Suggestion> = ArrayList()
    private var ProducerList: ArrayList<Suggestion> = ArrayList()

    private var TypeTitle: String? = null
    private var Title: String? = null
    private var SubTitle: String? = null
    private var BookTitle: String? = null
    private var PhotoUrl: String? = null
    private var Content: String? = null
    private var SyncUrl: String? = null
    private var SharingLink: String? = null
    private var SharingText: String? = null
    private var AverageRate: Float? = null
    private var Reviews: ArrayList<Review> = ArrayList()
    private var PublisherName: String? = null
    private var Language: String? = null
    private var Genres: ArrayList<Genre> = ArrayList()
    private var SizeDescription: Int? = null
    private var Price: Price? = null


}