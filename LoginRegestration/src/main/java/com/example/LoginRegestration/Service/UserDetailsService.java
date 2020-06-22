package com.example.LoginRegestration.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {

	 UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
