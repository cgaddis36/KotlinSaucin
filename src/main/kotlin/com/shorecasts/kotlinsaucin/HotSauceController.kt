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
    @GetMapping("/count")
    fun getCount(): ResponseEntity<Long> = ResponseEntity(hotSauceRepository.count(),
        HttpStatus.OK)
    @GetMapping("/{id}")
    fun getHotSauce(@PathVariable id: Long): ResponseEntity<Optional<HotSauce>> {
        if (hotSauceRepository.existsById(id)) {
            return ResponseEntity(hotSauceRepository.findById(id), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun createHotSauce(@RequestBody hotSauce: HotSauce): ResponseEntity<HotSauce> {
        return ResponseEntity(hotSauceRepository.save(hotSauce), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody sauceChanges: HotSauce): ResponseEntity<HotSauce?> {
        if (hotSauceRepository.existsById(id)) {
            val ogSauce = hotSauceRepository.findById(id).get()
            val newerSauce = HotSauce(
                id = id,
                brandName = if (sauceChanges.brandName != "") sauceChanges.brandName else ogSauce.brandName,
                sauceName = if (sauceChanges.sauceName != "") sauceChanges.sauceName else ogSauce.sauceName,
                description = if (sauceChanges.description != "") sauceChanges.description else ogSauce.description,
                url = if (sauceChanges.url != "") sauceChanges.url else ogSauce.url,
                heat = if (sauceChanges.heat != 0) sauceChanges.heat else ogSauce.heat,
                )
            return ResponseEntity(hotSauceRepository.save(newerSauce), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteHotSauce(@PathVariable id: Long): ResponseEntity<HotSauce?> {
        if (hotSauceRepository.existsById(id)) {
            hotSauceRepository.deleteById(id)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}