package com.albatros.springtemplate.domain.repository

import com.albatros.springtemplate.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>