package com.example.demo.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class UserController (val userService: UserService){

    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid reqSignUpDto: ReqSignDto): ResponseEntity<String>  {
        userService.signUp(reqSignUpDto)
        return ResponseEntity.status(HttpStatus.OK).body("생성 되었습니다")
    }
    @PostMapping("/login")
    fun login(@RequestBody @Valid reqSignInDto: ReqSignInDto): String {
        return userService.login(reqSignInDto)
    }
    @GetMapping("/")
    fun users(): ResponseEntity<MutableList<User>> {
        val users: MutableList<User> = userService.fetchUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        userService.deleteUser(id)
        return ResponseEntity.status(HttpStatus.OK).body("삭제 되었습니다")
    }
    @PutMapping("/{id}")
    fun editUser(@RequestBody @Valid reqUserUpdateDto: ReqUserUpdateDto, @PathVariable id: Long): ResponseEntity<User> {
        val user: User = userService.update(id, reqUserUpdateDto)
        return ResponseEntity.status(HttpStatus.OK).body(user)
    }
}