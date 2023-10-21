package com.albatros.springtemplate

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AtmRepository : JpaRepository<Atm, String>
