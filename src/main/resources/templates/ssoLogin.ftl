<!DOCTYPE html>
<html lang="en">
<head>
    <title>SSO</title>
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.getContextPath()}/webjars/bootstrap/css/bootstrap.min.css" />
	<script type="text/javascript" src="${springMacroRequestContext.getContextPath()}/webjars/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${springMacroRequestContext.getContextPath()}/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
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
    
        <div id="loginbox" style="margin-top:30px;" class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">SSO Sign In</div>	                        
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                      
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username">                                        
                            </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                            </div>
                                    
                                
                                <div style="margin-top:10px" class="col-md-12 col-lg-12 col-sm-12">                                
                                    <!-- Button -->									   
                                    <div class="controls">                                      
                                      <input class="btn btn-primary pull-right" type="submit" value="Login" />                                      
                                    </div>
                                    
                                </div>

                        	</div>
                        	                     
                    </div>  
        </div>
    </div>
            <div style='visibility: hidden'>springMacroRequestContext.getRequestUri(): ${springMacroRequestContext.getRequestUri()}</div>
            <div style='visibility: hidden'>springMacroRequestContext.getContextPath(): ${springMacroRequestContext.getContextPath()}</div>

    

    </form>
</div>
</body>
</html>