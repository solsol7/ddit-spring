package egovframework.let.sym.ccm.ccc.service;

import java.util.List;

/**
 *
 * 공통분류코드에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * </pre>
 */
public interface EgovCcmCmmnClCodeManageService {

	/**
	 * 공통분류코드를 삭제한다.
	 * @param cmmnClCode
	 * @throws Exception
	 */
	void deleteCmmnClCode(CmmnClCode cmmnClCode) throws Exception;

	/**
	 * 공통분류코드를 등록한다.
	 * @param cmmnClCode
	 * @throws Exception
	 */
	void insertCmmnClCode(CmmnClCode cmmnClCode) throws Exception;

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * @param cmmnClCode
	 * @return CmmnClCode(공통분류코드)
	 * @throws Exception
	 */
	 CmmnClCode selectCmmnClCodeDetail(CmmnClCode cmmnClCode) throws Exception;

	/**
	 * 공통분류코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통분류코드 목록)
	 * @throws Exception
	 */
	List<?> selectCmmnClCodeList(CmmnClCodeVO searchVO) throws Exception;

    /**
	 * 공통분류코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통분류코드 총 갯수)
     */
    int selectCmmnClCodeListTotCnt(CmmnClCodeVO searchVO) throws Exception;

	/**
	 * 공통분류코드를 수정한다.
	 * @param cmmnClCode
	 * @throws Exception
	 */
	void updateCmmnClCode(CmmnClCode cmmnClCode) throws Exception;

}