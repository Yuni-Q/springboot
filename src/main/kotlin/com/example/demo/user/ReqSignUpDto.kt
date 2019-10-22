package com.example.demo.user

import com.sun.istack.NotNull

data class ReqSignDto(
        @NotNull
        val userId: String,
        @NotNull
        val password: String,
        @NotNull
        val name: String
) {}