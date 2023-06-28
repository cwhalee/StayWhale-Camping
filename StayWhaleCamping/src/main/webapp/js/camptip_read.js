
      
   	$(function(){
		$("#revise_Cfm").hide();
		$("#revise_Cencel").hide();
		
		
		$('#revise').click(function(){
		
			$("#revise").hide();
			$("#delete").hide();
			$("#revise_Cfm").show();
			$("#revise_Cencel").show();
			$("#title").removeAttr("readonly");
			$("#title").css('border','2px solid black');
			$("#body").removeAttr("readonly");
			$("#body").css('border','2px solid black');
		});
	});
 