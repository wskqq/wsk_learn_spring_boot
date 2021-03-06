/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.graphql.security;

import graphql.GraphQL;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.graphql.servlet.GraphQlWebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.security.SecurityContextThreadLocalAccessor;
import org.springframework.graphql.security.SecurityDataFetcherExceptionResolver;
import org.springframework.graphql.web.webmvc.GraphQlHttpHandler;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for enabling Security support for
 * Spring GraphQL with MVC.
 *
 * @author Brian Clozel
 * @since 2.7.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({ GraphQL.class, GraphQlHttpHandler.class, EnableWebSecurity.class })
@ConditionalOnBean(GraphQlHttpHandler.class)
@AutoConfigureAfter(GraphQlWebMvcAutoConfiguration.class)
public class GraphQlWebMvcSecurityAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public SecurityDataFetcherExceptionResolver securityDataFetcherExceptionResolver() {
		return new SecurityDataFetcherExceptionResolver();
	}

	@Bean
	@ConditionalOnMissingBean
	public SecurityContextThreadLocalAccessor securityContextThreadLocalAccessor() {
		return new SecurityContextThreadLocalAccessor();
	}

}
