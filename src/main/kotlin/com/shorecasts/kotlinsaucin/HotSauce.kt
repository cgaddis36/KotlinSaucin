package com.shorecasts.kotlinsaucin

import jakarta.persistence.*

@Entity
class HotSauce {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    val id: Long = 0
    var brandName: String = ""
    var sauceName: String = ""

    @Lob
    var description: String = ""

    @Lob
    var url: String = ""
    var heat: Int = 0

}