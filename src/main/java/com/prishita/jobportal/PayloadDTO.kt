package com.prishita.jobportal

import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal

data class UserPayload(
    val name: String?,
    val username: String?,
    val password: String?,
    val email: String?,
    val phoneNo: String?,
    val address: String?,
    val gender: String?,
    val currentLocation: String?,
    val annualSalary: BigDecimal?,
    val currentIndustry: String?,
    val quali: String?,
    val profile : String?,
    val resume: MultipartFile?,
    val company: String?
)
