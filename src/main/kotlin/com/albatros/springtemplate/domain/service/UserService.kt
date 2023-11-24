package com.albatros.springtemplate.domain.service

import com.albatros.springtemplate.domain.model.User
import com.albatros.springtemplate.domain.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    @Caching(
        put = [CachePut(value = ["User"], key = "#user.id")],
        evict = [CacheEvict(value = ["Users"], allEntries = true)]
    )
    fun saveUser(user: User) = repository.save(user)

    @Cacheable(value = ["User"], key = "#userId")
    fun getUserById(userId: Long) = repository.findById(userId).orElseThrow()

    @Cacheable(value = ["Users"])
    fun getAll() = repository.findAll()

    @Caching(
        evict = [
            CacheEvict(value = ["Users"], allEntries = true),
            CacheEvict(value = ["User"], key = "#userId")
        ]
    )
    fun deleteById(userId: Long) = repository.deleteById(userId)

    @CachePut(value = ["User"], key = "#userId")
    fun updateUser(user: User, userId: Long): User {
        val found = repository.findById(userId).orElseThrow()
        found.name = user.name
        return repository.save(found)
    }
}
