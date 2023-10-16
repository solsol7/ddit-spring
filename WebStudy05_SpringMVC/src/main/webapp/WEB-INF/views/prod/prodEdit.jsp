<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
	th{
		text-align: center;
	}
	td{
		padding-bottom: 2px;
	}
</style>

<form:form enctype="multipart/form-data" modelAttribute="prod">
<!--	모델명 -> 여기까지 오기 직전에 컨트롤러에서 모델에 addAttribute한 것 -->
<!--	메소드는 post로 초기화되기 때문에 굳이 안써도됨 -->
<!--	액션도 현재페이지로 초기화됨 -->
	<form:hidden path="prodId" />
	<table class="col-md-6">
		<tr>
			<th><label for="prodName">상품명</label></th>
			<td>
				<form:input path="prodName" class="form-control" required="true" />
<!--	path -> name, id 자동부여 ... model로 전달되고있는 커맨드 오브젝트의 프로퍼티명 -->
<!-- 	name속성 -> path ..모델로 전달된 커맨드 오브젝트의 프로퍼티 네임이 반영됨 -->
<!--	value는 사용할 필요가 없는거지 사용하지 못하는것은 아님 -->
<!--	value는 modelAttribute와 path를 조합해서 생성해줌 -->
				<form:errors path="prodName" element="span" cssClass="error"></form:errors>
<!-- 				검증에 걸려서 에러가 존재할 때-->
<!-- 				path -> prodVO가 가지고있는 어떤 프로퍼티에 대한 검증 결과 -->
<!-- 				element="span" -> 스판태그 만들어줘  -->
			</td>
			<tr>
			<th><label for="prodLgu">상품분류</label></th>
			<td>
				<form:select path="prodLgu" id="prodLgu" class="form-select" required="true"
				items="${lprodList }" itemLabel="lprodNm" itemValue="lprodGu" />
				<form:errors path="prodLgu" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodBuyer">제조사</label></th>
			<td>
					<form:select path="prodBuyer" class="form-select">
						<option value>제조사</option>
							<c:forEach items="${buyerList }" var="buyer">
								<form:option class="${buyer.buyerLgu}" value="${buyer.buyerId }" label="${buyer.buyerName }" />
							</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodCost">구매가</label></th>
			<td>
				<form:input path="prodCost" class="form-control" required="true"/>
				<form:errors path="prodCost" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodPrice">판매가</label></th>
			<td>
				<form:input type="number" path="prodPrice" class="form-control" required="true"/>
				<form:errors path="prodPrice" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodSale">세일가</label></th>
			<td>
				<form:input type="number" path="prodSale" class="form-control" required="true"/>
				<form:errors path="prodSale" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodOutline">요약정보</label></th>
			<td>
				<form:input path="prodOutline" class="form-control" required="true"/>
				<form:errors path="prodOutline" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodDetail">상세정보</label></th>
			<td>
				<form:input path="prodDetail" class="form-control" required="true"/>
				<form:errors path="prodDetail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodImage">이미지</label></th>
			<td>
				<input type="file" name="prodImage" id="prodImage" class="form-control"/>
<!-- 	커스텀태그는 초기값을 쉽게 바인딩하기 위해 사용-> file에는 value가 의미가 없으니까 커스텀태그 안쓰는게 나음 -->
				<form:errors path="prodImg" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodTotalstock">총재고</label></th>
			<td>
				<form:input type="number" path="prodTotalstock" class="form-control" required="true"/>
				<form:errors path="prodTotalstock" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodInsdate">입고일</label></th>
			<td>
				<form:input type="date" path="prodInsdate" class="form-control" required="true"/>
				<form:errors path="prodInsdate" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodProperstock">적정재고</label></th>
			<td>
				<form:input type="number" path="prodProperstock" class="form-control" required="true"/>
				<form:errors path="prodProperstock" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodSize">크기</label></th>
			<td>
				<form:input path="prodSize" class="form-control" required="true"/>
				<form:errors path="prodSize" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodColor">색상</label></th>
			<td>
				<form:input path="prodColor" class="form-control" required="true"/>
				<form:errors path="prodColor" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodDelivery">배송방법</label></th>
			<td>
				<form:input path="prodDelivery" class="form-control" required="true"/>
				<form:errors path="prodDelivery" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodUnit">단위</label></th>
			<td>
				<form:input path="prodUnit" class="form-control" required="true"/>
				<form:errors path="prodUnit" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodQtyin">입고량</label></th>
			<td>
				<form:input type="number" path="prodQtyin" class="form-control" required="true"/>
				<form:errors path="prodQtyin" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodQtysale">판매량</label></th>
			<td>
				<form:input type="number" path="prodQtysale" class="form-control" required="true"/>
				<form:errors path="prodQtysale" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="prodMileage">마일리지</label></th>
			<td>
				<form:input type="number" path="prodMileage" class="form-control" required="true"/>
				<form:errors path="prodMileage" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="row g-2 d-flex justify-content-center mt-1">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">저장</button>
					</div>
					<div class="col-auto">
						<button type="reset" class="btn btn-warning">취소</button>
					</div>
					<div class="col-auto">	
						<a class="btn btn-secondary" href="<c:url value='/prod/prodList.do'/>">목록으로</a>
					</div>		
				</div>
			</td>
		</tr>
	</table>
</form:form>

<!-- --------------------------------------------------------- -->
<!-- 
<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="prodId" value="${prod.prodId}"/>
	<table class="col-md-6">
		<tr>
			<th><label for="prodName">상품명</label></th>
			<td>
				<input type="text" name="prodName" id="prodName" class="form-control" required
					value="${prod.prodName}" />
				<span class="error">${errors.prodName}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodLgu">상품분류</label></th>
			<td>
				<select name="prodLgu" id="prodLgu" class="form-select" required>
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
					</c:forEach>
				</select>
				<span class="error">${errors.prodLgu}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodBuyer">제조사</label></th>
			<td>
				<select name="prodBuyer" id="prodBuyer" class="form-select" required>
					<option value>제조사</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option class="${buyer.buyerLgu	 }" label="${buyer.buyerName }" value="${buyer.buyerId }" />
					</c:forEach>
				</select>
				<span class="error">${errors.prodBuyer}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodCost">구매가</label></th>
			<td>
				<input type="number" name="prodCost" id="prodCost" class="form-control" required
					value="${prod.prodCost}" />
				<span class="error">${errors.prodCost}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodPrice">판매가</label></th>
			<td>
				<input type="number" name="prodPrice" id="prodPrice" class="form-control" required
					value="${prod.prodPrice}" />
				<span class="error">${errors.prodPrice}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodSale">세일가</label></th>
			<td>
				<input type="number" name="prodSale" id="prodSale" class="form-control" required
					value="${prod.prodSale}" />
				<span class="error">${errors.prodSale}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodOutline">요약정보</label></th>
			<td>
				<input type="text" name="prodOutline" id="prodOutline" class="form-control" required
					value="${prod.prodOutline}" />
				<span class="error">${errors.prodOutline}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodDetail">상세정보</label></th>
			<td>
				<input type="text" name="prodDetail" id="prodDetail" class="form-control"
					value="${prod.prodDetail}" />
				<span class="error">${errors.prodDetail}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodImage">이미지</label></th>
			<td>
				<input type="file" name="prodImage" id="prodImage" class="form-control"/>
				<span class="error">${errors.prodImg}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodTotalstock">총재고</label></th>
			<td>
				<input type="number" name="prodTotalstock"
				id="prodTotalstock" class="form-control" required
				value="${prod.prodTotalstock}" />
				<span class="error">${errors.prodTotalstock}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodInsdate">입고일</label></th>
			<td>
				<input type="date" name="prodInsdate" id="prodInsdate" class="form-control"
					value="${prod.prodInsdate}" />
				<span class="error">${errors.prodInsdate}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodProperstock">적정재고</label></th>
			<td>
				<input type="number" name="prodProperstock" id="prodProperstock" class="form-control" required
				value="${prod.prodProperstock}" />
				<span class="error">${errors.prodProperstock}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodSize">크기</label></th>
			<td>
				<input type="text" name="prodSize" id="prodSize" class="form-control"
					value="${prod.prodSize}" />
				<span class="error">${errors.prodSize}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodColor">색상</label></th>
			<td>
				<input type="text" name="prodColor" id="prodColor" class="form-control"
					value="${prod.prodColor}" />
				<span class="error">${errors.prodColor}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodDelivery">배송방법</label></th>
			<td>
				<input type="text" name="prodDelivery" id="prodDelivery" class="form-control"
					value="${prod.prodDelivery}" />
				<span class="error">${errors.prodDelivery}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodUnit">단위</label></th>
			<td>
				<input type="text" name="prodUnit" id="prodUnit" class="form-control"
					value="${prod.prodUnit}" />
				<span class="error">${errors.prodUnit}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodQtyin">입고량</label></th>
			<td>
				<input type="number" name="prodQtyin" id="prodQtyin" class="form-control"
					value="${prod.prodQtyin}" />
				<span class="error">${errors.prodQtyin}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodQtysale">판매량</label></th>
			<td>
				<input type="number" name="prodQtysale" id="prodQtysale" class="form-control"
					value="${prod.prodQtysale}" />
				<span class="error">${errors.prodQtysale}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodMileage">마일리지</label></th>
			<td>
				<input type="number" name="prodMileage" id="prodMileage" class="form-control"
					value="${prod.prodMileage}" />
				<span class="error">${errors.prodMileage}</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="row g-2 d-flex justify-content-center mt-1">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">저장</button>
					</div>
					<div class="col-auto">
						<button type="reset" class="btn btn-warning">취소</button>
					</div>
					<div class="col-auto">	
						<a class="btn btn-secondary" href="<c:url value='/prod/prodList.do'/>">목록으로</a>
					</div>		
				</div>
			</td>
		</tr>
	</table>
</form>
 -->
<script>
let $prodBuyer = $("select[name=prodBuyer]");
$("select[name=prodLgu]").on("change", function(event){
	let lgu = $(this).val();
	let $options = $("select[name=prodBuyer]").find("option");
	$options.hide();
	$options.filter((i,e)=>i==0).show();
	if(lgu){
		$options.filter(`.\${lgu}`).show();
	}else{
		$options.show();
	}
}).change();
</script>