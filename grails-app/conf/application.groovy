

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'hibernate.error.example.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'hibernate.error.example.UserRole'
grails.plugin.springsecurity.authority.className = 'hibernate.error.example.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[
		pattern: '/**',
		filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
	]
]

grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.login.endpointUrl = '/auth/login'
grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true