package com.mcsjavaprojects.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import com.mcsjavaprojects.converter.RoleToUserProfileConverter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mcsjavaprojects")
public class AppConfig extends WebMvcConfigurerAdapter{
	
	
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
		
	/**
     * Configure ViewResolvers to deliver preferred views.
     */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(2);
		registry.viewResolver(viewResolver);
	}	
	 	 
	@Bean
    public JasperReportsViewResolver getJasperReportsViewResolver() {
        JasperReportsViewResolver viewresolver = new JasperReportsViewResolver();
        viewresolver.setPrefix("classpath:/reports/");
        viewresolver.setSuffix(".jrxml");       
        viewresolver.setReportDataKey("datasource");
        viewresolver.setViewNames("*_report");
        viewresolver.setViewClass(JasperReportsMultiFormatView.class);       
        viewresolver.setOrder(1);
        return viewresolver;
    }

	/**
     * Configure ResourceHandlers to serve static resources like Javascript, CSS, etc.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    /**
     * Configure Converter to be used.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }
        
    
    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
            
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
    
   
    
}


