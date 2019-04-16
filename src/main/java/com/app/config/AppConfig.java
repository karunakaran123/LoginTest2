package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc

@ComponentScan(basePackages="com.app")
@PropertySource("classpath:db.properties")
public class AppConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DriverManagerDataSource dmds() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("dc"));
		ds.setUrl(env.getProperty("url"));
		ds.setUsername(env.getProperty("un"));
		ds.setPassword(env.getProperty("pwd"));
		return ds;
	}
	@Bean
	public JdbcTemplate jdbc() {
		JdbcTemplate jt=new JdbcTemplate();
		jt.setDataSource(dmds());
		return jt;
	}
	  @Autowired
	  private ApplicationContext applicationContext;
	   @Bean
	   public SpringResourceTemplateResolver templateResolver() {
	      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	      templateResolver.setApplicationContext(applicationContext);
	      templateResolver.setPrefix("/WEB-INF/views/");
	      templateResolver.setSuffix(".html");
	      return templateResolver;
	   }
	   @Bean
	   public SpringTemplateEngine templateEngine() {
	      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	      templateEngine.setTemplateResolver(templateResolver());
	      return templateEngine;
	   }

	
	  @Override public void configureViewResolvers(ViewResolverRegistry registry) {
	  ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	  resolver.setTemplateEngine(templateEngine());
	  registry.viewResolver(resolver); }
	 
	  
	  @Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
		}
}
