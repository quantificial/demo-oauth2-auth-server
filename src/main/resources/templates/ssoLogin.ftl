<!DOCTYPE html>
<html lang="en">
<head>
    <title>SSO</title>
</head>
<body>
<div>
    <form name="f" action="${springMacroRequestContext.getContextPath()}/ssoLogin/process" method="post">
        <fieldset>
            <legend>Please Login</legend>
            
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
            <div>springMacroRequestContext.getRequestUri(): ${springMacroRequestContext.getRequestUri()}</div>
            <div>springMacroRequestContext.getContextPath(): ${springMacroRequestContext.getContextPath()}</div>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
            <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </fieldset>
    </form>
</div>
</body>
</html>