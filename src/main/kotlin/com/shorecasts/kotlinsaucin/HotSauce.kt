package com.shorecasts.kotlinsaucin

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class HotSauce(
            @Id @GeneratedValue(strategy = GenerationType.AUTO)
            val id: Long = 0,
            var sauceName: String = "",
            var brandName: String = "",
            var description: String = "",
            var url: String = "",
            var heat: Int = 0,
           ) {
}