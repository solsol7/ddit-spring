/**
 * 문서가 준비되면 실행되는 함수.
 */
$(function() {
	const cPath = $(this.body).data("contextPath");

	let makeTrTag = function(memoVO) {
		let trTag = `
         <tr data-code="${memoVO.code}">
			<td>${memoVO.code}</td>
            <td>${memoVO.writer}</td>
            <td>${memoVO.email}</td>
            <td>${memoVO.wrdate}</td>
			<td>${memoVO.content}</td>
            <td><input type="button" value="삭제" class="delBtn" /></td>
			<td><input type="button" value="수정" class="updateBtn" data-bs-toggle="modal" data-bs-target="#updateModal" /></td>
         </tr>
      `;
		return trTag;
	};


	const baseUrl = `${cPath}/memo`;

	$.getJSON(baseUrl, function(resp) {
		let memoList = resp.memoList;
		console.log(resp);
		trTags = "";

		if (resp.memoList?.length > 0) {
			$.each(resp.memoList, function() {
				trTags += makeTrTag(this);
			});
		} else {
			trTags += `
            <tr>
               <td colspan='4'>메모 없음</td>
            </tr>
         `;
		}// if~else end

		$("#listBody").html(trTags);
	});// getJSON end


	$("#memoForm").on("submit", function(event) {
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serializeJSON();
		let json = JSON.stringify(data);
		console.log("click");
		let settings = {
			url: baseUrl,
			method: method,
			data: json,
			headers: {
				"Content-Type": "application/json;charset=UTF-8"
			},
			dataType: "json"
		};


		$.ajax(settings)
			.done(function(resp) { 
				if (resp.success) {
					let trTag = makeTrTag(resp.originalData)
					$(listBody).prepend(trTag);
					memoForm.reset();
				} else {
					alert(resp.message);
				}
			});

		return false;
	});


	$(listBody).on("click", ".delBtn", function() {
		let flag = confirm("삭제하시겠습니까??");
		if (!flag) return false;


		let memoTr = $(this).parents("tr:first");
		let $memoTr = $(memoTr);
		let code = $(memoTr).data("code");
		let url = `${baseUrl}/${code}`;
		let settings = {

			url: url,
			method: "delete",
			dataType: "json"

		};
		$.ajax(settings)
			.done(function(resp) {
				if (resp.success) {
					$memoTr.remove();
				} else {
					alert(resp.message);
				}
			});
	});
	
   $("#modalForm").on("show.bs.modal", function (event) {
        let modalBtn = $(event.relatedTarget);
        let memoTr = modalBtn.closest('tr');
        let code = memoTr.data('code');
        let writer = memoTr.data('writer');
        let email = memoTr.data('email');
        let content = memoTr.data('content');

        $('#code').val(code);
        $('#writer').val(writer);
        $('#email').val(email);
        $('#content').val(content);
    });
   
   
   $("#modBtn").on("click", function(){
      console.log(this);
      let flag = confirm("수정하시겠습니까?");
      if(!flag) return false;

      let data = $("#updateForm").serializeJSON();
      let json = JSON.stringify(data);
      
      let settings = {
         url : baseUrl,
         method : "put",
         data : json,
         contentType : 'application/json;charset=utf-8',
         dataType : 'json'
      };
      
      $.ajax(settings)
         .done(function(resp){
            if(resp.success){
               $("#updateModal").modal('hide');
               location.reload();
            } else {
               alert(resp.message);
            }
         });
   });

});