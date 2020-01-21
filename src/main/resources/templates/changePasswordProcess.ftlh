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
   
<form name="f" action="${springMacroRequestContext.getContextPath()}/changePassword/process" method="post">
		<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
    <div class="container">
        <div class="card card-container">
                        
            <p id="profile-name" class="profile-name-card"></p>
            
            
            ${msg}
            
            
            <label></label>

        </div><!-- /card-container -->
    </div><!-- /container -->
    
   
            <div style='visibility: hidden'>springMacroRequestContext.getRequestUri(): ${springMacroRequestContext.getRequestUri()}</div>
            <div style='visibility: hidden'>springMacroRequestContext.getContextPath(): ${springMacroRequestContext.getContextPath()}</div>

    

    </form>
</div>
</body>
</html>