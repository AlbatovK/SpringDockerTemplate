package com.albatros.springtemplate.presentation

import com.albatros.springtemplate.domain.model.User
import com.albatros.springtemplate.domain.model.sbp.SbpRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaResponseModel
import com.albatros.springtemplate.domain.service.TestService
import com.albatros.springtemplate.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController()
class RestController {

    @Autowired
    lateinit var service: UserService

    @Autowired
    lateinit var test: TestService

    @GetMapping("/get/all")
    fun getAll() = service.getAll()

    @PutMapping(value = ["/visa"], consumes = ["application/json"])
    fun interceptVisaTest(@RequestBody visa: VisaRequestModel): VisaResponseModel? {
        return test.getTestServiceVisaResponse(visa)?.apply {
            id = visa.id
        }
    }

    @PostMapping(
        value = ["/master/{id}"],
        consumes = [MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE],
        produces = [MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE]
    )
    fun interceptSbpTest(@PathVariable id: String, @RequestBody request: SbpRequestModel): String? {
        return test.getTestServiceSbpResponse(id, request)
    }

    @DeleteMapping(
        value = ["/"]
    )
    fun eraseCache() = test.eraseCache()

    @GetMapping("/delete/{userId}")
    fun deleteUser(@PathVariable userId: Long) = service.deleteById(userId)

    @PostMapping("/save", consumes = ["application/json"])
    fun saveUser(@RequestBody user: User) = service.saveUser(user)

    @GetMapping("/get/{userId}")
    fun getById(@PathVariable userId: Long) = service.getUserById(userId)
}
