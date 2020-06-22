package com.example.LoginRegestration.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.LoginRegestration.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(// securedEnabled = true,// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		String[] resources = new String[] { "/", "/app-assets/js", "/app-assets/images/favicon/",
				"/app-assets/js/scripts/", "/app-assets/js/custom", "/app-assets/js/scripts/", "/app-assets/images/",
				"/app-assets/css/", "/app-assets/css/themes/vertical-modern-menu-template/", "app-assets/css/pages/",
				"/app-assets/css/custom/", "/app-assets/vendors/", "/app-assets/vendors/animate-css/",
				"/app-assets/vendors/chartist-js/", "/app-assets/vendors/chartjs/",
				"/app-assets/vendors/data-tables/css/", "/app-assets/vendors/data-tables/extensions/responsive/css/",
				"/app-assets/vendors/data-tables/extensions/responsive/js/", "/app-assets/vendors/data-tables/images/",
				"/app-assets/vendors/data-tables/js/", "/app-assets/vendors/css/", "/app-assets/vendors/js/",
				"/app-assets/vendors/flag-icon/css/", "/app-assets/vendors/flag-icon/flags/",
				"/app-assets/vendors/flag-icon/1x1/", "/app-assets/vendors/flag-icon/4x3/",
				"/app-assets/vendors/formatter/", "/app-assets/vendors/fullcalender/css/",
				"/app-assets/vendors/fullcalender/js", "/app-assets/vendors/fullcalender/lib/",
				"/app-assets/vendors/hover-effects/", "/app-assets/vendors/ionRangeSlider/css/",
				"/app-assets/vendors/ionRangeSlider/img", "/app-assets/vendors/ionRangeSlider/js",
				"/app-assets/vendors/jquery-cookies/", "/app-assets/vendors/jquery-jvectormap/",
				"/app-assets/vendors/jquery.nestable/", "/app-assets/vendors/magnific-popup/",
				"/app-assets/vendors/materialize-stepper/", "/app-assets/vendors/noUiSlider/",
				"/app-assets/vendors/sortable/", "/app-assets/vendors/sparkline/", "/app-assets/vendors/sweetalert/",
				"/app-assets/vendors/tinymce/", "/app-assets/vendors/translator/", "/app-assets/vendors/waypoints/",
				"/appointment/**", "/appointment/aicon/", "/appointment/aicon/demo-files/", "/appointment/aicon/fonts",
				"/appointment/css/", "/google-assets/**",
				/*
				 * ===============================PUBLIC
				 * URL================================================================
				 */
				"/gms-dashboard/**", "/gms-datatable/**", "/gms-datatablecheck**", "/gms-form**", "/app-assets/**",
				"/app-assets", "/Auth", "/error", "/makeAppointment/**", "/getDateUpdate/**", "/AppointmentBooking/**",
				"/getUserStudentData", "/Acces","/api/auth/*" };

		http.antMatcher("/**").authorizeRequests().antMatchers(resources).permitAll()

				.antMatchers("/test", "/SalesPipline", "/NewClient", "/getClient/**", "/getClients**", "/getBranches",
						"/CreateReminder", "/GetPipline**", "/getRegion/**", "/ClientPipline/**", "/UpdateRMPipline**",
						"/DropClient/**", "/CloseClientDeal/**", "/CheckClientValidity/**", "/PiplineGridView",
						"/Client", "/ClientProfile", "/DashBoard", "/Management/**", "/AllDomainUsers",
						"/RelationManagers", "/SuspendGMSAccount", "/AddNewAccount", "/AddSupervisor",
						"/SuperviseAccount", "/PostSuperisorComment", "/DropedDeals", "/WonDeals", "/DormantDeals",
						"/RMProfile/**", "/PostUnSuperviseAccounts/**", "/GetDashBoardData/**", "/PostReassignAccounts",
						"/getSubSector/**", "/SupervisorPipline", "/SuspendeDeals", "/PostUnSuspenDeals",
						"/RegionalPipline", "/ShedularSettings", "/PostUpdateShedular", "/AllPipline/**",
						"/PartialDisbursmentDeals", "/PartialClientPiplineDisbursment/**",
						"/FullClientPiplineDisbursment/**", "/PostUpdateDisbursmentPipline/**", "/RegionalPiplineGrid",
						"/GetDomainUsersByBranchRegion/**", "/OtherSettings", "/PostUpdateUtilities/**",
						"/PostUpdateRM", "/ProductSetting", "/PostProductUpdate", "/UpdateSystemAdminsList",
						"/ClientsSettings", "/UploadCSVFile", "/TMTReport/**", "/TMTProductReport/**", "/Reports/**",
						"/GetBranchsByRegions/**", "/GetRmsByBranch/**", "/SectorvsStageReport/***",
						"/ChangeRMUnderSupervision/**","/api/auth/*"

				).permitAll().anyRequest().authenticated().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/Exit")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//				.antMatchers("/api/auth/**", "/Dashboard").permitAll().antMatchers("/api/test/**").permitAll().anyRequest()
//				.authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}