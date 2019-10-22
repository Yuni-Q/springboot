package com.example.demo.user

import com.sun.istack.NotNull

data class ReqUserUpdateDto(
        val password: String,
        val name: String
) {}