package com.example.demo.user

import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.stream.Collectors
import javax.transaction.Transactional



@Service
class UserService(val userRepository: UserRepository) {
    fun signUp(reqSignUpDto: ReqSignDto) {
        val user = userRepository.findByUserId(reqSignUpDto.userId)
        if (userRepository.findByUserId(reqSignUpDto.userId).isPresent) {
            throw ExceptionInInitializerError("userId가 존재합니다.")
        }
        val newUser: User = User(reqSignUpDto.userId, reqSignUpDto.password, reqSignUpDto.name)
        userRepository.save(newUser)
    }
    fun login(reqSignInDto: ReqSignInDto): String {
        val user: User = userRepository.findByUserIdAndPassword(reqSignInDto.userId, reqSignInDto.password).orElseThrow { RuntimeException() }
        return user.name
    }
    fun fetchUsers(): MutableList<ResUserListDto>? {
        return userRepository.findAll().stream()
                .map { user: User -> ResUserListDto(user.userId, user.name) }
                .collect(Collectors.toList())
    }
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
    @Transactional
    fun update(id: Long, reqEditDto: ReqEditDto): User {
        val user: User = userRepository.findById(id).orElseThrow { RuntimeException() }
        reqEditDto.name?.let {
            user.name = it
        }
        reqEditDto.password?.let {
            user.password = it
        }
        return user
    }
}