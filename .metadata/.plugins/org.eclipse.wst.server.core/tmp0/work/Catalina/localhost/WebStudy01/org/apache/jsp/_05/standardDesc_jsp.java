/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.90
 * Generated at: 2023-09-12 00:53:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp._05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;

public final class standardDesc_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

 // field 나 method 선언 (전역변수)
	 			public void test(){}
	 		
  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Date");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h4>JSP 스펙과 Container</h4>\r\n");
      out.write("<pre>\r\n");
      out.write("	JSP(Jakarta Server Page) : 자바와 서블릿 스펙을 기반으로 한 템플릿 엔진\r\n");
      out.write("	JSP container : 템플릿 엔진의 제공자로 jsp 인스턴스의 생명주기 관리자\r\n");
      out.write("	JSP container 동작 단계\r\n");
      out.write("	 1. jsp 템플릿 파일로부터 서블릿 소스 생성\r\n");
      out.write("	 2. 컴파일\r\n");
      out.write("	 3. jsp 인스턴스(싱글턴) 생성\r\n");
      out.write("	 4. request callback 호출 후 응답 전송\r\n");
      out.write("	\r\n");
      out.write("	스펙에 따른 소스 구성 요소\r\n");
      out.write("	 1. 정적 텍스트, HTML, JS, CSS ... : Front-End 정적 요소\r\n");
      out.write("	 2. Back_End 요소(script 요소)\r\n");
      out.write("	 	1) scriptlet : \r\n");
      out.write("	 		");
 // java code -> request callback 메소드의 지역 코드화
	 			Date today = new Date();
	 		
      out.write("\r\n");
      out.write("	 	2) directive(지시자) : 실행코드나 응답 컨텐츠를 직접 구성하지 않고, 환경 설정에 사용. &lt;%@ page %&gt;\r\n");
      out.write("	 		- page (required) : jsp 페이지 자체에 대한 설정 변경(속성으로 변경함.)\r\n");
      out.write("	 			ex) 버퍼의 사용여부, 버퍼의 용량, 세션 사용 여부, el 사용여부 등...\r\n");
      out.write("	 		- taglib (optional)\r\n");
      out.write("	 		- include (optional)\r\n");
      out.write("	 	3) expression(표현식) : ");
      out.print( today );
      out.write("\r\n");
      out.write("	 	4) declaration(선언부) : \r\n");
      out.write("	 		");
      out.write("\r\n");
      out.write("	 	5) comment\r\n");
      out.write("<!-- 	 		comment1 -->\r\n");
      out.write("			");
      out.write("\r\n");
      out.write("	 		- client side comment : HTML comment, JS comment, CSS comment  -> 가능하면 사용하지 않도록\r\n");
      out.write("	 		- server side comment : Java comment, JSP comment\r\n");
      out.write("	 	6) action tag\r\n");
      out.write("	 	7) EL(Expression Language, 표현 언어), ${elVariables}\r\n");
      out.write("	 	8) JSTL(Jsp Standard Tag Library)\r\n");
      out.write("</pre>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("// 	comment2\r\n");
      out.write("</script>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
