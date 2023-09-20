/**
 * 
 */

$(function(){
	console.log(this);
//	var contextPath = this.body.dataset.contextPath;
	const cPath = $(this.body).data("contextPath");
	
	let makeTrTag = function(adrsVO){
		let trTag =`
				<tr>
					<td>${adrsVO.adrsName}</td>
					<td>${adrsVO.adrsHp}</td>
					<td>${adrsVO.adrsAdd}</td>
				</tr>
			`;
		return trTag;
	};
	
	let url = `${cPath}/adrs/address`;
	$.getJSON(url, function(resp){
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
	
});