package com.example.demo.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class UserController (val userService: UserService){

    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid reqSignUpDto: ReqSignDto): ResponseEntity<Response<String>> {
        userService.signUp(reqSignUpDto)
        val response = Response(200, null,"OK", "생성 되었습니다.")
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
    @PostMapping("/login")
    fun login(@RequestBody @Valid reqSignInDto: ReqSignInDto): ResponseEntity<Response<String>> {
        val name = userService.login(reqSignInDto)
        val response = Response(200, null, "OK", name)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
    @GetMapping("/users")
    fun users(): ResponseEntity<Response<MutableList<ResUserListDto>>> {
        val users: MutableList<ResUserListDto>? = userService.fetchUsers()
        val response = Response(200, null, "OK", users)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Response<String>> {
        userService.deleteUser(id)
        val response = Response(200, null, "OK", "삭제되었습니다")
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
    @PutMapping("/users/{id}")
    fun editUser(@RequestBody reqEditDto: ReqEditDto, @PathVariable id: Long): ResponseEntity<Response<User>> {
        val user: User = userService.update(id, reqEditDto)
        val response = Response(201, null, "OK", user)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}