package com.shorecasts.kotlinsaucin

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/hotsauces")

class HotSauceController(private val hotSauceRepository: HotSauceRepository) {

    @GetMapping("")

    fun getAll(@RequestParam(value = "brandname", required = false, defaultValue = "") brandNameFilter: String,
               @RequestParam(value = "saucename", required = false, defaultValue = "") sauceNameFilter: String,
               @RequestParam(value = "desc", required = false, defaultValue = "") descFilter: String,
               @RequestParam(value = "minheat", required = false, defaultValue = "") minHeat: String,
               @RequestParam(value = "maxheat", required = false, defaultValue = "") maxHeat: String): ResponseEntity<List<HotSauce>> {
        val maxScoville = 3_000_000
        val minScoville = 0
        val minHeatFilter = if (minHeat != "") minHeat.toInt() else minScoville
        val maxHeatFilter = if (maxHeat != "") maxHeat.toInt() else maxScoville
        return ResponseEntity(hotSauceRepository.findAll()
            .filter { it.brandName.contains(brandNameFilter, true) }
            .filter { it.sauceName.contains(sauceNameFilter, true) }
            .filter { it.description.contains(descFilter, true) }
            .filter { it.heat >= minHeatFilter }
            .filter { it.heat <= maxHeatFilter },
            HttpStatus.OK
        )
    }
}