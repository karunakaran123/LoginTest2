 $(document).ready(function(){
		 

		 $("#myform").submit(function(event){
			 event.preventDefault();
			  var data=$(this);
			 if(!data.valid()){
				 $("#userName-error").css("color","red")
				 $("#userPwd-error").css("color","red")
				 return false;
			 }  
			  ajaxPost();
			  
		     });
		 function ajaxPost(){
			 var form_data = {
		                      userName:$("#userName").val(),
			                  userPwd:$("#userPwd").val()
			                 }
			 $.ajax({
				 type:'POST',
				 url:'getUser',
				 data:JSON.stringify(form_data),
			     dataType:'json',
			     contentType:'application/json',
			 success:function(response)
			  {
				 if(response){
					 $('.successdiv pre code').text("User Logged In successfully");
					 $('.successdiv').show();
				 }else{
					 $('.successdiv pre code').text("Login Failed");
					 $('.successdiv').show();
				 }
			  },
			 error:function(e)
			 {
				 $('.successdiv').html("Error.....");
				 console.log("error...")
			 }	 
			
			 });
		 }
		 
		});