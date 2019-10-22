package com.example.demo.user

import org.springframework.stereotype.Service
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
    fun deleteUser(id: String) {
        val id: Long = id.toLong()
        return userRepository.deleteById(id)
    }
    fun update(id: String, reqSignUpDto: ReqSignDto): User {
        val id: Long = id.toLong()
        val user: User = userRepository.findById(id).get();
        user.userId = reqSignUpDto.userId
        user.password = reqSignUpDto.password
        user.name = reqSignUpDto.name
        return user
    }
}