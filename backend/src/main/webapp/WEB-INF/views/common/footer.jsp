<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- Footer-->
	<footer class="footer bg-light">
	    <div class="container">
	        <div class="row">
	            <div class="col-lg-6 h-100 text-center text-lg-start my-auto">
	                <ul class="list-inline mb-2">
	                    <li class="list-inline-item"><a href="#!">About</a></li>
	                    <li class="list-inline-item">⋅</li>
	                    <li class="list-inline-item"><a href="#!">Contact</a></li>
	                    <li class="list-inline-item">⋅</li>
	                    <li class="list-inline-item"><a href="#!">Terms of Use</a></li>
	                    <li class="list-inline-item">⋅</li>
	                    <li class="list-inline-item"><a href="#!">Privacy Policy</a></li>
	                </ul>
	                <p class="text-muted small mb-4 mb-lg-0">&copy; Your Website 2024. All Rights Reserved.</p>
	            </div>
	            <div class="col-lg-6 h-100 text-center text-lg-end my-auto">
	                <ul class="list-inline mb-0">
	                    <li class="list-inline-item me-4">
	                        <a href="#!"><i class="bi-facebook fs-3"></i></a>
	                    </li>
	                    <li class="list-inline-item me-4">
	                        <a href="#!"><i class="bi-twitter fs-3"></i></a>
	                    </li>
	                    <li class="list-inline-item">
	                        <a href="#!"><i class="bi-instagram fs-3"></i></a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</footer>
	</body>
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script>
	    document.addEventListener('DOMContentLoaded', function() {
	    	var loggedIn = <% out.print(session.getAttribute("userinfo") != null ? "true" : "false"); %>;
	        console.log(loggedIn);
	        if (loggedIn) {
	            document.getElementById('visitor-menu').style.display = 'none';
	            document.getElementById('member-menu').style.display = 'block';
	        } else {
	            document.getElementById('visitor-menu').style.display = 'block';
	            document.getElementById('member-menu').style.display = 'none';
	        }
	    });
	</script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</html>
