/**
 * 
 */

$(function(){	//$(document).on("load|ready",function(){});
	let selectValue = $(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	
 	$(calForm).on("submit", function(event){
		event.preventDefault();
		
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();	// query String
		
		let settings = {
			url : url,
			method : method,
			data : data,
			dataType : "html", //Accept request header : Content-Type response header
			success : function(resp) {
				$('#resultArea').html(resp);
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("errer : ", error);
			}
		};
		$.ajax(settings);
	})
});

 

/*  leftOp = $('input[name=leftOp]').val();
	rightOp = $('input[name=rightOp]').val();
	operator = $('select[name=operator]').val();
	
	console.log(leftOp)
	console.log(rightOp)
	console.log(operator)
	$('#calForm').on("submit",function(event){
		event.preventDefault();
		
		let settings = {
				url : "<%=request.getContextPath()%>/calculate/case2",
				method : "post",
				data : {
					"leftOp" : leftOp,
					"rightOp" : rightOp,
					"operator" : operator
				},
	 			dataType : "html", //Accept request header : Content-Type response header
				success : function(resp) {
					$('#resultArea').html(resp);
				},
				error : function(jqXhr, status, error) {
					console.log("jqXhr : ", jqXhr);
					console.log("status : ", status);
					console.log("errer : ", error);
				}
			};
			
			$.ajax(settings);
			
		return false;
	}); */

