<nav class="main-nav">
 	<!-- ***** Logo Start ***** -->
    <a href="index" class="logo">
       	<img src="assets/images/logo.png">
    </a>
    	<!-- ***** Logo End ***** -->
        <!-- ***** Menu Start ***** -->
        	<ul class="nav">
            	<li class="scroll-to-section"><a href="index" class="active">Home</a></li>
            	<% if (request.getRequestURI().endsWith("index.jsp")) { %>
				     <li class="scroll-to-section"><a href="#men">Men's</a></li>
                	 <li class="scroll-to-section"><a href="#women">Women's</a></li>
                	 <li class="scroll-to-section"><a href="#kids">Kid's</a></li>
				<% } else { %>
				   	<li class="scroll-to-section"><a href="product?category=Men">Men's</a></li>
                	<li class="scroll-to-section"><a href="product?category=Women">Women's</a></li>
                	<li class="scroll-to-section"><a href="product?category=Kid">Kid's</a></li>
				<% } %>
                <li class="scroll-to-section"><a href="#explore">Explore</a></li>
                <li class="submenu">
                	<a href="javascript:;">Pages</a>
                   	<ul>
                      	<li><a href="about.jsp">About Us</a></li>
                      	<li><a href="product">Products</a></li>
                        <li><a href="contact.jsp">Contact Us</a></li>
                  	</ul>
                </li>
                	<c:if test="${sessionScope.acc != null}">
                    	<li class="scroll-to-section"><a href="logout">Log Out</a></li>
				 	</c:if>
					<c:if test="${sessionScope.acc == null}">
						<li class="scroll-to-section"><a href="login">Log In</a></li>
                        <li class="scroll-to-section"><a href="signup">Sign Up</a></li>
					</c:if>
					<c:if test="${sessionScope.acc.admin == 1 && sessionScope.acc != null}">
						<li class="scroll-to-section"><a href="manageproduct">Admin</a></li>
					</c:if>
					<c:choose>
		      			<c:when test="${sessionScope.qty == 0 || sessionScope.qty == null}">
		                   	<li class="scroll-to-section"><a href="cart.jsp"><i class="fa fa-shopping-cart"></i>(0)</a></li>
						</c:when>
						<c:otherwise>
		   					<li class="scroll-to-section"><a href="cart.jsp"><i class="fa fa-shopping-cart"></i>(${sessionScope.qty})</a></li>       
		 	 			</c:otherwise>
	                </c:choose>
              </ul>         
      <a class='menu-trigger'>
         <span>Menu</span>
      </a>
              <!-- ***** Menu End ***** -->
</nav>