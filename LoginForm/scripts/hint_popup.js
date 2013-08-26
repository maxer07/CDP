$(document).ready(function() {
  
  $('.login_form input:required').focusout(function() {
		var a = $(this).val();
		if (a.length == 0) {
			$(this).removeClass("valid_input").addClass("invalid_input");
			$('.'+ $(this).attr('name') +'_req_hint').fadeIn("slow");

		}
		else {
			$('.'+ $(this).attr('name') +'_req_hint').fadeOut("slow");
			$(this).removeClass("invalid_input").addClass("valid_input");
			

		}
}); 

  $('.login_form input').focusout(function() {
		//check on pattern attribute in input
		if(!$(this).prop('pattern'))
            return;
		if(!$(this).val().match($(this).prop('pattern'))) {
			$('.'+ $(this).attr('name') +'_pattern_hint').fadeIn("slow");
			$(this).removeClass("valid_input").addClass("invalid_input");
		}
		else {
			$('.'+ $(this).attr('name') +'_pattern_hint').fadeOut("slow");
			$(this).removeClass("invalid_input").addClass("valid_input");
		}
            
  
}); 

		
		
  
});