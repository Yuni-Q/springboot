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
    @GetMapping("/")
    fun users(): ResponseEntity<MutableList<User>> {
        val user = userService.fetchUsers()
        return ResponseEntity.status(HttpStatus.OK).body(user)
    }
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<String> {
        userService.deleteUser(id)
        return ResponseEntity.status(HttpStatus.OK).body("삭제 되었습니다")
    }
    @PutMapping("/{id}")
    fun editUser(@RequestBody @Valid reqSignUpDto: ReqSignDto, @PathVariable id: String): ResponseEntity<User> {
        val user = userService.update(id, reqSignUpDto)
        return ResponseEntity.status(HttpStatus.OK).body(user)
    }
}