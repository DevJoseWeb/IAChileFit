package br.com.fiap.am.coopfit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.repositories.PessoaRepository;
import br.com.fiap.am.coopfit.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private PessoaRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa pessoa= repo.findByEmail(email);
		if (pessoa == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(pessoa.getId(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getPerfis());
	}

}
