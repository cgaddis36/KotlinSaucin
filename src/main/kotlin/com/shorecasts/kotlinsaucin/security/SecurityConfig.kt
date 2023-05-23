@file:Suppress("DEPRECATION")

package com.shorecasts.kotlinsaucin.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain

    @Configuration
    @EnableWebSecurity
    class SecurityConfig {
        @Value("\${auth0.audience}")
        private val audience: String = String()

        @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
        private val issuer: String = String()
        @Bean
        fun jwtDecoder(): JwtDecoder {
            val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoder
            val audienceValidator: OAuth2TokenValidator<Jwt> = AudienceValidator(audience)
            val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(issuer)
            val withAudience: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)
            jwtDecoder.setJwtValidator(withAudience)
            return jwtDecoder
        }
        @Bean
        @Throws(java.lang.Exception::class)
        fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
            http
                .authorizeRequests(
                    Customizer { authorizeRequests: ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry ->
                        authorizeRequests
                            .requestMatchers("/api/hotsauces/count").permitAll()
                            .requestMatchers("/api/hotsauces").authenticated()
                            .requestMatchers("/api/hotsauces/*").authenticated()
                    }
                )
                .oauth2ResourceServer { oauth2ResourceServer: OAuth2ResourceServerConfigurer<HttpSecurity?> ->
                    oauth2ResourceServer
                        .jwt(
                            Customizer { jwt: OAuth2ResourceServerConfigurer<HttpSecurity?>.JwtConfigurer ->
                                jwt
                                    .decoder(jwtDecoder())
                            }
                        )
                }
            return http.build()
        }


}


