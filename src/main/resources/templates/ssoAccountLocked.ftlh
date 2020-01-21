<!DOCTYPE html>
<html lang="en">
<head>
    <title>SSO</title>
</head>
<body>
<div>

        <fieldset>
            <legend>Account Locked</legend>
            
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

            <div>
            	please contact administrator
            </div>            
            
        </fieldset>

</div>
</body>
</html>