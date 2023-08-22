<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
	<title>Shopping Cart</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://www.dafontfree.net/embed/a29ibGVuei1zZXJpYWwtYm9sZCZkYXRhLzMzL2svMTQ3OTQ4L2tvYmxlbnotc2VyaWFsLWJvbGQudHRm" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="assets/css/cart.css">
</head>
<body>
	<main class="page">
	 	<section class="shopping-cart dark">
	 		<div class="container">
		        <div class="block-heading">
		          <h2>Shopping Cart</h2>
		          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo.</p>
		        </div>
		        <div class="content">
	 				<div class="row">
	 					<div class="col-md-12 col-lg-8">
	 						<div class="items">
	 							<c:if test="${sessionScope.cart == null || sessionScope.qty == 0}">
	 								<div class="no_product">
	 									<div class="col-md-12">
	 										<img style = "text-align:center;" class="img-fluid mx-auto d-block image" alt="" src="assets/images/cartEmpty.png">
	 									</div>
	 									<h2>oops!</h2>
	 									<h3>Your cart is Empty...</h3>
	 								</div>
	 							</c:if>
	 							<c:forEach items = "${sessionScope.cart.items}" var = "items">
					 				<div class="product">
					 					<div class="row">
						 					<div class="col-md-3">
						 						<img class="img-fluid mx-auto d-block image" src="${items.sanpham.hinhsp}">
						 					</div>
						 					<div class="col-md-8">
						 						<div class="info">
							 						<div class="row">
								 						<div class="col-md-5 product-name">
								 							<div class="product-name">
									 							<a href="#">${items.sanpham.tensp}</a>
									 							<div class="product-info">
										 							<div>Category: <span class="value">${items.sanpham.loaisp.tenloai}</span></div>
										 							<div>Price / product: <span class="value">${items.sanpham.giasp}</span></div>
										 						</div>
										 					</div>
								 						</div>
								 						<div class="col-md-4 quantity">
								 							<label for="quantity">Quantity:</label>
								 							<input onchange="updateQuantity(${items.sanpham.masp}, this.value)" id="quantity" min = "1" type="number" value ="${items.luongMua}" class="form-control quantity-input">
								 						</div>
								 						<div id="price_${items.sanpham.masp}" class="col-md-3 price">
								 							<span>${items.gia}$</span>
								 						</div>
								 						<div class="col-md-1 delete">
								 							<a href="deletecart?productId=${items.sanpham.masp}"><i class="fa fa-trash-o"></i></a>
								 						</div>
								 					</div>
								 				</div>
						 					</div>
						 				</div>
					 				</div>
				 				</c:forEach>
				 			</div>
			 			</div>
			 			<div class="col-md-12 col-lg-4">
			 				<div class="summary">
			 					<h3>Summary</h3>
			 					<div class="summary-item"><span class="text">Subtotal</span><span id="subtotal" class="price">${sessionScope.total == null ? 0 : sessionScope.total}$</span></div>
			 					<div class="summary-item"><span class="text">Discount</span><span class="price">0$</span></div>
			 					<div class="summary-item"><span class="text">Shipping</span><span class="price">0$</span></div>
			 					<div class="summary-item"><span class="text">Total</span><span id="total" class="price">${sessionScope.total == null ? 0 : sessionScope.total}$</span></div>
			 					<div class="d-flex justify-content-between">
			 						<div>
				 						<input onchange = "payMent();" type="radio" id="cash" name="payment" value="cash">
										<label for="payment">Cash</label>
									</div>
									<div>
										<input onchange="payMent();" type="radio" id="card" name="payment" value="card">
										<label for="cart">Credit Card</label>
									</div>
			 					</div>
			 					<div class="summary-item">
			 						<input type="text" id="cardnum" name="cardnum" value="" placeholder="Enter your credit card number" required style = "width:100%;display:none;">
			 					</div>
			 					<div class="summary-item"><span style ="color:red;" id ="error"></span></div>
			 					<button onclick="checkOut();" type="button" class="btn btn-primary btn-lg btn-block">Checkout</button>
				 			</div>
			 			</div>
		 			</div> 
		 		</div>
		 	<div class="continue">
		 		<a href="product"><i class="fa fa-arrow-circle-o-left"></i> Continue Shopping</a>
		 	</div>	
	 		</div>
		</section>
	</main>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
function updateQuantity(productId, newQuantity) {
	   var xhr = new XMLHttpRequest();
	   xhr.open("POST", "updatecart", true);
	   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	   xhr.onreadystatechange = function(){
	      if (xhr.readyState === 4 && xhr.status === 200){
	         var response = JSON.parse(xhr.responseText);
	         console.log(response);
	         	document.getElementById("price_" + productId).innerHTML = response.price+"$" ;
	         	document.getElementById("subtotal").innerHTML = response.total+"$";
	         	document.getElementById("total").innerHTML = response.total+"$";
	         }
	   };
	   xhr.send("product_id=" + productId + "&quantity=" + newQuantity);
}

function checkOut(){
	var payments= document.getElementsByName("payment");
	var payment; 
	var numcard = "";
	for (var i = 0; i < payments.length; i++) {
	  if (payments[i].checked) {
			payment = payments[i].value;
			if(payments[i].value == "card")
				numcard = document.getElementById("cardnum").value;
				console.log(numcard);
			if(is_creditCard(numcard) == false && numcard.trim().length != 0){
				var error = document.getElementById("error");
				error.innerHTML = "Your card is invalid !";
			}else{
				window.location.href = "checkout?payment=" + payment + "&numcard=" + numcard;
			}			
	  }
	}
}
function payMent(){
	var card = document.getElementById("card");
	var number = document.getElementById("cardnum");
	if(card.checked)
		number.style.display = "block";
	else{
		number.style.display = "none";
		number.value = null;
	}
}

function is_creditCard(str)
{
 regexp = /^(?:(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})|(6(?:011|5[0-9]{2})[0-9]{12})|(3[47][0-9]{13})|(3(?:0[0-5]|[68][0-9])[0-9]{11})|((?:2131|1800|35[0-9]{3})[0-9]{11}))$/;

		if (regexp.test(str))
		  {
			return true;
		  }
		else
		  {
			return false;
		  }
}
</script>
</body>
</html>
