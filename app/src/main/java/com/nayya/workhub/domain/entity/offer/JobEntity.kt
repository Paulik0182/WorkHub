package com.nayya.workhub.domain.entity.offer

data class JobEntity(
    val datePosted: String,
    val employmentType: String,
    val experienceRequirements: String,
    val hiringOrganization: String,
    val industry: String,
    val jobBenefits: String,
    val jobLocation: JobLocation,
    val responsibilities: String,
    val title: String,
    val validThrough: String
)