package com.dam.SpringCRUD.paquetVeterinari.controladors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author fta
 */

/*Classe de configuració de Spring Security per configurar l'accés d'usuaris (autenticació).
 *Aquesta classe ha d'extendre de la classe WebSecurityConfigurerAdapter de Spring Security per poder
 *autenticar els usuaaris.
 */
@Configuration //Indica al sistema que és una classe de configuració
@EnableWebSecurity //Habilita la seguretat web
public class ConfiguracioAutenticacio {

    @Autowired
    private UserDetailsService userDetailsService; //Objecte per recuperar l'usuari

    /*AUTENTICACIÓ*/
    /*Injectem mitjançant @Autowired, els mètodes de la classe AuthenticationManagerBuilder. Mitjançant
     *aquesta classe cridarem al mètode userDetailsService de la classe AuthenticationManagerBuilder què és el mètode que
     *realitzarà l'autenticació. Per parm̀etre el sistema li passa l'usuari introduit en el formulari d'autenticació.
     *Aquest usuari ens el retorna el mètode loadUserByUsername implementat en UsuariService.
     *
     *Cridem al mètode passwordEncoder passant-li com a paràmetre un objecte de tipus BCryptPasswordEncoder()
     *per encriptar el password introduït per l'usuari en el moment d'autenticar-se i comparar-lo
     *amb l'encriptació del password guardat a la BBDD corresponent a l'username introduït també en l'autenticació.
     */
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    /*AUTORITZACIÓ*/
    /*Utilitzem el mètode filterChain per configurar l'accés dels nostre usuaris a l'aplicació, segons
     *els seus rols, el que s'anomena autoritzar.
     *Passem com a paràmetre un objecte de la classe HttpSecurity de Spring Security que
     *és el que ens permetrà cridar als mètodes per configurar les restriccions d'accés a la nostra aplicació.
    */

   
    @Bean //L'indica al sistema que el mètode és un Bean, en aquest cas perquè crea un objecte de la classe HttpSecurity
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests((requests) -> requests
        	 //En el nostre cas el mètode hasAnyAuthority fa el mateix que HasAnyRoles, o hasAuthority el mateix que hasRol, però en aquesta nova versió per autoritzar els usuaris, els mètodes
        	 //dels rols, normalment donen problemes, els Authority, no.
                .requestMatchers("/iniciGossos/**", "/enviar/**", "/eliminar", "guardarGos/**")
                .hasAnyAuthority("veterinari", "pacient") //URL iniciGossos on pot accedir el rol de veterinari o pacient
                .anyRequest().authenticated() //Qualsevol altre sol.licitud que no coincideixi amb les regles anteriors cal autenticació
                )
                
                .formLogin((form) -> form //Objecte que representa el formulari de login personalitzat que utilitzarem
                .loginPage("/login")  //Pàgina on es troba el formulari per fer login personalitzat
                .permitAll() //Permet acceddir a tothom
                )
                
                .exceptionHandling((exception) -> exception //Quan es produeix una excepcció 403, accés denegat, mostrem el nostre missatge
                .accessDeniedPage("/errors/error403"))
                .build();

    }

}
