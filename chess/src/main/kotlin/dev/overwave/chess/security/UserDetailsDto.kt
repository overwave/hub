package dev.overwave.chess.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsDto(
    val login: String,
    private val password: String,
    private val authorities: List<GrantedAuthority>,
) : UserDetails {
    override fun getUsername() = login
    override fun getPassword() = password
    override fun getAuthorities() = authorities
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}