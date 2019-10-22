package com.example.demo.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserIdAndPassword(userId: String, password: String): Optional<User>
    fun findByUserId(userId: String): Optional<User>
}