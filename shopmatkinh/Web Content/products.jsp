<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html >

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Hexashop - Product Listing Page</title>


    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">

    <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="assets/css/owl-carousel.css">

    <link rel="stylesheet" href="assets/css/lightbox.css">
<!--

TemplateMo 571 Hexashop

https://templatemo.com/tm-571-hexashop

-->
    </head>
    
    <body>
    
    <!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>  
    <!-- ***** Preloader End ***** -->
    <!-- ***** Header Area Start ***** -->
    <header class="header-area header-sticky">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <%@ include file="nav.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!-- ***** Header Area End ***** -->

    <!-- ***** Main Banner Area Start ***** -->
    <div class="page-heading" id="top">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="inner-content">
                        <h2>Check Our Products</h2>
                        <span>Awesome &amp; Creative HTML CSS layout by TemplateMo</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Main Banner Area End ***** -->


    <!-- ***** Products Area Starts ***** -->
    <section class="section" id="products">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-heading">
                        <h2>Our Latest Products</h2>
                        <span>Check out all of our products.</span>
                    </div>
                </div>
            </div>
        </div>
   <!-- ***** Filter Product***** -->
   <div class="container">
   		<form name="sfrm" action="./filterproduct" method = "post" onsubmit = "return checkMinMax();">
   	 		<div class="d-flex justify-content-center">
				<div class="col-lg-2">
				    <select name="thutu" id="thutu">
						 <option value="">Thứ tự mặc định</option>
						 <option value="1">Tăng dần</option>
						 <option value="-1">Giảm dần</option>
					</select>
				</div>
				<div class="col-lg-3">
						<input type="text" style="width:100%;" id="name" name="name" placeholder = "Enter your product name">
				</div>
				<div class="slidecontainer d-flex align-items-center col-lg-3">
					<div class = "col-lg-6">
						<input  style = "width:100%;" name="min" type="number" id="min" placeholder = "Min price">
					</div>
					<div class = "col-lg-6">
						<input style = "width:100%;" name= "max" type ="number"  id ="max" placeholder = "Max price">
					</div>
				</div>
				<input type = "hidden" name = "filter" value ="yes">
				<button class ="ml-2" type="submit">Lọc</button>
			</div>
		</form>
   </div>
    <!-- *****End Filter Product***** -->
        <div class="container">
            <div class="row">
			<c:forEach items ="${listP}" var = "p">
                <div class="col-lg-4">
                    <div class="item">
                        <div class="thumb">
                            <div class="hover-content">
                                <ul>
                                    <li><a href="singleproduct?id=${p.masp}"><i class="fa fa-eye"></i></a></li>
                                    <c:if test="${p.soluong >= 1}">
                                    	<li><a href="addtocart?productId=${p.masp}"><i class="fa fa-shopping-cart"></i></a></li>
                           			</c:if>
                                </ul>
                            </div>
                            <img src="${p.hinhsp}" alt="">
                        </div>
                        <div class="down-content">
                            <h4>${p.tensp}</h4>
                            <c:choose>
                            	<c:when test="${p.soluong == 0}">
                            		<span class = "zero">Out Of Stock</span>
                            	</c:when>
                            	<c:otherwise>
                            		<span>${p.giasp}</span>
                            	</c:otherwise>
                            </c:choose>
                            <ul class="stars">
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                                <li><i class="fa fa-star"></i></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
             </div>
                <div class="col-lg-12">
                    <div class="pagination">
                        <ul>
		   				     <c:if test="${tag > 1 && tag <= endPage}">
		   						<li>
		                          	<c:choose>
		                            	<c:when test="${action!=null}">
		                            		<c:choose>
			                            		<c:when test="${category!=null}">
			                            			<a href="filterproduct?index=${tag-1}&category=${category}"><</a>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<a href="filterproduct?index=${tag-1}"><</a>
			                            		</c:otherwise>
			                            	</c:choose>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<c:choose>
			                            		<c:when test="${category!=null}">
			                            			<a href="product?index=${tag-1}&category=${category}"><</a>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<a href="product?index=${tag-1}"><</a>
			                            		</c:otherwise>
			                            	</c:choose>
		                            	</c:otherwise>
		                            </c:choose>
		                        </li>
		                    </c:if> 
	                        <c:forEach begin = "1" end = "${endPage}" var ="i"> 
	                            <li class="${tag == i?"active":""}" >
                            			<c:choose>
		                            	<c:when test="${action!=null}">
		                            		<c:choose>
			                            		<c:when test="${category!=null}">
			                            			<a href="filterproduct?index=${i}&category=${category}">${i}</a>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<a href="filterproduct?index=${i}">${i}</a>
			                            		</c:otherwise>
		                            		</c:choose>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<c:choose>
			                            		<c:when test="${category!=null}">
			                            			<a href="product?index=${i}&category=${category}">${i}</a>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<a href="product?index=${i}">${i}</a>
			                            		</c:otherwise>
		                            		</c:choose>
		                            	</c:otherwise>
		                            </c:choose>	
	                            </li>
	                        </c:forEach>
		   					<c:if test="${tag < endPage}">
		   						<li>
			   						<c:choose>
		                            	<c:when test="${action!=null}">
		                            		<c:choose>
			                            		<c:when test="${category!=null}">
			                            			<a href="filterproduct?index=${tag+1}&category=${category}">></a>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<a href="filterproduct?index=${tag+1}">></a>
			                            		</c:otherwise>
			                            	</c:choose>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<c:choose>
			                            		<c:when test="${category!=null}">
			                            			<a href="product?index=${tag+1}&category=${category}">></a>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<a href="product?index=${tag+1}">></a>
			                            		</c:otherwise>
			                            	</c:choose>
		                            	</c:otherwise>
		                            </c:choose>	
		                        </li>
		                    </c:if >           
                        </ul>
                    </div>
                </div>
            </div>
    </section>
    <!-- ***** Products Area Ends ***** -->
    
    <!-- ***** Footer Start ***** -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="first-item">
                        <div class="logo">
                            <img src="assets/images/white-logo.png" alt="hexashop ecommerce templatemo">
                        </div>
                        <ul>
                            <li><a href="#">16501 Collins Ave, Sunny Isles Beach, FL 33160, United States</a></li>
                            <li><a href="#">hexashop@company.com</a></li>
                            <li><a href="#">010-020-0340</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3">
                    <h4>Shopping &amp; Categories</h4>
                    <ul>
                        <li><a href="#">Menâs Shopping</a></li>
                        <li><a href="#">Womenâs Shopping</a></li>
                        <li><a href="#">Kid's Shopping</a></li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <h4>Useful Links</h4>
                    <ul>
                        <li><a href="#">Homepage</a></li>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Help</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <h4>Help &amp; Information</h4>
                    <ul>
                        <li><a href="#">Help</a></li>
                        <li><a href="#">FAQ's</a></li>
                        <li><a href="#">Shipping</a></li>
                        <li><a href="#">Tracking ID</a></li>
                    </ul>
                </div>
                <div class="col-lg-12">
                    <div class="under-footer">
                        <p>Copyright Â© 2022 HexaShop Co., Ltd. All Rights Reserved. 
                        
                        <br>Design: <a href="https://templatemo.com" target="_parent" title="free css templates">TemplateMo</a></p>
                        <ul>
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-behance"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    

    <!-- jQuery -->
    <script src="assets/js/jquery-2.1.0.min.js"></script>

    <!-- Bootstrap -->
    <script src="assets/js/popper.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Plugins -->
    <script src="assets/js/owl-carousel.js"></script>
    <script src="assets/js/accordions.js"></script>
    <script src="assets/js/datepicker.js"></script>
    <script src="assets/js/scrollreveal.min.js"></script>
    <script src="assets/js/waypoints.min.js"></script>
    <script src="assets/js/jquery.counterup.min.js"></script>
    <script src="assets/js/imgfix.min.js"></script> 
    <script src="assets/js/slick.js"></script> 
    <script src="assets/js/lightbox.js"></script> 
    <script src="assets/js/isotope.js"></script> 
    
    <!-- Global Init -->
    <script src="assets/js/custom.js"></script>

    <script>
        $(function() {
            var selectedClass = "";
            $("p").click(function(){
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
                $("#portfolio div").not("."+selectedClass).fadeOut();
            setTimeout(function() {
              $("."+selectedClass).fadeIn();
              $("#portfolio").fadeTo(50, 1);
            }, 500);
                
            });
        });
		
        function checkMinMax() {
        	  var smin = document.sfrm.min.value;
        	  var smax = document.sfrm.max.value;
        	  var min = parseFloat(smin);
        	  var max = parseFloat(smax);
        	  if (smin.trim().length == 0 && smax.trim().length != 0 || smin.trim().length != 0 && smax.trim().length == 0) {
        	    alert("Vui lòng nhập giá trị cho cả hai ô!");
        	    return false;
        	  }else if(smin.trim().length != 0 && smax.trim().length != 0 && min > max){
        		  alert("Vui lòng nhập min bé hơn max !")
        		  return false;
        	  }
        }
	</script>

  </body>

</html>
