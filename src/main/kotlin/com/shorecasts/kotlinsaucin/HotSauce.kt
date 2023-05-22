package com.shorecasts.kotlinsaucin

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class HotSauce {
    @Id @GeneratedValue(strategy = GenerationType.AUOT)

    val id: Long = 0
    var brandName: String = ""
    var sauceName: String = ""

    @Lob
    var description: String = ""

    @Lob
    var url: String = ""
    var heat: Int = 0

}