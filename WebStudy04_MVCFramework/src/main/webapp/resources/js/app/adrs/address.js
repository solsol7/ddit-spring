/**
 * 
 */

$(function(){
	console.log(this);
//	var contextPath = this.body.dataset.contextPath;
	const cPath = $(this.body).data("contextPath");
	
	let makeTrTag = function(adrsVO){
		let trTag =`
				<tr data-adrs-no="${adrsVO.adrsNo}">
					<td class="tbAdrsName">${adrsVO.adrsName}</td>
					<td class="tbAdrsHp">${adrsVO.adrsHp}</td>
					<td class="tbAdrsAdd">${adrsVO.adrsAdd}</td>
					<td><input type="button" value="삭제" class="delBtn"/></td>
						<!-- Button trigger modal -->
					<td><button type="button" class="btn btn-primary modBtn" data-bs-toggle="modal" data-bs-target="#exampleModal">
					  수정하기
					</button></td>
				</tr>
			`;
		return trTag;
	};
	
	const baseUrl = `${cPath}/adrs/address`;
	$.getJSON(baseUrl, function(resp){
	//	메소드가 이미 get, 데이터타입은 이미 json- 나머지조건만 주면 됨
		let adrsList = resp.adrsList;
		trTags="";
		if(adrsList?.length>0){
		//adrsList&&adrsList.length>0
			$.each(adrsList,function(){
				trTags+= makeTrTag(this);
				
				
			});
		}else{
			trTags += `
				<tr>
					<td colspan='3'>주소록 없음.</td>
				</tr>
			`;
		}// if~else end
		$(listBody).html(trTags);
	});	//getJSON end
	
	
	$(adrsForm).on('submit',function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serializeJSON();
		let json = JSON.stringify(data);
		let settings = {
			url : baseUrl,
			method : method,
			data : json,
			headers :{
				"Content-Type":"application/json; charset=UTF-8"
			},
			dataType : "json"
			
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					let trTag = makeTrTag(resp.originalData);
					$(listBody).prepend(trTag);
					adrsForm.reset();
				}else{
					alert(resp.message);
				}
			});
		
		return false;
	})
	
	
	$(listBody).on('click','.delBtn',function(){
		let flag = confirm("삭제 할텨?");
		if(!flag) return false;
		
		let adrsTr = $(this).parents("tr:first");
		let $adrsTr = $(adrsTr);
		let adrsNo = $adrsTr.data("adrsNo");
		let url = `${baseUrl}/${adrsNo}`;
		
		let settings = {
			url : url,
			method : "delete",
			dataType : "json"
		}
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					$adrsTr.remove();
				}else{
					alert(resp.message)
				}
			})
	});
	

	$(listBody).on('click','.modBtn', function(){
		
		let adrsTr = $(this).parents("tr:first");
		let $adrsTr = $(adrsTr);
		let adrsNo = $adrsTr.data("adrsNo");
		let adrsName = $(this).parent().siblings('.tbAdrsName').text();
		let adrsHp = $(this).parent().siblings('.tbAdrsHp').text();
		let adrsAdd = $(this).parent().siblings('.tbAdrsAdd').text();
		
		$('#modAdrsName').val(adrsName);
		$('#modAdrsHp').val(adrsHp);
		$('#modAdrsAdd').val(adrsAdd);
		$('#modAdrsNo').val(adrsNo);

	});
	
	$('#saveBtn').on('click',function(){
		$('#modalForm').submit();
	})
	
	$(modalForm).on('submit',function(event){
		event.preventDefault();

		let data = $(this).serializeJSON();
		let json = JSON.stringify(data);
		console.log(json);
		let settings = {
			url : baseUrl,
			method : "put",
			data : json,
			headers :{
				"Content-Type":"application/json; charset=UTF-8"
			},
			dataType : "json"
			
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					alert("성공");
					$('#exampleModal').modal('hide');
					let adrsNo = resp.originalData.adrsNo;
					let adrsName = resp.originalData.adrsName;
					let adrsHp = resp.originalData.adrsHp;
					let adrsAdd = resp.originalData.adrsAdd;
					$('tr[data-adrs-no='+adrsNo+']').children('.tbAdrsName').text(adrsName);
					$('tr[data-adrs-no='+adrsNo+']').children('.tbAdrsHp').text(adrsHp);
					$('tr[data-adrs-no='+adrsNo+']').children('.tbAdrsAdd').text(adrsAdd);
				}else{
					alert(resp.message);
				}
			});
		
		return false;
	})
	
});
