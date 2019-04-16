$(document).ready(function(){
	
	$.validator.addMethod("pwdd"
			              ,function(value,element){return this.optional(element)|| value.length>=6 && /\d/.test(value) && /[a-z]/i.test(value);}
                          ,'please enter 5 chars with digit'
                         ); 
	
	
	$("#myform").validate({
		
		rules:
		{
			
			userName:
				{
				  required:true, 
				  lettersonly:true,
				  minlength:5,
				  maxlength:10
				  },
			
			userEmail:
				{
				  required:true, 
				  email:true
				},
			userPwd:{
					  required:true,
					  pwdd:true
					 }
		},
		messages:
			{
			
		    userName:
		    	 {
		    	  lettersonly:'enter chars only',
		    	  maxlength:'max contain 10 chars only',
		    	  minlength:'atleast 5 characters'
		    	 }
			}
	});
});