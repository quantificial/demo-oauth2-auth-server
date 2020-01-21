<!DOCTYPE html>
<html lang="en">
<head>
    <title>SSO</title>
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.getContextPath()}/webjars/bootstrap/css/bootstrap.min.css" />
	<script type="text/javascript" src="${springMacroRequestContext.getContextPath()}/webjars/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${springMacroRequestContext.getContextPath()}/webjars/bootstrap/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
	
	<link rel="stylesheet" href="${springMacroRequestContext.getContextPath()}/css/login.css" />
</head>
<style>

</style>


<body>
   
<form name="f" action="${springMacroRequestContext.getContextPath()}/ssoLogin/process" method="post">
		<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
            <#assign abc = "this is abcdefg">
            <#if RequestParameters.error??>
            <div class="alert alert-error">
                Invalid username and password.
            </div>
            <#elseif RequestParameters.logout??>
            <div class="alert alert-success">
                You have been logged out.
            </div>
            </#if>
            
            <#if Session.message??>
            <div class="alert alert-success">
            	 ${Session.message}
            </div>
            </#if>
    <div class="container">
        <div class="card card-container">
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin">
                <span id="reauth-email" class="reauth-email"></span>

	            <input type="text" id="username" name="username" class="form-control" placeholder="username" required autofocus>				                
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                <div>
                	<label></label>
                </div>
                <!--
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                -->
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
            </form><!-- /form -->
            <label></label>
            <a href="${springMacroRequestContext.getContextPath()}/changePassword" class="forgot-password">
                change password?
            </a>
        </div><!-- /card-container -->
    </div><!-- /container -->
    
   
            <div style='visibility: hidden'>springMacroRequestContext.getRequestUri(): ${springMacroRequestContext.getRequestUri()}</div>
            <div style='visibility: hidden'>springMacroRequestContext.getContextPath(): ${springMacroRequestContext.getContextPath()}</div>

    

    </form>
</div>
</body>
</html>