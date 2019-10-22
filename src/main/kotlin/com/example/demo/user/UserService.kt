package com.example.demo.user

import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    fun signUp(reqSignUpDto: ReqSignDto) {
        val user: User = User(reqSignUpDto.userId, reqSignUpDto.password, reqSignUpDto.name)
        userRepository.save(user)
    }
    fun fetchUsers(): MutableList<User> {
        val users: MutableList<User> = userRepository.findAll();
        return users;
    }
    fun deleteUser(id: Long) {
        return userRepository.deleteById(id)
    }
    fun update(id: Long, reqSignUpDto: ReqSignDto): User {
        val user: User = userRepository.findById(id).orElseThrow { RuntimeException() }
        user.userId = reqSignUpDto.userId
        user.password = reqSignUpDto.password
        user.name = reqSignUpDto.name
        return user
    }
}