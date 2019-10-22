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
    fun login(reqSignInDto: ReqSignInDto): String {
        val user: User = userRepository.findByUserIdAndPassword(reqSignInDto.userId, reqSignInDto.password).orElseThrow { RuntimeException() }
        return user.name
    }
    fun fetchUsers(): MutableList<User> {
        val users: MutableList<User> = userRepository.findAll();
        return users;
    }
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
    fun update(id: Long, reqUserUpdateDto: ReqUserUpdateDto): User {
        val user: User = userRepository.findById(id).orElseThrow { RuntimeException() }
        user.password = reqUserUpdateDto.password
        user.name = reqUserUpdateDto.name
        return user
    }
}